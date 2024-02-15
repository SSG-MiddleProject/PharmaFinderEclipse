<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>마이 페이지</title>
    <style>
    /* 나의 회원정보 수정 버튼 */
    .my-button {
        position: absolute;
        top:15px;
        left: 500px; /* 오른쪽에서 60px 떨어진 곳에 위치 */
        width: auto; /* 너비를 텍스트에 맞게 자동 조정 */
        height: 40px; /* 버튼의 높이 조정 */
        font-family: 'Inter';
        font-style: normal;
        font-weight: 400;
        font-size: 15px;
        color: #FFFFFF;
        padding: 10px 20px; /* 내부 여백 조정 */
        border-radius: 50px; /* 버튼의 모서리를 둥글게 */
        background-color: #000; /* 배경색 */
        text-decoration: none; /* 텍스트 밑줄 제거 */
        transition: background-color 0.3s; /* 배경색 변경시 트랜지션 효과 */
    }

    .my-button:hover {
        background-color: #eee; /* 호버 시 배경색 변경 */
        color: #000; /* 호버 시 텍스트 색상 변경 */
    }

    .user-greeting {
         margin-bottom: 20px;
            font-family: 'Inter', sans-serif;
            font-weight: bold; /* 글씨 굵기를 굵게 */
            font-size: 48px; /* 기본 글씨 크기의 3배 */
            color: #000; /* 글씨 색상 */
    }

    .links {
        margin-bottom: 20px;
    }

    .button {
        display: inline-block;
        padding: 10px 20px;
        border-radius: 50px;
        background-color: #000;
        color: #fff;
        text-decoration: none;
        transition: background-color 0.3s;
    }

    .button:hover {
        background-color: #eee;
    }
</style>
</head>
<body>
    <c:if test="${not empty memberDto}">
        <div class="user-greeting">
            <h1>${memberDto.username}님, 안녕하세요</h1>
        </div>
    </c:if>

    <div class="links">
    <a href="${pageContext.request.contextPath}/userupdate.do" class="button my-button">회원정보수정</a>
	</div>

    <div class="bookmark-list">
        <c:if test="${not empty bookmarks}">
            <ul>
                <c:forEach items="${bookmarks}" var="bookmark">
                    <li>${bookmark.targetName}</li>
                </c:forEach>
            </ul>
        </c:if>
    </div>
</body>
</html>
