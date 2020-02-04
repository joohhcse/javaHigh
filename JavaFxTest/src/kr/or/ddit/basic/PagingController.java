package kr.or.ddit.basic;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class PagingController implements Initializable{

	@FXML TableView<MemberVO> tableView;
	@FXML TableColumn<MemberVO, String> id;
	@FXML TableColumn<MemberVO, String> name;
	@FXML TableColumn<MemberVO, String> addr;
	@FXML Pagination pagination;
	
	private int from, to, itemsForPage;
	
	private ObservableList<MemberVO> allTableData, currentPageData;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		addr.setCellValueFactory(new PropertyValueFactory<>("address"));
		
		// 전체 테이블 데이터 설정
		allTableData = FXCollections.observableArrayList();
		
		allTableData.add(new MemberVO("1", "홍길동1", "대전1"));
		allTableData.add(new MemberVO("2", "홍길동2", "대전2"));
		allTableData.add(new MemberVO("3", "홍길동3", "대전3"));
		allTableData.add(new MemberVO("4", "홍길동4", "대전4"));
		allTableData.add(new MemberVO("5", "홍길동5", "대전5"));
		allTableData.add(new MemberVO("6", "홍길동6", "대전6"));
		allTableData.add(new MemberVO("7", "홍길동7", "대전7"));
		allTableData.add(new MemberVO("8", "홍길동8", "대전8"));
		allTableData.add(new MemberVO("9", "홍길동9", "대전9"));
		allTableData.add(new MemberVO("10", "홍길동10", "대전10"));
		allTableData.add(new MemberVO("11", "홍길동11", "대전11"));
		allTableData.add(new MemberVO("12", "홍길동12", "대전12"));
		allTableData.add(new MemberVO("13", "홍길동13", "대전13"));
		allTableData.add(new MemberVO("14", "홍길동14", "대전14"));
		
		itemsForPage = 5;	//한페이지에 보여줄 할목 수 설정
		int totPageCount = allTableData.size()%itemsForPage == 0 
				? allTableData.size() / itemsForPage : allTableData.size() / itemsForPage + 1;
				
		pagination.setPageCount(totPageCount); // 전체페이지 수 설정		
		
		pagination.setPageFactory(new Callback<Integer, Node>(){
			
			@Override
			public Node call(Integer pageIndex) {
				from = pageIndex * itemsForPage;
				to = from + itemsForPage - 1;
				tableView.setItems(getTableViewData(from, to));
				
				System.out.println("from > " + from + " || to > " + to);
				
				return tableView;
			}
		});
	}
	
	//TableView에 채워줄 데이터를 가져오는 메서드
	protected ObservableList<MemberVO> getTableViewData(int from, int to) {
		//현재 페이지 데이터 초기화
		currentPageData = FXCollections.observableArrayList();
		
		int totSize = allTableData.size();
		for(int i = from ; i <= to && i < totSize; i++)
		{
			currentPageData.add(allTableData.get(i));
		}
		
		return currentPageData;
	}
	
	public class MemberVO {
		private String id;
		private String name;
		private String address;
		
		public MemberVO(String id, String name, String address) {
			super();
			this.id = id;
			this.name = name;
			this.address = address;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}
		
		
	}
}


