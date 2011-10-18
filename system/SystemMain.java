package system;

public class SystemMain {
	public SystemMain(){
		Node n1 = new Node("Node A");
		Node n2 = new Node("Node B");
		Node n3 = new Node("Node C");
		new Link(n1, 1.4, n2, 12.5);
		new Link(n1, 4.6, n3, 3.8);
		new Link(n2, 2.7, n3, 3.1);
		n1.setCurrentDestination(n3);
		try {
			Thread.sleep(10000);
			n1.setCurrentDestination(n2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
