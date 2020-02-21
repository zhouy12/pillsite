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
import pillsite.controller.model.maps.YourPills;

public class MainMenu implements Initializable{
	 	@FXML private TableView<Pills> tableView;
	    @FXML private TableColumn<Pills, String> Pill;
	    @FXML private TableColumn<Pills, String> Id;
	    @FXML private TableColumn<Pills, String> Type;
	    
	 	@FXML private TableView<YourPills> YourPills;
	    @FXML private TableColumn<YourPills, String> YourPill;
	    @FXML private TableColumn<YourPills, String> AL;
	    @FXML private TableColumn<YourPills, String> Dosage;
	    @FXML private TableColumn<YourPills, String> TPD;
	    @FXML private TableColumn<YourPills, String> TTT;
	    @FXML private TableColumn<YourPills, String> TLT;
	    
	 	@FXML private TableView<YourPills> NeededPills;
	    @FXML private TableColumn<YourPills, String> NeedName;
	    @FXML private TableColumn<YourPills, String> NeedDosage;
	    @FXML private TableColumn<YourPills, String> NeedTPD;
	    @FXML private TableColumn<YourPills, String> NeedTTT;
	    @FXML private TableColumn<YourPills, String> NeedTLT;
	    
	 	@FXML private TableView<YourPills> Refills;
	    @FXML private TableColumn<YourPills, String> RefillName;
	    @FXML private TableColumn<YourPills, String> RefillAL;
	    
	 	@FXML private TableView<Doctor> YourDoctors;
	    @FXML private TableColumn<Doctor, String> FName;
	    @FXML private TableColumn<Doctor, String> LName;
	    @FXML private TableColumn<Doctor, String> Hospital;
	    @FXML private TableColumn<Doctor, String> Dept;
	    @FXML private TableColumn<Doctor, String> PNum;
	    
	 	@FXML private TableView<Doctor> AllDoctors;
	    @FXML private TableColumn<Doctor, String> FName2;
	    @FXML private TableColumn<Doctor, String> LName2;
	    @FXML private TableColumn<Doctor, String> Hospital2;
	    @FXML private TableColumn<Doctor, String> Dept2;
	    @FXML private TableColumn<Doctor, String> PNum2;
	    
	    final ObservableList<Pills> data = FXCollections.observableArrayList(
	    	    );
	    final ObservableList<YourPills> data2 = FXCollections.observableArrayList(
	    	    );
	    
	    final ObservableList<YourPills> data3 = FXCollections.observableArrayList(
	    	    );
	    
	    final ObservableList<YourPills> data4 = FXCollections.observableArrayList(
	    	    );
	    
	    final ObservableList<Doctor> data5 = FXCollections.observableArrayList(
	    	    );
	    
	    final ObservableList<Doctor> data6 = FXCollections.observableArrayList(
	    	    );

