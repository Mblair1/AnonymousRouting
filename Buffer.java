import java.util.ArrayList;
import java.util.List;

public class Buffer {
	
	private List<String> buffer = new ArrayList<String>();
	private int length = 0;
	private static  Buffer INSTANCE = null;
	
	public Buffer() {
		
	}
	public static Buffer getInstance() {
		if(INSTANCE == null) 
			INSTANCE = new Buffer();
		return INSTANCE;
	}
	public List<String> getBuffer() {
		return buffer;
	}
	public void addMessage(String str) {
		buffer.add(str);
		length++;
	}
	public int getLength() {
		return length;
	}
}
