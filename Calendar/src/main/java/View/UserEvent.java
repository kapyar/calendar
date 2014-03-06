package View;

import java.awt.event.ActionListener;

import MYGUI.MetroPanel;
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

public class UserEvent extends MetroPanel {
	private JTextField txtName;
	private JTextField txtWhere;
	private JTextField txtWhen;
	private JButton btnCancel;
	private JButton btnSave;
	
	public UserEvent() {
		
		btnSave = new JButton("Save");
		btnSave.setBounds(701, 539, 89, 23);
		add(btnSave);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(602, 539, 89, 23);
		add(btnCancel);
		
		JLabel lblNewEvent = new JLabel("New Event");
		lblNewEvent.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewEvent.setBounds(336, 25, 60, 14);
		add(lblNewEvent);
		
		txtName = new JTextField();
		txtName.setBounds(175, 111, 89, 20);
		add(txtName);
		txtName.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(119, 114, 46, 14);
		add(lblName);
		
		JLabel lblPlace = new JLabel("Where");
		lblPlace.setBounds(119, 158, 46, 14);
		add(lblPlace);
		
		txtWhere = new JTextField();
		txtWhere.setBounds(175, 155, 86, 20);
		add(txtWhere);
		txtWhere.setColumns(10);
		
		JLabel lblWhen = new JLabel("When");
		lblWhen.setBounds(119, 208, 46, 14);
		add(lblWhen);
		
		txtWhen = new JTextField();
		txtWhen.setBounds(175, 205, 86, 20);
		add(txtWhen);
		txtWhen.setColumns(10);
		
		JTextPane txtDescription = new JTextPane();
		txtDescription.setBounds(422, 111, 283, 111);
		add(txtDescription);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(535, 97, 70, 14);
		add(lblDescription);
		
		JLabel lblRemind = new JLabel("Remind");
		lblRemind.setBounds(121, 309, 46, 14);
		add(lblRemind);
		
		JCheckBox chckbxEmail = new JCheckBox("email");
		chckbxEmail.setBounds(131, 330, 97, 23);
		add(chckbxEmail);
		
		JCheckBox chckbxSms = new JCheckBox("sms");
		chckbxSms.setBounds(131, 366, 97, 23);
		add(chckbxSms);
		
		JList listOfFriends = new JList();
		listOfFriends.setBounds(428, 308, 100, 140);
		add(listOfFriends);
		
		JLabel lblInvite = new JLabel("Invite");
		lblInvite.setBounds(455, 293, 46, 14);
		add(lblInvite);
	}

	public void addListener(ActionListener l) {
		btnSave.addActionListener(l);
		btnCancel.addActionListener(l);

	}

	public JTextField getTxtName() {
		return txtName;
	}

	public JTextField getTxtWhere() {
		return txtWhere;
	}

	public JTextField getTxtWhen() {
		return txtWhen;
	}

	public JButton getBtnCancel() {
		return btnCancel;
	}

	public JButton getBtnSave() {
		return btnSave;
	}
}
