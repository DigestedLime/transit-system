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
 * This class is responsible for the user travelling with card information, and
 * giving a status for the user to know if the trip was successful.
 * 
 * @author
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
	private HashMap<Integer, TravelCard> idToCard = new HashMap<Integer, TravelCard>();
	private HashMap<String, Station> nameToStations;
	
	/**
	 * @param users passes the list of all CustomerUsers in the system to this controller
	 */
	public void setData(ArrayList<CustomerUser> users) {
		this.users = users;
		for (CustomerUser user : this.users) {
			for (TravelCard card : user.getCards()) {
				this.idToCard.put(card.getID(), card);
			}
		}
	}

	public Station findStation(String a) {
		return this.nameToStations.get(a);
	}

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
				System.out.println(currentCard.pay((float) currentJourney.tripFare()));
				System.out.println(currentCard.getBalance());
				System.out.println(currentJourney.tripFare());
				System.out.println(currentJourney.calculateFare());
			} catch (Exception e) {
				status.setText("Error: Invalid date.");
				e.printStackTrace();
			}
		}
	}

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
				System.out.println(currentJourney);
				System.out.println(currentCard);
				currentCard.addJourney(currentJourney);
				System.out.println(currentCard.getBalance());
			} catch (Exception e) {
				status.setText("Error: Invalid date.");
				e.printStackTrace();
			}
		}
	}

	private TravelCard getCardUsingID(String id) {
		return this.idToCard.get(Integer.parseInt(id));
	}

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

		RouteMap temp = new RouteMap();
		temp.initialize("subway_map.txt");
		this.routeController = temp.getRouteMap();

		this.stationList.setItems(FXCollections.observableArrayList(this.routeController.getAllStations()));   
		this.nameToStations = this.routeController.getNameToStations();

	}
}
