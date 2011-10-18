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
		t.setDelay(getBackoff());
		parent.BroadcastUpdates();
	}
	
	private Integer getBackoff(){
		return 2500 + r.nextInt(1000);
	}
}
