package multiclientserver;

import java.io.*;
import java.net.*;

public class ClientHandler extends Thread {

	final DataInputStream dis;
	final DataOutputStream dos;
	final Socket s;

	public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) {
		this.s = s;
		this.dis = dis;
		this.dos = dos;
	}

	@Override
	public void run() {
		int choice;

		try {
			dos.writeUTF("Hello from the server\n");
		} catch (IOException e) {
			e.printStackTrace();
		}

		while (true) {
			try {

				choice = dis.readInt();

				if (choice == 1) {
					System.out.println("A client has requested to get a message.");
					dos.writeUTF("Test message");
				} else if (choice == 2) {
					System.out.println("A client has disconnected.");
					dos.writeUTF("Bye");
					this.s.close();
					break;
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			this.dis.close();
			this.dos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}