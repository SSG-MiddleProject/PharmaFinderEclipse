<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>로그인 페이지</title>
</head>
<body>

<div class="center">

<form action="loginAf.do" method="post">

	<h2>PharmaFinder</h2>
	
	<p>Login</p>

	<button onclick="">일반회원</button>
	<button onclick="">약국회원</button>
	<input type="text" id="id" name="id" size=20
		placeholder="example@test.com">
	<input type="password">

	<span><input type="submit" value="login"></span>
	
	<a href="#" >아이디/비밀번호 찾기</a>
	<div>
		<p class="mb-0">
			회원이 아니신가요? <a href="regi.do" class="text-white-50 fw-bold">회원가입</a>
		</p>
	</div>

</form>

</div>

</body>
</html>