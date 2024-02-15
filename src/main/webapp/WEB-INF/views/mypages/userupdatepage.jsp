<%@page import="ssg.middlepj.pharmafinder.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<html>
<head>
    <title>회원정보 수정</title>
    <style type="text/css">
        body {
            margin: 0;
            padding: 0;
            font-family: 'Inter', sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f9f9f9;
        }

        .container {
            max-width: 600px;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 10px;
            background-color: #fff;
        }

        h1 {
            font-weight: 800;
            font-size: 32px;
            text-align: center;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            margin-bottom: 20px;
        }

        th, td {
            padding: 10px;
            text-align: left;
        }

        input[type="submit"] {
            padding: 10px;
            margin: 5px 0;
            width: 100%;
            text-align: center;
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            border: none;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        #checkEmailBtn {
            padding: 5px 10px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        #checkEmailBtn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
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
    </div>
</body>
</html>

<script>
$(document).ready(function() {
    $("#checkEmailBtn").click(function() {
        var email = $("#email").val();
        // 이메일 형식을 검증하는 정규 표현식
        var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

        // 이메일 형식 검증
        if (!emailPattern.test(email)) {
            $("#emailMessage").text("유효하지 않은 이메일 형식입니다.").css("color", "red");
        } else {
            $.ajax({
                url: "/emailcheck.do",
                type: "POST",
                data: {email: email},
                success: function(data) {
                    var message = $("#emailMessage");
                    if(data.trim() === "YES") {
                        message.text("사용 가능한 이메일입니다.").css("color", "blue");
                    } else {
                        message.text("이미 사용 중인 이메일입니다.").css("color", "red");
                    }
                },
                error: function() {
                    alert("이메일 중복 체크 중 오류가 발생했습니다.");
                }
            });
        }
    });
});
</script>

