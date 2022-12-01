package bridge;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Board extends JPanel {
	final int WIDTH = 200;
	final int HEIGHT = 800;
	JLabel score;
	JLabel contract;
	JButton start;
	

	public Board() {
		super();
		this.setLayout(new FlowLayout());
		Dimension size = new Dimension(WIDTH, HEIGHT);
		this.setPreferredSize(size);
		this.setMaximumSize(size);
		this.setMinimumSize(size);
		this.setSize(WIDTH, HEIGHT);
		this.setVisible(true);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		
		Dimension labelSize = new Dimension(180, 50);
		
		JLabel name = new JLabel();
		name.setText("User Name:");
		name.setPreferredSize(labelSize);
		this.add(name);
		
		JLabel position = new JLabel();
		position.setText("Position: South");
		position.setPreferredSize(labelSize);
		this.add(position);
		
		score = new JLabel();
		score.setText("Score:");
		score.setPreferredSize(labelSize);
		this.add(score);
		
		start = new JButton("START");
		Dimension buttonSize = new Dimension(150, 25);
		start.setPreferredSize(buttonSize);
		this.add(start);
		
		contract = new JLabel();
		contract.setText("Contract:");
		contract.setPreferredSize(labelSize);
		contract.setMaximumSize(labelSize);
		this.add(contract);
	}
	
	public JButton getStart() {
		return this.start;
	}
	
	public void setContract(String str) {
		this.contract.setText("Contract: " + str);
	}
}
