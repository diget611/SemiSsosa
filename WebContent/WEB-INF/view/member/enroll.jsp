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
		<form action="enroll" method="post">
			<div class="form-floating mb-3">
				<input type="text" class="form-control" id="floatingInput" placeholder="ID" name="id">
				<label for="floatingInput">아이디</label>
			</div>
			<div class="form-floating mb-3">
				<input type="password" class="form-control" id="floatingPassword" placeholder="Password" name="password">
				<label for="floatingPassword">패스워드</label>
			</div>
			<div class="form-floating mb-3">
				<input type="password" class="form-control" id="floatingPasswordCk" placeholder="Password Check" name="passwordCk">
				<label for="floatingPassword">패스워드 확인</label>
			</div>
			<div class="form-floating mb-3">
				<input type="text" class="form-control" id="floatingName" placeholder="이름" name="name">
				<label for="floatingInput">이름</label>
			</div>
			<div class="form-floating mb-3">
				<input type="text" class="form-control" id="floatingEmail" placeholder="이메일 주소" name="email">
				<label for="floatingInput">이메일 주소</label>
			</div>
			<div class="row justify-content-center">
				<button type="submit" class="btn btn-secondary col-2">회원가입</button>
			</div>
		</form>
	</div>
	
	<script>
		$('[name=id]').on('blur', checkId);
		$('[name=password]').on('blur', checkPw);
		$('[name=passwordCk]').on('blur', checkPw);
		
		function checkId() {
			$.ajax({
				url: "<%=request.getContextPath()%>/checkId",
				type: "post",
				data: {id: $('[name=id]').val()},
				success: function(result) {
					if(result == 1) {
						$('[name=id]').next().html("중복된 아이디가 존재합니다.");
						$('[name=id]').next().css("color", "red");						
					} else {
						$('[name=id]').next().html("가입 가능한 아이디입니다.");
						$('[name=id]').next().css("color", "blue");
					}
				},
				
				error: function(request, status, error) {
					alert(request.status);
				}				
				
			});
		}
		
		function checkPw() {
			var password = $('[name=password]').val();
			var passwordCk = $('[name=passwordCk]').val();
			if(password != passwordCk) {
				$('[name=passwordCk]').next().html("비밀번호가 일치하지 않습니다.");
				$('[name=passwordCk]').next().css("color", "red");						
			} else {
				$('[name=passwordCk]').next().html("비밀번호가 일치합니다.");
				$('[name=passwordCk]').next().css("color", "blue");
			}
		}
		
	</script>
</body>
</html>