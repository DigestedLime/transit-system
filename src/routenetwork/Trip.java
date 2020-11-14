package routenetwork;

import java.util.ArrayList;

/**
 * 
 * 
 * A Trip class that stores the order of stations a trip went through, 
 * and the total cost of the trip
 */
public class Trip {
	
	private float total_cost;
	private ArrayList<Station> route;
	private Station current;
	private boolean trip_ended;
	
	public Trip(Station start) {
		this.total_cost = 0;
		this.route = new ArrayList<Station>();
		this.trip_ended = false;
		this.current = start;
		this.route.add(start);
		if (this.current.getFareType() == "BUS") {
			this.total_cost = 2;
		}
	}
	
	public void enter_station(Station station) {
		if (this.current.getFareType() == "TRAIN") {
			this.total_cost += 0.5;
		}
		this.current = station;
		this.route.add(this.current);
		if (this.current.getFareType() == "BUS") {
			this.total_cost += 2;
		}
		
		if (this.total_cost > 6) {
			this.total_cost = 6;
		}
	}
	/**
	 * Sets current station to null thus ending the trip
	 * 
	 * @return true if it made sense (i.e. they were in a station to exit)
	 *         and false otherwise (i.e. there were not in a station before leaving)
	 */
	public boolean exit_station() {
		if (this.current == null) {
			return false;
		}
		this.current = null;
		return true;
	}
	
	/***
	// Currently simply caps cost at 6, need to consider time eventually
	public void addStop(Station stop) {
		this.route.add(stop);
		if (this.current.getFareType() == "BUS") {
			this.total_cost += 2;
		} else if (this.current.getFareType() == "TRAIN") {
			this.total_cost += 0.5;
		}
		
	}***/
}