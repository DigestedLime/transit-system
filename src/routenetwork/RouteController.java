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
