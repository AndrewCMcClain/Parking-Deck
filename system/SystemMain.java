package system;

import gui.MainWindow;

public class SystemMain {
	public SystemMain(){
		SensorManager sm = SensorFactory.getSensorManager();
		Node n1 = new Node("Node A");
		sm.newNode(n1);
		Node n2 = new Node("Node B");
		sm.newNode(n2);
		Node n3 = new Node("Node C");
		sm.newNode(n3);
		
		n1.AddLink(n2, 2.0);
		n2.AddLink(n1, 4.7);
		n1.AddLink(n3, 17.0);
		n2.AddLink(n3, 3.8);
		n3.AddLink(n1, 1.4);
		n3.AddLink(n2, 6.7);
	}
}
