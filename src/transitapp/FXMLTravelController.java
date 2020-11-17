package transitapp;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import backendapi.RouteMap;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import routenetwork.Journey;
import routenetwork.RouteController;
import routenetwork.Station;
import user.CustomerUser;
import user.TravelCard;

/**
 * This class is responsible for the user traveling with card information, and
 * giving a status for the user to know if the trip was successful.
 *
 */
public class FXMLTravelController extends ControllerParent implements Initializable {

	@FXML
	public TextField time;
	@FXML
	public ListView<String> stationList;
	@FXML
	public TextField cardID;
	@FXML
	public Button status;
	@FXML
	public ImageView error;
	@FXML
	public ImageView checkmark;
	@FXML
	public ListView<String> departingStation;
	@FXML
	public ListView<String> terminusStation;

	public RouteController routeController;
	public ArrayList<CustomerUser> users;
	private HashMap<Integer, TravelCard> idToCard;
	private HashMap<String, Station> nameToStations;
	private HashMap<TravelCard, CustomerUser> cardToUser;

	/**
	 * Populates the data for this class.
	 * 
	 * @param users passes the list of all CustomerUsers in the system to this
	 *              controller
	 */
	public void setData(ArrayList<CustomerUser> users) {
		idToCard = new HashMap<>();
		cardToUser = new HashMap<>();
		this.users = users;
		for (CustomerUser user : this.users) {
			for (TravelCard card : user.getCards()) {
				this.idToCard.put(card.getID(), card);
				this.cardToUser.put(card, user);
			}
		}
	}

	/**
	 * Returns the station object that corresponds to this name.
	 * 
	 * @param name of a station
	 * @return return the station corresponding to this name.
	 */
	public Station findStation(String name) {
		return this.nameToStations.get(name);
	}

	/**
	 * This method ends a journey with the selected inputs.
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void journeyOffPush(ActionEvent event) throws IOException {
		if (!this.cardID.getText().matches("\\d+")) {
			status.setText("Error: Card IDs must be numeric.");
		} else if (!this.idToCard.containsKey(Integer.parseInt(this.cardID.getText()))) {
			status.setText("Error: Invalid Card.");
		} else {
			try {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
				LocalDateTime currTime = LocalDateTime.parse(time.getText(), formatter);
				TravelCard currentCard = this.getCardUsingID(this.cardID.getText());
				Journey currentJourney = currentCard.getCurrentJourney();
				currentJourney.tapOff(this.findStation(this.stationList.getSelectionModel().getSelectedItem()),
						currTime);
				Journey prevJourney = currentCard.getPrevJourney();
				if (prevJourney != null) {
					long durationMinutes = ChronoUnit.MINUTES.between(prevJourney.getStartTime(), currTime);
					if (durationMinutes < 120) {
						currentJourney.setCurrentFare(0.0);
						currentJourney.setRenewTime(prevJourney.getRenewTime());
					}
				}
				this.cardToUser.get(currentCard)
						.addTripString("[" + currentCard.getID() + "] " + currentJourney.toString());
			} catch (Exception e) {
				status.setText("Error: Invalid date.");
			}
		}
	}

	/**
	 * This method starts a journey with the selected inputs.
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void journeyPush(ActionEvent event) throws IOException {
		if (!this.cardID.getText().matches("\\d+")) {
			status.setText("Error: Card IDs must be numeric.");
		} else if (!this.idToCard.containsKey(Integer.parseInt(this.cardID.getText()))) {
			status.setText("Error: Invalid card.");
		} else if (this.idToCard.get(Integer.parseInt(this.cardID.getText())).cannotPay()) {
			status.setText("Error: Please load your card.");
		} else {
			try {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
				LocalDateTime currTime = LocalDateTime.parse(time.getText(), formatter);
				TravelCard currentCard = this.getCardUsingID(this.cardID.getText());
				Journey currentJourney = new Journey(this.routeController);
				currentJourney.tapOn(this.findStation(this.stationList.getSelectionModel().getSelectedItem()),
						currTime);
				currentCard.addJourney(currentJourney);
			} catch (Exception e) {
				status.setText("Error: Invalid date.");
			}
		}
	}

	/**
	 * This method returns the TravelCard associated to this id.
	 * 
	 * @param id of a card
	 * @return returns null if the id is not valid.
	 */
	private TravelCard getCardUsingID(String id) {
		return this.idToCard.get(Integer.parseInt(id));
	}

	/**
	 * This method changes the scene to the menu screen.
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void backBtnPush(ActionEvent event) throws IOException {
		FXMLLoader loader = changeScene(event, "FXMLMenu.FXML");
		FXMLMenuController temp = loader.getController();
		temp.setData(this.users);
	}

	/**
	 * Method that needs to be in the class from implementing Initializable.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		RouteMap map = new RouteMap();
		map.initialize("subway_map.txt");
		this.routeController = map.getRouteMap();


		this.stationList.setItems(FXCollections.observableArrayList(this.routeController.getAllStations()));   
		this.nameToStations = this.routeController.getNameToStations();
	}
}
