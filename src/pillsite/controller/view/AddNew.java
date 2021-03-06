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
import pillsite.controller.model.maps.Pills;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
import pillsite.DatabaseConnectionService;

	public class AddNew implements Initializable {
		@FXML private TextField SN;
		@FXML private TextField Number;
		@FXML private TextField Dosage;
		@FXML private TextField TPD;
		Stage storyStage;

	 	@FXML private TableView<Pills> RefTable;
	    @FXML private TableColumn<Pills, String> PName;
	    @FXML private TableColumn<Pills, String> SerN;
	    
	    final ObservableList<Pills> data = FXCollections.observableArrayList(
	    	    );

	    @Override
	    public void initialize(URL location, ResourceBundle resources) {
	    	try {
				addPills();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    
	    	PName.setCellValueFactory(new PropertyValueFactory<Pills, String>("name"));
	    	SerN.setCellValueFactory(new PropertyValueFactory<Pills, String>("id"));
	    	
	    	RefTable.setItems(data);
	    	RefTable.getColumns().addAll(PName, SerN);
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


			if(list.contains(SerialNumber) && isInteger(number) && isInteger(dosage) && isInteger(TimePD)) {
				cs = Main.getDcs().getConnection().prepareCall("{call [PillSite].[dbo].addtake(?,?,?,?,?,?)}");
				cs.setInt(1, Main.getID());
				cs.setString(2, SerialNumber);
				cs.setString(3,  number);
				cs.setString(4, dosage);
				cs.setInt(5, 0);
				cs.setString(6,  TimePD);
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


				if(list.contains(SerialNumber) && isInteger(number) && isInteger(dosage) && isInteger(TimePD)) {
					cs = Main.getDcs().getConnection().prepareCall("{call [PillSite].[dbo].addtake(?,?,?,?,?,?)}");
					cs.setInt(1, Main.getID());
					cs.setString(2, SerialNumber);
					cs.setString(3,  number);
					cs.setString(4, dosage);
					cs.setInt(5, 0);
					cs.setString(6,  TimePD);
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
