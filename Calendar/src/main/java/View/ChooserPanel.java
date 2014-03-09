package View;

import MYGUI.ButtonFactory;
import MYGUI.Decorator;
import MYGUI.MetroPanel;
import MYGUI.MyButton;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.border.Border;

public class ChooserPanel extends MetroPanel {
	private MyButton btnBack;
	private MyButton btnFriends;
	private MyButton btnEvent;

	public ChooserPanel() {

		JLabel lblNewLabel = new JLabel("Choose Your Action",SwingConstants.CENTER);
		lblNewLabel.setSize(260, 35);
		Decorator.decorateTitle(lblNewLabel);
		add(lblNewLabel);

		btnBack = ButtonFactory.getNormalButton("Back");
		btnBack.setBounds(10, 510, 89, 23);
		add(btnBack);

		JPanel panel = new MetroPanel();
		panel.setBounds(0, 202, 271, 266);
		int midX = Config.WIDTH / 2 - panel.getWidth() / 2;
		panel.setBounds(midX, 202, 271, 266);
		Font font = new Font("Segoe UI", 1, 11);
		panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createBevelBorder(1), "Action", 0, 0, font,
				Color.WHITE));
		panel.setAlignmentX(SwingConstants.CENTER);

		add(panel);

		btnFriends = ButtonFactory.getNormalButton("Add Friend");
		btnFriends.setBounds(88, 99, 101, 23);
		panel.add(btnFriends);

		btnEvent = ButtonFactory.getNormalButton("Create Event");
		btnEvent.setBounds(88, 164, 101, 23);
		panel.add(btnEvent);
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
}
