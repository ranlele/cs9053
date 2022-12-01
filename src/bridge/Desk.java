package bridge;

import java.util.*;
import java.util.List;
import java.awt.*;

import javax.swing.*;

public class Desk extends JPanel{
	final int WIDTH = 800;
	final int HEIGHT = 800;
	final int CARD_HEIGHT = 150;
	final int TABLE_LENGTH = 450;
	
	CardField east;
	CardField west;
	CardField north;
	CardField south;
	JPanel table;
	List<Integer> eastIDs = new ArrayList<>();
	List<Integer> westIDs = new ArrayList<>();
	List<Integer> northIDs = new ArrayList<>();
	List<Integer> southIDs = new ArrayList<>();
	List<Card> eastCards = new ArrayList<>();
	List<Card> westCards = new ArrayList<>();
	List<Card> northCards = new ArrayList<>();
	List<Card> southCards = new ArrayList<>();
	
	String banker;
	public boolean isPlaying;

	public Desk() {
		isPlaying = false;
		banker = "south";
		
		//set size
		Dimension size = new Dimension(WIDTH, HEIGHT);
		this.setPreferredSize(size);
		this.setMaximumSize(size);
		this.setMaximumSize(size);

		this.setVisible(true);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		
		north = new HorizonCardField();
		south = new HorizonCardField();
		west = new VerticalCardField();
		east = new VerticalCardField();
		table = new JPanel();
		table.setBorder(BorderFactory.createLineBorder(Color.black));
		table.setSize(TABLE_LENGTH, TABLE_LENGTH);
//		Card ca = new Card(1, true);
//		ca.setIsRotated(true);
//		table.add(ca);
		
		
		// Set the layout of the desk
		GroupLayout layout = new GroupLayout(this);
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
		this.setLayout(layout);
		
		// Set the horizon group
		GroupLayout.ParallelGroup hParalGroup = layout.createParallelGroup();
		hParalGroup.addComponent(north,TABLE_LENGTH,TABLE_LENGTH,TABLE_LENGTH);
		hParalGroup.addComponent(table,TABLE_LENGTH,TABLE_LENGTH,TABLE_LENGTH);
		hParalGroup.addComponent(south,TABLE_LENGTH,TABLE_LENGTH,TABLE_LENGTH);
		GroupLayout.SequentialGroup hSeqGroup = layout.createSequentialGroup();
		hSeqGroup.addGap(20);
		hSeqGroup.addComponent(west,CARD_HEIGHT,CARD_HEIGHT,CARD_HEIGHT);
		hSeqGroup.addGroup(hParalGroup);
		hSeqGroup.addComponent(east,CARD_HEIGHT,CARD_HEIGHT,CARD_HEIGHT);
		layout.setHorizontalGroup(hSeqGroup);
		
		// Set the vertical group
		GroupLayout.ParallelGroup vParalGroup = layout.createParallelGroup();
		vParalGroup.addComponent(west,TABLE_LENGTH,TABLE_LENGTH,TABLE_LENGTH);
		vParalGroup.addComponent(table,TABLE_LENGTH,TABLE_LENGTH,TABLE_LENGTH);
		vParalGroup.addComponent(east,TABLE_LENGTH,TABLE_LENGTH,TABLE_LENGTH);
		GroupLayout.SequentialGroup vSeqGroup = layout.createSequentialGroup();
		vSeqGroup.addGap(20);
		vSeqGroup.addComponent(north,CARD_HEIGHT,CARD_HEIGHT,CARD_HEIGHT);
		vSeqGroup.addGroup(vParalGroup);
		vSeqGroup.addComponent(south,CARD_HEIGHT,CARD_HEIGHT,CARD_HEIGHT);
		layout.setVerticalGroup(vSeqGroup);
	}
	
	public CardField getCardField(String position) {
		switch (position) {
		case "east":	return east;
		case "west":	return west;
		case "north":	return north;
		case "south":	return south;
		default:
			System.err.println("Error in Desk.getCardField(), "
					+ "argument must be one of east/west/north/south.");
			return null;
		}
	}
	
