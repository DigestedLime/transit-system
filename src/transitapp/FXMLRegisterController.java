package transitapp;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.control.TextField;
import user.CustomerUser;

/**
 * This class is responsible for registering a new user. It would take in the
 * full name of the user, their email, and the password that they wish to sign
 * in with. It also has a button to go back to the menu, and another button that
 * allows them to go the login screen if they already have an account and do not
 * wish to make another account.
 *
 */
public class FXMLRegisterController extends ControllerParent implements Initializable {

	@FXML
	private TextField fullName;
	@FXML
	private TextField email;
	@FXML
	private TextField password;

	private ArrayList<CustomerUser> users;
	
	private static String ADMIN_EMAIL = "admin2312";
	
	/**
	 * @param users passes the list of all CustomerUsers in the system to this
	 *              controller
	 */
	public void setData(ArrayList<CustomerUser> users) {
		this.users = users;
	}

	/**
	 * This method changes the screen to the menu screen.
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
	 * This method changes the screen to the login screen.
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void hasAccountButton(ActionEvent event) throws IOException {
		FXMLLoader loader = changeScene(event, "FXMLLogin.FXML");
		FXMLLoginController login = loader.getController();
		login.setData(this.users);
	}

	/**
	 * This method stores the information of the new user when they click the
	 * register button after putting in their information
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void regButtonPush(ActionEvent event) throws IOException {
		boolean email_used = false;
		for (int i = 0; i < this.users.size(); i++) {
			if (this.users.get(i).getEmail().equals(email.getText())) {
				email_used = true;
			}
		}
		
		if (email.getText() == ADMIN_EMAIL) {
			email_used = true;
		}
		
		if (!email_used && email.getText().trim().length() > 0 && 
				password.getText().trim().length() > 0 && !email.getText().contains(" ")) {
			CustomerUser temp = new CustomerUser(fullName.getText(), password.getText(), email.getText());
			temp.addCard();
			this.users.add(temp);
			FileHandler.writetoFile(this.users);
			FXMLLoader loader = changeScene(event, "FXMLDashboard.FXML");
			FXMLDashboardController dashboard = loader.getController();
			dashboard.setData(this.users, this.users.size() - 1);
		}

	}

	/**
	 * Method that needs to be in the class from implementing Initializable.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}