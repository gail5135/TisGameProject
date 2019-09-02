<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
                        <a href="${pageContext.request.contextPath}/main.do">Home</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/about.do">About</a>
                    </li>
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Board
                            <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="${pageContext.request.contextPath}/noticeList.do">Notice</a></li>
                            <li><a href="${pageContext.request.contextPath}/boardList.do">Free Board</a></li>
                            <li><a href="${pageContext.request.contextPath}/totalRanking.do">TOTAL RANKING</a></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Game
                            <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="${pageContext.request.contextPath}/1to50.do">1 to 50</a></li>
                            <li><a href="${pageContext.request.contextPath}/jellyfish.do">Flap Jellyfish</a></li>
                            <li><a href="${pageContext.request.contextPath}/hcjam.do">HC Jam</a></li>
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