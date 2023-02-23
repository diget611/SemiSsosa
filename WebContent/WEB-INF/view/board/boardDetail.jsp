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
		<div class="input-group mb-3">
 			<span class="input-group-text">제목</span>
  			<input type="text" class="form-control" value="${boardDetail.postName}" readonly style="background-color: white;">
		</div>
		<div class="row mb-3">	
			<div class="input-group col-auto">
    			<span class="input-group-text">작성자</span>
  				<input type="text" class="form-control" value="${boardDetail.writer}" readonly style="background-color: white;">
  			</div>
  			<div class="input-group col-auto">
  				<span class="input-group-text">조회수</span>
  				<input type="text" class="form-control" value="${boardDetail.views}" readonly style="background-color: white;">
  			</div>
    		<div class="input-group col-auto">
    			<span class="input-group-text">작성일</span>
  				<input type="text" class="form-control" value="${boardDetail.createDate}" readonly style="background-color: white;">
    		</div>
		</div>
		<div class="mb-3">
  			<textarea class="form-control" rows="10" style="resize: none; background-color: white;" readonly>${boardDetail.content }</textarea>
		</div>
		<div class="row justify-content-center mb-3">
			<button type="button" class="like btn update btn-primary col-2">추천</button>
			<button class="btn col-1 btn-light ms-1" disabled>${like }</button>
			<button type="button" class="dislike btn delete btn-danger col-2 ms-1">비추천</button>
		</div>
		<div class="row justify-content-end mb-3">
			<button type="button" class="btn update btn-secondary col-1">수정</button>
			<button type="button" class="btn delete btn-secondary col-1 ms-1">삭제</button>
		</div>
		<c:forEach items="${replyList }" var="reply">
			<c:choose>
				<c:when test="${reply.floor eq 0 }">
					<div class="row align-items-center m-3">
						<span class="col-4">${reply.content }</span>
						<span class="col-2">${reply.writer }</span>
						<span class="col-3">${reply.createDate }</span>
						<button type="button" class="btn replyToRe btn-secondary col-1 btn-sm">댓글</button>
						<button type="button" class="btn replyDelete btn-secondary ms-1 col-1 btn-sm">삭제</button>
					</div>
					<form action="reply" method="post" class="row g-3">
						<input type="hidden" name="idx" value="${reply.idx }">
						<input type="hidden" name="writer" value="${reply.writer }">
						<input type="hidden" name="havr" value="${reply.havr }">
						<input type="hidden" name="postNumber" value="${reply.postNumber }">
						<input type="hidden" name="orders" value="${reply.orders }">
						<input type="hidden" name="floor" value="${reply.floor}">
						<input type="hidden" name="groupNum" value="${reply.groupNum }">
					</form>
				</c:when>
				<c:otherwise>
					<div class="row align-items-center m-3">
						<c:forEach var="i" begin="1" end="${reply.floor}" step="1">
							<span>&nbsp;&nbsp;</span>
						</c:forEach>
						<span class="col-4">${reply.content }</span>
						<span class="col-2">${reply.writer }</span>
						<span class="col-3">${reply.createDate }</span>
						<button type="button" class="btn replyToRe btn-secondary col-1 btn-sm">댓글</button>
						<button type="button" class="btn replyDelete btn-secondary ms-1 col-1 btn-sm">삭제</button>
					</div>
					<form action="reply" method="post" class="row g-3">
						<input type="hidden" name="idx" value="${reply.idx }">
						<input type="hidden" name="writer" value="${reply.writer }">
						<input type="hidden" name="havr" value="${reply.havr }">
						<input type="hidden" name="postNumber" value="${reply.postNumber }">
						<input type="hidden" name="orders" value="${reply.orders }">
						<input type="hidden" name="floor" value="${reply.floor}">
						<input type="hidden" name="groupNum" value="${reply.groupNum }">
					</form>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<form action="reply" method="post" class="row g-3 mt-2">
			<input type="hidden" name="postNumber" value="${boardDetail.idx }">
			<div class="col-9">
				<input type="text" name="reply" class="form-control col-auto">
			</div>
			<div class="col-3">
				<button class="btn reply btn-secondary col-auto btn-sm">댓글</button>
			</div>
		</form>
	</div>
	
	<script>
		$('.btn.replyToRe').on("click", clickReplyToRe);
		$('.btn.replyDelete').on("click", clickReplyDelete);
		$('.btn.update').on("click", clickUpdate);
		$('.btn.delete').on("click", clickDelete);
		$('.btn.like').on("click", clickLike);
		$('.btn.dislike').on("click", clickDislike);
		
		function clickReplyToRe() {
			if($(this).parent().next().children('[name=groupNum]').next().html() == null) {
				console.log("a");
				$(this).parent().next().children('[name=groupNum]').after('<div class="col-9"><input type="text" name="reply" class="form-control col-auto"></div><div class="col-3"><button class="btn reply btn-secondary col-auto btn-sm">댓글</button></div>');
			} else {
				console.log("b");
				$(this).parent().next().children('[name=groupNum]').after().html('');
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
		
		function clickLike() {
			location.href = "<%=request.getContextPath()%>/like?like=1&idx=${boardDetail.idx}";
		}
		
		function clickDislike() {
			location.href = "<%=request.getContextPath()%>/like?like=-1&idx=${boardDetail.idx}";
		}
	</script>
</body>
</html>