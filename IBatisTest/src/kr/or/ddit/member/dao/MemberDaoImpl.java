package kr.or.ddit.member.dao;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.member.MemberMain;
import kr.or.ddit.member.vo.MemberVO;
//import kr.or.ddit.util.DBUtil2;
//import kr.or.ddit.util.DBUtil3;

public class MemberDaoImpl implements IMemberDao {
//	// Log4j를 이용한 로그남기기 위한 로거 생성
//	private static final Logger sqlLogger = Logger.getLogger("log4jexam.sql.Query");
//	private static final Logger paramLogger = Logger.getLogger("log4jexam.sql.Parameter");
//	private static final Logger resultLogger = Logger.getLogger(MemberMain.class);

	private static MemberDaoImpl dao;
	
	private SqlMapClient smc;
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private MemberDaoImpl() {
		Charset charset = Charset.forName("UTF-8");
		Resources.setCharset(charset);
		Reader rd;

		try {
			rd = Resources.getResourceAsReader("SqlMapConfig.xml");
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
		}
		catch(IOException e) {
			System.out.println("SqlMapClient 객체 생성 실패!");
			e.printStackTrace();
		}
		
	}
	
	public static MemberDaoImpl getInstance() {
		if(dao == null)
		{
			dao = new MemberDaoImpl();
		}
		
		return dao;
	}

	@Override
	public int insertMember(MemberVO mv) {
		int cnt = 0;
		
		try {
			Object obj = smc.insert("member.insertMember", mv);
			if(obj == null) //Success
			{
				cnt = 1;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}

		return cnt;
	}

	@Override
	public boolean getMember(String memId) {
		boolean chk = false;
		
		int cnt = 0;
		
		try {
			cnt = (int)smc.queryForObject("member.getMember", memId);
			
			if(cnt > 0)
			{
				chk = true;
			}
		} 
		catch(SQLException e) {
			e.printStackTrace();
			chk = false;
		}
		return chk;
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		List<MemberVO> memList = new ArrayList<MemberVO>();
		
		try {
			memList = smc.queryForList("member.getMemberAll");
			
//			while(rs.next())
//			{
//				MemberVO mv = new MemberVO();
//				mv.setMem_id(rs.getString("mem_id"));
//				mv.setMem_name(rs.getString("mem_name"));
//				mv.setMem_tel(rs.getString("mem_tel"));
//				mv.setMem_addr(rs.getString("mem_addr"));
//				
//				memList.add(mv);
//			}
//			System.out.println("=========================================");
//			System.out.println("=================출력작업 끝=================");
		}
		catch(SQLException e) {
			System.out.println("회원자료 가져오기 실패!");
			e.printStackTrace();
		}
		return memList;
	}

	@Override
	public int updateMember(MemberVO mv) {
		int cnt = 0;
		
		try {
			cnt = smc.update("member.updateMember", mv);
			
//			conn = DBUtil2.getConnection();
//			
//			String sql = "UPDATE mymember SET mem_name = ?, "
//						+ " mem_tel = ?, "
//						+ " mem_addr = ?"
//						+ " WHERE mem_id = ?";
//			
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, mv.getMem_name());
//			pstmt.setString(2, mv.getMem_tel());
//			pstmt.setString(3, mv.getMem_addr());
//			pstmt.setString(4, mv.getMem_id());
//			
//			cnt = pstmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		int cnt = 0;
		
		try {
			cnt = smc.delete("member.deleteMember", memId);
			
//			conn = DBUtil2.getConnection();
//			String sql = "DELETE FROM mymember WHERE mem_id = ?";
//			
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, memId);
//			
//			cnt = pstmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
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
	public List<MemberVO> getSearchMember(MemberVO mv) {
		List<MemberVO> memList = new ArrayList<>();
		
		//동적으로 쿼리를 만들어 준다
		try {
			memList = smc.queryForList("member.getSearchMember", mv);
			
//			conn = DBUtil3.getConnection();
//			
//			String sql = "SELECT * FROM mymember WHERE 1=1";
//			
//			if(mv.getMem_id() != null && !mv.getMem_id().equals("")) {
//				sql += " AND mem_id = ?";
//			}
//			if(mv.getMem_name() != null && !mv.getMem_name().equals("")) {
//				sql += " AND mem_name = ?";
//			}
//			if(mv.getMem_tel() != null && !mv.getMem_tel().equals("")) {
//				sql += " AND mem_tel = ?";
//			}
//			if(mv.getMem_addr() != null && !mv.getMem_addr().equals("")) {
//				sql += " AND mem_addr like '%' || ? || '%' ";
//			}
//			
//			pstmt = conn.prepareStatement(sql);
//			
//			int index = 1;
//			
//			if(mv.getMem_id() != null && !mv.getMem_id().equals("")) {
//				pstmt.setString(index++, mv.getMem_id());
//			}
//			if(mv.getMem_name() != null && !mv.getMem_name().equals("")) {
//				pstmt.setString(index++, mv.getMem_name());
//			}
//			if(mv.getMem_tel() != null && !mv.getMem_tel().equals("")) {
//				pstmt.setString(index++, mv.getMem_tel());
//			}
//			if(mv.getMem_addr() != null && !mv.getMem_addr().equals("")) {
//				pstmt.setString(index++, mv.getMem_addr());
//			}
//
//			rs = pstmt.executeQuery();
//			
//			while(rs.next())
//			{
//				MemberVO memVO = new MemberVO();
//				memVO.setMem_id(rs.getString("mem_id"));
//				memVO.setMem_name(rs.getString("mem_name"));
//				memVO.setMem_tel(rs.getString("mem_tel"));
//				memVO.setMem_addr(rs.getString("mem_addr"));
//				
//				memList.add(memVO);
//			}
		}
		catch (SQLException e) {
			memList = null;
			e.printStackTrace();
		}
		
		return memList;
	}
	
}






