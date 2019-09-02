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
				<h1>NOTICE</h1>
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
						<td colspan="4" align=center><a href="noticeForm.do">글쓰기</a> |
							<a href="noticeList.do">목록</a> | <a href="#"
							onclick="go('${board.idx}',1)">편집</a> | <a href="#"
							onclick="go('${board.idx}',2)">삭제</a></td>
					</tr>
				</table>
		</div>
	</div>
	</c:if>
	

	<!--편집 또는 삭제 관련 form 시작----------------  -->
	<form name="vf">
		<input type="hidden" name="idx"> <input type="hidden"
			name="mode">
	</form>
	<!-- -------------------------------------------- -->

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
		vf.action = "noticePasswd.do";//비밀번호 확인 페이지로 이동
		vf.submit();
	}
</script>

<c:import url="/foot.jsp" />

