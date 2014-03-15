package View;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import MYGUI.ButtonFactory;
import MYGUI.Decorator;
import MYGUI.MetroPanel;
import MYGUI.MyButton;
import Model.Model;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JProgressBar;
import javax.swing.ListModel;
import javax.swing.ProgressMonitor;
import javax.swing.SwingConstants;

import main.DataBaseAPI;
import main.User;

public class Friend extends MetroPanel {

	private JButton btnMakeFriendship;
	private JButton btnBack;
	private JProgressBar progressBar;
	private String[] st;
	private JList list;

	public Friend() {

		JLabel lblBalanceTitle = new JLabel("Create Your Event");
		Decorator.decorateTitle(lblBalanceTitle);
		lblBalanceTitle.setBounds(this.getPreferredSize().width / 2
				- lblBalanceTitle.getWidth() / 2, 40, 290, 40);
		add(lblBalanceTitle);

		btnMakeFriendship = ButtonFactory.getNormalButton("Make Friendship");
		btnMakeFriendship.setLocation(681, 537);
		btnMakeFriendship.setSize(100, 50);
		add(btnMakeFriendship);

		btnBack = ButtonFactory.getNormalButton("Back");
		btnBack.setLocation(10, 537);
		btnBack.setSize(100, 50);
		add(btnBack);

		progressBar = new JProgressBar();
		progressBar.setBounds(50, 411, 700, 29);
		progressBar.setVisible(false);
		add(progressBar);

		List<User> users = Model.MODEL.doGetAllUsers();
		st = new String[users.size()];

		list = new JList();

		for (int i = 0; i < users.size(); ++i) {
			st[i] = users.get(i).getMail();
		}

		list.setModel(new AbstractListModel() {
			String[] values = st;

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});

		DefaultListCellRenderer renderer = (DefaultListCellRenderer) list
				.getCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		list.setBounds(380, 91, 147, 264);
		add(list);

	}

	public void addListener(ActionListener l) {
		btnBack.addActionListener(l);
		btnMakeFriendship.addActionListener(l);
	}

	public JButton getBtnMakeFriendship() {
		return btnMakeFriendship;
	}

	public JButton getBtnBack() {
		return btnBack;
	}

	public ArrayList<String> getSelectedMails() {

		int[] inList = list.getSelectedIndices();

		ArrayList<String> l = new ArrayList<String>();

		for (int i = 0; i < inList.length; ++i) {
			l.add((String) list.getModel().getElementAt(inList[i]));

		}

		return l;
	}
}
