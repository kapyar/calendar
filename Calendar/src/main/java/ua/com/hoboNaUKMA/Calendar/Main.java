package ua.com.hoboNaUKMA.Calendar;

import java.util.Calendar;
import java.util.HashMap;

import main.APIConfig;
import main.DataBaseAPI;
import Controller.Controller;
import View.*;

public class Main {

	public static void main(String[] args) {

		MyCalendar calendarPane = new MyCalendar();
		MainContainer main = new MainContainer();
		Controller controller = new Controller(main);

		DataBaseAPI db = DataBaseAPI.GET;

		HashMap<String, String> user = new HashMap<String, String>();
		user.put(APIConfig.FIELD_USERNAME, "test");
		user.put(APIConfig.FIELD_USERPASS, "12345");
		user.put(APIConfig.FIELD_USERMAIL, "ddd@gmail.com");
		user.put(APIConfig.FIELD_USERPHONE, "54545479");

		System.out.println("------------------------------");
		try {
			db.addNewUser(user);

			db.logIn("ddd@gmail.com", "12345");
			System.out.println("All ok");
			db.logOut();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		db.closeConnection();
		System.out.println("------------------------------");
	}
}
