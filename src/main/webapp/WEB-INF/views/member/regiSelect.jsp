<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 선택페이지</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
<style type="text/css">
/* General styling */
body {
    margin: 0;
    padding: 0;
    background-color: #f9f9f9;
    text-align: center;
}

.center{
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}

.container {
    width: 80%;
    height: 100%;
    background-color: #f9f9f9;
}

.regi-title {
    font-weight: 800;
    font-size: 48px;
    text-align: center;
    margin-bottom: 0;
    margin-top: 30px;
}

.regi-title-sub {
    font-weight: 400;
    font-size: x-large;
    text-align: center;
    margin-top: 0;
}

.btn-container {
    display: flex;
    justify-content: space-around;
    margin-bottom: 40px;
    height: 55%;
}

.btn {
    width: 40%;
    padding: 10px;
    border: none;
    border-radius: 5px;
    background-color: #dbdbdb;
    color: #fff;
    cursor: pointer;
    transition: background-color 0.3s ease;
    text-decoration: none;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    font-weight: bold;
}

.btn:hover {
    background-color: #0056b3;
}

/* Login text */
.login-text {
    margin-top: 0;
    text-align: center;
    transition: color 0.3s ease;

}

.login-text a {
    color: #007bff;
    text-decoration: none;
}

.login-text a:hover {
    color: #0056b3;
}

i {
    font-size: 17rem;
    margin-bottom: 10px;
}

</style>

</head>
<body>

<div class="center">
<div class="container">
    <p class="regi-title">PharmaFinder</p>
    <p class="regi-title-sub">Register</p>
    <div class="btn-container">
        <a href="userRegi.do" class="btn"><i class="fa fa-user"></i><p>사용자 등록</p></a>
        <a href="pharmacyRegi.do" class="btn"><i class="fa fa-pills"></i>약국직원 등록</a>
    </div>
    <div class="login-text">
        이미 계정이 있으신가요? <a href="login.do">로그인</a>
    </div>
</div>
</div>

</body>
</html>
