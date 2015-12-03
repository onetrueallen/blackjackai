package networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class BlackJackServer {
	private ServerSocket server;
	private Socket client;
	PrintWriter output;

	public static void main(String[] args) throws IOException {
		new BlackJackServer().go();
	}

	private void go() throws IOException {
		try {
			server = new ServerSocket(5000);

			client = server.accept();
			System.out.println("connected");
		} catch (IOException e) {
			System.out.println("Connection error.");
			e.printStackTrace();
		}
		try {
			output = new PrintWriter(client.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			String message = "";
			message = br.readLine();
			if (message.length() > 0) {
				output.println(message);
				output.flush();
			}
		}
	}
}
