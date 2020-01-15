package basic;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import util.DBUtil2;

/*
	회원정보를 관리하는 프로그램을 작성하는데 
	아래의 메뉴를 모두 구현하시오. (CRUD기능 구현하기)
	(DB의 MYMEMBER테이블을 이용하여 작업한다.)
	
	* 자료 삭제는 회원ID를 입력 받아서 삭제한다.
	
	예시메뉴)
	----------------------
		== 작업 선택 ==
		1. 자료 입력			---> insert
		2. 자료 삭제			---> delete
		3. 자료 수정			---> update
		4. 전체 자료 출력	---> select
		5. 작업 끝.
	----------------------
	 
	   
// 회원관리 프로그램 테이블 생성 스크립트 
create table mymember(
    mem_id varchar2(8) not null,  -- 회원ID
    mem_name varchar2(100) not null, -- 이름
    mem_tel varchar2(50) not null, -- 전화번호
    mem_addr varchar2(128)    -- 주소
);

*/
public class BoardMain {
	// Service객체 변수를 선언한다.
	private IBoardService memService;
	
	public BoardMain() {
		memService = new BoardServiceImpl();
	}
	
	private Scanner scan = new Scanner(System.in); 
	
	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu(){
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 자료 입력");
		System.out.println("  2. 자료 삭제");
		System.out.println("  3. 자료 수정");
		System.out.println("  4. 전체 자료 출력");
		System.out.println("  5. 검색 자료 출력");
		System.out.println("  6. 작업 끝.");
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
				case 1 :  // 자료 입력
					insertBoard();
					break;
				case 2 :  // 자료 삭제
					deleteBoard();
					break;
				case 3 :  // 자료 수정
					updateBoard();
					break;
				case 4 :  // 전체 자료 출력
					displayBoardAll();
					break;
				case 5 :  // 검색 자료 출력
					searchBoard();
					break;
				case 6 :  // 작업 끝
					System.out.println("작업을 마칩니다.");
					
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice!=6);
		
