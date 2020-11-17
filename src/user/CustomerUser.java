package user;

import java.util.ArrayList;

/**
 * 
 * A Customer User is a customer that uses the transit system. They have an email, a list of the
 * recent trips, and a list of TravelCards that they own
 *
 */
public class CustomerUser extends User {
	
	private String email;
	private ArrayList<TravelCard> cards;
	//Has a record of the most recent trips. End of the array means latest trip
	private ArrayList<String> trips;
	
	/**
	 * Creates a new CustomerUser object with no cards or previous trips
	 * @param username
	 * @param password
	 * @param email
	 */
	public CustomerUser(String username, String password, String email) {
		super(username, password);
		this.email = email;
		this.cards = new ArrayList<TravelCard>();
		this.trips = new ArrayList<String>();
	}

	/**
	 * 
	 * @param newName  name to change to
	 */
	public void changeName(String newName) {
		this.username = newName;
	}
	
	
	/**
	 * 
	 * @param password
	 * @param email
	 * @return  true iff the password and email inputted match the email and password of this customer
	 */
	public boolean logIn(String password, String email) {
		return super.logIn(password) && this.email.equals(email);
	}
	
	/**
	 * 
	 * @return this customer's email
	 */
	public String getEmail() {
		return this.email;
	}
	
	/**
	 * 
	 * @return the list of the customer's cards
	 */
	public ArrayList<TravelCard> getCards() {
		return this.cards;
	}
	
	public ArrayList<String> getTrips() {
		return this.trips;
	}
	
	/**
	 * Adds a trip to the list of trips, assuming it is already a string representation
	 * @param trip
	 */
	public void addTripString(String journey) {
		this.trips.add(journey);
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
	
	/**
	 * Adds a card that is owned by the customer. Only used when reading from customer_users.txt
	 * for existing users
	 * @param id
	 * @param balance
	 * @param suspended
	 */
	public void addExistingCard(int id, float balance, boolean suspended) {
		this.cards.add(new TravelCard(id, balance, suspended));
	}
	
}