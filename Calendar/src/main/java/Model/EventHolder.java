package Model;

import java.io.Serializable;
import java.sql.Time;
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
	private int when;
	private String where;
	private int remind;
	private Strategy strategy;
	private String creatorEvent;
	private Date dateWhenRing;

	public EventHolder(String title2, int when, String where, String desc,
			boolean isEmail, boolean isSms, ArrayList<User> users, Date date2,
			int remind, String creator) {

		this.title = title2;
		this.when = when;
		this.where = where;
		this.description = desc;
		this.viaEmail = isEmail;
		this.viaSMS = isSms;
		this.userList = users;
		this.members = new ArrayList<Integer>();
		this.remind = remind;
		this.creatorEvent = creator;

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
		this.normalizeTimeWhen();
		this.dateWhenRing = convertWhenItHappens();

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

	public void normalizeTimeWhen() {
		System.out
				.println("=========================================================");
		System.out.println("1: " + date);
		date.setSeconds(0);
		date.setHours(0);
		date.setMinutes(0);
		System.out.println("2: " + date);

		long d = date.getTime() + when * 30 * 60 * 1000;
		date.setTime(d);// must exally time what we choose

		System.out.println("3  after normalizeTimeWhen: " + date);
		System.out
				.println("=========================================================");
	}

	public Date convertWhenItHappens() {

		// when it should be also the same

		int sec = remind * 1 * 60 * 1000;
		// seconds plus
		long dd = date.getTime() - sec;
		Date toReturn = new Date(dd);

		System.out.println("when it occurs: " + toReturn);
		return toReturn;

	}

	public Date getDateWhenRing() {
		return dateWhenRing;
	}
}
