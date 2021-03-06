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

	public class DoctorRegister  {
	Stage storyStage;


	@FXML
	public void handleFinishButton(ActionEvent event) throws Exception {
		storyStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Node node=(Node) event.getSource();
		Stage stage=(Stage) node.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
 
	@FXML
	public void handleFinishOnKeyPressed(KeyEvent event) throws IOException {
		if (event.getCode() == KeyCode.ENTER) {
			storyStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			Node node=(Node) event.getSource();
			Stage stage=(Stage) node.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		
	}

	@FXML
	public void handleGobackButton(ActionEvent event) throws IOException {
		storyStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Node node=(Node) event.getSource();
		Stage stage=(Stage) node.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("ChooseClass.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
 
	@FXML
	public void handleGobackKeyPressed(KeyEvent event) throws IOException {
		if (event.getCode() == KeyCode.ENTER) {
			storyStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			Node node=(Node) event.getSource();
			Stage stage=(Stage) node.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("ChooseClass.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	}

}
