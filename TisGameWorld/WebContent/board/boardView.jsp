<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ page import="java.net.URLEncoder"%>

<c:import url="/top.jsp" />

<div align="center" class="section">
	<div class="row">
		<div class="col-md-8 col-md-offset-2">
			<hgroup>
				<h1>글 내용 보기</h1>
			</hgroup>
			<br>
			<!-- ${board} -->
			<c:if test="${board eq null}">
				<!-- eq(== 과 동일한 연산다, ne(not equal !=와 동일) -->
				<h3 style="color: red">존재하지 않는 글입니다.</h3>
			</c:if>
			<c:if test="${board ne null}">

				<table class="table table-bordered">
					<tr>
						<td width="20%">글번호</td>
						<td width="30%"><c:out value="${board.idx}" /></td>
						<td width="20%">작성일</td>
						<td width="30%"><c:out value="${board.wdate}" /></td>
					</tr>
					<tr>
						<td width="20%">글쓴이</td>
						<td width="30%"><c:out value="${board.name}" /></td>
						<td width="20%">조회수</td>
						<td width="30%"><c:out value="${board.readnum}" /></td>
					</tr>

					<tr>
						<td width="20%">제목</td>
						<td colspan="3" align="left"><c:out value="${board.subject}" />
						</td>
					</tr>
					<tr height="60">
						<td width="20%">글내용</td>
						<td colspan="3" align="left"><c:out value="${board.content}" />
						</td>
					</tr>
					<tr>
						<td width="20%">첨부파일</td>
						<td colspan="3">
							<!-- 첨부 파일이 있다면 --> <c:if test="${board.filename ne null }">
								<a
									href="<%=request.getContextPath()%>/FileDown?filename=${board.filename}">
									<!-- FileDownloadServlet과 매핑된 FileDown을 걸어준다. --> <c:out
										value="${board.filename}" />
								</a> [ <c:out value="${board.filesize}" /> bytes]  
				<c:if
									test="${fn:endsWith(board.filename, '.jpg') or fn:endsWith(board.filename,'.png')}">
									<!-- -fn:endsWith:board.filename이 .jpg or .png으로 끝난다면 -->
									<img width="80px" class="img img-thumbnail"
										src="board/Upload/${board.filename}">
								</c:if>
							</c:if>


						</td>
					</tr>



					<tr>
						<td colspan="4" align=center><a href="boardForm.do">글쓰기</a>|
							<a href="boardList.do">목록</a>| <a href="#"
							onclick="go('${board.idx}',1)">편집</a>| <a href="#"
							onclick="go('${board.idx}',2)">삭제</a>| <a
							href="javascript:goRe()">답변</a></td>
					</tr>
				</table>
		</div>
	</div>
	</c:if>
	<!-- 댓글 달기 폼 시작-------------------------------------------- -->
	<div class="row">
		<div class="col-md-8 col-md-offset-2">
			<jsp:include page="replyWrite.jsp" />
		</div>
	</div>
	<!-- ------------------------------------------------------------- -->


	<!-- 댓글 목록 가져오기-------------------------------------------- -->
	<div class="row">
		<div class="col-md-8 col-md-offset-2">
			<jsp:include page="replyList.jsp" />
		</div>
	</div>
	<!-- -------------------------------------------------------------- -->

	<!--편집 또는 삭제 관련 form 시작----------------  -->
	<form name="vf">
		<input type="hidden" name="idx"> <input type="hidden"
			name="mode">
	</form>
	<!-- -------------------------------------------- -->
	<!-- 답변 달기 form 시작------------------------------- -->
	<form name="reF" action="rewrite" method="POST">
		<!-- hidden으로 부모글(원글)의 글번호(idx)와 제목 subject를 넘기자. -->
		<input type="hidden" name="idx" value="<c:out value="a"/>"> <input
			type="hidden" name="subject" value="<c:out value="a"/>">
	</form>

</div>
<script type="text/javascript">
	var goRe = function() {
		reF.submit();
	}

	var go = function(num, md) {
		//alert(num+"/"+md);
		vf.idx.value = num;
		vf.mode.value = md;//mode가 1이면 편집, 2면 삭제
		vf.method = "POST";
		//삭제시 한번 확인하기
		if (md == 2) {
			var yn = confirm(num + "번 글을 정말 삭제할까요");
			if (!yn) {
				return;
			}
		}
		vf.action = "boardPasswd.do";//비밀번호 확인 페이지로 이동
		vf.submit();
	}
</script>

<c:import url="/foot.jsp" />

