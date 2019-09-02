package notice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
import common.util.CommonUtil;
import notice.model.NoticeDAOMyBatis;
import notice.model.NoticeVO;

public class NoticeEditAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("NoticeEditAction execute() 호출됨....");
		
		String idx=req.getParameter("idx");
		String pwd=req.getParameter("pwd");
		String mode=req.getParameter("mode");
		if(idx==null||pwd==null||mode==null) {
			String view=CommonUtil.addMsgBack(req, "잘못 들어온 경로입네다.");
			this.setViewPage(view);
			this.setRedirect(false);
			return;
		}
		
		NoticeDAOMyBatis dao=new NoticeDAOMyBatis();
		NoticeVO board = dao.getNotice(idx);
		
		if(!board.getPwd().equals(pwd)) {
			this.setViewPage(CommonUtil.addMsgBack(req, "비밀번호가 틀립니다."));
			this.setRedirect(false);
			return;
		}
		
		//비번 일치시
		req.setAttribute("board", board);
		this.setViewPage("notice/noticeEdit.jsp");
		this.setRedirect(false);
	}

}
