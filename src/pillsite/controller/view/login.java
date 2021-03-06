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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;

import javax.swing.JOptionPane;
import pillsite.DatabaseConnectionService;

	public class login  {

	@FXML private TextField Username;
	@FXML private TextField Password;
	Stage storyStage;
	
	public boolean login(String username, String password) {
		 try {
	            PreparedStatement stmt = null;
	            String query = "SELECT Password,ID FROM [PillSite].[dbo].[Person] WHERE UserName = ?";
	            stmt = Main.getDcs().getConnection().prepareStatement(query);
	            stmt.setString(1, username);
	            
	            ResultSet rs = stmt.executeQuery();
	            String pword = "";
	            int iden = 0;
	            while (rs.next()) {
	    	        pword  = rs.getString("Password");
	    	        iden = rs.getInt("ID");
	    	    }
	            if(pword.equals(password)) {
	            	Main.setID(iden);
	            	return true;
	            }
	            JOptionPane.showMessageDialog(null, "Login Failed.");
	            return false;
	    	    
		 }
	        catch (SQLException ex) {
	            JOptionPane.showMessageDialog(null, "Login Failed.");
	            ex.printStackTrace();
	            return false;
	        }

	}



	@FXML
	public void handleLoginButton(ActionEvent event) throws Exception {
		boolean loog = login(Username.getText(), Password.getText());
		if(loog == true && !Username.getText().isEmpty()) {
			storyStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			System.out.println(Username.getText());
			System.out.println(Password.getText());
			Node node=(Node) event.getSource();
			Stage stage=(Stage) node.getScene().getWindow();
		    Statement stmt = null;	
		    ArrayList<String> id = new ArrayList<>();
		    String query = "SELECT ID FROM [PillSite].[dbo].[Doctor] WHERE ID = " + Main.getID();
		    stmt = Main.getDcs().getConnection().createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    while (rs.next()) {
		        String name = rs.getString("ID");
		        id.add(name);
		    }
			Parent root = null;
			if(id.isEmpty()) {
				root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
			}
			else {
				root = FXMLLoader.load(getClass().getResource("DoctorMainMenu.fxml"));
			}
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	}
 
	@FXML
	public void handleLoginOnKeyPressed(KeyEvent event) throws IOException {
		if (event.getCode() == KeyCode.ENTER) {
			storyStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			System.out.println(Username.getText());
			System.out.println(Password.getText());
			Node node=(Node) event.getSource();
			Stage stage=(Stage) node.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		
	}
	
	@FXML
	public void handleRegisterButton(ActionEvent event) throws Exception {
		storyStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		System.out.println(Username.getText());
		System.out.println(Password.getText());
		Node node=(Node) event.getSource();
		Stage stage=(Stage) node.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("ChooseClass.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
 
	@FXML
	public void handleRegisterOnKeyPressed(KeyEvent event) throws IOException {
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
