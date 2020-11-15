package user;

import java.util.ArrayList;

import routenetwork.Trip;

public class CustomerUser extends User {
	
	private String email;
	private ArrayList<TravelCard> cards;
	//Has a record of the 3 most recent trips. Start of the array means latest trip
	private ArrayList<Trip> trips;
	
	public CustomerUser(String username, String password, String email) {
		super(username, password);
		this.email = email;
		this.cards = new ArrayList<TravelCard>();
		this.trips = new ArrayList<Trip>();
	}

	
	public void changeName(String new_name) {
		this.username = new_name;
	}
	
	public void addTrip(Trip trip) {
		if (this.trips.size() == 3) {
			this.trips.remove(2);
		}
		this.trips.add(0, trip);
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public ArrayList<TravelCard> getCards() {
		return this.cards;
	}
	
	/**
	 * Makes a new travel card and returns the id made
	 * @return int representing the card's id
	 */
	public int addCard() {
		TravelCard newcard = new TravelCard();
		this.cards.add(newcard);
		return newcard.getID();
	}
	
	public void addExistingCard(int id, int balance, boolean suspended) {
		this.cards.add(new TravelCard(id, balance, suspended));
	}
	
	public boolean removeCard(int id) {
		for (int i = 0; i < this.cards.size(); i++) {
			if (this.cards.get(i).getID() == id) {
				this.cards.remove(i);
				return true;
			}
		}
		return false;
	}
	
	
	
}