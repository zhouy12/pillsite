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

	public class AddNew  {
		@FXML private TextField SN;
		@FXML private TextField lname;
		@FXML private TextField fname;
		@FXML private TextField age;
		@FXML private TextField mname;
		Stage storyStage;



	@FXML
	public void handleFinishButton(ActionEvent event) throws Exception {
//		CallableStatement cs = null;
//
//		try {
//			String pass1 = pword.getText();
//			String pass2 = pword2.getText();
//				cs = Main.getDcs().getConnection().prepareCall("{call [PillSite].[dbo].registerPatient(?,?,?,?,?,?,?,?)}");
//				cs.setString(1, uname.getText());
//				cs.setString(2, pword.getText());
//				cs.setString(3,  age.getText());
//				cs.setString(4, fname.getText());
//				cs.setString(5, mname.getText());
//				cs.setString(6,  lname.getText());
//				cs.setString(7,  ssn.getText());
//				cs.setString(8,  ssn.getText());
//				cs.execute();
//				JOptionPane.showMessageDialog(null, "Registration Complete");
//				storyStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//				Node node=(Node) event.getSource();
//				Stage stage=(Stage) node.getScene().getWindow();
//				Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
//				Scene scene = new Scene(root);
//				stage.setScene(scene);
//				stage.show();
//		} catch (SQLException e) {
//			JOptionPane.showMessageDialog(null, "Registration Failed");
//			e.printStackTrace();
//		}

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
