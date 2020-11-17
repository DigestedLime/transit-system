package transitapp;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import user.CustomerUser;

public class FXMLAdminController extends ControllerParent implements Initializable {
	
	public ArrayList<CustomerUser> users;
	
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
	
	/**
	 * This method would sign the user out of the account and return them back to
	 * the menu screen.
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void signOutPush(ActionEvent event) throws IOException {

		FXMLLoader loader = changeScene(event, "FXMLMenu.FXML");
		FXMLMenuController menu = loader.getController();
		menu.setData(this.users);
	}
	
	public void dailyReportButton(ActionEvent event) throws IOException {
		
		// TODO
	}
	
	public void generateRepButton(ActionEvent event) throws IOException {
		
		//TODO
	}
	
	/**
	 * @param users passes the list of all CustomerUsers in the system to this controller
	 */
	public void setData(ArrayList<CustomerUser> users) {
		this.users = users;
	}
}
