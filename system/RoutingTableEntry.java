package system;

import java.util.Collection;

public class RoutingTableEntry{
	private Node destination;
	private Node nextHop;
	private Double cost;
	
	public RoutingTableEntry(Node destination, Node nextHop, Double cost){
		this.destination = destination;
		this.nextHop = nextHop;
		this.cost = cost;
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
}
