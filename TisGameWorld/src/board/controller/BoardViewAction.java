package board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.BoardDAOMyBatis;
import board.model.BoardVO;
import board.model.ReplyVO;
import common.controller.AbstractAction;

public class BoardViewAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("BoardViewAction execute() 호출됨....");
		//1. 글번호 받기
		String idxStr=req.getParameter("idx");
		//2. 유효성 체크
		if(idxStr==null||idxStr.trim().isEmpty()) {
			this.setViewPage("boardList.do");
			this.setRedirect(true);
			return;
		}
		//3. DAO의 메소드 호출
		BoardDAOMyBatis dao=new BoardDAOMyBatis(); 
		//	-조회수 증가
		boolean bool=dao.updateReadnum(idxStr);
		//	-상세 글 내용 가져오기
		BoardVO board = dao.getBoard(idxStr);
		
		//3-2. 댓글 수 가져오기
		int replayCount = dao.replyCount(idxStr);
		
		//3-3. 댓글 목록 가져오기
		List<ReplyVO> rList=dao.listReply(idxStr);
		
		//3-4. 댓글 쓰기시 로그인 한 뒤 돌아갈 페이지를 세션에 저장하기
		HttpSession ses = req.getSession();
		ses.setAttribute("returnPage", "boardView.do?idx="+idxStr);
		
		//4. req에 저장
		req.setAttribute("board", board);//게시글
		req.setAttribute("replyList", rList);//댓글목록
		req.setAttribute("replyCount", replayCount);//댓글수
		
		//5. 뷰페이지 지정, 이동방식 지정
		this.setViewPage("board/boardView.jsp");
		this.setRedirect(false);
	}

}
