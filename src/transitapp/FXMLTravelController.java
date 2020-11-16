package transitapp;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class FXMLTravelController extends ControllerParent implements Initializable {
	
	@FXML
	public TextField time;
	@FXML
	public ListView<String> currentStation;
	@FXML
	public TextField cardID;
	@FXML
	public Button status;
	@FXML
	public ImageView error;
	@FXML
	public ImageView checkmark;
	
	
	public void startJourneyPush(ActionEvent event) throws IOException {
		// TODO
	}
}
