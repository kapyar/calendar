package Strategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import Model.EventHolder;
import WEB.User;

public class SmsOnlyStrategy extends Strategy {

	@Override
	public void send(EventHolder e) {

		System.out.println("Start sending msg");
		String msgBody;
		ArrayList<User> invited = e.getUserList();

		String user = "kapyar";
		String pass = ".Opelastra16";
		String mes = "You have meeting at: " + e.getDate();

		for (int i = 0; i < invited.size(); ++i) {

			URL obj;

			try {
				obj = new URL(urlFor(invited.get(i).getUser_phone(), mes));
				HttpURLConnection con;
				con = (HttpURLConnection) obj.openConnection();
				con.setRequestMethod("GET");
				BufferedReader in = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
				String inputLine;

				while ((inputLine = in.readLine()) != null)
					System.out.println(inputLine);

				in.close();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();

			}
			System.out.println("END");
		}

	}

	private String urlFor(String phone, String m) {

		String url = "http://smsc.ua/sys/send.php?login=kapyar&psw=.Opelastra16&phones="
				+ phone + "&mes=" + m;

		return url;
	}
}
