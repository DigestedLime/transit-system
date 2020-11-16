package routenetwork;

import java.util.ArrayList;

/**
 * 
 * 
 * A Trip class that stores the order of stations a trip went through, 
 * and the total cost of the trip
 */
public class Trip {
	
	private float totalCost;
	private ArrayList<Station> route;
	private Station current;
	private int twoHourPeriods;
	private int totalMinutes;
	
	public Trip(Station start) {
		this.totalCost = 0;
		this.route = new ArrayList<Station>();
		this.current = start;
		this.totalMinutes = 0;
		this.twoHourPeriods = 1;
		
		this.route.add(start);
		if (this.current.getFareType() == "BUS") {
			this.totalCost = 2;
		}
	}
	
	public void enterStation(Station station, int minutes) {
		if (this.current.getFareType() == "TRAIN") {
			this.totalCost += 0.5;
		}
		this.current = station;
		this.route.add(this.current);
		if (this.current.getFareType() == "BUS") {
			this.totalCost += 2;
		}
		
		if (this.totalCost > 6 * this.twoHourPeriods && this.totalMinutes < 120 * this.twoHourPeriods) {
			this.totalCost = 6 * this.twoHourPeriods;
		}
		
		this.totalMinutes += minutes;
		this.twoHourPeriods = (this.totalMinutes / 120) + 1;
	}
	
	public void enterStation(Station station, long minutes) {
		if (minutes > Integer.MAX_VALUE) {
			this.enterStation(station, Integer.MAX_VALUE - 1);
		} else {
			this.enterStation(station, (int) minutes);
		}
	}
	
	
	/**
	 * Sets current station to null thus ending the trip
	 * 
	 * @return true if it made sense (i.e. they were in a station to exit)
	 *         and false otherwise (i.e. there were not in a station before leaving)
	 */
	public boolean exitStation() {
		if (this.current == null) {
			return false;
		}
		this.current = null;
		return true;
	}
	
	/**
	 * 
	 * @return The cost of this trip
	 */
	public float getCost() {
		return this.totalCost;
	}
}