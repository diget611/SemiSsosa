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
		<form action="login" method="post">
			<div class="form-floating mb-3">
				<input type="text" class="form-control" id="floatingInput" placeholder="ID" name="id">
				<label for="floatingInput">ID</label>
			</div>
			<div class="form-floating mb-3">
				<input type="password" class="form-control" id="floatingPassword" placeholder="Password" name="password">
				<label for="floatingPassword">Password</label>
			</div>
			<div class="row justify-content-center">
				<button type="submit" class="btn btn-secondary col-2">로그인</button>
				<button type="button" class="btn btn-secondary ms-1 col-2 enroll">회원가입</button>
			</div>
		</form>
		
		<script>
			$('.btn.enroll').on("click", clickEnroll);
			
			function clickEnroll() {
				location.href = "<%=request.getContextPath()%>/enroll";
			}
		</script>
	</div>
</body>
</html>