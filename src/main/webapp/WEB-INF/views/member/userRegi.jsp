<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�Ϲ� ���� ȸ������������</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

<form action="regiAf.do" method="post">
<table>
<tr>
    <th>���̵�</th>
    <td>
        <input type="text" name="username" id="username">
		<p id="idcheck" style="font-size: 10px"></p>
		<input type="button" class="btn btn-primary" id="id_chk_btn" value="idȮ��">	
    </td>
</tr>
<tr>
    <th>�̸���</th>
    <td>
        <input type="email" name="email" id="email" required>
    </td>
</tr>
<tr>
    <th>��й�ȣ</th>
    <td>
        <input type="password" name="password" id="password" required>
    </td>
</tr>
</table>
<br>

<input type="submit" value="����">
</form>


<!-- id ��ȿ�� �˻� -->
<script type="text/javascript">
$(document).ready(function () {
	
	$("#id_chk_btn").click(function () {
		var inputId = $("#username").val().trim();
		
		if($("#username").val().trim() === "") {
	        alert("���̵� �Է����ּ���.");
	        return;
		}
		
		$.ajax({
			url:"idcheck.do",	//���� �ʿ��� �κ�
			type:"post",
			data:{ "username": inputId }, //���� �ʿ��� �κ�
			success:function(msg){
				// alert('success');
				if(msg.trim() === "YES"){
					$("#idcheck").css("color", "#0000ff");
					$("#idcheck").text("����� �� �ִ� ���̵��Դϴ�");
				}else{
					$("#idcheck").css("color", "#ff0000");
					$("#idcheck").text("������� ���̵��Դϴ�");
					$("#username").val("");
				}
			},
			error:function(){
				alert('���̵� Ȯ�� �� ������ �߻��߽��ϴ�');
			}
		});				
	});		
	
	$("#regibtn").click(function () {
		
		// ��ĭ �˻�(id, password)
		if($("#username").val().trim() === "") {
		    alert("���̵� �Է����ּ���.");
		    return;
		}
		
		if($("#password").val().trim() === "") {
		    alert("��й�ȣ�� �Է����ּ���.");
		    return;
		}
				
		$("#frm").submit();
	});
	
});
</script>

</body>
</html>
