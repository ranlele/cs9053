package common;

import java.awt.FlowLayout;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;

import bridge.Bridge;

public class MainPage extends JFrame {
	static final int WIDTH = 1024;
	static final int HEIGHT = 850;
	
	public MainPage() {
		super("HomePage");
		
		this.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setSize(WIDTH, HEIGHT);
	}
	
	public static void main(String[] args) {
		MainPage mainPage = new MainPage();
		Bridge bridge = new Bridge();
		mainPage.add(bridge);
		
	}
}
