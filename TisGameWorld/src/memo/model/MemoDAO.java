package memo.model;

import java.sql.*;
import java.util.*;
import common.util.DBUtil;
/* sw아키텍쳐 중 Persistence(영속성) 계층
 * - 데이터 처리 계층을 담당. 데이터베이스에 접속하여
 * 	 CRUD기능을 수행한다.
 * - Data Access Object를 줄여 DAO라고 함
 * 
 * */

public class MemoDAO {
	
	private Connection con;//-멤버변수
	private PreparedStatement ps;
	private ResultSet rs;

	public MemoDAO() {
		
	}
	/**메모글을 등록하는 메소드*/ //insert: memoVO유형
	public int insertMemo(MemoVO memo) throws SQLException{ //=insertMemo() 메소드에 MemoVO클래스를 넣고 memo라고 지칭함
		try {
			con=DBUtil.getCon();
			String sql="INSERT INTO jellyfish_memo VALUES(jellyfish_memo_seq.nextval,?,?,sysdate)";
			ps=con.prepareStatement(sql);
			ps.setString(1, memo.getName());
			ps.setString(2, memo.getMsg());
			
			int n = ps.executeUpdate();//-dml문장 (insert,update,delete)을 실행시킬때는 executeUpdate 실행되면 n은 1로 반환
			return n;
		} finally {
			close();
		}
	}
	
	/**메모글을 삭제하는 메소드 -pk로 삭제 delete문*/ //delete: primary key 유형
	public int deleteMemo(int idx) throws SQLException{//-idx가 primary key이기 때문
		try {
			con=DBUtil.getCon();
			//delete문 작성
			String sql="DELETE FROM jellyfish_memo WHERE idx=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1,idx);
			int n = ps.executeUpdate();
			return n;
		} finally {
			close();
		}
	}//-----------------------------------------------
	
	public int getTotalCount() throws SQLException {
		try {
			con=DBUtil.getCon();
			String sql="SELECT count(idx) FROM jellyfish_memo";//-pk로 넣어야 빠르게 수행가능
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			int total=0;
			if(rs.next()) { //-단일값이기 때문
				total=rs.getInt(1);
			}
			return total;
		} finally {
			close();
		}
		
	}//----------------------------------------------
	
	/**모든 메모글을 가져오는 메소드 -select문 수행*/
	public ArrayList<MemoVO> listMemo(int start, int end) throws SQLException{//MemoVO의 데이터를 하나 차곡차곡 쌓아 보이게 하기 위해 arraylist //ArrayList<MemoVO>형을 반환하는 listMemo()메소드
		try {
			con=DBUtil.getCon();
			//String sql="SELECT * FROM memo ORDER BY idx DESC";
			String sql="select * from(" + 
					" select rownum rn, a.* from" + 
					" (select * from jellyfish_memo order by idx desc) a" + 
					" ) where rn between ? and ?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
			rs=ps.executeQuery();//SELECT문일 경우 (DQL-Data Quary Language) executeQuery
			return makeList(rs); //=makeList(rs)로 반환한다.
			
		} finally {
			close();
		}
	}/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public ArrayList<MemoVO> makeList(ResultSet rs) throws SQLException{//
		ArrayList<MemoVO> arr = new ArrayList<MemoVO>();// -arraylist <MemoVO자료형인 >arr선언 //-arraylist생성
		while (rs.next()) {
			int idx = rs.getInt("idx");
			String name = rs.getString("name");
			String msg = rs.getString("msg");
			java.sql.Date wdate = rs.getDate("wdate");
			//record => MemoVO
			MemoVO memo = new MemoVO(idx,name,msg,wdate);//-이것을 어레이 리스트에 추가
			///////////////
			arr.add(memo);
		}
		return arr;
	}
	
	/**글 번호(pk)로 메모내용을 가져오는 메소드 -select문, where조건절*/
	public MemoVO selectMemoByIdx(int idx) throws SQLException{ //-idx가 primary key이기 때문에 idx로 받아옴
		try {
			con=DBUtil.getCon();
			String sql="SELECT * FROM jellyfish_memo WHERE idx=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, idx);
			rs=ps.executeQuery();
			ArrayList<MemoVO> arr=makeList(rs);//-rs를 받아서 ArrayList<MemoVO>로 넘겨준다
			if(arr!=null && arr.size()==1) {
				//해당 글이 존재한다면
				MemoVO memo=arr.get(0);
				return memo;
			}
			return null;
		} finally {
			close();
		}
	}
	
	/**메모글을 수정처리하는 메소드 => update문을 수행*/
	public int updateMemo(MemoVO memo) throws SQLException{
		try {
			con=DBUtil.getCon();
			String sql="UPDATE jellyfish_memo SET name=?, msg=? WHERE idx=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, memo.getName());
			ps.setString(2, memo.getMsg());
			ps.setInt(3, memo.getIdx());
			int n=ps.executeUpdate();
			return n;
		} finally {
			close();
		}
	}
	
	/**검색유형에 따라 검색하는 메소드-SELECT문, WHERE절에 LIKE절*/
	public ArrayList<MemoVO> findMemo(String colType, String keyword) throws SQLException{
		try {
			con=DBUtil.getCon();
			String sql="", colVal="";
			if(colType.contentEquals("idx")) {
				sql= "SELECT * FROM jellyfish_memo WHERE "+colType+" = ?";
				colVal=keyword;
			}else {
				sql= "SELECT * FROM jellyfish_memo WHERE "+colType+" LIKE ?";
				colVal="%"+keyword+"%";
			}
			ps=con.prepareStatement(sql);
			//ps.setString(1, colType);//-컬럼명은 인파라미터 (?)로 설정하면 안된다. ''가 생략되기 때문에 'name' 형식으로 인식된다.
			//ps.setString(1, "%"+keyword+"%"); //-여기에 %를 붙인다.
			ps.setString(1, colVal); //-int형도 date형도 다 String으로 호환가능
			rs=ps.executeQuery();
			ArrayList<MemoVO> arr=makeList(rs);//어레이 리스트 arr 받아오기 makelist에 rs만 넘겨주면 싹 받아옴
			return arr;

		} finally {
			close();
		}
	}
	
	/**DB관련 자원을 반납하는 메소드*/
	public void close() {
		try {
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(con!=null) con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}//------------------------------------
	
	
}////////////////////////////////////////////////////
