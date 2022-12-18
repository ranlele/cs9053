package common;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import BlackJack.BlackJackPanel;
import Muyu.Muyu;
import bridge.Bridge;

public class MainPage implements ActionListener {
	private static final long serialVersionUID = 1L;
	static final int WIDTH = 1024;
	static final int HEIGHT = 850;
	public static DBconnection db; // db connection
	static JFrame frame = new JFrame();
	static User user;
	JPanel main = new JPanel();
	
	public MainPage() {
		db  = new DBconnection();
		frame.setSize(WIDTH, HEIGHT);
		main.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		main.setLayout(new GridLayout(1, 4));
		frame.add(main, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Main Page");
		
		JLabel main_page_label = new JLabel("Which game would you like to play?");
		main.add(main_page_label);
		JButton blackjack_bt = new JButton("BlackJack");
		main.add(blackjack_bt);
		JButton bridge_bt = new JButton("Bridge");
		main.add(bridge_bt);
		JButton muyu_bt = new JButton("Muyu");
		main.add(muyu_bt);
	
		blackjack_bt.addActionListener(this);
		bridge_bt.addActionListener(this);
		muyu_bt.addActionListener(this);
		
		frame.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		 // prompt the user to enter their name
	    String name = JOptionPane.showInputDialog(frame, "What's your name?");

	    // get the user's input. note that if they press Cancel, 'name' will be null
	    System.out.printf("Welcome to Casino '%s'.\n", name);
	    user = new User(name);
		new MainPage();
		WindowListener exitListener = new WindowAdapter() {

		    @Override
		    public void windowClosing(WindowEvent e) {
		        int confirm = JOptionPane.showOptionDialog(
		             null, "Are You Sure to Close Application?", 
		             "Exit Confirmation", JOptionPane.YES_NO_OPTION, 
		             JOptionPane.QUESTION_MESSAGE, null, null, null);
		        if (confirm == 0) {
		        	try {
						db.conn.close();
						System.out.println("DB connection closed.");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
		           System.exit(0);
		        }
		    }
		};
		frame.addWindowListener(exitListener);
		
	}


    
	@Override
	public void actionPerformed(ActionEvent evt) {
		String event = evt.getActionCommand();
		if(event.equals("BlackJack")){
			System.out.println("playing blackjack");
			BlackJackPanel blackjackGame = new BlackJackPanel(user);

		}
		else if(event.equals("Bridge")){
			System.out.println("playing Bridge");
			Bridge bridgeGame = new Bridge(user);

			
		}
		else if(event.equals("Muyu")){
			System.out.println("playing Muyu");
			Muyu muyuGame = new Muyu(user, db);
		}
		
	}
}
