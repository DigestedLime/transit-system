package routenetwork;

/**
 * A bus station has a specific fare type of "TRAIN" which is used in fare
 * calculations
 */
public class TrainStation extends Station {

	/**
	 * 
	 * @param name name of the station
	 */
	public TrainStation(String name) {
		super(name, "TRAIN");
	}

	/**
	 * @param station BusStation to link this bus station to
	 */
	public void addBusStation(BusStation station) {
		this.linkedStns.add(station);
		station.addTrainStation(this);
	}

}
