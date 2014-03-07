package View;

import MYGUI.ButtonFactory;
import MYGUI.Label;
import MYGUI.MetroEditablePane;
import MYGUI.MetroEditablePin;
import MYGUI.MetroPanel;
import MYGUI.MyButton;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Register extends MetroPanel {
	private MetroEditablePane txtLogin;
	private MetroEditablePane txtEmail;
	private MetroEditablePane txtPhone;
	private MetroEditablePin txtPass;
	private MetroEditablePin txtConfirmPass;
	private MyButton btnSave;
	private MyButton btnCancel;

	public Register() {

		int w = 215;
		int h = 30;

		JLabel lblRegister = new JLabel("Register", SwingConstants.CENTER);
		Label.decorateTitle(lblRegister);
		lblRegister.setBounds(312, 26, 236, 40);
		add(lblRegister);

		JLabel lblLogin = new JLabel("login", SwingConstants.CENTER);
		Label.decorateNormal(lblLogin);
		lblLogin.setBounds(328, 147, 30, 14);
		add(lblLogin);

		txtLogin = new MetroEditablePane();
		txtLogin.setBounds(368, 144, w, h);
		add(txtLogin);

		JLabel lblEmail = new JLabel("e-mail", SwingConstants.CENTER);
		Label.decorateNormal(lblEmail);
		lblEmail.setBounds(328, 192, 40, 14);
		add(lblEmail);

		txtEmail = new MetroEditablePane();
		txtEmail.setBounds(368, 189, w, h);
		add(txtEmail);
		

		JLabel lblNewLabel = new JLabel("phone", SwingConstants.CENTER);
		Label.decorateNormal(lblNewLabel);
		lblNewLabel.setBounds(328, 236, 40, 14);
		add(lblNewLabel);

		txtPhone = new MetroEditablePane();
		txtPhone.setBounds(368, 233, w, h);
		add(txtPhone);

		JLabel lblPassword = new JLabel("password", SwingConstants.CENTER);
		Label.decorateNormal(lblPassword);
		lblPassword.setBounds(312, 279, 60, 14);
		add(lblPassword);

		txtPass = new MetroEditablePin();
		txtPass.getDel().setLocation(180, 2);
		txtPass.setBounds(368, 276, w, h);
		add(txtPass);

		JLabel lblConfigm = new JLabel("configm password",
				SwingConstants.CENTER);
		Label.decorateNormal(lblConfigm);
		lblConfigm.setBounds(260, 323, 120, 14);
		add(lblConfigm);

		txtConfirmPass = new MetroEditablePin();
		txtConfirmPass.getDel().setLocation(180, 3);
		txtConfirmPass.setBounds(368, 317, w, h);
		add(txtConfirmPass);

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

	public boolean isConfirmedPass() {
		return (!txtPass.getText().equals(""))
				&& txtPass.getText().equals(txtConfirmPass.getText())
				&& txtPass.getText().length() > 5;
	}

	public boolean isAllowToRegister() {
		highLight();
		return isConfirmedPass() && txtEmail.getText().contains("@");
	}

	public void highLight() {
	

		if (!isConfirmedPass()) {
			txtPass.showError();
			txtConfirmPass.showError();
		}
		
		if(!txtEmail.getText().contains("@")){
			txtEmail.showError();
		}
	}
}
