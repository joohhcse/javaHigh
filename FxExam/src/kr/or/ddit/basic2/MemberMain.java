package kr.or.ddit.basic2;

import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;	
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MemberMain extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		ObservableList<Member> data =
				FXCollections.observableArrayList(
					new Member("abc", "김갑환", "2222-2222", "대전")
		);
		
		BorderPane root = new BorderPane();
		
		GridPane grid = new GridPane();
		Text txt1 = new Text("회원ID : ");
		Text txt2 = new Text("회원이름 : ");
		Text txt3 = new Text("회원전화 : ");
		Text txt4 = new Text("회원주소 : ");

		TextField txtMemId = new TextField();
		TextField txtMemName = new TextField();
		TextField txtMemTel = new TextField();
		TextField txtMemAddr = new TextField();
		
		grid.add(txt1, 1, 1);
		grid.add(txtMemId, 2, 1);
		
		grid.add(txt2, 1, 2);
		grid.add(txtMemName, 2, 2);
		
		grid.add(txt3, 1, 3);
		grid.add(txtMemTel, 2, 3);
		
		grid.add(txt4, 1, 4);
		grid.add(txtMemAddr, 2, 4);
		
		grid.setVgap(5);
		grid.setHgap(10);
		grid.setPadding(new Insets(10,0,10,0));
		
		TableView<Member> table = new TableView<>(data); 

		TableColumn<Member, String> idCol = new TableColumn<>("회원ID");
		TableColumn<Member, String> nameCol = new TableColumn<>("회원이름");
		TableColumn<Member, String> telCol = new TableColumn<>("회원전화");
		TableColumn<Member, String> addrCol = new TableColumn<>("회원주소");
		
		idCol.setCellValueFactory(new PropertyValueFactory<>("memId"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("memName"));
		telCol.setCellValueFactory(new PropertyValueFactory<>("memTel"));
		addrCol.setCellValueFactory(new PropertyValueFactory<>("memAddr"));
		
		idCol.setStyle("-fx-alignment: CENTER;");
		idCol.setPrefWidth(100);
		nameCol.setStyle("-fx-alignment: CENTER;");
		nameCol.setPrefWidth(200);
		telCol.setStyle("-fx-alignment: CENTER;");
		telCol.setPrefWidth(200);
		addrCol.setStyle("-fx-alignment: CENTER;");
		addrCol.setPrefWidth(100);
				
		table.getColumns().addAll(idCol, nameCol, telCol, addrCol);
		

		HBox hbox = new HBox(10);
		hbox.setPadding(new Insets(10));
		
		Button btnAdd = new Button("추가");
		Button btnEdit = new Button("수정");
		Button btnDel = new Button("삭제");
		Button btnOk = new Button("확인");
		Button btnCancel = new Button("취소");
		
		btnAdd.setPrefWidth(80);
		btnEdit.setPrefWidth(80);
		btnDel.setPrefWidth(80);
		btnOk.setPrefWidth(80);
		btnCancel.setPrefWidth(80);
		
		btnAdd.setPrefHeight(50);
		btnEdit.setPrefHeight(50);
		btnDel.setPrefHeight(50);
		btnOk.setPrefHeight(50);
		btnCancel.setPrefHeight(50);
		
		btnAdd.setOnAction(e->{
			if(txtMemId.getText().isEmpty() 
				|| txtMemName.getText().isEmpty()	
				|| txtMemTel.getText().isEmpty()	
				|| txtMemAddr.getText().isEmpty()) 
			{
//				System.out.println("빈 항목이 있습니다.");
				errMsg("작업오류", "빈 항목이 있습니다.");
				
				return;
			}
			
			data.add(new Member(txtMemId.getText(), 
								txtMemName.getText(), 
								txtMemTel.getText(),
								txtMemAddr.getText()
								));
//			System.out.println("정보 수정 성공...");
			infoMsg("작업 결과", txtMemName.getText() + "님 정보를 추가했습니다.");
			
			txtMemId.clear();
			txtMemName.clear();
			txtMemTel.clear();
			txtMemAddr.clear();
			
			btnAdd.setDisable(true);
			btnEdit.setDisable(true);
			btnDel.setDisable(true);
			btnOk.setDisable(false);
			btnCancel.setDisable(false);
		});

		btnEdit.setOnAction(e->{
			if(txtMemId.getText().isEmpty() 
					|| txtMemName.getText().isEmpty()	
					|| txtMemTel.getText().isEmpty()	
					|| txtMemAddr.getText().isEmpty()) 
			{
//				System.out.println("빈 항목이 있습니다.");
				errMsg("작업오류", "빈 항목이 있습니다.");
				
				return;
			}

			data.set(table.getSelectionModel().getSelectedIndex(),
					new Member(txtMemId.getText(), 
							txtMemName.getText(), 
							txtMemTel.getText(), 
							txtMemAddr.getText()));
			
//			System.out.println("정보 추가 성공...");
			infoMsg("작업 결과", txtMemName.getText() + "님 정보를 수정했습니다.");
			
			txtMemId.clear();
			txtMemName.clear();
			txtMemTel.clear();
			txtMemAddr.clear();
			
			btnAdd.setDisable(true);
			btnEdit.setDisable(true);
			btnDel.setDisable(true);
			btnOk.setDisable(false);
			btnCancel.setDisable(false);
		});
		
		btnDel.setOnAction(e->{
			if(table.getSelectionModel().isEmpty())
			{
				errMsg("작업 오류", "삭제할 자료를 선택하세요");
				return;
			}
			
			data.remove(table.getSelectionModel().getSelectedIndex());
			
			infoMsg("작업 결과", txtMemName.getText() + "님 정보를 삭제했습니다.");
			
			txtMemId.clear();
			txtMemName.clear();
			txtMemTel.clear();
			txtMemAddr.clear();
			
			btnAdd.setDisable(true);
			btnEdit.setDisable(true);
			btnDel.setDisable(true);
			btnOk.setDisable(false);
			btnCancel.setDisable(false);
		});
		
		btnOk.setOnAction(e->{
			txtMemId.clear();
			txtMemName.clear();
			txtMemTel.clear();
			txtMemAddr.clear();
			
			btnAdd.setDisable(false);
			btnEdit.setDisable(false);
			btnDel.setDisable(false);
			btnOk.setDisable(true);
			btnCancel.setDisable(true);
		});
		
		btnCancel.setOnAction(e->{
			txtMemId.clear();
			txtMemName.clear();
			txtMemTel.clear();
			txtMemAddr.clear();

			btnAdd.setDisable(false);
			btnEdit.setDisable(false);
			btnDel.setDisable(false);
			btnOk.setDisable(true);
			btnCancel.setDisable(true);
		});
		
		// TableView를 클릭했을 때 처리
		table.setOnMouseClicked(e -> {
			// TableView에서 선택한 줄의 데이터를 가져온다
			Member mem = table.getSelectionModel().getSelectedItem();

			if (mem == null)
				return;

			txtMemId.setText(mem.getMemId());
			txtMemName.setText(mem.getMemName());
			txtMemTel.setText(mem.getMemTel());
			txtMemAddr.setText(mem.getMemAddr());
		});
		
		btnOk.setDisable(true);
		btnCancel.setDisable(true);
		
		txtMemId.setPrefWidth(300);
		txtMemName.setPrefWidth(300);
		txtMemTel.setPrefWidth(300);
		txtMemAddr.setPrefWidth(300);

		grid.setAlignment(Pos.CENTER);
		
		hbox.setAlignment(Pos.CENTER);
		hbox.setSpacing(60);
		hbox.getChildren().addAll(btnAdd, btnEdit, btnDel, btnOk, btnCancel);
		
		root.setTop(grid);
		root.setCenter(hbox);
		root.setBottom(table);
//		root.setCenter(grid);

		Scene scene = new Scene(root, 600, 400);

		primaryStage.setTitle("MemberMvcMain");
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void errMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.ERROR);
		errAlert.setTitle("오류");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();
	}
	
	public void infoMsg(String headerText, String msg) {
		Alert infoAlert = new Alert(AlertType.INFORMATION);
		infoAlert.setTitle("정보 확인");
		infoAlert.setHeaderText(headerText);
		infoAlert.setContentText(msg);
		infoAlert.showAndWait();
	}
	
	public class Member {
		private String memId;
		private String memName;
		private String memTel;
		private String memAddr;
		
		public Member(String memId, String memName, String memTel, String memAddr) {
			super();
			this.memId = memId;
			this.memName = memName;
			this.memTel = memTel;
			this.memAddr = memAddr;
		}

		public String getMemId() {
			return memId;
		}

		public void setMemId(String memId) {
			this.memId = memId;
		}

		public String getMemName() {
			return memName;
		}

		public void setMemName(String memName) {
			this.memName = memName;
		}

		public String getMemTel() {
			return memTel;
		}

		public void setMemTel(String memTel) {
			this.memTel = memTel;
		}

		public String getMemAddr() {
			return memAddr;
		}

		public void setMemAddr(String memAddr) {
			this.memAddr = memAddr;
		}
	}
}
