<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
	<meta charset="UTF-8" />
	<title>Login</title>
	<link
			rel="stylesheet"
			href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css"
	/>
	<link
			rel="stylesheet"
			href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"
	/>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<div class="center">
	<div
			class="container"
			style="
          display: flex;
          flex-direction: column;
          align-items: center;
          justify-content: center;
          width: 100vw;
          height: 100vh;
        "
	>
		<h1 class="title is-1" style="font-weight: bold; margin-bottom: 0">
			PharmaFinder
		</h1>
		<p style="font-size: x-large">Login</p>

		<!-- 로그인 실패 시 에러 메시지 출력 -->
		<% if (request.getAttribute("error") != null) { %>
		<p style="color: red"><%= request.getAttribute("error") %></p>
		<% } %>

		<form
				class="login-form"
				style="
            padding-top: 60px;
            display: flex;
            flex-direction: column;
            align-items: center;
            width: 70%;
          "
				action="loginAf.do"
				method="post"
		>
			<input
					class="input is-rounded"
					style="margin-bottom: 1rem; background-color: #f3f4f6; height: 3.5rem; width: 40rem"
					type="text"
					id="username"
					name="username"
<%--					size="30"--%>
					placeholder="UserId"
					required
			/>

			<input
					class="input is-rounded"
					style="background-color: #f3f4f6; height: 3.5rem; width: 40rem"
					type="password"
					name="password"
					placeholder="Password"
					required
			/>
			<!-- <div>
                <input class="input" type="checkbox" id="saveId" name="saveId" />
                <label for="saveUsername">아이디 저장</label>
              </div> -->
			<!-- <input type="submit" value="로그인" /> -->
			<button
					type="submit"
					class="button is-info is-rounded"
					style="margin-top: 20px; width: 7rem"
			>
				로그인
			</button>
		</form>

		<div
				class="login-links"
				style="
            display: flex;
            flex-direction: column;
            align-items: center;
            padding-top: 1rem;
          "
		>
			<div>
				<a href="findUsername.do">아이디 </a> /
				<a href="findPassword.do">비밀번호 찾기</a>
			</div>
			<p class="mb-0">
				아직 회원이 아니신가요?
				<a href="regiSelect.do" class="text-white-50 fw-bold">회원가입</a>
			</p>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function () {
		// 페이지 로드 시, 저장된 아이디가 있는지 확인하고 아이디 입력 필드에 설정
		var savedUsername = localStorage.getItem("savedUsername") || ""; // // 수정: undefined 대신 빈 문자열 설정
		$("#username").val(savedUsername);

		// 아이디 저장 체크박스 이벤트 리스너
		$("#saveId").change(function () {
			// 수정: 체크박스의 ID를 saveId로 변경
			if ($(this).is(":checked")) {
				// 체크되었을 때, 아이디를 로컬 스토리지에 저장
				localStorage.setItem("savedUsername", $("#username").val());
			} else {
				// 체크 해제되었을 때, 로컬 스토리지에서 아이디 삭제
				localStorage.removeItem("savedUsername");
			}
		});
	});
</script>
</body>
</html>
