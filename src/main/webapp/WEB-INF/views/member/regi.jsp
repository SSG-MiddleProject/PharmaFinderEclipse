<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입(약국)</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

<h1>회원가입</h1>

<form action="regiAf.do" id="frm" method="post">

<table>
<tr>
	<th>아이디</th>
	<td>
		<input type="text" name="username" id="username" size="30">
		<p id="idcheck" style="font-size: 10px"></p>
		<input type="button" class="btn btn-primary" id="id_chk_btn" value="id확인">	
	</td>
</tr>
<tr>
	<th>패스워드</th>
	<td>
		<input type="text" name="password" size="30">
	</td>
</tr>
<tr>
	<th>상호</th>
	<td>
		<input type="text" name="storeId" size="30">
	</td>
</tr>
<tr>
	<th>이메일</th>
	<td>
		<input type="text" name="email" size="30">
	</td>
</tr>
<tr>
	<th>사업자번호</th>
	<td>
		<input type="text" name="businessNumLeft" size="6"> -
		<input type="text" name="businessNumMiddle" size="4"> -
		<input type="text" name="businessNumRight" size="6">
	</td>
</tr>
</table>
<br>

<table>
<tr>
	<th>요일</th>
	<th>시작 시간</th>
	<th>종료 시간</th>
</tr>
</tr>

<% String[] days = {"일", "월", "화", "수", "목", "금", "토"}; %>
<% for(String day : days) { %>
<tr>
    <td><%=day %>요일</td>
    <td><input type="time" name="<%= day %>Start" value="09:00:00"></td>
    <td><input type="time" name="<%= day %>End" value="19:00:00"></td>
</tr>
<% } %>
</table>
</form>

<button type="button" onclick="submitOperatingHours()">등록하기</button>

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
			url:"idcheck.do",
			type:"post",
			data:{ "id":$("#username").val() },
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

<!-- 운영시간 정보 -->
<script type="text/javascript">
function submitOperatingHours() {
    var form = document.getElementById('operatingHoursForm');
    var operatingHours = {};
    
    <% for(String day : days) { %>
    operatingHours["<%= day %>"] = {
        start: form["<%= day %>Start"].value,
        end: form["<%= day %>End"].value
    };
    <% } %>
    
    // JSON 형식으로 변환
    var jsonOperatingHours = JSON.stringify(operatingHours);
    
    // fetch API를 사용하여 서버에 POST 요청 전송
    fetch('/path/to/your/server/endpoint', {
        method: 'POST', // HTTP 메소드 지정
        headers: {
            'Content-Type': 'application/json' // 컨텐츠 타입 지정
        },
        body: jsonOperatingHours // 전송할 데이터
    })
    .then(response => {
        if(response.ok) {
            return response.json(); // 응답을 JSON 형태로 파싱
        }
        throw new Error('Network response was not ok.');
    })
    .then(data => {
        console.log(data); // 서버로부터 반환된 데이터 처리
    })
    .catch(error => {
        console.error('There has been a problem with your fetch operation:', error);
    });
}
</script>

</body>
</html>