package MYGUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import View.Config;
import View.ConfigColor;

public class Decorator {

	/** FontType uses in Titles */
	public static final String FontType = "Segoe UI";

	public static void decorateTitle(JLabel label) {

		label.setFont(new Font(FontType, Font.PLAIN, 27));
		label.setForeground(ConfigColor._bCTC);

		// label.setAlignmentX(label.CENTER_ALIGNMENT);
		label.setLocation(Config.WIDTH / 2 - label.getWidth() / 2, 415);

	}

	public static void decorateNormal(JLabel label) {

		label.setSize(new Dimension(215,30));
		label.setForeground(ConfigColor._bCTC);
		label.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 15));

	}

	public static void decorateBorderTitle(JComponent component,String s) {

		Font font = new Font("Segoe UI", Font.ROMAN_BASELINE, 15);
		
		component.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createBevelBorder(1), s, 0, 0, font,
				ConfigColor._bCTC));
		component.setAlignmentX(SwingConstants.CENTER);

	}

	public static void decorateJCheckBox(JCheckBox chckbx) {
		
		chckbx.setForeground(ConfigColor._bgEDP);
		chckbx.setFont(Config.font);
		chckbx.setBackground(Config.COLOR);
		chckbx.setForeground(ConfigColor._bCTC);
		chckbx.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 15));
	}

}
