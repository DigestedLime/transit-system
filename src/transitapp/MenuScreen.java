package transitapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class MenuScreen extends Screen {

	public Scene getScreen(){
		
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(100));
		
		BorderPane bpane = new BorderPane();
		grid.setPadding(new Insets(10));
		
		makeLoginButton(grid);
		makeRegButton(grid);
		makeExitButton(grid);
		makeSettingsButton(grid);
		
		bpane.setTop(grid);
		
		Scene scene = new Scene(bpane, 300, 200);
		
		return scene;
	}
	
	public void makeLoginButton(GridPane grid) {
		
		Button loginButton = new Button("Log-in");
		grid.add(loginButton, 0, 0, 10, 20);
		LoginScreen loginObj = new LoginScreen();
		loginButton.setOnAction(e -> loginObj.setScreen());
	}
	
	public void makeRegButton(GridPane grid) {
		
		Button regButton = new Button("Register");
		grid.add(regButton, 0, 3, 10, 20);
		RegisterUserScreen regObj = new RegisterUserScreen();
		regButton.setOnAction(e -> regObj.setScreen());
	}
	
	public void makeExitButton(GridPane grid) {
		Button exitButton = new Button("Exit");
		grid.add(exitButton, 15, 3, 10, 20);
		exitButton.setOnAction(e -> Main.closeStage());
	}
	
	public void makeSettingsButton(GridPane grid) {
		
		Button settingsButton = new Button("Settings");
		grid.add(settingsButton, 15, 0, 10, 20);
		
	}
}
