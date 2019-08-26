package common.util;

import java.sql.*;
import javax.naming.*;
import javax.sql.*;


public class DAOBase {

	protected DataSource ds;
	private ConnectionPoolBean pool;
	protected Connection con;//1) DBUtil통해 얻기
							// 2) ConnectionPoolBean통해 얻기
							// 3) DataSource통해서 얻기(DBCP)
	protected PreparedStatement ps;
	protected ResultSet rs;
	
	public DAOBase() {
		try {
			Context ctx=new InitialContext();
			ds=(DataSource)ctx.lookup("java:comp/env/jdbc/mydev");
			System.out.println("데이터 소스 룩업 성공");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public ConnectionPoolBean getPool() {//get
		return pool;
	}
	
	public void setPool(ConnectionPoolBean pool) {//set
		this.pool = pool;
		System.out.println("커넥션풀 주입(setPool) : "+pool);
	}
	
	public void close() {
		try {
			if(rs!=null)rs.close();
			if(ps!=null)ps.close();
			if(con!=null)con.close();
			//DBCP는 con.close() 하면 연결을 끊는 것이 아니라
			//풀에 반납을 해준다.
			//if(con!=null)pool.returnConnection(con);
		} catch (Exception e) {
			System.out.println("error: "+e);
		}
	}
}
