package notice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import common.controller.AbstractAction;
import notice.model.NoticeDAOMyBatis;
import notice.model.NoticeVO;

public class NoticeListAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("NoticeListAction execute() 호출됨....");
		
		//세션 얻어오기
		HttpSession ses=req.getSession();
		
		//현재 보여줄 페이지 값 받기
		String cpStr=req.getParameter("cpage");
		if(cpStr==null||cpStr.trim().isEmpty()) {
			cpStr="1";
		}
		int cpage=Integer.parseInt(cpStr.trim());
		
		//int pageSize=5;
		String psStr=req.getParameter("pageSize");
		if(psStr==null) {
			//세션에 저장된 pageSize가 있는지 체크
			psStr=(String) ses.getAttribute("pageSize");
			if(psStr==null) {
				psStr="5";//디폴트 페이지 사이즈를 10개 지정
			}
		}
		int pageSize=Integer.parseInt(psStr);
		ses.setAttribute("pageSize", psStr);
		
		//-map
		Map<String, String> map = new HashMap<>();
		
		//검색어 받아서
		String findType=req.getParameter("findType");
		String findKeyword=req.getParameter("findKeyword");
		//-map에 넣어줌
		map.put("findType", findType);
		map.put("findKeyword", findKeyword);
		
		
		//총 게시물 수 구하기
		NoticeDAOMyBatis dao=new NoticeDAOMyBatis();
		int totalCount=dao.getToalCount(map);//검색유형, 검색어
		//페이지수 구하기
		int pageCount=(totalCount-1)/pageSize+1;
		
		if(cpage<=0) {
			cpage=1;
		}
		if(cpage>pageCount) {
			cpage=pageCount;
		}
		int end=cpage*pageSize;
		int start=end-(pageSize-1);
		/*
		 * [1][2][3][4][5] | [6][7][8][9][10] | [11][12]...
		 * cpage			pagingBlock				prevBlock			nextBlock
		 * 1~4,5				5개						0					6
		 * 6~9,10				5						5					11
		 * 11~14,15				5						10					16
		 * 
		 * prevBlock=(cpage-1)pagingBlock*pagingBlock;
		 * nextBlock=prevBlock+(pagingBlock+1)
		 * 
		 * */
		
		
		
		int pagingBlock=5;//5개 단위로 페이지 묶음 처리
		int prevBlock=(cpage-1)/pagingBlock*pagingBlock;
		int nextBlock=prevBlock+(pagingBlock+1);
		
		map.put("start", String.valueOf(start));
		map.put("end", String.valueOf(end));
		
		List<NoticeVO> arr=dao.listNotice(map);//-BoardDAOMyBatis()의 listBoard()를 실행하여 List<BoardVO>형인 arr에 담는다.
		
		req.setAttribute("boardArr", arr);//-arr을 boardArr로 저장하여 보낸다
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("cpage", cpage);
		req.setAttribute("pagingBlock", pagingBlock);
		req.setAttribute("prevBlock", prevBlock);
		req.setAttribute("nextBlock", nextBlock);
		req.setAttribute("findType", findType);
		req.setAttribute("findKeyword", findKeyword);
		
		//-내가 추가한것
		int firstCount=1;
		req.setAttribute("firstCount", firstCount);
		int lastCount=0;
		if(totalCount%pagingBlock!=0) {
			lastCount=(totalCount/pagingBlock)+1;
		} else {
			lastCount=totalCount/pagingBlock;
		}
		req.setAttribute("lastCount", lastCount);
		
		//테스트
		//int count = dao.getCount();
		//req.setAttribute("count", count);
		//this.setViewPage("board/testMybatis.jsp");
		
		this.setViewPage("notice/noticeList2.jsp");
		this.setRedirect(false);

	}

}
