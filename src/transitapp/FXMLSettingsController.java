package transitapp;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.*;

/**
 * This class shows the settings options and currently only has the back and the
 * exit button.
 * @author Lap Khang Tran
 *
 */
public class FXMLSettingsController extends ControllerParent implements Initializable {

	/**
	 * This method changes the scene to the menu screen.
	 * @param event
	 * @throws IOException
	 */
	public void backButtonPush(ActionEvent event) throws IOException {

		changeScene(event, "FXMLMenu.FXML");
	}
	
	/**
	 * Method that needs to be in the class from implementing Initializable.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}