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
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.Optional;

import javax.swing.JOptionPane;

import pillsite.DatabaseConnectionService;

	public class login  {
	DatabaseConnectionService dbService = null;
	@FXML private TextField Username;
//	DatabaseConnectionService dbService = "jdbc:sqlserver://"+ +";databaseName="+this.databaseName+";user="+user+";password="+pass;
	public login(DatabaseConnectionService dbService) {
		this.dbService = dbService;
	}

	@FXML
	public void handleLoginButton(ActionEvent event) throws Exception {
		System.out.println(Username.getText());
//		LoginService ls = new LoginService(DatabaseConnectionService dbService);
//		try {
//			CallableStatement stmt = null;
//			if(username !=null && password !=null && !username.isEmpty() && !password.isEmpty()) {
//			stmt = dbService.getConnection().prepareCall("{call Register(?,?,?)}");
//			byte[] salt = this.getNewSalt();
//			String hash = this.hashPassword(salt, password);
//			stmt.setString(1, username);
//			stmt.setBytes(2, salt);
//			stmt.setString(3, hash);
//			stmt.execute();
//			return true;
//			}
//			else {
//				JOptionPane.showMessageDialog(null, "password and username cannot be null.");
//				return false;
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//			JOptionPane.showMessageDialog(null, "register not implemented.");
//			return false;
//		}
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
