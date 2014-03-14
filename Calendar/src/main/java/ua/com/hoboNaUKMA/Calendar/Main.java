package ua.com.hoboNaUKMA.Calendar;

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

	}
}
