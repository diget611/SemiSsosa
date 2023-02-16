<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- <link rel="stylesheet" href="./resources/css/bootstrap.min.css">
<script src="./resources/js/jquery-3.6.3.min.js"></script>
<script src="./resources/js/bootstrap.min.js"></script> -->
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
</head>
<body>
	<jsp:include page="/WEB-INF/view/header.jsp"/>
	<div>
		<form action="login" method="post">
			ID : <input type="text" name="id">
			PW : <input type="password" name="password">
			<button type="submit">로그인</button>
		</form>
	</div>
</body>
</html>