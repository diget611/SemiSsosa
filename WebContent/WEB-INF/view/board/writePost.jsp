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
	글쓰기 페이지

	<form action="writePost" method="post">
		제목 <input type="text" name="title"><br>
		내용
		<textarea rows="" cols="" name="content"></textarea>
		<button>글쓰기</button>
	</form>
</body>
</html>