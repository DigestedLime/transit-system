package routenetwork;

import java.util.ArrayList;

public class BusStation extends Station {

	private String fareType = "BUS";
	private ArrayList<TrainStation> attachedTo = new ArrayList<TrainStation>();
	
	public BusStation(String name) {
		super(name);
	}
	
	public void addTrainStation(TrainStation station) {
		this.attachedTo.add(station);
	}
	
	@Override
	public String getFareType() {
		return this.fareType;
	}

}
