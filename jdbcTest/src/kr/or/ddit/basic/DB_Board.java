package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;
import kr.or.ddit.util.DBUtil2;
import oracle.sql.CLOB;

//위의 테이블을 작성하고 게시판을 관리하는
//다음 기능들을 구현하시오.
//
//기능 구현하기 ==> 전체 목록 출력, 새글작성, 수정, 삭제, 검색 

public class DB_Board {

	Scanner scan = new Scanner(System.in);
	
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;
		
	public void displayMenu(){
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 자료 입력");
		System.out.println("  2. 자료 삭제");
		System.out.println("  3. 자료 수정");
		System.out.println("  4. 전체 자료 출력");
		System.out.println("  5. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}
	
	/**
	 * 프로그램 시작메서드
	 */
	public void start(){
		int choice;
		do{
			displayMenu(); //메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch(choice){
				case 1 :  // 글 작성
					insertBoard();
					break;
				case 2 :  // 자료 삭제
					deleteBoard();
					break;
				case 3 :  // 자료 수정
					updateBoard();
					break;
				case 4 :  // 전체 자료 출력
					displayAll();
					break;
				case 5 :  // 작업 끝
					System.out.println("작업을 마칩니다.");
					
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice!=5);
		
		System.exit(0);
	}

	//글 작성
	private void insertBoard() {
		boolean chk = false;
//		String memId = "";
//		do {
//			System.out.println();
//			System.out.println("추가할 회원 정보를 입력하세요.");
//			System.out.println("회원 ID >> ");
//			memId = scan.next();
//			
//			chk = getMember(memId);
//			
//			if(chk)
//			{
//				System.out.println("회원ID가 " + memId + "인 회원은 이미 존재합니다");
//				System.out.println("다시 입력하세요>");
//			}
//			
//		} while(chk == true);
		
		System.out.println("제목 >> ");
		String dbTitle = scan.next();
		
		System.out.println("작성자 >> ");
		String dbUser = scan.next();
		
		scan.nextLine(); //버퍼 비우기

		System.out.println("글 내용 >> ");
		String dbContent = scan.nextLine();

		try {
			conn = DBUtil.getConnection();
//			String sql = "INSERT INTO jdbc_board "
//					+ " (board_no, board_title, board_writer, board_date, board_content)"
//					+ " VALUES (board_seq.NEXTVAL, ?, ?, ?, ?)";

			String sql = "INSERT INTO jdbc_board VALUES (board_seq.nextval, ?, ?, SYSDATE, ?)";

//			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dbTitle);
			pstmt.setString(2, dbUser);
			pstmt.setString(3, dbContent);

			int cnt = pstmt.executeUpdate();
			
		}
		catch(SQLException e) {
			System.out.println("글작성 작업 실패!!");
			e.printStackTrace();
		}
		finally {
			disConnect(); //자원 반납
		}
	}
	
	private void deleteBoard() {
		System.out.println();
		System.out.println("삭제할 글번호를 입력하세요");
		String dbNum = scan.next();
		
		try {
			conn = DBUtil.getConnection();
			String sql = "DELETE FROM jdbc_board WHERE board_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dbNum);
			
			int cnt = pstmt.executeUpdate();
			if(cnt > 0)
			{
				System.out.println(dbNum + "글 삭제 성공!");
			}
			else
			{
				System.out.println(dbNum + "글 삭제 실패!");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			disConnect();
		}
	}
	
	private void updateBoard() {
		System.out.println();
		String dbNum = "";
		boolean chk = true;
		
		do {
			System.out.print("수정할 글번호를 입력하세요 >> ");
			dbNum = scan.next();
			
			chk = getBoard(dbNum);
			
			if(chk == false)
			{
				System.out.println(dbNum + " 는 없는 글번호입니다!");
				System.out.println("수정할 자료가 없으니 다시 입력하세요");
			}
		} while(chk == false);
		
		System.out.println("수정할 내용을 입력하세요");
		System.out.print("새로운 글 제목 >> ");
		String dbTitle =scan.next();
		
		System.out.println("새로운 글 작성자 >> ");
		String dbUser = scan.next();
		
		scan.nextLine();	//버퍼 지우기
		System.out.print("새로운 글내용 >> ");
		String dbContent = scan.nextLine();
		
		try {
			conn = DBUtil2.getConnection();
			
			String sql = "UPDATE jdbc_board SET board_no = ?, board_title = ?, "
						+ " board_writer = ?, "
						+ " board_date = SYSDATE, "
						+ " board_content = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dbNum);
			pstmt.setString(2, dbTitle);
			pstmt.setString(3, dbUser);
			pstmt.setString(4, dbContent);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println(dbNum + " 를 수정했습니다.");
			}
			else
			{
				System.out.println(dbNum + " > 수정 실패!");
			}
		}
		catch(SQLException e) {
			System.out.println("글 정보 수정 실패!");
			e.printStackTrace();
		}
		finally {
			disConnect(); //자원 반납
		}	
	}

	private void disConnect() {
		if(rs!=null)try{ rs.close(); }catch(SQLException ee){}
		if(stmt!=null)try{ stmt.close(); }catch(SQLException ee){}
		if(pstmt!=null)try{ pstmt.close(); }catch(SQLException ee){}
//		if(pstmt2!=null)try{ pstmt2.close(); }catch(SQLException ee){}
		if(conn!=null)try{ conn.close(); }catch(SQLException ee){}
		
	}

	private void displayAll() {
		System.out.println();
		System.out.println("=========================================");
		System.out.println(" 글번호\t제목\t작성자\t\t글내용");
		System.out.println("=========================================");
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "SELECT * FROM jdbc_board";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				int no = rs.getInt("board_no");
				String title = rs.getString("board_title");
				String writer = rs.getString("board_writer");
				Date date = rs.getDate("board_date");
				String content = rs.getString("board_content");
				
				System.out.println(no + "\t" 
							   + title + "\t"
							   + writer +  "\t"
							   + date + "\t"
							   + content);
			}
			System.out.println("=========================================");
			System.out.println("=================출력작업 끝=================");
			
		}
		catch(SQLException e) {
			System.out.println("글 가져오기 실패!");
			e.printStackTrace();
		}
		finally	{
			disConnect(); //자원 반납
		}
		
		
	}

	private boolean getBoard(String dbNum) {
		boolean chk = false;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT COUNT(*) AS cnt FROM jdbc_board"
						+ " WHERE board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dbNum);
			
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			
			if(rs.next())
			{
				cnt = rs.getInt("cnt");
			}
			
			if(cnt > 0)
			{
				chk = true;
			}
		} 
		catch(SQLException e) {
			e.printStackTrace();
			chk = false;
		}
		finally {
			disConnect();
		}
		return chk;
	}


	public static void main(String[] args) {
		DB_Board dbObj = new DB_Board();
		dbObj.start();

	}
}
