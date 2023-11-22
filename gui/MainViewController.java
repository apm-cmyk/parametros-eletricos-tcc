package gui;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;


public class MainViewController implements Initializable {

	@FXML
	private Button btNewProject;
	@FXML
	private Button btInstruction;
	@FXML
	private Button btClose;
	

	@FXML
	public void onbtNewProjectClick() {
		Main.changeScene("newProject");
	}
	
	@FXML
	public void onBtClose() {
	Main.changeScene("Close");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
}
