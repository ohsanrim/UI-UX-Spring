package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
//자바에서는 자동 커밋을 하게 됨, 때문에 별도의 커밋은 필요하지 않음
public class UpdateTest {
	private String driver="oracle.jdbc.driver.OracleDriver";  //오라클에만 맞게 사용할 수 있는 드라이버를 로딩한다.(jar파일)
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String userName="c##java";
	private String password ="bit";
	private Connection conn;
	private PreparedStatement pstmt;
	public UpdateTest() {
		try {
			Class.forName(driver);   //정확한 타입을 모르기 때문에 그먕 클래스다!! 라고 강제로 클래스 타입으로 만들어 버리는 것
			System.out.println("드라이버 로딩 성공!");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
	public void insertArticle() {
		Scanner sc = new Scanner (System.in);
		System.out.print("수정할 이름 입력: ");
		String name= sc.nextLine();
		String sql = "update dbtest set age=age+1, height=height+1 where name like '%'||?||'%'";
		this.getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			int su = pstmt.executeUpdate();
			System.out.println(su+"개의 행이 수정되었습니다.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
				try {
					if(pstmt !=null)pstmt.close();
					if(conn!=null) conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		
	}
	public static void main(String[] args) {
		UpdateTest updateTest = new UpdateTest();
		updateTest.insertArticle();
		

	}

}
