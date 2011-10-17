package system;

import java.util.HashSet;

public class SimulatedSensorManager implements SensorManager {
	private HashSet<Node> nodes = new HashSet<Node>();
	private HashSet<Link> links = new HashSet<Link>();
	private Thread helperThread;
	public SimulatedSensorManager(){
		helperThread = new Thread(new SimulatedSensorManagerHelper(this));
		helperThread.start();
	}
	
	@Override
	public void newLink(Link l) {
		links.add(l);
	}

	@Override
	public void newNode(Node n) {
		nodes.add(n);
	}
	
	public void updateCostsAndSpots(){
		System.out.println("Updating links and nodes.");
	}
}
