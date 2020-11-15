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