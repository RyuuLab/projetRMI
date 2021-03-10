package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	private static Stage stg;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		//stg = primaryStage;
		//Parent root;
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../vues/Connexion.fxml"));
			Connexion connexion = new Connexion();
			fxmlLoader.setController(connexion);
			AnchorPane root = null;
			try{
				root = fxmlLoader.load();
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Connexion");
			primaryStage.show();
			root = FXMLLoader.load(getClass().getResource("../vues/Connexion.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
