package Muyu;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
import javax.swing.JPanel;

import common.DBconnection;
import common.User;

public class Muyu extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	User user;
	int balance = 0;
	JButton muyu_bt = new JButton();
	JLabel muyu_label = new JLabel();
	JLabel leaderboard = new JLabel();
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
		
		leaderboard.setFont(new Font("Serif", Font.PLAIN, 20));
		leaderboard.setText(pullLeaderBoardString());

		// TODO: muyu picture
		  try {
		    Image img = ImageIO.read(getClass().getResource("./muyu.png"));
		    muyu_bt.setIcon(new ImageIcon(img));
		    Dimension d = new Dimension(100,100);
		    muyu_bt.setPreferredSize(d);
		  } catch (Exception ex) {
		    System.out.println(ex);
		  }
		// invisible button
		muyu_bt.setOpaque(false);
		muyu_bt.setContentAreaFilled(false);
		muyu_bt.setBorderPainted(false);
		muyu_bt.setFocusPainted(false);
		muyu_label = new JLabel("Knock the Muyu to deposit your cash");
		muyu_label.setFont(new Font("Serif", Font.PLAIN, 18));
		// add buttons / labels
		add(muyu_label);
		add(leaderboard);
		add(muyu_bt);
		muyu_bt.addActionListener(this);
	}
	
	//build leader board string from db
	private String pullLeaderBoardString() {
		ArrayList<User> users = null;
		StringBuilder sb = new StringBuilder();
		try {
			users = db.getData(db.conn, "Users");
			System.out.println("pulling." + users);
			Collections.sort(users, new CustomComparator());
			sb.append("<html>" );
			sb.append("TOP Players <br/>");
			for (int i = 0; i < users.size(); i ++) { 
				sb.append(users.get(i).username + "   ---   ");
				sb.append(users.get(i).cash);
				sb.append("<br/>");	
			}
			sb.append("</html>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sb.toString();
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
			user.cash --;
			muyu_label.setText(String.format("Your cash %d ", user.cash));
			//to do, deposit sin into database 
		}
		
		else if (user.cash < 1) {
			showMessageDialog(null, "Deposit succeed. Greed is sin.");
			if (db.conn != null) {
				try {
					DBconnection.insertUser(db.conn, user.username, balance);
					leaderboard.setText(pullLeaderBoardString());
					db.conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
				}
			}
		}
	}
	
	

}
