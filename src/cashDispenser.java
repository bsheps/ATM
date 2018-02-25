/**@author BS
 *	Basic cash dispenser that originally starts with $500. It only works with whole dollars.
 */
public class cashDispenser {
	private int cashOnHand;
	/**no argument constructor-->
	 * $500 starting cash in the dispenser
	 */
	public cashDispenser() {
		cashOnHand = 500;	
	}
	/**
	 * @param amount
	 * @return whether the dispenser had enough money: boolean
	 */
	public boolean dispense(int amount) {
		if(cashOnHand >= amount) {
			cashOnHand -= amount;
			return true;
		}
		else return false;
	}
}
