package common;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Muyu.Muyu;
import bridge.Bridge;

public class MainPage implements ActionListener {
	private static final long serialVersionUID = 1L;
	static final int WIDTH = 1024;
	static final int HEIGHT = 850;

//	JPanel blackjack = new JPanel();
//	JPanel bridge = new JPanel();

	JPanel main = new JPanel();
//	JPanel leaderboard = new JPanel();
	JFrame frame = new JFrame();
	User user = new User("Test");
	public MainPage() {
		
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
		new MainPage();
//		new User();
//		Bridge bridge = new Bridge();
//		mainPage.add(bridge);

		
	}


	@Override
	public void actionPerformed(ActionEvent evt) {
		String event = evt.getActionCommand();
//		frame.dispose();
		if(event.equals("BlackJack")){
			System.out.println("playing blackjack");
//			blackjackGame.main(null);
//			frame.remove(main);
//          frame.add(blackjackGame);
//			setVisible(true);
		}
		else if(event.equals("Bridge")){
			System.out.println("playing Bridge");
			Bridge bridgeGame = new Bridge();
			frame.remove(main);
            frame.add(bridgeGame);
			frame.setVisible(true);
			
		}
		else if(event.equals("Muyu")){
			System.out.println("playing Muyu");
			Muyu muyuGame = new Muyu(user);
			frame.remove(main);
			frame.add(muyuGame);
			frame.setVisible(true);
		}
		
	}
}
