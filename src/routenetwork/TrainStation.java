package routenetwork;

import java.util.ArrayList;

public class TrainStation extends Station {

	private String fareType = "TRAIN";
	private ArrayList<BusStation> linkedStns = new ArrayList<BusStation>();
	
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
