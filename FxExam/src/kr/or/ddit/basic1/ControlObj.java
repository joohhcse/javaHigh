package kr.or.ddit.basic1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ControlObj extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		// fxml 파일 가져오기
		Parent root = FXMLLoader.load(getClass().getResource("control1.fxml"));

		Scene scene = new Scene(root);
		primaryStage.setTitle("control1");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
