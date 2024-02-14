<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="javax.servlet.http.HttpSession"%>

<html>
<head>
    <title>회원정보 수정</title>
</head>
<body>
    <h1>회원정보 수정 페이지</h1>
    <form action="userUpdateAf.do" method="post">
        <% 
            HttpSession sessionobj = request.getSession(false);
            if (sessionobj != null && sessionobj.getAttribute("username") != null) {
                String username = (String) sessionobj.getAttribute("username");
        %>
            <table>
                <tr>
                    <th>아이디</th>
                    <td><input type="text" name="username" value="<%= username %>" readonly></td>
                </tr>
                <tr>
                    <th>이메일</th>
                    <td><input type="email" name="email" required></td>
                </tr>
                <tr>
                    <th>비밀번호</th>
                    <td><input type="password" name="password" required></td>
                </tr>
            </table>
            <br>
            <input type="submit" value="수정 완료">
        <% 
            } else {
                // 사용자가 로그인하지 않았을 경우 로그인 페이지로 리다이렉트
                response.sendRedirect("login.do");
            }
        %>
    </form>
</body>
</html>
