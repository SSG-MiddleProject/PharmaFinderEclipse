<%@page import="ssg.middlepj.pharmafinder.dto.PharmaOutDetailDto"%>
<%@page import="java.util.List"%>
<%@page import="util.PharmaInOutCalendarUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String year = (String)request.getAttribute("year");
	String month = (String)request.getAttribute("month");
	String day = (String)request.getAttribute("day");

	String twoMonth = PharmaInOutCalendarUtil.two(month);
	String twoDay = PharmaInOutCalendarUtil.two(day);

	String formatedDate = year + "-" + twoMonth + "-" + twoDay;

	List<PharmaOutDetailDto> list = (List<PharmaOutDetailDto>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>입고 정보</h1>
<input type="date" id="date" value="<%=formatedDate %>" readonly />

<form action="./pharma-out-delete.do">
<input type="hidden" name="id" value="${id}">
<table class="table">
<thead>
	<tr>
		<th>제품코드</th><th>제품명</th><th>수량</th><th>입고단가</th>
	</tr>
</thead>
<tbody>
<%
	for(int i = 0; i < list.size(); i++){
		PharmaOutDetailDto dto = list.get(i);
%>
		<tr>
			<td><%=dto.getId() %></td>
			<td><%=dto.getItemName() %></td>
			<td><%=dto.getOutputCnt() %></td>
			<td><%=dto.getOutputPrice() %></td>
		</tr>
<%
	}
%>
</tbody>
</table>
<button type="submit">삭제</button>
</form>
</body>
</html>