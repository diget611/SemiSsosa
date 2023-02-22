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
		<div class="row justify-content-between mb-1">
			<div class="col-4">
				<span style="font-size: 20px; font-weight: bold;">게시판${board1 }의 최근 게시물</span>
			</div>
		</div>
		<table class="table table-striped table-hover table-lg mb-5">
			<thead>
				<tr>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>조회수</th>		
				</tr>
			<thead>
			<tbody>
				<c:forEach items="${board1List }" var="board">
					<tr>
						<td><a class="text-dark" style="text-decoration: none;" href='<%=request.getContextPath()%>/board/detail?idx=${board.idx }'>${board.postName }</a></td>
						<td style="text-align: center;">${board.writer }</td>
						<td style="text-align: center;">${board.createDate }</td>
						<td style="text-align: center;">${board.views }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="row justify-content-between mb-1">
			<div class="col-4">
				<span style="font-size: 20px; font-weight: bold;">게시판${board2 }의 최근 게시물</span>
			</div>
		</div>
		<table class="table table-striped table-hover table-lg mb-5">
			<thead>
				<tr>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>조회수</th>		
				</tr>
			<thead>
			<tbody>
				<c:forEach items="${board2List }" var="board">
					<tr>
						<td><a class="text-dark" style="text-decoration: none;" href='<%=request.getContextPath()%>/board/detail?idx=${board.idx }'>${board.postName }</a></td>
						<td style="text-align: center;">${board.writer }</td>
						<td style="text-align: center;">${board.createDate }</td>
						<td style="text-align: center;">${board.views }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="row justify-content-between mb-1">
			<div class="col-4">
				<span style="font-size: 20px; font-weight: bold;">게시판${board3 }의 최근 게시물</span>
			</div>
		</div>
		<table class="table table-striped table-hover table-lg mb-5">
			<thead>
				<tr>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>조회수</th>		
				</tr>
			<thead>
			<tbody>
				<c:forEach items="${board3List }" var="board">
					<tr>
						<td><a class="text-dark" style="text-decoration: none;" href='<%=request.getContextPath()%>/board/detail?idx=${board.idx }'>${board.postName }</a></td>
						<td style="text-align: center;">${board.writer }</td>
						<td style="text-align: center;">${board.createDate }</td>
						<td style="text-align: center;">${board.views }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>