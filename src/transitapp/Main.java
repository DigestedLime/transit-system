package transitapp;

import java.util.ArrayList;
import user.CustomerUser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import user.User;

/**
 * This is the entry class to the entire program.
 *
 */

public class Main extends Application {
	Boolean isLoggedIn = false;

	ArrayList<User> users;
	User currentUser;
	Scene currentScene;

	/**
	 * Launches the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * This method is from Application and runs the entire application.
	 */
	@Override
	public void start(Stage stage) throws Exception {

		ArrayList<CustomerUser> users = FileHandler.readFile();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLMenu.FXML"));

		Parent menuParent = loader.load();

		FXMLMenuController temp = loader.getController();

		temp.setData(users);

		Scene scene = new Scene(menuParent);

		scene.setFill(Color.TRANSPARENT);
		stage.initStyle(StageStyle.TRANSPARENT);

		stage.setScene(scene);
		stage.show();

	}
}