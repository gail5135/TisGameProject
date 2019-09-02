package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAOMyBatis;
import board.model.ReplyVO;
import common.controller.AbstractAction;
import common.util.CommonUtil;

public class ReplyInsertAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("ReplyInsertAction execute() 호출됨....");
		//userid, idx_fk, content
		String userid=req.getParameter("userid");
		String idx_fk=req.getParameter("idx_fk");
		String content=req.getParameter("content");
		
		if(userid==null||idx_fk==null||userid.trim().isEmpty()||idx_fk.trim().isEmpty()) {
			/* this.setViewPage("../boardList.do"); */
			this.setViewPage("boardList.do");
			this.setRedirect(true);
			return;
		}
		
		//ReplyVO에 담기
		ReplyVO reply = new ReplyVO(null, userid, content, null, idx_fk);
		//System.out.println(userid);
		
		//BoardDAOMyBatis의 insertReply호출 후 메시지 처리
		BoardDAOMyBatis dao = new BoardDAOMyBatis();
		int n=dao.insertReply(reply);
		/* this.setViewPage("../boardView.do?idx="+idx_fk); */
		this.setViewPage("boardView.do?idx="+idx_fk);
		this.setRedirect(true);
		
		/*
		String msg=(n>0)? "댓글 달기 성공":"댓글 달기 실패";
		String loc="../boardView.do?idx="+idx_fk;
		//String loc=(n>0)? "boardView.do?idx="+idx_fk:"javascript:history.back()";
		String viewName=CommonUtil.addMsgLoc(req, msg, loc);
		
		this.setViewPage(viewName);
		this.setRedirect(false);*/
	}

}
