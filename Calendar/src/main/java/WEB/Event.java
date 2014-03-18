package WEB;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import static WEB.APIConfig.log;

@Entity
@Table(name = "events")
public class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int 		id;
	private int 		event_admin;
	private Timestamp 	event_created;
	private Timestamp 	event_time;
	private Timestamp 	event_delta;
	private String 		event_title;
	private String 		event_description;
	
	public Event() {
		
	}
	
	public Event(int admin, String title, String desc, Date time, Date delta) {
		event_admin = admin;
		//TODO: check if this may automaticly be inited by mysql
		//event_created = System.currentTimeMillis() / 1000;
		event_time = new Timestamp(time.getTime());
		event_delta = new Timestamp(delta.getTime());
		event_title = title;
		event_description = desc;
		
		log("Event(): new event created. title: " + title);
	}
	
	public int getId() {
		return id;
	}
	
	public int getAdminId() {
		return event_admin;
	}
	
	public Date getTime() {
		Date date = new Date(event_time.getTime());
		return date;
	}
	
	public Date getDelta() {
		Date date = new Date(event_delta.getTime());
		return date;
	}
	
	public String getTitle() {
		return event_title;
	}
	
	public String getDescription() {
		return event_description;
	}

	@Override
	public String toString() {
		return "Event [event_time=" + event_time + ", event_delta="
				+ event_delta + ", event_title=" + event_title
				+ ", event_description=" + event_description + "]";
	}
	
	 
	
}
