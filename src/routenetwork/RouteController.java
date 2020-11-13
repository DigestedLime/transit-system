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
		// Future method for GUI. Adds new bus station to <stationName>.
		// Precondition: <stationName> must exist; if not, this will return false.
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
