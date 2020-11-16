package routenetwork;

public class BusStation extends Station {

	private String fareType = "BUS";
	
	public BusStation(String name) {
		super(name);
	}
	
	public void addTrainStation(TrainStation station) {
		this.linkedStns.add(station);
	}
	
	@Override
	public String getFareType() {
		return this.fareType;
	}

}