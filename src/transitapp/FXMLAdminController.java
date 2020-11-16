package transitapp;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class FXMLAdminController extends ControllerParent implements Initializable {
	
	@FXML
	public Text revenue;
	@FXML
	public Text costs;
	@FXML
	public Text profits;
	@FXML
	public DatePicker startDate;
	@FXML
	public DatePicker endDate;
	@FXML
	public TextField startTime;
	@FXML
	public TextField endTime;
	@FXML
	public Button status;
	@FXML
	public Text fullName;
	@FXML
	public Text email;
	
	public void signOutButton(ActionEvent event) throws IOException {
		
		changeScene(event, "FXMLMenu.FXML");
	}
	
	public void dailyReportButton(ActionEvent event) throws IOException {
		
		// TODO
	}
	
	public void generateRepButton(ActionEvent event) throws IOException {
		
		//TODO
	}
	
}
