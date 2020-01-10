package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class T02_jdbcTest {

	/*
	 *  문제1) 사용자로부터 lprod_id값을 입력받아 입력한 값보다 lprod_id가 큰 자료들을 출력하시오
	 *  
	 *  문제2) lprod_id값을 2개 입력받아 두 값 중 작은 값부터 큰값 사이의 자료를 출력하시오
	 */
	
	public static void main(String[] args) {
		
		// DB작업에 필요한 객체 변수 선언
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;	//  쿼리문이 SELECT일 경우에 필요

		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. DB에 접속(Connection 객체 생성)
			String url = "jdbc:oracle:thin:@localhost:1521/xe";
			String userId = "joo";
			String password = "java";
			
			// 실제적으로 OracleDriver가 사용되는 부분
			conn = DriverManager.getConnection(url, userId, password);
			
			// 3. Statement객체 생성 -> Connection 객체를 이용한다
			stmt = conn.createStatement();
			
			Scanner sc = new Scanner(System.in);

			int num1, num2;
			int max, min;
			
			System.out.println("lprod_id 두개의 값 입력>");
			num1 = Integer.parseInt(sc.nextLine());
			num2 = Integer.parseInt(sc.nextLine());
			
			max = Math.max(num1, num2);
			min = Math.min(num1, num2);
			
			System.out.println("문제1) lprod_id값을 입력받아 입력한 값보다 lprod_id가 큰 자료들을 출력");
//			int col = Integer.parseInt(sc.nextLine());
//			String sql = "";
//			sql.format("SELECT * FROM lprod WHERE lprod_id > %d", col);
			String sql = "SELECT * FROM lprod " + " WHERE lprod_id BETWEEN " + min + " AND " + max;
			
			// SQL문이 SELECT일 경우에는 executeQuery() 메서드를 사용하고  INSERT, UPDATE, DELETE일 경우에는 executeUpdate() 메서드 사용함
			rs = stmt.executeQuery(sql);
			
			// 5. ResultSet객체에 저장되어 있는 자료를 반복문과 next()메서드를 이용하여 차례로 읽어와 처리한다
			System.out.println("실행한 쿼리문 : " + sql );
			System.out.println("=== 쿼리문 실행결과 ===");
			
			// rs.next() -> ResultSet객체의 데이터를 가리키는 포인터를 다음 레코드로 이동시키고 그 곳에 자료가 있으면 true, 없으면 false를 반환한다
			while(rs.next()) 
			{
				// 컬럼의 자료를 가져오는 방법
				// 방법1) rs.get자료형이름("컬럼명")
				// 방법2) rs.get자료형이름(컬럼번호) -> 컬럼번호는 1번부터 시작
				System.out.println("lprod_id : " + rs.getInt("lprod_id"));
				System.out.println("lprod_gu : " + rs.getString("lprod_gu"));
				System.out.println("lprod_nm : " + rs.getString("lprod_nm"));
				System.out.println("=====================================");
			}
			
			System.out.println("출력 끝..");
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			// 6. 종료 ( 사용했던 자원을 모두 반납한다.)
			if( rs != null ) try {rs.close();} catch (SQLException e2) {}
			if( stmt != null ) try {stmt.close();} catch (SQLException e2) {}
			if( conn != null ) try {conn.close();} catch (SQLException e2) {}
				
		}
		
	}
}
