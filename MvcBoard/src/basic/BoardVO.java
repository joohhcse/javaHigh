package basic;

//DB 테이블에 있는 컬럼을 기준으로 데이터를 객체화한 클래스
//
//<p>
//DB테이블의 '컬럼'이 이 클래스의 '멤버변수'가 된다 .<br>
//DB테이블의 컬럼과 클래스의 멤버변수를 매핑하는 역할을 수행한다.<br>
//</p>


public class BoardVO {
	private String dbNum;	
	private String dbTitle;	 
	private String dbUser;
	private String dbDate;
	private String dbContent;
	
	public String getDbNum() {
		return dbNum;
	}
	public void setDbNum(String dbNum) {
		this.dbNum = dbNum;
	}
	public String getDbTitle() {
		return dbTitle;
	}
	public void setDbTitle(String dbTitle) {
		this.dbTitle = dbTitle;
	}
	public String getDbUser() {
		return dbUser;
	}
	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}
	public String getDbDate() {
		return dbDate;
	}
	public void setDbDate(String dbDate) {
		this.dbDate = dbDate;
	}
	public String getDbContent() {
		return dbContent;
	}
	public void setDbContent(String dbContent) {
		this.dbContent = dbContent;
	}
	@Override
	public String toString() {
		return "BoardVO [dbNum=" + dbNum + ", dbTitle=" + dbTitle + ", dbUser=" + dbUser + ", dbDate=" + dbDate
				+ ", dbContent=" + dbContent + "]";
	}

	
	
}
