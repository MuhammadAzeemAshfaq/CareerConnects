package application;
	
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application 
{
	@Override
	public void start(Stage primaryStage) {
		try {
			/*BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());*/
			//System.out.println("profile clicked successfully");
			//Parent root=FXMLLoader.load(getClass().getResource("MainSceneJS.fxml"));
			Parent root=FXMLLoader.load(getClass().getResource("Login.fxml"));
			Scene scene=new Scene(root);  
					
			primaryStage.setTitle("CareerConnects");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		launch(args);
		
		
		
	}
}
