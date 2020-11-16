package routenetwork;

public class TrainStation extends Station {

	private String fareType = "TRAIN";
	
	public TrainStation(String name) {
		super(name);
	}
	
	public void addBusStation(BusStation station) {
		this.linkedStns.add(station);
		station.addTrainStation(this);
	}
	
	@Override
	public String getFareType() {
		return this.fareType;
	}
	
	
}
