package routenetwork;

import java.util.ArrayList;
import java.util.HashMap;

public class RouteController {

	private ArrayList<TrainStation> route = new ArrayList<>();

	public RouteController() {
		// Nothing required
	}

	public void addStation(TrainStation temp) {
		this.route.add(temp);
	}
	
	public TrainStation getStation(int i) {
		return this.route.get(i);
	}
	
	public int routeLength() {
		return this.route.size();
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
	
	public int stationDistance(TrainStation startStation, TrainStation endStation) {
		int startIndex = -1; int endIndex = -1;
		TrainStation currStn;
		do {
			startIndex++;
			currStn= this.getStation(startIndex);
		} while (currStn != startStation && currStn != endStation);
		for (int i = startIndex; i < this.routeLength(); i++) {
			currStn = this.getStation(i);
			if (currStn == startStation || currStn == endStation) {
				endIndex = i;
			}
		}
		return Math.abs(endIndex - startIndex);
	}
	
	public ArrayList<TrainStation> getTrainRoute (){
		return this.route;
	}
	
	public ArrayList<BusStation> getBusStations () {
		ArrayList<BusStation> busStns = new ArrayList<>();
		for (TrainStation trainStation: this.route) {
			for (Station busStation: trainStation.getLinkedStations()) {
				if (!busStns.contains(busStation)) {
					busStns.add((BusStation) busStation);
				}
			}
		}
		return busStns;
	}
	
	public ArrayList<String> getAllStations () {
		ArrayList<String> allStations = new ArrayList<>();
		for (Station station: this.getTrainRoute()) {
			allStations.add(station.getName());
		}
		for(Station station: this.getBusStations()) {
			allStations.add(station.getName());
		}
		return allStations;
	}
	
	public HashMap<String, Station> getNameToStations () {
		HashMap<String, Station> nameToStations = new HashMap<>();
		for (Station station: this.getTrainRoute()) {
			nameToStations.put(station.getName(), station);
		}
		for(Station station: this.getBusStations()) {
			nameToStations.put(station.getName(), station);
		}
		return nameToStations;
	}
	
	public String toString() {
		ArrayList<String> stationNames = new ArrayList<>();
		for (TrainStation temp : this.route) {
			ArrayList<String> busLineNames = new ArrayList<>();
			for (Station tempTwo : temp.getLinkedStations()) {
				busLineNames.add(tempTwo.getName());
			}
			stationNames.add(temp.getName() + " " + busLineNames);
		}
		return "Route network: " + stationNames;
	}

}
