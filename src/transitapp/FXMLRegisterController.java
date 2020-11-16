package transitapp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.*;
import user.CustomerUser;
import user.TravelCard;

/**
 * This class is responsible for registering a new user. It would take in the full name of the user,
 * their email, and the password that they wish to sign in with. It also has a button to go back
 * to the menu, and another button that allows them to go the login screen if they already have
 * an account and do not wish to make another account.
 * @author Lap Khang Tran
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
	
	public void setData(ArrayList<CustomerUser> users) {
		this.users = users;
	}
	
	
	/**
	 * This method changes the screen to the menu screen.
	 * @param event
	 * @throws IOException
	 */
	public void backButtonPush(ActionEvent event) throws IOException {

		changeScene(event, "FXMLMenu.FXML");
	}
	
	/**
	 * This method changes the screen to the login screen.
	 * @param event
	 * @throws IOException
	 */
	public void hasAccountButton(ActionEvent event) throws IOException {
		changeScene(event, "FXMLLogin.FXML");
	}
	
	/**
	 * This method stores the information of the new user when they click the register button
	 * after putting in their information
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
		
		if (!email_used) {
			this.users.add(new CustomerUser(fullName.getText(), password.getText(), email.getText()));
			FileHandler.writetoFile(this.users);
			FXMLLoader loader = changeScene(event, "FXMLDashboard.FXML");
			FXMLDashboardController dashboard = loader.getController();
			dashboard.setData(this.users, this.users.size() - 1);
		} else {
			/* TODO: Might make it clear via message that it didn't work
			 * 
			 */
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