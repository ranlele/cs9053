package bridge;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.ListIterator;

public class VerticalCardField extends CardField {
	static final int WIDTH = 150;
	static final int HEIGHT = 450;
	
	public VerticalCardField(Desk desk, String position) {
		super(desk, position, WIDTH, HEIGHT);
	}
	
	public void setCardsList() {
		// refresh the cardList before set new cards to it
		this.cards.clear();
		// if the displayable is true, show the card to the player
		if (this.displayable == true) {
			for (int i : this.cardsIDs) {
				this.cards.add(new Card(i, true, this));
			}
		}else {	// if the displayable is false, show the back of the card to the player
			for (int i : this.cardsIDs) {
				this.cards.add(new Card(-1, true, this));
			}
		}
	}
	
	public void displayCards() {
		this.removeAll();
		if (cards == null ||cards.size() <= 0)	return;
		
		int offset = cards.get(0).getImageWidth() / 3;
		int x = (WIDTH - cards.get(0).getImageHeight()) / 2;
		int y = (HEIGHT - (offset * (cards.size() - 1) + cards.get(0).getImageWidth())) / 2;
		
		ListIterator<Card> iterator = cards.listIterator();
		while (iterator.hasNext()){
//		for (Card c : cards) {
//			System.out.println(c.id);
			Card c = (Card)iterator.next();
			c.setIsRotated(true);
			this.add(c, 0);
			c.setLocation(x, y);
			c.repaint();
			this.repaint();
			y += offset;
		}
	}
	
//	public void paintComponent(Graphics g) {
//		if (cards.size() <= 0)	return;
//		Graphics2D g2d = (Graphics2D)g;
//		//rotate the images to vertical
//		g2d.rotate(Math.toRadians(90), HEIGHT/2, HEIGHT/2);
//
//		int offset = cards.get(0).getWidth() / 3;
//		int x = (WIDTH - (offset * (cards.size() - 1) + cards.get(0).getWidth())) / 2;
//		int y = (HEIGHT - cards.get(0).getHeight()) / 2;
//		for (Card c : cards) {
//			boolean draw = g2d.drawImage(c.getImage(), x, y, c.getWidth(), c.getHeight(), this);
//			x += offset;
//		}
//	}
}
