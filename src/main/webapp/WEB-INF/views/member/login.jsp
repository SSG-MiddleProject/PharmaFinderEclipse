<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

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
.container {
    max-width: 600px;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 10px;
    background-color: #f9f9f9;
}

/* PharmaFinder Login */
.title {
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
<div class="container">
	<h1 class="title">PharmaFinder</h1>
	
	<p align="center">Login</p>
	
	<!-- 로그인 실패 시 에러 메시지 출력 -->
        <% if (request.getAttribute("error") != null) { %>
            <p style="color: red;">
            <%= request.getAttribute("error") %></p>
        <% } %>

	
	<form class="login-form" action="loginAf.do" method="post">
		<input type="text" id="username" name="username" size=20 placeholder="Id" required>
		<input type="password" name="password" placeholder="Password" required>
		<input type="checkbox" id="saveId" name="saveId">
		<label for="saveUsername">아이디 저장</label><br/><br/>
		<input type="submit" value="로그인">
	</form>
	<br/>
	<div class="login-links">
		<a href="findUsername.do">아이디 찾기 / </a>
		<a href="findPassword.do">비밀번호 찾기</a>
		<div>
			<p class="mb-0">
				회원이 아니신가요?<a href="regiSelect.do" class="text-white-50 fw-bold">회원가입</a><br/>
			</p>
		</div>
	</div>

</div>

</div>

<script type="text/javascript">
$(document).ready(function() {
    // 페이지 로드 시, 저장된 아이디가 있는지 확인하고 아이디 입력 필드에 설정
    var savedUsername = localStorage.getItem("savedUsername");
    if (savedUsername) {
        $("#username").val(savedUsername);
        $("#saveUsername").prop("checked", true);
    }
    
    // 아이디 저장 체크박스 이벤트 리스너
    $("#saveUsername").change(function() {
        if ($(this).is(":checked")) {
            // 체크되었을 때, 아이디를 로컬 스토리지에 저장
            localStorage.setItem("savedUsername", $("#username").val());
        } else {
            // 체크 해제되었을 때, 로컬 스토리지에서 아이디 삭제
            localStorage.removeItem("savedUsername");
        }
    });
});
</script>

</body>
</html>
