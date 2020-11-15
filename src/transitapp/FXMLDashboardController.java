package transitapp;

import java.awt.List;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import backendapi.RouteMap;
import javafx.collections.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.*;
import routenetwork.BusStation;
import routenetwork.Journey;
import routenetwork.RouteController;
import routenetwork.Station;
import routenetwork.TrainStation;
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
	
	public CustomerUser currentUser;
	public TravelCard currentCard;
	public RouteController routeController;
	
	@FXML
	public Text fullName;
	@FXML
	public Text email;
	@FXML
	public Text cardBalance;
	@FXML
	public ListView<TravelCard> cardList;
	@FXML
	public ListView<String> departingStation;
	@FXML
	public ListView<String> terminusStation;
	
	
	private String dataFullName = "fullName";
	private String dataEmail = "emailAddress";
	
	/**
	 * This method is responsible for saving the full name and the email information that the user has 
	 * entered previously
	 * @param a
	 * @param b
	 */
	public void setData(CustomerUser current_user) {
		this.currentUser = current_user;
		if (this.currentUser != null) {
			fullName.setText(this.currentUser.getUsername());
			email.setText(this.currentUser.getEmail());
			if (this.currentUser.getCards().size() != 0) {
				currentCard = this.currentUser.getCards().get(0);
				this.update();
			}
		}
	}
	
	/**
	 * This method would sign the user out of the account and return them back to the menu screen.
	 * @param event
	 * @throws IOException
	 */
	public void signOutPush(ActionEvent event) throws IOException {
		setData(null); //TODO: THIS WILL ERROR
		changeScene(event, "FXMLMenu.FXML");
	}
	
	/**
	 * This method adds 10 dollars to the current card.
	 * @param event
	 * @throws IOException
	 */
	public void load10Push(ActionEvent event) throws IOException {
		this.currentCard.addBalance(10);
		this.update();
	}
	
	/**
	 * This method adds 20 dollars to the current card.
	 * @param event
	 * @throws IOException
	 */
	public void load20Push(ActionEvent event) throws IOException {
		this.currentCard.addBalance(20);
		this.update();
	}
	
	/**
	 * This method adds 50 dollars to the current card.
	 * @param event
	 * @throws IOException
	 */
	public void load50Push(ActionEvent event) throws IOException {
		this.currentCard.addBalance(50);
		this.update();
	}
	
	/**
	 * This method adds a new card for the current user.
	 * @param event
	 * @throws IOException
	 */
	public void addCardPush(ActionEvent event) throws IOException {
		this.currentUser.addCard();
		currentCard = this.currentUser.getCards().get(this.currentUser.getCards().size() - 1);
		this.update();

	}
	
	public void startJourneyPush(ActionEvent event) throws IOException {
		String depart = departingStation.getSelectionModel().getSelectedItem();
		String end = terminusStation.getSelectionModel().getSelectedItem();
		System.out.println("Depart from: " + depart + ", End at: " + end);
		Journey j = new Journey(depart, end, this.routeController);
		this.currentCard.pay((float) j.calculateFare());
		this.update();
	}
	
	
	public void update() {
		cardBalance.setText("$" + Float.toString(currentCard.getBalance()));
	}
	
	
	/**
	 * Method that needs to be in the class from implementing Initializable.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		RouteMap temp = new RouteMap();
		temp.initialize("subway_map.txt");
		this.routeController = temp.getRouteMap();
		
		departingStation.setItems(FXCollections.observableArrayList(this.routeController.getAllStations()));
		terminusStation.setItems(FXCollections.observableArrayList(this.routeController.getAllStations()));
		
		
		
	}

}