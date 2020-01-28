package pillsite.controller.view;

import javafx.stage.Stage;
import pillsite.controller.model.maps.room;

public class roomSelect {
	private Stage currentStage;

	public void selectRoom(Stage stage) throws Exception {

		currentStage = stage;
		room roomba = new room();
		roomba.mapGeneration(currentStage);
	}

}
  