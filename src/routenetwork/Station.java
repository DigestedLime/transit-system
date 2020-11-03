package routenetwork;

public abstract class Station {

	private String name;
	
	public Station(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		// Stations cannot be renamed? Possibly change to private at a later stage.
		this.name = name;
	}
	
	public String getFareType() {
		return null;
	}
	
}
