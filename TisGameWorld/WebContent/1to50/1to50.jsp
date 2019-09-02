<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="./css/style.css">
    <script src="./js/jquery-3.4.1.min.js"></script>
    <script src="./js/game.js"></script>
<jsp:include page="/top.jsp"/>
	
<div class="container">
        <div class="header">
            <h1 class="title">1 to 50</h1>
            <div class="timer">0.000</div>
        </div>
        <div class="controller">
            <button class="btn btn-primary">Restart</button>
        </div>
        <div class="game" id="game">Game Start</div>
    </div>
<jsp:include page="/foot.jsp"/>