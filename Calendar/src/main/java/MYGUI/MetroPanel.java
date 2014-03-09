package MYGUI;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import View.Config;


public class MetroPanel extends JPanel {
	public MetroPanel () {
		this.setSize(new Dimension(800, 600));
		this.setBackground(Config.COLOR);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
	}


}
