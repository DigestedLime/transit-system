package routenetwork;

/**
 * A bus station has a specific faretype of "BUS" which is used in fare calculations
 */
public class BusStation extends Station {

	private String fareType = "BUS";
	
	/**
	 * 
	 * @param name   name of the station
	 */
	public BusStation(String name) {
		super(name);
	}
	
	/**
	 * @param station TrainStation to link this bus station to
	 */
	public void addTrainStation(TrainStation station) {
		this.linkedStns.add(station);
	}
}