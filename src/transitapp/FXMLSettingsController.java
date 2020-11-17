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
 * This class shows the settings options and currently only has the back and the
 * exit button.
 * @author
 *
 */
public class FXMLSettingsController extends ControllerParent implements Initializable {
	private ArrayList<CustomerUser> users;
	
	/**
	 * @param users passes the list of all CustomerUsers in the system to this controller
	 */
	public void setData(ArrayList<CustomerUser> users) {
		this.users = users;
	}
	
	
	/**
	 * This method changes the scene to the menu screen.
	 * @param event
	 * @throws IOException
	 */
	public void backButtonPush(ActionEvent event) throws IOException {
		FXMLLoader loader = changeScene(event, "FXMLMenu.FXML");
		FXMLMenuController menu = loader.getController();
		menu.setData(this.users);

	}
	
	/**
	 * Method that needs to be in the class from implementing Initializable.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}
}