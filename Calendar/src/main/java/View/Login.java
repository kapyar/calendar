package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import MYGUI.ButtonFactory;
import MYGUI.ConfigGUICLient;
import MYGUI.Decorator;
import MYGUI.MetroEditablePane;
import MYGUI.MetroEditablePin;
import MYGUI.MetroPanel;
import MYGUI.MyButton;

import javax.swing.JButton;

import java.awt.Component;

import javax.swing.SwingConstants;

public class Login extends MetroPanel implements MouseListener {

	// private Font localFont = new Font("Viner Hand ITC", Font.PLAIN, 19);
	private String FontType = ConfigGUICLient.FontType;

	private MyButton myButton_Enter;
	private MetroPanel panel;

	private MyButton myButton_Cancel;
	private ArrayList<MyButton> listOfComponents = new ArrayList<MyButton>();
	private JRadioButton rdbtnCardNumb;
	private JRadioButton rdbtPass;
	private MetroEditablePane txt;
	private MetroEditablePin pin;

	// StartFrame sizes and coordinates
	private int _W;
	private int _x;

	public JProgressBar progressBar;
	private MyButton btnRegister;

	public Login() {
	
		_W = super.getWidth();
		_x = super.getX();

		JLabel lblBalanceTitle = new JLabel("Create Your Event",
				SwingConstants.CENTER);
		Decorator.decorateTitle(lblBalanceTitle);
		lblBalanceTitle.setBounds(277, 40, 290, 40);
		add(lblBalanceTitle);
		
		panel = new MetroPanel();
		panel.setBounds(42, 116, 688, 296);
		panel.setOpaque(true);
		panel.setBackground(new Color(40, 140, 255));
		panel.setVisible(true);
		add(panel);

		myButton_Enter = ButtonFactory.getNumbButton("Enter", 'E');
		myButton_Enter.setBounds(346, 205, 87, 31);
		panel.add(myButton_Enter);
		// listOfComponents.add(myButton_Enter);

		myButton_Cancel = ButtonFactory.getNumbButton("Cancel", 'C');
		myButton_Cancel.setBounds(249, 205, 87, 31);
		panel.add(myButton_Cancel);
		listOfComponents.add(myButton_Cancel);

		// ���������� �� ������ �������� �����
		int _xRightElemets = 224;
		int _yRightElemets = 68;
		int _deltaYRE = 35;// delta y right element;

		int _WRightElemets = 190;
		int _HRightElemets = 31;

		JLabel lblNubrerCart = new JLabel("Login", SwingConstants.CENTER);
		Decorator.decorateNormal(lblNubrerCart);
		lblNubrerCart.setBounds(243, 56, 190, 15);
		panel.add(lblNubrerCart);

		txt = new MetroEditablePane();
		txt.getDel().setLocation(160, 3);
		txt.getTextField().setBounds(8, 5, 144, 20);
		txt.getTextField().addMouseListener(this);
		txt.setBounds(243, 75, _WRightElemets, _HRightElemets);
		panel.add(txt);

		JLabel lblPin = new JLabel("Password", SwingConstants.CENTER);
		Decorator.decorateNormal(lblPin);
		lblPin.setBounds(243, 126, 190, 15);
		panel.add(lblPin);

		pin = new MetroEditablePin();
		pin.getPass().addMouseListener(this);
		pin.setBounds(243, 145, _WRightElemets, _HRightElemets);
		panel.add(pin);

		rdbtnCardNumb = new JRadioButton("");
		rdbtnCardNumb.setBounds(222, 78, 21, 23);
		rdbtnCardNumb.setBackground(new Color(40, 140, 255));
		rdbtnCardNumb.setSelected(true);
		panel.add(rdbtnCardNumb);

		rdbtPass = new JRadioButton("");
		rdbtPass.setBounds(222, 148, 21, 23);
		rdbtPass.setBackground(new Color(40, 140, 255));
		panel.add(rdbtPass);

		ButtonGroup radioGroup = new ButtonGroup();
		radioGroup.add(rdbtnCardNumb);
		radioGroup.add(rdbtPass);
		
		btnRegister = ButtonFactory.getNormalButton("Register");
		btnRegister.setBounds(599, 273, 89, 23);
		panel.add(btnRegister);

		progressBar = new JProgressBar();
		progressBar.setBounds(42, 408, 688, 29);
		progressBar.setVisible(false);
		add(progressBar);
	}

	public JRadioButton getCardRadioBtn() {
		return rdbtnCardNumb;
	}

	public void clearFields() {
		txt.getTextField().setText("");
		pin.getPass().setText("");
		rdbtnCardNumb.setFocusable(true);
	}

	public void addListener(ActionListener m) {
		myButton_Enter.addActionListener(m);
		myButton_Cancel.addActionListener(m);
		btnRegister.addActionListener(m);
	}

	public MyButton getMyButton_Enter() {
		return myButton_Enter;
	}

	private void cleanField(JTextField txtCardNumb) {
		txtCardNumb.setBackground(new Color(255, 255, 255));
	}

	private void highlightInputFields(final JTextField txtCardNumb,
			final JTextField txtPin) {
		txtCardNumb.setBackground(new Color(123, 255, 0));
		txtPin.setBackground(new Color(123, 255, 0));

	}

	// Need to find how to input into right fields
	private void writeToField(String s) {
		if (rdbtnCardNumb.isSelected()) {
			txt.getTextField().setText(txt.getTextField().getText() + s);
			cleanField(txt.getTextField());

		} else if (rdbtPass.isSelected()) {
			pin.getPass().setText(pin.getPass().getText() + s);
			cleanField(pin.getPass());
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object source = e.getSource();
		if (source == txt.getTextField()) {
			// System.out.println("txt");
			rdbtnCardNumb.setSelected(true);
		}
		if (source == pin.getPass()) {
			// System.out.println("pin");
			rdbtPass.setSelected(true);
		}
		// System.out.println("("+e.getX()+":"+e.getY()+")");

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// empty method

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// empty method

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// empty method
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// empty method

	}

	public String getLogin() {
		return txt.getTextField().getText();
	}

	public String getPass() {
		return pin.getPass().getText();
	}

	public JProgressBar getProgressBar() {
		return progressBar;
	}

	public MyButton getMyButton_Cancel() {
		return myButton_Cancel;
	}

	public MyButton getBtnRegister() {
		return btnRegister;
	}

	public MetroEditablePane getTxt() {
		return txt;
	}

	public MetroEditablePin getPin() {
		return pin;
	}
}
