package user;

import java.util.ArrayList;

import routenetwork.Journey;

public class TravelCard {
	private float balance;
	private boolean cannotPay;
	private boolean suspended;
	private int id;
	private ArrayList<Journey> journeys;
	
	public static int UNIQUE_ID = 0;
	
	public TravelCard() {
		this.id = UNIQUE_ID;
		UNIQUE_ID++;
		
		this.journeys = new ArrayList<>();
		this.balance = 19;
		this.cannotPay = false;
		this.suspended = false;
	}
	
	public TravelCard(int id, float balance, boolean suspended) {
		this.id = id;
		this.balance = balance;
		this.suspended = suspended;
		
		this.journeys = new ArrayList<>();
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
			System.out.println("Suspended: " + this.suspended);
			System.out.println("Cannot Pay: " + this.cannotPay);
			return false;
		} 
		this.balance -= amount;
		if (this.balance <= 0) {
			this.cannotPay = true;
		}
		return true;
	}

	public Journey getCurrentJourney() {
		if (this.journeys.isEmpty()) {
			return null;
		}
		return this.journeys.get(this.journeys.size() - 1);
	}
	
	public void addJourney(Journey journey) {
		this.journeys.add(journey);
	}
	
	public Journey getPrevJourney() {
		if (this.journeys.size() > 1) {
			return this.journeys.get(this.journeys.size() - 2);
		}
		return null;
	}
	
	public boolean cannotPay() {
		return this.cannotPay;
	}
	
	public String toString() {
		return ((Integer)this.id).toString();
	}
	
}