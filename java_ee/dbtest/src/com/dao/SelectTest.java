package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectTest {
	private String driver="oracle.jdbc.driver.OracleDriver";  //오라클에만 맞게 사용할 수 있는 드라이버를 로딩한다.(jar파일)
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String userName="c##java";
	private String password ="bit";
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	public SelectTest() {
		try {
			Class.forName(driver);   //정확한 타입을 모르기 때문에 그냥 클래스다!! 라고 강제로 클래스 타입으로 만들어 버리는 것
			System.out.println("드라이버 로딩 성공!");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//resultSet
	}
	public void getConnection() {
		try {
			conn=DriverManager.getConnection(url, userName, password);
			System.out.println("오라클 접속 성공");
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
			//rs.next(); 현재 위치에 레코드(행)가 있으면 true, 없으면 false
			//re.getString ("name") 또는 rs.getString(1);
			//rs.getInt("age") 또는 re.getInt(2);
			//rs.getDouble("height") 또는 rs.getDouble(3);
			//rs.getDate()
			//rs.getString("logTime") 또는 rs.getString(4);
			while(rs.next()) {  //테이블에 데이터가 없을 때 까지 반복
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
