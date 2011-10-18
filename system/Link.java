package system;

import java.util.UUID;

public class Link {
	private Node leftNode;
	private Node rightNode;
	private Double leftCost;
	private Double rightCost;
	private UUID identifier;
	
	protected Link(Node leftNode, Double leftCost, Node rightNode, Double rightCost){
		this.leftNode=leftNode;
		this.rightNode=rightNode;
		this.leftCost=leftCost;
		this.rightCost=rightCost;
		this.identifier = UUID.randomUUID();
		SensorFactory.getSensorManager().newLink(this);
		leftNode.AddLink(rightNode, this);
		rightNode.AddLink(leftNode, this);
		LinkManager.getLinkManager().registerLink(this);
	}
	
	public void setLeftNode(Node n){
		leftNode = n;
	}
	
	public void setRightNode(Node n){
		rightNode = n;
	}
	
	public Double getCost(Node sender){
		if(sender == leftNode){
			return leftCost;
		}else if (sender == rightNode){
			return rightCost;
		}else{
			return Double.MAX_VALUE;
		}
	}
	
	public void setLeftCost(Double cost){
		leftCost = cost;
	}
	
	public void setRightCost(Double cost){
		rightCost = cost;
	}
	
	public UUID getIdentifier(){
		return identifier;
	}
}
