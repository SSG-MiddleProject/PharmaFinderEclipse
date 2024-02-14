<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Find Username</title>
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

/* Container */
.container {
    max-width: 600px;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 10px;
    background-color: #f9f9f9;
}

/* Title */
.title {
    font-weight: 800;
    font-size: 48px;
    text-align: center;
    margin-bottom: 20px;
}

/* Form fields */
.form input[type="email"],
.form input[type="submit"] {
    width: 100%;
    padding: 10px;
    margin-bottom: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    box-sizing: border-box;
}

/* Submit button */
.form input[type="submit"] {
    border: none;
    border-radius: 5px;
    background-color: #007bff;
    color: #fff;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

.form input[type="submit"]:hover {
    background-color: #0056b3;
}
</style>
</head>
<body>

<div class="center">
<div class="container">
<h1 class="title">아이디 찾기</h1>

<% String errorMessage = (String) request.getAttribute("error");
   if (errorMessage != null) { %>
    <div style="color: red;"><%= errorMessage %></div>
<% } %>

<form class="form" action="findUsernameAf.do" method="post">
    <label for="email">이메일</label>
    <input type="email" id="email" name="email" placeholder="이메일을 입력하세요" required><br>
    <input type="submit" value="아이디 찾기">
</form>

</body>
</html>