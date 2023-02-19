<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
</script>
</body>
</html>