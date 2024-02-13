<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>회원가입 선택페이지</title>

<style type="text/css">
/* General styling */
body {
    margin: 0;
    padding: 0;
    font-family: 'Inter', sans-serif;
    background-color: #f9f9f9;
}

.container {
    position: relative;
    width: 100%;
    max-width: 1440px;
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
}

.title {
    font-family: 'Inter';
    font-style: normal;
    font-weight: 800;
    font-size: 48px;
    line-height: 58px;
    text-align: center;
    color: #000000;
    margin-bottom: 20px;
}

.btn-container {
    display: flex;
    justify-content: center;
    align-items: center;
}

.btn {
    position: relative;
    width: 300px;
    height: 70px;
    background: #FFFFFF;
    border: 2px solid #000000;
    border-radius: 10px;
    display: flex;
    justify-content: center;
    align-items: center;
    margin: 0 20px;
    text-decoration: none;
    color: #000000;
    font-family: 'Inter';
    font-style: normal;
    font-weight: bold;
    font-size: 24px;
    line-height: 29px;
    transition: all 0.3s ease;
}

.btn:hover {
    background: #000000;
    color: #FFFFFF;
}

/* Login text */
.login-text {
    font-family: 'Inter';
    font-style: normal;
    font-weight: 500;
    font-size: 24px;
    line-height: 29px;
    text-align: center;
    color: #989898;
    margin-top: 30px;
}

</style>

</head>
<body>

<div class="container">
    <h2 class="title">PharmaFinder</h2>
    <p>Register</p>
    <div class="btn-container">
        <a href="userregi.do" class="btn">일반고객 회원가입</a>
        <a href="regi.do" class="btn">약국 회원가입</a>
    </div>
</div>

</body>
</html>
