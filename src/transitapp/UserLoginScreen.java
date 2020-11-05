package transitapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class UserLoginScreen extends Screen{
	
	Scene scene;
	
	@Override
	public Scene getScreen() {
		
		System.out.println("bruh but in Login");
		
		GridPane grid = new GridPane();
		
		TextField usernameText = new TextField();
		TextField passwordText = new TextField();
		Button loginButton = new Button("Login");
		Button registerButton = new Button("Don't have an account? Register here.");
		Button goBackToMenu = new Button("Go Back to Menu");
		
		grid.add(usernameText, 0, 0, 10, 40);
		grid.add(passwordText, 0, 50, 10, 40);
		grid.add(loginButton, 0, 150, 10, 30); 
		grid.add(registerButton, 0, 300, 10, 40);
		grid.add(goBackToMenu, 0, 400, 10, 40);
		
		scene = new Scene(grid, 500, 500);
		
		return scene;
	}

}
