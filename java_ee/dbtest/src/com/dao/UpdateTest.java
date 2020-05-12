package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
//�ڹٿ����� �ڵ� Ŀ���� �ϰ� ��, ������ ������ Ŀ���� �ʿ����� ����
public class UpdateTest {
	private String driver="oracle.jdbc.driver.OracleDriver";  //����Ŭ���� �°� ����� �� �ִ� ����̹��� �ε��Ѵ�.(jar����)
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String userName="c##java";
	private String password ="bit";
	private Connection conn;
	private PreparedStatement pstmt;
	public UpdateTest() {
		try {
			Class.forName(driver);   //��Ȯ�� Ÿ���� �𸣱� ������ �׸� Ŭ������!! ��� ������ Ŭ���� Ÿ������ ����� ������ ��
			System.out.println("����̹� �ε� ����!");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
	public void insertArticle() {
		Scanner sc = new Scanner (System.in);
		System.out.print("������ �̸� �Է�: ");
		String name= sc.nextLine();
		String sql = "update dbtest set age=age+1, height=height+1 where name like '%'||?||'%'";
		this.getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			int su = pstmt.executeUpdate();
			System.out.println(su+"���� ���� �����Ǿ����ϴ�.");
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
