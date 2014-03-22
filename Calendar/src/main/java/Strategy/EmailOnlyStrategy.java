package Strategy;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import Model.EventHolder;
import View.Config;
import WEB.User;

public class EmailOnlyStrategy extends Strategy {

	@Override
	public void send(EventHolder eh) {
		System.out.println("Start sending msg");
		String msgBody;
		String from;
		ArrayList<User> invited;
		String subject;

		from = eh.getCreatorEvent();
		invited = eh.getUserList();
		subject = eh.getTitle();
		msgBody = ", you were invited to meeting at: " + eh.getDate() + '\n'
				+ eh.getDescription();
		Charset.forName("UTF-8").encode(msgBody);
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		for (int i = 0; i < invited.size(); ++i) {
			try {
				System.out.println("Start loop");

				Message msg = new MimeMessage(session);

				msg.setFrom(new InternetAddress(from, "Awesome Calendar Inc"));
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
						invited.get(i).getUser_mail(), "Mr. User"));
				msg.setSubject(subject);
				msgBody = invited.get(i).getUser_name() + msgBody;
				msg.setText(msgBody);
				Transport.send(msg);
				System.out.println("END loop");
			} catch (AddressException e) {
				e.printStackTrace();
			} catch (MessagingException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
