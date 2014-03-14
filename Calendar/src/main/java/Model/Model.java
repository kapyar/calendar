package Model;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import main.APIConfig;
import main.Action;
import main.DataBaseAPI;
import main.MultiJabberClient;
import main.User;

/*
 * All entity should be in this package
 * all work with DB should be implemented
 * via Model.class interface
 */

public enum Model {
	MODEL;
	private DataBaseAPI dataBase = DataBaseAPI.GET;

	public String CURRENT_LOGIN;

	public boolean doLogIn(final String mail, final String pass) {

		try {
			dataBase.logIn(mail, pass);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean doRegisterNewOne(User user) {
		// make a command to server

		HashMap<String, String> lc = new HashMap<String, String>();
		lc.put(APIConfig.FIELD_USERNAME, user.getName());
		lc.put(APIConfig.FIELD_USERPASS, user.getPass());
		lc.put(APIConfig.FIELD_USERMAIL, user.getMail());
		lc.put(APIConfig.FIELD_USERPHONE, user.getPhone());

		try {
			dataBase.addNewUser(lc);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean doLogOut() {
		dataBase.logOut();
		return true;

	}

	public List<User> doAddAllFriend() {

		try {
			return dataBase.getAllUsers();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public boolean doCreateEvent(Date date) {
		// (String title, String desc, Date time, Date delta,
		// Collection<Integer> members)
		return false;
	}

	public List<User> doGetAllFriend(){

		try {
			return dataBase.getFriends();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	// ///////////private part goes here/////////////////////////////

}
