package ssg.middlepj.pharmafinder.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ssg.middlepj.pharmafinder.dto.InventoryDto;
import ssg.middlepj.pharmafinder.dto.MemberDto;
import ssg.middlepj.pharmafinder.dto.PharmaInventoryParam;
import ssg.middlepj.pharmafinder.service.PharmaInventoryService;
import util.PharmaInOutCalendarUtil;

@Controller
public class PharmaInventoryController {

	@Autowired
	private PharmaInventoryService service;

	// 처음 진입점
	@GetMapping(value = "/pharma-inventory-inquiry.do")
	public String pharmaInventoryInquiry(String searchType, String keyword, PharmaInventoryParam param, Model model, HttpServletRequest request) {
		System.out.println("pharmaInOutCalendar pharmaInventoryInquiry()");

		if((searchType !=  null && searchType.equals("id")) && (keyword != null && !keyword.equals(""))) {
			param.setProductId(Integer.parseInt(keyword));
			model.addAttribute("searchType", searchType);
			model.addAttribute("keyword", keyword);
		} else if((searchType !=  null && searchType.equals("itemName")) && (keyword != null && !keyword.equals(""))) {
			param.setItemName(keyword);
			model.addAttribute("searchType", searchType);
			model.addAttribute("keyword", keyword);
		}

		String currentDate;

		if (param.getCurrentDate() == null || param.getCurrentDate() == "") {
			LocalDate currentDateObj = LocalDate.now();
			currentDate = currentDateObj.toString();
		} else {
			currentDate = param.getCurrentDate();
		}

		int storeId = ((MemberDto)request.getSession().getAttribute("member")).getStoreId();

		param.setCurrentDate(currentDate);
		param.setStoreId(storeId);

		List<InventoryDto> list = service.selectInventory(param);

		model.addAttribute("currentDate", currentDate);
		model.addAttribute("list", list);

		return "pharma/inventory/inquiry.tiles";
	}
}
