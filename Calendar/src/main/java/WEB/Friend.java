package WEB;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Table(name = "friends")
public class Friend {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int user_owner;
	private int user_slave;
	
	public Friend() {
		
	}
	
	public Friend(int owner, int slave) {
		this.user_owner = owner;
		this.user_slave = slave;
	}

	public int getUser_owner() {
		return user_owner;
	}

	public void setUser_owner(int user_owner) {
		this.user_owner = user_owner;
	}

	public int getUser_slave() {
		return user_slave;
	}

	public void setUser_slave(int user_slave) {
		this.user_slave = user_slave;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

}
