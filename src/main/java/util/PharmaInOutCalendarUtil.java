package util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import ssg.middlepj.pharmafinder.dto.PharmaInDto;
import ssg.middlepj.pharmafinder.dto.PharmaInOutDto;
import ssg.middlepj.pharmafinder.dto.PharmaOutDto;

public class PharmaInOutCalendarUtil {

	// 문자열 검사 : 빈문자열 -> true
	public static boolean nvl(String msg) { // not value
		return msg == null || msg.trim().equals("") ? true : false;
	}

	// 한자리 숫자를 두자리로 만들어 주는 함수 : 1 ~ 9 -> 01 ~ 09
	public static String two(String msg) {
		return msg.trim().length() < 2 ? "0" + msg.trim() : msg.trim();
	}

	// 앞에 0이 있는 문자열을 한자리 문자로 만들어주는 함수 : 01 ~ 09 -> 1 ~ 9
	public static String one(String msg) {
		return msg.charAt(0) == '0' ? msg.charAt(1) + "" : msg;
	}

	public static String inoutCalWrite(int year, int month, int day, PharmaInOutDto[] inoutDtoes) {
		// inoutDto 0번은 in 1번은 out

		String inWrite = "";
		String outWrite = "";

		if (inoutDtoes[0] == null) {
			inWrite = inputCalWrite(year, month, day);
		}

		if (inoutDtoes[1] == null) {
			outWrite = outputCalWrite(year, month, day);
		}

		return String.format("<td style='border:none;text-align: center;'>%s</td><td style='border:none;text-align: center;'>%s</td>", inWrite, outWrite);
	}

	// 입고쓰기
	public static String inputCalWrite(int year, int month, int day) {
		String str = "";

		String img = "<i class='fas fa-file-signature' style='color: #93c5fd;' title='입고'></i>";
		str = String.format("<a class='js-modal-trigger' data-target='modal' data-rw='w' data-inout='in' data-syear='%d' data-smonth='%d' data-sday='%d'>%s</a>", year, month, day, img);

		return str;
	}

	// 출고쓰기
	public static String outputCalWrite(int year, int month, int day) {
		String str = "";

		String img = "<i class='fas fa-file-signature' style='color: #fb7185;' title='출고'></i>";
		str = String.format("<a class='js-modal-trigger' data-target='modal' data-rw='w' data-inout='out' data-syear='%d' data-smonth='%d' data-sday='%d'>%s</a>", year, month, day, img);

		return str;
	}

	// 일별 일정을 모두 볼 수 있는 bbsdaylist.jsp로 이동되도록 하는 함수
	public static String daylist(int year, int month, int day) {
		LocalDate ld = LocalDate.now();
		String str = "";

		if (year == ld.getYear() && month == ld.getMonthValue() && day == ld.getDayOfMonth()) {
			str += "<a class='day is-today' href='/pharma-inventory-inquiry.do?currentDate=%s' style='color:#333;text-decoration:none'><b>";
		} else {
			str += "<a class='day' href='/pharma-inventory-inquiry.do?currentDate=%s' style='color:#333;text-decoration:none'><b>";
		}
		str += String.format("%2d", day);
		str += "</b></a>";

//		str += String.format(
//				"&nbsp;<a href='/pharma-inventory-inquiry.do?currentDate=%s' style='color:#333;text-decoration:none'><b>",
//				toDashYYYYMMdd(year,month,day));
//		str += String.format("%2d", day);
//		str += "</b></a>";



		return str;
	}

	// 제목이 길 경우에 ...으로 처리하는 함수
	public static String dot3(String msg) {
		String str = "";

		if (msg.length() >= 10) {
			// 0번 인덱스부터 9번 인덱스까지 자름(10바로 앞에)
			str = msg.substring(0, 10);
			str += "...";
		} else {
			str = msg.trim();
		}

		return str;
	}

	// 날짜별로 테이블(그날 일정목록)을 생성하기 위한 함수
	public static String makeTable(int year, int month, int day, PharmaInOutDto[] inoutDtoes) {
		String str = "";

		// 202401180415
		String dates = (year + "") + two(month + "") + two(day + "");

		str += "<table style='margin:10px'>";
		str += "<col style='width:50px'>";
		str += "<col style='width:50px'>";

		str += "<tr>";
		str += "	<td style='border:none'>";

		if(inoutDtoes[0] != null) {
			PharmaInDto inDto = (PharmaInDto)inoutDtoes[0];

			String appendStr = String.format("<a class='js-modal-trigger' data-target='modal' data-rw='r' data-inout='in' data-syear='%d' data-smonth='%d' data-sday='%d' data-id='%d'>%s</a>",
					year, month, day, inDto.getId(), "<i class='fas fa-file-download fa-3x' style='color: #93c5fd'></i>");

			str += appendStr;
		}

		str += "	</td>";

		str += "	<td style='border:none'>";

		if(inoutDtoes[1] != null) {
			PharmaOutDto outDto = (PharmaOutDto)inoutDtoes[1];

			String appendStr = String.format("<a class='js-modal-trigger' data-target='modal' data-rw='r' data-inout='out' data-syear='%d' data-smonth='%d' data-sday='%d' data-id='%d'>%s</a>",
												year, month, day, outDto.getId(), "<i class='fas fa-file-upload fa-3x' style='color: #fb7185'></i>");
			str += appendStr;
		}

		str += "	</td>";

		str += "</tr>";

		str += "</table>";

		return str;
	}

	// 날짜와 시간을 사용자가 보기 쉽게 출력하는 함수
	// 202401181622 -> 2024년 01월 18일 16시 22분
	public static String toDates(String mdate) {
		// 날짜형식
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분");

		// 0부터 3번인덱스까지 자름
		String s = mdate.substring(0, 4) + "-" // yyyy
				+ mdate.substring(4, 6) + "-" // MM
				+ mdate.substring(6, 8) + " " // dd, dd와 hh 구분을위해 " "로 한칸 띄워줘야함
				+ mdate.substring(8, 10) + ":" // hh
				// 10부터 나머지 다자름
				+ mdate.substring(10) + ":00"; // mm이며 :00는 초 mm:ss

		Timestamp d = Timestamp.valueOf(s);
		// sdf 포맷으로 맞춰서 출력
		return sdf.format(d);
	}

	public static String toDashYYYYMMdd(int year, int month, int day) {
		return year + "-" + two(month + "") + "-" + two(day + "");
	}
}
