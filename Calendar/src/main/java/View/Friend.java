package View;

import java.awt.event.ActionListener;
import java.util.List;

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

import main.DataBaseAPI;
import main.User;

public class Friend extends MetroPanel {
	private MyButton btnBack;
	private MyButton btnMakeFriend;
	private DataBaseAPI dataBase = DataBaseAPI.GET;
	
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
		try {
			final List<User> us = dataBase.getAllUsers();
		
		list.setModel(new AbstractListModel() {
			String[] values = new String[us.size()] ;
			
			{
			for (int i= 0; i<us.size();++i)
			{
				
				values[i]=us.get(i).getName();
			}
			}
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
		list.setBounds(365, 142, 195, 301);
		add(list);

		btnMakeFriend = ButtonFactory.getNormalButton("Make Friend");
		btnMakeFriend.setBounds(692, 526, 98, 23);
		add(btnMakeFriend);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initList() {
		// query to DB to initialize

	}

	public void addListener(ActionListener l) {
		//System.out.println("Ac List Chos");
		btnBack.addActionListener(l);
		btnMakeFriend.addActionListener(l);
	}

	public MyButton getBtnBack() {
		return btnBack;
	}
	public MyButton getBtnMakeFriend() {
		return btnMakeFriend;
	}
}
