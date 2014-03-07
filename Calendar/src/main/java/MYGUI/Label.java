package MYGUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

import View.Config;

public class Label {

	/** FontType uses in Titles */
	public static final String FontType = "Segoe UI";

	public static void decorateTitle( JLabel label) {

		label.setFont(new Font(FontType, Font.PLAIN, 27));
		label.setForeground(new Color(255, 255, 255));

	//	label.setAlignmentX(label.CENTER_ALIGNMENT);
		label.setLocation(Config.WIDTH / 2 - label.getWidth() / 2 ,10);

	}
	
	public static void decorateNormal(JLabel label){
		
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Segoe UI", Font.PLAIN, 11));
	
		
	}

}
