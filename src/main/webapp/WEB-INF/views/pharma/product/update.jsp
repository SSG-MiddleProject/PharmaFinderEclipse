<%@page import="ssg.middlepj.pharmafinder.dto.PharmaProductWithProductDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	PharmaProductWithProductDto dto = (PharmaProductWithProductDto)request.getAttribute("dto");
    String imgUrl = dto.getItemImage() == "" || dto.getItemImage() ==  null ? "https://st4.depositphotos.com/14953852/22772/v/450/depositphotos_227725020-stock-illustration-image-available-icon-flat-vector.jpg": dto.getItemImage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="./pharma-product-updateAf.do">
		<table class="table">
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
				<td><img src="<%=imgUrl%>" width='200px' height='200px'/></td>
			</tr>
			<tr>
				<th>입고단가</th>
				<td><input type="text" name="inputPrice" value="<%=dto.getInputPrice()%>" /></td>
			</tr>
			<tr>
				<th>출고단가</th>
				<td><input type="text" name="outputPrice" value="<%=dto.getOutputPrice()%>" /></td>
			</tr>
			<tr>
				<th>제품 등록일</th>
				<td><%=dto.getCreatedAt().substring(0, 10) %></td>
				<input type="hidden" name="createdAt" value="<%=dto.getCreatedAt()%>" />
			</tr>
		</table>
		<button type="submit">수정완료</button>
	</form>
</body>
</html>