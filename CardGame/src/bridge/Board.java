package bridge;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import common.User;

public class Board extends JPanel {
	final int WIDTH = 200;
	final int HEIGHT = 800;
	User user;
	JLabel name;
	JLabel score;
	JLabel contract;
	JButton start;
	JLabel wins;
	int nWins;
	protected int nContract;
	

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
		
		nWins = 0;
		Dimension labelSize = new Dimension(180, 50);
		
		name = new JLabel();
		name.setText("User Name: ");
		name.setPreferredSize(labelSize);
		this.add(name);
		
		JLabel position = new JLabel();
		position.setText("Position: South");
		position.setPreferredSize(labelSize);
		this.add(position);
		
		score = new JLabel();
		score.setText("Score: ");
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
		
		wins = new JLabel();
		wins.setText("Number of Wins: " + nWins + " / 13");
		wins.setPreferredSize(labelSize);
		wins.setMaximumSize(labelSize);
		this.add(wins);
	}
	
	public Board(User user) {
		this();
		this.user = user;
		name.setText("User Name: " + user.username);
		score.setText("Score: " + user.cash);
	}
	
	public User getUser() {
		return this.user;
	}
	
	public void setUser(User user) {
		this.user = user;
		name.setText("User Name: " + user.username);
		score.setText("Score: " + user.cash);
	}
	
	public JButton getStart() {
		return this.start;
	}
	
	public void setScore() {
		this.score.setText("Score: " + user.cash);
	}
	
	public void setContract(String str) {
		this.contract.setText("Contract: " + str);
		this.nContract = Integer.parseInt(str.split(" ")[0]);
	}
	
	public void plusOneWins() {
		this.nWins++;
		wins.setText("Number of Wins: " + nWins + " / 13");
	}
	
	public int getNumOfWins() {
		return this.nWins;
	}
}
