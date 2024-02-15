<%@page import="ssg.middlepj.pharmafinder.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

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
                    <td>
                    	<input type="email" id="email" name="email" required>
        				<button type="button" id="checkEmailBtn">이메일 중복 확인</button>
        				<div id="emailMessage"></div> <!-- 중복 검사 결과 메시지를 표시할 요소 -->
                    </td>
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

<script>
$(document).ready(function() {
    $("#checkEmailBtn").click(function() {
        var email = $("#email").val();
        if(email) {
            $.ajax({
                url: "/emailcheck.do",
                type: "POST",
                data: {email: email},
                success: function(data) {
                    var message = $("#emailMessage");
                    if(data.trim() === "YES") {
                        message.text("사용 가능한 이메일입니다.").css("color", "green");
                    } else {
                        message.text("이미 사용 중인 이메일입니다.").css("color", "red");
                    }
                },
                error: function() {
                    alert("이메일 중복 체크 중 오류가 발생했습니다.");
                }
            });
        } else {
            alert("이메일을 입력해주세요.");
        }
    });
});
</script>