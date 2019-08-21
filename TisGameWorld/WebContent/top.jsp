<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
<link rel="stylesheet" href="https://static.pingendo.com/bootstrap/bootstrap-4.3.1.css">
</head>

<body style="" class="bg-dark">
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container">
			<div>
				<a class="" href="${pageContext.request.contextPath}/main.do">
					<!-- <i class="fa d-inline fa-gamepad fa-lg pull-right fa-fw"></i> -->
					<img style="width:300px" src="./images/tisgameworldlogo3.png">
					<!-- test -->
				</a>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item text-info"><a class="nav-link" href="#">1번게임</a></li>
					<li class="nav-item"><a class="nav-link" href="#">2번 게임</a></li>
					<li class="nav-item"><a class="nav-link" href="#">3번 게임</a></li>
				</ul>
				<ul class="navbar-nav ml-auto">
					<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/boardList.do">자유게시판</a></li>
					<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/main.do">[닉네임]내정보</a></li>
					<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/login.do">로그인</a></li>
				</ul>
			</div>
		</div>
	</nav>
	
<!-- 	  <div class="">
    <div class="container">
      <div class="row">
        <div class="col-md-12"><img class="img-fluid d-block mx-auto w-100" src="./images/tisgameworldlogo3.png"></div>
      </div>
    </div>
  </div> -->