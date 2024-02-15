<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Registration</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
    <script src="https://code.jquery.com/jquery-3.7.1.js"
            integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
</head>
<body>

<div class="columns is-centered">
    <div class="column is-half">
        <section class="section">
            <div class="container">
                <h1 class="title has-text-centered">PharmaFinder</h1>
                <h2 class="subtitle has-text-centered">회원가입</h2>
                <form action="userRegiAf.do" method="post">
                    <div class="field">
                        <label class="label" for="username">아이디</label>
                        <div class="control has-icons-left">
                            <input class="input" type="text" name="username" id="username" placeholder="아이디 입력"
                                   required>
                            <span class="icon is-small is-left">
                                <i class="fas fa-user"></i>
                            </span>
                        </div>
                        <p id="usernamecheck"></p>
                    </div>
                    <div class="field">
                        <label class="label" for="email">이메일</label>
                        <div class="control has-icons-left">
                            <input class="input" type="email" name="email" id="email" placeholder="이메일 입력" required>
                            <span class="icon is-small is-left">
                                <i class="fas fa-envelope"></i>
                            </span>
                        </div>
                        <p id="emailcheck"></p>
                    </div>
                    <div class="field">
                        <label class="label" for="password">비밀번호</label>
                        <div class="control has-icons-left">
                            <input class="input" type="password" name="password" id="password" placeholder="비밀번호 입력"
                                   required>
                            <span class="icon is-small is-left">
                                <i class="fas fa-lock"></i>
                            </span>
                        </div>
                        <p id="passwordcheck"></p>
                    </div>
                    <div class="field">
                        <div class="control">
                            <button class="button is-primary is-fullwidth" type="submit">회원가입</button>
                        </div>
                    </div>
                </form>
            </div>
        </section>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        // 아이디 유효성 검사 및 중복 확인
        $('#username').keyup(function () {
            var username = $(this).val().trim();
            var idPattern = /^[a-z0-9]{5,15}$/;
            if (username === "") {
                $("#usernamecheck").empty();
            } else {
                if (!idPattern.test(username)) {
                    $("#usernamecheck").text("유효하지 않은 형식입니다 (5-15 글자, 영문 소문자 및 숫자만 가능)").css("color", "#ff0000");
                } else {
                    $.ajax({
                        url: "usernamecheck.do",
                        type: "POST",
                        data: {"username": username},
                        success: function (response) {
                            if (response.trim() === "YES") {
                                $("#usernamecheck").text("사용 가능합니다").css("color", "#00ff00");
                            } else {
                                $("#usernamecheck").text("이미 사용 중인 아이디입니다").css("color", "#ff0000");
                            }
                        }
                    });
                }
            }
        });


        // 이메일 유효성 검사 및 중복 확인
        $('#email').keyup(function () {
            var email = $(this).val().trim();
            var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if (email === "") {
                $("#emailcheck").empty();
            } else {
                if (!emailPattern.test(email)) {
                    $("#emailcheck").text("유효하지 않은 이메일 형식입니다").css("color", "#ff0000");
                } else {
                    $.ajax({
                        url: "emailcheck.do",
                        type: "POST",
                        data: {"email": email},
                        success: function (response) {
                            if (response.trim() === "YES") {
                                $("#emailcheck").text("사용 가능합니다").css("color", "#00ff00");
                            } else {
                                $("#emailcheck").text("이미 사용 중인 이메일입니다").css("color", "#ff0000");
                            }
                        }
                    });
                }
            }
        });

        // 비밀번호 유효성 검사
        $('#password').keyup(function () {
            var password = $(this).val();
            var passwordPattern = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*]).{5,15}$/;
            if (!passwordPattern.test(password)) {
                $("#passwordcheck").text("비밀번호는 5-15 글자이며, 영문, 숫자, 특수문자를 최소한 하나씩 포함해야 합니다").css("color", "#ff0000");
            } else {
                $("#passwordcheck").text("사용 가능합니다").css("color", "#00ff00");
            }
        });
    });
</script>


</body>
</html>
