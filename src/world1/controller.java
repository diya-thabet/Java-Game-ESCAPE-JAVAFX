package world1;

import java.io.IOException;
import java.util.LinkedList;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.Node;
import javafx.stage.Stage;
public class controller {
	Pane root;
	Stage stage;
	Scene scene;

	@FXML
	private Label MyLabel;
	@FXML
	private ImageView TextImage;
	@FXML 
	private ImageView imageView1;
	@FXML 
	private ImageView imageView2;
	@FXML 
	private ImageView imageView3;

	
	public void Start(ActionEvent e) throws IOException {
	  root = FXMLLoader.load(getClass().getResource("/world1/MyHouse.fxml"));
	  stage = (Stage)((Node)e.getSource()).getScene().getWindow();
	  scene = new Scene(root);
	  stage.setScene(scene);
	  root.requestFocus();
	  stage.show();

	}

	public void Help(ActionEvent e) {
		MyLabel.setOpacity(1);
		TextImage.setOpacity(1);
	}
	public void Exit(ActionEvent e) {
		Platform.exit();
	}
	public  void handleImageHover1(MouseEvent e) {
	   imageView1.setImage(new Image(getClass().getResource("/button_image_selected.png").toExternalForm()));
	     
	   }
	public void handleMouseExit1(MouseEvent e) {
	    // Reset the image when the mouse exits
	    imageView1.setImage(new Image(getClass().getResource("/button_image.png").toExternalForm()));
	}
	public  void handleImageHover2(MouseEvent e) {
		   imageView2.setImage(new Image(getClass().getResource("/button_image_selected.png").toExternalForm()));
		     
		   }
	public void handleMouseExit2(MouseEvent e) {
		    // Reset the image when the mouse exits
		    imageView2.setImage(new Image(getClass().getResource("/button_image.png").toExternalForm()));
	}
	public  void handleImageHover3(MouseEvent e) {
		   imageView3.setImage(new Image(getClass().getResource("/button_image_selected.png").toExternalForm()));
		     
		   }
	public void handleMouseExit3(MouseEvent e) {
		    // Reset the image when the mouse exits
		    imageView3.setImage(new Image(getClass().getResource("/button_image.png").toExternalForm()));
	}
		
}
	
