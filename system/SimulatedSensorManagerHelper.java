package system;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;


public class SimulatedSensorManagerHelper implements Runnable, ActionListener{

	private SimulatedSensorManager parent;
	private Timer t;
	public SimulatedSensorManagerHelper(SimulatedSensorManager s){
		parent = s;
		t = new Timer(10000, this);
	}
	
	@Override
	public void run() {
		t.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		parent.updateCostsAndSpots();
	}

}
