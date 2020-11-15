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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.*;

/**
 * This class is responsible for the keeping the information in the textfields when you fill
 * in the information for username and password. It also changes the screen to menu when clicking
 * back and changes the screen to the register screen when the user does not have an account.
 * If an account get signed in, it would change the current screen to the dashboard screen.
 * @author Lap Khang Tran
 *
 */
public class FXMLLoginController extends ControllerParent implements Initializable {

	@FXML
	private TextField email;
	@FXML
	private TextField password;
	
	/**
	 * This method changes the scene by returning to the menu.
	 * @param event
	 * @throws IOException
	 */
	public void backButtonPush(ActionEvent event) throws IOException {

		changeScene(event, "FXMLMenu.FXML");
	}
	
	/**
	 * This method changes the scene to the login screen.
	 * @param event
	 * @throws IOException
	 */
	public void noAccountButton(ActionEvent event) throws IOException {

		changeScene(event, "FXMLRegister.FXML");
	}
	
	/**
	 * This method stores the information from the textfields for the password
	 * and the username and checks that the credentials are correct. It holds 
	 * the information to display it onto the dashboard. It also changes screen
	 * to the dashboard.
	 * @param event
	 * @throws IOException
	 */
	public void loginButtonPush(ActionEvent event) throws IOException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDashboard.FXML"));
		Parent dashParent = loader.load();
		FXMLDashboardController temp = loader.getController();
		// authenticate user and pass data over (pass User object later)
		temp.setData("firstName", email.getText());
		Scene dashScene = new Scene(dashParent);
		dashScene.setFill(Color.TRANSPARENT);
		Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
		stage.setScene(dashScene);
		stage.show();
	}
	
	/**
	 * Method that needs to be in the class from implementing Initializable.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

}
