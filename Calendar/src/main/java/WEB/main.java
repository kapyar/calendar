package WEB;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class main {
	
	 

	public static void main(String[] args) {

		DataBaseAPI db = DataBaseAPI.GET;
			
		HashMap<String, String> user = new HashMap<String, String>();
		user.put(APIConfig.FIELD_USERNAME, 	"Serhio");
		user.put(APIConfig.FIELD_USERPASS, 	"12345");
		user.put(APIConfig.FIELD_USERMAIL, 	"dfgdfg");
		user.put(APIConfig.FIELD_USERPHONE, "54545479");
		
		
		
		try  {
			//db.addNewUser(user);
			
			db.logIn("admin@admin.com", "12345");
			
			List<User> fr = db.getFriends();
			
			for (User u: fr) {
				System.out.println(u);
			}
			
			db.addFriend(8);
			db.addFriend(8);
			//db.addFriend(11 );
			
//			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//			Date time = dateFormat.parse("23/09/2007");
//			
//			DateFormat deltaFormat = new SimpleDateFormat("HH:mm");
//			Date delta = deltaFormat.parse("03:00");
//			
//			String title = "Event 1";
//			String desc = "Another awesome event";
//
//			List<Integer> ms = new ArrayList<Integer>();
//			ms.add(4);
//			ms.add(5);
//			ms.add(11);
//			
//			db.createEvent(title, desc, time, delta, ms);
			
			
			

//			List<Event> events = db.getEvents();
//			
//			for (Event e: events) {
//				System.out.println(e);
//			}
			
			
			// get event members
			List<User> mems = db.getEventMembers(5);
			
			for (User u: mems) {
				System.out.println(u);
			}
			
			
			db.logOut();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}

		
		
		db.closeConnection();


	}
}
