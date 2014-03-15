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
import javax.swing.UIManager;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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
		panel.setSize(new Dimension(400, 400));
		int midXfP = Config.WIDTH / 2 - panel.getWidth() / 2;
		int midX = Config.WIDTH / 2;
		panel.setLocation(midXfP,50);
		Font font = new Font("Segoe UI", 1, 25);
		panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createBevelBorder(1), "Registration", 0, 0, font,
				ConfigColor._bCTC));
		panel.setAlignmentX(SwingConstants.CENTER);
		add(panel);
		
		int _Y = 50;
		int delta = 42+20;
		
		
		JLabel lblLogin = new JLabel("login", SwingConstants.LEFT);
		lblLogin.setLocation(new Point(118,_Y+delta*0));
		panel.add(lblLogin);
		Decorator.decorateNormal(lblLogin);

		txtLogin = new MetroEditablePane();
		txtLogin.setLocation(new Point(Config._EP_X,_Y+delta*0));
		panel.add(txtLogin);

		JLabel lblEmail = new JLabel("e-mail", SwingConstants.LEFT);
		lblEmail.setLocation(new Point(110,_Y+delta*1));
		panel.add(lblEmail);
		Decorator.decorateNormal(lblEmail);

		txtEmail = new MetroEditablePane();
		txtEmail.setLocation(new Point(Config._EP_X,_Y+delta*1));
		
		panel.add(txtEmail);

		JLabel lblNewLabel = new JLabel("phone", SwingConstants.LEFT);
		lblNewLabel.setLocation(new Point(112,_Y+delta*2));
		panel.add(lblNewLabel);
		Decorator.decorateNormal(lblNewLabel);

		txtPhone = new MetroEditablePane();
		txtPhone.setLocation(new Point(Config._EP_X,_Y+delta*2));
		panel.add(txtPhone);

		JLabel lblPassword = new JLabel("password", SwingConstants.LEFT);
		lblPassword.setLocation(new Point(91,_Y+delta*3));
		panel.add(lblPassword);
		Decorator.decorateNormal(lblPassword);

		txtPass = new MetroEditablePin();
		txtPass.setLocation(new Point(Config._EP_X,_Y+delta*3));
		panel.add(txtPass);
		txtPass.getDel().setLocation(180, 2);

		JLabel lblConfigm = new JLabel("confirm password",SwingConstants.LEFT);
		lblConfigm.setLocation(new Point(35,_Y+delta*4));
		panel.add(lblConfigm);
		Decorator.decorateNormal(lblConfigm);

		txtConfirmPass = new MetroEditablePin();
		txtConfirmPass.setLocation(new Point(Config._EP_X,_Y+delta*4));
		panel.add(txtConfirmPass);
		
		txtConfirmPass.getDel().setLocation(180, 3);
		
		btnSave = ButtonFactory.getNormalButton("Save");
		btnSave.setLocation(new Point(midX+25,Config.HEIGHT - 120));
		add(btnSave);
		

		btnCancel = ButtonFactory.getNormalButton("Cancel");
		
	
		btnCancel.setLocation(new Point(midX-125,Config.HEIGHT - 120));
		add(btnCancel);

		progressBar = new JProgressBar();
		progressBar.setBounds(10, panel.getHeight()-35, panel.getWidth()-20, 25);
		progressBar.setVisible(false);
//		UIManager.put("ProgressBar.background", Color.white);
//		UIManager.put("ProgressBar.foreground", new Color(63, 210, 253));
		panel.add(progressBar);

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
