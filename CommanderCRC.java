import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CommanderCRC {
	public static int CRC = 5353;
	public static CommanderCRC INSTANCE = null;
	
	private CommanderCRC() {
		try {
		      File myObj = new File("C:\\Users\\mblai\\Desktop\\CRC.txt");
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		      }
		      
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	public static CommanderCRC getInstance() {
		if(INSTANCE == null)
			INSTANCE = new CommanderCRC();
		return INSTANCE;
	}
	public void setCRC() {
		CRC = (int) (Math.random() * 1000000) + 1;
		try {
		      FileWriter myWriter = new FileWriter("C:\\Users\\mblai\\Desktop\\CRC.txt",false);
		      myWriter.write(String.valueOf(CRC));
		      myWriter.close();
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	public int getCRC() {
		try {
		      File myObj = new File("C:\\Users\\mblai\\Desktop\\CRC.txt");
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        CRC = Integer.parseInt(data);
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		return CRC;
	}
}
