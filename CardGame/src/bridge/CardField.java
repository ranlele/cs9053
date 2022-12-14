package bridge;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.util.*;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public abstract class CardField extends JPanel {
	Desk desk;
	String position;
	List<Integer> cardsIDs;
	List<Card> cards;
	boolean displayable;

	public CardField(Desk desk, String position) {
		super();
		this.desk = desk;
		this.position = position;
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setVisible(true);
		
		cards = new ArrayList<Card>();
		displayable = false;
	}
	
	public CardField(Desk desk, String position, int width, int height) {
		this(desk, position);
		this.setSize(width, height);
	}
	
	// if the cardField belongs to the player, set the displayable to true;
	public void setDisplayable(boolean flag) {
		this.displayable = flag;
	}
	
	public void setIDsList(List<Integer> cardsIDs) {
		this.cardsIDs = cardsIDs;
		setCardsList();
//		this.repaint();
		this.displayCards();
	}
	
	public Desk getDesk() {
		return this.desk;
	}
	
	public String getPosition() {
		return this.position;
	}
	
	public List<Card> getCardsList(){
		return cards;
	}
	
	public abstract void setCardsList();
	
	public abstract void displayCards();
	
	public void paintComponent(Graphics g) {
		displayCards();
	}
}
