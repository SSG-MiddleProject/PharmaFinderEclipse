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

<div style="padding: 10px">

<p><%=formatedDate%></p>
<p style="padding-top: 20px; padding-bottom: 10px; font-size: x-large; font-weight: bold">출고 정보</p>

<form action="./pharma-out-delete.do" style="width: 100%">
<input type="hidden" name="id" value="${id}">
	<table class="table has-text-centered" style="width: inherit">
<thead>
	<tr>
		<th class="has-text-centered">입고 id</th><th class="has-text-centered">제품코드</th><th class="has-text-centered">제품명</th><th class="has-text-centered">수량</th><th class="has-text-centered">출고단가</th>
	</tr>
</thead>
<tbody>
<%
	for(int i = 0; i < list.size(); i++){
		PharmaOutDetailDto dto = list.get(i);
%>
		<tr>
			<td><%=dto.getId() %></td>
			<td><%=dto.getProductId()%></td>
			<td><%=dto.getItemName() %></td>
			<td><%=dto.getOutputCnt() %></td>
			<td><%=dto.getOutputPrice() %></td>
		</tr>
<%
	}
%>
</tbody>
</table>
	<div style="position: absolute; bottom: 10px; left: 44%">
		<button class="button" type="submit">삭제</button>
		<button class="button" type="button">수정</button>
	</div></form>
</div>
