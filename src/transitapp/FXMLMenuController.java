package transitapp;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import user.CustomerUser;

/**
 * This class is responsible for the events that happen in the menu screen. Any
 * button event that happens would cause change the scene to login, settings or
 * register.
 * 
 * @author
 *
 */
public class FXMLMenuController extends ControllerParent implements Initializable {
	private ArrayList<CustomerUser> users;
	
	public void setData(ArrayList<CustomerUser> users) {
		this.users = users;
	}
	
	/**
	 * This method changes the scene to the login screen.
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void loginButtonPush(ActionEvent event) throws IOException {
		FXMLLoader loader = changeScene(event, "FXMLLogin.FXML");
		FXMLLoginController temp = loader.getController();
		temp.setData(this.users);
	}

	/**
	 * This method changes the scene to the settings screen.
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void settingsButtonPush(ActionEvent event) throws IOException {
		FXMLLoader loader = changeScene(event, "FXMLSettings.FXML");
		FXMLSettingsController settings = loader.getController();
		settings.setData(this.users);
	}

	/**
	 * This method changes the scene to the register screen.
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void registerButtonPush(ActionEvent event) throws IOException {
		FXMLLoader loader = changeScene(event, "FXMLRegister.FXML");
		FXMLRegisterController temp = loader.getController();
		temp.setData(this.users);
	}
	
	public void travelButtonPush(ActionEvent event) throws IOException {
		FXMLLoader loader = changeScene(event, "FXMLTravel.FXML");
		FXMLRegisterController temp = loader.getController();
		temp.setData(this.users);
	}
	
	
	/**
	 * Method that needs to be in the class from implementing Initializable.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

}
