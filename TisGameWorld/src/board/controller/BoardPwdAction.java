package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
import oracle.net.aso.e;

public class BoardPwdAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("BoardPwdAction execute() 호출됨....");
		
		if(!req.getMethod().equalsIgnoreCase("Post")){//-대소문자 구분 안하고 post방식으로 들어온 것이 아니라면
				this.setRedirect(true);
				this.setViewPage("boardList.do");
				return;
		}
		
		String idx = req.getParameter("idx");
		String mode = req.getParameter("mode");
		
		int md = Integer.parseInt(mode);
		String title="";
		if (md==1) {
			title="MODIFY";
		}else if (md==2) {
			title="DELETE";
		}
		req.setAttribute("idx", idx);
		req.setAttribute("mode", mode);
		req.setAttribute("title", title);
		
		//비밀번호 입력받는 페이지로 이동
		
		this.setViewPage("board/boardPwd.jsp");
		this.setRedirect(false);
	}

}
