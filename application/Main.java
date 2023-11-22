package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;

//main final
public class Main extends Application {
	
	private static Stage stage;
	private static Scene mainScene;
	private static Scene newProjectScene;

	@Override
	public void start(Stage primaryStage) {
		
		stage = primaryStage;
		
		try {
			
			//add icon
			
			primaryStage.getIcons().add(new Image("C:\\Users\\anapa\\eclipse-workspace\\ParametrosEletricosLTFinal\\image\\iconTorre.jpg"));
			
			//criação stages
			
			FXMLLoader fxmlMain = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
			Pane paneMain = fxmlMain.load();
			Image towerMainImage = new Image("C:\\Users\\anapa\\eclipse-workspace\\ParametrosEletricosLTFinal\\image\\towerMainImage.jpg");
			BackgroundImage img = new BackgroundImage(towerMainImage, BackgroundRepeat.NO_REPEAT,
					BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			Background backImg = new Background(img);
			mainScene = new Scene(paneMain);
			
			
			FXMLLoader fxmlNewProject = new FXMLLoader(getClass().getResource("/gui/NewProjectView.fxml"));
			Pane scrollPaneNewProject = fxmlNewProject.load();
			newProjectScene = new Scene(scrollPaneNewProject);
			//scrollPaneNewProject.setFitToHeight(true);
			//scrollPaneNewProject.setFitToWidth(true);
		
			
			paneMain.setBackground(backImg);
			primaryStage.setResizable(false);
			primaryStage.setScene(mainScene);
			primaryStage.setTitle("Parâmetros Elétricos LT - UFF");
			primaryStage.show();
			
			
		
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void changeScene(String scene) {

		switch (scene) {
		case "ReturnMain":
			stage.setScene(mainScene);
			stage.setTitle("Parâmetros Elétricos LT - UFF");
			break;
		case "newProject":
			stage.setScene(newProjectScene);
			stage.setTitle("Novo Projeto - Parâmetros Elétricos LT - UFF");
			break;
			
		case "Close":
			stage.close();
			break;

		}

	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
