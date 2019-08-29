<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	//컨텍스트명 알아내기
	String myctx = request.getContextPath();
	System.out.println("myctx: " + myctx); //"/MyWeb"
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet"
	href="https://static.pingendo.com/bootstrap/bootstrap-4.3.1.css">
</head>

<body class="bg-dark">
	<div class="py-5 text-center bg-dark text-white">
		<div class="container">
			<div class="row bg-dark">
				<div class="p-5 col-lg-6 col-10 mx-auto border-0">
					<img class="img-fluid d-block mb-3"
						src="./images/tisgameworldlogo2.png" id="logo">
					<form>
						<div class="form-group">
							<input type="text"
								class="form-control form-control-lg text-center mb-3"
								id="Recommendation_Code" name="Recommendation_Code"
								placeholder="입장코드를 입력하세요.">
						</div>
						<div class="form-group">
							<small class="form-text text-muted text-right"></small>
						</div>
						<button type="submit" class="btn text-dark btn-warning" id="Enter">입장</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
		integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous" style=""></script>
</body>

</html>