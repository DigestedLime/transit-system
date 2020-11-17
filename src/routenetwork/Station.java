package routenetwork;

import java.util.ArrayList;

/**
 * 
 * Abstract class representing a station.
 * A station has a name, fareType, and list of stations it is attached to
 */
public abstract class Station {
	
	private String name;
	private String fareType;
	protected ArrayList<Station> linkedStns = new ArrayList<Station>();
	
	/**
	 * Constructs a new station, setting the name for it.
	 * @param name
	 */
	public Station(String name) {
		this.setName(name);
	}
	
	/**
	 * 
	 * @return the name of the station
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @param name new name to change station to.
	 */
	public void setName(String name) {
		// Stations cannot be renamed? Possibly change to private at a later stage.
		this.name = name;
	}
	
	/**
	 * @return stations linked to this one
	 */
	public ArrayList<Station> getLinkedStations() {
		return this.linkedStns;
	}
	
	/**
	 * @return This stations fare type
	 */
	public String getFareType() {
		return this.fareType;
	}
	
	//When printing, just show the stations name
	public String toString() {
		return this.getName();
	}
	
}
