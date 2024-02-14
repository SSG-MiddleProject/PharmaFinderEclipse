<%@page import="ssg.middlepj.pharmafinder.dto.InventoryDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	List<InventoryDto> list = (List<InventoryDto>)request.getAttribute("list");
	String searchType = (String)request.getAttribute("searchType");
	String keyword = (String)request.getAttribute("keyword");

	boolean isSearch = searchType != null && !searchType.equals("")
					&& keyword != null && !keyword.equals("");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.wrapper{
	width: calc(100vw - 104px - 15px - 40px);
    height: 100vh;
    display:flex;
    justify-content: center;
    font-size: 1.5em
}

.title{
	margin-top: 1.5em;
	margin-bottom: 1.5em;
}

</style>
</head>
<body>
<div class="wrapper">
<div class="main">
<h1 class="title">재고 조회</h1>
<input type="date" value="${currentDate}" class="input" style="width:20%" onchange="inquiry(this)" >
<table class="table">
<thead>
	<tr>
		<th>상품명</th><th>상품코드</th><th>입고</th><th>출고</th><th>현재고</th><th>입고단가</th><th>출고단가</th>
	</tr>
</thead>
<tbody>
	<%
	for(int i = 0; i < list.size();i++){
		InventoryDto dto = list.get(i);
	%>
		<tr>
			<td><%=dto.getItemName() %></td><td><%=dto.getProductId() %>
			<td><%=dto.getInputCnt() %></td><td><%=dto.getOutputCnt() %></td><td><%=dto.getCurrentStockCnt() %></td><td><%=dto.getInputPrice() %></td><td><%=dto.getOutputPrice() %></td>
		</tr>
	<%
		}
	%>
</tbody>
</table>

<form action="/pharma-inventory-inquiry.do">
	<input type="hidden" name="currentDate" value="${currentDate}"/>
<div class="field has-addons has-addons-centered">
  <p class="control">
    <span class="select">
      <select id="searchType" name="searchType">
		<option value="type" selected>선택</option>
        <option value="id">제품코드</option>
        <option value="itemName">제품명</option>
    </select>
    </span>
  </p>
  <p class="control">
    <input class="input" type="text" name="keyword" value="${keyword}">
  </p>
  <p class="control">
      <button class="button is-primary" type="submit">검색</button>
  </p>
</div>


</form>
</div>
</div>

<script type="text/javascript">
function inquiry(ele){

	if($("#searchType").val() === "id"){
		if(isNaN($("keyword").val())){
			alert("제품코드는 숫자입니다.");
			return;
		}
	}

	location.href="/pharma-inventory-inquiry.do?currentDate=" + $(ele).val()
			+ "&searchType=" + $("searchType").val() + "&keyword=" + $("keyword").val();
}

$(document).ready(function(){
	<%if(isSearch){ %>
		$('#searchType').val("<%=searchType%>").prop('selected', true);
	<%} %>
})
</script>
</body>
</html>