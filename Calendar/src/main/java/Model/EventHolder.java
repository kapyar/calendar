package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import Strategy.EmailOnlyStrategy;
import Strategy.EmptyStrategy;
import Strategy.SMSEmailStrategy;
import Strategy.SmsOnlyStrategy;
import Strategy.Strategy;
import WEB.User;

public class EventHolder implements Serializable {

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
	private Strategy strategy;
	private String creatorEvent;

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
		this.members = new ArrayList<Integer>();

		if (isEmail && isSms) {
			strategy = new SMSEmailStrategy();
		} else if (isEmail) {
			strategy = new EmailOnlyStrategy();
		} else if (isSms) {
			strategy = new SmsOnlyStrategy();
		} else {
			strategy = new EmptyStrategy();
		}

		// init members number

		ArrayList<Integer> usersId = new ArrayList<>();

		for (User u : users) {
			members.add(u.getId());
		}

		this.date = date2;
		this.remind = remind;
	}

	public void doStrategy() {
		strategy.send(this);
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

	public String getCreatorEvent() {
		return creatorEvent;
	}

	public void setCreatorEvent(String creatorEvent) {
		this.creatorEvent = creatorEvent;
	}

	public Strategy getStrategy() {
		return strategy;
	}

}
