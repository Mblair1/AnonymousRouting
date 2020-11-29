import java.io.PrintWriter;

class EchoSender extends Thread
{
	private PrintWriter mOut;
	
    public EchoSender(PrintWriter aOut)
    {
    	mOut = aOut;
    }

    /**
     * Until interrupted reads messages from the standard input (keyboard)
     * and sends them to the chat server through the socket.
     */
    public void run()
    {
    	Buffer buffer = new Buffer().getInstance();
        while (true) {
        	try {
        		mOut.println(buffer.getBuffer().get(buffer.getLength() + 1));
        		mOut.flush();
        	} catch (IndexOutOfBoundsException e) {
        	
        	}
        }
    }
}