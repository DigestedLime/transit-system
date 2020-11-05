package routenetwork;

public class Main {

	public static void main(String args[]) {
		// TESTING. FOR REFERENCE WHEN WRITING EVENT PARSER FOR SUBWAY/BUS ROUTES.
		
		RouteController subwayLine = new RouteController();
		
		TrainStation[] trainStations = {new TrainStation("Kipling"), new TrainStation("Islington"), new TrainStation("Royal York")};

		// Kipling
		subwayLine.addStation(trainStations[0]);
		trainStations[0].addBusStation(new BusStation("21B"));
		trainStations[0].addBusStation(new BusStation("21C"));
		
		// Islington
		subwayLine.addStation(trainStations[1]);
		trainStations[1].addBusStation(new BusStation("21D"));
		
		// Royal York
		subwayLine.addStation(trainStations[2]);
		trainStations[2].addBusStation(new BusStation("22A"));
		
		// ==========  TRAVERSAL ===========
		//  Kipling ------> Islington ----> Royal York
		//    | 	            |               |
		// [21B, 21C]         [21D]           [22A]
		// 
		// loop through subwayLine, loop through <station>.getBusLine() {
		//    if we find the bus station, then we have found the correct station
		//    charge fare based on time taken and the number of stations traversed to reach the bus station (busses cost nothing?)
		// }
		
		System.out.println(subwayLine);

	}
	
}
