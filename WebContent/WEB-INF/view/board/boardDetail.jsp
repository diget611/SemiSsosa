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
	<button type="button" class="btn update">수정</button>
	<button type="button" class="btn delete">삭제</button>
	<hr>
	<c:forEach items="${replyList }" var="reply">
		<c:choose>
			<c:when test="${reply.floor eq 0 }">
				<span>${reply.content }</span>
				<span>${reply.writer }</span>
				<button type="button" class="btn replyToRe">reply</button>
				<button type="button" class="btn replyDelete">삭제</button>
				<form action="reply" method="post">
					<input type="hidden" name="idx" value="${reply.idx }">
					<input type="hidden" name="writer" value="${reply.writer }">
					<input type="hidden" name="havr" value="${reply.havr }">
					<input type="hidden" name="postNumber" value="${reply.postNumber }">
					<input type="hidden" name="orders" value="${reply.orders }">
					<input type="hidden" name="floor" value="${reply.floor}">
					<input type="hidden" name="groupNum" value="${reply.groupNum }">
				</form>
				<hr>
			</c:when>
			<c:otherwise>
				<c:forEach var="i" begin="1" end="${reply.floor}" step="1">
					<span>&nbsp;&nbsp;</span>
				</c:forEach>
				<span>${reply.content }</span>
				<span>${reply.writer }</span>
				<button type="button" class="btn replyToRe">reply</button>
				<button type="button" class="btn replyDelete">삭제</button>
				<form action="reply" method="post">
					<input type="hidden" name="idx" value="${reply.idx }">
					<input type="hidden" name="writer" value="${reply.writer }">
					<input type="hidden" name="havr" value="${reply.havr }">
					<input type="hidden" name="postNumber" value="${reply.postNumber }">
					<input type="hidden" name="orders" value="${reply.orders }">
					<input type="hidden" name="floor" value="${reply.floor}">
					<input type="hidden" name="groupNum" value="${reply.groupNum }">
				</form>
				<hr>
			</c:otherwise>
		</c:choose>
		
	</c:forEach>
	<form action="reply" method="post">
		<input type="hidden" name="postNumber" value="${boardDetail.idx }">
		<input type="text" name="reply">
		<button class="btn reply">reply</button>
	</form>
	
	<script>
		$('.btn.replyToRe').on("click", clickReplyToRe);
		$('.btn.replyDelete').on("click", clickReplyDelete);
		$('.btn.update').on("click", clickUpdate);
		$('.btn.delete').on("click", clickDelete);
		
		function clickReplyToRe() {
			if($(this).next().children('[name=groupNum]').next('[name=reply]').html() == null) {
				console.log("a");
				$(this).next().children('[name=groupNum]').after('<input type="text" name="reply"><button type="submit" class="btn reply">reply</button>');
			} else {
				console.log("b");
				$(this).next().children('[name=groupNum]').next().html('');
			}
		}
		
		function clickReplyDelete() {
			var idx = $(this).next().children('[name=idx]').val();
			var writer = $(this).next().children('[name=writer]').val();
			var havr = $(this).next().children('[name=havr]').val();
			location.href = "<%=request.getContextPath()%>/deleteReply?board=${boardDetail.idx}&idx=" + idx + "&writer=" + writer + "&havr=" + havr;
		}
		
		function clickUpdate() {
			location.href = "<%=request.getContextPath()%>/updateBoard?idx=${boardDetail.idx}&writer=${boardDetail.writer}&category=${boardDetail.category}";
		}
		
		function clickDelete() {
			location.href = "<%=request.getContextPath()%>/deleteBoard?idx=${boardDetail.idx}&writer=${boardDetail.writer}";
		}
	</script>
</body>
</html>