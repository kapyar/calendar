package ua.com.hoboNaUKMA.Calendar;

import java.util.HashMap;

import Controller.Controller;
import View.*;
import WEB.DataBaseAPI;

public class Main {

	public static void main(String[] args) {

		MyCalendar calendarPane = new MyCalendar();
		MainContainer main = new MainContainer();
		Controller controller = new Controller(main);

	}
}
