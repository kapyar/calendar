package main;

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
	
	public int getOwner() {
		return user_owner;
	}
	
	public int getSlave() {
		return user_slave;
	}

}
