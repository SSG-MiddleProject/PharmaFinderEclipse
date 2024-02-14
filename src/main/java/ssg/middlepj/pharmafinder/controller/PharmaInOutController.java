package ssg.middlepj.pharmafinder.controller;

import java.time.YearMonth;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import message.ResultMsg;
import ssg.middlepj.pharmafinder.dto.PharmaDailyInOutSearchByPeriodParam;
import ssg.middlepj.pharmafinder.dto.PharmaInDetailDto;
import ssg.middlepj.pharmafinder.dto.PharmaInDto;
import ssg.middlepj.pharmafinder.dto.PharmaInOutDto;
import ssg.middlepj.pharmafinder.dto.PharmaOutDetailDto;
import ssg.middlepj.pharmafinder.dto.PharmaOutDto;
import ssg.middlepj.pharmafinder.service.PharmaInService;
import ssg.middlepj.pharmafinder.service.PharmaOutService;
import util.PharmaInOutCalendarUtil;

@Controller
public class PharmaInOutController {

	@Autowired
	private PharmaInService inService;

	@Autowired
	private PharmaOutService outService;

	// 처음 진입점
	@GetMapping(value = "/pharma-inout-calendar.do")
	public String pharmaInOutCalendar(String syear, String smonth, Model model, HttpServletRequest request) {
		System.out.println("pharmaInOutCalendar list()");
		request.getSession().setAttribute("storeId", 9999);
		int storeId = (int)request.getSession().getAttribute("storeId");
		// 달력에 표시해줄 데이터
		Calendar cal = Calendar.getInstance();
		//Calnedar가 1일로 맞춰짐
		cal.set(Calendar.DATE, 1);

		// 현재 연도와 월을 구한다	-> 처음 이 페이지가 실행시에 적용
		int year = cal.get(Calendar.YEAR);
		// 넘어온 파라미터 값이 있음, null이나 ""이 아니다
		if(PharmaInOutCalendarUtil.nvl(syear) == false){	// 넘어 온 파라미터 값이 있음
			year = Integer.parseInt(syear);
		}

		int month = cal.get(Calendar.MONTH) + 1;	// 0 ~ 11 까지이므로

		if(PharmaInOutCalendarUtil.nvl(smonth) == false){
			month = Integer.parseInt(smonth);
		}

		if(month < 1){
			month = 12;
			year--;
		}

		if(month > 12){
			month = 1;
			year++;
		}

		// Calendar의 month는 0 ~ 11까지 이므로 실제값 -1, 마지막 인수 1일
		cal.set(year, month-1, 1);

		// 요일					(주의 일)요일임
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

		// <<	year-- 작년 버튼
		String pp = String.format("<a href='./pharma-inout-calendar.do?syear=%d&smonth=%d' style='text-decoration:none'>"
								+ 		"<img src='./images/left.png' width='20px' height='20px'>"
								+ "</a>",  year-1, month);

		// <	month-- 저번달 버튼
		String p = String.format("<a href='./pharma-inout-calendar.do?syear=%d&smonth=%d' style='text-decoration:none'>"
								+ 		"<img src='./images/prev.png' width='20px' height='20px'>"
								+ "</a>",  year, month-1);

		// >	month++ 다음달 버튼
		String n = String.format("<a href='./pharma-inout-calendar.do?syear=%d&smonth=%d' style='text-decoration:none'>"
								+ 		"<img src='./images/next.png' width='20px' height='20px'>"
								+ "</a>",  year, month+1);

		// >>	year++ 내년 버튼
		String nn = String.format("<a href='./pharma-inout-calendar.do?syear=%d&smonth=%d' style='text-decoration:none'>"
								+ 		"<img src='./images/last.png' width='20px' height='20px'>"
								+ "</a>",  year+1, month);

		// 해당 년도와 월에 대한 YearMonth 객체 생성
        YearMonth yearMonth = YearMonth.of(year, month);

        // 해당 월의 마지막 날짜 구하기
        int lastDayOfMonth = yearMonth.lengthOfMonth();


        String stratDate = PharmaInOutCalendarUtil.toDashYYYYMMdd(year, month, 1);
        String endDate = PharmaInOutCalendarUtil.toDashYYYYMMdd(year, month, lastDayOfMonth);

		PharmaDailyInOutSearchByPeriodParam param = new PharmaDailyInOutSearchByPeriodParam(storeId, stratDate, endDate);

		// 202401
		//List<CalendarDto> list = dao.getCalendarList(9999, year + CalendarUtil.two(month + ""));
		List<PharmaInDto> inDtoes = inService.selectDailyInputs(param);
		List<PharmaOutDto> outDtoes = outService.selectDailyOutputs(param);

		// {day, [in, out]}
		Map<Integer, PharmaInOutDto[]> resultMap = new HashMap<Integer, PharmaInOutDto[]>();

		for(int i = 1; i <= lastDayOfMonth; i++) {
			PharmaInOutDto[] inout = new PharmaInOutDto[2];
			resultMap.put(i, inout);
		}

		for (PharmaInDto pharmaInDto : inDtoes) {
			String day = pharmaInDto.getCurrentInputDate().substring(8, 10);
			int numDay = Integer.parseInt(day);

			PharmaInOutDto[] inout = resultMap.get(numDay);
			inout[0] = pharmaInDto;
		}

		for (PharmaOutDto pharmaOutDto : outDtoes) {
			String day = pharmaOutDto.getCurrentOutputDate().substring(8, 10);
			int numDay = Integer.parseInt(day);

			PharmaInOutDto[] inout = resultMap.get(numDay);
			inout[1] = pharmaOutDto;
		}

		// 짐싸!
		model.addAttribute("resultMap", resultMap);
		model.addAttribute("pp", pp);
		model.addAttribute("p", p);
		model.addAttribute("n", n);
		model.addAttribute("nn", nn);
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		model.addAttribute("cal", cal);
		model.addAttribute("dayOfWeek", dayOfWeek);

		return "pharma/inout/calendar.tiles";
	}

