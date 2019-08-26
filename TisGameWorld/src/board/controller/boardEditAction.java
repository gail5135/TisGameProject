package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAOMyBatis;
import board.model.BoardVO;
import common.controller.AbstractAction;
import common.util.CommonUtil;

public class boardEditAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("boardEditAction execute() 호출됨....");
		
		String idx=req.getParameter("idx");
		String pwd=req.getParameter("pwd");
		String mode=req.getParameter("mode");
		if(idx==null||pwd==null||mode==null) {
			String view=CommonUtil.addMsgBack(req, "잘못 들어온 경로입네다.");
			this.setViewPage(view);
			this.setRedirect(false);
			return;
		}
		
		BoardDAOMyBatis dao=new BoardDAOMyBatis();
		BoardVO board = dao.getBoard(idx);
		
		if(!board.getPwd().equals(pwd)) {
			this.setViewPage(CommonUtil.addMsgBack(req, "비밀번호가 틀립네다."));
			this.setRedirect(false);
			return;
		}
		
		//비번 일치시
		req.setAttribute("board", board);
		this.setViewPage("board/boardEdit.jsp");
		this.setRedirect(false);
	}

}
