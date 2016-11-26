/**
 * 
 */
package controller.view;

import controller.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author melikaayoughi
 *
 */
public class reportWindowController {

	private MainApplication mainApp;
	
	@FXML 
	private ImageView imageView1;
	@FXML 
	private ImageView imageView2;
	@FXML 
	private ImageView imageView3;
	@FXML
	private Button prevButton;
	@FXML
	private Button nextButton;
	@FXML
	private Label iterationLabel;
	
	@FXML
    private void handleNextBtnAction(ActionEvent e){
	}
	
	@FXML
    private void handlePrevBtnAction(ActionEvent e){
	}
	
	//////////////////////////////////// public setters ////////////////////////////////////////
	public void setIteration(String iterationString){
		iterationLabel.setText(iterationString);
	}
	
	private void initializeView(){

		// load the image
//		Image image1 = new Image(MainApplication.class.getResourceAsStream("view/Images/2.jpg"));
//	    
//		System.out.println( "Path: " + getClass().getResource("/controller/view").toExternalForm());
//		Image image1 = new Image(getClass().getResource("Images/1.png").toExternalForm());
//		Image image1 = new Image("Images/1.png");
//	    Image image2 = new Image("Images/2.jpg");
//	    Image image3 = new Image("Images/3.png");
	    // simple displays ImageView the image as is
	    
//	    imageView1.setImage(image1);
//	    imageView2.setImage(image2);
//	    imageView3.setImage(image3);
		iterationLabel.setAlignment(Pos.CENTER);
		setIteration("First Iteration");
	}
	
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	initializeView();
    }
	
	/**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApplication mainApp) {
        this.mainApp = mainApp;
    }
}
