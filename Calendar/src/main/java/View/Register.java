package View;

import MYGUI.ButtonFactory;
import MYGUI.Decorator;
import MYGUI.MetroEditablePane;
import MYGUI.MetroEditablePin;
import MYGUI.MetroPanel;
import MYGUI.MyButton;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar;

public class Register extends MetroPanel {
	private MetroEditablePane txtLogin;
	private MetroEditablePane txtEmail;
	private MetroEditablePane txtPhone;
	private MetroEditablePin txtPass;
	private MetroEditablePin txtConfirmPass;
	private MyButton btnSave;
	private MyButton btnCancel;
	private JProgressBar progressBar;

	public Register() {

		int w = 215;
		int h = 30;

		MetroPanel panel = new MetroPanel();
		panel.setBounds(199, 61, 400, 450);
		int midX = Config.WIDTH / 2 - panel.getWidth() / 2;
		panel.setBounds(midX, 100, 400, 400);
		Font font = new Font("Segoe UI", 1, 25);
		panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createBevelBorder(1), "Action", 0, 0, font,
				Color.WHITE));
		panel.setAlignmentX(SwingConstants.CENTER);

		add(panel);

		JLabel lblLogin = new JLabel("login", SwingConstants.CENTER);
		lblLogin.setBounds(118, 55, 30, 14);
		panel.add(lblLogin);
		Decorator.decorateNormal(lblLogin);

		txtLogin = new MetroEditablePane();
		txtLogin.setBounds(151, 47, 215, 30);

		panel.add(txtLogin);

		JLabel lblEmail = new JLabel("e-mail", SwingConstants.CENTER);
		lblEmail.setBounds(110, 107, 40, 14);
		panel.add(lblEmail);
		Decorator.decorateNormal(lblEmail);

		txtEmail = new MetroEditablePane();
		txtEmail.setBounds(152, 102, 215, 30);
		panel.add(txtEmail);

		JLabel lblNewLabel = new JLabel("phone", SwingConstants.CENTER);
		lblNewLabel.setBounds(112, 167, 40, 14);
		panel.add(lblNewLabel);
		Decorator.decorateNormal(lblNewLabel);

		txtPhone = new MetroEditablePane();
		txtPhone.setBounds(155, 157, 215, 30);
		panel.add(txtPhone);

		JLabel lblPassword = new JLabel("password", SwingConstants.CENTER);
		lblPassword.setBounds(91, 220, 60, 14);
		panel.add(lblPassword);
		Decorator.decorateNormal(lblPassword);

		txtPass = new MetroEditablePin();
		txtPass.setBounds(154, 210, 215, 30);
		panel.add(txtPass);
		txtPass.getDel().setLocation(180, 2);

		JLabel lblConfigm = new JLabel("configm password",
				SwingConstants.CENTER);
		lblConfigm.setBounds(35, 266, 120, 14);
		panel.add(lblConfigm);
		Decorator.decorateNormal(lblConfigm);

		txtConfirmPass = new MetroEditablePin();
		txtConfirmPass.setBounds(155, 258, 215, 30);
		panel.add(txtConfirmPass);
		txtConfirmPass.getDel().setLocation(180, 3);

		btnSave = ButtonFactory.getNormalButton("Save");
		//btnSave.setBounds(701, 527, 89, 23);
		btnSave.setLocation(new Point(701,521));
		add(btnSave);

		JLabel lblRegister = new JLabel("Register", SwingConstants.CENTER);
		lblRegister.setBounds(0, 0, 236, 40);
		add(lblRegister);
		Decorator.decorateTitle(lblRegister);

		btnCancel = ButtonFactory.getNormalButton("Cancel");
		
		//btnCancel.setBounds(602, 527, 89, 23);
		btnCancel.setLocation(new Point(602,527));
		add(btnCancel);

		progressBar = new JProgressBar();
		progressBar.setBounds(200, 499, 400, 23);
		progressBar.setVisible(false);
		add(progressBar);

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
				&& txtPass.getText().length() > 4;
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

		if (!txtEmail.getText().contains("@")) {
			txtEmail.showError();
		}
	}

	public JProgressBar getProgressBar() {
		return progressBar;
	}

	public MetroEditablePane getTxtLogin() {
		return txtLogin;
	}

	public MetroEditablePane getTxtEmail() {
		return txtEmail;
	}

	public MetroEditablePane getTxtPhone() {
		return txtPhone;
	}

	public MetroEditablePin getTxtPass() {
		return txtPass;
	}

	public MetroEditablePin getTxtConfirmPass() {
		return txtConfirmPass;
	}
}
