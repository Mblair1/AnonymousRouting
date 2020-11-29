
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Echo
{
    public static final String SERVER_HOSTNAME = "localhost";
    public static final int SERVER_PORT = 5353;

    public static void main(String[] args) {
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			// Connect to Nakov Chat Server
			Socket socket = new Socket(SERVER_HOSTNAME, SERVER_PORT);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			System.out.println("Connected to server " + SERVER_HOSTNAME + ":" + SERVER_PORT);
		} catch (IOException ioe) {
			System.err.println("Can not establish connection to " + SERVER_HOSTNAME + ":" + SERVER_PORT);
			ioe.printStackTrace();
			System.exit(-1);
		}

		// Create and start Sender thread
		EchoSender sender = new EchoSender(out);
		sender.setDaemon(true);
		sender.start();

		try {
			// Read messages from the server and print them
			String message;
			Buffer buffer = Buffer.getInstance();
			CommanderCRC CRC = CommanderCRC.getInstance();
			while ((message = in.readLine()) != null) {
				if(message.contains(String.valueOf(CRC.getCRC()))) 
					System.out.println("Commander : " + message);
				else
					System.out.println(message);
				buffer.addMessage(message);
			}
		} catch (IOException ioe) {
			System.err.println("Connection to server broken.");
			ioe.printStackTrace();
		}
	}
}