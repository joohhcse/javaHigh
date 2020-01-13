package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;


public class T04_jdbcTest {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		
		try {
			
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			conn = DriverManager.getConnection(
//					"jdbc:oracle:thin:@localhost:1521:xe", 
//					"joo", 
//					"java");
			
			// DBUtil 에서 가져오기
			conn = DBUtil.getConnection();
						
			// lprod_id의 최대값을 가져와서 1 증가시키기
			stmt = conn.createStatement();
			String sql = "SELECT MAX(lprod_id) FROM lprod";
			//String sql = "SELECT MAX(lprod_id) maxNum FROM lprod";
			rs = stmt.executeQuery(sql);
			int num = 0;
			if(rs.next()){
				//num = rs.getInt(1);  // 컬럼번호로 가져오기
				//num = rs.getInt("maxNum"); // 엘리어스가 있을 때
				num = rs.getInt("MAX(lprod_id)");  // 엘리어스가 없을 때
			}
			num++;
			
			int count;
			String sql3 = "SELECT COUNT(*) cnt FROM lprod "
					+ " WHERE lprod_gu = ?";
			pstmt = conn.prepareStatement(sql3);
			String gu = null;
			do{
				
				System.out.print("상품 분류코드(LPROD_GU) 입력 : ");
				gu = scan.next();
				
				pstmt.setString(1, gu);
				
				rs = pstmt.executeQuery();
				count = 0;
				if(rs.next()){
					count = rs.getInt("cnt");
				}
				if(count>0){
					System.out.println("상품 분류 코드 " + gu + "은(는) "
							+ "이미 있는 상품입니다.");
					System.out.println("다시 입력하세요.");
					System.out.println();
				}
				
			}while(count>0);
			
			System.out.print("상품 분류명(LPROD_NM) 입력 : ");
			String nm = scan.next();
			
			scan.close();
			
			String sql2 = "INSERT INTO lprod (lprod_id, lprod_gu, lprod_nm) "
					+ " VALUES (?, ?, ?)";
			pstmt2 = conn.prepareStatement(sql2);
			
			pstmt2.setInt(1, num);
			pstmt2.setString(2, gu);
			pstmt2.setString(3, nm);
			
			int cnt = pstmt2.executeUpdate();
			if(cnt>0){
				System.out.println(gu + "를 추가했습니다.");
			}else{
				System.out.println(gu + "를 추가하는데 실패했습니다.");
			}
			
			
		} 
//		catch (ClassNotFoundException e) {
//			System.out.println("드라이버 로딩 실패!!");
//		}
		catch (SQLException e){
			e.printStackTrace();
		} finally {
			//  사용했던 자원 반납
			if(rs!=null)try{ rs.close(); }catch(SQLException ee){}
			if(stmt!=null)try{ stmt.close(); }catch(SQLException ee){}
			if(pstmt!=null)try{ pstmt.close(); }catch(SQLException ee){}
			if(pstmt2!=null)try{ pstmt2.close(); }catch(SQLException ee){}
			if(conn!=null)try{ conn.close(); }catch(SQLException ee){}
		}
	}
}

//public class T04_jdbcTest {
//
//	/*
//	 * lprod 테이블에 새로운 데이터를 추가하기
//	 * 
//	 * 	lprod_gu의 lprod_nm은 직접 입력받아 처리하고
//	 *  lprod_id는 현재의 lprod_id중 제일 큰 값보다 1증가된 값으로 한다
//	 *  (기타사항 : lprod_gu도 중복되는지 검사한다)
//	 */
//	
//	public static void main(String[] args) {
//		Connection conn = null;
//		Statement stmt = null;
//		PreparedStatement pstmt = null;
//		PreparedStatement pstmt2 = null;
//		ResultSet rs = null;
//		
//		Scanner sc = new Scanner(System.in);
//		
//		try {
//			// 드라이버 등록
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			
//			// Connection객체 생성
//			String url = "jdbc:oracle:thin:@localhost:1521/xe";
//			String userId = "joo";
//			String password = "java";
//			conn = DriverManager.getConnection(url, userId, password);
//			
//			stmt = conn.createStatement();
//			String sql = "SELECT MAX(lprod_id) FROM lprod";
//			
//			rs = stmt.executeQuery(sql);
//			int num = 0;
//			if(rs.next())
//			{
//				num= rs.getInt("MAX(lprod_id)");
//			}
//			num++;
//			
//			int count ;
//			String sql3 = "SELECT COUNT(*) cnt FROM lprod"
//					+  " WHERE lprod_gu = ?";
//			pstmt = conn.prepareStatement(sql3);
//			String gu =null;
//			
//			do {
//				System.out.println("상품 분류코드 > ");
//				
//				gu = sc.next();
//						
//				pstmt.setString(1, gu);
//				
//				rs = pstmt.executeQuery();
//				count = 0;
//				if(rs.next())
//				{
//					count = rs.getInt("cnt");
//				}
//				
//				if(count > 0)
//				{
//					System.out.println("이미있는 상품입니다.");
//					System.out.println("다시 입력하세요");
//				}
//			} while(count > 0);
//			
//			System.out.println("LPROD_NM 입력 >>");
//			
//			String nm = sc.next();
//			
//			sc.close();
//			
//			String sql2 = "INSERT INTO lprod (lprod_id, lprod_gu,"
//			
//			
////			
////			String lprod_gu = "' OR 1 = 1 --";	// 사용자 입력값
////			
//////			System.out.println(" 실행할 쿼리 : " + sql);
////			
////			Scanner sc = new Scanner(System.in);
////			
////			String lprod_nm = sc.nextLine();
////			
////			String sql = "INSERT INTO lprod VALUES(" + lprod_nm;
////					
////			rs = stmt.executeQuery(sql);
////			
////			// ResultSet 객체에 저장된 자료를 출력한다
////			System.out.println("=== 쿼리문 실행결과 ===");
////			
////			// rs.next() -> ResultSet객체의 데이터를 가리키는 포인터를 다음 레코드로 이동시키고 그 곳에 자료가 있으면 true, 없으면 false를 반환한다
////			while(rs.next()) 
////			{
////				// 컬럼의 자료를 가져오는 방법
////				// 방법1) rs.get자료형이름("컬럼명")
////				// 방법2) rs.get자료형이름(컬럼번호) -> 컬럼번호는 1번부터 시작
////				System.out.println("lprod_id : " + rs.getInt("lprod_id"));
////				System.out.println("lprod_gu : " + rs.getString("lprod_gu"));
////				System.out.println("lprod_nm : " + rs.getString("lprod_nm"));
////				System.out.println("=====================================");
////			}
////			
////			
////			System.out.println("작업 끝!!!");
//		}
//		catch(ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		finally {
//			// 6. 종료 ( 사용했던 자원을 모두 반납한다.)
//			if( rs != null ) try {rs.close();} catch (SQLException e2) {}
//			if( pstmt != null ) try {pstmt.close();} catch (SQLException e2) {}
//			if( stmt != null ) try {stmt.close();} catch (SQLException e2) {}
//			if( conn != null ) try {conn.close();} catch (SQLException e2) {}
//				
//		}
//
//	}
//
//}
