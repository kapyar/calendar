package View;

import java.awt.event.ActionListener;

import MYGUI.ButtonFactory;
import MYGUI.Label;
import MYGUI.MetroEditablePane;
import MYGUI.MetroPanel;
import MYGUI.MyButton;

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
	private MetroEditablePane txtName;
	private MetroEditablePane txtWhere;
	private MetroEditablePane txtWhen;
	private MyButton btnCancel;
	private MyButton btnSave;
	
	public UserEvent() {
		
		int w = 215;
		int h = 30;
		btnSave = ButtonFactory.getNormalButton("Save");
		btnSave.setBounds(701, 539, 89, 23);
		add(btnSave);
		
		btnCancel = ButtonFactory.getNormalButton("Cancel");
		btnCancel.setBounds(602, 539, 89, 23);
		add(btnCancel);
		
		JLabel lblNewEvent = new JLabel("New Event");
		Label.decorateTitle(lblNewEvent);
		lblNewEvent.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewEvent.setBounds(336, 25, 150, 40);
		add(lblNewEvent);
		
		txtName = new MetroEditablePane();
		txtName.setBounds(175, 111, w, h);
		add(txtName);
	
		
		JLabel lblName = new JLabel("Name");
		Label.decorateNormal(lblName);
		lblName.setBounds(119, 114, 46, 14);
		add(lblName);
		
		JLabel lblPlace = new JLabel("Where");
		Label.decorateNormal(lblPlace);
		lblPlace.setBounds(119, 158, 46, 14);
		add(lblPlace);
		
		txtWhere = new MetroEditablePane();
		txtWhere.setBounds(175, 155, w, h);
		add(txtWhere);
		
		
		JLabel lblWhen = new JLabel("When");
		Label.decorateNormal(lblWhen);
		lblWhen.setBounds(119, 208, 46, 14);
		add(lblWhen);
		
		txtWhen = new MetroEditablePane();
		txtWhen.setBounds(175, 205, w, h);
		add(txtWhen);
		
		
		JTextPane txtDescription = new JTextPane();
		txtDescription.setBounds(422, 111, 283, 111);
		add(txtDescription);
		
		JLabel lblDescription = new JLabel("Description");
		Label.decorateNormal(lblDescription);
		lblDescription.setBounds(535, 97, 70, 14);
		add(lblDescription);
		
		JLabel lblRemind = new JLabel("Remind");
		Label.decorateNormal(lblRemind);
		lblRemind.setBounds(121, 309, 46, 14);
		add(lblRemind);
		
		JCheckBox chckbxEmail = new JCheckBox("email");
		chckbxEmail.setBackground(Config.COLOR);
		chckbxEmail.setBounds(131, 330, 97, 23);
		add(chckbxEmail);
		
		JCheckBox chckbxSms = new JCheckBox("sms");
		chckbxSms.setBackground(Config.COLOR);
		chckbxSms.setBounds(131, 366, 97, 23);
		add(chckbxSms);
		
		JList listOfFriends = new JList();
		listOfFriends.setModel(new AbstractListModel() {
			String[] values = new String[] {"Andrew", "Mike", "John", "Victor"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		listOfFriends.setBounds(428, 308, 100, 140);
		add(listOfFriends);
		
		JLabel lblInvite = new JLabel("Invite");
		Label.decorateNormal(lblInvite);
		lblInvite.setBounds(455, 293, 46, 14);
		add(lblInvite);
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
}
