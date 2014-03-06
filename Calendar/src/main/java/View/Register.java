package View;

import MYGUI.ButtonFactory;
import MYGUI.MetroPanel;
import MYGUI.MyButton;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Register extends MetroPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private MyButton btnSave;
	private MyButton btnCancel;

	public Register() {

		JLabel lblRegister = new JLabel("Register");
		lblRegister.setBounds(423, 26, 46, 14);
		add(lblRegister);

		JLabel lblLogin = new JLabel("login");
		lblLogin.setBounds(328, 147, 30, 14);
		add(lblLogin);

		textField = new JTextField();
		textField.setBounds(368, 144, 86, 20);
		add(textField);
		textField.setColumns(10);

		JLabel lblEmail = new JLabel("e-mail");
		lblEmail.setBounds(328, 192, 30, 14);
		add(lblEmail);

		textField_1 = new JTextField();
		textField_1.setBounds(368, 189, 86, 20);
		add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel = new JLabel("phone");
		lblNewLabel.setBounds(328, 236, 30, 14);
		add(lblNewLabel);

		textField_2 = new JTextField();
		textField_2.setBounds(368, 233, 86, 20);
		add(textField_2);
		textField_2.setColumns(10);

		JLabel lblPassword = new JLabel("password");
		lblPassword.setBounds(312, 279, 46, 14);
		add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(368, 276, 86, 20);
		add(passwordField);

		JLabel lblConfigm = new JLabel("configm password");
		lblConfigm.setBounds(272, 315, 86, 14);
		add(lblConfigm);

		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(368, 312, 86, 20);
		add(passwordField_1);

		btnSave = ButtonFactory.getNormalButton("Save");
		btnSave.setBounds(701, 527, 89, 23);
		add(btnSave);

		btnCancel = ButtonFactory.getNormalButton("Cancel");
		btnCancel.setBounds(602, 527, 89, 23);
		add(btnCancel);
	}

	public void addListener(ActionListener l) {
		btnSave.addActionListener(l);
		btnCancel.addActionListener(l);

	}

	public MyButton getBtnSave() {
		return btnSave;
	}

	public MyButton getBtnCancel() {
		return btnCancel;
	}
}
