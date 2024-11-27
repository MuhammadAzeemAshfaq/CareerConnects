package application;

import java.io.IOException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Backend.Application;
import Backend.JobDescription;
import Backend.JobPortal;
import Backend.Profile;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

//import java.awt.Button;

//import java.awt.event.ActionEvent;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
//import javafx.fxml.Initializable;
/*import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;*/
//import javafx.fxml.FXML;
//import javafx.stage.Stage;
//import javafx.stage.Window;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
//import javafx.fxml.FXML;
import javafx.stage.Stage;

public class MainSceneJSController
{

	private JobPortal jp;
	private Stage stage;
	 private Scene scene;
	 private Parent root;
	private String search_data=""; 
	 
	    @FXML
	    private Button applcation_btn;
	 
	    @FXML
	    private Button home_btn;

	    @FXML
	    private AnchorPane mainpane;

	    @FXML
	    private Button profile_btn;

	    @FXML
	    private TextField search_bar;

	    @FXML
	    private Button search_btn;

	    @FXML
	    private Button signout_btn;
	    
	    @FXML
	    private AnchorPane ApplicationFrm;

	    @FXML
	    private AnchorPane ProfileForm;
	    
	    @FXML
	    private AnchorPane jobsForm;
	    
	    @FXML
	    private AnchorPane ProfileEditForm;
	    
	    @FXML
	    private VBox JobsLayout;
	    
	    @FXML
	    private VBox applicationLayout;
	    
	    @FXML
	    private Label ContactData;

	    @FXML
	    private Label EmailData;


	    @FXML
	    private Label NameData;

	    @FXML
	    private Label PassData;

	    @FXML
	    private Label UnameData;
	    
	    @FXML
		private Button editbtn;
	    
	    @FXML
	    private Button updatebtn;
	    
	    @FXML
	    private TextField NewContactInput;

	    @FXML
	    private TextField NewEmailInput;

	    @FXML
	    private TextField NewNameinput;

	    @FXML
	    private TextField NewPasswordInput;

	    @FXML
	    private TextField NewUnameInput;
	    
	    
	public MainSceneJSController() 
	{
        //System.out.println("Controller initialized");
        jp = JobPortal.getInstance(); 
        
    }
	
