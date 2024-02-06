<%@page import="ssg.middlepj.pharmafinder.dto.MemberDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입(약국)</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

<%
List<MemberDto> list = (List<MemberDto>) request.getAttribute("list");
%>

<h1>회원가입</h1>

<!-- <form action="regiAf.do" id="frm" method="post"> -->

<label for="roll">회원 유형:</label>
<select id="roll" name="roll">
	<option value="0">약국 관리자</option>
	<option value="1">약국 직원</option>
	<option value="2">고객</option>
</select>

<!-- 공통 입력 필드 -->
<div id="commonFields">
<table>
<tr>
	<th>아이디</th>
	<td><input type="text" name="username" id="username" size="30">
		<p id="usernamecheckResult" style="font-size: 10px"></p></td>
	</tr>
	<tr>
		<th>패스워드</th>
		<td><input type="password" name="password" size="30"></td>
	</tr>

	<tr>
		<th>이메일</th>
		<td><input type="text" name="email" size="30"></td>
	</tr>
	
</table>
<br>
</div>

<!-- 약국 관리자 및 직원 전용 입력 필드 -->
<div id="pharmacyFields" style="display:none;">        
<table>
<tr>
	<th>상호</th>
	<td><input type="text" name="storeId" size="30"></td>
</tr>
<tr>
	<th>사업자번호</th>
	<td>
		<input type="text" name="businessNumLeft" size="6"> -
		<input type="text" name="businessNumMiddle" size="4"> -
		<input type="text" name="businessNumRight" size="6">
	</td>
</tr>

<tr>
	<th>요일</th>
	<th>시작 시간</th>
	<th>종료 시간</th>
</tr>
</tr>

<%
String[] days = {"일", "월", "화", "수", "목", "금", "토"};
%>
<%
for (String day : days) {
%>
<tr>
	<td><%=day%>요일</td>
	<td><input type="time" name="<%=day%>Start" value="09:00:00"></td>
	<td><input type="time" name="<%=day%>End" value="19:00:00"></td>
</tr>
<%
}
%>
</table>
</div>

<input type="submit" value="회원가입">

</form>

<!-- roll에 따른 회원가입 폼 추가작성 -->
<script type="text/javascript">
    $(document).ready(function() {
        $("#roll").change(function() {
            var selectedRoll = $(this).val();
            if (selectedRoll == "0" || selectedRoll == "1") {
                $("#pharmacyFields").show();
            } else {
                $("#pharmacyFields").hide();
            }
        });
    });
</script>

<!-- id 유효성 검사(실시간 중복 체크) -->
<script type="text/javascript">
	$(document).ready(
			function() {

				$("#username").keyup(
						function() { // 입력 필드에서 키보드 입력이 이루어질 때마다 이벤트 처리
							var username = $(this).val().trim();

							// username 이 비어있지 않은 경우 서버에 요청
							if (username != "") {
								$.ajax({
									url : "./usernamecheck.do",
									type : "GET",
									data : {
										"username" : username
									},
									success : function(msg) {
										// alert('success');
										if (msg.trim() === "YES") {
											$("#usernamecheckResult").css(
													"color", "#0000ff");
											$("#usernamecheckResult").text(
													"사용가능한 아이디입니다");
										} else {
											$("#usernamecheckResult").css(
													"color", "#ff0000");
											$("#usernamecheckResult").text(
													"이미 사용중인 아이디입니다");
										}
									},
									error : function() {
										$("#usernamecheckResult").text(
												'아이디 확인 중 오류가 발생했습니다');
									}
								});
							} else {
								$("#usernamecheckResult").text(""); // 입력값이 없을 때 메세지 제거
							}
						});
/* 
				$("#regibtn").click(function() {

					// 빈칸들을 조사
					// id, pw, name

					$("#frm").submit();
				});
				 */
			});
	;
</script>

</body>
</html>