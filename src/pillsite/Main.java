package pillsite;

import javafx.application.Application; 
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Set title
        primaryStage.setTitle("PillSite");

        // This creates the Intro
     		FXMLLoader loader = new FXMLLoader();
     		loader.setLocation(Main.class.getResource("controller/view/Login.fxml"));
     		Parent root = loader.load();
     		Scene scene = new Scene(root);
     		primaryStage.setScene(scene);
    		primaryStage.setResizable(false); 
     		primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);

    }
}
