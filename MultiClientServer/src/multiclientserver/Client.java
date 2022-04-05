package multiclientserver;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Client {
	public static void main(String[] args) throws IOException {

		String serverHostname = new String("127.0.0.1");

		if (args.length > 0)
			serverHostname = args[0];
		System.out.println("Attemping to connect to host " + serverHostname + " on port 1254.");

		Socket echoSocket = null;
		DataInputStream dis = null;
		DataOutputStream dos = null;

		try {
			echoSocket = new Socket(serverHostname, 1254);
			dis = new DataInputStream(echoSocket.getInputStream());
			dos = new DataOutputStream(echoSocket.getOutputStream());
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: " + serverHostname);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for " + "the connection to: " + serverHostname);
			System.exit(1);
		}

		String message = dis.readUTF();
		System.out.println(message);

		Scanner input = new Scanner(System.in);
		boolean quit = false;
		do {
			System.out.println("press 1 to get a message, press 2 to quit.");

			int choice = input.nextInt();
			dos.writeInt(choice);

			System.out.println(dis.readUTF());

			if (choice == 2) {
				quit = true;
			}

		} while (!quit);

		dis.close();
		dos.close();
		echoSocket.close();
	}
}
