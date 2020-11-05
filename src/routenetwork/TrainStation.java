package routenetwork;

import java.util.ArrayList;

public class TrainStation extends Station {

	private String fareType = "TRAIN";
	private ArrayList<BusStation> attachedTo = new ArrayList<BusStation>();
	
	public TrainStation(String name) {
		super(name);
	}
	
	public void addBusStation(BusStation station) {
		this.attachedTo.add(station);
	}
	
	public ArrayList<BusStation> getLinkedStations() {
		return this.attachedTo;
	}
	
	@Override
	public String getFareType() {
		return this.fareType;
	}
	
	
}
