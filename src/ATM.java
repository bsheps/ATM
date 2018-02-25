import java.io.IOException;
import java.util.Scanner;

public class ATM {
	private Scanner input;
	private CardReader cardReader;
	private cashDispenser dispenser;
	private ATMprinter p;

	private int accountNum, pinEntered;
	private String withdraw, entry;
	/**
	 * default constructor for JUNIT testing
	 */
	public ATM() {/*LEAVE EMPTY*/}
	
/**
 * @constructor: This method initializes the input scanner and string entries
 * @param thisScanner
 * @throws IOException
 */
	public ATM(Scanner thisScanner) throws IOException {
		input = thisScanner;
		entry = withdraw = "";

	}

/**
 * Initializes the accounts in the bank per class instructions
 */
	public void initialize() {
		Bank b1 = new Bank();		
		b1.createAccount(1234, 6789, 80);
		b1.createAccount(6789, 4321, 60);
	}
	/** 
	 * @throws IOException 
	 * @initializes cardReader, ATMprinter, cashDispenser
	 * @MAIN_WHILE_LOOP
	 * main loop for ATM -->
	 * all validation for account/ pin, withdraw, deposit, check balance functions happen with this loop
	 */
	public void start() throws IOException {
		p = new ATMprinter();
		cardReader = new CardReader(input);
		dispenser = new cashDispenser();
		while (!entry.equals("-1") && accountNum != -1 && pinEntered !=-1) {	// 
			try {	
				System.out.println("Welcome to the ATM.");
				accountNum =Integer.parseInt(cardReader.cardRead());
				p.printThis("CARDREAD "+ Integer.toString(accountNum), true);
				if(accountNum == -1)break;
				for(int pinAttempt =1; pinAttempt < 4; ++ pinAttempt) {
					p.printThis("ENTER PIN", false);
					pinEntered =  Integer.parseInt(input.nextLine());
					p.printThis("NUM "+ Integer.toString(pinEntered), true);
					if (Bank.validate(accountNum, pinEntered)) {
						Account customer = Bank.getAcc(accountNum);
						entry = "";
						while (!entry.equals("CANCEL")) {
							p.printThis(("Choose transaction W - withdraw, CB - check balance, CANCEL - return card"), false);
							entry = input.nextLine();
							p.printThis("BUTTON " + entry, true);
							switch(entry) {
							case	"CANCEL" :
								p.printThis("ENJECT CARD", false);
								break;

							case 	"CB" :
								p.printThis("CB "+Double.toString(customer.getBalance()), false);
								break;
							case	"W"	:
								p.printThis("Amount?", false);
								withdraw = input.nextLine();
								p.printThis("W " +withdraw, true);
								if(customer.withdrawl(Double.parseDouble(withdraw)))
									if(!dispenser.dispense(Integer.parseInt(withdraw))){	// if atm is out of money, redeposit the amount back to the account
										customer.deposit(Double.parseDouble(withdraw));	
										p.printThis("ATM out of cash", true);
									}
									else 
										p.printThis("NEW BALANCE: "+ Double.toString(customer.getBalance()), false);
								else {
									p.printThis("DECLINED", false);
								}
								break;
							default:
								break;
							}

						}
					break;
					}
					
				}
			}
			catch(NumberFormatException e) { 
				System.out.println("ERROR! INVALID:" +e.getMessage());	
			}
		}
		p.printThis("ATM SHUTDOWN", true);
		p.shutDownPrinter();
	}
}