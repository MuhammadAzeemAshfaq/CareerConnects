package application;

import java.io.IOException;

import Backend.JobDescription;
import Backend.JobPortal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class AppyDataInputController 
{
	private JobPortal jp;
	JobDescription j;
	private Stage stage;
	 private Scene scene;
	 private Parent root;
	 
    @FXML
    private TextArea coverletter;

    @FXML
    private TextArea resume;

    @FXML
    private Button submitbtn;
    @FXML
    private Button Back_btn;
    
    public AppyDataInputController () 
	{
        
        jp = JobPortal.getInstance(); 
        //this.j=j;
        
    }
    
    public void submit()
    {
    	
    	
    	
    }
    
    public void submit_clicked(ActionEvent event)
    {
    	if(event.getSource()==submitbtn)
		{
    		
    		String Coverletter2=coverletter.getText();
    		String resume2=resume.getText();
    		
    		if(Coverletter2.length()>0&&resume2.length()>1)
    		{
    			
    			//System.out.println("id :"+jp.cach2);
    			//System.out.println("id :"+jp.cach1);
    			
    			//System.out.println("Job apply successfully");
        		this.jp.SubmitApplication(jp.cach2, jp.cach1, resume2, Coverletter2);
        		this.jp.cach1=0;
        		this.jp.cach2=0;
        		//System.out.println("Job apply successfully");
        		
        		try {
					
					root = FXMLLoader.load(getClass().getResource("MainSceneJS.fxml"));
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
    	
    	
    }
    
    public void back_clicked(ActionEvent event)
    {
    	
    	try {
			
			root = FXMLLoader.load(getClass().getResource("MainSceneJS.fxml"));
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
