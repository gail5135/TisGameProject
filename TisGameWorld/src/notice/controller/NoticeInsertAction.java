package notice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
import notice.model.NoticeDAOMyBatis;
import notice.model.NoticeVO;

public class NoticeInsertAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("NoticeInsertAction execute() 호출됨....");
		
		
		String name=req.getParameter("name");
		String subject=req.getParameter("subject");
		String content=req.getParameter("content");
		String pwd=req.getParameter("pwd");
		
		NoticeVO notice=new NoticeVO(null,name,pwd,subject,content,null,0);
		NoticeDAOMyBatis dao=new NoticeDAOMyBatis();
		
		int n=0;
		//for(int i=0;i<50;i++) {	//-글 여러번 쓰게
			n =dao.insertNotice(notice);
		//}
		
		String str=(n>0)? "글쓰기 성공":"글쓰기 실패";
		String loc=(n>0)? "noticeList.do":"javascript:history.back()";
		
		req.setAttribute("msg", str);
		req.setAttribute("loc", loc);
		this.setViewPage("memo/message.jsp");
		this.setRedirect(false);
	}
}
