package backendapi;

import java.io.*;
import java.util.Scanner;

import routenetwork.RouteController;
import routenetwork.TrainStation;

public class RouteMap {

	private RouteController subwayLine = null;
	
	public RouteMap() {
		// Nothing
	}

	public RouteController getRouteMap() {
		return this.subwayLine;
	}
	
	public void initialize(String subwayFile, String busFile) {
		try {
			this.readSubway(subwayFile);
			this.readBusStations(busFile);
			
			
		} catch (FileNotFoundException e) {
			System.out.println("Initialization Failed.");
		}
		
	}
	
	private void readBusStations(String file) throws FileNotFoundException {
		// Precondition: readSubway() must be run before running readBusStations().
		File routeFile = new File(file);
		Scanner sc = new Scanner(routeFile);
		while (sc.hasNextLine()) {
			String data = sc.nextLine();
			String tempArray[] = data.split("/");
			if (tempArray.length > 1) {
				// Skip first entry (the station name to add bus stations to)
				for (int a = 1; a < tempArray.length; a++) {
					this.subwayLine.addToStation(tempArray[0], tempArray[a]);
				}
			}
			
		}
	}
	
	private void readSubway(String file) throws FileNotFoundException {
		File routeFile = new File(file);
		RouteController temp = new RouteController();
		Scanner sc = new Scanner(routeFile);
		while (sc.hasNextLine()) {
			String data = sc.nextLine();
			String tempArray[] = data.split("/");
			for (String stationName : tempArray) {
				temp.addStation(new TrainStation(stationName));
			}
		}
		sc.close();
		this.subwayLine = temp;
	}

}
