package transitapp;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.*;

public class FXMLMenuController implements Initializable {

	@FXML
	private Button loginButton;
	@FXML
	private Button regButton;
	@FXML
	private Button settingsButton;

	public void loginButtonPush(ActionEvent event) throws IOException {

		Parent loginParent = FXMLLoader.load(getClass().getResource("FXMLLogin.FXML"));
		Scene loginScene = new Scene(loginParent);

		Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());

		stage.setScene(loginScene);
		stage.show();
	}
	
	public void settingsButtonPush(ActionEvent event) throws IOException {
		
		Parent settingsParent = FXMLLoader.load(getClass().getResource("FXMLSettings.FXML"));
		Scene settingsScene = new Scene(settingsParent);
		
		Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());

		stage.setScene(settingsScene);
		stage.show();
	}
	
	public void registerButtonPush(ActionEvent event) throws IOException {
		
		Parent regParent = FXMLLoader.load(getClass().getResource("FXMLRegister.FXML"));
		Scene regScene = new Scene(regParent);
		
		Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());

		stage.setScene(regScene);
		stage.show();
	}

	public void exitButton(ActionEvent event) throws IOException {

		Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());

		stage.close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO
	}

}
