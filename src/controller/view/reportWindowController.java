/**
 * 
 */
package controller.view;

import com.sun.java.swing.plaf.windows.resources.windows;

import controller.MainApplication;
import experiment.ExperimentGUI;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * @author melikaayoughi
 *
 */
public class reportWindowController {

	private MainApplication mainApp;

	private int iteration;
	private ExperimentGUI experiment;
	
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
		if(iteration < experiment.getNumIterations()) {
			setIteration(iteration+1);
		}
	}
	
	@FXML
    private void handlePrevBtnAction(ActionEvent e){
		if(iteration > 1) {
			setIteration(iteration-1);
		}
	}
	
	private void setIteration(int iteration){
		this.iteration = iteration;
		iterationLabel.setText("Iteration " + iteration);
		
		imageView2.setImage(SwingFXUtils.toFXImage(experiment.getGlobalResponsePlot(200, 200, iteration), new WritableImage(200, 200)));
		imageView3.setImage(SwingFXUtils.toFXImage(experiment.getAgentChangesPlot(200, 200, iteration), new WritableImage(200, 200)));
	}
	
	private void initializeView(){
		// TODO: remove once the experiment is set by the configuration window
		experiment = new ExperimentGUI();
		experiment.setNumIterations(10);

		// load the image
//		Image image1 = new Image(MainApplication.class.getResourceAsStream("view/Images/2.jpg"));
//	    
//		System.out.println( "Path: " + getClass().getResource("/controller/view").toExternalForm());
//		Image image1 = new Image(getClass().getResource("Images/1.png").toExternalForm());
//		Image image1 = new Image("controller/view/Images/1.png");
//	    Image image2 = new Image("controller/view/Images/2.jpg");
//	    Image image3 = new Image("controller/view/Images/3.png");
	    // simple displays ImageView the image as is
	    
//	    imageView1.setImage(image1);
//	    imageView2.setImage(image2);
//	    imageView3.setImage(image3);
		iterationLabel.setAlignment(Pos.CENTER);
		imageView1.setImage(SwingFXUtils.toFXImage(experiment.getGlobalCostPlot(400, 200), new WritableImage(400, 200)));
		setIteration(1);
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
