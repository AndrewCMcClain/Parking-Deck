package system;

public class Link {
	Node leftNode;
	Node rightNode;
	Double leftCost;
	Double rightCost;
	public Link(Node leftNode, Node rightNode, Double leftCost, Double rightCost){
		this.leftNode=leftNode;
		this.rightNode=rightNode;
		this.leftCost=leftCost;
		this.rightCost=rightCost;
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
			return -1.00;
		}
	}
}
