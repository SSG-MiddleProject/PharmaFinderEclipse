<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>마이 페이지</title>
    <style type="text/css">
        body {
            margin: 0;
            padding: 0;
            font-family: 'Inter', sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f9f9f9;
        }

        .mypage-container {
            max-width: 600px;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 10px;
            background-color: #fff;
        }

        .mypage-title {
            font-weight: 800;
            font-size: 32px;
            text-align: center;
            margin-bottom: 20px;
        }

        .mypage-links {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .mypage-links a {
            padding: 10px;
            margin: 5px 0;
            width: 60%;
            text-align: center;
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        .mypage-links a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="mypage-container">
        <h2 class="mypage-title">마이 페이지</h2>
        <div class="mypage-links">
            <a href="bookmark.do">즐겨찾기</a>
            <a href="userupdate.do">회원정보수정</a>
        </div>
    </div>
</body>
</html>
