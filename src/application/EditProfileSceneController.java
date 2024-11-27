package application;

import javafx.fxml.FXML;


import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.Node;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import Backend.JobDescription;
import Backend.JobPortal;
import Backend.Profile;

public class EditProfileSceneController {
	private JobPortal jp;
	private String type;
	
	private Stage stage;
	 private Scene scene;
	 private Parent root;
	 
	 @FXML
	 private TextField nameField;
	 @FXML
	 private TextField contactField;
	 @FXML
	 private TextField usernameField;
	 @FXML
	 private TextField emailField;
	 @FXML
	 private TextField currentPassField;
	 @FXML
	 private TextField newPassField;
	 
	 @FXML
	 private Button SubmitBtn;
	 @FXML private CheckBox changePassword;
	 
	 
	 public EditProfileSceneController()
	 {
		jp=JobPortal.getInstance();
	 }

	 public void setProfileDetails() 
	 {
		 this.nameField.setText(jp.getLogedInEmployer().getName());
	     this.contactField.setText(jp.getLogedInEmployer().getContact());
	     this.usernameField.setText(jp.getLogedInEmployer().getuname());
	     this.emailField.setText(jp.getLogedInEmployer().getEmail());
	 }
	 
	 @FXML
	 public void submitChange(ActionEvent event)
	 {		 
		 String username=usernameField.getText();
		 String name=nameField.getText();
		 String contact=contactField.getText();
		 String email=emailField.getText();
		 
		 if(username.isEmpty() || name.isEmpty() || contact.isEmpty() || email.isEmpty())
		 {
			 showAlert(Alert.AlertType.ERROR, "Invalid ", "Please fill all fields");
			 return;
		 }
		 
		 for(Profile profile:jp.getAllProfiles())
		 {
			 if(profile.getuname().equals(username) && !profile.equals(jp.getLogedInEmployer()))
			 {
				 showAlert(Alert.AlertType.ERROR, "Username Exists", "The username already exists. Please choose another.");
		            return;
			 }
		 }
		 
		 for(Profile profile:jp.getAllProfiles())
		 {
			 if(profile.getEmail().equals(email) && !profile.equals(jp.getLogedInEmployer()))
			 {
				 showAlert(Alert.AlertType.ERROR, "Email Exists", "The email is already registered. Please choose another.");
		            return;
			 }
		 }
		 if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
			    showAlert(Alert.AlertType.ERROR, "Invalid Email", 
			              "Please enter a valid email address.");
			    return;
			}
		 if(changePassword.isSelected())
		 {
			 String currentPassword = currentPassField.getText();
		     String newPassword = newPassField.getText();
			 if(!currentPassword.equals(jp.getLogedInEmployer().getPassword()))
			 {
				 showAlert(Alert.AlertType.ERROR, "Incorrect Password", "The current password is incorrect.");
				 return;
			 }
			 
			 if(newPassword.equals(jp.getLogedInEmployer().getPassword()))
			 {
				 showAlert(Alert.AlertType.ERROR, "Invalid Password", "The new password cannot be old password.");
				 return;
			 }
			 
			 if(newPassword.isEmpty() || currentPassword.isEmpty())
			 {
				 showAlert(Alert.AlertType.ERROR, "Invalid Password", "Please enter correct password");
				 return;
			 }
			 jp.submitProfileUpdate2(name, email, contact, username, newPassword);
			 this.jp.updateAllData();

		 }
		 else
		 {
			 jp.submitProfileUpdate2(name, email, contact, username, jp.getLogedInEmployer().getPassword());
			 this.jp.updateAllData();

		 }
		 
		 showAlert(Alert.AlertType.INFORMATION,"Success","Profile is updated");
		 
		 try {
				root = FXMLLoader.load(getClass().getResource("Employer.fxml"));
				stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
	 }
	 
	 @FXML
	 public void enableField(ActionEvent event)
	 {
		 if(changePassword.isSelected())
		 {
			 currentPassField.setDisable(false);
			 newPassField.setDisable(false);
		 }
		 else
		 {
			 currentPassField.setDisable(true);
			 newPassField.setDisable(true);
			 
			 currentPassField.clear();
			 newPassField.clear();
		 }
	 }
	 
	 @FXML
	    void GoBack(ActionEvent event)
	    {
	    	try {
				root = FXMLLoader.load(getClass().getResource("Employer.fxml"));
				stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	 private void showAlert(Alert.AlertType type, String title, String message) {
	        Alert alert = new Alert(type);
	        alert.setTitle(title);
	        alert.setHeaderText(null);
	        alert.setContentText(message);
	        alert.showAndWait();
	    }
}
