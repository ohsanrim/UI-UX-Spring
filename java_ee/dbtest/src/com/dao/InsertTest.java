package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
// 오라클 접속하는 예제
public class InsertTest {
	private String driver="oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String userName = "c##java";
	private String password = "bit";
	private Connection conn;
	private PreparedStatement pstmt;

	public InsertTest() {
		// "OracleDriver.class" //생성
		try {
			Class.forName(driver); // 드라이버 생성
			System.out.println("드라이버 로딩 성공!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void getConnection() {
		try {
			conn = DriverManager.getConnection(url, userName, password); // 나의
			System.out.println("오라클 접속 성공");																		// 데이터베이스드라이버
																					// 접속
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void insertArticle() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("이름 입력: ");
		String name = sc.nextLine();
		System.out.print("나이 입력: ");
		int age = sc.nextInt();
		System.out.print("키 입력: ");
		double height = sc.nextDouble();
		String sql ="insert into dbtest values(?,?,?,sysdate)";  //보안을 위해서. 이렇게 하지 않으면 역추적을 통해서 개인정보를 확인할 수 있기 때문에 조심해야 한다. 
		//DB
		this.getConnection();
		//오라클로 명령어를 통역하여 전달해줄 것이 필요함
		try {
			pstmt = conn.prepareStatement(sql);
			//데이터를 ?에 주입 (자바는 인덱스가 0번부터 시작하지만 오라클은 1부터 시작한다)
			pstmt.setString(1, name);
			pstmt.setInt(2, age);
			pstmt.setDouble(3, height);
			int su=pstmt.executeUpdate();  //실행
			System.out.println(su+"개의 행이 만들어졌습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//에러가 있던 없던 실행하라
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	} 

	public static void main(String[] args) {
		InsertTest insertTest = new InsertTest();
		insertTest.insertArticle();
	}

}
