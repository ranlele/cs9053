package bridge;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class Card extends JPanel{
	CardField cardField;
	public int id;
	Image img;
	int height;
	int width;
	int imgheight;
	int imgwidth;
	boolean isRotated;
	boolean playable;

	public Card() {
		super();
	}
	
	public Card(int id, boolean isRotated) {
		this();
		this.id = id;
		this.isRotated = isRotated;
		this.playable = false;
		String name = new String("./img/" + (id + 1) + ".png");
		img = new ImageIcon(name).getImage();
		imgheight = img.getHeight(null);
		imgwidth = img.getWidth(null);
		
//		Dimension size = isRotated ? new Dimension(height, width) : new Dimension(width, height);
		if(isRotated) {
			width = imgheight;
			height = imgwidth;
		}
		else{
			width = imgwidth;
			height = imgheight;
		}
		Dimension size = new Dimension(width, height);
		this.setPreferredSize(size);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setVisible(true);
		
		this.addMouseListener(new PlayCard(this));
	}
	
	public Card(int id, boolean isRotated, CardField cardField) {
		this(id, isRotated);
		this.cardField = cardField;
	}
	
	public Image getImage() {
		return this.img;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getImageHeight() {
		return this.imgheight;
	}
	
	public int getImageWidth() {
		return this.imgwidth;
	}
	
	public void setIsRotated(boolean flag) {
		this.isRotated = flag;
	}
	
	public void setPlayable(boolean flag) {
		this.playable = flag;
	}
	
	public boolean getPlayable() {
		return this.playable;
	}
	
	public CardField getCardField() {
		return this.cardField;
	}
	
	public BufferedImage rotate(Image img) {
		BufferedImage bimg = new BufferedImage(imgheight, imgwidth,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = bimg.createGraphics();
		g2d.rotate(Math.toRadians(90), width/2,width/2);
		g2d.drawImage(img, 0, 0, null);
		g2d.dispose();
		
		return bimg;
	}
	
	public void paintComponent(Graphics g) {
		if(!this.isRotated) {
			g.drawImage(img, 0, 0, width, height, null);
		}
		else {
			Graphics2D g2d = (Graphics2D)g;
//			g2d.rotate(Math.toRadians(90), height/2, height/2);
			g2d.drawImage(rotate(img), 0, 0, width, height, null);
		}
		
	}
	
	public class PlayCard extends MouseAdapter {
		Card card;
		
		public PlayCard(Card card) {
			this.card = card;
		}
		
		// play the selected card
		@Override
		public void mousePressed(MouseEvent e) {
			System.out.println(card.id + " " + card.getPlayable());
			CardField cf = this.card.getCardField();
			// cards can only be played when they are on a cardField and are playable by the player
			if (cf == null || this.card.getPlayable() == false)
				return;
			
			Center center = cf.getDesk().getCenter();
			switch(cf.getPosition()) {
			case "north": 
				center.cardsSlot[0] = this.card;
				break;
			case "east":
				center.cardsSlot[1] = this.card;
				break;
			case "south":
				center.cardsSlot[2] = this.card;
				break;
			case "west":
				center.cardsSlot[3] = this.card;
				break;
			default:
				System.err.println("Error in Card.PlayCard.mouseReleased(), "
						+ "position must be one of east/west/north/south.");
			}
			center.repaint();
			
			if(!cf.getCardsList().remove(this.card)) {
				System.err.println("Play card " + card.id + " failed");
			}
			cf.repaint();
			
		}
		
		
	}
	
	
	
//	public static void main(String[] args) {
//		JFrame frame = new JFrame();
//		JPanel panel = new JPanel();
//		panel.setBorder(BorderFactory.createLineBorder(Color.black));
//		panel.setSize(100, 100);
////		panel.setLocation(0, 100);
//		Card card = new Card(1);
//		card.setLocation(100, 100);
//		panel.add(card);
//		frame.add(panel);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setVisible(true);
//		frame.setSize(500, 500);
//		
//		
//	}
}
