package application;

import javafx.fxml.FXML;

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

import javax.imageio.plugins.jpeg.JPEGImageReadParam;

import Backend.JobPortal;

public class EmployeeSceneController{
	
	private Stage stage;
	 private Scene scene;
	 private Parent root;
	 
	private JobPortal jp;
	@FXML
	private Button seeJobBtn;
	
	@FXML
    private Button editProfile_btn;

    @FXML
    private Button SignOut_btn;
	   
	@FXML
	private Button seeApplicationBtn;
	@FXML
	private Text NameField;
	@FXML
	private Text NameField2;
	@FXML
	private Text EmailField;
	@FXML
	private Text UsernameField;
	@FXML
	private Text ContactField;
	
	public EmployeeSceneController()
	{
		jp = JobPortal.getInstance();
	}
	
	 @FXML
	    public void initialize() {
	        setEmployerDetails();
	    }
	
	public void setEmployerDetails()
	{
		this.NameField.setText(jp.p.getName());
		this.NameField2.setText(jp.p.getName());
		this.UsernameField.setText(jp.p.getuname());
		this.EmailField.setText(jp.p.getEmail());
		this.ContactField.setText(jp.p.getContact());
		
	}
	@FXML
	public void editProfileBtnClicked(ActionEvent event)
	{
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("EditProfile.fxml"));
	        Parent root = loader.load();
	        EditProfileSceneController controller = loader.getController();
	        controller.setProfileDetails();	
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
	public void getJobs(ActionEvent event)
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
	public void getApplicants(ActionEvent event)
	{
		try {
	   		FXMLLoader loader = new FXMLLoader(getClass().getResource("AcceptedApplicants.fxml"));
	        Parent root = loader.load();
	        AcceptedApplicantsSceneController controller = loader.getController();
	        controller.setJobDetails(jp.getAllJobs());
	        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
	        scene = new Scene(root);
	        stage.setScene(scene);
	        stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void SignOut_clicked(ActionEvent event)
	{
		
		try {
			root = FXMLLoader.load(getClass().getResource("Login.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
