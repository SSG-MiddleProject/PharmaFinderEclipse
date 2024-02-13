<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지 즐겨찾기</title>
</head>
<body>
	<h1>즐겨찾기 페이지</h1>
	<!-- 여기에 즐겨찾기 목록 표시 -->
	<ul>
        <!-- 즐겨찾기 목록 표시 -->
        <c:forEach items="${bookmarks}" var="bookmark">
            <li>${bookmark.pharmacyName}</li>
            <!-- 약국 정보 표시 -->
            <li>${bookmark.productName}</li>
            <!-- 제품 정보 표시 -->
        </c:forEach>
    </ul>
</body>
</html>