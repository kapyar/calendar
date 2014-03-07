package DataBase;


import java.util.HashMap;


public class main {
	
	 

	public static void main(String[] args) {

		DataBaseAPI db = DataBaseAPI.GET;
			
		HashMap<String, String> user = new HashMap<String, String>();
		user.put(APIConfig.FIELD_USERNAME, 	"");
		user.put(APIConfig.FIELD_USERPASS, 	"12345");
		user.put(APIConfig.FIELD_USERMAIL, 	"");
		user.put(APIConfig.FIELD_USERPHONE, "54545479");
		
		
		
		try  {
			db.addNewUser(user);
			
			db.logIn("admin@admin.com", "12345");
			System.out.println("All ok");
			db.logOut();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}

		
		
		db.closeConnection();


	}
}
