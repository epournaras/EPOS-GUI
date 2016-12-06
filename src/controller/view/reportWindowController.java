/**
 * 
 */
package controller.view;

import controller.MainApplication;
//TODO
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
	//TODO
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
//		if(iteration < experiment.getNumIterations()) {
//			setIteration(iteration+1);
//		}
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
		
//		imageView2.setImage(SwingFXUtils.toFXImage(experiment.getGlobalResponsePlot(200, 200, iteration), new WritableImage(200, 200)));
//		imageView3.setImage(SwingFXUtils.toFXImage(experiment.getAgentChangesPlot(200, 200, iteration), new WritableImage(200, 200)));
	}
	
	private void initializeView(){
		
		iterationLabel.setAlignment(Pos.CENTER);
//		imageView1.setImage(SwingFXUtils.toFXImage(experiment.getGlobalCostPlot(400, 200), new WritableImage(400, 200)));
		setIteration(1);
	}
	
	//TODO
	/**
	 * Sets the experiment to GUI experiment 
	 * specified in configuration window controller
	 * 
	 * @param experiment
	 */
	public void setExperiment(ExperimentGUI experiment){
		this.experiment = experiment;
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
