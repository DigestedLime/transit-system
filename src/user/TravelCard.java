package user;

import routenetwork.Journey;

/**
 * 
 * A travel card that a customer can have. It has a unique ID, a balance, and the ability to be 
 * suspended. It also keeps track which journey it is currently on
 *
 */

public class TravelCard {
	private float balance;
	private boolean cannotPay;
	private boolean suspended;
	private int id;
	private Journey currentJourney;
	
	/* A static variable makes sure the ID is always unique by incrementing it whenever a new id
	 * is made
	 */
	public static int UNIQUE_ID = 0;
	
	/**
	 * Creates a new travel card
	 */
	public TravelCard() {
		this.id = UNIQUE_ID;
		// Increment UNIQUE_ID to ensure the current id is not used again
		UNIQUE_ID++;
		
		this.setCurrentJourney(null);
		this.balance = 19;
		this.cannotPay = false;
		this.suspended = false;
	}
	
	/**
	 * Creates a travel card that has existed before for a user. Is used when reading from
	 * customer_users.txt
	 * @param id
	 * @param balance
	 * @param suspended
	 */
	public TravelCard(int id, float balance, boolean suspended) {
		this.id = id;
		this.balance = balance;
		this.suspended = suspended;
		//Automatically determines if one cannot pay something new
		if (this.balance < 0) {
			this.cannotPay = true;
		} else {
			this.cannotPay = false;
		}
	}
	
	/**
	 * @return the balance of the card
	 */
	public float getBalance() {
		return this.balance;
	}
	
	/**
	 * @return the id of the card
	 */
	public int getID() {
		return this.id;
	}
	
	/**
	 * @return true if the card is suspended, false otherwise
	 */
	public boolean isSuspended() {
		return this.suspended;
	}
	
	/**
	 * Set the card to suspended
	 */
	public void suspendCard() {
		this.suspended = true;
	}
	
	/**
	 * Set the card to not be suspended
	 */
	public void unSuspendCard() {
		this.suspended = false;
	}
	
	/**
	 * Adds the balance only if it is one of the pre set amounts: 10, 20, or 50
	 * @param amount
	 * @return true if the balance was added, false if not (which means the card was suspended)
	 */
	public boolean addBalance(int amount) {
		if (this.suspended) {
			return false;
		}
		if (amount ==  10 || amount == 20 || amount == 50) {
			this.balance += amount;
		} else {
			return false;
		}
		/*If the card could not be used to pay before and is now positive, allow the card
		* to be able to be used to pay
		*/
		if (this.balance >= 0) {
			this.cannotPay = false;
		}
		return true;
	}
	
	/**
	 * Pay the given amount 
	 * @param amount
	 * @return true if the payment was succesful, false otherwise (either a non-positive balance
	 *         or the card is suspended)
	 */
	public boolean pay(float amount) {
		if (this.cannotPay || this.suspended) {
			return false;
		} 
		this.balance -= amount;
		//If the balance is no longer positive, you can no longer pay after this
		if (this.balance <= 0) {
			this.cannotPay = true;
		}
		return true;
	}
	
	/**
	 * @return return the journey the card is currently doing
	 */
	public Journey getCurrentJourney() {
		return currentJourney;
	}

	/**
	 * @param currentJourney set the currentJourney to this
	 */
	public void setCurrentJourney(Journey currentJourney) {
		this.currentJourney = currentJourney;
	}
	
	
	
}