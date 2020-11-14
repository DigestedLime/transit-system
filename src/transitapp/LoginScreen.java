package transitapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class LoginScreen extends Screen{
	
	Scene scene;
	
	@Override
	public Scene getScreen() {

		GridPane grid = new GridPane();
		
		makeUserText(grid);
		makePassText(grid);
		makeLoginButton(grid);
		makeRegButton(grid);
		makeBackButton(grid);
		makeExitButton(grid);
		
		scene = new Scene(grid, 500, 500);
		
		return scene;
	}
	
	public void makeUserText(GridPane grid) {
		
		TextField usernameText = new TextField();
		grid.add(usernameText, 0, 0, 10, 40);
	}

	public void makePassText(GridPane grid) {
		
		TextField passwordText = new TextField();
		grid.add(passwordText, 0, 50, 10, 40);
	}
	
	public void makeLoginButton(GridPane grid) {
		
		Button loginButton = new Button("Login");
		grid.add(loginButton, 0, 150, 10, 30); 
		loginButton.setOnAction(e -> System.out.println("Logging in"));
	}
	
	public void makeRegButton(GridPane grid) {
		
		Button registerButton = new Button("Don't have an account? Register here.");
		grid.add(registerButton, 0, 300, 10, 40);
		RegisterUserScreen regObj = new RegisterUserScreen();
		registerButton.setOnAction(e -> regObj.getScreen());
	}
	
	public void makeBackButton(GridPane grid) {
		
		Button backButton = new Button("Go Back to Menu");
		grid.add(backButton, 0, 400, 10, 40);
		MenuScreen menuObj = new MenuScreen();
		backButton.setOnAction(e -> menuObj.getScreen());
	}
	
	public void makeExitButton(GridPane grid) {
		
		Button exitButton = new Button("Exit");
		grid.add(exitButton, 0, 500);
		exitButton.setOnAction(e -> Main.closeStage());
	}
}
