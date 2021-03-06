package transitapp;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.*;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import user.CustomerUser;
import user.TravelCard;

/**
 * This class displays all the information that the user would want to see when
 * they are signed into their account such as their name, their email address,
 * card balance, the departing station, the card that they want to use, adding a
 * new card, ways to load in their card, starting their journey, signing out of
 * the account, and exiting the application.
 *
 */
public class FXMLDashboardController extends ControllerParent implements Initializable {

	public ArrayList<CustomerUser> users;
	public CustomerUser currentUser;
	public TravelCard currentCard;

	@FXML
	public Text fullName;
	@FXML
	public Text email;
	@FXML
	public Text cardBalance;
	@FXML
	public ListView<String> cardList;
	@FXML
	public Text balance;
	@FXML
	public Text averageTripCost;
	@FXML
	public Button toggleActivate;
	@FXML
	public Button status;
	@FXML
	public ListView<String> recentTripsList;

	/**
	 * This method is responsible for populating the data in this class.
	 * 
	 * @param users     passes the list of all CustomerUsers in the system to this
	 *                  controller
	 * @param userIndex passes the index of the user whose dashboard needs to be
	 *                  presented
	 */
	public void setData(ArrayList<CustomerUser> users, int userIndex) {
		this.users = users;
		this.currentUser = users.get(userIndex);
		if (this.currentUser != null) {
			fullName.setText(this.currentUser.getUsername());
			email.setText(this.currentUser.getEmail());
			ArrayList<String> cardNames = new ArrayList<String>();
			for (TravelCard card : this.currentUser.getCards()) {
				cardNames.add(((Integer) card.getID()).toString());
			}
			cardList.setItems(FXCollections.observableArrayList(cardNames));
			if (this.currentUser.getCards().size() != 0) {
				currentCard = this.currentUser.getCards().get(0);
				if (currentCard.isSuspended()) {
					toggleActivate.setText("Unsuspend");
				} else {
					toggleActivate.setText("Suspend");
				}
				cardList.getSelectionModel().select("" + this.currentCard.getID());
				this.update();
			}
			if (this.currentUser.getTrips() != null) {
				recentTripsList.setItems(FXCollections.observableArrayList(this.currentUser.getTrips()));
			}
		}
	}

	/**
	 * This method would sign the user out of the account and return them back to
	 * the menu screen.
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void signOutPush(ActionEvent event) throws IOException {
		FileHandler.writetoFile(this.users);
		this.currentUser = null;
		FXMLLoader loader = changeScene(event, "FXMLMenu.FXML");
		FXMLMenuController menu = loader.getController();
		menu.setData(this.users);
	}

	/**
	 * This method adds 10 dollars to the current card.
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void load10Push(ActionEvent event) throws IOException {
		if (this.currentCard != null) {
			if (!this.currentCard.isSuspended()) {
				status.setText("Added $10 to " + this.currentCard.getID() + ".");
				this.currentCard.addBalance(10);
				this.update();
			} else {
				status.setText("Please unsuspend card to make changes.");
			}
		} else {
			status.setText("Unspecified Error.");
		}
	}

	/**
	 * This method adds 20 dollars to the current card.
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void load20Push(ActionEvent event) throws IOException {
		if (this.currentCard != null) {
			if (!this.currentCard.isSuspended()) {
				status.setText("Added $20 to " + this.currentCard.getID() + ".");
				this.currentCard.addBalance(20);
				this.update();
			} else {
				status.setText("Please unsuspend card to make changes.");
			}
		} else {
			status.setText("Unspecified Error.");
		}
	}

	/**
	 * This method adds 50 dollars to the current card.
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void load50Push(ActionEvent event) throws IOException {
		if (this.currentCard != null) {
			if (!this.currentCard.isSuspended()) {
				status.setText("Added $50 to " + this.currentCard.getID() + ".");
				this.currentCard.addBalance(50);
				this.update();
			} else {
				status.setText("Please unsuspend card to make changes.");
			}
		} else {
			status.setText("Unspecified Error.");
		}
	}

	/**
	 * This method toggles the suspension of the currently selected card and also
	 * updates the button text.
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void toggleSuspension(ActionEvent event) throws IOException {
		for (TravelCard card : this.currentUser.getCards()) {
			if (card.getID() == Integer.parseInt(this.cardList.getSelectionModel().getSelectedItem())) {
				if (card.isSuspended()) {
					status.setText("Card " + this.cardList.getSelectionModel().getSelectedItem() + " unsuspended.");
					card.unSuspendCard();
				} else {
					status.setText("Card " + this.cardList.getSelectionModel().getSelectedItem() + " suspended.");
					card.suspendCard();
				}
				this.update();
			}
		}

	}

	/**
	 * This method removes the selected card from the current user's list of cards.
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void removeCardPush(ActionEvent event) throws IOException {
		if (this.currentUser.getCards().size() > 1) {
			if (this.currentCard.isSuspended()) {
				status.setText("Please unsuspend card to make changes.");
			} else {
				status.setText("Card removed.");
				this.currentUser.getCards().remove(this.currentCard);
				ArrayList<String> cardNames = new ArrayList<String>();
				for (TravelCard card : this.currentUser.getCards()) {
					cardNames.add(((Integer) card.getID()).toString());
				}
				cardList.setItems(FXCollections.observableArrayList(cardNames));
				currentCard = this.currentUser.getCards().get(0);
				cardList.getSelectionModel().select(currentCard.getID() + "");
				this.update();
			}

		} else {
			status.setText("You cannot remove your last card.");
		}
	}

	/**
	 * This method adds a new card for the current user.
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void addCardPush(ActionEvent event) throws IOException {
		this.currentUser.addCard();
		status.setText("Card added.");
		ArrayList<String> cardNames = new ArrayList<String>();
		for (TravelCard card : this.currentUser.getCards()) {
			cardNames.add(((Integer) card.getID()).toString());
		}
		cardList.setItems(FXCollections.observableArrayList(cardNames));

		currentCard = this.currentUser.getCards().get(this.currentUser.getCards().size() - 1);
		cardList.getSelectionModel().select(currentCard.getID() + "");
		this.update();

	}

	/**
	 * This method updates the view to show the data for the currently selected
	 * card.
	 * 
	 */
	public void update() {
		if (cardList != null) {
			if (cardList.getSelectionModel().getSelectedItem() != null) {
				for (TravelCard card : this.currentUser.getCards()) {
					if ((Integer) card.getID() == Integer.parseInt(cardList.getSelectionModel().getSelectedItem())) {
						currentCard = card;
						if (card.isSuspended()) {
							toggleActivate.setText("Unsuspend");
						} else {
							toggleActivate.setText("Suspend");
						}
					}
				}
			}
		}
		FileHandler.writetoFile(this.users);
		DecimalFormat doubleDecimal = new DecimalFormat("0.##");
		cardBalance.setText("$" + doubleDecimal.format(currentCard.getBalance()));
	}

	/**
	 * This method is for exiting the app.
	 * 
	 * @param event
	 * @throws IOException
	 */
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
		

	}

}