package pillsite.controller.view;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.*;
import javafx.stage.*;
import pillsite.DatabaseConnectionService;
import pillsite.LoginService;
import pillsite.Main;
import pillsite.controller.model.maps.Patients;
import pillsite.controller.model.maps.Pills;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
import pillsite.DatabaseConnectionService;

	public class AddPrescription implements Initializable {
		@FXML private TextField SN;
		@FXML private TextField Number;
		@FXML private TextField Dosage;
		@FXML private TextField TPD;
		@FXML private TextField PatientID;
		Stage storyStage;

	 	@FXML private TableView<Pills> RefTable;
	    @FXML private TableColumn<Pills, String> PName;
	    @FXML private TableColumn<Pills, String> SerN;
	    
	 	@FXML private TableView<Patients> PTable;
	    @FXML private TableColumn<Patients, String> FName;
	    @FXML private TableColumn<Patients, String> LName;
	    @FXML private TableColumn<Patients, String> ID;
	    
	    final ObservableList<Pills> data = FXCollections.observableArrayList(
	    	    );
	    final ObservableList<Patients> data2 = FXCollections.observableArrayList(
	    	    );

	    @Override
	    public void initialize(URL location, ResourceBundle resources) {
	    	try {
				addPills();
				addYourPatients();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    
	    	PName.setCellValueFactory(new PropertyValueFactory<Pills, String>("name"));
	    	SerN.setCellValueFactory(new PropertyValueFactory<Pills, String>("id"));
	    	
	    	RefTable.setItems(data);
	    	RefTable.getColumns().addAll(PName, SerN);
	    	
	    	FName.setCellValueFactory(new PropertyValueFactory<Patients, String>("Fname"));
	    	LName.setCellValueFactory(new PropertyValueFactory<Patients, String>("Lname"));
	    	ID.setCellValueFactory(new PropertyValueFactory<Patients, String>("ID"));
	    	
	    	PTable.setItems(data2);
	    	PTable.getColumns().addAll(FName, LName, ID);
	    }
	    
	    public static boolean isInteger(String str) {
	        if (str == null) {
	            return false;
	        }
	        int length = str.length();
	        if (length == 0) {
	            return false;
	        }
	        int i = 0;
	        if (str.charAt(0) == '-') {
	            if (length == 1) {
	                return false;
	            }
	            i = 1;
	        }
	        for (; i < length; i++) {
	            char c = str.charAt(i);
	            if (c < '0' || c > '9') {
	                return false;
	            }
	        }
	        return true;
	    }
    	
	    private void addPills() throws SQLException {
	    	ArrayList<String> names = getPillName();
	    	ArrayList<String> ids = getPillId();
	    	ArrayList<String> types = getPillType();
	    	for(int i = 0; i < names.size(); i++){
	    		data.add(new Pills(names.get(i), ids.get(i), types.get(i)));
	    	}
	    }
	    
	    private void addYourPatients() throws SQLException {
	    	ArrayList<String> fName = getPatientFName();
	    	ArrayList<String> lName = getPatientLName();
	    	ArrayList<String> ID = getPatientID();
		    Statement stmt = null;	
		    ArrayList<String> names = new ArrayList<>();
		    String query = "SELECT PatientID FROM [PillSite].[dbo].[Belongs] JOIN [PillSite].[dbo].[Patient] ON "
		    		+ " [PillSite].[dbo].[Belongs].PatientID = [PillSite].[dbo].[Patient].ID WHERE DoctorID = " + Main.getID();
		    stmt = Main.getDcs().getConnection().createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    while (rs.next()) {
		        String name = rs.getString("PatientID");
		        names.add(name);
		    }
	    	for(int i = 0; i < ID.size(); i++){
	    		if(names.contains(ID.get(i))) {
	    			data2.add(new Patients(fName.get(i), lName.get(i), ID.get(i)));
	    		}
	    	}
	    }
	    
		public ArrayList<String> getPatientFName() throws SQLException {	
		    Statement stmt = null;	
		    ArrayList<String> id = new ArrayList<>();
		    String query = "SELECT FName FROM [PillSite].[dbo].[Patient] JOIN [PillSite].[dbo].[Person] ON "
		    		+ "[PillSite].[dbo].[Patient].ID = [PillSite].[dbo].[Person].ID";
		    stmt = Main.getDcs().getConnection().createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    while (rs.next()) {
		        String name = rs.getString("FName");
		        id.add(name);
		    }
		    return id;
		}
		
		public ArrayList<String> getPatientLName() throws SQLException {	
		    Statement stmt = null;	
		    ArrayList<String> id = new ArrayList<>();
		    String query = "SELECT LName FROM [PillSite].[dbo].[Patient] JOIN [PillSite].[dbo].[Person] ON "
		    		+ "[PillSite].[dbo].[Patient].ID = [PillSite].[dbo].[Person].ID";
		    stmt = Main.getDcs().getConnection().createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    while (rs.next()) {
		        String name = rs.getString("LName");
		        id.add(name);
		    }
		    return id;
		}
		
		
		public ArrayList<String> getPatientID() throws SQLException {	
		    Statement stmt = null;	
		    ArrayList<String> id = new ArrayList<>();
		    String query = "SELECT ID FROM [PillSite].[dbo].[Patient]";
		    stmt = Main.getDcs().getConnection().createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    while (rs.next()) {
		        String name = rs.getString("ID");
		        id.add(name);
		    }
		    return id;
		}
		
	
	    
		public ArrayList<String> getPillName() throws SQLException {	
		    Statement stmt = null;	
		    ArrayList<String> names = new ArrayList<>();
		    String query = "SELECT PillName FROM [PillSite].[dbo].[Pill]";
		    stmt = Main.getDcs().getConnection().createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    while (rs.next()) {
		        String name = rs.getString("PillName");
		        names.add(name);
		    }
		    return names;
		}
		
		public ArrayList<String> getPillType() throws SQLException {	
		    Statement stmt = null;	
		    ArrayList<String> type = new ArrayList<>();
		    String query = "SELECT Pilltype FROM [PillSite].[dbo].[Pill]";
		    stmt = Main.getDcs().getConnection().createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    while (rs.next()) {
		        String name = rs.getString("Pilltype");
		        type.add(name);
		    }
		    return type;
		}
		
		public ArrayList<String> getPillId() throws SQLException {	
		    Statement stmt = null;	
		    ArrayList<String> id = new ArrayList<>();
		    String query = "SELECT SerialNumber FROM [PillSite].[dbo].[Pill]";
		    stmt = Main.getDcs().getConnection().createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    while (rs.next()) {
		        String name = rs.getString("SerialNumber");
		        id.add(name);
		    }
		    return id;
		}
		
		
		

	@FXML
	public void handleFinishButton(ActionEvent event) throws Exception {
		ArrayList<String> list = getPillId();
		CallableStatement cs = null;

		try {
			String SerialNumber = SN.getText();
			String number = Number.getText();
			String dosage = Dosage.getText();
			String TimePD = TPD.getText();
			String patient = PatientID.getText();


			if(list.contains(SerialNumber) && isInteger(number) && isInteger(dosage) && isInteger(TimePD)) {
				cs = Main.getDcs().getConnection().prepareCall("{call [PillSite].[dbo].addprescription(?,?,?,?,?,?)}");
				cs.setInt(1, Main.getID());
				Random r = new Random();
				cs.setInt(2, Main.getID() + Integer.parseInt(patient) + r.nextInt(100));
				cs.setString(3,  patient);
				cs.setString(4, dosage);
				cs.setString(5, TimePD);
				cs.setString(6,  SerialNumber);
				cs.execute();
				JOptionPane.showMessageDialog(null, "Added Complete");
				storyStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				Node node=(Node) event.getSource();
				Stage stage=(Stage) node.getScene().getWindow();
				Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			}
			else {
				JOptionPane.showMessageDialog(null, "Added Failed");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Added Failed");
			e.printStackTrace();
		}

	}
 
	@FXML
	public void handleFinishOnKeyPressed(KeyEvent event) throws IOException, SQLException {
		if (event.getCode() == KeyCode.ENTER) {
			ArrayList<String> list = getPillId();
			CallableStatement cs = null;

			try {
				String SerialNumber = SN.getText();
				String number = Number.getText();
				String dosage = Dosage.getText();
				String TimePD = TPD.getText();
				String patient = PatientID.getText();


				if(list.contains(SerialNumber) && isInteger(number) && isInteger(dosage) && isInteger(TimePD)) {
					cs = Main.getDcs().getConnection().prepareCall("{call [PillSite].[dbo].addprescription(?,?,?,?,?,?)}");
					cs.setInt(1, Main.getID());
					Random r = new Random();
					cs.setInt(2, Main.getID() + Integer.parseInt(patient) + r.nextInt(100));
					cs.setString(3,  patient);
					cs.setString(4, dosage);
					cs.setString(5, TimePD);
					cs.setString(6,  SerialNumber);
					cs.execute();
					JOptionPane.showMessageDialog(null, "Added Complete");
					storyStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					Node node=(Node) event.getSource();
					Stage stage=(Stage) node.getScene().getWindow();
					Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
					Scene scene = new Scene(root);
					stage.setScene(scene);
					stage.show();
				}
				else {
					JOptionPane.showMessageDialog(null, "Added Failed");
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Added Failed");
				e.printStackTrace();
			}


		}
		
	}

	@FXML
	public void handleGobackButton(ActionEvent event) throws IOException {
		storyStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Node node=(Node) event.getSource();
		Stage stage=(Stage) node.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
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
			Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	}

}
