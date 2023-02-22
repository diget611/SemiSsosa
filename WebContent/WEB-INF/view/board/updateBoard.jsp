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
<script src="https://cdn.ckeditor.com/ckeditor5/36.0.1/classic/ckeditor.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/view/header.jsp"/>
	<div class="container-center">
		<form action="updateBoard" method="post">
			<input type="hidden" value="<%=request.getParameter("idx") %>" name="idx">
			<input type="hidden" value="<%=request.getParameter("category") %>" name="category">
			<input type="Hidden" value="<%=request.getParameter("writer") %>" name="writer">
			제목 <input type="text" name="title"><br>
			내용
			<textarea rows="" cols="" name="content" id="editor"></textarea>
			<button type="submit">수정하기</button>
		</form>
	</div>
	
	<script>
      	ClassicEditor.create( document.querySelector( '#editor' ) );
    </script>
</body>
</html>