package View;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

import MYGUI.ButtonFactory;
import MYGUI.ConfigGUICLient;
import MYGUI.Decorator;
import MYGUI.MetroEditablePane;
import MYGUI.MetroEditablePin;
import MYGUI.MetroPanel;
import MYGUI.MyButton;

import javax.swing.UIManager;

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

		JLabel lblBalanceTitle = new JLabel("Create Your Event");
		Decorator.decorateTitle(lblBalanceTitle);
		lblBalanceTitle.setBounds(_W / 2
				- lblBalanceTitle.getPreferredSize().width / 2, 40, 290, 40);
		add(lblBalanceTitle);

		panel = new MetroPanel();
		panel.setBounds(50, 116, _W - 100, 296);
		panel.setOpaque(true);
		panel.setBackground(ConfigColor._logBG);
		panel.setVisible(true);
		add(panel);

		int midP_X = panel.getWidth() / 2;
		int _Y = 20;
		int delta = 42;

		myButton_Enter = ButtonFactory.getNumbButton("Log In", 'L');
		myButton_Enter.setLocation(new Point(midP_X - myButton_Enter.getWidth()
				/ 2 + 60, _Y + delta * 5 - 20));
		panel.add(myButton_Enter);
		// listOfComponents.add(myButton_Enter);

		myButton_Cancel = ButtonFactory.getNumbButton("Exit", 'E');
		myButton_Cancel.setLocation(new Point(midP_X
				- myButton_Cancel.getWidth() / 2 - 60, _Y + delta * 5 - 20));
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
		lblNubrerCart.setLocation(midP_X - lblNubrerCart.getWidth() / 2, _Y
				+ delta * 0);
		panel.add(lblNubrerCart);

		txt = new MetroEditablePane();
		txt.getTextField().setBounds(8, 5, 144, 20);
		txt.getTextField().addMouseListener(this);
		txt.setLocation(midP_X - txt.getWidth() / 2, _Y + delta * 1);
		panel.add(txt);

		JLabel lblPin = new JLabel("Password", SwingConstants.CENTER);
		Decorator.decorateNormal(lblPin);
		lblPin.setLocation(midP_X - lblPin.getWidth() / 2, _Y + delta * 2);
		panel.add(lblPin);

		pin = new MetroEditablePin();
		pin.getPass().addMouseListener(this);
		pin.setLocation(midP_X - pin.getWidth() / 2, _Y + delta * 3);
		panel.add(pin);

		rdbtnCardNumb = new JRadioButton("");
		rdbtnCardNumb.setBounds(222, _Y + delta * 1 - 5, 21, 40);
		rdbtnCardNumb.setBackground(ConfigColor._rbtnBG);
		rdbtnCardNumb.setSelected(true);
		panel.add(rdbtnCardNumb);

		rdbtPass = new JRadioButton("");
		rdbtPass.setBounds(222, _Y + delta * 3 - 5, 21, 40);
		rdbtPass.setBackground(ConfigColor._rbtnBG);
		panel.add(rdbtPass);

		ButtonGroup radioGroup = new ButtonGroup();
		radioGroup.add(rdbtnCardNumb);
		radioGroup.add(rdbtPass);

		btnRegister = ButtonFactory.getNormalButton("Register");
		btnRegister.setLocation(new Point(580, 250));

		panel.add(btnRegister);

		progressBar = new JProgressBar();
		progressBar.setBounds(55, 420, 690, 29);
		progressBar.setVisible(false);

		add(progressBar);

	}

	public JRadioButton getCardRadioBtn() {
		return rdbtnCardNumb;
	}

	public void clearFields() {
		// txt.getTextField().setText("");
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
		txtCardNumb.setBackground(ConfigColor._clFlBG);
	}

	private void highlightInputFields(final JTextField txtCardNumb,
			final JTextField txtPin) {
		txtCardNumb.setBackground(new Color(255, 0, 0));
		txtPin.setBackground(new Color(255, 0, 0));

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
