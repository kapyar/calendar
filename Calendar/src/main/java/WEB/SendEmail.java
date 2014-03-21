//package WEB;
//
//import java.io.UnsupportedEncodingException;
//import java.util.ArrayList;
//import java.util.Properties;
//
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.AddressException;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//
//import WEB.User;
//import Model.EventHolder;
//import Strategy.Strategy;
//import View.Config;
//
///*
// * 
// * need to add html
// */
//public class SendEmail implements Strategy{
//
//	private String msgBody;
//	private String from;
//	private ArrayList<User> invited;
//	private String subject;
//
//	public SendEmail(EventHolder eh, String loginFrom) {
//		this.from = loginFrom;
//		this.invited = eh.getUserList();
//		this.subject = eh.getTitle();
//		this.msgBody = ", you were invited to meeting at: " + eh.getWhen()
//				+ " on " + eh.getDate().getDay() + " ,"
//				+ Config.MONTHS[eh.getDate().getMonth()] + " in "
//				+ eh.getDate().getYear() + "\n" + eh.getDescription();
//
//	}
//
//	public void send()  {
//
//		Properties props = new Properties();
//		Session session = Session.getDefaultInstance(props, null);
//
//		for (int i = 0; i < invited.size(); ++i) {
//			try {
//				Message msg = new MimeMessage(session);
//
//				msg.setFrom(new InternetAddress(from, "Awesome Calendar Inc"));
//				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
//						invited.get(i).getUser_mail(), "Mr. User"));
//				msg.setSubject(subject);
//				msgBody = invited.get(i).getUser_name() + msgBody;
//				msg.setText(msgBody);
//				Transport.send(msg);
//
//			} catch (AddressException e) {
//				// ...
//			} catch (MessagingException e) {
//				// ...
//			} catch (UnsupportedEncodingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//
//	}
//
//	@Override
//	public void send(EventHolder e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//}