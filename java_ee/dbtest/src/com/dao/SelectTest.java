package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectTest {
	private String driver="oracle.jdbc.driver.OracleDriver";  //����Ŭ���� �°� ����� �� �ִ� ����̹��� �ε��Ѵ�.(jar����)
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String userName="c##java";
	private String password ="bit";
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	public SelectTest() {
		try {
			Class.forName(driver);   //��Ȯ�� Ÿ���� �𸣱� ������ �׳� Ŭ������!! ��� ������ Ŭ���� Ÿ������ ����� ������ ��
			System.out.println("����̹� �ε� ����!");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//resultSet
	}
	public void getConnection() {
		try {
			conn=DriverManager.getConnection(url, userName, password);
			System.out.println("����Ŭ ���� ����");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void selectArticle() {
		getConnection();
		
		String sql = "Select * from dbTest";
		try {
			
			pstmt = conn.prepareStatement(sql);
			int su = pstmt.executeUpdate();
			rs = pstmt.executeQuery();  
			//rs.next(); ���� ��ġ�� ���ڵ�(��)�� ������ true, ������ false
			//re.getString ("name") �Ǵ� rs.getString(1);
			//rs.getInt("age") �Ǵ� re.getInt(2);
			//rs.getDouble("height") �Ǵ� rs.getDouble(3);
			//rs.getDate()
			//rs.getString("logTime") �Ǵ� rs.getString(4);
			while(rs.next()) {  //���̺� �����Ͱ� ���� �� ���� �ݺ�
				System.out.println(rs.getString("name")+"\t"+rs.getInt("age")+"\t"+rs.getDouble("height")+"\t"+rs.getString("logTime"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	public static void main(String[] args) {
		SelectTest selectTest = new SelectTest();
		selectTest.selectArticle();
		

	}

}
