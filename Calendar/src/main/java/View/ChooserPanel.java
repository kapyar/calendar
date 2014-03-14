package View;

import MYGUI.ButtonFactory;
import MYGUI.ConfigGUICLient;
import MYGUI.Decorator;
import MYGUI.MetroPanel;
import MYGUI.MyButton;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.border.Border;

public class ChooserPanel extends MetroPanel {
	private MyButton btnBack;
	private MyButton btnFriends;
	private MyButton btnEvent;
	private int _W;
	private JProgressBar progressBar;
	
	public ChooserPanel() {
		_W = super.getWidth();

			
		JLabel lblNewLabel = new JLabel("Choose Your Action");
		lblNewLabel.setBounds(0, 0, 260, 35);
	
		Decorator.decorateTitle(lblNewLabel);
		add(lblNewLabel);

		btnBack = ButtonFactory.getButtonForGirl("Back");
		//btnBack.setBounds(45, Config.HEIGHT - 120, Config._bW, Config._bH);
		btnBack.setLocation(new Point(45,Config.HEIGHT - 120));
		add(btnBack);

//		JPanel panel = new MetroPanel();
//		panel.setBounds(0, 202, 271, 266);
//		int midX = Config.WIDTH / 2 - panel.getWidth() / 2;
//		panel.setBounds(midX, 202, 271, 266);
//		Font font = new Font("Segoe UI", 1, 15);
//		panel.setBorder(BorderFactory.createTitledBorder(
//				BorderFactory.createBevelBorder(1), "Action", 0, 0, font,
//				Color.WHITE));
//		panel.setAlignmentX(SwingConstants.CENTER);
//
//		add(panel);

		btnFriends = ButtonFactory.getButtonForGirl("Add Friend");
		btnFriends.setLocation(new Point(45,163));
		//btnFriends.setBounds(45, 163, Config._bW, Config._bH);
		this.add(btnFriends);

		btnEvent = ButtonFactory.getButtonForGirl("Create Event");
		btnEvent.setLocation(new Point(620,163));
		//btnEvent.setBounds(620, 163, Config._bW, Config._bH);
		this.add(btnEvent);
		
		JLabel logo = new JLabel();
		logo.setSize(Config.WIDTH, Config.HEIGHT);
		ImageIcon start = new ImageIcon("resources\\fons.png");
		logo.setIcon(start);
		this.add(logo);
		
		progressBar = new JProgressBar();
		progressBar.setBounds(50, 411, 700, 29);
		progressBar.setVisible(false);
		add(progressBar);
		
	}


	
	public void addListener(ActionListener l) {
		
		btnBack.addActionListener(l);
		btnFriends.addActionListener(l);
		btnEvent.addActionListener(l);
		
	}

	public MyButton getBtnBack() {
		return btnBack;
	}

	public MyButton getBtnFriends() {
		return btnFriends;
	}

	public MyButton getBtnEvent() {
		return btnEvent;
	}



	public JProgressBar getProgressBar() {
		return progressBar;
	}
}
