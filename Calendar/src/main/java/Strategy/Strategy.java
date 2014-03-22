package Strategy;

import java.io.Serializable;

import Model.EventHolder;

public abstract class Strategy implements Serializable {
	
	public abstract void send(EventHolder e);

}
