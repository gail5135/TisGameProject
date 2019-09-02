package memo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
import memo.model.MemoDAO;
import memo.model.MemoVO;

// /memoAdd.do => MemoAddAction => /memo/message.jsp

public class MemoAddAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("MemoAddAction execute() 호출됨....");
		
		//res.setCharacterEncoding("UTF-8");
		String name=req.getParameter("name");
		String msg=req.getParameter("msg");
		if(name==null||msg==null||name.trim().isEmpty()) {
			//redirect로 메모 등록 폼 페이지로 이동
			this.setViewPage("jellyfish.do");
			this.setRedirect(true);//redirect방식으로 이동 //-redirect할때는--.do로 이동해야함
			return;
		}
		MemoVO memo=new MemoVO(0,name,msg,null);
		MemoDAO dao=new MemoDAO();
		int n=dao.insertMemo(memo);
		String str=(n>0)?"등록 성공":"등록 실패";
		String loc=(n>0)?"jellyfish.do":"javascript:history.back()";
		
		req.setAttribute("msg", str);
		req.setAttribute("loc", loc);
		
		//뷰페이지 지정
		this.setViewPage("./memo/message.jsp");
		//이동방식 지정
		this.setRedirect(false);//forward방식으로 이동

	}

}
