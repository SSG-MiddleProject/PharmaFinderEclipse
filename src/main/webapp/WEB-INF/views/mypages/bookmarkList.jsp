<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
    <h1>북마크 목록</h1>
    <ul>
        <c:forEach items="${bookmarks}" var="bookmark">
            <!-- 약국정보표시 --> <!-- 제품 정보표시 -->
            <li>${bookmark.targetName} </li>
        </c:forEach>
    </ul>

</div>