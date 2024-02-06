<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>로그인 페이지</title>
<style type="text/css">
/* General styling */
body {
    margin: 0;
    padding: 0;
    font-family: 'Inter', sans-serif;
}

.center {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}

/* Login container */
.login-container {
    max-width: 600px;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 10px;
    background-color: #f9f9f9;
}

/* PharmaFinder Login */
.login-title {
    font-weight: 800;
    font-size: 48px;
    text-align: center;
    margin-bottom: 20px;
}

/* Buttons */
.login-buttons {
    display: flex;
    justify-content: space-around;
    margin-bottom: 20px;
}


.login-buttons button:hover {
    background-color: #ddd;
}

/* Form fields */
.login-form input[type="text"],
.login-form input[type="password"] {
    width: 100%;
    padding: 10px;
    margin-bottom: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    box-sizing: border-box;
}

/* Submit button */
.login-form input[type="submit"] {
    width: 100%;
    padding: 10px;
    border: none;
    border-radius: 5px;
    background-color: #007bff;
    color: #fff;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

.login-form input[type="submit"]:hover {
    background-color: #0056b3;
}

/* Links */
.login-links {
    text-align: center;
}

.login-links a {
    color: #007bff;
    text-decoration: none;
    transition: color 0.3s ease;
}

.login-links a:hover {
    color: #0056b3;
}
</style>

</head>
<body>

<div class="center">

<div class="login-container">

	<h2 class="login-title">PharmaFinder</h2>
	
	<p align="center">Login</p>

	
	<form class="login-form" action="loginAf.do" method="post">
		<input type="text" id="id" name="id" size=20 placeholder="example@test.com">
		<input type="password" name="password" placeholder="Password">
		<input type="submit" value="login">
	</form>
	<br/>
	<div class="login-links">
		<a href="#">아이디/비밀번호 찾기</a>
		
		<div>
			<p class="mb-0">
				회원이 아니신가요? <a href="regi.do" class="text-white-50 fw-bold">회원가입</a>
			</p>
		</div>
	</div>

</div>

</div>

</body>
</html>