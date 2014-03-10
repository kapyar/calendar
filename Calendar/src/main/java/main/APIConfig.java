package main;

public class APIConfig {
	public static final boolean DEBUG = true;
	
	public static void log(String message) {
		if (APIConfig.DEBUG) {
			System.out.println("Debug: " + message);
		}
	}
	
	public static final String FIELD_USERNAME 	= "user_name";
	public static final String FIELD_USERPASS 	= "user_pass";
	public static final String FIELD_USERMAIL 	= "user_mail";
	public static final String FIELD_USERPHONE 	= "user_phone";
	
	public static final String ERROR_USER_EXISTS 		= "User with this email aleady exists";
	public static final String ERROR_WRONG_LOGIN_DATA 	= "There is no user with this email and password";
	public static final String ERROR_NOT_LOGGED_IN 		= "You are not logged in";
	public static final String ERROR_FRIEND_CLONE 		= "This user is already your friend";
}
