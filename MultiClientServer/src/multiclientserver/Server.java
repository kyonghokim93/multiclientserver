package multiclientserver;

import java.net.*;
import java.io.*;

public class Server {
	public static void main(String[] args) throws IOException {

		ServerSocket serverSocket = new ServerSocket(1254);

		System.out.println("Server on\nWaiting for clients to connect...");

		while (true) {
			Socket clientSocket = null;

			try {
				clientSocket = serverSocket.accept();

				System.out.println("New client has connected.");

				DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
				DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());

				System.out.println("New thread for this client is created.");

				Thread thread = new ClientHandler(clientSocket, dis, dos);

				thread.start();

			} catch (Exception e) {
				clientSocket.close();
				e.printStackTrace();
			}

		}

	}

}
