package common.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	
	private static String url="jdbc:oracle:thin:@localhost:1521:xe";
	private static String user="mydev";
	private static String pwd="tiger";
	
	static {
		//static initializer : main()�޼ҵ� ���ٵ� ���� �����ϴ� ��
		//���⼭ ����Ŭ ����̹� �ε�
		
		//System.out.println("stack block");
		
		try {//static���� throws �Ұ���
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("Driver Loading Success!");
		}catch(ClassNotFoundException e) {//����
			System.out.println("Driver Loading Fail..: ");
			e.printStackTrace();
		}
		
	}
	
	public static Connection getCon() throws java.sql.SQLException{
		Connection con=DriverManager.getConnection(url,user,pwd);
		return con;
	}
	
}
