package Muyu;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import common.DBconnection;
import common.User;

public class Muyu extends JPanel implements ActionListener {
	User user;
	int balance = 0;
	JButton muyu_bt = new JButton();
	Clip clip = null; // sound clip
	DBconnection db; // db connection
	
	public Muyu(User user) {
		db  = new DBconnection();
		this.user = user;
		setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		setLayout(new GridLayout(1,3));
		// muyu sound
		String soundName = "./CardGame/src/Muyu/muyu.wav";    
		AudioInputStream audioInputStream = null;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		try {
			clip.open(audioInputStream);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//leader board
		ArrayList<User> users;
		JLabel leaderboard = new JLabel();
		leaderboard.setFont(new Font("Serif", Font.PLAIN, 20));
		StringBuilder sb = new StringBuilder();
		
		try {
			users = db.getData(db.conn, "Users");
			Collections.sort(users, new CustomComparator());
			leaderboard.setText(sb.toString());
			sb.append("<html>" );
			sb.append("TOP Players <br/>");
			for (int i = 0; i < users.size(); i ++) { 
				sb.append(users.get(i).username + "   ---   ");
				sb.append(users.get(i).cash);
				sb.append("<br/>");
//				System.out.print(users.get(i).username + " "); 
//		        System.out.println(users.get(i).cash); 		
			}
			sb.append("</html>");
			leaderboard.setText(sb.toString());
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// TODO: muyu picture
		  try {
		    Image img = ImageIO.read(getClass().getResource("./muyu.png"));
		    muyu_bt.setIcon(new ImageIcon(img));
		    Dimension d = new Dimension(100,100);
		    muyu_bt.setPreferredSize(d);
		  } catch (Exception ex) {
		    System.out.println(ex);
		  }
		
		JLabel muyu_label = new JLabel("Knock the Muyu to deposit your cash");
		muyu_label.setFont(new Font("Serif", Font.PLAIN, 18));
		// add buttons / labels
		add(muyu_label);
		add(leaderboard);
		add(muyu_bt);
		muyu_bt.addActionListener(this);
	}
	
	// custom comparator for leaderboard
	public class CustomComparator implements Comparator<User> {
	    @Override
	    public int compare(User o1, User o2) {
	        return o2.cash - o1.cash;
	    }
	}
	
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		if(user.cash >= 1) {
			clip.setFramePosition(0);
			clip.start();
			balance ++;
			muyu_bt.setText(String.format("Your cash %d ", --user.cash));
			//to do, deposit sin into database 
		}
		
		else if (user.cash < 1) {
			showMessageDialog(null, "Deposit succeed. Greed is sin.");
			if (db.conn != null) {
				try {
					DBconnection.insertUser(db.conn, user.username, balance);
					db.conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
				}
			}
 
		};
	}

}
