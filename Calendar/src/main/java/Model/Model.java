package Model;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import main.APIConfig;
import main.Action;
import main.MultiJabberClient;

/*
 * All entity should be in this package
 * all work with DB should be implemented
 * via Model.class interface
 */

public enum Model {
	MODEL;
	

	public String CURRENT_LOGIN;

	public boolean doLogIn(final String login, final String pass) {

		// make a command to server
		HashMap<Action, Object> command = new HashMap<Action, Object>();
		command.put(Action.ACTION, Action.LOG_IN);

		CURRENT_LOGIN = login;
		command.put(Action.LOGIN_FIELD, login);
		command.put(Action.PASS_FIELD, pass);

		ExecutorService ex = Executors.newCachedThreadPool();
		try {
			Future<HashMap<Action, Object>> res = ex
					.submit(new MultiJabberClient(command));

			System.out.println(" GET: " + res.get());

			if (checkLogin(res.get())) {
				return true;
			} else {
				return false;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ex.shutdown();
		return true;
	}

	public boolean doRegisterNewOne(UserDataHolder user) {
		// make a command to server
		HashMap<Action, Object> command = new HashMap<Action, Object>();
		command.put(Action.ACTION, Action.REGISTER);

		command.put(Action.NAME, user.getUser_name());
		command.put(Action.MAIL, user.getUser_mail());
		command.put(Action.PHONE, user.getUser_phone());
		command.put(Action.PASS, user.getUser_pass());
		
		System.out.println("lkajhlkfjashdkl"+user.getUser_pass());

		ExecutorService ex = Executors.newCachedThreadPool();
		try {
			Future<HashMap<Action, Object>> res = ex
					.submit(new MultiJabberClient(command));

			System.out.println(" GET: " + res.get());

			if (checkRegister(res.get())) {
				return true;
			} else {
				return false;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ex.shutdown();
		return true;
	}

	public boolean doLogOut() {
		HashMap<Action, Object> command = new HashMap<Action, Object>();
		command.put(Action.ACTION, Action.LOG_OUT);

		ExecutorService ex = Executors.newCachedThreadPool();
		try {
			Future<HashMap<Action, Object>> res = ex
					.submit(new MultiJabberClient(command));

			System.out.println(" GET: " + res.get());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ex.shutdown();
		return true;

	}

	// ///////////private part goes here/////////////////////////////

	private boolean checkRegister(HashMap<Action, Object> hashMap) {
		Action t = (Action) hashMap.get(Action.REGISTER);

		if (t == Action.REGISTER)
			return true;
		return false;
	}

	//
	private boolean checkLogin(HashMap<Action, Object> hashMap) {
		Action t = (Action) hashMap.get(Action.LOG_IN);

		if (t == Action.LOG_IN)
			return true;
		return false;
	}

}
