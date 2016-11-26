/**
 * 
 */
package controller.view;

import java.io.File;

import Utilities.ConfirmBox;
import controller.MainApplication;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;

/**
 * @author melikaayoughi
 *
 */
public class configurationWindowController {

	private MainApplication mainApp;
	
	@FXML
	private TextField dataSetLocationTextField;
	@FXML
	private Button dataSetBrowse;
	@FXML
	private TextField globalCostLocationTextField;
	@FXML
	private Button globalCostBrowse;
	@FXML
	private Slider localCostInfluenceSlider;
	@FXML
	private Label localCostLabel;
	@FXML
	private ComboBox<String> algorithmComboBox;
	@FXML
	private Slider numberOfIterationsSlider;
	@FXML
	private Label numberOfIterationsLabel;
	@FXML
	private TextField seedTextField;
	@FXML
	private Button abortBtn;
	@FXML
	private Button runBtn;
	
	////////////////////////////////// public getters ////////////////////////////////////////
	
	public String getDataSetLocation(){
		if(!dataSetLocationTextField.getText().isEmpty())
			return dataSetLocationTextField.getText();
		else
			return dataSetLocationTextField.getPromptText();
	}
	
	public String getGlobalCostLocation(){
		if(!globalCostLocationTextField.getText().isEmpty())
			return globalCostLocationTextField.getText();
		else
			return globalCostLocationTextField.getPromptText();
	}
	
	public Double getLocalCostInfluence(){
		return Double.parseDouble(localCostLabel.getText().toString());
	}
	
	public String getAlgorithm(){
		return algorithmComboBox.getValue();
	}
	
	public Double getNumberOfIterations(){
		return Double.parseDouble(numberOfIterationsLabel.getText().toString());
	}
	
	public String getSeed(){
		if(!seedTextField.getText().isEmpty())
			return seedTextField.getText();
		else
			return seedTextField.getPromptText();
	}
	
	////////////////////////////////// Set Desired Defaults ///////////////////////////////////
	
	private void setDefaultsConfigurationWindow(){
		
		// Data Set Location
		dataSetLocationTextField.setPromptText("Some default location");
		
		// Global Cost Location
		globalCostLocationTextField.setPromptText("Some default cost location");
		
		// Local Cost Influence Slider
		localCostLabel.setText(String.format("%.1f", localCostInfluenceSlider.getValue()));
		localCostInfluenceSlider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov,
					Number old_val, Number new_val) {
				localCostInfluenceSlider.setValue(new_val.intValue());
				localCostLabel.setText(String.format("%.1f", new_val));
			}
		});

		// Algorithm combo box
		ObservableList<String> optAlgorithm = 
				FXCollections.observableArrayList(
						"Algorithm 1",
						"Algorithm 2",
						"Algorithm 3");
		algorithmComboBox.setItems(optAlgorithm);
		algorithmComboBox.getSelectionModel().selectFirst();

		// Number of Iterations Slider
		numberOfIterationsLabel.setText(String.format("%.0f", numberOfIterationsSlider.getValue()));
		numberOfIterationsSlider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov,
					Number old_val, Number new_val) {
				numberOfIterationsSlider.setValue(new_val.intValue());
				numberOfIterationsLabel.setText(String.format("%.0f", new_val));
			}
		});

		// Seed Text Field
		seedTextField.setPromptText("default seed");
	}
	
	@FXML
    private void handleBrowseDataSetLocation(ActionEvent e){
    	DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("This is my file ch");
        //Show open file dialog
        File file = directoryChooser.showDialog(null);

       if(file!=null)
            dataSetLocationTextField.setText(file.getPath());
    }
	
	@FXML
    private void handleBrowseGlobalCostLocation(ActionEvent e){
    	DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("This is my file ch");
        //Show open file dialog
        File file = directoryChooser.showDialog(null);

       if(file!=null)
            globalCostLocationTextField.setText(file.getPath());
    }
	
	@FXML
    private void handleRunBtn(ActionEvent e){
		try {
			ConfirmBox confirmBox = new ConfirmBox();
			boolean answer = confirmBox.display("Warning", "You have chosen the following settings."+
			"Please check them before running" + "\n" +
					"data set location: " + getDataSetLocation() + "\n" +
					"global cost location: " + getGlobalCostLocation() + "\n" +
					"local cost influence: " + getLocalCostInfluence().toString() + "\n" +
					"algorithm: " + getAlgorithm() + "\n" +
					"number of iterations: " + getNumberOfIterations().toString() + "\n" +
					"seed: " + getSeed());
			if (answer == true){
				
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	/**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	// Set prompt values for text fields and combo boxes
    	setDefaultsConfigurationWindow();
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
