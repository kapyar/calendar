package Controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.TableView.TableCell;

import View.Login;
import View.MainContainer;
import View.MyCalendar;
import View.Register;
import View.UserEvent;

public class Controller {

	private MainContainer frame;
	private MyCalendar calendar;
	private Login login;
	private UserEvent event;
	private Register register;

	public Controller(MainContainer main) {
		frame = main;
		login = new Login();
		frame.showPane(login);
		login.addListener(new LoginListener());
	}

	// ////LISTENER CLASSES////////////

	private class LoginListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			Object source = e.getSource();

			if (source == login.getMyButton_Enter()) {
				class MyWorker extends SwingWorker<Object, Object> {

					@Override
					protected Object doInBackground() throws Exception {
						login.getProgressBar().setVisible(true);
						login.getProgressBar().setIndeterminate(true);
						String name = login.getLogin();
						String pass = login.getPass();
						if (Model.Model.MODEL.doLogIn(name, pass)) {

							calendar = new MyCalendar();
							calendar.addListener(new CalendarListner());
							calendar.addMyActionListener(new CalendarListner());
							frame.showPane(calendar);

						} else {
							// incorrect input value
						}
						return null;
					}

					@Override
					protected void done() {
						login.getProgressBar().setVisible(false);
					}
				}
				new MyWorker().execute();
			}

			if (source == login.getMyButton_Cancel()) {
				System.exit(0);
			}
			if (source == login.getBtnRegister()) {
				register = new Register();
				register.addListener(new RegisterListener());
				frame.showPane(register);

			}

		}
	}// end of LoginListener

	private class CalendarListner implements MouseListener, ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();

			if (source == calendar.getBtnBack()) {
				frame.showPane(login);

			}

		}

		@Override
		public void mouseClicked(MouseEvent e) {

			if (e.getClickCount() == 2) {

				JTable target = (JTable) e.getSource();
				int row = target.getSelectedRow();
				int column = target.getSelectedColumn();

				System.out.println("Found, column: " + column + " row: " + row);
				// need to rennder chosen cell

				event = new UserEvent();
				event.addListener(new UserEventListener());
				frame.showPane(event);

			}

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

	}// END CalendarListner

	private class UserEventListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();

			if (source == event.getBtnCancel()) {
				frame.showPane(calendar);
			}

			if (source == event.getBtnSave()) {
				frame.showPane(calendar);
			}

		}

	}// END UserEventListener

	private class RegisterListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();

			if (source == register.getBtnCancel()) {
				frame.showPane(login);
			}

			if (source == register.getBtnSave()) {

				if (register.isAllowToRegister()) {
					if (calendar == null) {
						calendar = new MyCalendar();
						CalendarListner cc = new CalendarListner();
						calendar.addListener(cc);
						calendar.addMyActionListener(cc);
					}

					frame.showPane(calendar);
				}else{
					System.out.println("cant.register you");
				}
			}

		}

	}// END RegisterListener

}
