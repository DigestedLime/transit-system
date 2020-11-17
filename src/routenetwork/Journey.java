package routenetwork;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * This class is the main journey class that represents trips, and also
 * calculates the fare. This class supports tapping on, tapping off, calculating
 * the trip fare, and getters and setters for the data.
 *
 */
public class Journey {

	private RouteController rcontrol;
	private double currentFare;

	private boolean tripEnded;

	private Station[] route = new Station[2];
	private LocalDateTime startTime;

	// This time represents when the overall trip started, as it can last through
	// multiple journey segments
	private LocalDateTime renewTime;

	public Journey(RouteController rc) {
		this.currentFare = 0.0;
		this.tripEnded = false;
		this.rcontrol = rc;
	}

	/**
	 * Sets the time variables and the route variable, and also if this journey
	 * starts at a bus station, creates the fare and immediately ends the journey.
	 *
	 * @param station     the station that this journey starts at
	 * @param currentTime the time at which we tap on
	 * @return returns if the tap on event successfully occurred, returns false if
	 *         this journey already ended.
	 */
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

	/**
	 * Ends the journey, sets the time variables and the route variable, and
	 * calculates the fare. If the trip starts at a TrainStation and ends at a
	 * BusStation (or vice-versa), we assume that the 2 hour "$6.0" cap is used up
	 * on the train segment (if that even applies).
	 * 
	 * @param station     the station that this journey ends at
	 * @param currentTime the time at which we tap off
	 * @return returns if the tap off event successfully occurred, returns false if
	 *         this journey already ended.
	 */
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
						this.currentFare += 2.0; // We assume you spend 2 hours on the train.
					} else if (route[1].getFareType() == "BUS") { // they're both bus
						this.currentFare += 4.0; // We assume you spend 2 hours on the train.
					}
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * @return return the fare of this journey.
	 */
	public double tripFare() {
		return this.currentFare;
	}

	/**
	 * @return return true if this trip ended.
	 */
	public boolean isTripEnded() {
		return this.tripEnded;
	}

	/**
	 * @return return the fare corresponding to route in rcontrol, and the start and
	 *         end station specified in route.
	 */
	public double calculateFare() {
		/*
		 * BUS -> BUS BUS -> TRAIN TRAIN -> TRAIN TRAIN -> BUS
		 */

		Station startStn = this.route[0];
		Station endStn = this.route[1];
		RouteController rcontrol = this.rcontrol;

		double fare = 0;
		if (startStn.getFareType() == "BUS" && endStn.getFareType() == "BUS") {
			int minDistance = (int) 1e9;
			for (Station firstStation : startStn.getLinkedStations()) {
				for (Station secondStation : endStn.getLinkedStations()) {
					int distance = rcontrol.stationDistance((TrainStation) firstStation, (TrainStation) secondStation);
					if (distance < minDistance) {
						minDistance = distance;
					}
				}
			}
			fare = (double) 2 + (0.5 * minDistance) + 2;
		} else if (startStn.getFareType() == "BUS" && endStn.getFareType() == "TRAIN") {
			int minDistance = (int) 1e9;
			for (Station station : startStn.getLinkedStations()) {
				int distance = rcontrol.stationDistance((TrainStation) station, (TrainStation) endStn);
				if (distance < minDistance) {
					minDistance = distance;
				}
			}
			fare = (double) (0.5 * minDistance) + 2;
		} else if (startStn.getFareType() == "TRAIN" && endStn.getFareType() == "BUS") {
			int minDistance = (int) 1e9;
			for (Station station : endStn.getLinkedStations()) {
				int distance = rcontrol.stationDistance((TrainStation) station, (TrainStation) startStn);
				if (distance < minDistance) {
					minDistance = distance;
				}
			}
			fare = (double) (0.5 * minDistance) + 2;
		} else {
			// Both are Train
			fare = (double) (0.5 * (rcontrol.stationDistance((TrainStation) startStn, (TrainStation) endStn)));
		}

		if (fare > 6) {
			return 6.0;
		}
		return fare;

	}

	/**
	 * @return returns the start time
	 */
	public LocalDateTime getStartTime() {
		return startTime;
	}

	/**
	 * Sets the start time as <currentTime>.
	 * 
	 * @param currentTime
	 */
	private void setStartTime(LocalDateTime currentTime) {
		this.startTime = currentTime;
	}

	/**
	 * Sets the current fare as <fare>.
	 * 
	 * @param currentTime
	 */
	public void setCurrentFare(double fare) {
		this.currentFare = fare;
	}

	/**
	 * @return return the renew time
	 */
	public LocalDateTime getRenewTime() {
		return renewTime;
	}

	/**
	 * Sets this renew time to <renewTime>.
	 * 
	 * @param renewTime
	 */
	public void setRenewTime(LocalDateTime renewTime) {
		this.renewTime = renewTime;
	}

	/**
	 * @return returns the string representation of this route.
	 */
	public String toString() {
		return this.route[0].getName() + " -> " + this.route[1].getName() + ": " + Double.toString(this.currentFare);

	}
}