package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	static final int PORT = 8079;

	public static void main(String[] args) throws IOException {

		ServerSocket s = new ServerSocket(PORT);
		System.out.println("Waiting for clients...");

		try {
			while (true) {
				Socket socket = s.accept();
				try {
					new ServeOneJabber(socket);
				} catch (IOException e) {
				
					socket.close();
				}
			}
		} finally {
			s.close();
		}
	}

}
	
