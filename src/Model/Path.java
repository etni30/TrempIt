package Model;

public class Path {

	private Group g;
	private Float walkDistance;
	private Time departureTime;
	private Time arriveTime;
	public Path(Group g, Float walkDistance, Time departureTime, Time arriveTime) {
		super();
		this.g = g;
		this.walkDistance = walkDistance;
		this.departureTime = departureTime;
		this.arriveTime = arriveTime;
	}
	public Group getG() {
		return g;
	}
	public void setG(Group g) {
		this.g = g;
	}
	public Float getWalkDistance() {
		return walkDistance;
	}
	public void setWalkDistance(Float walkDistance) {
		this.walkDistance = walkDistance;
	}
	public Time getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(Time departureTime) {
		this.departureTime = departureTime;
	}
	public Time getArriveTime() {
		return arriveTime;
	}
	public void setArriveTime(Time arriveTime) {
		this.arriveTime = arriveTime;
	}
	
	
}
