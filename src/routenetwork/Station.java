package routenetwork;

import java.util.ArrayList;

public abstract class Station {

	private String name;
	private String fareType;
	private ArrayList<Station> linkedStns = new ArrayList<Station>();
	
	public Station(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		// Stations cannot be renamed? Possibly change to private at a later stage.
		this.name = name;
	}
	
	public ArrayList<Station> getLinkedStations() {
		return this.linkedStns;
	}
	
	public String getFareType() {
		return this.fareType;
	}

	public String toString() {
		return this.getName();
	}
	
}
