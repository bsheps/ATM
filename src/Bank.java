import java.util.HashMap;

public class Bank {

	private static HashMap<Integer, Account> Account;
	/**All the accounts are stored with in the bank via HashMap.*/
	 
	public Bank() {
		Account = new HashMap<Integer, Account>();
	}
	/**
	 * @param accountNumber
	 * @param pin
	 * @param balance
	 * @return null if account number is already in use, else it returns a newly made account specified by parameters above
	 */
	public Integer createAccount(int accountNumber, int pin, double balance) {
		if (Account.containsKey(accountNumber)) return null;	// Special case: account already exits. Can't have duplicate accounts
		Account.put(accountNumber, new Account(pin, balance));
		return accountNumber;
	}
	/**does the account exist?
	 * @param accountNumber
	 * @param pinEntered 
	 * @return null if the account doesn't exist
	 */
	public static boolean validate(Integer accountNumber, int pinEntered) {
		Account doesItExist = Account.get(accountNumber);
		return doesItExist != null && doesItExist.validate(pinEntered);
	}
	public static Account getAcc(int accountNum) {
		return Account.get(accountNum);
	}
}
