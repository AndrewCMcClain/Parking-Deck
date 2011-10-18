package system;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class RoutingTable {
	 private Map<Node, RoutingTableEntry> table = new TreeMap<Node, RoutingTableEntry>();
	 private Map<Node, Link> links = new TreeMap<Node, Link>();
	 private Node parent;
	 private Boolean sendNewDestination = false;
	 private Node newDestination;
	 
	 public RoutingTable(Node n){
		 parent = n;
	 }
	 
	 public void addtLink(Node n, Link l){
		 table.put(n, new RoutingTableEntry(n, n, l.getCost(parent), new Date(System.currentTimeMillis())));
		 links.put(n, l);
	 }
	 
	 public void removeLink(Node n){
		 if(table.get(n).getNextHop() == n)
			 table.remove(n);
		 links.remove(n);
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
	 
	 public void floodNewDestination(Node destination){
		 sendNewDestination = true;
		 newDestination = destination;
	 }
	 
	 public void broadcastUpdate(){
		 updateLinkCosts();
		 //then broadcast the information to all the neighbors
		 for(Node n : links.keySet()){
			 if(sendNewDestination){
				 n.setCurrentDestination(newDestination);
			 }
			 Map<Node, RoutingTableEntry> update = new TreeMap<Node, RoutingTableEntry>();
			 for(Node m : table.keySet()){
				 if(table.get(m).getNextHop() != n && table.get(m).getDestination() != n){
					 update.put(m, table.get(m));
				 }
			 }
			 n.Update(update, parent);
		 }
		 sendNewDestination=false;
		 printRoutingTableAndLinkCosts();
	 }
	 
	 public void updateTable(Map<Node, RoutingTableEntry> update, Node sender){
		 updateLinkCosts();
		 //Iterate over the nodes that were sent in the update
		 for(Node n : update.keySet()){
			 //If we don't have an entry for that node
			 if(!table.containsKey(n)){
				 //Then add the entry
				 table.put(n, new RoutingTableEntry(n, sender, update.get(n).getCost() + links.get(sender).getCost(parent), new Date(System.currentTimeMillis())));
			 //Otherwise
			 }else{
				 //if the sender is not the next hop
				 if(sender != table.get(n).getNextHop()){
					 //only add the entry if the cost is smaller
					 if(update.get(n).getCost() + links.get(sender).getCost(parent) < table.get(n).getCost()){
						 RoutingTableEntry oldValue = table.put(n, new RoutingTableEntry(n, sender, update.get(n).getCost() + links.get(sender).getCost(parent), new Date(System.currentTimeMillis())));
						 StringBuilder s = new StringBuilder();
						 s.append("Replacing the old value for " + oldValue.getDestination() + " in " + parent.getName() + "\n");
						 s.append("Old Value || Destination : " + oldValue.getDestination() + " || Next Hop : " + oldValue.getNextHop() + " || Cost : " + oldValue.getCost() + "\n");
						 s.append("New Value || Destination : " + update.get(n).getDestination() + " || Next Hop : " + update.get(n).getNextHop() + " || Cost : " + update.get(n).getCost() + "\n");
						 s.append("\n\n");
						 System.out.println(s);
					 }
				 //if the sender is the next hop, then update the cost regardless
				 } else {
					 table.put(n, new RoutingTableEntry(n, sender, update.get(n).getCost() + links.get(sender).getCost(parent), new Date(System.currentTimeMillis())));
				 }
			 }
		 }
		 removeOldEntries();
		 printRoutingTableAndLinkCosts();
	 }
	 
	 private void updateLinkCosts(){
		 for(Node n : links.keySet()){
			 Double costChange = links.get(n).getCost(parent) - table.get(n).getCost();
			 for(Node m : table.keySet()){
				 if(table.get(m).getNextHop() == n){
					 table.get(m).setCost(table.get(m).getCost() + costChange);
				 }
			 }
		 }
	 }
	 
	 private void removeOldEntries(){
		 ArrayList<Node> entriesToRemove = new ArrayList<Node>();
		 for(Node n : table.keySet()){
			 if(System.currentTimeMillis() - table.get(n).getLastUpdate().getTime() >= 60000){
				 entriesToRemove.add(n);
			 }
		 }
		 for(Node n : entriesToRemove){
			 table.remove(n);
		 }
	 }
	 
	 private void printRoutingTableAndLinkCosts(){
		 StringBuilder s = new StringBuilder();
		 s.append("Routing Table for " + parent.getName() + " || UUID : " + parent.getIdentifier() + "\n");
		 if(parent.getCurrentDestination() == null)
			 s.append("Currently Routing to nowhere\n");
		 else
			 s.append("Currently Routing to " + parent.getCurrentDestination() + "\n");
		 s.append("------------------------------\n");
		 for(Node n : table.keySet()){
			 RoutingTableEntry r = table.get(n);
			 s.append("Destination : " + n.getName() + " || Next Hop : " + r.getNextHop() + " || Cost : " + r.getCost() + "\n");
		 }
		 s.append("------------------------------\n");
		 
		 
		 s.append("Link Costs for " + parent.getName() + "\n");
		 for(Node n : links.keySet()){
			 s.append("Cost to reach " + n.getName() + " = " + links.get(n).getCost(parent) + "\n");
		 }
		 s.append("\n\n");
		 System.out.println(s);
	 }
}