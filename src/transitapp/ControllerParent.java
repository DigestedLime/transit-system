package transitapp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * This parent class is responsible for making controller children and
 * reducing the amount of code that is written. This class has the exit button
 * and the general layout for changing scenes when a button is pressed.
 * 
 * Citing (note: these were all used in the controller classes to implement the different screens that were used and on how to use the screens using javafx)
 * 
 * 		- https://www.youtube.com/watch?v=XCgcQTQCfJQ&t=118s&ab_channel=JaretWright
 * 			- this was mainly used as a guide on how the controllers in the system works, and how we would move on to different
 * 			  scenes using the controllers. Also used the video as a tutorial on how to use scenebuilder and how FXML files
 * 			  work, and how to hook up methods and objects to the objects that are made in the scenes when using scenebuilder.
 * 			- this code from the video was used in ALL the controller classes.
 * 
 * 		- https://www.youtube.com/watch?v=L72FEmEt-G8
 * 			- this was used to make the window of the applcation borderless
 * 
 * 		- https://www.youtube.com/watch?v=cwJK_WpseKQ&t=138s
 * 			- this was used to make take the information from the javafx textboxes so that we can store the information
 * 
 * 		- https://www.youtube.com/watch?v=ULuXfAm3HLg&t=903s&ab_channel=JavaCodes
 * 			- this was used to see how textfields in javafx would work
 * 
 * 		- https://www.youtube.com/watch?v=Z1W4E2d4Yxo&ab_channel=GenuineCoder
 * 			- this was used when first using scenebuilder to make the scenes for the GUI
 * 		
 * 		- 
 * @author
 *
 */

abstract public class ControllerParent implements Initializable {
	
	/**
	 * This method would close the window of the application.
	 * @param event
	 * @throws IOException
	 */
	public void exitButton(ActionEvent event) throws IOException {
		
		Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
		
		stage.close();
	}
	
	/**
	 * This method would open up the FXML file and then display it as the
	 * current stage. It would also make the corners of the window not sharp.
	 * 
	 * This code is inspired from the YouTube video linked below.
	 * 
	 * @param event
	 * @param file
	 * @throws IOException
	 */
	public FXMLLoader changeScene(Event event, String file) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
		Parent parent = loader.load();
		Scene scene = new Scene(parent);
		scene.setFill(Color.TRANSPARENT);

		Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());

		stage.setScene(scene);
		stage.show();
		return loader;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
