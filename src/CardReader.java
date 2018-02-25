import java.util.Scanner;
/**
 * 
 * @author BS
 * returns a card number for an account
 */
public class CardReader {
	Scanner scanCardNumber;
	/**
	 * @CONSTRUCTOR Sets up the cardreader with same scanner as ATM
	 * @param sameScannerAsATM
	 */
	public CardReader(Scanner sameScannerAsATM) {
	scanCardNumber = sameScannerAsATM;
	}
	/**
	 * @return the card number
	 */
	public String cardRead() {
		System.out.println("Enter your card");
		return scanCardNumber.nextLine();
		
	}
}