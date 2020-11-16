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

/**
 * This parent class is responsible for making controller children and
 * reducing the amount of code that is written. This class has the exit button
 * and the general layout for changing scenes when a button is pressed.
 * @author 
 *
 */

abstract public class ControllerParent implements Initializable {
	
	/**
	 * This method would close the window of the application.
	 * @param event
	 * @throws IOException
	 */
	public void exitButton(ActionEvent event) throws IOException {

		Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());

		stage.close();
	}
	
	/**
	 * This method would open up the FXML file and then display it as the
	 * current stage. It would also make the corners of the window not sharp.
	 * 
	 * This code is inspired from the YouTube video linked below.
	 * 
	 * @param event
	 * @param file
	 * @throws IOException
	 */
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
