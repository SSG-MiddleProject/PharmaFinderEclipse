<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
   <title>User Registration Result</title>
   
<style type="text/css">
body {
    margin: 0;
    padding: 0;
    font-family: 'Inter', sans-serif;
    background-color: #f9f9f9;
}

.center {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}

.container {
    max-width: 400px;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 10px;
    background-color: #fff;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    text-align: center;
}

strong {
    color: #007bff;
}

a {
    display: inline-block;
    margin-top: 20px;
    padding: 10px 15px;
    background-color: #007bff;
    color: #fff;
    border-radius: 5px;
    text-decoration: none;
    transition: background-color 0.3s ease;
}

a:hover {
    background-color: #0056b3;
}
</style>

</head>

<body>

<div class="center">
    <div class="container">
        <h2>아이디 찾기 결과</h2>
        <p>귀하의 아이디는 <strong>${username}</strong> 입니다.</p>
        <a href="login.do">로그인 페이지로 돌아가기</a>
    </div>
</div>

</body>
</html>