package basic;

import java.util.List;

public class BoardServiceImpl implements IBoardService {
	// 사용할 Dao의 객체변수를 선언한다.
	private IBoardDao memDao;
	
	public BoardServiceImpl() {
		memDao = new BaordDaoImpl();
	}

	@Override
	public int insertBoard(BoardVO bv) {
		return memDao.insertBoard(bv);
	}

	@Override
	public boolean getBoard(String dbNum) {
		return memDao.getBoard(dbNum);
	}

	@Override
	public List<BoardVO> getAllBoardList() {
		return memDao.getAllBoardList();
	}

	@Override
	public int updateBoard(BoardVO bv) {
		return memDao.updateBoard(bv);
	}

	@Override
	public int deleteBoard(String dbNum) {
		int cnt = memDao.deleteBoard(dbNum);
		if(cnt > 0)
		{
			// 관리자에게 메일 발송하기..
		}
		return cnt;
	}

	@Override
	public List<BoardVO> getSearchBoard(BoardVO bv) {
		return memDao.getSearchBoard(bv);
	}

	
}
