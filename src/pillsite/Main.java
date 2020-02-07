package pillsite;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.application.Application; 
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static DatabaseConnectionService dcs = new DatabaseConnectionService("golem.csse.rose-hulman.edu", "PillSite");
    boolean connect = getDcs().connect("zhouy12", "zyjZYJ88");
    private static int Id = 0;

    @Override
    public void start(Stage primaryStage) throws Exception{
    	//Connect to database
    	System.out.println(connect);
        getDcs().getConnection();
        
        //Test
        Statement stmt = null;	
	    ArrayList<String> restaurants = new ArrayList<>();
	    String query = "SELECT * FROM [PillSite].[dbo].[Pill]";
	    stmt = dcs.getConnection().createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	    while (rs.next()) {
	        String name = rs.getString("PillName");
	        System.out.println(name);
	        restaurants.add(name);
	    }
    	
        // Set title
        primaryStage.setTitle("PillSite");

        // This creates the Intro
     		FXMLLoader loader = new FXMLLoader();
     		loader.setLocation(getClass().getResource("controller/view/Login.fxml"));
     		Parent root = loader.load();
     		Scene scene = new Scene(root);
     		primaryStage.setScene(scene);
    		primaryStage.setResizable(false); 
     		primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


	public static DatabaseConnectionService getDcs() {
		return dcs;
	}


	public void setDcs(DatabaseConnectionService dcs) {
		Main.dcs = dcs;
	}
	
	public static int getID() {
		return Id;
	}


	public static void setID(int ID) {
		Main.Id = ID;
	}
}
