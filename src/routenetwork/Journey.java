package routenetwork;

public class Journey {
	
	private String startStnName;
	private String endStnName;
	private String startTime;
	private String endTime;
	
	private Station startStn;
	private Station endStn;
	private RouteController rcontrol;
	
	public Journey (String departingStation, String terminus, String startTime, String endTime, RouteController rcontrol) {
		this(departingStation, terminus, rcontrol);
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public Journey (String departingStation, String terminus, RouteController rcontrol) {
		this.startStnName = departingStation;
		this.endStnName = terminus;
		this.rcontrol = rcontrol;
		this.findStations();
	}
	
	private void findStations() {
		for (Station trainStation: rcontrol.getTrainRoute()) {
			if (trainStation.getName() == startStnName) {
				startStn = trainStation;
			}
			if (trainStation.getName() == endStnName) {
				endStn = trainStation;
			}
			for (Station busStation: trainStation.getLinkedStations()) {
				if (busStation.getName() == startStnName) {
					startStn = busStation;
				}
				if (busStation.getName() == endStnName) {
					endStn = busStation;
				}
			}
		}
	}

	public double calculateFare() {
		/* BUS -> BUS
		 * BUS -> TRAIN
		 * TRAIN -> TRAIN
		 * TRAIN -> BUS
		 */
		
		double fare = 0;
		if (startStn instanceof BusStation && endStn instanceof BusStation) {
			int minDistance = (int) 1e9;
			for (Station firstStation: startStn.getLinkedStations()) {
				for (Station secondStation: endStn.getLinkedStations()) {
					int distance = rcontrol.stationDistance((TrainStation) firstStation, (TrainStation) secondStation);
					if (distance < minDistance){
						minDistance = distance;
					}
				}
			}
			fare = (double) 2 + (0.5* minDistance) + 2;
		} else if (startStn instanceof BusStation && endStn instanceof TrainStation) {
			int minDistance = (int) 1e9;
			for (Station station: startStn.getLinkedStations()) {
				int distance = rcontrol.stationDistance((TrainStation) station, (TrainStation) endStn);
				if (distance < minDistance) {
					minDistance = distance;
				}
			}
			fare = (double) (0.5* minDistance) + 2;
		} else if (startStn instanceof TrainStation && endStn instanceof BusStation) {
			int minDistance = (int) 1e9;
			for (Station station: endStn.getLinkedStations()) {
				int distance = rcontrol.stationDistance((TrainStation) station, (TrainStation) startStn);
				if (distance < minDistance) {
					minDistance = distance;
				}
			}
			fare = (double) (0.5* minDistance) + 2;
		} else {
			//Both are Train
			fare = (double) (0.5*(rcontrol.stationDistance((TrainStation) startStn, (TrainStation)endStn)));
		}
		
		if (fare > 6) {
			return 6.0;
		}
		return fare;
		
	}
}