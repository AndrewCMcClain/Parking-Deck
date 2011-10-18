package system;

import java.util.Collection;
import java.util.Date;

public class RoutingTableEntry{
	private Node destination;
	private Node nextHop;
	private Double cost;
	private Date lastUpdate;
	
	public RoutingTableEntry(Node destination, Node nextHop, Double cost, Date currentTime){
		this.destination = destination;
		this.nextHop = nextHop;
		this.cost = cost;
		lastUpdate = currentTime;
	}
	
	public Node getDestination() {
		return destination;
	}
	public Node getNextHop() {
		return nextHop;
	}
	public void setNextHop(Node nextHop) {
		this.nextHop = nextHop;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	
	public Date getLastUpdate(){
		return lastUpdate;
	}
	
	public void touch(){
		lastUpdate = new Date(System.currentTimeMillis());
	}
}
