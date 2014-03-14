package Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class EventHolder {

	// createEvent(String title, String desc, Date time, Date delta,
	// Collection<Integer> members) throws Exception {

	private String title;
	private String description;
	private Date date;
	private ArrayList<Integer> members;
	private boolean viaEmail = false;
	private boolean viaSMS = false;
	private Date delta;

}
