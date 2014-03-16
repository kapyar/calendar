package Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import main.User;

public class EventHolder {

	private String title;
	private String description;
	private Date date;
	private ArrayList<User> userList;
	private ArrayList<Integer> members;
	private boolean viaEmail = false;
	private boolean viaSMS = false;
	private String when;
	private String where;
	private String remind;

	public EventHolder(String title2, String when, String where, String desc,
			boolean isEmail, boolean isSms, ArrayList<User> users, Date date2,
			String remind) {

		this.title = title2;
		this.when = when;
		this.where = where;
		this.description = desc;
		this.viaEmail = isEmail;
		this.viaSMS = isSms;
		this.userList = users;

		// init members number

		ArrayList<Integer> usersId = new ArrayList<>();

		for (User u : users) {
			members.add(u.getId());
		}

		this.date = date2;
		this.remind = remind;
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

	@Override
	public String toString() {
		return "EventHolder [title=" + title + ", description=" + description
				+ ", date=" + date + ", members=" + members + ", viaEmail="
				+ viaEmail + ", viaSMS=" + viaSMS + ", when=" + when
				+ ", where=" + where + ", remind=" + remind + "]";
	}

	public ArrayList<User> getUserList() {
		return userList;
	}

}
