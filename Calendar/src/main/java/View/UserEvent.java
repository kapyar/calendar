package View;

import java.awt.Color;
import java.awt.ScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import MYGUI.ButtonFactory;
import MYGUI.Decorator;
import MYGUI.MetroBigEditPanel;
import MYGUI.MetroEditablePane;
import MYGUI.MetroList;
import MYGUI.MetroPanel;
import MYGUI.MyButton;
import Model.Model;
import WEB.DataBaseAPI;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
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

import WEB.User;

public class UserEvent extends MetroPanel {
	private MetroEditablePane txtName;
	private MetroEditablePane txtWhere;
	private MyButton btnCancel;
	private MyButton btnSave;
	private String[] st;
	private Date dateEvent;
	private JList listOfFriends;
	private MetroBigEditPanel txtDescription;
	private JCheckBox chckbxEmail;
	private JCheckBox chckbxSms;
	private JComboBox comboBox;
	private JProgressBar progressBar;
	private JComboBox cmbbxWhen;
	private ScrollPane scrollPane;

	public UserEvent(Date dateWhen) {
		int w = 215;
		int h = 30;
		int _Y = 120;
		int deltaY = 50;
		int lblX = 450;
		int txtX = 520;
		btnSave = ButtonFactory.getNormalButton("Save");
		;
		btnSave.setLocation(txtX + 110, _Y + deltaY * 7);
		add(btnSave);

		btnCancel = ButtonFactory.getNormalButton("Cancel");
		btnCancel.setLocation(lblX, _Y + deltaY * 7);
		add(btnCancel);

		JLabel lblNewEvent = new JLabel("New Event");
		Decorator.decorateTitle(lblNewEvent);
		lblNewEvent.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewEvent.setBounds(336, 25, 150, 40);
		add(lblNewEvent);

		JLabel lblName = new JLabel("What");
		Decorator.decorateNormal(lblName);
		lblName.setLocation(lblX, _Y + deltaY * 0);
		add(lblName);

		txtName = new MetroEditablePane();
		txtName.setLocation(txtX, _Y + deltaY * 0);
		add(txtName);

		JLabel lblPlace = new JLabel("Where");
		Decorator.decorateNormal(lblPlace);
		lblPlace.setLocation(lblX, _Y + deltaY * 1);
		add(lblPlace);

		txtWhere = new MetroEditablePane();
		txtWhere.setLocation(txtX, _Y + deltaY * 1);
		add(txtWhere);

		JLabel lblWhen = new JLabel("When");
		Decorator.decorateNormal(lblWhen);
		lblWhen.setLocation(lblX, _Y + deltaY * 2);
		add(lblWhen);

		MetroPanel panel = new MetroPanel();
		panel.setBounds(lblX, _Y + deltaY * 3, 131, 140);
		Decorator.decorateBorderTitle(panel, "Remind");
		add(panel);
		int _inPX = 28;
		int _inPY = 20;
		int _inPdelta = 23;
		chckbxEmail = new JCheckBox("email");
		Decorator.decorateJCheckBox(chckbxEmail);
		chckbxEmail.setBounds(_inPX, _inPY + _inPdelta * 0, 97, 23);
		panel.add(chckbxEmail);

		chckbxSms = new JCheckBox("sms");
		Decorator.decorateJCheckBox(chckbxSms);
		chckbxSms.setBounds(_inPX, _inPY + _inPdelta * 1, 97, 23);
		panel.add(chckbxSms);

		JLabel lblRemindIn = new JLabel("In:");
		lblRemindIn.setLocation(_inPX, _inPY + _inPdelta * 2);
		Decorator.decorateNormal(lblRemindIn);
		panel.add(lblRemindIn);

		comboBox = new JComboBox();
		comboBox.setModel(Config.getTimeModel());
		comboBox.setBounds(_inPX, _inPY + _inPdelta * 3 + 10, panel.getWidth()
				- 2 * _inPX, 20);
		panel.add(comboBox);

		JLabel lblDescription = new JLabel("Description");
		Decorator.decorateNormal(lblDescription);
		lblDescription.setLocation(90, 80);
		add(lblDescription);

		txtDescription = new MetroBigEditPanel();
		txtDescription.setBounds(90, _Y + deltaY * 0, 283,
				deltaY * 3 + panel.getHeight());
		add(txtDescription);

		cmbbxWhen = new JComboBox();
		cmbbxWhen.setModel(Config.getTimeModel());
		cmbbxWhen.setBounds(txtX, _Y + deltaY * 2, 210, 31);
		add(cmbbxWhen);

		MetroPanel panelFriend = new MetroPanel();
		panelFriend.setBounds(lblX + 150, _Y + deltaY * 3, 131, 140);
		Decorator.decorateBorderTitle(panelFriend, "Invite Friends");
		add(panelFriend);

		dateEvent = dateWhen;

		scrollPane = new ScrollPane();
		listOfFriends = new MetroList();
		listOfFriends
				.setToolTipText("Invite your frinds to do something together");

		DefaultListCellRenderer renderer = (DefaultListCellRenderer) listOfFriends
				.getCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setBounds(10, 20, panelFriend.getWidth() - 20,
				panelFriend.getHeight() - 30);
		scrollPane.add(listOfFriends);

		panelFriend.add(scrollPane);

		progressBar = new JProgressBar();
		progressBar.setBounds(txtDescription.getX(), btnCancel.getY(),
				txtDescription.getWidth(), 39);
		progressBar.setVisible(false);
		add(progressBar);

		setListModel();
	}

	public void setListModel() {
		List<User> users = Model.MODEL.doGetAllFriend();
		System.out.println("AAABBB" + users);
		System.out.println(users);
		st = new String[users.size()];

		for (int i = 0; i < users.size(); ++i) {
			st[i] = users.get(i).getUser_mail();
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

	}

	public void clearFileds() {
		txtName.getTextField().setText("");
		txtWhere.getTextField().setText("");
		txtDescription.getTextField().setText("");
		chckbxEmail.setSelected(false);
		chckbxSms.setSelected(false);
		comboBox.setModel(Config.getTimeModel());
		cmbbxWhen.setModel(Config.getTimeModel());
		return;
	};

	public boolean isAlowed() {
		return lenght(txtName) && lenght(txtWhere);
	}

	private boolean lenght(MetroEditablePane m) {
		if (m.getText().length() > 1)
			return true;
		m.showError();
		return false;
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

	public MetroBigEditPanel getTxtDescription() {
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

	public JProgressBar getProgressBar() {
		return progressBar;
	}

	public JComboBox getCmbbxWhen() {
		return cmbbxWhen;
	}
}
