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
import pillsite.controller.model.maps.Pills;

public class MainMenu implements Initializable{
	 @FXML private TableView<Pills> tableView;
	    @FXML private TableColumn<Pills, String> Pill;
	    @FXML private TableColumn<Pills, String> Id;
	    @FXML private TableColumn<Pills, String> Type;
	    
	    final ObservableList<Pills> data = FXCollections.observableArrayList(
	    	    );

	    @Override
	    public void initialize(URL location, ResourceBundle resources) {
	    	try {
				addPills();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    	Pill.setCellValueFactory(new PropertyValueFactory<Pills, String>("name"));
	    	Id.setCellValueFactory(new PropertyValueFactory<Pills, String>("id"));
	    	Type.setCellValueFactory(new PropertyValueFactory<Pills, String>("type"));
	    	
	    	tableView.setItems(data);
	    	tableView.getColumns().addAll(Pill, Type, Id);

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
	    	ArrayList<String> ids = getYourPillId();
	    	ArrayList<String> number = getYourNumberLeft();
	    	for(int i = 0; i < names.size(); i++){
	    		data.add(new Pills(names.get(i), ids.get(i), number.get(i)));
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
		
		public ArrayList<String> getYourPillId() throws SQLException {	
		    Statement stmt = null;	
		    ArrayList<String> id = new ArrayList<>();
		    String query = "SELECT PillSerialNumber FROM [PillSite].[dbo].[Take] WHERE PatientUsername = " + Main.getID();
		    stmt = Main.getDcs().getConnection().createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    while (rs.next()) {
		        String name = rs.getString("PillSerialNumber");
		        id.add(name);
		    }
		    return id;
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
		public void handleAddNewButton(ActionEvent event) throws Exception {
		}
	 
		@FXML
		public void handleAddNewOnKeyPressed(KeyEvent event) throws IOException {
			if (event.getCode() == KeyCode.ENTER) {
			}
			
		}

}
