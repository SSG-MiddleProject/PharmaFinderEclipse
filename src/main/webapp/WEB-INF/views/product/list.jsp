<%@ page import="ssg.middlepj.pharmafinder.dto.ProductDto" %><%--
  Created by IntelliJ IDEA.
  User: hdw
  Date: 2/1/24
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@page import="ssg.middlepj.pharmafinder.dto.Pagination" %>
<%@page import="ssg.middlepj.pharmafinder.dto.ProductDto" %>
<%@page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Pagination pagination = (Pagination) request.getAttribute("pagination");
    List<ProductDto> productList = (List<ProductDto>) request.getAttribute("products");

    String search = pagination.getKeyword();
    if (search.isEmpty()) {
        search = "";
    }
%>

<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<label>
    <input type="text" name="search" value="<%=search%>">
</label>
<button type="button" onclick="handleSearch()">검색</button>
<button type="button" onclick="handleSearch2()">efcy검색</button>


<% for (ProductDto product : productList) { %>
<div>
        <p><%=product.getItemName()%></p>
        <p><%=product.getEntpName()%></p>
        <p><%=product.getEfcyQes()%></p>
</div>
<% } %>

<p>
    ${test}
</p>

<script>
    let search = "<%=search%>"

    function handleSearch() {
        const search = document.querySelector('input[name="search"]').value;
        location.href = encodeURI("search.do?keyword=" + search);
    }
    function handleSearch2() {
        const search = document.querySelector('input[name="search"]').value;
        location.href = "/product/search2.do"
    }
</script>
</body>
</html>
