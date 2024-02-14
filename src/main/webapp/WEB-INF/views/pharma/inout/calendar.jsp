<%@page import="ssg.middlepj.pharmafinder.dto.PharmaInOutDto"%>
<%@page import="java.util.Map"%>
<%@page import="util.PharmaInOutCalendarUtil"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
<style>

/* th{
	vertical-align: middle;
} */

th {
	border:none;
}

.calendarHeader {
	border: 1px solid #999;
}

.calendar td{
	border: 1px solid #999;
}

.titleDiv{
	padding-top:20px;
	padding-bottom:20px;
}

</style>
</head>
<body>
<div class="titleDiv has-text-centered">
	<h1 class="title ">입출고 내역</h1>
</div>
<%
	Map<Integer, PharmaInOutDto[]> resultMap = (Map<Integer, PharmaInOutDto[]>)request.getAttribute("resultMap");

	//
	String pp = (String)request.getAttribute("pp");
	String p = (String)request.getAttribute("p");
	String n = (String)request.getAttribute("n");
	String nn = (String)request.getAttribute("nn");

	int year = (Integer)request.getAttribute("year");
	int month = (Integer)request.getAttribute("month");
	int dayOfWeek = (Integer)request.getAttribute("dayOfWeek");

	Calendar cal = (Calendar)request.getAttribute("cal");

%>

<div align="center">

<table class="calendar">
<col width="120"/><col width="120"/><col width="120"/><col width="120"/>
<col width="120"/><col width="120"/><col width="120"/>

<tr height="80">
	<td colspan="7" align="center" style="vertical-align: middle;">
		<%=pp %>&nbsp;<%=p %>&nbsp;&nbsp;&nbsp;&nbsp;

		<font style="color: #3c3c3c; font-size: 40px; font-family: fantasy; vertical-align: middle;">
			<%=String.format("%d년&nbsp;&nbsp;%2d월", year, month) %>
		</font>

		&nbsp;&nbsp;&nbsp;&nbsp;<%=n %>&nbsp;<%=nn %>
	</td>
</tr>

<tr class="calendarHeader" height="30" style ="background-color:royalblue;color:white;">
	<th class="has-text-centered">일</th>
	<th class="has-text-centered">월</th>
	<th class="has-text-centered">화</th>
	<th class="has-text-centered">수</th>
	<th class="has-text-centered">목</th>
	<th class="has-text-centered">금</th>
	<th class="has-text-centered">토</th>
</tr>
<tr height="120" align="left" valign="top">
<%
// 위쪽 빈칸, 1월 1일이 화요일부터 시작하면 1월 1일에 빈칸표시
for(int i = 1;i < dayOfWeek; i++){
	%>
	<td style="background-color: #eeeeee">&nbsp;</td>
	<%
}

// 날짜				달의 마지막날
int lastday = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

for(int i = 1; i <= lastday; i++){
	%>
	<td style="color:#3c3c3c;padding-top:5px">
		<table>
		<col width='30px'/>
		<col width='30px'/>
		<col width='30px'/>
		<td style='border:none'>
		<%=PharmaInOutCalendarUtil.daylist(year, month, i) %>
		</td>
		<%= PharmaInOutCalendarUtil.inoutCalWrite(year, month, i, resultMap.get(i))%>
		</table>
		<%=PharmaInOutCalendarUtil.makeTable(year, month, i, resultMap.get(i)) %>
	</td>
	<%
	if((i + dayOfWeek -1) % 7 == 0 && i != lastday){
		%>
		</tr><tr height="120" align="left" valign="top">
		<%
	}
}

// 아래쪽 빈칸, 달의 마지막일 나머지 요일 빈칸
cal.set(Calendar.DATE, lastday);
int weekday = cal.get(Calendar.DAY_OF_WEEK);
for(int i = 0; i< 7 -weekday;i++){
	%>
	<td style="background-color: #eeeeee">&nbsp;</td>
	<%
}

%>
</tr>
</table>

</div>

</body>
</html>