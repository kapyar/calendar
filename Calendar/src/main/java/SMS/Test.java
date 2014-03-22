package SMS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Test {

	public static void main(String[] args) throws IOException {

		String user = "kapyar";
		String pass = ".Opelastra16";

		String url = "http://smsc.ua/sys/send.php?login=kapyar&psw=.Opelastra16&phones=+380679664588&mes=meeting1";

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;

		while ((inputLine = in.readLine()) != null)
			System.out.println(inputLine);
		in.close();
		System.out.println("END");
	}

}
