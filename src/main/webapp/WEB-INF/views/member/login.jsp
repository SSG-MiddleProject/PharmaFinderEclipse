<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�α��� ������</title>
</head>
<body>

<div class="center">

<form action="loginAf.do" method="post">

	<h2>PharmaFinder</h2>
	
	<p>Login</p>

	<button onclick="">�Ϲ�ȸ��</button>
	<button onclick="">�౹ȸ��</button>
	<input type="text" id="id" name="id" size=20
		placeholder="example@test.com">
	<input type="password">

	<span><input type="submit" value="login"></span>
	
	<a href="#" >���̵�/��й�ȣ ã��</a>
	<div>
		<p class="mb-0">
			ȸ���� �ƴϽŰ���? <a href="regi.do" class="text-white-50 fw-bold">ȸ������</a>
		</p>
	</div>

</form>

</div>

</body>
</html>