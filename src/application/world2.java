package application;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import tile.TileManager;

public class world2  {
    
	
    public void start1(Stage stage) {
        GamePanel gp = new GamePanel();
        
        gp.KeyHandeler();
        gp.player.HandlePlayer(); 
        gp.player.draw(gp.getCharacterLayer());	
        stage.setScene(gp.getScene()); // Use the scene from GamePanel
        stage.setTitle("Game Panel Example");
        gp.getRoot().requestFocus();
        stage.show();
        
    }

   /* public static void main(String[] args) {
        launch();
    }*/
}
