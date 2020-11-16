package user;

import java.util.ArrayList;

import routenetwork.Trip;

public class CustomerUser extends User {
	
	private String email;
	private ArrayList<TravelCard> cards;
	private static int MAX_TRIPS = 5;
	//Has a record of the most recent trips. Start of the array means latest trip
	private ArrayList<String> trips;
	
	public CustomerUser(String username, String password, String email) {
		super(username, password);
		this.email = email;
		this.cards = new ArrayList<TravelCard>();
		this.trips = new ArrayList<String>();
	}

	public void changeName(String new_name) {
		this.username = new_name;
	}
	
	public void addTrip(Trip trip) {
		if (this.trips.size() == MAX_TRIPS) {
			this.trips.remove(0);
		}
		this.trips.add(trip.getRoute().get(0).getName() + " -> " + trip.getRoute().get(
				trip.getRoute().size() - 2).getName() + ": " + Float.toString(trip.get_cost()));
	}
	
	public void addTripString(String trip) {
		if (this.trips.size() == MAX_TRIPS) {
			this.trips.remove(MAX_TRIPS - 1);
		}
		this.trips.add(trip);
	}
	
	public boolean logIn(String password, String email) {
		return super.logIn(password) && this.email.equals(email);
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public ArrayList<TravelCard> getCards() {
		return this.cards;
	}
	
	public ArrayList<String> getTrips() {
		return this.trips;
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
	
	public void addExistingCard(int id, float balance, boolean suspended) {
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