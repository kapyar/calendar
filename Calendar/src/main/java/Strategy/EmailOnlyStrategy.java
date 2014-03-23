package Strategy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
		String msgBody = null;
		String from;
		ArrayList<User> invited;
		String subject;
		
		 try {
			msgBody = readFile("resources/email.html");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		 
		StringBuffer msgStBuf = new StringBuffer(msgBody);
		
		
		
		from = eh.getCreatorEvent();
		invited = eh.getUserList();
		subject = eh.getTitle();
		
		msgStBuf.insert( msgStBuf.lastIndexOf("_fWhat")+8, eh.getTitle());
		msgStBuf.insert( msgStBuf.lastIndexOf("_fWhen")+8, eh.getDate());
		msgStBuf.insert( msgStBuf.lastIndexOf("_fDescription")+15, eh.getDescription());
		
		msgBody = ", you were invited to meeting at: " + eh.getDate() + '\n'
				+ eh.getDescription();
		
		Charset.forName("UTF-8").encode(msgBody);
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		for (int i = 0; i < invited.size(); ++i) {
			try {
				System.out.println("Start loop");

				Message msg = new MimeMessage(session);

				msg.setFrom(new InternetAddress(from, "MPK Calendar Inc"));
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
						invited.get(i).getUser_mail(), "Mr. User"));
				msg.setSubject(subject);
				
				msgStBuf.insert( msgStBuf.lastIndexOf("_fWho")+7, "Dear, "+invited.get(i).getUser_name());
				msgBody =  msgStBuf.toString();
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
	
	private static String readFile( String file ) throws IOException {
	    BufferedReader reader = new BufferedReader( new FileReader (file));
	    String         line = null;
	    StringBuilder  stringBuilder = new StringBuilder();
	    String         ls = System.getProperty("line.separator");

	    while( ( line = reader.readLine() ) != null ) {
	        stringBuilder.append( line );
	        stringBuilder.append( ls );
	    }

	    return stringBuilder.toString();
	}

}
