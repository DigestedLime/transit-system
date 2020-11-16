package transitapp;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import backendapi.RouteMap;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
 * This class is responsible for the user travelling with card information, and giving a status for the user
 * to know if the trip was successful.
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
    private HashMap<Integer, TravelCard> idToCard;
	private HashMap<String, Station> nameToStations;
	
    
    public void setData(ArrayList<CustomerUser> users) {
    	this.users = users;
    	for (CustomerUser user: this.users) {
    		for (TravelCard card : user.getCards()) {
    			this.idToCard.put(card.getID(), card);
    		}
    	}
    }
    
	
	public void journeyPush(ActionEvent event) throws IOException {
		Journey currentJourney = this.getCardUsingID(this.cardID.getText()).getCurrentJourney();
		if (currentJourney == null) {
			//start journey
		} else if (currentJourney.isTripEnded()) {
			//make it null
		} else {
			//continue journey/end journey
		}
		
//		String depart = departingStation.getSelectionModel().getSelectedItem();
//		String end = terminusStation.getSelectionModel().getSelectedItem();
//		System.out.println("Depart from: " + depart + ", End at: " + end);
//		Journey j = new Journey(depart, end, this.routeController);
//		String id = this.cardID.getText();
//		TravelCard currentCard = getCardUsingID(id);
//		currentCard.pay((float) j.calculateFare());
	}
	
	
	private TravelCard getCardUsingID(String id) {
		return this.idToCard.get(Integer.parseInt(id));
	}

	
//	public void update() {
//		if (stationList != null) {
//			if (cardList.getSelectionModel().getSelectedItem() != null) {
//				for (TravelCard card : this.currentUser.getCards()) {
//					if ((Integer) card.getID() == Integer.parseInt(cardList.getSelectionModel().getSelectedItem())) {
//						currentCard = card;
//						if (card.isSuspended()) {
//							toggleActivate.setText("Unsuspend");
//						} else {
//							toggleActivate.setText("Suspend");
//						}
//					}
//				}
//			}
//		}
//		DecimalFormat doubleDecimal = new DecimalFormat("0.##");
//		System.out.println(currentCard.getBalance());
//		cardBalance.setText("$" + doubleDecimal.format(currentCard.getBalance()));
//	}
	
	/**
     * Method that needs to be in the class from implementing Initializable.
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	
        RouteMap temp = new RouteMap();
        temp.initialize("subway_map.txt");
        this.routeController = temp.getRouteMap();
        
        this.stationList.setItems(FXCollections.observableArrayList(this.routeController.getAllStations()));
//        departingStation.setItems(FXCollections.observableArrayList(this.routeController.getAllStations()));
//        terminusStation.setItems(FXCollections.observableArrayList(this.routeController.getAllStations()));
//        
        this.nameToStations = this.routeController.getNameToStations();
    
    }
}
