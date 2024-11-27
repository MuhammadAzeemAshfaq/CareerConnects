package application;

import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.Node;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Backend.JobDescription;
import Backend.JobPortal;

public class EditJobSceneController {
	private Stage stage;
	 private Scene scene;
	 private Parent root;
	 private JobDescription job;
	 
	private JobPortal jp;
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
	
	
	public EditJobSceneController()
	{
		jp=JobPortal.getInstance();
	}
	
	public void setJobDetails(JobDescription job) {
        this.job = job;
        this.TitleField.setText(job.getTitle());
        this.DescriptionField.setText(job.getDescription());
        this.minSalaryField.setText(String.valueOf(job.getMinSal()));
        this.maxSalaryField.setText(String.valueOf(job.getMaxSal()));
        this.dateField.setText(String.valueOf(job.getCloseDate()));
        this.SkillsField.setText(String.join(", ", job.getSkills()));
    }
	public void updateBtnClicked(ActionEvent event)
	{
		String title=TitleField.getText();
		 String desc=DescriptionField.getText();
		 String minSalTxt=minSalaryField.getText().trim();
		 String maxSalTxt=maxSalaryField.getText().trim();
		 String dateTxt=dateField.getText().trim();
		 
		 if(title.isEmpty() || desc.isEmpty() || minSalTxt.isEmpty() || maxSalTxt.isEmpty() ||dateTxt.isEmpty())
		 {
			 showAlert(Alert.AlertType.ERROR, "Invalid ", "Please fill all fields");
			 return;
		 }
		 
		 double minSal = 0;
	     double maxSal = 0;
	        try {
	            minSal = Double.parseDouble(minSalTxt);
	        } catch (NumberFormatException e) {
	        	showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please enter a valid minimum salary.");
	            return;
	        }
	        
	        try {
	            maxSal = Double.parseDouble(maxSalTxt);
	        } catch (NumberFormatException e) {
	        	showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please enter a valid maximum salary.");
	            return;
	        }
	        
	        if (minSal > maxSal) {
	            showAlert(Alert.AlertType.ERROR, "Invalid Input", "Minimum salary cannot be greater than maximum salary.");
	            return;
	        }

	        LocalDate date = null;
	        try {
	            date = LocalDate.parse(dateTxt);
	        } catch (DateTimeParseException e) {
	        	showAlert(Alert.AlertType.ERROR, "Invalid Date", "Please enter a valid date in the format yyyy-MM-dd.");
	            return;
	        }
	        
	        if (date.isBefore(LocalDate.now())) {
	            showAlert(Alert.AlertType.ERROR, "Invalid Date", "Closing date cannot be in the past.");
	            return;
	        }
	        
	        String skills = SkillsField.getText();
	        List<String> skillsList;
	        if(!skills.isEmpty())
	        {
	        	skillsList = processSkillsInput(skills);
	        	if (skillsList.isEmpty()) {
	        	    skillsList.add("No skills required");
	        	}
	        }
	        else {
	        	skillsList=new ArrayList<>();
	        	skillsList.add("No skills required");
	        }
	        
		jp.EditJobDetails(job.getJobId(), title, desc, skillsList, minSal, maxSal, date);
	        

        showAlert(Alert.AlertType.INFORMATION, "Success", "The job post has been updated successfully!");

        try {
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("Job.fxml"));
            Parent root = loader.load();
            JobSceneController jobController = loader.getController();
            jobController.refreshTable();
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
		 List<String> skillsArray= Arrays.stream(skills.split("\\s*,\\s*")).filter(skill -> !skill.isBlank()).toList();
	        return skillsArray;
	    }
	
	@FXML
    void GoBack(ActionEvent event)
    {
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
	
	private void showAlert(Alert.AlertType alertType, String title, String message) {
	    Alert alert = new Alert(alertType);
	    alert.setTitle(title);
	    alert.setHeaderText(null);
	    alert.setContentText(message);
	    alert.showAndWait();
	}
}
