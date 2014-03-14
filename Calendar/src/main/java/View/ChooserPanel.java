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
	private Image bgImage = Toolkit.getDefaultToolkit().getImage("resources\\fons.jpg");
	private JProgressBar progressBar;;

	
	public ChooserPanel() {
		_W = super.getWidth();

			
		JLabel lblNewLabel = new JLabel("Choose Your Action");
		lblNewLabel.setBounds(0, 0, 260, 35);
	
		Decorator.decorateTitle(lblNewLabel);
		add(lblNewLabel);

		btnBack = ButtonFactory.getNormalButton("Back");
		btnBack.setBounds(45, Config.HEIGHT - 120, Config._bW, Config._bH);
		btnBack.setForeground(ConfigColor._bCTC.brighter());
		btnBack.setHoverBackgroundColor(ConfigColor._bCHBC);
		btnBack.setPressedBackgroundColor(ConfigColor._bCPBC);
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

		btnFriends = ButtonFactory.getNormalButton("Add Friend");
		btnFriends.setBounds(45, 163, Config._bW, Config._bH);
		btnFriends.setForeground(ConfigColor._bCTC.brighter());
		btnFriends.setHoverBackgroundColor(ConfigColor._bCHBC);
		btnFriends.setPressedBackgroundColor(ConfigColor._bCPBC);
		this.add(btnFriends);

		btnEvent = ButtonFactory.getNormalButton("Create Event");
		btnEvent.setBounds(620, 163, Config._bW, Config._bH);
		btnEvent.setForeground(ConfigColor._bCTC.brighter());
		btnEvent.setHoverBackgroundColor(ConfigColor._bCHBC);
		btnEvent.setPressedBackgroundColor(ConfigColor._bCPBC);
		this.add(btnEvent);
		
		JLabel logo = new JLabel();
		logo.setSize(800, 600);
		logo.setLocation(0, 0);
		ImageIcon start = new ImageIcon("resources\\fons.png");
		logo.setIcon(start);
		this.add(logo);
		
		progressBar = new JProgressBar();
		progressBar.setBounds(50, 411, 700, 29);
		progressBar.setVisible(false);
		add(progressBar);
		
		
	}

	@Override
	  protected void paintComponent(Graphics g) {

	    super.paintComponent(g);
	        g.drawImage(bgImage, 0, 0, null);
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
