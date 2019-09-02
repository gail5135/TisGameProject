package notice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;

public class NoticeFormAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("NoticeFormAction execute() 호출됨....");
		//뷰페이지 지정
		//boardForm.do ===> board/boardWrite.jsp보여주도록
		this.setViewPage("notice/noticeWrite.jsp");
		this.setRedirect(false);
	}

}
