<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�α��� ������</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

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

/* Login container */
.login-container {
    max-width: 600px;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 10px;
    background-color: #f9f9f9;
}

/* PharmaFinder Login */
.login-title {
    font-weight: 800;
    font-size: 48px;
    text-align: center;
    margin-bottom: 20px;
}

/* Buttons */
.login-buttons {
    display: flex;
    justify-content: space-around;
    margin-bottom: 20px;
}


.login-buttons button:hover {
    background-color: #ddd;
}

/* Form fields */
.login-form input[type="text"],
.login-form input[type="password"] {
    width: 100%;
    padding: 10px;
    margin-bottom: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    box-sizing: border-box;
}

/* Submit button */
.login-form input[type="submit"] {
    width: 100%;
    padding: 10px;
    border: none;
    border-radius: 5px;
    background-color: #007bff;
    color: #fff;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

.login-form input[type="submit"]:hover {
    background-color: #0056b3;
}

/* Links */
.login-links {
    text-align: center;
}

.login-links a {
    color: #007bff;
    text-decoration: none;
    transition: color 0.3s ease;
}

.login-links a:hover {
    color: #0056b3;
}
</style>

</head>
<body>

<div class="center">

<div class="login-container">

	<h2 class="login-title">PharmaFinder</h2>
	
	<p align="center">Login</p>

	
	<form class="login-form" action="loginAf.do" method="post">
		<input type="text" id="username" name="username" size=20 placeholder="example@test.com">
		<input type="password" name="password" placeholder="Password">
		<input type="checkbox" id="saveId" name="saveId">
		<label for="saveId">���̵� ����</label><br/><br/>
		<input type="submit" value="login">
	</form>
	<br/>
	<div class="login-links">
		<a href="#">���̵�/��й�ȣ ã��</a>
		<form action="">
		<div>
			<p class="mb-0">
				ȸ���� �ƴϽŰ���?<a href="regiSelect.do" class="text-white-50 fw-bold">ȸ������</a><br/>
			</p>
		</div>
		</form>
	</div>

</div>

</div>

<script type="text/javascript">
$(document).ready(function() {
    // ������ �ε� ��, ����� ���̵� �ִ��� Ȯ���ϰ� ���̵� �Է� �ʵ忡 ����
    var savedUsername = localStorage.getItem("savedUsername");
    if (savedUsername) {
        $("#username").val(savedUsername);
        $("#saveId").prop("checked", true);
    }
    
    // ���̵� ���� üũ�ڽ� �̺�Ʈ ������
    $("#saveId").change(function() {
        if ($(this).is(":checked")) {
            // üũ�Ǿ��� ��, ���̵� ���� ���丮���� ����
            localStorage.setItem("savedUsername", $("#username").val());
        } else {
            // üũ �����Ǿ��� ��, ���� ���丮������ ���̵� ����
            localStorage.removeItem("savedUsername");
        }
    });

    // �������� �޾ƿ� roll ���� ���� ������ ���𷺼� ����
    var roll = "<%= request.getAttribute("roll") %>";
    if (roll === "1") {
        window.location.href = "regi.do"; // �౹�Ϲ� �������� ���𷺼�
    } else if (roll === "2") {
        window.location.href = "userregi.do"; // �Ϲ� ���� �������� ���𷺼�
    }
});
</script>

</body>
</html>
