public class Account {

	private int pin;
	private double balance;

	public Account(int pin, double balance) {
		this.pin = pin;
		if (balance >= 0) 
			this.balance = balance;	
	}
	/** Allows the user to check their balance
	 * @return account balance*/
	public double getBalance() {
		return balance;
	}
	/**deposit money into account
	 * @param depositAmount
	 * @return whether deposit was successful (bool)*/
	public boolean deposit(double depositAmount) {
		if (depositAmount <= 0) return false; // Special case: can't deposit negative money
		balance += depositAmount;
		return true;
	}
	/**withdraw money from account
	 * @param withdrawAmount
	 * @return whether withdraw was successful (bool) */
	public boolean withdrawl(double withdrawAmount) {
		if (balance-withdrawAmount < 0 || withdrawAmount < 0) return false; // Special cases: can't withdraw more than your balance; can't withdraw negative money
		balance -= withdrawAmount;
		return true;
	}
	/**validate the pin
	 * @param enteredPin
	 * @return if pin was valid
	 */
	public boolean validate(int enteredPin) {
		return pin == enteredPin;
	}
}