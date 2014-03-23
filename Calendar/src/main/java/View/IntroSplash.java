package View;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import MYGUI.MetroPanel;

public class IntroSplash extends MetroPanel implements Runnable {

	public IntroSplash() {
		System.out.println("IntroSplash");
		JLabel logo = new JLabel();
		logo.setSize(400, 400);
		logo.setLocation(110, 70);
		ImageIcon start = new ImageIcon("resources\\splash.png");
		logo.setIcon(start);
		this.add(logo);
		System.out.println("Added splash");
	}

	@Override
	public void run() {
		Model.Model.MODEL.dummy();

	}
}
