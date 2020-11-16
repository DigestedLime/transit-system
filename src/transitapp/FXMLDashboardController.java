package transitapp;

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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.*;
import user.CustomerUser;
import user.TravelCard;
import user.User;

/**
 * This class displays all the information that the user would want to see when they are signed into their account
 * such as their name, their email address, card balance, the departing station, the card that they want to use,
 * adding a new card, ways to load in their card, starting their journey, signing out of the account, 
 * and exiting the application
 * @author Lap Khang Tran
 *
 */
public class FXMLDashboardController extends ControllerParent implements Initializable {
	
	public CustomerUser current_user;
	public ArrayList<CustomerUser> users;
	public TravelCard current_card;
	
	@FXML
	public Text fullName;
	@FXML
	public Text email;
	@FXML
	public Text cardBalance;
	@FXML
	public ListView cardList;
	
	private String dataFullName = "fullName";
	private String dataEmail = "emailAddress";
	
	/**
	 * This method is responsible for saving the full name and the email information that the user has 
	 * entered previously
	 * @param a
	 * @param b
	 */
	public void setData(ArrayList<CustomerUser> users, int index) {
		this.users = users;
		this.current_user = users.get(index);
		fullName.setText(this.current_user.getUsername());
		email.setText(this.current_user.getEmail());
		if (this.current_user.getCards().size() != 0) {
			current_card = this.current_user.getCards().get(0);
			this.update();
		}
	}
	
	/**
	 * This method would sign the user out of the account and return them back to the menu screen.
	 * @param event
	 * @throws IOException
	 */
	public void signOutPush(ActionEvent event) throws IOException {
		FileHandler.writetoFile(this.users);
		changeScene(event, "FXMLMenu.FXML");
	}
	
	/**
	 * This method adds 10 dollars to the current card.
	 * @param event
	 * @throws IOException
	 */
	public void load10Push(ActionEvent event) throws IOException {
		if (this.current_card != null) {
			this.current_card.addBalance(10);
			this.update();
		}
	}
	
	/**
	 * This method adds 20 dollars to the current card.
	 * @param event
	 * @throws IOException
	 */
	public void load20Push(ActionEvent event) throws IOException {
		if (this.current_card != null) {
			this.current_card.addBalance(20);
			this.update();
		}
	}
	
	/**
	 * This method adds 50 dollars to the current card.
	 * @param event
	 * @throws IOException
	 */
	public void load50Push(ActionEvent event) throws IOException {
		if (this.current_card != null) {
			this.current_card.addBalance(50);
			this.update();
		}
	}
	
	/**
	 * This method adds a new card for the current user.
	 * @param event
	 * @throws IOException
	 */
	public void addCardPush(ActionEvent event) throws IOException {
		this.current_user.addCard();
		current_card = this.current_user.getCards().get(this.current_user.getCards().size() - 1);
		this.update();

	}
	
	public void update() {
		cardBalance.setText("$" + Float.toString(current_card.getBalance()));
	}
	
	@Override
	public void exitButton(ActionEvent event) throws IOException {
		FileHandler.writetoFile(this.users);
		super.exitButton(event);
	}
	
	
	/**
	 * Method that needs to be in the class from implementing Initializable.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}