package MYGUI;

import java.awt.Dimension;

import javax.swing.JPanel;

import View.ConfigColor;



public class MetroBigEditPanel extends JPanel {
	private MetroTextArea textField;
	
	public MetroBigEditPanel() {
		super();
		this.setSize(new Dimension(283, 111));
		this.setBackground(ConfigColor._bgEDP);
		this.setLayout(null);
		
		textField = new MetroTextArea();
		textField.getMargin();
		textField.setBounds(8, 5, this.getWidth() - 45, this.getHeight()-10);
		textField.setColumns(5);
		textField.setRows(4);
		textField.setLineWrap(true);
		add(textField);
	}

	public MetroTextArea getTextField() {
		return textField;
	}

	

}
