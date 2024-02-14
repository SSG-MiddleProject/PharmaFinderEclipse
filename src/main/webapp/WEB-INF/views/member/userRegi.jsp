<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반 유저 회원가입페이지</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

<form action="userRegiAf.do" method="post">
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
</table>
<br>

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
	
	// 이메일 중복 체크
	$("#email").keyup(function() {
	    var inputEmail = $(this).val().trim();
	    
	    if(inputEmail !== "") {
	        $.ajax({
	            url: "emailcheck.do",
	            type: "post",
	            data: {"email": inputEmail},
	            success: function(msg) {
	                if(msg.trim() === "YES") {
	                    $("#emailcheck").css("color", "#0000ff");
	                    $("#emailcheck").text("사용할 수 있는 이메일입니다");
	                } else {
	                    $("#emailcheck").css("color", "#ff0000");
	                    $("#emailcheck").text("사용중인 이메일입니다");
	                    $("#emailcheck").val("");
	                }
	            },
	            error: function() {
	                alert("이메일 확인 중 오류가 발생했습니다");
	            }
	        });
	    } else {
	        $("#emailcheck").text(""); // 입력값이 없을 때 메시지를 비움
	    }
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

</body>
</html>
