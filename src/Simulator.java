import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
/**
 * @author BS
 * Simulates ATM by either cmd or a text file
 */
public class Simulator {
	static String processedFile="";
	int testMethod;
	final String TRIGGER_SHUTDOWN= "-1",
			ENDOFLINEMARKER = "\n",
			SEPARATION = "************************************************\n";
	/**
	 * SIMULATOR DRIVER
	 * @throws IOException
	 * @idea Does 2 things: 1.) Let the user decide how to run the simulator. 2.) Starts ATM and does the simulation
	 */
	public void runSimulator() throws IOException{
		@SuppressWarnings("resource")
		Scanner userPrompt = new Scanner(System.in);
		while(testMethod != 3) {
			System.out.println(SEPARATION +"ATM SIMULATOR. ENTER 1 TO CONSOLE TEST, 2 TO TEST BY FILE, 3 TO EXIT:");
			testMethod = userPrompt.nextInt();
			// test by console
			if(testMethod ==1) {
				ATM a = new ATM(new Scanner(System.in));
				a.initialize();
				a.start();
			}
			// test by file
			else if (testMethod == 2){
				System.out.println("FILE TO PROCESS:");
				String filename = userPrompt.next();
				processedFile = processFile(filename);
				ATM a = new ATM(new Scanner(processedFile));
				a.initialize();
				a.start();
			}
		}
	}
	/**
	 * @param fileName
	 * @return a string in an ATM-readable-format
	 */
	public String processFile(String fileName) {
		String output = "";
		try (Scanner file2scan = new Scanner(new File(fileName))){
			while(file2scan.hasNextLine()) {
				output += fileParser(file2scan.nextLine().trim().split(" ")) + ENDOFLINEMARKER;
			}	
		}
		catch(FileNotFoundException e) {
			System.out.println("File not found");
		}
		return output + TRIGGER_SHUTDOWN;
	}
	/**
	 * @param input (string array)
	 * @return what entry should be typed into the ATM
	 */
	public String fileParser(String[] input) {
		switch(input[0]) {
		case "CARDREAD":
			return input[1];
		case "NUM":
			return input[1];
		case "DIS" :
			System.out.println("Console Display: " + input[1]);
			break;
		case "BUTTON" : 
			switch(input[1]){
			case "W" :
				return input[1];
			case "CB" :
				return input[1];
			case "CANCEL" :
				return input[1];
			default :
				System.out.print("error reading Button");
				break;
			}
		default :
			System.out.println("error reading input");
			break;
		}
		return "";
	}
	
	public static void main(String[] args) throws IOException {
		Simulator s = new Simulator();
		s.runSimulator();
	}
}