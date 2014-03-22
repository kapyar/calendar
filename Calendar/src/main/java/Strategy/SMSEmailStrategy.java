package Strategy;

import java.io.Serializable;

import Model.EventHolder;

public class SMSEmailStrategy extends Strategy {

	@Override
	public void send(EventHolder e) {
		EmailOnlyStrategy em = new EmailOnlyStrategy();
		SmsOnlyStrategy sms = new SmsOnlyStrategy();
		
		em.send(e);
		sms.send(e);

	}

}
