package Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class EventHolder {

	private String title;
	private String description;
	private Date date;
	private ArrayList<Integer> members;
	private boolean viaEmail = false;
	private boolean viaSMS = false;
	private String when;
	private String where;
	private String remind;

	public EventHolder(String title2, String when, String where, String desc,
			boolean isEmail, boolean isSms, ArrayList<Integer> usersId,
			Date date2, String remind) {

		this.title	 	= title2;
		this.when 		= when;
		this.where 		= where;
		this.description = desc;
		this.viaEmail	 = isEmail;
		this.viaSMS 	= isSms;
		this.members 	= usersId;
		this.date 		= date2;
		this.remind 	= remind;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Date getDate() {
		return date;
	}

	public ArrayList<Integer> getMembers() {
		return members;
	}

	public boolean isViaEmail() {
		return viaEmail;
	}

	public boolean isViaSMS() {
		return viaSMS;
	}

	public String getWhere() {
		return where;
	}

	public String getWhen() {
		return when;
	}

}
