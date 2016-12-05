/**
 * 
 */
package controller.view;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;
import java.util.Random;
import Utilities.ConfirmBox;
import controller.MainApplication;
import experiment.ExperimentGUI;
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
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

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
	private Slider numberOfChildrenSlider;
	@FXML
	private Label numberOfChildrenLabel;
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
	
	public Integer getNumberOfChildren(){
		return Integer.parseInt(numberOfChildrenLabel.getText());
	}

	public Integer getNumberOfIterations(){
		return Integer.parseInt(numberOfIterationsLabel.getText());
	}
	
	public Long getSeed(){
		if(!seedTextField.getText().isEmpty())
			return Long.parseLong(seedTextField.getText());
		else
			return Long.parseLong(seedTextField.getPromptText());
	}
	
	////////////////////////////////// Set Desired Defaults ///////////////////////////////////
	
	private void setDefaultsConfigurationWindow(){
		Locale.setDefault(Locale.US);
		
		// Data Set Location
		dataSetLocationTextField.setPromptText("datasets/");
		
		// Global Cost Location
		globalCostLocationTextField.setPromptText("Some default cost location");
		
		// Local Cost Influence Slider
		localCostLabel.setText(String.format("%.1f", localCostInfluenceSlider.getValue()));
		localCostInfluenceSlider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov,
					Number old_val, Number new_val) {
				Double toBeTruncated = new Double(new_val.doubleValue());
				Double truncatedDouble = BigDecimal.valueOf(toBeTruncated)
					    .setScale(1, RoundingMode.HALF_UP)
					    .doubleValue();
				localCostInfluenceSlider.setValue(truncatedDouble);
				localCostLabel.setText(String.format("%.1f", new_val));
			}
		});

		// Algorithm combo box
		ObservableList<String> optAlgorithm = 
				FXCollections.observableArrayList(
						"I-EPOS",
						"Global Gradient",
						"Individual Gradient",
						"Adaptive Gradient");
		algorithmComboBox.setItems(optAlgorithm);
		algorithmComboBox.getSelectionModel().selectFirst();

		// Number of Children Slider
		numberOfChildrenLabel.setText(String.format("%.0f", numberOfChildrenSlider.getValue()));
		numberOfChildrenSlider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov,
					Number old_val, Number new_val) {
				numberOfChildrenSlider.setValue(new_val.intValue());
				numberOfChildrenLabel.setText(Integer.toString(new_val.intValue()));
				}
		});
				
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
		seedTextField.setPromptText("" + new Random().nextLong());
	}
	
	@FXML
    private void handleBrowseDataSetLocation(ActionEvent e){
    	DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("This is my file ch");
        directoryChooser.setInitialDirectory(new File("datasets"));
        //Show open file dialog
        File file = directoryChooser.showDialog(null);

       if(file!=null)
            dataSetLocationTextField.setText(file.getPath());
    }
	
	@FXML
    private void handleBrowseGlobalCostLocation(ActionEvent e){
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("This is my file ch");
    	fileChooser.setInitialDirectory(new File(getDataSetLocation()));
    	//fileChooser.getExtensionFilters().add(new ExtensionFilter("target files", ".target", ".csv"));
        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);

       if(file!=null)
            globalCostLocationTextField.setText(file.getPath());
    }
	
	@FXML
	public void handleAbortButtonAction(ActionEvent event) {
	    Stage stage = (Stage) abortBtn.getScene().getWindow();
	    stage.close();
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
					"number of children: " + getNumberOfChildren() + "\n" +
					"number of iterations: " + getNumberOfIterations() + "\n" +
					"seed: " + getSeed());
			if (answer == true){
				ExperimentGUI experiment = new ExperimentGUI();
				experiment.setAlgorithm(getAlgorithm());
				experiment.setDataset(getDataSetLocation());
				experiment.setGlobalCostFunc(getGlobalCostLocation());
				experiment.setLambda(getLocalCostInfluence());
				experiment.setNumChildren(getNumberOfChildren());
				experiment.setNumIterations(getNumberOfIterations());
				experiment.setSeed(getSeed());
				experiment.onProgressDo(percentComplete -> {
					//TODO: set progressbar to the given percentage
				});
				experiment.run();
				
				// TODO: switch to the report window and pass the experiment to the reportWindowController
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
