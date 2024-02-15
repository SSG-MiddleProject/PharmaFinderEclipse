<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>마이 페이지</title>
    <style type="text/css">
    .nav-bar {
        position: fixed;
        top: 0;
        left: 0;
        width: 200px; /* 네비게이션 바의 너비 */
        height: 100%;
        background-color: #007bff;
        color: white;
        display: flex;
        flex-direction: column; /* 링크들을 수직으로 정렬 */
        padding: 1rem;
        box-shadow: 2px 0 4px rgba(0,0,0,.1);
    }
    
    .nav-links {
        display: flex;
        flex-direction: column;
        align-items: flex-start; /* 왼쪽 정렬 */
    }
    
    .nav-links a {
        color: white;
        text-decoration: none;
        margin: 10px 0;
    }

    .nav-logo img {
        width: 80%; /* 로고 이미지 크기 조정 */
        height: auto;
        margin-bottom: 20px; /* 로고와 링크 사이의 간격 */
    }
    </style>
</head>
<body>
    <div class="nav-bar">
        <div class="nav-logo">
            <a href="${pageContext.request.contextPath}/">
                <img src="${pageContext.request.contextPath}/resources/logo.png" alt="로고" />
            </a>
        </div>
        <div class="nav-links">
            <a href="${pageContext.request.contextPath}/bookmark/list.do">즐겨찾기</a>
            <a href="${pageContext.request.contextPath}/userupdate.do">회원정보수정</a>
            <!-- 추가적으로 필요한 링크들을 여기에 추가하세요 -->
            <a href="${pageContext.request.contextPath}/logout.do">로그아웃</a>
        </div>
    </div>

    <div class="mypage-container">
    <h2 class="mypage-title">마이 페이지</h2>
    <!-- 마이 페이지의 내용이 여기에 들어갑니다 -->
</div>
</body>
</html>
