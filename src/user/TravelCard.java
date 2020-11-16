package user;

import routenetwork.Journey;

public class TravelCard {
	private float balance;
	private boolean cannotPay;
	private boolean suspended;
	private int id;
	private Journey currentJourney;
	
	public static int UNIQUE_ID = 0;
	
	public TravelCard() {
		this.id = UNIQUE_ID;
		UNIQUE_ID++;
		
		this.setCurrentJourney(null);
		this.balance = 19;
		this.cannotPay = false;
		this.suspended = false;
	}
	
	public TravelCard(int id, float balance, boolean suspended) {
		this.id = id;
		this.balance = balance;
		this.suspended = suspended;
		if (this.balance < 0) {
			this.cannotPay = true;
		} else {
			this.cannotPay = false;
		}
	}
	
	public float getBalance() {
		return this.balance;
	}
	
	public int getID() {
		return this.id;
	}
	
	public boolean isSuspended() {
		return this.suspended;
	}
	
	public void suspendCard() {
		this.suspended = true;
	}
	
	public void unSuspendCard() {
		this.suspended = false;
	}
	
	public boolean addBalance(int amount) {
		if (this.suspended) {
			return false;
		}
		if (amount ==  10 || amount == 20 || amount == 50) {
			this.balance += amount;
		} else {
			return false;
		}
		if (this.balance >= 0) {
			this.cannotPay = false;
		}
		return true;
	}
	
	public boolean pay(float amount) {
		if (this.cannotPay || this.suspended) {
			return false;
		} 
		this.balance -= amount;
		if (this.balance <= 0) {
			this.cannotPay = true;
		}
		return true;
	}

	public Journey getCurrentJourney() {
		return currentJourney;
	}

	public void setCurrentJourney(Journey currentJourney) {
		this.currentJourney = currentJourney;
	}
	
	
	
}