		System.exit(0);
	}
	
	// 회원정보를 삭제하는 메서드(입력받은 회원 ID를 이용하여 삭제한다)
	private void deleteBoard() {
		System.out.println();
		System.out.println("삭제할 글 번호를 입력하세요");
		String memId = scan.next();
		
		int cnt = memService.deleteBoard(memId);
		
		if(cnt > 0) {
			System.out.println(memId + "글 삭제 성공...");
		}
		else {
			System.out.println(memId + "글 삭제 실패!!!");
		}
	}

	// 회원정보를 수정하는 메서드
	private void updateBoard() {
		System.out.println();
		String dbNum = "";
		boolean chk = true;
		
		do {
			System.out.print("수정할  글번호를 입력하세요 >> ");
			dbNum = scan.next();
			
			chk = getBoard(dbNum);
			
			if(chk == false)
			{
				System.out.println(dbNum + " > 글은 없는 글입니다!");
				System.out.println("수정할 자료가 없으니 다시 입력하세요");
			}
		} while(chk == false);
		
		System.out.println("수정할  글제목을 입력하세요");
		System.out.print("새로운 글제목 >> ");
		String dbTitle =scan.next();
		
		System.out.println("작성자 입력 >> ");
		String dbUser = scan.next();
		
		scan.nextLine();	//버퍼 지우기
		System.out.print("내용 입력 >> ");
		String dbContent = scan.nextLine();
		
		BoardVO bv = new BoardVO();
		bv.setDbNum(dbNum);
		bv.setDbTitle(dbTitle);
		bv.setDbUser(dbUser);
//		bv.setDbDate(dbDate);
		bv.setDbContent(dbContent);
		
		int cnt = memService.updateBoard(bv);
		
		if( cnt > 0 ) {
			System.out.println(dbNum + "글 수정 완료...");
		}
		else {
			System.out.println(dbNum + "글 수정 실패!!!");
		}
	}

	// 전체 회원을 출력하는 메서드
	private void displayBoardAll() {
		System.out.println();
		System.out.println("=========================================");
		System.out.println(" 글번호\t제목\t작성자\t날짜\t글 내용");
		System.out.println("=========================================");
		
		List<BoardVO> memList = memService.getAllBoardList();
		
		if(memList.size()==0) {
			System.out.println("출력할 회원정보가 없습니다.");
		}
		else {
			for(BoardVO bv2 : memList) {
				System.out.println(bv2.getDbNum() + "\t" 
						   + bv2.getDbTitle() + "\t"
						   + bv2.getDbUser() +  "\t"
						   + bv2.getDbDate() +  "\t"
						   + bv2.getDbContent() );
			}
			
		}
		
		System.out.println("=========================================");
		System.out.println("===============출력 작업 끝===================");
		
		
	}

	// 회원 추가하는 메서드
	private void insertBoard() {
		boolean chk = false;
		String dbNum = "";
		do {
			System.out.println();
			System.out.println("추가할 글 정보를 입력하세요.");
			System.out.println("글 번호 >> ");
			dbNum = scan.next();
			
			chk = getBoard(dbNum);
			
			if(chk)
			{
				System.out.println("글 번호가 " + dbNum + "인 글은 이미 존재합니다");
				System.out.println("다시 입력하세요>");
			}
			
		} while(chk == true);
		
		System.out.println("글 제목 >> ");
		String dbTitle = scan.next();
		
		System.out.println("작성자 이름 >> ");
		String dbUser = scan.next();
		
		scan.nextLine();
		System.out.println("글 내용 >> ");
		String dbContent = scan.nextLine();
		
		// 입력받은 정보를 VO객체에 넣는다
		BoardVO bv = new BoardVO();
		bv.setDbNum(dbNum);
		bv.setDbTitle(dbTitle);
		bv.setDbUser(dbUser);
		bv.setDbContent(dbContent);
		
		int cnt = memService.insertBoard(bv);
		
		if(cnt > 0) {
			System.out.println(dbNum + "글 추가 작업 성공");
		}
		else {
			System.out.println(dbNum + "글 추가 작업 실패!");
		}
	}
	
	// 회원 ID를 이용하여 회원이 있는지 알려주는 메서드
	private boolean getBoard(String memId) {
		boolean chk = false;
		
		chk = memService.getBoard(memId);
		
		return chk;
	}
	
	public void searchBoard() {
		// 검색할 회원ID, 회원이름, 전화번호, 주소등을 입력하면 입력한 정보만 사용하여 검색하는 기능을 구현하시오.
		// 주소는 입력한 값이 포함만 되어도 검색되도록 한다.
		// 입력을 하지 않을 자료는 엔터키로 다음 입력으로 넘긴다.
		
		scan.nextLine(); // 입력버퍼 지우기
		System.out.println();
		System.out.println("검색할 정보를 입력하세요.");
		System.out.println("글 번호 >> ");
		String dbNum = scan.nextLine().trim();
		
		System.out.println("글 제목 >> ");
		String dbTitle = scan.nextLine().trim();
		
		System.out.println("작성자 >> ");
		String dbUser = scan.nextLine().trim();

		System.out.println("글 내용 >> ");
		String dbContent = scan.nextLine().trim();
		
		BoardVO mv = new BoardVO();
		mv.setDbNum(dbNum);
		mv.setDbTitle(dbTitle);
		mv.setDbUser(dbUser);
		mv.setDbContent(dbContent);
		
		System.out.println();
		System.out.println("=========================================");
		System.out.println(" 글번호\t글 제목\t작성자\t글 내용");
		System.out.println("=========================================");
		
		List<BoardVO> memList = memService.getSearchBoard(mv);
//		List<MemberVO> memList = memService.getAllMemberList();
		
		if(memList.size()==0) {
			System.out.println("출력할 회원정보가 없습니다.");
		}
		else {
			for(BoardVO mv2 : memList) {
				System.out.println(mv2.getDbNum() + "\t" 
						   + mv2.getDbTitle() + "\t"
						   + mv2.getDbUser() +  "\t"
						   + mv2.getDbContent() );
			}
		}
		System.out.println("=========================================");
		System.out.println("===============출력 작업 끝===================");
	}
	
	public static void main(String[] args) {
		BoardMain memObj = new BoardMain();
		memObj.start();
	}
}