	// In AREA START

	@GetMapping(value = "/pharma-in-write.do")
	public String pharmaInWrite(String syear, String smonth, String sday, Model model) {
		System.out.println("PharmaInOutController pharmaInWrite()");

		model.addAttribute("year", syear);
		model.addAttribute("month", smonth);
		model.addAttribute("day", sday);

		return "pharma/inout/inwrite";
	}

	@ResponseBody
	@GetMapping(value = "/pharma-in-writeAf.do")
	public ResultMsg pharmaInWriteAf(PharmaInDto dto, Model model, HttpServletRequest request) {
		System.out.println("PharmaInOutController pharmaInWriteAf()");

		dto.setStoreId((int)request.getSession().getAttribute("storeId"));

		boolean isSuccess = inService.insertDailyInput(dto);

		return new ResultMsg(isSuccess);
	}

	@GetMapping(value = "/pharma-in-detail.do")
	public String pharmaInDetail(String syear, String smonth, String sday, int id, Model model, HttpServletRequest request) {
		System.out.println("pharmaInOutCalendar pharmaInDetail()");

		List<PharmaInDetailDto> list = inService.selectDailyInputProducts(id);

		model.addAttribute("id", id);
		model.addAttribute("year", syear);
		model.addAttribute("month", smonth);
		model.addAttribute("day", sday);
		model.addAttribute("list", list);

		return "pharma/inout/indetail";
	}

	@GetMapping(value = "/pharma-in-delete.do")
	public String pharmaInDelete(int id, Model model, HttpServletRequest request) {
		System.out.println("PharmaInOutController pharmaInDelete()");

		boolean isS = inService.deleteDailyInput(id);

		return "redirect:/pharma-inout-calendar.do";
	}

	// In AREA END

	// OUT AREA START

	@GetMapping(value = "/pharma-out-write.do")
	public String pharmaOutWrite(String syear, String smonth, String sday, Model model) {
		System.out.println("PharmaInOutController pharmaOutWrite()");

		model.addAttribute("year", syear);
		model.addAttribute("month", smonth);
		model.addAttribute("day", sday);

		return "pharma/inout/outwrite";
	}

	@ResponseBody
	@GetMapping(value = "/pharma-out-writeAf.do")
	public ResultMsg pharmaOutWriteAf(PharmaOutDto dto, Model model, HttpServletRequest request) {
		System.out.println("PharmaInOutController pharmaOutWriteAf()");

		dto.setStoreId((int)request.getSession().getAttribute("storeId"));

		boolean isSuccess = outService.insertDailyOutput(dto);

		return new ResultMsg(isSuccess);
	}

	@GetMapping(value = "/pharma-out-detail.do")
	public String pharmaOutDetail(String syear, String smonth, String sday, int id, Model model, HttpServletRequest request) {
		System.out.println("pharmaInOutCalendar pharmaOutDetail()");

		List<PharmaOutDetailDto> list = outService.selectDailyOutputProducts(id);

		model.addAttribute("id", id);
		model.addAttribute("year", syear);
		model.addAttribute("month", smonth);
		model.addAttribute("day", sday);
		model.addAttribute("list", list);

		return "pharma/inout/outdetail";
	}

	@GetMapping(value = "/pharma-out-delete.do")
	public String pharmaOutDelete(int id, Model model, HttpServletRequest request) {
		System.out.println("PharmaInOutController pharmaOutDelete()");

		boolean isS = outService.deleteDailyOutput(id);

		return "redirect:/pharma-inout-calendar.do";
	}

	// OUT AREA END

}
