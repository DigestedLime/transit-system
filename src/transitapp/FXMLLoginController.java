package transitapp;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.*;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.*;
import user.*;

/**
 * This class is responsible for the keeping the information in the textfields
 * when you fill in the information for username and password. It also changes
 * the screen to menu when clicking back and changes the screen to the register
 * screen when the user does not have an account. If an account get signed in,
 * it would change the current screen to the dashboard screen.
 *
 */
public class FXMLLoginController extends ControllerParent implements Initializable {

	@FXML
	private TextField email;
	@FXML
	private TextField password;
	@FXML
	private Text errorMsg;

	private ArrayList<CustomerUser> users;
	
	private static String ADMIN_EMAIL = "admin2312";
	private static String ADMIN_PASSWORD = "557324";
	
	
	/**
	 * @param users passes the list of all CustomerUsers in the system to this
	 *              controller
	 */
	public void setData(ArrayList<CustomerUser> users) {
		this.users = users;
	}

	/**
	 * This method changes the scene by returning to the menu.
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void backButtonPush(ActionEvent event) throws IOException {
		FXMLLoader loader = changeScene(event, "FXMLMenu.FXML");
		FXMLMenuController menu = loader.getController();
		menu.setData(this.users);
	}

	/**
	 * This method changes the scene to the login screen.
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void noAccountButton(ActionEvent event) throws IOException {
		FXMLLoader loader = changeScene(event, "FXMLRegister.FXML");
		FXMLRegisterController register = loader.getController();
		register.setData(this.users);
	}

	/**
	 * This method stores the information from the textfields for the password and
	 * the username and checks that the credentials are correct. It holds the
	 * information to display it onto the dashboard. It also changes screen to the
	 * dashboard.
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void loginButtonPush(ActionEvent event) throws IOException {
		boolean logInSuccess = false;
		if (email.getText().equals(ADMIN_EMAIL) && password.getText().equals(ADMIN_PASSWORD))  {
			FXMLLoader loader = changeScene(event, "FXMLAdmin.FXML");
			FXMLAdminController admin = loader.getController();
			admin.setData(this.users);
		} else {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDashboard.FXML"));
			Parent dashParent = loader.load();
			FXMLDashboardController temp = loader.getController();
			
			int userIndex = 0;
			for (int i = 0; i < this.users.size(); i++) {
				if (this.users.get(i).logIn(password.getText(), email.getText())) {
					userIndex = i;
					errorMsg.setText("");
					logInSuccess = true;
					break;
				}
			}
			
			if (!logInSuccess) {
				errorMsg.setText("Error: Invalid Email/Password");
			} else {
				temp.setData(this.users, userIndex);
				Scene dashScene = new Scene(dashParent);
				dashScene.setFill(Color.TRANSPARENT);
				Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
				stage.setScene(dashScene);
				stage.show();
			}
			
		}

	}

	/**
	 * Method that needs to be in the class from implementing Initializable.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

}
