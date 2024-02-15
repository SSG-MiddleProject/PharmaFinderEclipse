<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 업데이트</title>
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
<h2>새로운 비밀번호 설정</h2>

<form action="updatePasswordProcess.do" method="post">
    <div>
        <label for="username">아이디:</label>
        <input type="text" id="username" name="username" required>
    </div>
    <div>
        <label for="temporaryPassword">임시 비밀번호:</label>
        <input type="password" id="temporaryPassword" name="temporaryPassword" required>
    </div>
    <div>
        <label for="newPassword">새로운 비밀번호:</label>
        <input type="password" id="newPassword" name="newPassword" required>
    </div>
    <div>
        <label for="confirmPassword">새로운 비밀번호 확인:</label>
        <input type="password" id="confirmPassword" name="confirmPassword" required>
        <span id="passwordMatch"></span>
    </div>
    <button type="submit">비밀번호 변경</button>
</form>

</body>
</html>