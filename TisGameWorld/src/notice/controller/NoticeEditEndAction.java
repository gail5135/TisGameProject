package notice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
import common.util.CommonUtil;
import notice.model.NoticeDAOMyBatis;
import notice.model.NoticeVO;

public class NoticeEditEndAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("NoticeEditEndAction execute() 호출됨....");

		
		String idx = req.getParameter("idx");//수정할 글번호 받기
		String name = req.getParameter("name");
		String subject = req.getParameter("subject");
		String content = req.getParameter("content");
		String pwd = req.getParameter("pwd");
		
		NoticeVO notice = new NoticeVO(idx, name, pwd, subject, content, null, 0);
		NoticeDAOMyBatis dao = new NoticeDAOMyBatis();

		int n = dao.editNotice(notice);

		String str = (n > 0) ? "글수정 성공" : "글수정 실패";
		String loc = (n > 0) ? "noticeList.do" : "javascript:history.back()";
		
		String view=CommonUtil.addMsgLoc(req, str, loc);
		this.setViewPage(view);

		this.setRedirect(false);
	}

}
