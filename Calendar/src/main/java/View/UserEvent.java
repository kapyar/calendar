package View;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import MYGUI.ButtonFactory;
import MYGUI.Decorator;
import MYGUI.MetroEditablePane;
import MYGUI.MetroPanel;
import MYGUI.MyButton;
import Model.Model;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.JFormattedTextField;
import javax.swing.AbstractListModel;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import main.DataBaseAPI;
import main.User;

public class UserEvent extends MetroPanel {
	private MetroEditablePane txtName;
	private MetroEditablePane txtWhere;
	private MetroEditablePane txtWhen;
	private MyButton btnCancel;
	private MyButton btnSave;
	private String[] st;
	private Date dateEvent;
	private JList listOfFriends;
	private JTextPane txtDescription;
	private JCheckBox chckbxEmail;
	private JCheckBox chckbxSms;
	private JComboBox comboBox;

	public UserEvent(Date dateWhen) {
		int w = 215;
		int h = 30;
		btnSave = ButtonFactory.getNormalButton("Save");
		btnSave.setBounds(701, 539, Config._bW, Config._bH);
		add(btnSave);

		btnCancel = ButtonFactory.getNormalButton("Cancel");
		btnCancel.setBounds(602, 539, Config._bW, Config._bH);
		add(btnCancel);

		JLabel lblNewEvent = new JLabel("New Event");
		Decorator.decorateTitle(lblNewEvent);
		lblNewEvent.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewEvent.setBounds(336, 25, 150, 40);
		add(lblNewEvent);

		txtName = new MetroEditablePane();
		txtName.setBounds(175, 111, w, h);
		add(txtName);

		JLabel lblName = new JLabel("Name");
		Decorator.decorateNormal(lblName);
		lblName.setBounds(119, 114, 46, 14);
		add(lblName);

		JLabel lblPlace = new JLabel("Where");
		Decorator.decorateNormal(lblPlace);
		lblPlace.setBounds(119, 158, 46, 14);
		add(lblPlace);

		txtWhere = new MetroEditablePane();
		txtWhere.setBounds(175, 155, w, h);
		add(txtWhere);

		JLabel lblWhen = new JLabel("When");
		Decorator.decorateNormal(lblWhen);
		lblWhen.setBounds(119, 208, 46, 14);
		add(lblWhen);

		txtWhen = new MetroEditablePane();
		txtWhen.setBounds(175, 205, w, h);
		add(txtWhen);

		txtDescription = new JTextPane();
		txtDescription.setBounds(422, 111, 283, 111);
		// Decorator.decorateBorderTitle(txtDescription, "Description");
		add(txtDescription);

		JLabel lblDescription = new JLabel("Description");
		Decorator.decorateNormal(lblDescription);
		lblDescription.setBounds(535, 97, 70, 14);
		add(lblDescription);

		JLabel lblInvite = new JLabel("Invite Friends");
		Decorator.decorateNormal(lblInvite);
		lblInvite.setBounds(440, 293, 120, 14);
		add(lblInvite);

		MetroPanel panel = new MetroPanel();
		panel.setBounds(136, 308, 131, 140);
		Decorator.decorateBorderTitle(panel, "Remind");
		add(panel);

		chckbxEmail = new JCheckBox("email");
		chckbxEmail.setBounds(28, 24, 97, 23);
		chckbxEmail.setForeground(Color.WHITE);
		chckbxEmail.setFont(Config.font);
		panel.add(chckbxEmail);
		chckbxEmail.setBackground(Config.COLOR);

		chckbxSms = new JCheckBox("sms");
		chckbxSms.setBounds(28, 60, 97, 23);
		chckbxSms.setForeground(Color.WHITE);
		chckbxSms.setFont(Config.font);
		panel.add(chckbxSms);
		chckbxSms.setBackground(Config.COLOR);

		JLabel lblRemindIn = new JLabel("Remind in");
		lblRemindIn.setBounds(545, 293, 60, 14);
		Decorator.decorateNormal(lblRemindIn);
		add(lblRemindIn);

		comboBox = new JComboBox();
		comboBox.setModel(Config.comboBoxModel);
		comboBox.setBounds(615, 290, 90, 20);
		add(comboBox);
		dateEvent = dateWhen;

		listOfFriends = new JList();

		List<User> users =  Model.MODEL.doGetAllFriend();
		System.out.println(users);
		st = new String[users.size()];

		for (int i = 0; i < users.size(); ++i) {
			st[i] = users.get(i).getMail();
		}

		listOfFriends
				.setToolTipText("Invite your frinds to do something together");
		listOfFriends.setModel(new AbstractListModel() {
			String[] values = st;

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		listOfFriends.setBounds(428, 308, 100, 140);
		DefaultListCellRenderer renderer = (DefaultListCellRenderer) listOfFriends
				.getCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		add(listOfFriends);
	}

	public void addListener(ActionListener l) {
		btnSave.addActionListener(l);
		btnCancel.addActionListener(l);

	}

	public JButton getBtnCancel() {
		return btnCancel;
	}

	public JButton getBtnSave() {
		return btnSave;
	}

	// return selected string in list
	public ArrayList<String> getSelected() {

		int[] inList = listOfFriends.getSelectedIndices();

		ArrayList<String> l = new ArrayList<String>();

		for (int i = 0; i < inList.length; ++i) {
			l.add((String) listOfFriends.getModel().getElementAt(inList[i]));

		}

		return l;
	}

	public MetroEditablePane getTxtName() {
		return txtName;
	}

	public MetroEditablePane getTxtWhere() {
		return txtWhere;
	}

	public MetroEditablePane getTxtWhen() {
		return txtWhen;
	}

	public JTextPane getTxtDescription() {
		return txtDescription;
	}

	public JCheckBox getChckbxEmail() {
		return chckbxEmail;
	}

	public JCheckBox getChckbxSms() {
		return chckbxSms;
	}

	public JComboBox getComboBox() {
		return comboBox;
	}

	public Date getDateEvent() {
		return dateEvent;
	}
}
