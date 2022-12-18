package BlackJack;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import common.User;



public class BlackJackPanel extends JFrame {
	static final int WIDTH = 1024;
	static final int HEIGHT = 850;
	
	JLabel result;
	JLabel gamblingMoneyLabel;
    JPanel topPanel;
    JPanel midPanel;
    JPanel bottomPanel;
    JTextField betMoneyText;
    Timer timer;
    boolean isStop = false;
    boolean isBet = false;
    boolean isHit = false;
    int gamblingMoney = 0;
    static User user;
    int bet = 0;
	
    int[] pokersOnDealer = new int[]{52, 53, 54, 55, 56};
	int[] pokersOnPlayer = new int[]{57, 58, 59, 60, 61};
    ImagePanel[] pokers = new ImagePanel[62];
    static boolean[] usedPokers = new boolean[62];
	
	public BlackJackPanel(User user) {
		isStop = false;
		BlackJackPanel.user = user;
		gamblingMoney = user.cash;	
		this.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		for (int i = 0; i < 52; i++) {
			this.pokers[i] = new ImagePanel("./CardGame/img/"+(i+1)+".png", i);
		}
		for (int i = 0; i < 10; i++) {
			pokers[i+52] = new ImagePanel("./CardGame/img/"+0+".png", 0);
		}
		setupPanel();
		this.setVisible(true);
		this.setSize(WIDTH, HEIGHT);
//		this.setResizable(false);
	}
	
