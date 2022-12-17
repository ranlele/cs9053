package bridge;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.util.*;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Center extends JPanel {
	
	static final int MARGIN = 30;
	
	protected Card[] cardsSlot;

	public Center() {
		super();
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setVisible(true);
		
		cardsSlot = new Card[4];	//{0: N, 1: E, 2: S, 3: W}
//		cardsSlot[0] = new Card(0,false);
//		cardsSlot[1] = new Card(0,true);
//		cardsSlot[2] = new Card(0,false);
//		cardsSlot[3] = new Card(0,true);
	}

	public Center(int width, int height) {
		this();
		this.setSize(width, height);
	}
	
	public void displayCards() {
		this.removeAll();
		int width = this.getWidth();
		int height = this.getHeight();
		
		for (int i = 0; i < 4; i++) {
			if(cardsSlot[i] != null) {
				Card c = cardsSlot[i];
				this.add(c);
				int x, y;
				if (i == 0) {
					x = (width - c.getImageWidth()) / 2;
					y = MARGIN;
				}
				else if (i == 1) {
					x = width - c.getImageHeight() - MARGIN;
					y = (height - c.getImageWidth()) / 2;
				}
				else if (i == 2) {
					x = (width - c.getImageWidth()) / 2;
					y = height - c.getImageHeight() - MARGIN;
				}
				else {
					x = MARGIN;
					y = (height - c.getImageWidth()) / 2;
				}
				c.setLocation(x, y);
				c.repaint();
			}
			this.repaint();
		}
		
	}
	
	public void paintComponent(Graphics g) {
		displayCards();
	}

}
