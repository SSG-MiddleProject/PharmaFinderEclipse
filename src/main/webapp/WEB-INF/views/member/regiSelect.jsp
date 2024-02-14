<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 선택페이지</title>

<style type="text/css">
/* General styling */
body {
    margin: 0;
    padding: 0;
    font-family: 'Inter', sans-serif;
    background-color: #f9f9f9;
}

.center{
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}

.container {
    max-width: 600px;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 10px;
    background-color: #f9f9f9;
}

.title {
    font-weight: 800;
    font-size: 48px;
    text-align: center;
    margin-bottom: 20px;
}

.btn-container {
    display: flex;
    justify-content: space-around;
    margin-bottom: 20px;
}

.btn {
    padding: 10px;
    border: none;
    border-radius: 5px;
    background-color: #007bff;
    color: #fff;
    cursor: pointer;
    transition: background-color 0.3s ease;
    text-decoration: none;
    display: inline-block;
    font-weight: bold;
}

.btn:hover {
    background-color: #0056b3;
}

/* Login text */
.login-text {
    text-align: center;
    margin-top: 30px;
    transition: color 0.3s ease;
}

.login-text a {
    color: #007bff;
    text-decoration: none;
}

.login-text a:hover {
    color: #0056b3;
}

</style>

</head>
<body>

<div class="center">
<div class="container">
    <h2 class="title">PharmaFinder</h2>
    <p class="login-text">회원가입 선택</p>
    <div class="btn-container">
        <a href="userRegi.do" class="btn">사용자 등록</a>
        <a href="pharmacyRegi.do" class="btn">약국직원 등록</a>
    </div>
    <div class="login-text">
        이미 계정이 있으신가요? <a href="login.do">로그인</a>
    </div>
</div>
</div>

</body>
</html>
