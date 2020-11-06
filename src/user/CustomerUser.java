package user;

import java.util.ArrayList;

class CustomerUser extends User {
	
	private String email;
	private ArrayList<TravelCard> cards;
	
	public CustomerUser(String username, String password, String email) {
		super(username, password);
		this.email = email;
		this.cards = new ArrayList<TravelCard>();
	}

	
	public void changeName(String new_name) {
		this.username = new_name;
	}
	
	public String getEmail() {
		return this.email;
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