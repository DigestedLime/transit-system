package routenetwork;

public class TrainStation extends Station {

	private String fareType = "TRAIN";
	
	public TrainStation(String name) {
		super(name);
	}
	
	@Override
	public String getFareType() {
		return this.fareType;
	}
	
}
