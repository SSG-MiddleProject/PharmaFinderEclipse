<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>북마크 목록</title>
</head>
<body>
    <h1>북마크 목록</h1>
    <ul>
        <c:forEach items="${bookmarks}" var="bookmark">
            <li>${bookmark.pharmacyName} / ${bookmark.productName}</li>
        </c:forEach>
    </ul>
</body>
</html>