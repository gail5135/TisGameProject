package memo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;

public class MemoFormAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("MemoFormAction execute() 호출됨....");
		
		//뷰페이지 지정
		this.setViewPage("./flapjellyfish/jellyfish.jsp");
		//이동방식 지정
		this.setRedirect(false);//forward방식으로 이동

	}

}
