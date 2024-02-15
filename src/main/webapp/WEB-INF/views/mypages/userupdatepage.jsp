
<%@page import="ssg.middlepj.pharmafinder.dto.MemberDto" %>
<%@ page
        language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page
        import="javax.servlet.http.HttpSession" %>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link
        rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css"
/>
<link
        rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"
/>

<style>
    .input-box {
        display: flex;
        align-items: center;
        justify-content: center;
        padding: 0.5rem;
        width: 30rem;
    }

    #full-screen {
        width: calc(100vw - 4rem);
    }
</style>

<div id="full-screen"
     style="
    /*width: 100vw;*/
    height: 100vh;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
  "
>
    <h1 class="title is-3">PharmaFinder</h1>
    <p>회원정보 수정</p>
    <form
            action="userUpdateAf.do"
            method="post"
            style="display: flex; flex-direction: column"
    >
        <% MemberDto loginedMember = (MemberDto) session.getAttribute("member"); %>

        <div class="field">
            <label class="label">User ID</label>
            <div class="control has-icons-left has-icons-right">
                <input
                        class="input"
                        type="text"
                        name="username"
                        value="<%=loginedMember.getUsername()%>"
                        disabled
                />
                <span class="icon is-small is-left">
          <i class="fas fa-user"></i>
        </span>
            </div>
        </div>

        <div class="field">
            <label class="label">Email</label>
            <div class="control has-icons-left has-icons-right">
                <div style="display: flex; align-items: center">
                    <input
                            class="input"
                            placeholder="User Email"
                            type="email"
                            id="inputEmail"
                            name="email"
                            style="flex-shrink: 0"
                            value="<%=loginedMember.getEmail()%>"
                            required
                    />
                    <button
                            style="margin-left: 5px"
                            type="button"
                            class="button is-small is-rounded"
                            id="checkEmailBtn"
                    >
                        중복 확인
                    </button>
                </div>
                <span class="icon is-small is-left">
          <i class="fas fa-envelope"></i>
        </span>
                <span class="icon is-small is-right">
          <i id="checkEmail" style="display: none" class="fas fa-check"></i>
        </span>
            </div>
            <p id="successEmail" style="display: none" class="help is-success">
                사용 가능한 이메일입니다.
            </p>
            <p id="failEmail" style="display: none" class="help is-danger">
                이미 사용중인 이메일입니다.
            </p>
            <p id="notEmail" style="display: none" class="help is-danger">
                유효하지않은 이메일입니다.
            </p>
        </div>

        <div class="field">
            <label class="label">Password</label>
            <div class="control has-icons-left has-icons-right">
                <input id="checkPassword" name="password" class="input" type="password" placeholder="New Password"/>
                <span class="icon is-small is-left">
          <i class="fas fa-lock"></i>
                </span>
            </div>

            <p id="successPW" style="display: none" class="help is-success">
                사용 가능한 비밀번호입니다.
            </p>
            <p id="failPW" style="display: none" class="help is-danger">
                비밀번호는 8자 이상의 대문자, 소문자, 숫자, 특수문자가 포함돼야 합니다.
            </p>
        </div>

        <button
                type="submit"
                class="button is-success is-rounded"
                style="margin-top: 20"
        >
            수정완료
        </button>

    </form>
</div>

<script>


    $(document).ready(function() {
        $("#checkEmailBtn").click(function () {
            $("#notEmail").hide();
            $("#failEmail").hide();
            $("#successEmail").hide();

            console.log("click");
            var email = $("#inputEmail").val();
            console.log(email);
            // 이메일 형식을 검증하는 정규 표현식
            var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

            // 이메일 형식 검증
            if (!emailPattern.test(email)) {
                $("#notEmail").show();
            } else {
                $.ajax({
                    url: "/emailcheck.do",
                    type: "POST",
                    data: {email: email},
                    success: function (data) {
                        var message = $("#emailMessage");
                        if (data.trim() === "YES") {
                            $("#successEmail").show();
                        } else {
                            $("#failEmail").show();
                        }
                    },
                    error: function () {
                        alert("이메일 중복 체크 중 오류가 발생했습니다.");
                    }
                });
            }
        });

        $('#checkPassword').keyup(function() {
            $("#successPW").hide();
            $("#failPW").hide();

            var password = $(this).val();
            var passwordPattern = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*]).{5,15}$/;
            if(!passwordPattern.test(password)) {
                $("#failPW").show();
            } else {
                $("#successPW").show();
            }
        });
    });
</script>
