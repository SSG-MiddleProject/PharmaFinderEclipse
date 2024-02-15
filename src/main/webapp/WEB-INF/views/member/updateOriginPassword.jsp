<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 업데이트</title>
<style type="text/css">
    /* General styling */
    body {
        margin: 0;
        padding: 0;
        font-family: 'Inter', sans-serif;
    }

    .center {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }

    /* Container */
    .container {
        max-width: 600px;
        padding: 20px;
        border: 1px solid #ccc;
        border-radius: 10px;
        background-color: #f9f9f9;
    }

    /* Title */
    .title {
        font-weight: 800;
        font-size: 48px;
        text-align: center;
        margin-bottom: 20px;
    }

    /* Form fields */
    .form input[type="text"],
    .form input[type="password"] {
        width: calc(100% - 1px);
        padding: 10px;
        margin-bottom: 10px;
        border: 1px solid #ccc;
        border-radius: 5px;
        box-sizing: border-box;
    }

    /* Submit button */
    .form input[type="submit"] {
        width: 100%;
        padding: 10px;
        margin-bottom: 10px;
        border: 1px solid #ccc;
        border-radius: 5px;
        box-sizing: border-box;
        border: none;
        border-radius: 5px;
        background-color: #007bff;
        color: #fff;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }

    .form input[type="submit"]:hover {
        background-color: #0056b3;
    }
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(document).ready(function() {
    // 비밀번호 입력 필드와 비밀번호 확인 필드에 keyup 이벤트 핸들러 추가
    $('#newPassword, #confirmPassword').keyup(function() {
        var newPassword = $('#newPassword').val();
        var confirmPassword = $('#confirmPassword').val();
        
        // 비밀번호와 비밀번호 확인이 일치하는 경우
        if(newPassword === confirmPassword) {
            $('#passwordMatch').css('color', 'blue').text('비밀번호가 일치합니다.');
        } else {
            $('#passwordMatch').css('color', 'red').text('비밀번호가 일치하지 않습니다.');
        }
    });
});
</script>
</head>
<body>

<div class="center">
<div class="container">
<h1 class="title">새로운 비밀번호 설정</h1>

<form class="form" action="updatePasswordProcess.do" method="post">
	<div>
        <label for="username"></label>
        <input type="text" id="username" name="username" placeholder="아이디를 입력하세요" required>
	</div>
    <div>
        <label for="temporaryPassword"></label>
        <input type="password" id="temporaryPassword" name="temporaryPassword" placeholder="임시 비밀번호를 입력하세요" required>
	</div>
    <div>
		<label for="newPassword"></label>
        <input type="password" id="newPassword" name="newPassword" placeholder="변경할 비밀번호를 입력하세요" required>
	</div>
    <div>
	    <label for="confirmPassword"></label>
        <input type="password" id="confirmPassword" name="confirmPassword" placeholder="변경할 비밀번호를 확인하세요" required>
        <span id="passwordMatch"></span>
	</div>
	<input type="submit" value="비밀번호 변경">
</form>

</div>
</div>
</body>
</html>