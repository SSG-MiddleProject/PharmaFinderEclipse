<%@page import="ssg.middlepj.pharmafinder.dto.MemberDto"%>
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
        MemberDto loginedMember = (MemberDto) session.getAttribute("member");
        %>
            <table>
                <tr>
                    <th>아이디</th>
                    <td><input type="text" name="username" value="<%=loginedMember.getUsername()%>" readonly></td>
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
       
    </form>
</body>
</html>
