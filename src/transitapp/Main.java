
package transitapp;

import java.util.HashMap;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import user.User;

public class Main extends Application{
	
	HashMap<String, User> users;
	
	Boolean isLoggedIn = false;
	
	User currentUser;
	private static Stage currentStage;
	
	public static Stage getStage() {
		return currentStage;
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Main.currentStage = stage;
		Screen menu = null;
		if (isLoggedIn) {
			//menu = new DashboardScreen(); 
		} else {
			menu = new MenuScreen();
		}
		Scene scene = menu.getScreen();
		Main.currentStage.setTitle("Transit Application");
		Main.currentStage.setScene(scene);
		Main.currentStage.show();
		
	}
	
	
}
