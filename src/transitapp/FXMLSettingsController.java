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

public class FXMLSettingsController implements Initializable {

	public void backButtonPush(ActionEvent event) throws IOException {

		Parent menuParent = FXMLLoader.load(getClass().getResource("FXMLMenu.FXML"));
		Scene menuScene = new Scene(menuParent);

		Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());

		stage.setScene(menuScene);
		stage.show();
	}
	
	public void exitButton(ActionEvent event) throws IOException {

		Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());

		stage.close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}