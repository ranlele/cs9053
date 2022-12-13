package Muyu;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import common.User;

public class Muyu extends JPanel implements ActionListener {
	User user = new User("a");
	JButton muyu_bt = new JButton("Click");
	int sin = user.cash;
	
	public Muyu() {
		setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		setLayout(new GridLayout(2,1));
		JLabel muyu_label = new JLabel("Knock the Muyu to deposit your cash");
		add(muyu_label);
		add(muyu_bt);
		muyu_bt.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		muyu_bt.setText(String.format("Your sin %d ", --sin));
		//to do, deposit sin into database 
		if (sin <= 2) {
			sin = 1;
		}
 
    };


}
