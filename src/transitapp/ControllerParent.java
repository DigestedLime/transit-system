package transitapp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

abstract public class ControllerParent implements Initializable {
	
	public void exitButton(ActionEvent event) throws IOException {

		Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());

		stage.close();
	}
	
	public void changeScene(Event event, String file) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource(file));
		Scene scene = new Scene(parent);
		scene.setFill(Color.TRANSPARENT);

		Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());

		stage.setScene(scene);
		stage.show();
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
