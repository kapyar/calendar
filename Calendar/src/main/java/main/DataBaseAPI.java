package main;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.commons.codec.digest.DigestUtils;

import static main.APIConfig.log;

public enum DataBaseAPI {
	GET;

	private static final String PERSISTENCE_UNIT_NAME = "calendar";
	private EntityManagerFactory factory;
	private EntityManager em;
	private Session session;
	private User user;

	DataBaseAPI() {
		log("DataBaseAPI(): constructor started");
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		log("DataBaseAPI(): constructor finished");
	}

	public void addNewUser(HashMap<String, String> userData) throws Exception {

		String name = userData.get(APIConfig.FIELD_USERNAME);
		String pass = userData.get(APIConfig.FIELD_USERPASS);
		String mail = userData.get(APIConfig.FIELD_USERMAIL);
		String phone = userData.get(APIConfig.FIELD_USERPHONE);

		if (this.userExists(mail)) {
			log("addNewUser(): user already exists");
			throw new Exception(APIConfig.ERROR_USER_EXISTS);
		}

		em.getTransaction().begin();
		User newGuy = new User(name, pass, mail, phone);
		em.persist(newGuy);
		em.getTransaction().commit();

		log("addNewUser(): user {" + name + ", " + mail + ", " + phone + ", "
				+ pass + "} has been added");
	}

	public void logIn(String mail, String pass) throws Exception {
		// if there is no combination of mail/pass fire an error
		List<User> users = this.getUserWithData(mail, pass);
		
		if (users.size() == 0) {
			throw new Exception(APIConfig.ERROR_WRONG_LOGIN_DATA);
		}

		// get the only one user
		// TODO: check if the only one?

		user = users.get(0);
		session = new Session(user.getMail());

		em.getTransaction().begin();
		user.setSession(session.getSessionId());
		em.getTransaction().commit();
		log("logIn(): User with id " + user.getId() + " has logged in");
	}

	public boolean isLoggedIn() {
		if (session == null || this.session.getSessionId() == null) {
			System.out.println(session);
			System.out.println(this.session.getSessionId());
			return false;
		}
		// compare sessions on server and local
		User serverUser = getUserWithEmail(session.getName());
		return serverUser.getSession().equals(session.getSessionId());
	}

	public User getUserWithEmail(String mail) {
		Query q = em
				.createQuery("SELECT a FROM User a WHERE a.user_mail=:arg_mail");
		q.setParameter("arg_mail", mail);
		List<User> users = q.getResultList();
		return users.get(0);
	}

	public void logOut() {
		em.getTransaction().begin();
		session.setSession(null);
		user.setSession(null);
		em.getTransaction().commit();
		log("logOut(): user " + user.getId() + " has logged out");
	}

	// this one is used in addNewUser
	public boolean userExists(String mail) {
		Query q = em
				.createQuery("SELECT a FROM User a WHERE a.user_mail=:arg_mail");
		q.setParameter("arg_mail", mail);
		List<User> users = q.getResultList();
		return users.size() > 0;
	}

	// this one is used in LogIn
	private List<User> getUserWithData(String mail, String pass) {
		pass = hash(pass);

		Query q = em
				.createQuery("SELECT a FROM User a WHERE a.user_mail = :arg_mail AND a.user_pass = :arg_pass");

		q.setParameter("arg_mail", mail);
		q.setParameter("arg_pass", pass);

		List<User> users = q.getResultList();
		return users;
	}

	private String hash(String pass) {
		return DigestUtils.md5Hex(pass);
	}

	private boolean userHasFriend(int friendId) {
		Query q = em
				.createQuery("SELECT u FROM Friend u WHERE u.user_owner = :owner");
		q.setParameter("owner", friendId);
		List<Friend> f = q.getResultList();

		return f.size() > 0;
	}

	public void addFriend(int friendId) throws Exception {
		if (!isLoggedIn()) {
			log("addFriend(): exception: not logged in");
			throw new Exception(APIConfig.ERROR_NOT_LOGGED_IN);
		}

		if (userHasFriend(friendId)) {
			log("addFriend(): exception: user aleady has this friend");
			throw new Exception(APIConfig.ERROR_FRIEND_CLONE);
		}

		Friend oneWay = new Friend(user.getId(), friendId);
		Friend otherWay = new Friend(friendId, user.getId());

		em.getTransaction().begin();
		em.persist(oneWay);
		em.persist(otherWay);
		em.getTransaction().commit();

		log("addFriend(): friend with id " + friendId
				+ " added ( current userId: " + user.getId());
	}

	public List<User> getFriends() throws Exception {
		if (!isLoggedIn()) {
			log("getFriends(): exception: not logged in");
			throw new Exception(APIConfig.ERROR_NOT_LOGGED_IN);
		}

		Query q = em
				.createQuery("SELECT u FROM User u JOIN Friend f ON u.id = f.user_slave AND f.user_owner = :owner");
		q.setParameter("owner", user.getId());
		List<User> friends = q.getResultList();

		log("getFriends(): list of friends retreived");
		return friends;
	}

	public void createEvent(String title, String desc, Date time, Date delta,
			Collection<Integer> members) throws Exception {
		if (!isLoggedIn()) {
			log("createEvent(): exception: not logged in");
			throw new Exception(APIConfig.ERROR_NOT_LOGGED_IN);
		}

		Event e = new Event(user.getId(), title, desc, time, delta);

		em.getTransaction().begin();
		em.persist(e);
		em.flush();
		int eventId = e.getId();
		em.getTransaction().commit();

		addMembers(members, eventId);

		log("createEvent(): event created");
	}

	public List<Event> getEvents() throws Exception {
		if (!isLoggedIn()) {
			log("getEvents(): exception: not logged in");
			throw new Exception(APIConfig.ERROR_NOT_LOGGED_IN);
		}

		Query q = em
				.createQuery("SELECT e FROM Event e WHERE e.event_admin = :admin_id");
		q.setParameter("admin_id", user.getId());
		List<Event> events = q.getResultList();

		log("getEvents(): list of events retreived");
		return events;
	}

	private void addMembers(Collection<Integer> members, int eventId) {
		log("addMembers(): starting adding event members");

		em.getTransaction().begin();

		for (Integer member : members) {
			Member m = new Member(eventId, member);
			em.persist(m);
		}

		em.getTransaction().commit();

		log("addMembers(): members added");
	}

	public List<User> getEventMembers(int eventId) throws Exception {
		if (!isLoggedIn()) {
			log("getEventMembers(): exception: not logged in");
			throw new Exception(APIConfig.ERROR_NOT_LOGGED_IN);
		}

		Query q = em
				.createQuery("SELECT u FROM User u WHERE u.id IN (SELECT u2.member_id FROM Member u2 WHERE u2.event_id = :event)");
		q.setParameter("event", eventId);
		List<User> members = q.getResultList();

		log("getEventMembers(): list of event members retreived");
		return members;
	}

	public List<User> getAllUsers() throws Exception {
		if (!isLoggedIn()) {
			log("getAllUsers(): exception: not logged in");
			throw new Exception(APIConfig.ERROR_NOT_LOGGED_IN);
		}

		Query q = em.createQuery("SELECT u FROM User u");
		List<User> allUsers = q.getResultList();
		return allUsers;
	}

	// TODO: call this in shutDown hook in main app
	public void closeConnection() {
		log("closeConnection(): starting closing connection");
		em.close();
		log("closeConnection(): connection closed");
	}

}
