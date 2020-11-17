package routenetwork;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is the controller class for the main TrainStation route. It
 * calculates distances between different TrainStations and supports getting the
 * TrainStations, all stations and just the BusStations.
 *
 */

public class RouteController {

	private ArrayList<TrainStation> route = new ArrayList<>();

	/**
	 * Constructs a new RouteController.
	 */
	public RouteController() {
		// Nothing required
	}

	/**
	 * Adds the station to this route.
	 * 
	 * @param trainStation the station to be added.
	 */
	public void addStation(TrainStation trainStation) {
		this.route.add(trainStation);
	}

	/**
	 * Return the station at the index i.
	 * 
	 * @param i
	 * @return return the station at the index i
	 */
	public TrainStation getStation(int i) {
		return this.route.get(i);
	}

	/**
	 * @return return the size of the train route.
	 */
	public int routeLength() {
		return this.route.size();
	}

	/**
	 * @param startStation One of the two stations to calculate the distance for.
	 * @param endStation   One of the two stations to calculate the distance for.
	 * @return returns the distance between startStation and endStation
	 */
	public int stationDistance(TrainStation startStation, TrainStation endStation) {
		int startIndex = -1;
		int endIndex = -1;
		TrainStation currStn;
		do {
			startIndex++;
			currStn = this.getStation(startIndex);
		} while (currStn != startStation && currStn != endStation);
		for (int i = startIndex; i < this.routeLength(); i++) {
			currStn = this.getStation(i);
			if (currStn == startStation || currStn == endStation) {
				endIndex = i;
			}
		}
		return Math.abs(endIndex - startIndex);
	}

	/**
	 * @return returns the route this controller
	 */
	public ArrayList<TrainStation> getTrainRoute() {
		return this.route;
	}

	/**
	 * @return return an ArrayList only containing the bus stations in this route.
	 */
	public ArrayList<BusStation> getBusStations() {
		ArrayList<BusStation> busStns = new ArrayList<>();
		for (TrainStation trainStation : this.route) {
			for (Station busStation : trainStation.getLinkedStations()) {
				if (!busStns.contains(busStation)) {
					busStns.add((BusStation) busStation);
				}
			}
		}
		return busStns;
	}

	/**
	 * @return return an ArrayList containing the names of every station in this
	 *         route.
	 */
	public ArrayList<String> getAllStations() {
		ArrayList<String> allStations = new ArrayList<>();
		for (Station station : this.getTrainRoute()) {
			allStations.add(station.getName());
		}
		for (Station station : this.getBusStations()) {
			allStations.add(station.getName());
		}
		return allStations;
	}

	/**
	 * @return return a HashMap that has key name and value Station. Will contain
	 *         every station in this route.
	 */
	public HashMap<String, Station> getNameToStations() {
		HashMap<String, Station> nameToStations = new HashMap<>();
		for (Station station : this.getTrainRoute()) {
			nameToStations.put(station.getName(), station);
		}
		for (Station station : this.getBusStations()) {
			nameToStations.put(station.getName(), station);
		}
		return nameToStations;
	}

	/**
	 * @return return a string that represents the data in this controller.
	 */
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
