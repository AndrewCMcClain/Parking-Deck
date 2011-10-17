package system;

import java.util.ArrayList;
import java.util.HashMap;

public class RoutingTable {
	 private HashMap<Node, RoutingTableEntry> table = new HashMap<Node, RoutingTableEntry>();
	 private HashMap<Node, Link> links = new HashMap<Node, Link>();
	 private Object lockObject = new Object();
	 private Node parentNode;
	 
	 public RoutingTable(Node n){
		 parentNode = n;
	 }
	 
	 public void insertLink(Node n, Double Cost){
		 table.put(n, new RoutingTableEntry(n, n, Cost));
	 }
	 
	 public void removeLink(Node n){
		 table.remove(n);
	 }
	 
	 public Double getCost(Node n){
		 if(table.containsKey(n)){
			 return table.get(n).getCost();
		 }
		 return null;
	 }
	 
	 public Node getNextHop(Node n){
		 if(table.containsKey(n)){
			 return table.get(n).getNextHop();
		 }
		 return null;
	 }
	 
	 public ArrayList<Node> getNeighbors(){
		 ArrayList<Node> neighbors = new ArrayList<Node>();
		 for(Node n : table.keySet()){
			 if(n == table.get(n).getNextHop()){
				 neighbors.add(n);
			 }
		 }
		 return neighbors;
	 }
	 
	 public void UpdateLinkCosts(){
		 
	 }
	 
	 public void broadcastUpdate(){
		 ArrayList<Node> neighbors = getNeighbors();
		 for(Node n : neighbors){
			 HashMap<Node, RoutingTableEntry> tempTable = table;
			 tempTable.remove(n);
			 for(Node m : tempTable.keySet()){
				 if(tempTable.get(m).getNextHop() == n){
					 tempTable.remove(m);
				 }
			 }
			 n.Update(tempTable, parentNode);
		 }
	 }
	 
	 public void updateTable(HashMap<Node, RoutingTableEntry> update, Node sender){
		 if(update != null){
			 for(Node n : update.keySet()){
				 //TODO : The following test of the sender is a workaround, need to ensure that all neighbors are always in the routing table
				 if(table.containsKey(sender)){
					 if(!table.containsKey(n)){
						 try{
						 	table.put(n, new RoutingTableEntry(n, sender, update.get(n).getCost() + table.get(sender).getCost()));
						 }catch (Exception e ){
							 e.printStackTrace();
						 }
					 }else{
						 if(update.get(n).getCost() + table.get(sender).getCost() < table.get(n).getCost()){
							 table.put(n, new RoutingTableEntry(n, sender, update.get(n).getCost() + table.get(sender).getCost()));
						 }
					 }
				 }
			 }
		 }
		 for(Node n : table.keySet()){
			 
		 }
	 }
}
