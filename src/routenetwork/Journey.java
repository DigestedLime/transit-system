package routenetwork;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Journey {
	
	private LocalDateTime startTime;
	private LocalDateTime lastTime;
	
	private Station startStn;
	private RouteController rcontrol;
	private Trip trip;
	
	private long duration;
	private boolean unlimTravel;
	private double currentFare;
	
	private boolean tripEnded;
	
	private ArrayList<Station> route;
	
//	public Journey (String departingStation, String terminus, String startTime, String endTime, RouteController rcontrol) {
//		this(departingStation, terminus, rcontrol);
//		//	this.startTime = startTime;
//		this.endTime = endTime;
//	}
//	
//	public Journey (String departingStation, String terminus, RouteController rcontrol) {
//		this.startStnName = departingStation;
//		this.endStnName = terminus;
//		this.rcontrol = rcontrol;
//		this.findStations();
//	}
	
	
	public Journey(LocalDateTime startTime, Station startingStation) {
		this.startTime = startTime;
		this.lastTime = startTime;
		this.startStn = startingStation;
		this.trip = new Trip(startingStation);
		this.route = new ArrayList<>();
		this.route.add(startingStation);
		this.duration = 0;
		this.currentFare = 0.0;
		this.tripEnded = false;
	}
	
	public void tapOn(Station station, LocalDateTime currentTime) {
		if (!this.tripEnded) {
			long minutes = ChronoUnit.MINUTES.between(this.lastTime, currentTime);
			this.lastTime = currentTime;
			this.route.add(station);
			Station prevStation = this.route.get(this.route.size() - 1);
			double fare = Journey.calculateFare(prevStation, station, this.rcontrol);
			
			if (this.currentFare >= 6.0 && duration >= 120) {
				this.unlimTravel = true;
			} 
			
			if (this.unlimTravel) {
				this.currentFare = 6.0;
			}
		}
		
		
	}
	
	public void tapOff(Station station) {
		this.tripEnded = true;
	}
	
	public double tripFare () {
		return this.currentFare;
	}
	
	public boolean isTripEnded() {
		return this.tripEnded;
	}
	
	

	public static double calculateFare(Station startStn, Station endStn, RouteController rcontrol) {
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