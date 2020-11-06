package transitapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MenuScreen extends Screen {

	public Scene getScreen(){
		
		System.out.println("bruh but in Menu");
		
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(100));
		
		BorderPane bpane = new BorderPane();
		grid.setPadding(new Insets(10));
		
		Button userLoginButton = new Button("User Login");
		Button registerButton = new Button("Register");
		Button adminLoginButton = new Button("Admin Login");
		Button settingsButton = new Button("Settings");
		Button exitButton = new Button("Exit");
		
		grid.add(userLoginButton, 0, 0, 10, 20);
		grid.add(registerButton, 0, 3, 10, 20);
		grid.add(adminLoginButton, 15, 0, 10, 20);
		grid.add(settingsButton, 15, 3, 10, 20);
		grid.add(exitButton, 0, 20, 10, 20);
		
		bpane.setTop(grid);
		
		Scene scene = new Scene(bpane, 300, 200);
		
		return scene;
	}

//	public void getMenuScene(user u) {
//		something goes here
//	}

}
