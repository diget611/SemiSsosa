<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/reset.css">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/main.css">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<nav class="navbar bg-light">
	<div class="container-fluid">
		<a class="btn navbar-brand title">TITLE</a>
		<div>
			<c:choose>
				<c:when test="${empty login }">
					<button type="button" class="btn btn-secondary login">로그인</button>
				</c:when>
				<c:otherwise>
					<button type="button" class="btn btn-secondary mypage">마이페이지</button>
					<button type="button" class="btn btn-secondary logout">로그아웃</button>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</nav>

<ul class="nav mb-3">
  <li class="nav-item">
    <a class="nav-link text-dark" href="<%=request.getContextPath()%>/board?idx=1">board1</a>
  </li>
  <li class="nav-item">
    <a class="nav-link text-dark" href="<%=request.getContextPath()%>/board?idx=2">board2</a>
  </li>
  <li class="nav-item">
    <a class="nav-link text-dark" href="<%=request.getContextPath()%>/board?idx=3">board3</a>
  </li>
</ul>


<script>
	$(".btn.login").on("click", clickLogin);
	$(".btn.logout").on("click", clickLogout);
	$(".btn.title").on("click", toMain);
	$(".btn.board1").on("click", toBoard1);
	$(".btn.board2").on("click", toBoard2);
	$(".btn.board3").on("click", toBoard3);
	$('.btn.mypage').on("click", clickMypage);
	
	function clickMypage() {
		location.href="<%=request.getContextPath()%>/mypage";
	}
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
