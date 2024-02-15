<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Registration</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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
	height: 100vh;
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

input[type="text"], input[type="email"], input[type="password"], input[type="submit"]
	{
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
			<h2 style="text-align: center; margin-bottom: 20px;">회원가입</h2>
			<form action="userRegiAf.do" method="post">
				<div class="form-group">
					<label for="username"></label> 
					<input type="text" name="username" id="username" placeholder="아이디 입력" required>
					<button type="button" id="checkUsername">중복 확인</button>
					<p id="usernamecheck"></p>
				</div>
				<div class="form-group">
					<label for="email"></label> 
					<input type="email" name="email" id="email" placeholder="이메일 입력" required>
					<button type="button" id="checkEmail">중복 확인</button>
					<p id="emailcheck"></p>
				</div>
				<div class="form-group">
					<label for="password"></label>
					<input type="password" name="password" id="password" placeholder="비밀번호 입력" required>
					<p id="passwordcheck"></p>
				</div>
				<input type="submit" value="회원가입">
			</form>
		</div>
	</div>
	
<script type="text/javascript">
$(document).ready(function() {
    // ID Validation & Duplication Check
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
});
</script>

</body>
</html>