package bridge;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public abstract class CardField extends JPanel {
	Desk desk;
	String position;
	CopyOnWriteArrayList<Integer> cardsIDs;
	List<Card> cards;
	boolean displayable;
	protected boolean isPlayed;

	public CardField(Desk desk, String position) {
		super();
		this.desk = desk;
		this.position = position;
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setVisible(true);
		
		cardsIDs = new CopyOnWriteArrayList<Integer>();
		cards = new ArrayList<Card>();
		displayable = false;
		isPlayed = false;
	}
	
	public CardField(Desk desk, String position, int width, int height) {
		this(desk, position);
		this.setSize(width, height);
	}
	
	// if the cardField belongs to the player, set the displayable to true;
	public void setDisplayable(boolean flag) {
		this.displayable = flag;
	}
	
	public boolean getDisplayable() {
		return this.displayable;
	}
	
	public void setIDsList(CopyOnWriteArrayList<Integer> cardsIDs) {
		this.cardsIDs = cardsIDs;
		setCardsList();
//		this.repaint();
		this.displayCards();
	}
	
	public CopyOnWriteArrayList<Integer> getIDsList(){
		return this.cardsIDs;
	}
	
	public Desk getDesk() {
		return this.desk;
	}
	
	public String getPosition() {
		return this.position;
	}
	
	public int getPositionId() {
		switch(this.position) {
		case "north":	return 0;
		case "east":	return 1;
		case "south":	return 2;
		case "west":	return 3;
		}
		
		return -1;
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
