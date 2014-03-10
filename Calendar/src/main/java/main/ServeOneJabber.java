package main;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Random;

public class ServeOneJabber extends Thread {

	private Socket socket;
	// private HashMap<Actions.Action, Object> dataFromUser;

	private ObjectInputStream osIn;
	private ObjectOutputStream osOut;
	private DataBaseAPI dataBase = DataBaseAPI.GET;

	public ServeOneJabber(Socket s) throws IOException {
		System.out.println("Constructor ServerOneJabber");
		socket = s;
		// dataBase = new SQLwrapper();

		this.start();
	}

	@Override
	public void run() {
		try {
			System.out.println("ServerOneJabber run()");

			osIn = new ObjectInputStream(socket.getInputStream());
			osOut = new ObjectOutputStream(socket.getOutputStream());

			HashMap<Action, Object> in = (HashMap<Action, Object>) osIn
					.readObject();
			HashMap<Action, Object> out = new HashMap<Action, Object>();

			switch ((Action) in.get(Action.ACTION)) {

			case LOG_IN:

				System.out.println("LOGIN PART");
				String login = (String) in.get(Action.LOGIN_FIELD);
				String pass = (String) in.get(Action.PASS_FIELD);

				try {
					dataBase.logIn(login, pass);
				} catch (Exception e) {
					out.put(Action.LOG_IN, Action.ERROR_NOT_MATCHES);
				}
				out.put(Action.LOG_IN, Action.LOG_IN);

				break;

			case REGISTER:
				
				System.out.println("REGISTER PART");
				String name = (String) in.get(Action.NAME);
				String mail = (String) in.get(Action.MAIL);
				String phone = (String) in.get(Action.PHONE);
				String passw = (String) in.get(Action.PASS);

				HashMap<String, String> lc = new HashMap<String, String>();
				lc.put(APIConfig.FIELD_USERNAME, name);
				lc.put(APIConfig.FIELD_USERPASS, passw);
				lc.put(APIConfig.FIELD_USERMAIL, mail);
				lc.put(APIConfig.FIELD_USERPHONE, phone);

				try {
					dataBase.addNewUser(lc);
					out.put(Action.REGISTER, Action.REGISTER);
				} catch (Exception e) {
					out.put(Action.REGISTER, Action.ERROR_CODE);
				}

				break;

			case LOG_OUT:
				System.out.println("LOG OUT PART");
				dataBase.logOut();
				System.out.println("_____________==========____________");
				out.put(Action.LOG_OUT, Action.LOG_OUT);
				break;

			}// END of switch

			System.out.println("All ok");

			osOut.writeObject(out);

			osOut.flush();
			socket.close();

		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			System.out.println("Finally part ServerOneJabber");
			try {
				socket.close();
			} catch (IOException e) {
				System.err.println("Cought smth ...");
			}
		}
	}
}