    @FXML
    public void initialize() 
    {
    	
    	JobsLayout.getChildren().clear();
    	applicationLayout.getChildren().clear();
    	
    	if(JobsLayout==null)
        {
        	System.out.println("joblayourt is null");
        }
        List<JobDescription> jb=new ArrayList<>(this.jp.getJobs());
        
        
        for(int i=0;i<jb.size();i++)
        {
        	if(search_data.length()==0)
        	{
        		// System.out.println("normal jobsssssssssss");
		        	FXMLLoader fxmlloader=new FXMLLoader();
		        	fxmlloader.setLocation(getClass().getResource("JobItem.fxml"));
	     
	        	
	        	try {
					HBox hbox=fxmlloader.load();
					JobItemController jic=fxmlloader.getController();
					jic.setdata(jb.get(i));
					
					
					
					JobsLayout.getChildren().add(hbox);  
					} 
		        	catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        	}
        	else if(search_data.length()>0 && jb.get(i).getTitle().equals(search_data))
        	{
        		//System.out.println("searched jobsssssssssss");
		        	FXMLLoader fxmlloader=new FXMLLoader();
		        	fxmlloader.setLocation(getClass().getResource("JobItem.fxml"));
	     
	        	
	        	try {
					HBox hbox=fxmlloader.load();
					JobItemController jic=fxmlloader.getController();
					jic.setdata(jb.get(i));
					
					
					
					JobsLayout.getChildren().add(hbox);  
					} 
		        	catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        	}
	     }
        
        if(applicationLayout==null)
        {
        	System.out.println("applicationlayourt is null");
        }
        List<Application> apli= new ArrayList<>(this.jp.NavigateApplicationStatus());
        
        for(int i=0;i<apli.size();i++)
        {
        	FXMLLoader fxmlloader=new FXMLLoader();
        	fxmlloader.setLocation(getClass().getResource("ApplicationItem.fxml"));
     
        	
        	try {
				HBox hbox=fxmlloader.load();
				ApplicationItemController aic=fxmlloader.getController();
				aic.setdata(apli.get(i));
				
				
				applicationLayout.getChildren().add(hbox);  
			} 
        	catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        	
    }
    
	//===================Switching=============
	public void switchscreen(ActionEvent event)
	{
		if(event.getSource()==profile_btn)
		{
			ProfileForm.setVisible(true);
			ApplicationFrm.setVisible(false);
			jobsForm.setVisible(false);
			ProfileEditForm.setVisible(false);
			
			this.jp.updateAllData();
			if(this.jp.selectManageProfile()!=null)
			{
			this.NameData.setText(this.jp.selectManageProfile().getName());
			this.ContactData.setText(this.jp.selectManageProfile().getContact());
			this.EmailData.setText(this.jp.selectManageProfile().getEmail());
			this.PassData.setText(this.jp.selectManageProfile().getPassword());
			this.UnameData.setText(this.jp.selectManageProfile().getuname());
			
			}
			
			
			
		}
		else if(event.getSource()==applcation_btn)
		{
			this.jp.updateAllData();

			ProfileForm.setVisible(false);
			ApplicationFrm.setVisible(true);
			jobsForm.setVisible(false);
			ProfileEditForm.setVisible(false);
			
		}
		else if(event.getSource()==home_btn)
		{
			this.jp.updateAllData();

			ProfileForm.setVisible(false);
			ApplicationFrm.setVisible(false);
			ProfileEditForm.setVisible(false);
			jobsForm.setVisible(true);
			
			search_data=""; 
			this.search_bar.clear();
			this.initialize();
		}
		
		
		
	}
	
	//=================Edit Profile page=========
	@FXML
	public void Editprofilebtn_clicked(ActionEvent event)
	{
		
		System.out.println("profile edit clicked successfully");
		if(event.getSource()==editbtn)
		{
			//this.jp.updateAllData();

			ProfileForm.setVisible(false);
			ApplicationFrm.setVisible(false);
			jobsForm.setVisible(false);
			ProfileEditForm.setVisible(true);
			
		}
	}
	//=========================PROFILE UPDATE==================
	@FXML
	public void Updateprofilebtn_clicked(ActionEvent event)
	{
		
		//System.out.println("profile update clicked successfully");
		if(event.getSource()==updatebtn)
		{
			
			String nme=this.NewNameinput.getText();
			String contct=this.NewContactInput.getText();
			String emal=this.NewEmailInput.getText();
			String unme=this.NewUnameInput.getText();
			String pas=this.NewPasswordInput.getText();
			
			for(Profile profile:jp.getAllProfiles())
			 {
				 if(profile.getuname().equals(unme))
				 {
					 showAlert(Alert.AlertType.ERROR, "Username Exists", "The username already exists. Please choose another.");
					 
			            return;
				 }
			 }
			
			for(Profile profile:jp.getAllProfiles())
			 {
				 if(profile.getEmail().equals(emal))
				 {
					 showAlert(Alert.AlertType.ERROR, "Email Exists", "The email is already registered. Please choose another.");
					 System.out.println("Email already exist");
			            return;
				 }
			 }
			
			this.jp.submitProfileUpdate(nme, emal, contct, unme, pas);
			
			//showAlert(Alert.AlertType.INFORMATION,"Success","Profile updated successful");
			
			this.NewNameinput.clear();
			this.NewContactInput.clear();
			NewEmailInput.clear();
			NewUnameInput.clear();
			NewPasswordInput.clear();
			
			this.jp.updateAllData();

			ProfileForm.setVisible(true);
			ApplicationFrm.setVisible(false);
			jobsForm.setVisible(false);
			ProfileEditForm.setVisible(false);
			
		}
	}
	//========================SIGN-OUT==============

	 public void signoutbtn_clicked(ActionEvent event)
	 {
		 //System.out.println("signout clicked successfully");
		 
		 
		 
		 if(event.getSource()==signout_btn)
			{
				//this.jp.updateAllData();

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
	@FXML
	public void profilebtn_clicked(ActionEvent event)
	{
		
		//System.out.println("profile clicked successfully");
	}
	@FXML
	public void homebtn_clicked(ActionEvent event) 
	{
		//System.out.println("home clicked successfully");
    }
	@FXML
	public void applicatiobtn_clicked(ActionEvent event) 
	{
		//System.out.println("application clicked successfully");
    }	
	
 	@FXML
	 void searchbtn_clicked(ActionEvent event)
	 {
		
			if(event.getSource()==search_btn)
			{
				//System.out.println("search clicked successfully");
				String search_criteria=this.search_bar.getText();
				//System.out.println("search Data :"+search_criteria);
				search_data=search_criteria;
				//JobsLayout.getChildren().clear();
				this.initialize();
			}
		
     }
 	
 	private void showAlert(Alert.AlertType alerttype,String title,String message)
	{
	   	Alert alert=new Alert(alerttype);
	   	alert.setTitle(title);
	   	alert.setHeaderText(null);
	   	alert.setContentText(message);
	   	alert.showAndWait();
	}
	
	

}
