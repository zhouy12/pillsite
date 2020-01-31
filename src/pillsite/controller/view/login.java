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
import pillsite.Main;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.Optional;

import javax.swing.JOptionPane;
import pillsite.DatabaseConnectionService;

	public class login  {

	@FXML private TextField Username;
	@FXML private TextField Password;
	Stage storyStage;


	@FXML
	public void handleLoginButton(ActionEvent event) throws Exception {
		storyStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		System.out.println(Username.getText());
		System.out.println(Password.getText());
		Node node=(Node) event.getSource();
		Stage stage=(Stage) node.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("ListTest.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
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
