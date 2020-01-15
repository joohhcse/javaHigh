package basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil2;
import util.DBUtil3;

public class BaordDaoImpl implements IBoardDao {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	@Override
	public int insertBoard(BoardVO bv) {
		
		int cnt = 0;
		
		try {
			conn = DBUtil2.getConnection();
			String sql = "INSERT INTO jdbc_board VALUES (board_seq.nextval, ?, ?, SYSDATE, ?)";
			
			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, bv.getDbNum());
			pstmt.setString(1, bv.getDbTitle());
			pstmt.setString(2, bv.getDbUser());
			pstmt.setString(3, bv.getDbContent());
			
			cnt = pstmt.executeUpdate();
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			disConnect(); //자원 반납
		}

		return cnt;
	}

	@Override
	public boolean getBoard(String dbNum) {
		boolean chk = false;
		
		try {
			conn = DBUtil2.getConnection();
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

	@Override
	public List<BoardVO> getAllBoardList() {
		List<BoardVO> memList = new ArrayList<BoardVO>();
		
		try {
			conn = DBUtil2.getConnection();
			
			String sql = "SELECT * FROM jdbc_board";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				BoardVO bv = new BoardVO();
				bv.setDbNum(rs.getString("board_no"));
				bv.setDbTitle(rs.getString("board_title"));
				bv.setDbUser(rs.getString("board_writer"));
				bv.setDbDate(rs.getString("board_date"));
				bv.setDbContent(rs.getString("board_content"));
				
				memList.add(bv);
			}
//			System.out.println("=========================================");
//			System.out.println("=================출력작업 끝=================");
			
		}
		catch(SQLException e) {
			System.out.println("자료 가져오기 실패!");
			e.printStackTrace();
		}
		finally	{
			disConnect(); //자원 반납
		}
		
		return memList;
	}

	@Override
	public int updateBoard(BoardVO bv) {
		int cnt = 0;
		
		try {
			conn = DBUtil2.getConnection();
			
			String sql = "UPDATE jdbc_board SET board_title = ?, "
						+ " board_writer = ?, "
						+ " board_date = SYSDATE,"
						+ " board_content = ?"
						+ " WHERE board_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bv.getDbTitle());
			pstmt.setString(2, bv.getDbUser());
//			pstmt.setString(3, bv.getDbDate());
			pstmt.setString(3, bv.getDbContent());
			pstmt.setString(4, bv.getDbNum());
			
			cnt = pstmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			disConnect(); //자원 반납
		}
		
		return cnt;
	}

	@Override
	public int deleteBoard(String dbNum) {
		int cnt = 0;
		
		try {
			conn = DBUtil2.getConnection();
			String sql = "DELETE FROM jdbc_board WHERE board_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dbNum);
			
			cnt = pstmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			disConnect();
		}
		
		return cnt;
	}

	// 자원반납용 메서드
	private void disConnect() {
		if(rs!=null)try{ rs.close(); }catch(SQLException ee){}
		if(stmt!=null)try{ stmt.close(); }catch(SQLException ee){}
		if(pstmt!=null)try{ pstmt.close(); }catch(SQLException ee){}
//		if(pstmt2!=null)try{ pstmt2.close(); }catch(SQLException ee){}
		if(conn!=null)try{ conn.close(); }catch(SQLException ee){}
	}

	
	@Override
	public List<BoardVO> getSearchBoard(BoardVO bv) {
		List<BoardVO> boardList = new ArrayList<>();
		
		//동적으로 쿼리를 만들어 준다
		try {
			conn = DBUtil2.getConnection();
			
			String sql = "SELECT * FROM jdbc_board WHERE 1=1";
			
			if(bv.getDbNum() != null && !bv.getDbNum().equals("")) {
				sql += " AND board_no = ?";
			}
			if(bv.getDbTitle() != null && !bv.getDbTitle().equals("")) {
				sql += " AND board_title = ?";
			}
			if(bv.getDbUser() != null && !bv.getDbUser().equals("")) {
				sql += " AND board_writer = ?";
			}
			if(bv.getDbContent() != null && !bv.getDbContent().equals("")) {
				sql += " AND board_content like '%' || ? || '%' ";
			}
			
			pstmt = conn.prepareStatement(sql);
			
			int index = 1;
			
			if(bv.getDbNum() != null && !bv.getDbNum().equals("")) {
				pstmt.setString(index++, bv.getDbNum());
			}
			if(bv.getDbTitle() != null && !bv.getDbTitle().equals("")) {
				pstmt.setString(index++, bv.getDbTitle());
			}
			if(bv.getDbUser() != null && !bv.getDbUser().equals("")) {
				pstmt.setString(index++, bv.getDbUser());
			}
			if(bv.getDbContent() != null && !bv.getDbContent().equals("")) {
				pstmt.setString(index++, bv.getDbContent());
			}

			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				BoardVO boardVO = new BoardVO();
				boardVO.setDbNum(rs.getString("board_no"));
				boardVO.setDbTitle(rs.getString("board_title"));
				boardVO.setDbUser(rs.getString("board_writer"));
				boardVO.setDbContent(rs.getString("board_content"));
				
				boardList.add(boardVO);
			}
		}
		catch (SQLException e) {
			boardList = null;
			e.printStackTrace();
		}
		finally {
			disConnect(); //자원 반납
		}
		
		return boardList;
	}
	
}






