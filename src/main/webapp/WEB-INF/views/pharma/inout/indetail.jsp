<%@page import="ssg.middlepj.pharmafinder.dto.PharmaInDetailDto"%>
<%@page import="ssg.middlepj.pharmafinder.dto.PharmaInProductDto"%>
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

	List<PharmaInDetailDto> list = (List<PharmaInDetailDto>)request.getAttribute("list");
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

<form action="./pharma-in-delete.do">
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
		PharmaInDetailDto dto = list.get(i);
%>
		<tr>
			<td><%=dto.getId() %></td>
			<td><%=dto.getItemName() %></td>
			<td><%=dto.getInputCnt() %></td>
			<td><%=dto.getInputPrice() %></td>
		</tr>
<%
	}
%>
</tbody>
</table>
<button type="submit">삭제</button>
<button type="button">수정</button>
</form>
</body>
</html>