package transitapp;

import java.util.HashMap;

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
	
	HashMap<String, User> users;
	
	Boolean isLoggedIn = false;
	
	User currentUser;
	Scene currentScene;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		Parent menuParent = FXMLLoader.load(getClass().getResource("FXMLMenu.FXML"));
		
		Scene scene = new Scene(menuParent);
		
		scene.setFill(Color.TRANSPARENT);
		stage.initStyle(StageStyle.TRANSPARENT);
		
		stage.setScene(scene);
		stage.show();
		
	}
	
}