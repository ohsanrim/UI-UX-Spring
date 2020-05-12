package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Student {
	private String driver="oracle.jdbc.driver.OracleDriver";//
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String userName = "c##java";
	private String password = "bit";
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	public Student() {
		try {
			Class.forName(driver); // ����̹� ����
			System.out.println("����̹� �ε� ����!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Student s = new Student();
		s.menu();
	}
	public void menu() {
		Scanner sc = new Scanner (System.in);
		while(true) {
			System.out.println("************");
			System.out.println("           ����");
			System.out.println("************");
			System.out.println("1. �Է�");
			System.out.println("2. �˻�");
			System.out.println("3. ����");
			System.out.println("4. ����");
			System.out.println("************");
			System.out.print("��ȣ ����: ");
			int choice = sc.nextInt();
			sc.nextLine();
			if(choice ==4) {
				System.out.println("�ý����� �����մϴ�!");
			} else if(choice==1) {
				int code =0;   //�ڵ��ȣ
				String name;   //�̸�
				int hak;   //�й�
				
				System.out.println("************");
				System.out.println("1. �л�");
				System.out.println("2. ����");
				System.out.println("3. ������");
				System.out.println("4. �����޴�");
				System.out.println("************");
				System.out.print("��ȣ ����: ");
				int who = sc.nextInt();
				sc.nextLine();
				switch (who) {
				case 1: code=1; break;
				case 2: code=2; break;
				case 3: code=3; break;
				case 4: code=4; break;
				}  //switch
				if(code >=1&&code<=3) insertArticle(code);
				else System.out.println("���� ȭ������ ���ư��ϴ�.");
			} else if(choice==2) {
				System.out.println("************");
				System.out.println("1. �̸� �˻�");
				System.out.println("2. ��ü �˻�");
				System.out.println("3. �����޴�");
				System.out.println("************");
				System.out.print("��ȣ ����: ");
				int num= sc.nextInt();
				if(num==1||num==2) {
					selectArticle(num);
				}
			}  //�˻�
			else if(choice==3) {
				deleteArticle();
			}  //����
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
	public void insertArticle(int choice) {
		Scanner sc = new Scanner(System.in);
		int code=choice;
		//�̸� �Է�
		System.out.print("�̸� �Է�: ");
		String name = sc.nextLine();
		//�й�,����, �μ� �Է�
		if(code==1) System.out.print("�й� �Է�: ");
		else if(code==2) System.out.print("���� �Է�: ");
		else if(code==3) System.out.print("�μ� �Է�: ");
		String hak = sc.nextLine();
		
		String sql ="insert into student values(?,?,?)";  //������ ���ؼ�. �̷��� ���� ������ �������� ���ؼ� ���������� Ȯ���� �� �ֱ� ������ �����ؾ� �Ѵ�. 
		//DB
		this.getConnection();
		//����Ŭ�� ��ɾ �뿪�Ͽ� �������� ���� �ʿ���
		try {
			pstmt = conn.prepareStatement(sql);
			//�����͸� ?�� ���� (�ڹٴ� �ε����� 0������ ���������� ����Ŭ�� 1���� �����Ѵ�)
			pstmt.setString(1, name);
			pstmt.setString(2, hak);
			pstmt.setDouble(3, code);
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
	public void selectArticle(int num) {
		String sql=null;
		String name=null;
		if(num==1) {
			Scanner sc= new Scanner (System.in);
			System.out.print("�˻��� �̸��� �Է��ϼ���!");
			name= sc.nextLine();
		}
		getConnection();
		if(num==1) {
			sql = "Select * from student where name like '%'||?||'%'";
		}
		else if(num==2) sql = "Select * from student";
		try {
			pstmt = conn.prepareStatement(sql);
			if(num==1)pstmt.setString(1, name);
			int su = pstmt.executeUpdate();  
			rs = pstmt.executeQuery(); 
			while(rs.next()) {  //���̺� �����Ͱ� ���� �� ���� �ݺ�
				System.out.println(rs.getString("name")+"\t"+rs.getString("value")+"\t"+rs.getInt("code"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void deleteArticle() {
		Scanner sc = new Scanner(System.in);
		getConnection();
		//�̸� �Է�
		System.out.print("������ ���ϴ� �̸� �Է�: ");
		String name = sc.nextLine();
		String sql ="delete student where name = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			int su = pstmt.executeUpdate();  
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(pstmt!=null) pstmt.close();
			if(conn!=null)conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("�����Ǿ����ϴ�!");
	}
	

}
