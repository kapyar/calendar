package MYGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class MetroEditablePin extends JPanel {

	private MetroPassView pass;
	private MyButton del;

	public MetroEditablePin() {

		this.setSize(new Dimension(210, 31));
		this.setBackground(Color.WHITE);
		this.setLayout(null);

		pass = new MetroPassView();
		pass.setBounds(8, 5, 144, 20);
		add(pass);
		pass.setColumns(10);
		pass.getDocument().addDocumentListener(new DocumentListener() {

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
		del.setLocation(this.getWidth()-30, 3);
		add(del);

		addInnerListener();
	}

	private void addInnerListener() {
		del.addActionListener(new InnerListener());
		pass.addActionListener(new InnerListener());

	}

	private class InnerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			Object source = e.getSource();

			Color highlight = Color.WHITE;
			Border border = BorderFactory.createSoftBevelBorder(1, highlight,
					highlight);

			if (source == pass) {
				System.out.println("border?");
				setBorder(border);
			}

			if (source == del) {
				// deleting
				setBorder(border);
				String s = pass.getText();
				cleanField(pass);
				if (!s.isEmpty()) {
					s = s.substring(0, s.length() - 1);
					pass.setText(s);
				}
			}// del

		}

		private void cleanField(MetroPassView txtCardNumb) {
			txtCardNumb.setBackground(new Color(255, 255, 255));
		}
	}// END InnerListener

	public MetroPassView getPass() {
		return pass;
	}

	public MyButton getDel() {
		return del;
	}

	public String getText() {
		return pass.getText();
	}
	
	public void showError() {

		Color highlight = new Color(255, 67, 67);
		Border border = BorderFactory.createSoftBevelBorder(1, highlight,
				highlight);
		this.setBorder(border);
	}
}
