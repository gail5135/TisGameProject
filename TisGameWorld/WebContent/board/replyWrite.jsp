<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- "user/***.do"패턴은 로그인해야 이용 가능하도록 처리 예정 -->
<form name="rf" id="rf" action="user/replyInsert.do">
	<input type="hidden" name="userid" value="${loginUser.userid}">
	<input type="hidden" name="idx_fk" value="${board.idx}">
	<table class="table">
		<tr>
			<th colspan="3" class="text-center">
			<h4 class="text-primary">댓글 달기</h4>
			</th>
		</tr>
		<tr>
			<td>작성자			
			</td>
			<td>
			<textarea name="content" rows="3" cols="70" class="form-control"></textarea><!-- -붙여놔야함 엔터만 쳐도 데이터가 있는것으로 판단 -->
			</td>
			<td><button type="button" onclick="reply()"
			 class="btn btn-success">댓글쓰기</button></td>
		</tr>
	</table>    
</form>
<script>
	var reply=function(){
		if(!rf.userid.value){
			alert('로그인해야 이용 가능합니다.');
			return;
		}
		if(!rf.content.value){
			alert('댓글 내용을 입력하세요');
			rf.content.focus();
			return;
		}
		rf.submit();
	}
</script>





