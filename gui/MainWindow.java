package gui;

import java.awt.Dimension;

import javax.swing.JFrame;

import system.SystemMain;

public class MainWindow extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SystemMain systemMain;
	public static void main(String args[]){
		initialize();
	}
	
	public static void initialize(){
		MainWindow m = new MainWindow();
		m.setVisible(true);
	}
	
	private MainWindow(){
		this.setTitle("Parking Deck Administrator");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(new Dimension(800, 600));
		systemMain = new SystemMain();
	}
}
