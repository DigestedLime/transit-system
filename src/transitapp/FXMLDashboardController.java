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
import javafx.scene.text.Text;
import javafx.stage.*;

/**
 * This class displays all the information that the user would want to see when they are signed into their account
 * such as their name, their email address, card balance, the departing station, the card that they want to use,
 * adding a new card, ways to load in their card, starting their journey, signing out of the account, 
 * and exiting the application
 * @author Lap Khang Tran
 *
 */
public class FXMLDashboardController extends ControllerParent implements Initializable {

	@FXML
	public Text fullName;
	@FXML
	public Text email;
	
	private String dataFullName = "fullName";
	private String dataEmail = "emailAddress";
	
	/**
	 * This method is responsible for saving the full name and the email information that the user has 
	 * entered previously
	 * @param a
	 * @param b
	 */
	public void setData(String a, String b) {
		
		this.dataFullName = a;
		this.dataEmail = b;
		fullName.setText(a);
		email.setText(b);
	}
	
	/**
	 * This method would sign the user out of the account and return them back to the menu screen.
	 * @param event
	 * @throws IOException
	 */
	public void signOutPush(ActionEvent event) throws IOException {
		
		setData("firstName", "emailAddress");
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