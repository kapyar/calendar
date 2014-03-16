package main;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.codec.digest.DigestUtils;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String user_name;
	private String user_pass;
	private String user_mail;
	private String user_phone;
	private String user_session;

	public User() {
		// looks like JPA really wants this one
	}

	public User(String name, String pass, String mail, String phone) {
		user_name = name;
		user_pass = DigestUtils.md5Hex(pass);
		user_mail = mail;
		user_phone = phone;
	}

	public String getName() {
		return user_name;
	}

	public String getPass() {
		return user_pass;
	}

	public String getMail() {
		return user_mail;
	}

	public String getPhone() {
		return user_phone;
	}

	public String getSession() {
		return user_session;
	}

	public int getId() {
		return id;
	}

	public void setSession(String session) {
		user_session = session;
	}

	@Override
	public String toString() {
		return "Name:" + user_name + "\nPass: " + user_pass + "\nId: " + id
				+ "\nMail: " + user_mail + "\nPhone: " + user_phone + "\n\n";
	}

}