	private void setupPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		
		// the largest panel
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(WIDTH/2, HEIGHT/2));
        gridBagLayout.rowHeights = new int[]{0, 0, 0};
        gridBagLayout.rowWeights = new double[] {0.3, 0.4, 0.3};
        panel.setLayout(gridBagLayout);
      
        
        
        // top Panel
        topPanel = new JPanel();
        gridBagLayout.columnWidths = new int[]{0};
        gridBagLayout.rowHeights = new int[]{0};
        gridBagLayout.columnWeights = new double[] {1.0};
        gridBagLayout.rowWeights = new double[] {1.0};
        topPanel.setLayout(gridBagLayout);
        showDealerResult();
        
        
        
        // midPanel
        midPanel = new JPanel();
        gridBagLayout.columnWidths = new int[]{0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[] {1.0};
        gridBagLayout.rowWeights = new double[] {0.25, 0.25, 0.25};
        midPanel.setLayout(gridBagLayout);
        
        
        // bet Money Panel
        JPanel betMoneyPanel = new JPanel();
        gridBagLayout.columnWidths = new int[]{0, 0};
        gridBagLayout.rowHeights = new int[]{0};
        gridBagLayout.columnWeights = new double[] {0.2, 0.8};
        gridBagLayout.rowWeights = new double[] {1.0};
        betMoneyPanel.setLayout(gridBagLayout);
        
        // bet Money Text
        betMoneyText = new JTextField("Input Bet Money");
        gbc = gbcSettings(0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE);
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.anchor = GridBagConstraints.CENTER;
//        gbc.fill = GridBagConstraints.NONE;
        betMoneyPanel.add(betMoneyText, gbc);
        
        //bet Money Button
        JButton buttonBet = new JButton("Bet");
        ButtonBetListener buttonBetListener = new ButtonBetListener();
        buttonBet.addActionListener(buttonBetListener);
        gbc = gbcSettings(1, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE);
//        gbc.gridx = 1;
//        gbc.gridy = 0;
//        gbc.anchor = GridBagConstraints.CENTER;
//        gbc.fill = GridBagConstraints.NONE;
        betMoneyPanel.add(buttonBet, gbc);
        
        gbc = gbcSettings(0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE);
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.anchor = GridBagConstraints.CENTER;
//        gbc.fill = GridBagConstraints.NONE;
        midPanel.add(betMoneyPanel, gbc);
        
        
        // button Panel Hit & Stop & New Game
        JPanel buttonPanel = new JPanel();
        gridBagLayout.columnWidths = new int[]{0, 0};
        gridBagLayout.rowHeights = new int[]{0};
        gridBagLayout.columnWeights = new double[] {0.5, 0.5};
        gridBagLayout.rowWeights = new double[] {1.0};
        buttonPanel.setLayout(gridBagLayout);
        
        // hit Button
        JButton buttonHit = new JButton("Hit");
        ButtonHitListener buttonHitListener = new ButtonHitListener();
        buttonHit.addActionListener(buttonHitListener);
        gbc = gbcSettings(0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE);
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.anchor = GridBagConstraints.CENTER;
//        gbc.fill = GridBagConstraints.NONE;
        buttonPanel.add(buttonHit, gbc);
        
        // stop Button
        JButton buttonStop = new JButton("Stop");
        ButtonStopListener buttonStopListener = new ButtonStopListener();
        buttonStop.addActionListener(buttonStopListener);
        gbc = gbcSettings(1, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE);
//        gbc.gridx = 1;
//        gbc.gridy = 0;
//        gbc.anchor = GridBagConstraints.CENTER;
//        gbc.fill = GridBagConstraints.NONE;
        buttonPanel.add(buttonStop, gbc);
        
        gbc = gbcSettings(0, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
//        gbc.gridx = 0;
//        gbc.gridy = 1;
//        gbc.anchor = GridBagConstraints.CENTER;
//        gbc.fill = GridBagConstraints.NONE;
        midPanel.add(buttonPanel, gbc);
        
        
        // New Game Panel
        JPanel newGamePanel = new JPanel();
        gridBagLayout.columnWidths = new int[]{0};
        gridBagLayout.rowHeights = new int[]{0};
        gridBagLayout.columnWeights = new double[] {1.0};
        gridBagLayout.rowWeights = new double[] {1.0};
        buttonPanel.setLayout(gridBagLayout);
        
        // New Game Button
        JButton buttonNewGame = new JButton("New Game");
        ButtonNewGameListener buttonNewGameListener = new ButtonNewGameListener();
        buttonNewGame.addActionListener(buttonNewGameListener);
        gbc = gbcSettings(0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE);
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.anchor = GridBagConstraints.CENTER;
//        gbc.fill = GridBagConstraints.NONE;
        newGamePanel.add(buttonNewGame, gbc);
        
        gbc = gbcSettings(0, 2, GridBagConstraints.CENTER, GridBagConstraints.NONE);
//        gbc.gridx = 0;
//        gbc.gridy = 2;
//        gbc.anchor = GridBagConstraints.CENTER;
//        gbc.fill = GridBagConstraints.NONE;
        midPanel.add(newGamePanel, gbc);
        
        // result Panel
        JPanel resultPanel = new JPanel();
        gridBagLayout.columnWidths = new int[]{0};
        gridBagLayout.rowHeights = new int[]{0};
        gridBagLayout.columnWeights = new double[] {1.0};
        gridBagLayout.rowWeights = new double[] {1.0};
        resultPanel.setLayout(gridBagLayout);
        
        // result Label
        int[] resultOnHand = sumOfPokers(pokersOnPlayer);
        result = new JLabel("Result: " + resultOnHand[0] + (resultOnHand.length == 1? "": resultOnHand[1]));
        resultPanel.add(result);
        
        gbc = gbcSettings(0, 3, GridBagConstraints.CENTER, GridBagConstraints.NONE);
//        gbc.gridx = 0;
//        gbc.gridy = 3;
//        gbc.anchor = GridBagConstraints.CENTER;
//        gbc.fill = GridBagConstraints.NONE;
        midPanel.add(resultPanel, gbc);

        
        
        // bottom Panel
        bottomPanel = new JPanel();
        gridBagLayout.columnWidths = new int[]{0};
        gridBagLayout.rowHeights = new int[]{0};
        gridBagLayout.columnWeights = new double[] {1.0};
        gridBagLayout.rowWeights = new double[] {1.0};
        bottomPanel.setLayout(gridBagLayout);
        
        
        
        // add all panels
        gbc = gbcSettings(0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.anchor = GridBagConstraints.CENTER;
//        gbc.fill = GridBagConstraints.BOTH;
        panel.add(topPanel, gbc);
        
        gbc = gbcSettings(0, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
//        gbc.gridx = 0;
//        gbc.gridy = 1;
//        gbc.anchor = GridBagConstraints.CENTER;
//        gbc.fill = GridBagConstraints.BOTH;
        panel.add(midPanel, gbc);
        
        gbc = gbcSettings(0, 2, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
//        gbc.gridx = 0;
//        gbc.gridy = 2;
//        gbc.anchor = GridBagConstraints.CENTER;
//        gbc.fill = GridBagConstraints.BOTH;
        panel.add(bottomPanel, gbc);
        
        showDealerResult();
        updatePlayerResult();
        
        gbc = gbcSettings(0, 0, GridBagConstraints.CENTER, GridBagConstraints.RELATIVE);
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.anchor = GridBagConstraints.CENTER;
//        gbc.fill = GridBagConstraints.RELATIVE;
        gamblingMoneyLabel = new JLabel("Gambling Money: " + user.cash);
        this.add(gamblingMoneyLabel, gbc);
        
        
        gbc = gbcSettings(1, 0, GridBagConstraints.CENTER, GridBagConstraints.RELATIVE);
//        gbc.gridx = 1;
//        gbc.gridy = 0;
//        gbc.anchor = GridBagConstraints.CENTER;
//        gbc.fill = GridBagConstraints.REMAINDER;
        this.add(panel, gbc);
        
        JLabel l = new JLabel("<html><body><br><br>Dealer<br><br><br><br><br><br><br>Player<body/><html/>");
        gbc = gbcSettings(1, 0, GridBagConstraints.CENTER, GridBagConstraints.RELATIVE);
        this.add(l, gbc);
    }
	
	private void initialBlackJack() {
		Random r = new Random();
		int randomNum = r.nextInt(52);
		while (usedPokers[randomNum]) randomNum = r.nextInt(52);
		pokersOnDealer[0] = randomNum;
		usedPokers[randomNum] = true;
		
		randomNum = r.nextInt(52);
		while (usedPokers[randomNum]) randomNum = r.nextInt(52);
		pokersOnPlayer[0] = randomNum;
		usedPokers[randomNum] = true;
		
		randomNum = r.nextInt(52);
		while (usedPokers[randomNum]) randomNum = r.nextInt(52);
		pokersOnDealer[1] = r.nextInt(52);
		usedPokers[randomNum] = true;
		
		randomNum = r.nextInt(52);
		while (usedPokers[randomNum]) randomNum = r.nextInt(52);
		pokersOnPlayer[1] = randomNum;
		usedPokers[randomNum] = true;
		
		initialPokersOnDealer();
	}
	
	private void initialPokersOnDealer() {
		int[] resultOnDealer = sumOfPokers(pokersOnDealer);
		boolean isInitialized = false;
		int indexOfPokers = 2;
		while (!isInitialized) {
//			else if (resultOnDealer[0] == 21 || resultOnDealer[1] == 21) {
//				isInitialized = true;
//				showDealerResult();
//				System.out.println("Dealer: " + 21);
//				System.out.println("You LOSE")
//				JOptionPane.showMessageDialog(null,"Dealer BlackJack！You LOSE!","Message",JOptionPane.WARNING_MESSAGE);
//			}
			if (resultOnDealer.length == 1) {
				if (resultOnDealer[0] < 17) {
					if (indexOfPokers == 5) {
						isInitialized = true;
						showDealerResult();
						break;
					}
					Random r = new Random();
					int randomNum = r.nextInt(52);
					while (usedPokers[randomNum]) randomNum = r.nextInt(52);
					pokersOnDealer[indexOfPokers] = randomNum;
					usedPokers[randomNum] = true;
					indexOfPokers++;
					resultOnDealer = sumOfPokers(pokersOnDealer);
				}
				else if (resultOnDealer[0] < 21) {
					isInitialized = true;
					showDealerResult();
				}
				else if (resultOnDealer[0] == 21) {
					isInitialized = true;
					isStop = true;
					showDealerResult();
					JOptionPane.showMessageDialog(null,"You LOSE！","Message",JOptionPane.WARNING_MESSAGE);
				}
				else if (resultOnDealer[0] > 21){
					isInitialized = true;
					isStop = true;
					showDealerResult();
					int[] valueOnDealer = sumOfPokers(pokersOnDealer);
	        		int DealerResult = valueOnDealer.length == 1 || valueOnDealer[1] > 21? valueOnDealer[0]: valueOnDealer[1];
					System.out.println("Dealer: " + DealerResult);
//					System.out.println(valueOnDealer[0]);
//            		System.out.println(valueOnDealer[1]);
//            		System.out.println(valueOnDealer[2]);
//            		System.out.println(valueOnDealer[3]);
//            		System.out.println(valueOnDealer[4]);
					System.out.println("You WIN");
					user.cash += 2*Integer.valueOf(bet);
        			gamblingMoneyLabel.setText("Gambling Money: " + user.cash);
					JOptionPane.showMessageDialog(null,"Dealer Bust1！You WIN!","Message",JOptionPane.WARNING_MESSAGE);
				}
			}
			else {
				if (resultOnDealer[1] < 17 || (resultOnDealer[0] < 17 && resultOnDealer[1] > 21)) {
					if (indexOfPokers == 5) {
						isInitialized = true;
						showDealerResult();
					}
					Random r = new Random();
					int randomNum = r.nextInt(52);
					while (usedPokers[randomNum]) randomNum = r.nextInt(52);
					pokersOnDealer[indexOfPokers] = randomNum;
					usedPokers[randomNum] = true;
					indexOfPokers++;
					resultOnDealer = sumOfPokers(pokersOnDealer);
				}
				else if (resultOnDealer[0] == 21 || resultOnDealer[1] == 1) {
					isInitialized = true;
					isStop = true;
					showDealerResult();
					JOptionPane.showMessageDialog(null,"You LOSE！","Message",JOptionPane.WARNING_MESSAGE);
				}
				else if (resultOnDealer[1] <= 21 || (resultOnDealer[0] <= 21 && resultOnDealer[1] > 21)) {
					isInitialized = true;
					showDealerResult();
				}
				else if (resultOnDealer[0] > 21){
					isInitialized = true;
					isStop = true;
					showDealerResult();
					int[] valueOnDealer = sumOfPokers(pokersOnDealer);
	        		int DealerResult = valueOnDealer.length == 1 || valueOnDealer[1] > 21? valueOnDealer[0]: valueOnDealer[1];
					System.out.println("Dealer: " + DealerResult);
//					System.out.println(valueOnDealer[0]);
//            		System.out.println(valueOnDealer[1]);
//            		System.out.println(valueOnDealer[2]);
//            		System.out.println(valueOnDealer[3]);
//            		System.out.println(valueOnDealer[4]);
					System.out.println("You WIN");
					user.cash += 2*Integer.valueOf(bet);
        			gamblingMoneyLabel.setText("Gambling Money: " + user.cash);
					JOptionPane.showMessageDialog(null,"Dealer Bust2！You WIN!","Message",JOptionPane.WARNING_MESSAGE);
				}
			}
		}
	}
	
	private int[] sumOfPokers(int[] calPokers) {
		int[] valuePokers = new int[]{0, 0, 0, 0, 0};
		
		for (int i = 0; i < 5; i++)
			if (calPokers[i] < 52)	valuePokers[i] = (calPokers[i] % 13 + 1) >= 10? 10: (calPokers[i] % 13 + 1);
		
//		System.out.println(valuePokers[0]);
//		System.out.println(valuePokers[1]);
//		System.out.println(valuePokers[2]);
//		System.out.println(valuePokers[3]);
//		System.out.println(valuePokers[4]);
//		System.out.println();
		int result1 = 0, result2 = 0;
		for (int i = 0; i < 5; i++) {
			if (valuePokers[i] != 1 || (result1 != result2)) {
				result1 += valuePokers[i];
				result2 += valuePokers[i];
			}
			else {
				result1 += valuePokers[i];
				result2 += valuePokers[i]+10;
			}
		}
		
		ArrayList<Integer> returnArray = new ArrayList<Integer>();
		if (result1 == result2 || result2 > 21) returnArray.add(result1);
		else {
			returnArray.add(result1);
			returnArray.add(result2);
		}
		
		return result1 == result2? new int[]{result1}: new int[]{result1, result2};
	}
	
	private void showDealerResult() {
		topPanel.removeAll(); 
		if (!isStop) 
			topPanel.add(pokers[52]);
		else
			topPanel.add(pokers[pokersOnDealer[0]]);
//        System.out.print("Dealer: " + pokersOnDealer[0] + " ");
        for (int i = 1; i < 5; i++) {
        	topPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        	topPanel.add(pokers[pokersOnDealer[i]]);
//        	System.out.print(pokersOnDealer[i] + " ");
        }
//        System.out.println();
        topPanel.revalidate();
        topPanel.repaint();
	}
	
	private void updatePlayerResult() {
        bottomPanel.removeAll();
        bottomPanel.add(pokers[pokersOnPlayer[0]]);
//        System.out.print("Player: " + pokersOnPlayer[0] + " ");
        
        for (int i = 1; i < 5; i++) {
        	bottomPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        	bottomPanel.add(pokers[pokersOnPlayer[i]]);
//        	System.out.print(pokersOnPlayer[i] + " ");
        }
//        System.out.println();
        bottomPanel.revalidate();
        bottomPanel.repaint();
    }
	
	private GridBagConstraints gbcSettings(int gridx, int gridy, int anchor, int fill) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		gbc.anchor = anchor;
		gbc.fill = fill;
		return gbc;
	}
	
	class ButtonHitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
        	isHit = true;
//        	System.out.println(isStop);
        	
        	if (!isStop & isHit) {
        		isHit = false;
	            timer = new Timer(100, new HitListener());
	            Thread thread = new Thread(() -> {
	                try {
	                    Thread.sleep(200);
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
	                timer.stop();
	                int[] resultOnHand = sumOfPokers(pokersOnPlayer);
	                result.setText("Result: " + resultOnHand[0] + (resultOnHand.length == 1? "": " or " + resultOnHand[1]));
	                if (resultOnHand[0] > 21) {
	                	isStop = true;
	                	showDealerResult();
	                	int[] valueOnDealer = sumOfPokers(pokersOnDealer);
	            		int DealerResult = valueOnDealer.length == 1 || valueOnDealer[1] > 21? valueOnDealer[0]: valueOnDealer[1];
	            		int[] valueOnPlayer = sumOfPokers(pokersOnPlayer);
	            		int PlayerResult = valueOnPlayer.length == 1 || valueOnPlayer[1] > 21? valueOnPlayer[0]: valueOnPlayer[1];
	            		System.out.println("Dealer: " + DealerResult + "  You: " + PlayerResult);
//	            		System.out.println(valueOnDealer[0]);
//	            		System.out.println(valueOnDealer[1]);
//	            		System.out.println(valueOnDealer[2]);
//	            		System.out.println(valueOnDealer[3]);
//	            		System.out.println(valueOnDealer[4]);
	            		System.out.println("You LOSE");
	                	JOptionPane.showMessageDialog(null,"Bust3！","Message",JOptionPane.WARNING_MESSAGE);
	                }
	            });
	            thread.start();
	            timer.start();
        	}
        }
    }
	
	class ButtonBetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
        	String text = betMoneyText.getText();
        	try {
        		bet = Integer.valueOf(text);
        	} catch (NumberFormatException e) {
        		JOptionPane.showMessageDialog(null,"Please input Integer over 0！","Message",JOptionPane.WARNING_MESSAGE);
	        	return;
        	}
        	if (bet > user.cash) JOptionPane.showMessageDialog(null,"You Don't Have Enough Bet！","Message",JOptionPane.WARNING_MESSAGE);
        	else if (!isBet) {
        		isBet = true;
        		isStop = false;
        		user.cash -= bet;
        		gamblingMoneyLabel.setText("Gambling Money: " + user.cash);
        		initialBlackJack();
        		updatePlayerResult();
        		int[] resultOnHand = sumOfPokers(pokersOnPlayer);
        		result.setText("Result: " + resultOnHand[0] + (resultOnHand.length == 1? "": " or " + resultOnHand[1]));
        	}
        }
    }
	
	class ButtonNewGameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
        	isBet = false;
        	isStop = false;
        	for (int i = 0; i < 5; i++) { 
        		pokersOnPlayer[i] = 57+i;
        		pokersOnDealer[i] = 52+i;
        	}
        	
        	for (int i = 0; i < 62; i++) usedPokers[i] = false;
        	updatePlayerResult();
        	showDealerResult();
        	
        	System.out.println("New Game");
        }
    }
	
	class HitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
        	if (isBet) {
        		Random r = new Random();
        		int randomNum = r.nextInt(52);
	            while (usedPokers[randomNum]) randomNum = r.nextInt(52);
	            usedPokers[randomNum] = true;
	            if (pokersOnPlayer[0] >= 52)	pokersOnPlayer[0] = randomNum;
	            else if (pokersOnPlayer[1] >= 52)	pokersOnPlayer[1] = randomNum;
	            else if (pokersOnPlayer[2] >= 52)	pokersOnPlayer[2] = randomNum;
	            else if (pokersOnPlayer[3] >= 52)	pokersOnPlayer[3] = randomNum;
	            else if (pokersOnPlayer[4] >= 52)	pokersOnPlayer[4] = randomNum;
	            updatePlayerResult();
	            if (pokersOnPlayer[4] < 52) isStop = true;
        	}
        }
    }
	
	class ButtonStopListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
        		isStop = true;
        		isBet = false;
        		showDealerResult();
        		int[] valueOnDealer = sumOfPokers(pokersOnDealer);
        		int DealerResult = valueOnDealer.length == 1 || valueOnDealer[1] > 21? valueOnDealer[0]: valueOnDealer[1];
        		int[] valueOnPlayer = sumOfPokers(pokersOnPlayer);
        		int PlayerResult = valueOnPlayer.length == 1 || valueOnPlayer[1] > 21? valueOnPlayer[0]: valueOnPlayer[1];
        		System.out.println("Dealer: " + DealerResult + "  You: " + PlayerResult);
        		System.out.println(DealerResult < PlayerResult? "You WIN": "You LOSE");
//        		System.out.println(valueOnDealer[0]);
//        		System.out.println(valueOnDealer[1]);
//        		System.out.println(valueOnDealer[2]);
//        		System.out.println(valueOnDealer[3]);
//        		System.out.println(valueOnDealer[4]);
        		if (DealerResult >= PlayerResult) {
        			JOptionPane.showMessageDialog(null,"You LOSE！","Message",JOptionPane.WARNING_MESSAGE);
        		}
        		else {
        			user.cash += 2*Integer.valueOf(bet);
        			gamblingMoneyLabel.setText("Gambling Money: " + user.cash);
        			JOptionPane.showMessageDialog(null,"You WIN！","Message",JOptionPane.WARNING_MESSAGE);
        		}
        		isStop = false;
        }
    }
	
	public static void main(String[] args) {
//		User user = new User("D");
//		BlackJackPanel blackJackPanel = new BlackJackPanel(user);
	}
}

