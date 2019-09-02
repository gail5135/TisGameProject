package notice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.BoardVO;
import board.model.ReplyVO;
import common.controller.AbstractAction;
import notice.model.NoticeDAOMyBatis;
import notice.model.NoticeVO;

public class NoticeViewAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("NoticeViewAction execute() 호출됨....");
		//1. 글번호 받기
		String idxStr=req.getParameter("idx");
		//2. 유효성 체크
		if(idxStr==null||idxStr.trim().isEmpty()) {
			this.setViewPage("noticeList.do");
			this.setRedirect(true);
			return;
		}
		//3. DAO의 메소드 호출
		NoticeDAOMyBatis dao=new NoticeDAOMyBatis(); 
		//	-조회수 증가
		boolean bool=dao.updateReadnum(idxStr);
		//	-상세 글 내용 가져오기
		NoticeVO notice = dao.getNotice(idxStr);
		
		
		//4. req에 저장
		req.setAttribute("board", notice);//게시글

		//5. 뷰페이지 지정, 이동방식 지정
		this.setViewPage("notice/noticeView.jsp");
		this.setRedirect(false);
	}

}
