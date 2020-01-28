import javafx.application.Platform;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.*;
import javafx.stage.*;

import java.io.IOException;
import java.util.Optional;


public class login  {

	Stage storyStage;

	@FXML
	public void handleStartButton(ActionEvent event) throws Exception {
		storyStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		roomSelect select = new roomSelect();
		try {
			select.selectRoom(storyStage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
	@FXML
	public void handleStartOnKeyPressed(KeyEvent event) throws IOException {
		if (event.getCode() == KeyCode.ENTER) {
			storyStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			roomSelect select = new roomSelect();
			try {
				select.selectRoom(storyStage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	public void handleExitButton(ActionEvent event) {
		System.exit(0);
	}
 
	@FXML
	public void handleExitOnKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			System.exit(0);
		}
	}

}