	public List<Integer> getCardIDs(String position){
		switch (position) {
		case "east":	return eastIDs;
		case "west":	return westIDs;
		case "north":	return northIDs;
		case "south":	return southIDs;
		default:
			System.err.println("Error in Desk.getCartList(), "
					+ "argument must be one of east/west/north/south.");
			return null;
		}
	}
	
	public String getBanker() {
		return this.banker;
	}
	
	public void setBanker(String banker) {
		switch (banker) {
		case "east":	break;
		case "west":	break;
		case "north":	break;
		case "south":	break;
		default:
			System.err.println("Error in Desk.setBanker(), "
					+ "argument must be one of east/west/north/south.");
			return;
		}
		
		this.banker = banker;
	}

	public void shuffleCards() {
		// Initialize a new pair of cards
		ArrayList<Integer> allCards = new ArrayList<>();
		for(int i = 0; i < 52; i++) {
			allCards.add(i);
		}
		
		// shuffle the cards randomly
		int times = new Random().nextInt(10);
		for (int k = 0; k < times; k++) {
			for (int i = 0; i < 52; i++) {
				int idx = new Random().nextInt(52);
				int temp = allCards.get(i);
				allCards.set(i, allCards.get(idx));
				allCards.set(idx, temp);
			}
		}
		
		// deal cards to each player
		eastIDs = allCards.subList(0, 13);
		westIDs = allCards.subList(13, 26);
		northIDs = allCards.subList(26, 39);
		southIDs = allCards.subList(39, 52);
		
		System.out.println(eastIDs.toString());
		System.out.println(westIDs.toString());
		System.out.println(northIDs.toString());
		System.out.println(southIDs.toString());
	}
	
	public void dealSelectedCards(String position) {
		CardField cardField = null;
		List<Integer> idsList = null;
		// get selected variables
		switch (position) {
		case "east":	
			cardField = this.east;
			idsList = this.eastIDs;
			break;
		case "west":	
			cardField = this.west;
			idsList = this.westIDs;
			break;
		case "north":	
			cardField = this.north;
			idsList = this.northIDs;
			break;
		case "south":	
			cardField = this.south;
			idsList = this.southIDs;
			cardField.setDisplayable(true);
			break;
		default:
			System.err.println(position + "Error in Desk.dealSelectedCards(), "
					+ "argument must be one of east/west/north/south.");
		}
		
		// sort the cards in order
		idsList.sort((a, b) -> {
			if (a / 13 != b / 13)
				return a / 13 - b / 13;
			else
				return b % 13 - a % 13;
		});
		
		cardField.setIDsList(idsList);
//		cardField.repaint();
	}
	
	public void dealCards() {
		dealSelectedCards("east");
		dealSelectedCards("west");
		dealSelectedCards("north");
		dealSelectedCards("south");
	}
	
//	public void dealSelectedCards(String position) {
//		CardField cardField = null;
//		List<Integer> idsList = null;
//		List<Card> cardsList = null;
//		
//		// get selected variables
//		switch (position) {
//		case "east":	
//			cardField = this.east;
//			idsList = this.eastIDs;
//			cardsList = this.eastCards;	
//			break;
//		case "west":	
//			cardField = this.west;
//			idsList = this.westIDs;
//			cardsList = this.westCards;
//			break;
//		case "north":	
//			cardField = this.north;
//			idsList = this.northIDs;
//			cardsList = this.northCards;
//			break;
//		case "south":	
//			cardField = this.south;
//			idsList = this.southIDs;
//			cardsList = this.southCards;
//			break;
//		default:
//			System.err.println(position + "Error in Desk.dealSelectedCards(), "
//					+ "argument must be one of east/west/north/south.");
//		}
//		
//		// sort the cards in order
//		idsList.sort((a, b) -> {
//			if (a / 13 != b / 13)
//				return a / 13 - b / 13;
//			else
//				return b % 13 - a % 13;
//		});
//		// refresh the cardList before deal new cards to it
//		cardsList.clear();
//		for (int i : idsList) {
//			cardsList.add(new Card(i));
//		}
//		cardField.setCardsList(cardsList);
//		cardField.repaint();
//	}
//	
//	public void dealCards() {
//		dealSelectedCards("east");
//		dealSelectedCards("west");
//		dealSelectedCards("north");
//		dealSelectedCards("south");
//	}
	
	
}
