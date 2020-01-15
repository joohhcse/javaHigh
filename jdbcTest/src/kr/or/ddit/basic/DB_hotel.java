package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DB_hotel {

	public static void displayMenu(){
		System.out.println();
		System.out.println("메뉴를 선택하세요.");
		System.out.println(" 1. 체크인");
		System.out.println(" 2. 체크아웃");
		System.out.println(" 3. 객실 상태");
		System.out.println(" 0. 업무 종료");
		System.out.print(" 메뉴 선택 >> ");		
	}
		
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		int cnt = 0;
		boolean bChk = false;
		String sqlCheckRoom;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// Connection객체 생성
			String url = "jdbc:oracle:thin:@localhost:1521/xe";
			String userId = "joo";
			String password = "java";
			conn = DriverManager.getConnection(url, userId, password);
			stmt = conn.createStatement();

			String sql = "INSERT INTO hotel" + " (room_num, person_name) "
					+ " VALUES(?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			System.out.println("===============================================");
			System.out.println("   호텔 관리 프로그램(jdbc이용)");
			System.out.println("===============================================");

			while(true){
				
				displayMenu();  // 메뉴 출력
				
				int menuNum = sc.nextInt();   // 메뉴 번호 입력
				
				switch(menuNum){
					case 1 : // check in
						System.out.println();
						System.out.println("어느방에 체크인 하시겠습니까?");
						System.out.print("방 번호 입력 >> ");
						int roomNum = Integer.parseInt(sc.next());
						
						sqlCheckRoom = "SELECT * FROM hotel WHERE room_num = " + roomNum;
						rs = stmt.executeQuery(sqlCheckRoom);
						bChk = rs.next();
						
						if( !bChk )
						{
							pstmt.setInt(1, roomNum);

							System.out.println("이름 >> ");
							String personName = sc.next();
							pstmt.setString(2, personName);
							cnt = pstmt.executeUpdate();
						}
						else
						{
							System.out.printf("%d호는 이미 사용 중입니다!!!", roomNum);
						}
						break;
						
					case 2 : // check out
						System.out.println("=== 체크 아웃 ===");
						System.out.println("어느방을 체크아웃 하시겠습니까?");
						System.out.print("체크아웃 할 방 번호 입력 >> ");
						int checkOutRoomNum = Integer.parseInt(sc.next()); 

						sqlCheckRoom = "SELECT * FROM hotel WHERE room_num = " + checkOutRoomNum;
						rs = stmt.executeQuery(sqlCheckRoom);
						bChk = rs.next();
						
						if( bChk )
						{
							sqlCheckRoom = "DELETE FROM hotel WHERE room_num = " + checkOutRoomNum;
							rs = stmt.executeQuery(sqlCheckRoom);
							System.out.printf("%d 호 체크아웃 완료!", checkOutRoomNum);
						}
						else
						{
							System.out.printf("%d호는 체크아웃 할 수 없습니다!!!", checkOutRoomNum);
						}
								
						break;
						
					case 3 : // room state
						System.out.println("=== 객실 상태 ===");
						String sqlRoomState = "SELECT * FROM hotel";
//						System.out.println(sqlRoomState);	//test

						rs = stmt.executeQuery(sqlRoomState);

						while(rs.next()) 
						{
							// 컬럼의 자료를 가져오는 방법
							// 방법1) rs.get자료형이름("컬럼명")
							// 방법2) rs.get자료형이름(컬럼번호) -> 컬럼번호는 1번부터 시작
							System.out.println("=====================================");
							System.out.println("room_number : " + rs.getInt("room_num"));
							System.out.println("name : " + rs.getString("person_name"));
							System.out.println("=====================================");
						}

						break;
					case 4 :
						System.out.println("프로그램을 종료합니다...");
						return;
					default :
						System.out.println("잘못 입력했습니다. 다시입력하세요.");
				} // switch문
			} // while문
			
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if( pstmt != null ) try {pstmt.close();} catch (SQLException e2) {}
			if( stmt != null ) try {stmt.close();} catch (SQLException e2) {}
			if( conn != null ) try {conn.close();} catch (SQLException e2) {}
				
		}


	}

}
