<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Find Password</title>
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
    .form input[type="text"],
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
<h1 class="title">비밀번호 찾기</h1>
        
<% String errorMessage = (String) request.getAttribute("error");
	if (errorMessage != null) { %>
    	<div style="color: red;"><%= errorMessage %></div>
<% } %>

<form class="form" action="findPasswordAf.do" method="post">
    <label for="username"></label>
    <input type="text" id="username" name="username" placeholder="아이디를 입력하세요" required><br><br>
    <label for="email"></label>
    <input type="email" id="email" name="email" placeholder="이메일을 입력하세요" required><br><br>
    <input type="submit" value="임시 비밀번호 발급하기">
</form>
</div>
</div>

<!-- 공백일 때 폼 제출 안됨 -->
<script>
    function validateForm() {
        var username = document.getElementById("username").value;
        var email = document.getElementById("email").value;

        if (username.trim() == "") {
            alert("아이디를 입력하세요.");
            return false;
        }

        if (email.trim() == "") {
            alert("이메일을 입력하세요.");
            return false;
        }

        return true;
    }
</script>

</body>
</html>
