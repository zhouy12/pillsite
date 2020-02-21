package pillsite.controller.view;

import java.io.IOException; 
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import pillsite.Main;
import pillsite.controller.model.maps.Doctor;
import pillsite.controller.model.maps.Pills;
import pillsite.controller.model.maps.Prescriptions;
import pillsite.controller.model.maps.Patients;

public class DoctorMainMenu implements Initializable{
	Stage storyStage;
	 	@FXML private TableView<Pills> DocPills;
	    @FXML private TableColumn<Pills, String> DocPillName;
	    @FXML private TableColumn<Pills, String> PillID;
	    @FXML private TableColumn<Pills, String> Type;
	    
	 	@FXML private TableView<Patients> allName;
	    @FXML private TableColumn<Patients, String> allFN;
	    @FXML private TableColumn<Patients, String> allLN;
	    
	 	@FXML private TableView<Patients> yourName;
	    @FXML private TableColumn<Patients, String> yourFN;
	    @FXML private TableColumn<Patients, String> yourLN;
	  
	    
	 	@FXML private TableView<Prescriptions> DoctorPre;
	    @FXML private TableColumn<Prescriptions, String> PatientUser;
	    @FXML private TableColumn<Prescriptions, String> PrePill;
	    @FXML private TableColumn<Prescriptions, String> PreDosage;
	    @FXML private TableColumn<Prescriptions, String> PreTPD;
	   
	    
	    final ObservableList<Pills> data = FXCollections.observableArrayList(
	    	    );
	    final ObservableList<Patients> data2 = FXCollections.observableArrayList(
	    	    );
	    
	    final ObservableList<Patients> data3 = FXCollections.observableArrayList(
	    	    );
	    
	    final ObservableList<Patients> data4 = FXCollections.observableArrayList(
	    	    );
	    
	    final ObservableList<Prescriptions> data5 = FXCollections.observableArrayList(
	    	    );
	    

