<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>회원정보 수정</title>
</head>
<body>
    <h1>회원정보 수정 페이지</h1>
    <!-- 여기에 회원정보 수정 폼 추가 -->
 <form action="userupdateAf.do" method="post">
    <input type="hidden" name="id" value="${user.id}">
    <table>
        <tr>
            <th>아이디</th>
            <td>${user.username}</td>
        </tr>
        <tr>
            <th>이메일</th>
            <td><input type="email" name="email" value="${user.email}" required></td>
        </tr>
        <tr>
            <th>비밀번호</th>
            <td><input type="password" name="password" required></td>
        </tr>
    </table>
    <br>
    <input type="submit" value="수정 완료">
</form>
</body>
</html>
