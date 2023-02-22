<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/reset.css">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/main.css">
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
	<jsp:include page="/WEB-INF/view/header.jsp"/>
	<div class="container-center">
		<form action="updateinfo" method="post" onsubmit="return checkPassword();">
			<div class="mb-3">
				<label class="form-label">아이디</label>
				<input type="text" class="form-control" name="id" value="${info.id }" disabled>
			</div>
			<div class="mb-3">
				<label class="form-label">현재 패스워드</label>
				<input type="password" class="form-control change" name="pwNow">
			</div>
			<div class="mb-3">
				<label class="form-label">변경할 패스워드</label>
				<input type="password" class="form-control change" name="pwChange" disabled>
			</div>
			<div class="mb-3">
				<label class="form-label">패스워드 확인</label>
				<input type="password" class="form-control change" name="pwCheck" disabled>
			</div>
			<div class="mb-3">
				<label class="form-label">이름</label>
				<input type="text" class="form-control change" name="name" value="${info.name }" disabled>
			</div>
			<div class="mb-3">
				<label class="form-label">이름</label>
				<input type="text" class="form-control change" name="email" value="${info.email }" disabled>
			</div>
			<button type="button" class="btn btn-secondary me-1 changebtn">수정</button>
		</form>
	</div>
	
	<script>
		$('button').on("click", changeStatus);
		function changeStatus() {
			if($('[name=pwNow]').val() == "${info.password}") {
				$('.change').attr("disabled", false);
				$('[name=pwNow]').attr("disabled", true);
				$('.btn.changebtn').after('<button type="submit" class="btn btn-secondary">수정완료</button>');
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