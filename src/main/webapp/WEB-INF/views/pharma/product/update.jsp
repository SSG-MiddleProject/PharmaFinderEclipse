<%@page import="ssg.middlepj.pharmafinder.dto.PharmaProductWithProductDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	PharmaProductWithProductDto dto = (PharmaProductWithProductDto)request.getAttribute("dto");
    String imgUrl = dto.getItemImage() == "" || dto.getItemImage() ==  null ? "https://st4.depositphotos.com/14953851.5/1.51.5771.5/v/450/depositphotos_1.51.5771.5501.50-stock-illustration-image-available-icon-flat-vector.jpg": dto.getItemImage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.wrapper {
	width: calc(100vw - 104px - 15px - 40px);
	height: 100vh;
	display: flex;
	justify-content: center;
}

.main {
	font-size: 1.5em;
}

caption {
	margin-top: 1.5em;
	margin-bottom: 1.5em;
}
</style>
</head>
<body>
<div class="wrapper">
	<div class="main">
	<form action="./pharma-product-updateAf.do">
		<table class="table">
		<caption class="title"> 등록된 제품 정보수정</caption>
		<input type="hidden" name="id" value="<%=dto.getId() %>" />
		<input type="hidden" name="storeId" value="<%=dto.getStoreId() %>" />
			<tr>
				<th>제품코드</th>
				<td><%=dto.getProductId()%></td>
				<input type="hidden" name="productId" value="<%=dto.getProductId() %>" />
			</tr>
			<tr>
				<th>제품명</th>
				<td><%=dto.getItemName() %></td>
				<input type="hidden" name="itemName" value="<%=dto.getItemName()%>" />
			</tr>
			<tr>
				<th>사진</th>
				<td><img src="<%=imgUrl%>" width='1.500px' height='1.500px'/></td>
			</tr>
			<tr>
				<th style="line-height:3em">입고단가</th>
				<td><input type="text" name="inputPrice" value="<%=dto.getInputPrice()%>" style="font-size:1.5em" /></td>
			</tr>
			<tr>
				<th style="line-height:3em">출고단가</th>
				<td><input type="text" name="outputPrice" value="<%=dto.getOutputPrice()%>" style="font-size:1.5em" /></td>
			</tr>
			<tr>
				<th>제품 등록일</th>
				<td><%=dto.getCreatedAt().substring(0, 10) %></td>
				<input type="hidden" name="createdAt" value="<%=dto.getCreatedAt()%>" />
			</tr>
		</table>
		<button class="button is-primary is-outlined is-large" type="submit" >수정완료</button>
	</form>
	</div>
	</div>
</body>
</html>