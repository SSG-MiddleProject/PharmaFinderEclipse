<%@page import="ssg.middlepj.pharmafinder.dto.MemberDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입(약국)</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- 주소 API 스크립트 추가 -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	
</head>
<body>

<h1>회원가입</h1>
 
<form action="pharmacyRegiAf.do" method="post" id="regiForm">

<!-- 약국 관리자 및 직원 전용 입력 필드 -->       
<table>
<tr>
    <th>아이디</th>
    <td>
        <input type="text" name="username" id="username">
		<p id="idcheck" style="font-size: 10px"></p>
		<input type="button" class="btn btn-primary" id="id_chk_btn" value="id확인">	
    </td>
</tr>
<tr>
    <th>이메일</th>
    <td>
        <input type="email" name="email" id="email" required>
    </td>
</tr>
<tr>
    <th>비밀번호</th>
    <td>
        <input type="password" name="password" id="password" required>
    </td>
</tr>
<tr>
	<th>상호</th>
	<td>
		<input type="text" name="dutyName" size="30">
	</td>
</tr>
<tr>
    <th>전화번호</th>
    <td>
		<input type="text" name="dutyTel1" placeholder="예: 010-1234-5678">
	</td>
</tr>
<tr>
	<th>사업자번호</th>
	<td>
		<input type="text" name="bnRegNum" placeholder="예: 123-45-67890">
	</td>
</tr>
<!-- 주소 찾기 API를 통한 주소 입력 필드 -->
<tr>
    <th>우편번호</th>
    <td><input type="text" id="sample4_postcode" name="postcode" placeholder="우편번호">
    <input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br></td>
</tr>
<tr>
    <th>도로명주소</th>
    <td><input type="text" id="sample4_dutyAddr" name="dutyAddr" placeholder="도로명주소"></td>
</tr>
<tr>
    <th>지번주소</th>
    <td><input type="text" id="sample4_jibunAddress" name="jibunAddress" placeholder="지번주소"></td>
</tr>
<tr>
    <th>상세주소</th>
    <td><input type="text" id="sample4_detailAddress" name="detailAddress" placeholder="상세주소"></td>
</tr>
<tr>
    <td>월요일</td>
    <td><input type="time" name="dutyTime1s" value="09:00"></td>
    <td><input type="time" name="dutyTime1c" value="18:00"></td>
</tr>
<tr>
    <td>화요일</td>
    <td><input type="time" name="dutyTime2s" value="09:00"></td>
    <td><input type="time" name="dutyTime2c" value="18:00"></td>
</tr>
<tr>
    <td>수요일</td>
    <td><input type="time" name="dutyTime3s" value="09:00"></td>
    <td><input type="time" name="dutyTime3c" value="18:00"></td>
</tr>
<tr>
    <td>목요일</td>
    <td><input type="time" name="dutyTime4s" value="09:00"></td>
    <td><input type="time" name="dutyTime4c" value="18:00"></td>
</tr>
<tr>
    <td>금요일</td>
    <td><input type="time" name="dutyTime5s" value="09:00"></td>
    <td><input type="time" name="dutyTime5c" value="18:00"></td>
</tr>
<tr>
    <td>토요일</td>
    <td><input type="time" name="dutyTime6s" value="09:00"></td>
    <td><input type="time" name="dutyTime6c" value="18:00"></td>
</tr>
<tr>
    <td>일요일</td>
    <td><input type="time" name="dutyTime7s" value="09:00"></td>
    <td><input type="time" name="dutyTime7c" value="18:00"></td>
</tr>
<tr>
    <td>공휴일</td>
    <td><input type="time" name="dutyTime8s" value="09:00"></td>
    <td><input type="time" name="dutyTime8c" value="18:00"></td>
</tr>
</table>
<input type="submit" value="회원가입">
</form>

<!-- id 유효성 검사 -->
<script type="text/javascript">
$(document).ready(function () {
	
	$("#id_chk_btn").click(function () {
		var inputId = $("#username").val().trim();
		
		if($("#username").val().trim() === "") {
	        alert("아이디를 입력해주세요.");
	        return;
		}
		
		$.ajax({
			url:"idcheck.do",	//수정 필요한 부분
			type:"post",
			data:{ "username": inputId }, //수정 필요한 부분
			success:function(msg){
				// alert('success');
				if(msg.trim() === "YES"){
					$("#idcheck").css("color", "#0000ff");
					$("#idcheck").text("사용할 수 있는 아이디입니다");
				}else{
					$("#idcheck").css("color", "#ff0000");
					$("#idcheck").text("사용중인 아이디입니다");
					$("#username").val("");
				}
			},
			error:function(){
				alert('아이디 확인 중 오류가 발생했습니다');
			}
		});				
	});		

	$("#regibtn").click(function () {
		
		// 빈칸 검사(id, password)
		if($("#username").val().trim() === "") {
		    alert("아이디를 입력해주세요.");
		    return;
		}
		
		if($("#password").val().trim() === "") {
		    alert("비밀번호를 입력해주세요.");
		    return;
		}
				
		$("#frm").submit();
	});
	
});
</script>

<!-- 도로명 주소 검색 기능 -->
<script>
function sample4_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을 때 실행할 코드를 작성하는 부분.
            // 도로명 주소나 지번 주소 등을 필요한 input field에 값으로 설정
            document.getElementById('sample4_postcode').value = data.zonecode;
            document.getElementById('sample4_dutyAddr').value = data.dutyAddr;
            document.getElementById('sample4_jibunAddress').value = data.jibunAddress;
            
            // 상세주소에 초점 맞추기
            document.getElementById('sample4_detailAddress').focus();
        }
    }).open();
}
</script>

</body>
</html>