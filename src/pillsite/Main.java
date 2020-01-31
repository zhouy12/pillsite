package pillsite;

import javafx.application.Application; 
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
    	//Connect to database
    	
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
        DatabaseConnectionService dcs = new DatabaseConnectionService("golem.csse.rose-hulman.edu", "PillSite");
        System.out.println(dcs.connect("zhouy12", "zyjZYJ88"));
        dcs.getConnection();
        launch(args);
    }
}
