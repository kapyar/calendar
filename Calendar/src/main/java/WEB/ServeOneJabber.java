package WEB;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import WEB.User;
import Model.EventHolder;

public class ServeOneJabber extends Thread {

	private Socket socket;
	// private HashMap<Actions.Action, Object> dataFromUser;

	private ObjectInputStream osIn;
	private ObjectOutputStream osOut;
	private List<User> us;

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

			case EVENT:
				System.out.println("Event Part");
				EventHolder holder = (EventHolder) in.get(Action.EVENT_HOLDER);
				holder.doStrategy();
				out.put(Action.ACTION.EVENT, Action.SEND);
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
