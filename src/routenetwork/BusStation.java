package routenetwork;

public class BusStation extends Station {

	private String fareType = "BUS";
	
	public BusStation(String name) {
		super(name);
	}
	
	@Override
	public String getFareType() {
		return this.fareType;
	}
	
}
