package View;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import MYGUI.MetroPanel;

public class IntroSplash extends MetroPanel implements Runnable {

	public IntroSplash() {
		JLabel logo = new JLabel();
		logo.setSize(Config.WIDTH, Config.HEIGHT);
		ImageIcon start = new ImageIcon("resources\\splah.png");
		logo.setIcon(start);
		this.add(logo);
	}

	@Override
	public void run() {
		Model.Model.MODEL.dummy();

	}
}
