package system;

import java.util.HashSet;
import java.util.Random;

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
		Random r = new Random();
		for(Link l : links){
			l.setLeftCost((double) Math.round(r.nextDouble() * 50));
			l.setRightCost((double) Math.round(r.nextDouble() * 50));
		}
	}
}
