package transitapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import user.CustomerUser;
import user.TravelCard;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import user.User;

public class Main extends Application{
	
	//HashMap<String, User> users;
	
	Boolean isLoggedIn = false;
	
	ArrayList<User> users;
	User currentUser;
	Scene currentScene;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		ArrayList<CustomerUser> users = readUser();
		
		Parent menuParent = FXMLLoader.load(getClass().getResource("FXMLMenu.FXML"));
		
		Scene scene = new Scene(menuParent);
		
		scene.setFill(Color.TRANSPARENT);
		stage.initStyle(StageStyle.TRANSPARENT);
		
		stage.setScene(scene);
		stage.show();
		
	}
	
	
	private ArrayList<CustomerUser> readUser() {
		ArrayList<CustomerUser> users = new ArrayList<CustomerUser>();
		Scanner sc;
		try {
			sc = new Scanner(new File("src/users.txt"));
			String line = sc.nextLine();
			String[] line_elements = line.split(" ");
			int card_count = Integer.parseInt(line_elements[0]);
			TravelCard.UNIQUE_ID = card_count + 1;
			while (sc.hasNextLine()) {
				line = sc.nextLine();
				line_elements = line.split(" ");
				int name_words = Integer.parseInt(line_elements[0]);
				String name = "";
				for (int i = 0; i < name_words; i++) {
					name += line_elements[i + 1] + " ";
					
				}
				name = name.substring(0, name.length() - 1);
				
				String password = line_elements[name_words + 1];
				String email = line_elements[name_words + 2];
				CustomerUser current_user = new CustomerUser(name, password, email);
				
				
				int num_cards = Integer.parseInt(line_elements[name_words + 3]);
				for (int i = 0; i < num_cards; i++) {
					line = sc.nextLine();
					line_elements = line.split(" ");
					int id = Integer.parseInt(line_elements[0]);
					boolean is_susp = true;
					if (line_elements[1].startsWith("f")) {
						is_susp = false;
					}
					int balance = Integer.parseInt(line_elements[2]);
					current_user.addExistingCard(id, balance, is_susp);
				}
				users.add(current_user);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
		
	}
}