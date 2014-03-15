package MYGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import View.ConfigColor;

public class MetroEditablePane extends JPanel {

	private MetroTextView textField;
	private MyButton del;

	public MetroEditablePane() {
		this.setSize(new Dimension(210, 31));
		this.setBackground(ConfigColor._bgEDP);
		this.setLayout(null);

		textField = new MetroTextView();
		textField.getMargin();
		textField.setBounds(8, 5, this.getWidth() - 45, 20);
		add(textField);
		textField.setColumns(10);
		textField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				Color highlight = Color.WHITE;
				Border border = BorderFactory.createSoftBevelBorder(1,
						highlight, highlight);
				setBorder(border);

			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub

			}
		});

		del = ButtonFactory.getDelButton("resources\\imagesClient\\del_1.png");
		del.setLocation(this.getWidth() - 30, 3);
		add(del);
		addInnerListener();
	}

	private void addInnerListener() {
		InnerListener in = new InnerListener();
		del.addActionListener(in);

	}

	private class InnerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			Object source = e.getSource();

			Color highlight = Color.WHITE;
			Border border = BorderFactory.createSoftBevelBorder(1, highlight,
					highlight);

			if (source == textField) {
				System.out.println("border?");
				setBorder(border);
			}

			if (source == del) {
				// deleting
				setBorder(border);
				String s = textField.getText();

				if (!s.isEmpty()) {
					s = s.substring(0, s.length() - 1);
					textField.setText(s);
				}
			}// del

		}

	}// END InnerListener

	public MyButton getDel() {
		return del;
	}

	public void setTextField(MetroTextView textField) {
		this.textField = textField;
	}

	public MetroTextView getTextField() {
		// TODO Auto-generated method stub
		return textField;
	}

	public String getText() {
		return textField.getText();
	}

	public void showError() {

		Color highlight = new Color(255, 67, 67);
		Border border = BorderFactory.createSoftBevelBorder(1, highlight,
				highlight);
		this.setBorder(border);
	}
}
