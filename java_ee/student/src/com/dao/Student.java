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
			Class.forName(driver); // 드라이버 생성
			System.out.println("드라이버 로딩 성공!");
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
			System.out.println("           관리");
			System.out.println("************");
			System.out.println("1. 입력");
			System.out.println("2. 검색");
			System.out.println("3. 삭제");
			System.out.println("4. 종료");
			System.out.println("************");
			System.out.print("번호 선택: ");
			int choice = sc.nextInt();
			sc.nextLine();
			if(choice ==4) {
				System.out.println("시스템을 종료합니다!");
			} else if(choice==1) {
				int code =0;   //코드번호
				String name;   //이름
				int hak;   //학번
				
				System.out.println("************");
				System.out.println("1. 학생");
				System.out.println("2. 교수");
				System.out.println("3. 관리자");
				System.out.println("4. 이전메뉴");
				System.out.println("************");
				System.out.print("번호 선택: ");
				int who = sc.nextInt();
				sc.nextLine();
				switch (who) {
				case 1: code=1; break;
				case 2: code=2; break;
				case 3: code=3; break;
				case 4: code=4; break;
				}  //switch
				if(code >=1&&code<=3) insertArticle(code);
				else System.out.println("이전 화면으로 돌아갑니다.");
			} else if(choice==2) {
				System.out.println("************");
				System.out.println("1. 이름 검색");
				System.out.println("2. 전체 검색");
				System.out.println("3. 이전메뉴");
				System.out.println("************");
				System.out.print("번호 선택: ");
				int num= sc.nextInt();
				if(num==1||num==2) {
					selectArticle(num);
				}
			}  //검색
			else if(choice==3) {
				deleteArticle();
			}  //삭제
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
	public void insertArticle(int choice) {
		Scanner sc = new Scanner(System.in);
		int code=choice;
		//이름 입력
		System.out.print("이름 입력: ");
		String name = sc.nextLine();
		//학번,과목, 부서 입력
		if(code==1) System.out.print("학번 입력: ");
		else if(code==2) System.out.print("과목 입력: ");
		else if(code==3) System.out.print("부서 입력: ");
		String hak = sc.nextLine();
		
		String sql ="insert into student values(?,?,?)";  //보안을 위해서. 이렇게 하지 않으면 역추적을 통해서 개인정보를 확인할 수 있기 때문에 조심해야 한다. 
		//DB
		this.getConnection();
		//오라클로 명령어를 통역하여 전달해줄 것이 필요함
		try {
			pstmt = conn.prepareStatement(sql);
			//데이터를 ?에 주입 (자바는 인덱스가 0번부터 시작하지만 오라클은 1부터 시작한다)
			pstmt.setString(1, name);
			pstmt.setString(2, hak);
			pstmt.setDouble(3, code);
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
	public void selectArticle(int num) {
		String sql=null;
		String name=null;
		if(num==1) {
			Scanner sc= new Scanner (System.in);
			System.out.print("검색할 이름을 입력하세요!");
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
			while(rs.next()) {  //테이블에 데이터가 없을 때 까지 반복
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
		//이름 입력
		System.out.print("삭제를 원하는 이름 입력: ");
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
		System.out.println("삭제되었습니다!");
	}
	

}
