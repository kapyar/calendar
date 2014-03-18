package WEB;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Table(name = "event_members")
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int event_id;
	private int member_id;
	
	public Member() {
		
	}
	
	public Member(int eventId, int memberId) {
		this.event_id 	= eventId;
		this.member_id 	= memberId;
	}
	
	public int getEventId() {
		return event_id;
	}
	
	public int getMemberId() {
		return member_id;
	}
	
}
