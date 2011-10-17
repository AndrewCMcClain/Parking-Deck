package system;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

public class Node extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1498884115956767205L;
	private RoutingTable table = new RoutingTable(this);
	private Thread helperThread;
	private String nodeName;
	public Node(String name){
		helperThread = new Thread(new NodeHelper(this));
		helperThread.start();
		nodeName = name;
	}
	
	public void AddLink(Node n, Double Cost){
		table.insertLink(n, Cost);
	}
	
	public void RemoveLink(Node n){
		table.removeLink(n);
	}
	
	public void Update(HashMap<Node, RoutingTableEntry> m, Node sender){
		table.updateTable(m, sender);
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
	
	public void UpdateLinkCosts(){
		
	}
}
