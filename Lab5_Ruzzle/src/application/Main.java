package application;
	
import it.polito.tdp.model.RuzzleModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			System.out.println("1");
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Ruzzle.fxml"));
			BorderPane root = (BorderPane)loader.load();
			
			RuzzleController controller= loader.getController();
			
			
			RuzzleModel model=new RuzzleModel();
			controller.setModel(model);
			
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
