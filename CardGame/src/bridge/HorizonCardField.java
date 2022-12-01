package bridge;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.*;

public class HorizonCardField extends CardField {
	static final int WIDTH = 450;
	static final int HEIGHT = 150;
	
	public HorizonCardField() {
		super(WIDTH, HEIGHT);
	}
	
	public void setCardsList() {
		// refresh the cardList before set new cards to it
		this.cards.clear();
		// if the displayable is true, show the card to the player
		if (this.displayable == true) {
			for (int i : this.cardsIDs) {
				this.cards.add(new Card(i, false));
			}
		}else {	// if the displayable is false, show the back of the card to the player
			for (int i : this.cardsIDs) {
				this.cards.add(new Card(-1, false));
			}
		}
	}
	
	public void displayCards() {
		this.removeAll();
		if (cards == null ||cards.size() <= 0)	return;
		
		int len = cards.size();
		int offset = cards.get(0).getImageWidth() / 3;
		int x = (WIDTH - (offset * (len - 1) + cards.get(0).getImageWidth())) / 2;		
		int y = (HEIGHT - cards.get(0).getImageHeight()) / 2;
		for (int i = 0; i < len; i++) {
			Card c = cards.get(i);
//			System.out.println(c.id);
			this.add(c, 0);
			c.setLocation(x, y);
			c.repaint();
			this.repaint();
			x += offset;
		}
	}
}
