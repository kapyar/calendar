package Model;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {

	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println("---------Start sending-------");
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		String msgBody = "KJSDhfkjhsdlfkjhasldkjfh " + "sdf' kasdkjfh aslkjdhf"
				+ "asf; kalsdfjkhas;df" + "'ksadhk;jhfldskhkas";

		try {
			Message msg = new MimeMessage(session);

			msg.setFrom(new InternetAddress("kapyar@ukr.net",
					"Example.com Admin"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
					"123kapyar@gmail.com", "Mr. User"));
			msg.setSubject("WORKS OR NOT");
			msg.setText(msgBody);
			Transport.send(msg);

		} catch (AddressException e) {
			// ...
		} catch (MessagingException e) {
			// ...
		}
		System.out.println("---------Stop sending-------");
	}
}