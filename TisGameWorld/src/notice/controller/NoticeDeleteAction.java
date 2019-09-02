package notice.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import common.controller.AbstractAction;
import common.util.CommonUtil;
import notice.model.NoticeDAOMyBatis;
import notice.model.NoticeVO;

public class NoticeDeleteAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("NoticeDeleteAction execute() 호출됨....");
		
		//1. 글번호, 비밀번호 파라미터 값 받기
		String idx=req.getParameter("idx");
		String pwd=req.getParameter("pwd");
		String mode=req.getParameter("mode");//삭제 모드는 2
		if(idx==null||pwd==null||mode==null) {
			this.setViewPage("noticeList.do");
			this.setRedirect(true);
			return;
		}
		
		NoticeDAOMyBatis dao=new NoticeDAOMyBatis();
		NoticeVO dbBoard=dao.getNotice(idx);
		if(!pwd.equals(dbBoard.getPwd())) {//-비밀번호가 일치하지 않다면 (항상 부정적인것부터 먼저 처리)
			req.setAttribute("msg", "비밀번호가 일치하지 않아요");
			req.setAttribute("loc", "javascript:history.back()");
			this.setViewPage("memo/message.jsp");
			this.setRedirect(false);
			return;
		}
		
		//2. 해당 글의 비밀번호가 DB board 테이블의 값과 일치할 경우
		//	 삭제 처리
		//	 [1] 첨부파일이 있을경우
		//		  서버에서 업로드된 파일을 삭제처리한 후
		//		  DB에서 해달 글을 삭제한다.
		//	 [2] 첨부파일이 없을경우 => 삭제
		
		/*
		 * long fsize=dbBoard.getFilesize(); if(fsize>0) {//첨부파일이 있는 경우 String
		 * filename=dbBoard.getFilename(); System.out.println(filename); ServletContext
		 * app=req.getServletContext(); String
		 * upDir=app.getRealPath("/board/Upload");//-board밑의 upload
		 * System.out.println(upDir); File delFile=new
		 * File(upDir+File.separator+filename);//-파일의 절대 경로
		 * System.out.println(delFile.getAbsolutePath()); if(delFile.exists()) {//-파일이
		 * 존재하는가? boolean b=delFile.delete();//파일 삭제 처리
		 * System.out.println("삭제 처리 여부: "+b); } }//if--------------------
		 */		
		//DB에서 해당 글 삭제 처리
		int n = dao.deleteNotice(idx);
		String str=(n>0)?"삭제 성공":"삭제 실패";
		String loc=(n>0)?"noticeList.do":"javascript:history.back()";
		String view=CommonUtil.addMsgLoc(req, str, loc);
		
		this.setViewPage(view);
		this.setRedirect(false);
	}

}
