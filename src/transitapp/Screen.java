package transitapp;

import javafx.scene.Scene;

abstract public class Screen {
	
	abstract public Scene getScreen();
	
	public void setScreen() {
		Main.getStage().setScene(this.getScreen());
	}
	
}
