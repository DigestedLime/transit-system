package routenetwork;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Journey {
	
	private RouteController rcontrol;
	private double currentFare;
	
	private boolean tripEnded;
	
	private Station[] route = new Station[2];
	private LocalDateTime startTime;
	
	private LocalDateTime renewTime;

	public Journey(RouteController rc) {
		this.currentFare = 0.0;
		this.tripEnded = false;
		this.rcontrol = rc;
	}
	
	public boolean tapOn(Station station, LocalDateTime currentTime) {
		if (!this.isTripEnded()) {			
			this.setStartTime(currentTime);
			this.setRenewTime(currentTime);
			this.route[0] = station;
			if (station.getFareType() == "BUS") {
				this.currentFare += 2.0;
				this.tripEnded = true;
				this.route[1] = station;
			}
			return true;
		}
		return false;
	}

	public boolean tapOff(Station station, LocalDateTime currentTime) {
		if (!this.isTripEnded()) {
			this.tripEnded = true;
			this.route[1] = station;
			long durationMinutes = ChronoUnit.MINUTES.between(this.getStartTime(), currentTime);
			this.currentFare += this.calculateFare();
			if (this.currentFare > 6.0) {
				this.currentFare = 6.0;
				if (durationMinutes > 120) {
					if (route[1].getFareType() != route[0].getFareType()) {
						this.currentFare += 2.0; //We assume you spend 2 hours on the train.
					} else if (route[1].getFareType() == "BUS") { //they're both bus
						this.currentFare += 4.0; //We assume you spend 2 hours on the train.
					}
				}
			}
			return true;
		}
		return false;
	}
	
	public double tripFare () {
		return this.currentFare;
	}
	
	public boolean isTripEnded() {
		return this.tripEnded;
	}

	public double calculateFare() {
		/* BUS -> BUS
		 * BUS -> TRAIN
		 * TRAIN -> TRAIN
		 * TRAIN -> BUS
		 */
		
		Station startStn = this.route[0];
		Station endStn = this.route[1];
		RouteController rcontrol = this.rcontrol;
		
		double fare = 0;
		if (startStn.getFareType() == "BUS" && endStn.getFareType() == "BUS") {
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
		} else if (startStn.getFareType() == "BUS" && endStn.getFareType() == "TRAIN") {
			int minDistance = (int) 1e9;
			for (Station station: startStn.getLinkedStations()) {
				int distance = rcontrol.stationDistance((TrainStation) station, (TrainStation) endStn);
				if (distance < minDistance) {
					minDistance = distance;
				}
			}
			fare = (double) (0.5* minDistance) + 2;
		} else if (startStn.getFareType() == "TRAIN" && endStn.getFareType() == "BUS") {
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
			fare = (double) (0.5*(rcontrol.stationDistance((TrainStation) startStn, (TrainStation) endStn)));
		}
		
		if (fare > 6) {
			return 6.0;
		}
		return fare;
		
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}
	
	private void setStartTime(LocalDateTime currentTime) {
		this.startTime = currentTime;
	}

	public void setCurrentFare(double fare) {
		this.currentFare = fare;
	}

	public LocalDateTime getRenewTime() {
		return renewTime;
	}

	public void setRenewTime(LocalDateTime renewTime) {
		this.renewTime = renewTime;
	}
	
	public String toString() {
		return this.route[0].getName() + " -> " +
				this.route[1].getName() + ": " + Double.toString(this.currentFare);
		
	}
}