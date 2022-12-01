package bridge;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class Card extends JPanel{
	public int id;
	Image img;
	int height;
	int width;
	int imgheight;
	int imgwidth;
	boolean isRotated;

	public Card() {
		super();
	}
	
	public Card(int id, boolean isRotated) {
		this();
		this.id = id;
		this.isRotated = isRotated;
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
