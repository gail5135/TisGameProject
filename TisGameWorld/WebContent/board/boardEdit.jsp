<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/top.jsp" />

<script type="text/javascript">
	$(function() {
		$('#btnWrite').click(function() {
			if (!$('#subject').val()) {
				alert('글제목을 입력하세요');
				$('#subject').focus();
				return;
			}
			if (!$('#name').val()) {
				alert('글쓴이를 입력하세요');
				$('#name').focus();
				return;
			}

			if (!$('#bpwd').val()) {
				alert('비밀번호를 입력하세요');
				$('#bpwd').focus();
				return;
			}

			$('#bf').submit();
		});
	});
</script>

<%
	String ctx = request.getContextPath();
%>
<div class="container">
	<div class="row">
		<div align="center" id="bbs" class="col-md-8 col-md-offset-2">
			<!-- <h1>MVC Board</h1> -->
			<p>
			<a><img src="./images/mvcboard.png"></a>
			<p>
			<p>
				<a href="<%=ctx%>/board.do#bbs">글쓰기</a>| <a
					href="<%=ctx%>/boardList.do#bbs">글목록</a>
			<p>
				<!--파일 업로드시
	method: POST
	enctype: multipart/form-data  -->
			<form name="bf" id="bf" role="form" action="boardEditEnd.do" method="POST"
				enctype="multipart/form-data">
				<input type="hidden" name="mode" value="edit">
				<!-- 원본글쓰기mode는 write, 답변글쓰기 mode는 rewrite로 감  -->
				<table class="table table-bordered">
					<tr>
						<td style="width: 20%"><b>글번호</b></td>
						<td style="width: 80%"><input type="text" name="idx"
							id="idx" value="<c:out value="${board.idx}"/>" readonly class="form-control"></td>
					</tr>
					<tr>
						<td style="width: 20%"><b>제목</b></td>
						<td style="width: 80%"><input type="text" name="subject"
							id="subject" value="<c:out value="${board.subject}"/>" class="form-control"></td>
					</tr>
					<tr>
						<td style="width: 20%"><b>글쓴이</b></td>
						<td style="width: 80%"><input type="text" name="name"
							id="name" value="<c:out value="${board.name}"/>" class="form-control"></td>
					</tr>
					<tr>
						<td style="width: 20%"><b>글내용</b></td>
						<td style="width: 80%"><textarea name="content" id="content"
								rows="10" cols="50" class="form-control"><c:out value="${board.content}"/></textarea></td>
					</tr>
					<tr>
						<td style="width: 20%"><b>비밀번호</b></td>
						<td style="width: 80%">
							<div class="col-md-5">
								<input type="password" name="pwd" id="bpwd" class="form-control">
							</div>
						</td>
					</tr>
					<tr>
						<td style="width: 20%"><b>첨부파일</b></td>
						<td style="width: 80%">
						<div>
							${board.filename} [${board.filesize} bytes]
							<input type="hidden" name="oldfile" value="${board.filename}">
							<!-- 기존에 업로드 했던 파일을 hidden으로 넘긴다. -->
						</div>
						
						<input type="file" name="filename"
							id="filename" class="form-control"></td>
					</tr>
					<tr>
						<td colspan="2">
							<button type="button" id="btnWrite" class="btn btn-success">글수정</button>
							<button type="reset" id="btnReset" class="btn btn-warning">다시쓰기</button>
						</td>
					</tr>

				</table>


			</form>

		</div>
	</div>
</div>
<c:import url="/foot.jsp" />











