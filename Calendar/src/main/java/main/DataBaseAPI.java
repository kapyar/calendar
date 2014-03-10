package main;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.commons.codec.digest.DigestUtils;

public enum DataBaseAPI {
	GET;

	private static final String PERSISTENCE_UNIT_NAME = "calendar";
	private EntityManagerFactory factory;
	private EntityManager em;
	private Session session;
	private User user;

	DataBaseAPI() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();

	}

	// TODO: trash. remove this one later
	public void getAllUsers() {
		Query q = em.createQuery("SELECT a FROM User a");
		List<User> todoList = q.getResultList();
		for (User todo : todoList) {
			System.out.println(todo);
		}
		System.out.println("Size: " + todoList.size());
	}

	public void addNewUser(HashMap<String, String> userData) throws Exception {

		String name = userData.get(APIConfig.FIELD_USERNAME);
		String pass = userData.get(APIConfig.FIELD_USERPASS);
		String mail = userData.get(APIConfig.FIELD_USERMAIL);
		String phone = userData.get(APIConfig.FIELD_USERPHONE);

		if (this.userExists(mail)) {
			throw new Exception(APIConfig.ERROR_USER_EXISTS);
		}

		em.getTransaction().begin();
		User newGuy = new User(name, pass, mail, phone);
		em.persist(newGuy);
		em.getTransaction().commit();
	}

	public void logIn(String mail, String pass) throws Exception {
		// if there is no combination of mail/pass fire an error
		List<User> users = this.getUserWithData(mail, pass);
		if (users.size() == 0) {
			throw new Exception(APIConfig.ERROR_WRONG_LOGIN_DATA);
		}

		// get the only one user
		// TODO: check if the only one?
		System.out.println("DATABASEAPI: " + users.get(0));
		user = users.get(0);
		session = new Session(user.getMail());

		em.getTransaction().begin();
		user.setSession(session.getSessionId());
		em.getTransaction().commit();

	}

	public boolean isLoggedIn() {
		return this.session.getSessionId() != null;
	}

	public void logOut() {
		em.getTransaction().begin();
		session.setSession(null);
		user.setSession(null);
		em.getTransaction().commit();
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

	// TODO: call this in shutDown hook in main app
	public void closeConnection() {
		em.close();
	}

}
