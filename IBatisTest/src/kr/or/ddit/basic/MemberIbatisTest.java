package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class MemberIbatisTest {
	public static void main(String[] args)	{
		// iBatis를 이용하여 DB자료를 처리하는 작업순서
		// 1. iBatis의 환경설정파일을 읽어와 실행시킨다.
		
		try {
			// 1-1. xml문서 일겅오기
			// 설정파일의 인코딩 설정
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			Reader rd = Resources.getResourceAsReader("SqlMapConfig.xml");
			
			// 1-2. d위에서 읽어온 Reader객체를 이용하여 실제 작업을 진행할 객체 생성
			SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close(); // Reader 객체 닫기
			
			// 2. 실행할 SQL문에 맞는 쿼리문을 호출해서 원하는 직업을 수행한다.
			
			// 2-1. INSERT작업 연습
			System.out.println("Insert 작업 시작...");
			
			// 1) 저장할 데이터를 VO에 담는다.
			MemberVO mv = new MemberVO();
			mv.setMem_id("d001");
			mv.setMem_name("김갑환");
			mv.setMem_tel("010-1111-2222");
			mv.setMem_addr("대전시 중구 대흥동");
			
			// 2) SqlMapClient 객체 변수를 이용하여 해당 쿼리문을 실행한다.
			// 형식) smc.insert("namespace값.id값", 파라미터객체);
			// 		반환값 : 성공하면 null 이 반환된다.
			Object obj = smc.insert("memberTest.insertMember", mv);
			if(obj == null) {
				System.out.println("Insert 작업 성공");
			}
			else {
				System.out.println("Insert 작업 실패");
			}
			System.out.println("============================================");
			
			// 2-2. update 작업 연습
			System.out.println("Update 작업 시작...");
			
			MemberVO mv2 = new MemberVO();
			mv2.setMem_id("d001");
			mv2.setMem_name("최번개");
			mv2.setMem_tel("010-2222-2222");
			mv2.setMem_addr("대전시 동구 대동");
			
			// update() 메서드의 반환값은 성공한 레코드 수이다
			int cnt = smc.update("memberTest.updateMember", mv2);
			
			if(cnt > 0)
			{
				System.out.println("Update 작업 성공");
			}
			else
			{
				System.out.println("Update 작업 실패");
			}
			System.out.println("============================================");
			
			// 2-3. delete 작업 연습
			System.out.println("Delete 작업 시작...");
			
			// delete 메서드의 반환값 : 성공한 레코드 수
			
			int cnt2 = smc.delete("memberTest.deleteMember", "d001");
			
			if(cnt2 > 0) {
				System.out.println("Delete 작업 성공");
			}
			else {
				System.out.println("Delete 작업 실패");
			}
			System.out.println("============================================");
			
			// 2-4. select 작업 연습
			// 1) 응답의 결과가 여러개일 경우
			System.out.println("Select 연습 시작 (결과가 여러개인 경우)...");
			List<MemberVO> memList;
			
			// 응답 결과가 여러개일 경우에는 queryForList 메서드를 사용한다.
			// 이 메서드는 여러개의 레코드를 VO에 담은 후 이 VO데이터를 
			// List에 추가해 주는 작업을 자동으로 수행한다.
			memList = smc.queryForList("memberTest.getMemberAll");
			for(MemberVO memVO : memList)
			{
				System.out.println("ID : " + memVO.getMem_id());
				System.out.println("이름 : " + memVO.getMem_name());
				System.out.println("전화 : " + memVO.getMem_tel());
				System.out.println("주소 : " + memVO.getMem_addr());
				
			}
			
			// 2) 응답이 1개일 경우
			System.out.println("select 연습 시작(결과가 1개인 경우...)");
			
			// 응답 결과가 1개가 확실할 경우에는 queryForObject메서드를 사용한다
			MemberVO mv3 = (MemberVO) smc.queryForObject("memberTest.getMember", "002");
			System.out.println("============================================");
			System.out.println("ID : " + mv3.getMem_id());
			System.out.println("이름 : " + mv3.getMem_name());
			System.out.println("전화 : " + mv3.getMem_tel());
			System.out.println("주소 : " + mv3.getMem_addr());
			System.out.println("============================================");
			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
}
