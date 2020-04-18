package Model;

public class Edge {
	private String station1;
	private String station2;
	private String city;
	private float distance;
	
	public Edge(String station1, String station2, String city, float distance) {
		super();
		this.station1 = station1;
		this.station2 = station2;
		this.city = city;
		this.distance = distance;
	}
	
	// get and set:
	public String getStation1() {
		return station1;
	}
	public void setStation1(String station1) {
		this.station1 = station1;
	}
	public String getStation2() {
		return station2;
	}
	public void setStation2(String station2) {
		this.station2 = station2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public float getDistance() {
		return distance;
	}
	public void setDistance(float distance) {
		this.distance = distance;
	}
	
}
