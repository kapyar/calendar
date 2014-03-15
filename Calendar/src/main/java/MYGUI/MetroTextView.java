package MYGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.Border;

import View.ConfigColor;

public class MetroTextView extends JTextField {

	public MetroTextView() {
		super();
		setFont(new Font("Segoe UI", Font.PLAIN, 15));
		this.setBackground(ConfigColor._bgEDP);
	}

	@Override
	public void setBorder(Border border) {
		// TODO Auto-generated method stub
		super.setBorder(BorderFactory.createEmptyBorder());
	}

};
