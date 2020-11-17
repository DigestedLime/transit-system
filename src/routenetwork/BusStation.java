package routenetwork;

/**
 * A bus station has a specific fare type of "BUS" which is used in fare
 * calculations
 */
public class BusStation extends Station {

	/**
	 * 
	 * @param name name of the station
	 */
	public BusStation(String name) {
		super(name, "BUS");
	}

	/**
	 * @param station TrainStation to link this bus station to
	 */
	public void addTrainStation(TrainStation station) {
		this.linkedStns.add(station);
	}
}