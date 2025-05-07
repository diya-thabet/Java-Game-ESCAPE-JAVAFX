package world1;

import java.io.IOException;
import java.util.LinkedList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class World extends Application {
	
	Parent root;
	//public static final double tileWidth = 57.1;
	//public static final double tileHeight = 56.2;
	//LinkedList<ObstacleTile> barrier;
    //Character character;
	
	public static void main( String[] args ) { launch(args);}

	@Override
	public void start(Stage stage) { 
		// First show game starting screen
		try {
			root= FXMLLoader.load(getClass().getResource("/world1/world.fxml"));
			Scene scene = new Scene(root );
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setTitle("Starting Menu");
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	 }
}