package Controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.TableView.TableCell;

import com.mysql.jdbc.TimeUtil;

import main.User;
import Model.EventHolder;
import Model.Model;
import Model.MyUser;
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
	private Date dateWhen;

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
						if (Model.MODEL.doLogIn(name, pass)) {

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
				event = new UserEvent(new Date());
				event.addListener(new UserEventListener());
				frame.showPane(event);
				//System.exit(0);
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
				System.out.println("WAT");
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

				dateWhen = new java.util.Date();

				Integer day = (Integer) target.getValueAt(row, column);
				dateWhen.setDate(day);

				int month = Config.getIndex(Config.MONTHS, calendar
						.getLblMonth().getText());
				dateWhen.setMonth(month);

				Integer year = Integer.parseInt((String) calendar.getCmbYear()
						.getSelectedItem());
				dateWhen.setYear(year);

				System.out.println("Choosen date: " + dateWhen);

				event = new UserEvent(dateWhen);
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
				// TODO hold data in EventHOlder
				// and send to Model to do the rest
				EventHolder eh = getDataToSend();
				Model.MODEL.doCreateEvent(eh);

				frame.showPane(calendar);
			}

		}

		private EventHolder getDataToSend() {

			String title = event.getTxtName().getText();
			String where = event.getTxtWhere().getText();
			String when = event.getTxtWhen().getText();

			String desc = event.getTxtWhen().getText();
			boolean isEmail = event.getChckbxEmail().isSelected();
			boolean isSms = event.getChckbxSms().isSelected();
			Date date = event.getDateEvent();

			String remind = (String) event.getComboBox().getSelectedItem();

			ArrayList<User> users = (ArrayList<User>) Model.MODEL
					.doGetListPeolpleByEmail(event.getSelected());
			// to send to eventHolder
			ArrayList<Integer> usersId = new ArrayList<>();

			for (User u : users) {
				usersId.add(u.getId());
			}

			EventHolder eh = new EventHolder(title, when, where, desc, isEmail,
					isSms, usersId, date, remind);

			return eh;

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
							MyUser user = new MyUser(name, pass, mail, phone);

							if (Model.MODEL.doRegisterNewOne(user)) {

								if (calendar == null) {
									calendar = new MyCalendar();
									CalendarListner cc = new CalendarListner();
									calendar.addListener(cc);
									calendar.addMyActionListener(cc);
								}

								frame.showPane(calendar);

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
				Model.MODEL.doLogOut();
				frame.showPane(login);
			}

			if (source == choose.getBtnFriends()) {
				class MyWorker extends SwingWorker<Object, Object> {

					@Override
					protected Object doInBackground() throws Exception {

						choose.getProgressBar().setVisible(true);
						choose.getProgressBar().setIndeterminate(true);
						
						friend = new Friend();
						friend.addListener(new FriendsListener());
						frame.showPane(friend);

						return null;
					}

					protected void done() {
						choose.getProgressBar().setVisible(false);
					}

				}
				new MyWorker().execute();
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

			if (source == friend.getBtnMakeFriendship()) {

				Model.MODEL.doMakeFriendShip(friend.getSelectedMails());

				if (calendar == null) {
					calendar = new MyCalendar();
					calendar.addListener(new CalendarListner());

				}
				frame.showPane(calendar);
			}

		}

	}// END FriendsListener

}