	    @Override
	    public void initialize(URL location, ResourceBundle resources) {
	    	try {
				addPills();				
				
				addAllPatients();
				addYourPatients();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    	DocPillName.setCellValueFactory(new PropertyValueFactory<Pills, String>("name"));
	    	PillID.setCellValueFactory(new PropertyValueFactory<Pills, String>("id"));
	    	Type.setCellValueFactory(new PropertyValueFactory<Pills, String>("type"));
	    	
	    	allFN.setCellValueFactory(new PropertyValueFactory<Patients, String>("allfirstname"));
	    	allLN.setCellValueFactory(new PropertyValueFactory<Patients, String>("alllastname"));
	    	
	    	yourFN.setCellValueFactory(new PropertyValueFactory<Patients, String>("yourfirstname"));
	    	yourLN.setCellValueFactory(new PropertyValueFactory<Patients, String>("yourlastname"));
	    	
	    	PatientUser.setCellValueFactory(new PropertyValueFactory<Prescriptions, String>("patient"));
	    	PreDosage.setCellValueFactory(new PropertyValueFactory<Prescriptions, String>("dosage"));
	    	PrePill.setCellValueFactory(new PropertyValueFactory<Prescriptions, String>("Pill"));
	    	PreTPD.setCellValueFactory(new PropertyValueFactory<Prescriptions, String>("TPD"));
	    	
		  
	    	
	    	
	    	DocPills.setItems(data);
	    	DocPills.getColumns().addAll(DocPillName, Type, PillID);
	    	
	    	allName.setItems(data2);
	    	allName.getColumns().addAll(allFN, allLN);
	    	
	    	yourName.setItems(data3);
	    	yourName.getColumns().addAll(yourFN,yourLN);
	    	
	    	DoctorPre.setItems(data5);
	    	DoctorPre.getColumns().addAll(PatientUser,PreDosage,PrePill,PreTPD);
	    	
	    	

	    }
	    
	    private void addPills() throws SQLException {
	    	ArrayList<String> names = getPillName();
	    	ArrayList<String> ids = getPillId();
	    	ArrayList<String> types = getPillType();
	    	for(int i = 0; i < names.size(); i++){
	    		data.add(new Pills(names.get(i), ids.get(i), types.get(i)));
	    	}
	    }
	    
	    
	    
	    private void addAllPatients() throws SQLException {
	    	ArrayList<String> fName = getPatientFName();
	    	ArrayList<String> lName = getPatientLName();
	    	ArrayList<String> ID = getPatientID();
	    	for(int i = 0; i < ID.size(); i++){
	    		data2.add(new Patients(fName.get(i), lName.get(i), ID.get(i)));
	    	}
	    }
	    
	    private void addYourPatients() throws SQLException {
	    	ArrayList<String> fName = getPatientFName();
	    	ArrayList<String> lName = getPatientLName();
	    	ArrayList<String> ID = getPatientID();
		    Statement stmt = null;	
		    ArrayList<String> names = new ArrayList<>();
		    String query = "SELECT PatientID FROM [PillSite].[dbo].[Belongs] WHERE DoctorID = " + Main.getID();
		    stmt = Main.getDcs().getConnection().createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    while (rs.next()) {
		        String name = rs.getString("PatientID");
		        names.add(name);
		    }
	    	for(int i = 0; i < ID.size(); i++){
	    		if(names.contains(ID.get(i))) {
	    			data3.add(new Patients(fName.get(i), lName.get(i), ID.get(i)));
	    		}
	    	}
	    }
	    private void addPrescription() throws SQLException {
	    	
	    	ArrayList<String> dosage = getDosage();
	    	ArrayList<String> pill= getPill();
	    	ArrayList<String> TPD = getTPD();
	    	ArrayList<String> ID = getId();
		    Statement stmt = null;	
		    ArrayList<String> names = new ArrayList<>();
		    String query = "SELECT PatientID FROM [PillSite].[dbo].[Belongs] WHERE DoctorID = " + Main.getID();
		    stmt = Main.getDcs().getConnection().createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    while (rs.next()) {
		        String name = rs.getString("PatientID");
		        names.add(name);
		    }
	    	for(int i = 0; i < ID.size(); i++){
	    		if(names.contains(ID.get(i))) {
	    			data5.add(new Prescriptions(dosage.get(i), pill.get(i), TPD.get(i),ID.get(i)));
	    		}
	    	}
	    }
	    
		public ArrayList<String> getYourPillName() throws SQLException {	
		    Statement stmt = null;	
		    ArrayList<String> names = new ArrayList<>();
		    String query = "SELECT PillName FROM [PillSite].[dbo].[Take] JOIN [PillSite].[dbo].[Pill] ON [PillSite].[dbo].[Pill].[SerialNumber] = [PillSite].[dbo].[Take].[PillSerialNumber] "
		    		+ "WHERE PatientUsername = " + Main.getID();
		    stmt = Main.getDcs().getConnection().createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    while (rs.next()) {
		        String name = rs.getString("PillName");
		        names.add(name);
		    }
		    return names;
		}
		
		public ArrayList<String> getPill() throws SQLException {	
		    Statement stmt = null;	
		    ArrayList<String> id = new ArrayList<>();
		    String query = "SELECT Pill FROM [PillSite].[dbo].[Prescription] WHERE PatientUsername = " + Main.getID();
		    stmt = Main.getDcs().getConnection().createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    while (rs.next()) {
		        String name = rs.getString("Pill");
		        id.add(name);
		    }
		    return id;
		}
		
		public ArrayList<String> getDosage() throws SQLException {	
		    Statement stmt = null;	
		    ArrayList<String> id = new ArrayList<>();
		    String query = "SELECT dosage FROM [PillSite].[dbo].[Prescription] WHERE PatientUsername = " + Main.getID();
		    stmt = Main.getDcs().getConnection().createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    while (rs.next()) {
		        String name = rs.getString("dosage");
		        id.add(name);
		    }
		    return id;
		}
		public ArrayList<String> getId() throws SQLException {	
		    Statement stmt = null;	
		    ArrayList<String> id = new ArrayList<>();
		    String query = "SELECT ID FROM [PillSite].[dbo].[Prescription] WHERE PatientUsername = " + Main.getID();
		    stmt = Main.getDcs().getConnection().createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    while (rs.next()) {
		        String name = rs.getString("ID");
		        id.add(name);
		    }
		    return id;
		}
		public ArrayList<String> getTPD() throws SQLException {	
		    Statement stmt = null;	
		    ArrayList<String> TPD = new ArrayList<>();
		    String query = "SELECT SugTimesPerDay FROM [PillSite].[dbo].[Prescription] WHERE PatientUsername = " + Main.getID();
		    stmt = Main.getDcs().getConnection().createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    while (rs.next()) {
		        String name = rs.getString("sugTPD");
		        TPD.add(name);
		    }
		    return TPD;
		}
		
		public ArrayList<String> getYourTTT() throws SQLException {	
		    Statement stmt = null;	
		    ArrayList<String> id = new ArrayList<>();
		    String query = "SELECT TimesPerDay FROM [PillSite].[dbo].[Take] WHERE PatientUsername = " + Main.getID();
		    stmt = Main.getDcs().getConnection().createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    while (rs.next()) {
		        String name = rs.getString("TimesPerDay");
		        id.add(name);
		    }
		    return id;
		}
		
		public ArrayList<String> getYourTLT() throws SQLException {	
		    Statement stmt = null;	
		    ArrayList<String> id = new ArrayList<>();
		    String query = "SELECT DateTaken FROM [PillSite].[dbo].[Take] WHERE PatientUsername = " + Main.getID();
		    stmt = Main.getDcs().getConnection().createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    while (rs.next()) {
		        String name = rs.getString("DateTaken");
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
		
	
		
		@FXML
		public void handleAddNewButton(ActionEvent event) throws Exception {
			storyStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			Node node=(Node) event.getSource();
			Stage stage=(Stage) node.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("AddPrescription.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	 
		@FXML
		public void handleAddNewOnKeyPressed(KeyEvent event) throws IOException {
			if (event.getCode() == KeyCode.ENTER) {
				storyStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				Node node=(Node) event.getSource();
				Stage stage=(Stage) node.getScene().getWindow();
				Parent root = FXMLLoader.load(getClass().getResource("AddPrescription.fxml"));
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			}
			
		}


}
