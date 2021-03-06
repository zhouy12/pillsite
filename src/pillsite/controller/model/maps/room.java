package pillsite.controller.model.maps;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class room {
	private String file = "src/roomFiles/tester2";
	
	private ArrayList<Node> allobjects = new ArrayList<>();

	private Pane appRoot = new Pane();
	private Pane gameRoot = new Pane();

	private Stage stage;


	public void mapGeneration(Stage map) throws Exception {

		makeRoom();
		this.stage = map;
		this.stage.setHeight(map.getHeight());
		this.stage.setWidth(map.getWidth());
		this.stage.setResizable(false);

		Scene scene = new Scene(appRoot);

		System.out.println(map);
		map.setScene(scene);
		map.show();

	}

	public void makeRoom() throws FileNotFoundException {

		Scanner sc;
		FileReader file = new FileReader(this.file);
		sc = new Scanner(file);
		int y = 0;
  
		while(sc.hasNextLine()) {
			String row = sc.nextLine().trim();
			int x = 0;
			for(char ch : row.toCharArray()) {
				switch(ch) {
				case '.':
					break;
				case 'w':
					Node wall = createImage(40, 40, "images/black.png");
					wall.setLayoutX(x);
					wall.setLayoutY(y);
					allobjects.add(wall);
					break;
				}
				x = x + 40;
			}
			y = y + 40;
		}
		
		
		appRoot.getChildren().addAll(gameRoot);
	}
	// Creates uncrossables

	private Node createImage(int h, int w, String link) { 

		Image img = new Image(link);
		ImageView imageView = new ImageView(img);
		imageView.setFitHeight(h);
		imageView.setFitWidth(w);
		imageView.getProperties().put("alive", true);

		gameRoot.getChildren().add(imageView);
		return imageView;
	}
	
	// Setters and Getters


}
