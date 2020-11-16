package transitapp;

import java.io.IOException;
import java.net.URL;
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
import routenetwork.RouteController;

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
	public ListView<String> currentStation;
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
	
	
	public void startJourneyPush(ActionEvent event) throws IOException {
		// TODO
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
