package memo.controller;

import java.util.List;

/*
 * 페이징 처리
 * [1] 총 게시글 수 (totalCount)
 * [2] 한 페이지당 보여줄 목록 갯수 (pageSize)
 * [3] 페이지수 (pageCount)
 * 
 * -자바 스크립트 일때 처리 방식
 * 	pageCount = Math.ceil(totalCount/display)
 * 
 * -자바-----------------------------------------
 * if(totalCount%pageSize==0)
 * 				pageCount=totalCount/pageSize
 * else
 * 				pageCount=totalCount/pageSize +1
 * ----------------------------------------------
 * 한줄로 줄일수 있다!
 * 	=>	pageCount=(totalCount-1)/pageSize+1;
 * 
 * totalCount	pageCount	pageSize
 * 1~9,	  10		1			10
 * 11~19, 20		2			10
 * 21~29, 30		3			10
 * */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
import memo.model.MemoDAO;
import memo.model.MemoVO;

public class MemoListAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("MemoListAction execute() 호출됨....");
		//현재 보여줄 페이지(cpage) 파라미터값 받기
		String cpStr=req.getParameter("cpage");
		if(cpStr==null) {//-cpage가 없다면?
			cpStr="1";//디폴트 페이지를 1페이지로 지정
		}
		int cpage=Integer.parseInt(cpStr.trim());
		
		if(cpage<=0) {
			cpage=1;//음수 값으로 왔을때 첫 페이지 지정
		}

		MemoDAO dao = new MemoDAO();
		// [1] 총 게시글 수 가져오기
		int totalCount = dao.getTotalCount();
		// [2] 한 페이지에 보여줄 목록갯수 정하기
		int pageSize = 5;
		// [3] 페이지수 구하기
		int pageCount = (totalCount - 1) / pageSize + 1;
		if(cpage>pageCount) {
			cpage=pageCount;//마지막 페이지를 지정
		}
		//cpage에 해당하는 데이터를 db에서 끊어 가져온다.
		//db에서 끊어 가져오기 위한 변수값 구하기
		int end = cpage*pageSize;
		int start=end-(pageSize-1);

		List<MemoVO> arr = dao.listMemo(start, end);
		req.setAttribute("memoArr", arr);// -key값이 중요
		req.setAttribute("totalCount", totalCount);// -req에 저장
		req.setAttribute("pageSize", pageSize);
		req.setAttribute("pageCount", pageCount);

		this.setViewPage("./flapjellyfish/jellyfish.jsp");
		this.setRedirect(false);

	}

}
