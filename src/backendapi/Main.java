package backendapi;

public class Main {

	public static void main(String args[]) {
		RouteMap subway = new RouteMap();
		subway.initialize("subway_map.txt", "bus_map.txt");
		System.out.println(subway.getRouteMap());
	}
	
}
