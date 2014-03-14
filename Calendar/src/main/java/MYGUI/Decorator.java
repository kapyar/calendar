package MYGUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
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

		label.setForeground(Color.WHITE);
		label.setFont(new Font("Segoe UI", Font.PLAIN, 11));

	}

	public static void decorateBorderTitle(JComponent component,String s) {

		Font font = new Font("Segoe UI", 1, 11);
		component.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createBevelBorder(1), s, 0, 0, font,
				Color.WHITE));
		component.setAlignmentX(SwingConstants.CENTER);

	}

}
