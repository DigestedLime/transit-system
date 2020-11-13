package transitapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class MenuScreen extends Screen {

	public Scene getScreen(){
		
		System.out.println("bruh but in Menu");
		
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(100));
		
		BorderPane bpane = new BorderPane();
		grid.setPadding(new Insets(10));
		
		Button loginButton = new Button("Log-in");
		Button registerButton = new Button("Register");
		Button settingsButton = new Button("Settings");
		Button exitButton = new Button("Exit");
		
		grid.add(loginButton, 0, 0, 10, 20);
		grid.add(registerButton, 0, 3, 10, 20);
		grid.add(settingsButton, 15, 0, 10, 20);
		grid.add(exitButton, 15, 3, 10, 20);
		
		LoginScreen loginObj = new LoginScreen();
		
		loginButton.setOnAction(e -> loginObj.setScreen());
		
		bpane.setTop(grid);
		
		Scene scene = new Scene(bpane, 300, 200);
		
		return scene;
	}

}
