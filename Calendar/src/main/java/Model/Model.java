package Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import View.MainContainer;
import WEB.APIConfig;
import WEB.Action;
import WEB.DataBaseAPI;
import WEB.MultiJabberClient;
import WEB.User;
import Controller.Controller;

/*
 * All entity should be in this package
 * all work with DB should be implemented
 * via Model.class interface
 */

public enum Model {
	MODEL;
	private DataBaseAPI dataBase = DataBaseAPI.GET;
	private boolean isEnterLogIn = false;
	private String CURRENT_LOGIN;

	public boolean doLogIn(final String mail, final String pass) {
		try {
			dataBase.logIn(mail, pass);
		} catch (Exception e) {
			return false;
		}
		isEnterLogIn = true;
		CURRENT_LOGIN = mail;
		return true;
	}

	public boolean doRegisterNewOne(MyUser user) {
		// make a command to server

		HashMap<String, String> lc = new HashMap<String, String>();
		lc.put(APIConfig.FIELD_USERNAME, user.getUser_name());
		lc.put(APIConfig.FIELD_USERPASS, user.getUser_pass());
		lc.put(APIConfig.FIELD_USERMAIL, user.getUser_mail());
		lc.put(APIConfig.FIELD_USERPHONE, user.getUser_phone());

		try {
			dataBase.addNewUser(lc);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean doLogOut() {
		if (isEnterLogIn) {
			dataBase.logOut();// make session null
			dataBase.closeConnection();// close all connection
			isEnterLogIn = false;
		}
		return true;

	}

	public boolean isLoginIn() {
		return isEnterLogIn;
	}

	// select all user in Makefriendship
	public List<User> doGetAllUsers() {

		try {
			return dataBase.getAllUsers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public boolean doCreateEvent(EventHolder eh) {

		try {
			dataBase.createEvent(eh.getTitle(), eh.getDescription(),
					eh.getDate(), eh.getDate(), eh.getMembers());
		} catch (Exception e) {
			System.out.println("BUT WHY model 92");
			e.printStackTrace();
			return false;
		}
		if (eh.isViaEmail()) {
			HashMap<Action, Object> command = new HashMap<Action, Object>();

			command.put(Action.ACTION, Action.EVENT);
			command.put(Action.EVENT_HOLDER, eh);
			command.put(Action.LOGIN_FIELD, CURRENT_LOGIN);

			ExecutorService ex = Executors.newCachedThreadPool();
			try {
				Future<HashMap<Action, Object>> res = ex
						.submit(new MultiJabberClient(command));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Cant send it back");
				e.printStackTrace();
			}
			ex.shutdown();
		}

		return true;
	}

	// return my friends
	public List<User> doGetAllFriend() {

		try {
			return dataBase.getFriends();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	public boolean doMakeFriendShip(ArrayList<String> list) {

		List<User> l = doGetListPeolpleByEmail(list);

		// if not my friend
		List<User> myFriendsAlready = new ArrayList<>();
		try {
			myFriendsAlready = dataBase.getFriends();
		} catch (Exception e1) {
			System.out.println("Exception  Get friends check is not");
			e1.printStackTrace();
		}

		for (int i = 0; i < l.size(); ++i) {
			try {
				if (!myFriendsAlready.contains((l.get(i)))) {
					dataBase.addFriend(l.get(i).getId());
				} else {
					System.out.println(l.get(i) + " Is Already your friend ");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				continue;
			}
		}
		return true;
	}

	public List<User> doGetListPeolpleByEmail(ArrayList<String> mail) {

		List<User> l = new ArrayList<User>();
		for (int i = 0; i < mail.size(); ++i) {
			l.add(dataBase.getUserWithEmail(mail.get(i)));
		}
		return l;
	}
	// ///////////private part goes here/////////////////////////////

}
