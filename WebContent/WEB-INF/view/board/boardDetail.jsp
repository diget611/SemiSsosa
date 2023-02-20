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
	${boardDetail }
	<hr>
	<c:forEach items="${replyList }" var="reply">
		<span>${reply.content }</span>
		<span>${reply.writer }</span>
		<button type="button" class="btn replyToRe">reply</button>
		<form action="reply" method="post">
			<input type="hidden" name="postNumber" value="${reply.postNumber }">
			<input type="hidden" name="orders" value="${reply.orders }">
			<input type="hidden" name="floor" value="${reply.floor}">
			<input type="hidden" name="groupNum" value="${reply.groupNum }">
		</form>
		<hr> 
	</c:forEach>
	<form action="reply" method="post">
		<input type="hidden" name="postNumber" value="${boardDetail.idx }">
		<input type="text" name="reply">
		<button class="btn reply">reply</button>	
	</form>
	
	<script>
		$('.btn.reply').on("click", clickReply);
		$('.btn.replyToRe').on("click", clickReplyToRe);
		
		function clickReply() {
			location.href = "<%=request.getContextPath()%>/board/reply";
		}
		
		function clickReplyToRe() {
			$(this).next().append('<input type="text" name="reply"><button class="btn reply">replll</button>');
		}
	</script>
</body>
</html>