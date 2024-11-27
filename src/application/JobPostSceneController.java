package application;


import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.Node;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.awt.TextArea;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

import Backend.JobPortal;


public class JobPostSceneController {
	private JobPortal jp;
	private Stage stage;
	 private Scene scene;
	 private Parent root;
	 
	 @FXML
	 private TextField TitleField;
	 @FXML
	 private TextField DescriptionField;
	 @FXML
	 private TextField minSalaryField;
	 @FXML
	 private TextField maxSalaryField;
	 @FXML
	 private TextField dateField;
	 @FXML
	 private TextField SkillsField;
	 
	 @FXML
	 private Button postBtn;
	 
	 
	 
	 public JobPostSceneController()
	 {
		 jp = JobPortal.getInstance();
	 }
	 
	 public void postBtnClicked(ActionEvent event)
	 {
		 String title=TitleField.getText();
		 String desc=DescriptionField.getText();
		 double minSal = 0;
	     double maxSal = 0;
	        try {
	            minSal = Double.parseDouble(minSalaryField.getText());
	        } catch (NumberFormatException e) {
	            // Handle invalid input for min salary (show error, return, etc.)
	        	showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please enter a valid minimum salary.");
	            return;
	        }
	        
	        try {
	            maxSal = Double.parseDouble(maxSalaryField.getText());
	        } catch (NumberFormatException e) {
	            // Handle invalid input for max salary (show error, return, etc.)
	        	showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please enter a valid maximum salary.");
	            return;
	        }

	        // Validate and parse the date field (dateField)
	        LocalDate date = null;
	        try {
	            date = LocalDate.parse(dateField.getText());
	        } catch (DateTimeParseException e) {
	            // Handle invalid date format (show error, return, etc.)
	        	showAlert(Alert.AlertType.ERROR, "Invalid Date", "Please enter a valid date in the format yyyy-MM-dd.");
	            return;
	        }
	        
	        String skills = SkillsField.getText();
	        List<String> skillsList = processSkillsInput(skills);
	        
	        int id=jp.p.getuid();
	        
	        jp.CreateJobPost(id, title, desc, skillsList, minSal, maxSal, date);   
	        
	        showAlert(Alert.AlertType.INFORMATION, "Success", "The job post has been created successfully!");
	        this.jp.updateAllData();
	        try {
				root = FXMLLoader.load(getClass().getResource("Job.fxml"));
				stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }
	 
	 private List<String> processSkillsInput(String skills) {
	        String[] skillsArray = skills.split("\\s*,\\s*"); // Split by commas and trim spaces
	        return Arrays.asList(skillsArray);
	    }
	 
	 private void showAlert(Alert.AlertType alertType, String title, String message) {
		    Alert alert = new Alert(alertType);
		    alert.setTitle(title);
		    alert.setHeaderText(null);
		    alert.setContentText(message);
		    alert.showAndWait();
		}
}
