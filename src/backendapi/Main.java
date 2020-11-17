package backendapi;

public class Main {
	/**
	 * Test file to try out RouteMap.
	 * 
	 * 
	 * @param args
	 */

	public static void main(String args[]) {
		RouteMap subway = new RouteMap();
		subway.initialize("subway_map.txt");
		System.out.println(subway.getRouteMap());
	}

}
