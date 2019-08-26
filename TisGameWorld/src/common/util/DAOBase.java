package common.util;

import java.sql.*;
import javax.naming.*;
import javax.sql.*;


public class DAOBase {

	protected DataSource ds;
	private ConnectionPoolBean pool;
	protected Connection con;//1) DBUtil���� ���
							// 2) ConnectionPoolBean���� ���
							// 3) DataSource���ؼ� ���(DBCP)
	protected PreparedStatement ps;
	protected ResultSet rs;
	
	public DAOBase() {
		try {
			Context ctx=new InitialContext();
			ds=(DataSource)ctx.lookup("java:comp/env/jdbc/mydev");
			System.out.println("������ �ҽ� ��� ����");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public ConnectionPoolBean getPool() {//get
		return pool;
	}
	
	public void setPool(ConnectionPoolBean pool) {//set
		this.pool = pool;
		System.out.println("Ŀ�ؼ�Ǯ ����(setPool) : "+pool);
	}
	
	public void close() {
		try {
			if(rs!=null)rs.close();
			if(ps!=null)ps.close();
			if(con!=null)con.close();
			//DBCP�� con.close() �ϸ� ������ ���� ���� �ƴ϶�
			//Ǯ�� �ݳ��� ���ش�.
			//if(con!=null)pool.returnConnection(con);
		} catch (Exception e) {
			System.out.println("error: "+e);
		}
	}
}
