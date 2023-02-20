<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<div>
	<button type="button" class="btn title">title</button>
</div>
<div>
	<button type="button" class="btn board1">board1</button>
</div>
<div>
	<button type="button" class="btn board2">board2</button>
</div>
<div>
	<button type="button" class="btn board3">board3</button>
</div>
<div>
	<c:choose>
		<c:when test="${empty login }">
			<button type="button" class="btn login">로그인</button>
		</c:when>
		<c:otherwise>
			<button type="button" class="btn logout">로그아웃</button>
		</c:otherwise>
	</c:choose>
</div>
<hr>


<script>
	$(".btn.login").on("click", clickLogin);
	$(".btn.logout").on("click", clickLogout);
	$(".btn.title").on("click", toMain);
	$(".btn.board1").on("click", toBoard1);
	$(".btn.board2").on("click", toBoard2);
	$(".btn.board3").on("click", toBoard3);
	
	function clickLogin() {
		console.log("로그인 버튼 클릭");
		location.href="<%=request.getContextPath()%>/login";
	}
	function clickLogout() {
		console.log("로그아웃 버튼 클릭");
		location.href="<%=request.getContextPath()%>/logout";
	}
	function toMain() {
		location.href="<%=request.getContextPath()%>/"
	}
	function toBoard1() {
		location.href="<%=request.getContextPath()%>/board?idx=1"
	}
	function toBoard2() {
		location.href="<%=request.getContextPath()%>/board?idx=2"
	}
	function toBoard3() {
		location.href="<%=request.getContextPath()%>/board?idx=3"
	}
</script>
