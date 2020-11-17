package backendapi;

import java.io.*;
import java.util.Scanner;

import routenetwork.BusStation;
import routenetwork.RouteController;
import routenetwork.TrainStation;


/**
 * This class is responsible for the parsing of the input subway map and 
 * converting it into an object.
 *
 */
public class RouteMap {

	private RouteController subwayLine = null;
	
	public RouteMap() {
		// Nothing
	}
	
	/** Return the route controller associated to this route map.
	 * 
	 * @return RouteController 
	 */
	public RouteController getRouteMap() {
		return this.subwayLine;
	}
	
	/** Opens the file at <subwayFile>, and parses it.
	 * 
	 * @param subwayFile	file name of the subway map
	 */
	public void initialize(String subwayFile) {
		try {
			this.readSystem(subwayFile);
		} catch (FileNotFoundException e) {
			System.out.println("Initialization Failed.");
		}

	}
	
	
	/** Parses the file at <file>.
	 * 
	 * @param file		file name of the subway map
	 * @throws FileNotFoundException
	 */
	private void readSystem(String file) throws FileNotFoundException {
		File routeFile = new File(file);
		RouteController temp = new RouteController();
		Scanner sc = new Scanner(routeFile);
		while (sc.hasNextLine()) {
			String data = sc.nextLine();
			if (data.contains("/")) {
				String splitData[] = data.split("/");
				TrainStation ref = new TrainStation(splitData[0]);
				// Extra validation - checks for when a user adds invalid data 
				// (ie. stationName)
				if (splitData.length > 1) {
					for (int a = 1; a < splitData.length; a++) {
						ref.addBusStation(new BusStation(splitData[a]));
					}
				}
				temp.addStation(ref);
			} else {
				temp.addStation(new TrainStation(data));
			}
		}
		sc.close();
		this.subwayLine = temp;
	}

}
