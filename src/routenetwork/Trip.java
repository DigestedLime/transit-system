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
	
	public Trip(Station start) {
		this.total_cost = 0;
		this.route = new ArrayList<Station>();
		this.current = start;
		this.route.add(start);
	}
	
	// Currently simply caps cost at 6, need to consider time eventually
	public void addStop(Station stop) {
		this.route.add(stop);
		if (this.current.getFareType() == "BUS") {
			this.total_cost += 2;
		} else if (this.current.getFareType() == "TRAIN") {
			this.total_cost += 0.5;
		}
		if (this.total_cost > 6) {
			this.total_cost = 6;
		}
	}
}