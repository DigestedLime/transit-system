package routenetwork;
import java.util.ArrayList;

public class RouteController {

	ArrayList<Station> route = new ArrayList<Station>();
	
	public RouteController() {	
		// Nothing required
	}
	
	public void addStation(Station temp) {
		this.route.add(temp);
	}
	
	public String toString() {
		return "Route network: " + this.route;
	}
	
}
