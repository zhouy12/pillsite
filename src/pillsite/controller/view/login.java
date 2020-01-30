package pillsite.controller.view;

import javafx.application.Platform;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.*;
import javafx.stage.*;
import pillsite.DatabaseConnectionService;
import pillsite.LoginService;

import java.io.IOException;
import java.util.Optional;


public class login  {

//	DatabaseConnectionService dbService = "jdbc:sqlserver://"+ +";databaseName="+this.databaseName+";user="+user+";password="+pass;


	@FXML
	public void handleLoginButton(ActionEvent event) throws Exception {
//		LoginService ls = new LoginService(DatabaseConnectionService dbService);
	}
 
	@FXML
	public void handleLoginOnKeyPressed(KeyEvent event) throws IOException {
		
	}
	
	@FXML
	public void handleRegisterButton(ActionEvent event) throws Exception {
		
	}
 
	@FXML
	public void handleRegisterOnKeyPressed(KeyEvent event) throws IOException {
		
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
