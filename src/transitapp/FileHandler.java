package transitapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import user.CustomerUser;
import user.TravelCard;

public class FileHandler {
	
	
	public static ArrayList<CustomerUser> readFile() {
		ArrayList<CustomerUser> users = new ArrayList<CustomerUser>();
		Scanner sc;
		try {
			sc = new Scanner(new File("customer_users.txt"));
			
			//Reads the first line containing the comment about the files format
			sc.nextLine();
			
			//Reads the first line, which represents the previous card id counter, to ensure
			//the card ids stay unique
			String line = sc.nextLine();
			String[] line_elements = line.split(" ");
			int card_count = Integer.parseInt(line_elements[0]);
			TravelCard.UNIQUE_ID = card_count + 1;
			while (sc.hasNextLine()) {
				//Reads the first line for a user, setting the data to the appropriate variable
				line = sc.nextLine();
				line_elements = line.split(" ");
				int name_words = Integer.parseInt(line_elements[0]);
				String name = "";
				for (int i = 0; i < name_words; i++) {
					name += line_elements[i + 1] + " ";
					
				}
				//Removes the last space added in the for loop
				name = name.substring(0, name.length() - 1);
				
				String password = line_elements[name_words + 1];
				String email = line_elements[name_words + 2];
				CustomerUser current_user = new CustomerUser(name, password, email);
				
				//read through the file for the number of cards, then trips there are
				//for the user
				int num_cards = Integer.parseInt(line_elements[name_words + 3]);
				int num_trips = Integer.parseInt(line_elements[name_words + 4]);
				for (int i = 0; i < num_cards; i++) {
					line = sc.nextLine();
					line_elements = line.split(" ");
					int id = Integer.parseInt(line_elements[0]);
					boolean is_susp = true;
					if (line_elements[1].startsWith("f")) {
						is_susp = false;
					}
					float balance = Float.parseFloat(line_elements[2]);
					current_user.addExistingCard(id, balance, is_susp);
				}
				
				//Adds the data to the user object

				for (int i = 0; i < num_trips; i++) {
					line = sc.nextLine();
					current_user.addTripString(line);
				}
				users.add(current_user);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public static void writetoFile(ArrayList<CustomerUser> users) {
		/* Source used to learn how to write to a file:
		 * https://www.w3schools.com/java/java_files_create.asp
		 */
		try {
		      FileWriter writer = new FileWriter("customer_users.txt");
		      writer.write("# FORMAT: First line after this comment represents the current counter "
		      		+ "for TravelCard.UNIQUE_ID so that id numbers stay unique. The format after is "
		      		+ "for a user: <number of words in name> <name> <password> <email> <number of "
		      		+ "cards of user> <number of recent trips>. For each card of the user, there is "
		      		+ "then a line after the user line in format of <unique_id> <\"t\" if suspended, "
		      		+ "\"f\" otherwise\"> <card balance>. After all cards of one user are shown, the "
		      		+ "next line is the first trip, which is in the same format as recent trips in "
		      		+ "CustomerUser. After all trips are displayed, the next line is the next user, "
		      		+ "assuming there is one.\n");
		      writer.write(Integer.toString(TravelCard.UNIQUE_ID));
		      //Only write new line characters when there is something next to write
		      if (users.size() > 0) {
		    	  writer.write("\n");
		      }
		      
		      for (int i = 0; i < users.size(); i++) {
		    	  CustomerUser user = users.get(i);
		    	  writer.write(Integer.toString(user.getUsername().split(" ").length));
		    	  writer.write(" " + user.getUsername() + " " + user.getPassword() +
		    			  " " + user.getEmail() + " " + user.getCards().size() +
		    			  " " + user.getTrips().size());
		    	  
		    	  if (user.getCards().size() > 0) {
		    		  writer.write("\n");
		    	  }
		    	  
		    	  for (int j = 0; j < user.getCards().size(); j++) {
		    		  TravelCard card = user.getCards().get(j);
		    		  if (card.isSuspended()) {
		    			  writer.write(Integer.toString(card.getID()) + " t " + 
		    					  Float.toString(card.getBalance()));
		    		  } else {
		    			  writer.write(Integer.toString(card.getID()) + " f " + 
		    					  Float.toString(card.getBalance()));
		    		  }
		    		  
		    		  if (j < user.getCards().size() - 1) {
		    			  writer.write("\n");
		    		  }
		    	  }
		    	  
		    	  if (user.getTrips().size() > 0) {
		    		  writer.write("\n");
		    	  }
		    	  for (int j = 0; j < user.getTrips().size(); j++) {
		    		  writer.write(user.getTrips().get(j));
		    		  if (j < user.getTrips().size() - 1) {
		    			  writer.write("\n");
		    		  }
		    	  }
		    	  if (i < users.size() - 1) {
		    		  writer.write("\n");
		    	  } 
		      }
		      
		      writer.close();
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
	}
	
	
}