package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
// ����Ŭ �����ϴ� ����
public class InsertTest {
	private String driver="oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String userName = "c##java";
	private String password = "bit";
	private Connection conn;
	private PreparedStatement pstmt;

	public InsertTest() {
		// "OracleDriver.class" //����
		try {
			Class.forName(driver); // ����̹� ����
			System.out.println("����̹� �ε� ����!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void getConnection() {
		try {
			conn = DriverManager.getConnection(url, userName, password); // ����
			System.out.println("����Ŭ ���� ����");																		// �����ͺ��̽�����̹�
																					// ����
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void insertArticle() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("�̸� �Է�: ");
		String name = sc.nextLine();
		System.out.print("���� �Է�: ");
		int age = sc.nextInt();
		System.out.print("Ű �Է�: ");
		double height = sc.nextDouble();
		String sql ="insert into dbtest values(?,?,?,sysdate)";  //������ ���ؼ�. �̷��� ���� ������ �������� ���ؼ� ���������� Ȯ���� �� �ֱ� ������ �����ؾ� �Ѵ�. 
		//DB
		this.getConnection();
		//����Ŭ�� ��ɾ �뿪�Ͽ� �������� ���� �ʿ���
		try {
			pstmt = conn.prepareStatement(sql);
			//�����͸� ?�� ���� (�ڹٴ� �ε����� 0������ ���������� ����Ŭ�� 1���� �����Ѵ�)
			pstmt.setString(1, name);
			pstmt.setInt(2, age);
			pstmt.setDouble(3, height);
			int su=pstmt.executeUpdate();  //����
			System.out.println(su+"���� ���� ����������ϴ�.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//������ �ִ� ���� �����϶�
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
