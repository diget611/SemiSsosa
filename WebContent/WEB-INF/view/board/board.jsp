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
		<h4>게시판임</h4>
		<hr>
		<table class="table table-striped table-hover table-lg">
			<thead>
				<tr>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>조회수</th>		
				</tr>
			<thead>
			<tbody>
				<c:forEach items="${boardList }" var="board">
					<tr>
						<td><a class="text-dark" style="text-decoration: none;" href='<%=request.getContextPath()%>/board/detail?idx=${board.idx }'>${board.postName }</a></td>
						<td style="text-align: center;">${board.writer }</td>
						<td style="text-align: center;">${board.createDate }</td>
						<td style="text-align: center;">${board.views }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<nav>
			<ul class="pagination">
			<c:choose>
				<c:when test="${currPage - 1 le 0 }">
					<li class="page-item disabled"><a class="page-link">Previous</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/board?idx=${category}&page=${currPage - 1}">Previous</a></li>
				</c:otherwise>
			</c:choose>
			<c:forEach var="paging" begin="${start }" end="${end }" step="1">
				<li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/board?idx=${category}&page=${paging}">${paging}</a></li>
			</c:forEach>
	  		<c:choose>
				<c:when test="${currPage + 1 > page }">
					<li class="page-item disabled"><a class="page-link">Next</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/board?idx=${category}&page=${currPage + 1}">Next</a></li>
				</c:otherwise>
			</c:choose>
	  		</ul>
		</nav>
		<button type='button' name='writePost'>글쓰기</button>
	</div>
	
	<script>
		$('[name=writePost]').on('click', clickWrite);
		
		function clickWrite() {
			location.href = '<%=request.getContextPath()%>/writePost?idx=${category }';
		};
	</script>
</body>
</html>