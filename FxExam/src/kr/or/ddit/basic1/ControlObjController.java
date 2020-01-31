package kr.or.ddit.basic1;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.event.ActionEvent;

public class ControlObjController implements Initializable {

	@FXML RadioButton radio_male;
	@FXML RadioButton radio_female;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}

	@FXML public void maleClicked(ActionEvent event) {
		if(radio_female.isSelected())
			radio_male.setSelected(false);
		else if(radio_male.isSelected())
			radio_male.setSelected(true);
	}

	@FXML public void femaleClicked(ActionEvent event) {
		if(radio_female.isSelected())
			radio_male.setSelected(false);
		else if(radio_male.isSelected())
			radio_male.setSelected(true);
	}
	
}
