import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

class Sender extends Thread
{
    private PrintWriter mOut;
    private boolean isCommander;
    
    public Sender(PrintWriter aOut, boolean isCommander)
    {
        mOut = aOut;
        this.isCommander = isCommander;
    }

    /**
     * Until interrupted reads messages from the standard input (keyboard)
     * and sends them to the chat server through the socket.
     */
    public void run()
    {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            CommanderCRC CRC = CommanderCRC.getInstance();
            String message;
            String outputMessage;
            while (!isInterrupted()) {
            	if(isCommander) {
            		message = in.readLine();
            		outputMessage = CRC.CRC + " " + message;
            		mOut.println(outputMessage);
            		mOut.flush();
            		Thread.sleep(1000);
            		CRC.setCRC();
            	}
            	else {
            		message = in.readLine();
            		outputMessage = String.valueOf((int) (Math.random() * 1000000) + 1) + " " + message;
            		mOut.println(outputMessage);
            		mOut.flush();
            	}
            }
        } catch (IOException | InterruptedException e) {
            // Communication is broken
        }
    }
}