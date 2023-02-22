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
<style>
	.ck.ck-editror{
		
	}
	.ck-editor__editable{
		min-height: 600px;
	}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/view/header.jsp"/>
	<div class="container-center">
		<form action="writePost" method="post">
			<div class="form-floating mb-3">
  				<input type="text" class="form-control" placeholder="제목">
				<label for="floatingInput">제목</label>
			</div>
			<input type="hidden" value="<%=request.getParameter("idx") %>" name="category">
			<textarea rows="100" cols="" name="content" id="editor"></textarea>
			<div class="row justify-content-center mt-2">
    			<div class="col-2">
					<button class="btn btn-secondary">글쓰기</button>
    			</div>
  			</div>
		</form>
	</div>
	
	<script>
      ClassicEditor.create( document.querySelector( '#editor' ) );
    </script>
</body>
</html>