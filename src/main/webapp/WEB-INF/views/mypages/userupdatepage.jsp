<!-- <%@page import="ssg.middlepj.pharmafinder.dto.MemberDto"%> <%@ page
        language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <%@ page
        import="javax.servlet.http.HttpSession"%> -->
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
        <!-- <% MemberDto loginedMember = (MemberDto) session.getAttribute("member"); %> -->

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
                            id="email"
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
        </div>

        <div class="field">
            <label class="label">Password</label>
            <div class="control has-icons-left has-icons-right">
                <input class="input" type="password" value="adfafdad" />
                <span class="icon is-small is-left">
          <i class="fas fa-user"></i>
        </span>
                <span class="icon is-small is-right">
          <i id="checkPW" style="display: none" class="fas fa-check"></i>
        </span>
            </div>

            <p id="successEmail" style="display: none" class="help is-success">
                사용 가능한 비밀번호입니다.
            </p>
            <p id="failEmail" style="display: none" class="help is-danger">
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

        <!-- <table>
      <tr>
        <th>아이디</th>
        <td>
          <input
            type="text"
            name="username"
            value="<%=loginedMember.getUsername()%>"
            readonly
          />
        </td>
      </tr>
      <tr>
        <th>이메일</th>
        <td>
          <input type="email" id="email" name="email" required />
          <button type="button" id="checkEmailBtn">이메일 중복 확인</button>
          <div id="emailMessage"></div>
          <!-- 중복 검사 결과 메시지를 표시할 요소 -->
        <!-- </td>
          </tr>
          <tr>
            <th>비밀번호</th>
            <td><input type="password" name="password" required /></td>
          </tr>
        </table> -->
    </form>
</div>

<script>
    $(document).ready(function () {
        $("#checkEmailBtn").click(function () {
            var email = $("#email").val();
            // 이메일 형식을 검증하는 정규 표현식
            var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

            // 이메일 형식 검증
            if (!emailPattern.test(email)) {
                $("#emailMessage")
                    .text("유효하지 않은 이메일 형식입니다.")
                    .css("color", "red");
            } else {
                $.ajax({
                    url: "/emailcheck.do",
                    type: "POST",
                    data: { email: email },
                    success: function (data) {
                        var message = $("#emailMessage");
                        if (data.trim() === "YES") {
                            message.text("사용 가능한 이메일입니다.").css("color", "blue");
                        } else {
                            message.text("이미 사용 중인 이메일입니다.").css("color", "red");
                        }
                    },
                    error: function () {
                        alert("이메일 중복 체크 중 오류가 발생했습니다.");
                    },
                });
            }
        });
    });
</script>
