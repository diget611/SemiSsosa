<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<form action="updateinfo" method="post" onsubmit="return checkPassword();">
		ID : <input type="text" name="id" value="${info.id }" disabled>${info.id }<br>
		현재PW : <input type="password" name="pwNow"><br>
		변경할PW : <input type="password" name="pwChange" class="change" disabled><br>
		PW확인 : <input type="password" name="pwCheck" class="change" disabled><br>
		NAME : <input type="text" name="name" value="${info.name }" class="change" disabled><br>
		EMAIL : <input type="text" name="email" value="${info.email }" class="change" disabled><br>
		<button type="button">수정</button>
	</form>
	<script>
		$('button').on("click", changeStatus);
		function changeStatus() {
			if($('[name=pwNow]').val() === '${info.password}') {
				$('.change').attr("disabled", false);
				$('[name=pwNow]').attr("disabled", true);
				$('button').after('<button type="submit">수정완료</button>');
			} else {
				alert("비밀번호가 일치하지 않습니다. 비밀번호를 확인하세요.");
			}
		}
		
		function checkPassword() {
			var pw1 = $('[name=pwChange]').val();
			var pw2 = $('[name=pwCheck]').val();
			
			if(pw1 != pw2) {
				alert("변경할 비밀번호와 비밀번호 확인이 일치하지 않습니다.");
				$('[name=pwCheck]').val("");
				return false;
			}
		}
	</script>
</body>
</html>