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
	<h4>게시판임</h4>
	<table class="table">
		<thead>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
		<thead>
		<c:forEach items="${boardList }" var="board">
			<tr>
				<td>${board.idx} </td>
				<td><a href='<%=request.getContextPath()%>/board/detail?idx=${board.idx }'>${board.boardName }</a></td>
				<td>${board.boardWriter }</td>
				<td>${board.boardDate }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>