package View;

import java.awt.event.ActionListener;

import MYGUI.ButtonFactory;
import MYGUI.Decorator;
import MYGUI.MetroPanel;
import MYGUI.MyButton;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.SwingConstants;

public class Friend extends MetroPanel {
	private MyButton btnBack;
	private MyButton btnMakeFriend;

	public Friend() {

		JLabel lblFindYourFriends = new JLabel("Find Your Friends");
		lblFindYourFriends.setBounds(411, 11, 220, 35);
		Decorator.decorateTitle(lblFindYourFriends);
		add(lblFindYourFriends);

		btnBack = ButtonFactory.getNormalButton("Back");
		btnBack.setBounds(10, 525, 89, 23);
		add(btnBack);

		JList list = new JList();
		initList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] { "s" };

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		DefaultListCellRenderer renderer = (DefaultListCellRenderer) list.getCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		list.setBounds(365, 142, 195, 301);
		add(list);

		btnMakeFriend = ButtonFactory.getNormalButton("Make Friend");
		btnMakeFriend.setBounds(692, 526, 98, 23);
		add(btnMakeFriend);
	}

	private void initList() {
		// query to DB to initialize

	}

	public void addListener(ActionListener l) {
		btnBack.addActionListener(l);
	}

	public MyButton getBtnBack() {
		return btnBack;
	}
}