	    @Override
	    public void initialize(URL location, ResourceBundle resources) {
	    	try {
				addPills();
				addYourPills();
				addNeedPills();
				addRefills();
				addAllDoctors();
				addYourDoctors();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    	Pill.setCellValueFactory(new PropertyValueFactory<Pills, String>("name"));
	    	Id.setCellValueFactory(new PropertyValueFactory<Pills, String>("id"));
	    	Type.setCellValueFactory(new PropertyValueFactory<Pills, String>("type"));
	    	
	    	AL.setCellValueFactory(new PropertyValueFactory<YourPills, String>("AL"));
	    	YourPill.setCellValueFactory(new PropertyValueFactory<YourPills, String>("name"));
	    	Dosage.setCellValueFactory(new PropertyValueFactory<YourPills, String>("dosage"));
	    	TPD.setCellValueFactory(new PropertyValueFactory<YourPills, String>("TPD"));
	    	TTT.setCellValueFactory(new PropertyValueFactory<YourPills, String>("TTT"));
	    	TLT.setCellValueFactory(new PropertyValueFactory<YourPills, String>("TLT"));
	    	
	    	NeedDosage.setCellValueFactory(new PropertyValueFactory<YourPills, String>("dosage"));
	    	NeedName.setCellValueFactory(new PropertyValueFactory<YourPills, String>("name"));
	    	NeedTPD.setCellValueFactory(new PropertyValueFactory<YourPills, String>("TPD"));
	    	NeedTTT.setCellValueFactory(new PropertyValueFactory<YourPills, String>("TTT"));
	    	NeedTLT.setCellValueFactory(new PropertyValueFactory<YourPills, String>("TLT"));
	    	
	    	RefillAL.setCellValueFactory(new PropertyValueFactory<YourPills, String>("AL"));
	    	RefillName.setCellValueFactory(new PropertyValueFactory<YourPills, String>("name"));
	    	
		    FName2.setCellValueFactory(new PropertyValueFactory<Doctor, String>("Fname"));
		    LName2.setCellValueFactory(new PropertyValueFactory<Doctor, String>("Lname"));
		    Hospital2.setCellValueFactory(new PropertyValueFactory<Doctor, String>("hospital"));
		    Dept2.setCellValueFactory(new PropertyValueFactory<Doctor, String>("department"));
		    PNum2.setCellValueFactory(new PropertyValueFactory<Doctor, String>("phoneNumber"));
	    	
	    	
	    	
	    	tableView.setItems(data);
	    	tableView.getColumns().addAll(Pill, Type, Id);
	    	
	    	YourPills.setItems(data2);
	    	YourPills.getColumns().addAll(YourPill, AL, Dosage, TPD, TTT, TLT);
	    	
	    	NeededPills.setItems(data3);
	    	NeededPills.getColumns().addAll(NeedName, NeedDosage, NeedTPD, NeedTTT, NeedTLT);
	    	
	    	Refills.setItems(data4);
	    	Refills.getColumns().addAll(RefillName, RefillAL);
	    	
	    	AllDoctors.setItems(data5);
	    	AllDoctors.getColumns().addAll(FName2, LName2, Hospital2, Dept2, PNum2);
	    	
	    	YourDoctors.setItems(data6);
	    	YourDoctors.getColumns().addAll(FName, LName, Hospital, Dept, PNum);

	    }
	    
	    private void addPills() throws SQLException {
	    	ArrayList<String> names = getPillName();
	    	ArrayList<String> ids = getPillId();
	    	ArrayList<String> types = getPillType();
	    	for(int i = 0; i < names.size(); i++){
	    		data.add(new Pills(names.get(i), ids.get(i), types.get(i)));
	    	}
	    }
	    
	    private void addYourPills() throws SQLException {
	    	ArrayList<String> names = getYourPillName();
	    	ArrayList<String> number = getYourNumberLeft();
	    	ArrayList<String> dosage = getYourDosage();
	    	ArrayList<String> fixedTPD = getYourTPD();
	    	ArrayList<String> TTT = getYourTTT();
	    	ArrayList<String> TLT = getYourTLT();
	    	for(int i = 0; i < names.size(); i++){
	    		data2.add(new YourPills(names.get(i), number.get(i), dosage.get(i), fixedTPD.get(i), TTT.get(i), TLT.get(i)));
	    	}
	    }
	    
	    private void addNeedPills() throws SQLException {
	    	ArrayList<String> names = getYourPillName();
	    	ArrayList<String> number = getYourNumberLeft();
	    	ArrayList<String> dosage = getYourDosage();
	    	ArrayList<String> fixedTPD = getYourTPD();
	    	ArrayList<String> TTT = getYourTTT();
	    	ArrayList<String> TLT = getYourTLT();
	    	for(int i = 0; i < names.size(); i++){
	    		if(Integer.parseInt(TTT.get(i)) < Integer.parseInt(fixedTPD.get(i))) {
	    			data3.add(new YourPills(names.get(i), number.get(i), dosage.get(i), fixedTPD.get(i), TTT.get(i), TLT.get(i)));
	    		}
	    	}
	    }
	    
	    private void addRefills() throws SQLException {
	    	ArrayList<String> names = getYourPillName();
	    	ArrayList<String> number = getYourNumberLeft();
	    	ArrayList<String> dosage = getYourDosage();
	    	ArrayList<String> fixedTPD = getYourTPD();
	    	ArrayList<String> TTT = getYourTTT();
	    	ArrayList<String> TLT = getYourTLT();
	    	for(int i = 0; i < names.size(); i++){
	    		if(Integer.parseInt(number.get(i)) < 10) {
	    			data4.add(new YourPills(names.get(i), number.get(i), dosage.get(i), fixedTPD.get(i), TTT.get(i), TLT.get(i)));
	    		}
	    	}
	    }
	    
	    private void addAllDoctors() throws SQLException {
	    	ArrayList<String> fName = getDoctorFName();
	    	ArrayList<String> lName = getDoctorLName();
	    	ArrayList<String> ID = getDocID();
	    	ArrayList<String> department = getDept();
	    	ArrayList<String> pNum = getPNum();
	    	ArrayList<String> hospital = getHospital();
	    	for(int i = 0; i < ID.size(); i++){
	    		data5.add(new Doctor(fName.get(i), lName.get(i), ID.get(i), hospital.get(i), department.get(i), pNum.get(i)));
	    	}
	    }
	    
	    private void addYourDoctors() throws SQLException {
	    	ArrayList<String> fName = getDoctorFName();
	    	ArrayList<String> lName = getDoctorLName();
	    	ArrayList<String> ID = getDocID();
	    	ArrayList<String> department = getDept();
	    	ArrayList<String> pNum = getPNum();
	    	ArrayList<String> hospital = getHospital();
		    Statement stmt = null;	
		    ArrayList<String> names = new ArrayList<>();
		    String query = "SELECT DoctorID FROM [PillSite].[dbo].[Belongs] WHERE PatientID = " + Main.getID();
		    stmt = Main.getDcs().getConnection().createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    while (rs.next()) {
		        String name = rs.getString("DoctorID");
		        names.add(name);
		    }
	    	for(int i = 0; i < ID.size(); i++){
	    		if(names.contains(ID.get(i))) {
	    			data6.add(new Doctor(fName.get(i), lName.get(i), ID.get(i), hospital.get(i), department.get(i), pNum.get(i)));
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
		
		public ArrayList<String> getYourNumberLeft() throws SQLException {	
		    Statement stmt = null;	
		    ArrayList<String> id = new ArrayList<>();
		    String query = "SELECT NumberLeft FROM [PillSite].[dbo].[Take] WHERE PatientUsername = " + Main.getID();
		    stmt = Main.getDcs().getConnection().createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    while (rs.next()) {
		        String name = rs.getString("NumberLeft");
		        id.add(name);
		    }
		    return id;
		}
		
		public ArrayList<String> getYourDosage() throws SQLException {	
		    Statement stmt = null;	
		    ArrayList<String> id = new ArrayList<>();
		    String query = "SELECT dosage FROM [PillSite].[dbo].[Take] WHERE PatientUsername = " + Main.getID();
		    stmt = Main.getDcs().getConnection().createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    while (rs.next()) {
		        String name = rs.getString("dosage");
		        id.add(name);
		    }
		    return id;
		}
		
		public ArrayList<String> getYourTPD() throws SQLException {	
		    Statement stmt = null;	
		    ArrayList<String> id = new ArrayList<>();
		    String query = "SELECT FixedTPD FROM [PillSite].[dbo].[Take] WHERE PatientUsername = " + Main.getID();
		    stmt = Main.getDcs().getConnection().createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    while (rs.next()) {
		        String name = rs.getString("FixedTPD");
		        id.add(name);
		    }
		    return id;
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
		
		public ArrayList<String> getDoctorFName() throws SQLException {	
		    Statement stmt = null;	
		    ArrayList<String> id = new ArrayList<>();
		    String query = "SELECT FName FROM [PillSite].[dbo].[Doctor] JOIN [PillSite].[dbo].[Person] ON "
		    		+ "[PillSite].[dbo].[Doctor].ID = [PillSite].[dbo].[Person].ID";
		    stmt = Main.getDcs().getConnection().createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    while (rs.next()) {
		        String name = rs.getString("FName");
		        id.add(name);
		    }
		    return id;
		}
		
		public ArrayList<String> getDoctorLName() throws SQLException {	
		    Statement stmt = null;	
		    ArrayList<String> id = new ArrayList<>();
		    String query = "SELECT LName FROM [PillSite].[dbo].[Doctor] JOIN [PillSite].[dbo].[Person] ON "
		    		+ "[PillSite].[dbo].[Doctor].ID = [PillSite].[dbo].[Person].ID";
		    stmt = Main.getDcs().getConnection().createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    while (rs.next()) {
		        String name = rs.getString("LName");
		        id.add(name);
		    }
		    return id;
		}
		
		public ArrayList<String> getHospital() throws SQLException {	
		    Statement stmt = null;	
		    ArrayList<String> id = new ArrayList<>();
		    String query = "SELECT Hospital FROM [PillSite].[dbo].[Doctor]";
		    stmt = Main.getDcs().getConnection().createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    while (rs.next()) {
		        String name = rs.getString("Hospital");
		        id.add(name);
		    }
		    return id;
		}
		
		public ArrayList<String> getDept() throws SQLException {	
		    Statement stmt = null;	
		    ArrayList<String> id = new ArrayList<>();
		    String query = "SELECT Department FROM [PillSite].[dbo].[Doctor]";
		    stmt = Main.getDcs().getConnection().createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    while (rs.next()) {
		        String name = rs.getString("Department");
		        id.add(name);
		    }
		    return id;
		}
		
		public ArrayList<String> getDocID() throws SQLException {	
		    Statement stmt = null;	
		    ArrayList<String> id = new ArrayList<>();
		    String query = "SELECT ID FROM [PillSite].[dbo].[Doctor]";
		    stmt = Main.getDcs().getConnection().createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    while (rs.next()) {
		        String name = rs.getString("ID");
		        id.add(name);
		    }
		    return id;
		}
		
		public ArrayList<String> getPNum() throws SQLException {	
		    Statement stmt = null;	
		    ArrayList<String> id = new ArrayList<>();
		    String query = "SELECT phoneNumber FROM [PillSite].[dbo].[Doctor]";
		    stmt = Main.getDcs().getConnection().createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    while (rs.next()) {
		        String name = rs.getString("phoneNumber");
		        id.add(name);
		    }
		    return id;
		}
		
		@FXML
		public void handleAddNewButton(ActionEvent event) throws Exception {
		}
	 
		@FXML
		public void handleAddNewOnKeyPressed(KeyEvent event) throws IOException {
			if (event.getCode() == KeyCode.ENTER) {
			}
			
		}
		
		@FXML
		public void handleAccountButton(ActionEvent event) throws Exception {
		}
	 
		@FXML
		public void handleAccountOnKeyPressed(KeyEvent event) throws IOException {
			if (event.getCode() == KeyCode.ENTER) {
			}
			
		}

}
