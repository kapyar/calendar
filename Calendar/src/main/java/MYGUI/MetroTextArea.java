package MYGUI;

import java.awt.Font;

import javax.swing.JTextArea;

import View.ConfigColor;



public class MetroTextArea extends JTextArea {

	public MetroTextArea() {
		super();
		setFont(new Font("Segoe UI", Font.PLAIN, 15));
		this.setBackground(ConfigColor._bgEDP);
	}

	public MetroTextArea(int arg0, int arg1) {
		super(arg0, arg1);
		setFont(new Font("Segoe UI", Font.PLAIN, 15));
		this.setBackground(ConfigColor._bgEDP);
	}

}
