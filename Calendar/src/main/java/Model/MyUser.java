package Model;

public class MyUser {

	private String user_name;
	private String user_pass;
	private String user_mail;
	private String user_phone;

	public MyUser(String name, String pass, String mail, String phone) {
		user_name = name;
		user_pass = pass;
		user_mail = mail;
		user_phone = phone;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_pass() {
		return user_pass;
	}

	public void setUser_pass(String user_pass) {
		this.user_pass = user_pass;
	}

	public String getUser_mail() {
		return user_mail;
	}

	public void setUser_mail(String user_mail) {
		this.user_mail = user_mail;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
}