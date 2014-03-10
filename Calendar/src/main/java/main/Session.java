package main;

import java.math.BigInteger;
import java.security.SecureRandom;

import static main.APIConfig.log;

public class Session {
	private static SecureRandom random = new SecureRandom();
	
	// NOTE: user_name of user EMAIL, not actual name
	private String user_name;
	private String user_session;
	
	public Session() {
	}
	
	public Session(String name) {
		user_name = name;
		user_session = nextSessionId();
		log("Session(): New session created(user: " + user_name + ", sessionId: " + user_session + ")");
	}
	

	private static String nextSessionId() {
		return new BigInteger(130, random).toString(32);
	}
	
	
	// Boring Staff goes here
	
	public void setSession(String session) {
		user_session = session;
	}
	
	public void setName(String name) {
		user_name = name;
	}
	
	public String getSessionId() {
		return user_session;
	}
	
	public String getName() {
		return user_name;
	}
}
