package routenetwork;
import java.util.ArrayList;

public class RouteController {

	ArrayList<TrainStation> route = new ArrayList<TrainStation>();
	
	public RouteController() {	
		// Nothing required
	}
	
	public void addStation(TrainStation temp) {
		this.route.add(temp);
	}
	
	public boolean addToStation(String stationName, String busStation) {
		// Precondition: <stationName> must be a station on the subway
		// Validation will be added later (both in the GUI and here).
		// Edit: Returns true if bus linked sucessfully to station.
		// Returns false if station does not exist.
		for (TrainStation temp : this.route) {
			if (temp.getName().equals(stationName)) {
				temp.addBusStation(new BusStation(busStation));
				return true;
			}
		}
		return false;
	}
	
	public String toString() {
		ArrayList<String> stationNames = new ArrayList<String>();
		for (TrainStation temp : this.route) {
			ArrayList<String> busLineNames = new ArrayList<String>();
			for (BusStation tempTwo : temp.getLinkedStations()) {
				busLineNames.add(tempTwo.getName());
			}
			stationNames.add(temp.getName() + " " + busLineNames);
		}
		return "Route network: " + stationNames;
	}
	
}
