package bridge;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import common.User;

public class Bridge extends JPanel {
	final int WIDTH = 1024;
	final int HEIGHT = 850;
	User user;
	Desk desk;
	Board board;

	public Bridge() {
//		super("Bridge");
		
		this.setLayout(new FlowLayout());
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setSize(WIDTH, HEIGHT);
		
		desk = new Desk(this);
		this.add(desk);
		board = new Board();
		this.add(board);
		board.getStart().addActionListener(new StartAction(desk, board));
	}
	
	public Bridge(User user) {
		this();
		this.user = user;
		this.board.setUser(user);
	}
	
	public User getUser() {
		return this.user;
	}
	
	public Desk getDesk() {
		return this.desk;
	}
	
	public Board getBoard() {
		return this.board;
	}
	
	public class StartAction implements ActionListener{
		Desk desk;
		Board board;
		
		public StartAction(Desk desk, Board board) {
			this.desk = desk;
			this.board = board;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// cannot start a game before finish the one is playing
			if(this.desk.isPlaying == true) {
				JOptionPane.showMessageDialog(desk, "Please finish this game before starting a new one.", 
						null, JOptionPane.PLAIN_MESSAGE);
				return;
			}
			
//			board.getUser().cash -= 25;
//			board.setScore();
			
			this.desk.isPlaying = true;
			// shuffle and deal cards
			this.desk.shuffleCards();
			this.desk.dealCards();
			
			// Bid
			String[] orderOptions = {"1", "2", "3", "4", "5", "6", "7"};
			String order = null;
			while(order == null) {
				order = (String)JOptionPane.showInputDialog(desk, "Choose the order: ", "Bid", 
						JOptionPane.PLAIN_MESSAGE, null, orderOptions, orderOptions[0]);
			}
			String[] suitOptions = {"♠", "♥", "♦", "♣", "NT"};
			String suit = null;
			while (suit == null) {
				suit = (String)JOptionPane.showInputDialog(desk, "Choose the trump suit: ", "Bid", 
						JOptionPane.PLAIN_MESSAGE, null, suitOptions, suitOptions[0]);
			}
			this.board.setContract(new String(order + " " + suit));
			this.desk.trumpSuit = suit;
			
			// Display the cards of the banker's teammate
			String banker = this.desk.getBanker();
			CardField bankerField = null;
			CardField partnerField = null;
			switch (banker) {
			case "east":
				bankerField = desk.getCardField("east");
				partnerField = desk.getCardField("west");
				break;
			case "west":
				bankerField = desk.getCardField("east");
				partnerField = desk.getCardField("west");
				break;
			case "north":
				bankerField = desk.getCardField("north");
				partnerField = desk.getCardField("south");
				break;
			case "south":
				bankerField = desk.getCardField("south");
				partnerField = desk.getCardField("north");
				break;
			default: 
				System.err.println("Error in Bridge.StartAction.actionPerformed(), "
					+ "argument must be one of east/west/north/south.");
				return;
			}
			
			for (Card c : bankerField.getCardsList()) {
				c.setPlayable(true);
			}
			bankerField.repaint();
			
			partnerField.setDisplayable(true);
			partnerField.setCardsList();
			for (Card c : partnerField.getCardsList()) {
//				System.out.print(c.id + " ");
				c.setPlayable(true);
			}
			partnerField.displayCards();
			//partnerField.repaint();
			
			// Remind the banker to play the paetner's cards
			JOptionPane.showMessageDialog(desk, "You are the banker and need to play your partner's cards.", 
					"Reminder" , JOptionPane.PLAIN_MESSAGE);
			
			//start playing cards
			try {
				desk.startPlaying();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
	}
	
	
//	public static void main(String[] args) {
//		Bridge bridge = new Bridge();
//	}
}
