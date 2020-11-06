package user;



class TravelCard {
	private float balance;
	private boolean cannot_pay;
	private int id;
	
	private static int UNIQUE_ID = 0;
	
	public TravelCard() {
		this.id = UNIQUE_ID;
		UNIQUE_ID++;
		
		this.balance = 19;
		this.cannot_pay = false;
	}
	
	public float getBalance() {
		return this.balance;
	}
	
	public int getID() {
		return this.id;
	}
	
	
	public boolean addBalance(int amount) {
		if (amount ==  10 || amount == 20 || amount == 50) {
			this.balance += amount;
		} else {
			return false;
		}
		if (this.balance >= 0) {
			this.cannot_pay = false;
		}
		return true;
	}
	
	public boolean pay(float amount) {
		if (this.cannot_pay) {
			return false;
		} 
		this.balance -= amount;
		if (this.balance <= 0) {
			this.cannot_pay = true;
		}
		return true;
	}
	
	
	
}