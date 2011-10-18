package system;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class Node implements Comparable{
	private RoutingTable table = new RoutingTable(this);
	private Node currentDestination;
	private Date lastDestinationChange;
	private Thread helperThread;
	private String nodeName;
	private UUID identifier;
	
	public Node(String name){
		helperThread = new Thread(new NodeHelper(this));
		helperThread.start();
		nodeName = name;
		identifier = UUID.randomUUID();
		currentDestination = null;
		lastDestinationChange = new Date(0);
		SensorFactory.getSensorManager().newNode(this);
	}
	
	public void AddLink(Node n, Link l){
		table.addtLink(n, l);
	}
	
	public void RemoveLink(Node n){
		table.removeLink(n);
	}
	
	public void Update(Map<Node, RoutingTableEntry> updateTable, Node sender){
		table.updateTable(updateTable, sender);
	}
	
	public void BroadcastUpdates(){
		table.broadcastUpdate();
	}
	
	public void setName(String name){
		nodeName = name;
	}
	
	public String getName(){
		return nodeName;
	}
	
	public String toString(){
		return nodeName;
	}
	
	public UUID getIdentifier(){
		return identifier;
	}
	
	public Node getCurrentDestination(){
		return currentDestination;
	}
	
	public synchronized void setCurrentDestination(Node n){
		currentDestination = n;
		table.floodNewDestination(n);
	}
	
	@Override
	public int compareTo(Object o) {
		Node n = (Node) o;
		return identifier.compareTo(n.getIdentifier());
	}
}
