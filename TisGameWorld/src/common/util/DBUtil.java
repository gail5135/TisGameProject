package common.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	
	private static String url="jdbc:oracle:thin:@localhost:1521:xe";
	private static String user="mydev";
	private static String pwd="tiger";
	
	static {
		//static initializer : main()메소드 보다도 먼저 실행하는 블럭
		//여기서 오라클 드라이버 로드
		
		//System.out.println("stack block");
		
		try {//static문은 throws 불가능
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("Driver Loading Success!");
		}catch(ClassNotFoundException e) {//실패
			System.out.println("Driver Loading Fail..: ");
			e.printStackTrace();
		}
		
	}
	
	public static Connection getCon() throws java.sql.SQLException{
		Connection con=DriverManager.getConnection(url,user,pwd);
		return con;
	}
	
}
