package Controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.TableView.TableCell;

import Model.UserDataHolder;
import View.Config;
import View.Friend;
import View.ChooserPanel;
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
	private ChooserPanel choose;
	private Friend friend;

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

							choose = new ChooserPanel();
							choose.addListener(new ChooseListener());
							frame.showPane(choose);

						} else {
							login.getTxt().showError();
							login.getPin().showError();
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
				frame.showPane(choose);

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

				Date d = new Date();

				Integer day = (Integer) target.getValueAt(row, column);
				d.setDate(day);

				int month = Config.getIndex(Config.MONTHS, calendar
						.getLblMonth().getText());
				d.setMonth(month);

				Integer year = Integer.parseInt((String) calendar.getCmbYear()
						.getSelectedItem());
				d.setYear(year);

				System.out.println("Choosen date: " + d);

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

				choose = new ChooserPanel();
				choose.addListener(new ChooseListener());
				frame.showPane(choose);

			}

			if (source == register.getBtnSave()) {

				if (register.isAllowToRegister()) {

					class MyWorker extends SwingWorker<Object, Object> {
						@Override
						protected Object doInBackground() throws Exception {
							register.getProgressBar().setVisible(true);
							register.getProgressBar().setIndeterminate(true);

							String name = register.getTxtLogin().getText();
							String pass = register.getTxtConfirmPass()
									.getText();

							String mail = register.getTxtEmail().getText();
							String phone = register.getTxtPhone().getText();
							UserDataHolder user = new UserDataHolder(name,
									pass, mail, phone);

							if (Model.Model.MODEL.doRegisterNewOne(user)) {

								if (login == null) {
									login = new Login();
									login.addListener(new LoginListener());
								}

								frame.showPane(login);

							} else {
								System.out.println("Already exists");
							}
							return null;
						}

						@Override
						protected void done() {
							register.getProgressBar().setVisible(false);
						}
					}
					new MyWorker().execute();

				} else {

					System.out.println("CCant.register you");
				}
			}

		}
	}// END RegisterListener

	private class ChooseListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();

			if (source == choose.getBtnEvent()) {
				calendar = new MyCalendar();
				calendar.addListener(new CalendarListner());
				calendar.addMyActionListener(new CalendarListner());
				frame.showPane(calendar);
			}

			if (source == choose.getBtnBack()) {
				Model.Model.MODEL.doLogOut();
				frame.showPane(login);
			}

			if (source == choose.getBtnFriends()) {
				friend = new Friend();
				friend.addListener(new FriendsListener());
				frame.showPane(friend);
			}

		}

	}// END ChooseListener

	private class FriendsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();

			if (source == friend.getBtnBack()) {
				frame.showPane(choose);
			}

		}

	}// END FriendsListener

}
