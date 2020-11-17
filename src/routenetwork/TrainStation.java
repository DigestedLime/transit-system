package routenetwork;

/**
 * A bus station has a specific faretype of "BUS" which is used in fare calculations
 */
public class TrainStation extends Station {

	private String fareType = "TRAIN";
	
	/**
	 * 
	 * @param name   name of the station
	 */
	public TrainStation(String name) {
		super(name);
	}
	
	/**
	 * @param station BusStation to link this bus station to
	 */
	public void addBusStation(BusStation station) {
		this.linkedStns.add(station);
		station.addTrainStation(this);
	}
	
}
