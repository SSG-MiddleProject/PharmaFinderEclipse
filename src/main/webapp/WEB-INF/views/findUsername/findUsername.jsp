<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
</head>
<body>

<h1>아이디 찾기</h1>

<form action="findUsername.do" method="post">
    <label for="email">이메일:</label>
    <input type="email" id="email" name="email" required><br><br>
    <input type="submit" value="아이디 찾기">
</form>

</body>
</html>
