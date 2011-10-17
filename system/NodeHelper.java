package system;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

public class NodeHelper implements Runnable, ActionListener{
	private Node parent;
	private Timer t;
	private Random r;
	public NodeHelper(Node parent){
		r = new Random();
		t = new Timer(getBackoff(), this);
		this.parent = parent;
	}
	
	@Override
	public void run() {
		System.out.println("Firing up helper thread");
		t.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Firing update for " + parent.getName());
		System.out.println("Updating link costs of " + parent.getName());
		System.out.println("Setting Random Backoff for" + parent.getName());
		int backoff = 1000 * (30 + (((int) Math.random())*10) - 5);
		t.setDelay(getBackoff());
		parent.BroadcastUpdates();
	}
	
	private Integer getBackoff(){
		return 2500 + r.nextInt(1000);
	}
}
