<%@page import="ssg.middlepj.pharmafinder.dto.MemberDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pharmacy Registration</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- 주소 API 스크립트 추가 -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<style type="text/css">
/* General styling */
body {
	margin: 0;
	padding: 0;
	font-family: 'Inter', sans-serif;
	background-color: #f9f9f9;
}

.center {
	display: flex;
	justify-content: center;
	align-items: center;
	/* height: 100vh; */
}

.container {
	max-width: 400px;
	padding: 20px;
	border: 1px solid #ccc;
	border-radius: 10px;
	background-color: #fff;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.form-group {
	margin-bottom: 15px;
}

label {
	display: block;
	margin-bottom: 5px;
}

input[type="text"], input[type="email"], input[type="password"], input[type="submit"] {
	width: 100%;
	padding: 10px;
	margin-bottom: 10px;
	border: 1px solid #ccc;
	border-radius: 5px;
	box-sizing: border-box;
}

input[type="submit"] {
	background-color: #007bff;
	color: white;
	cursor: pointer;
	border: none;
}

input[type="submit"]:hover {
	background-color: #0056b3;
}

#usernamecheck, #emailcheck, #passwordcheck {
	font-size: 12px;
}
</style>

</head>
<body>

<div class="center">
	<div class="container">
		<h2 style="text-align: center; margin-bottom: 20px;">회원가입(약국)</h2>
		<form action="pharmacyRegiAf.do" method="post" id="regiForm">
			<div class="form-group">
				<label for="username"></label>
				<input type="text" name="username" id="username" placeholder="아이디를 입력해주세요" required>
				<button type="button" id="checkUsername">중복 확인</button>
                <p id="usernamecheck"></p>
			</div>
			<div class="form-group">
				<label for="email"></label>
				<input type="email" name="email" id="email" placeholder="이메일을 입력해주세요" required>
				<button type="button" id="checkEmail">중복 확인</button>
                <p id="emailcheck"></p>
			</div>
			<div class="form-group">
				<label for="password"></label>
				<input type="password" name="password" id="password" placeholder="비밀번호를 입력해주세요" required>
				<p id="passwordcheck"></p>
			</div>
            <div class="form-group">
                <label for="dutyName"></label>
                <input type="text" name="dutyName" id="dutyName" placeholder="약국이름을 입력해주세요" required>
            </div>
            <div class="form-group">
                <label for="dutyTel1">전화번호</label>
                <input type="text" name="dutyTel1" id="dutyTel1" placeholder="010-1234-5678" required>
            </div>
            <div class="form-group">
                <label for="bnRegNum">사업자번호</label>
                <input type="text" name="bnRegNum" id="bnRegNum" placeholder="123-45-67890" required>
            </div>
            <div class="form-group">
                <label for="sample4_postcode"></label>
                <button type="button" onclick="sample4_execDaumPostcode()">우편번호 찾기</button>
                <input type="text" id="sample4_postcode" name="postcode" placeholder="우편번호" required>                
            </div>
            <div class="form-group">
                <label for="sample4_dutyAddr"></label>
                <input type="text" id="sample4_dutyAddr" name="dutyAddr" placeholder="도로명주소" required>
            </div>
            <div class="form-group">
		    <label for="dutyTime1s">월요일:</label>
		    <input type="time" name="dutyTime1s" id="dutyTime1s" value="09:00" required>
		    ~
		    <input type="time" name="dutyTime1c" id="dutyTime1c" value="18:00" required>
			</div>
			<div class="form-group">
			    <label for="dutyTime2s">화요일:</label>
			    <input type="time" name="dutyTime2s" id="dutyTime2s" value="09:00" required>
			    ~
			    <input type="time" name="dutyTime2c" id="dutyTime2c" value="18:00" required>
			</div>
			<div class="form-group">
			    <label for="dutyTime3s">수요일:</label>
			    <input type="time" name="dutyTime3s" id="dutyTime3s" value="09:00" required>
			    ~
			    <input type="time" name="dutyTime3c" id="dutyTime3c" value="18:00" required>
			</div>
			<div class="form-group">
			    <label for="dutyTime4s">목요일:</label>
			    <input type="time" name="dutyTime4s" id="dutyTime4s" value="09:00" required>
			    ~
			    <input type="time" name="dutyTime4c" id="dutyTime4c" value="18:00" required>
			</div>
			<div class="form-group">
			    <label for="dutyTime5s">금요일:</label>
			    <input type="time" name="dutyTime5s" id="dutyTime5s" value="09:00" required>
			    ~
			    <input type="time" name="dutyTime5c" id="dutyTime5c" value="18:00" required>
			</div>
			<div class="form-group">
			    <label for="dutyTime6s">토요일:</label>
			    <input type="time" name="dutyTime6s" id="dutyTime6s" value="09:00" required>
			    ~
			    <input type="time" name="dutyTime6c" id="dutyTime6c" value="18:00" required>
			</div>
			<div class="form-group">
			    <label for="dutyTime7s">일요일:</label>
			    <input type="time" name="dutyTime7s" id="dutyTime7s" value="09:00" required>
			    ~
			    <input type="time" name="dutyTime7c" id="dutyTime7c" value="18:00" required>
			</div>
			<div class="form-group">
			    <label for="dutyTime8s">공휴일:</label>
			    <input type="time" name="dutyTime8s" id="dutyTime8s" value="09:00" required>
			    ~
			    <input type="time" name="dutyTime8c" id="dutyTime8c" value="18:00" required>
			</div>  
			<input type="submit" value="회원가입">
		</form>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function() {
    // Username Validation & Duplication Check
    $('#username').keyup(function() {
        var username = $(this).val().trim();
        var idPattern = /^[a-z0-9]{5,15}$/;
        if(!idPattern.test(username)) {
            $("#usernamecheck").text("Invalid format (5-15 characters, a-z, 0-9)").css("color", "#ff0000");
        } else {
            $("#usernamecheck").empty();
        }
    });

    $('#checkUsername').click(function() {
        var username = $('#username').val().trim();
        if(username !== "") {
            $.ajax({
                url: "usernamecheck.do",
                type: "POST",
                data: { "username": username },
                success: function(response) {
                    $("#usernamecheck").text(response.trim() === "YES" ? "Available" : "Already in use").css("color", "#0000ff");
                }
            });
        }
    });

    // Email Validation & Duplication Check
    $('#email').keyup(function() {
        var email = $(this).val().trim();
        var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if(!emailPattern.test(email)) {
            $("#emailcheck").text("Invalid email format").css("color", "#ff0000");
        } else {
            $("#emailcheck").empty();
        }
    });

    $('#checkEmail').click(function() {
        var email = $('#email').val().trim();
        if(email !== "") {
            $.ajax({
                url: "emailcheck.do",
                type: "POST",
                data: { "email": email },
                success: function(response) {
                    $("#emailcheck").text(response.trim() === "YES" ? "Available" : "Already in use").css("color", "#0000ff");
                }
            });
        }
    });

    // Password Validation
    $('#password').keyup(function() {
        var password = $(this).val();
        var passwordPattern = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*]).{5,15}$/;
        if(!passwordPattern.test(password)) {
            $("#passwordcheck").text("Password must be 5-15 characters and include at least one letter, one number, and one special character.").css("color", "#ff0000");
        } else {
            $("#passwordcheck").empty();
        }
    });
    
    // 전화번호 형식 변환 (010-1234-5678)
    $('#dutyTel1').keyup(function() {
        var val = this.value.replace(/\D/g, '');
        var newVal = '';
        if (val.length > 3) {
            newVal += val.substr(0, 3) + '-';
            val = val.substr(3);
        }
        if (val.length > 4) {
            newVal += val.substr(0, 4) + '-';
            val = val.substr(4);
        }
        newVal += val;
        this.value = newVal.substring(0, 13); // 최대 입력 가능한 문자 수 제한
    });

    // 사업자등록번호 형식 변환 (123-45-67890)
    $('#bnRegNum').keyup(function() {
        var val = this.value.replace(/\D/g, '');
        var newVal = '';
        if (val.length > 3) {
            newVal += val.substr(0, 3) + '-';
            val = val.substr(3);
        }
        if (val.length > 2) {
            newVal += val.substr(0, 2) + '-';
            val = val.substr(2);
        }
        newVal += val;
        this.value = newVal.substring(0, 12); // 최대 입력 가능한 문자 수 제한
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
            document.getElementById('sample4_dutyAddr').value = data.roadAddress;
        }
    }).open();
}
</script>

</body>
</html>