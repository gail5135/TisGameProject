<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<<<<<<< HEAD

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
    <script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet"
        type="text/css">
    <link href="./css/style.css" rel="stylesheet" type="text/css">
    <link href="./css/mystyle.css" rel="stylesheet" type="text/css">
</head>

<body>
    <div class="container">
        <div class="navbar navbar-default navbar-static-top">
            <div class="logo" style="display: block; text-align: center; margin-top: 5px;">
                <a href="${pageContext.request.contextPath}/main.do">
                    <img style="width: 200px" src="./images/tisgameworldlogo.gif" />
                </a>
            </div>
            <div></div>
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-ex-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <div class="collapse navbar-collapse" id="navbar-ex-collapse">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="#">Home</a>
                    </li>
                    <li>
                        <a href="#">About</a>
                    </li>
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Board
                            <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Notice</a></li>
                            <li><a href="${pageContext.request.contextPath}/boardList.do">Free Board</a></li>
                            <li><a href="#">HC Jam</a></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Game
                            <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">1 to 50</a></li>
                            <li><a href="${pageContext.request.contextPath}/jellyfish.do">Flap Jellyfish</a></li>
                            <li><a href="#">HC Jam</a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a href="#"><img src="./images/user.png" style="width: 25px" alt="login" />Sign Up</a>
                    </li>
                    <li>
                        <a href="#"><img src="./images/login.png" style="width: 25px" alt="login" />Login</a>
                    </li>
                </ul>
            </div>
        </div>
=======
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
<link rel="stylesheet" href="https://static.pingendo.com/bootstrap/bootstrap-4.3.1.css">

</head>

<body style="" class="bg-dark">
<div>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container">
			<div>
				<a class="" href="${pageContext.request.contextPath}/main.do">
					<img style="width:300px" src="./images/tisgameworldlogo3.png">
					<!-- <i class="fa d-inline fa-gamepad fa-lg pull-right fa-fw"></i> -->
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
	</div>
	
<!-- 	  <div class="">
    <div class="container">
      <div class="row">
        <div class="col-md-12"><img class="img-fluid d-block mx-auto w-100" src="./images/tisgameworldlogo3.png"></div>
      </div>
    </div>
  </div> -->
>>>>>>> refs/remotes/origin/YINGXIONGLIANMENG
