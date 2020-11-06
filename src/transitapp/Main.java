
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
	Scene currentScene;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		MenuScreen menu = new MenuScreen();
		this.currentScene = menu.getScreen();
		
		stage.setTitle("Transit Application");
		stage.setScene(this.currentScene);
		stage.show();
		
	}
	
	
}
