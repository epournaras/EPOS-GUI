/**
 * 
 */
package controller;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @author melikaayoughi
 *
 */
public class MainApplication extends Application {
	
	private Stage primaryStage;
	private AnchorPane configurationWindow;
	private AnchorPane reportWindow;
	
	@Override
	public void start(Stage primaryStage) /*throws Exception */{
		
		try {
			this.primaryStage = primaryStage;
	        this.primaryStage.setTitle("Configuration");
	        showConfigurationWindow();
//	        showReportWindow();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	/**
     * Shows the configuration window
     */
    public void showConfigurationWindow() {
        try {
            // Load configuration from fxml file.
            FXMLLoader configurationWindowloader = new FXMLLoader();
            configurationWindowloader.setLocation(MainApplication.class.getResource("view/configurationWindow.fxml"));
            configurationWindow = (AnchorPane) configurationWindowloader.load();
            
            // Show the scene containing the configuration window.
            Scene scene = new Scene(configurationWindow);
            primaryStage.setScene(scene);
            primaryStage.show();      
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Shows the report window
     */
    private void showReportWindow(){
    	try {
    		// Load configuration from fxml file.
            FXMLLoader reportWindowloader = new FXMLLoader();
            reportWindowloader.setLocation(MainApplication.class.getResource("view/reportWindow.fxml"));
            reportWindow = (AnchorPane) reportWindowloader.load();
            
            // Show the scene containing the configuration window.
            Scene scene = new Scene(reportWindow);
            primaryStage.setScene(scene);
            primaryStage.show();
            
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
	public static void main(String[] args) {
		try {
			launch(args);
		} catch (Exception e) {
			e.printStackTrace();
		}       
    }
}
