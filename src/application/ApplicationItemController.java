package application;

//import java.text.SimpleDateFormat;

import Backend.Application;
import Backend.JobDescription;
import Backend.JobPortal;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ApplicationItemController 
{
	private JobPortal jp;
	
    @FXML
    private Label ApplicationIDData;

    @FXML
    private Label AppliedDateData;

    @FXML
    private Label JobIDData;

    @FXML
    private Label StatusData;
    
    public ApplicationItemController()
    {
    jp = JobPortal.getInstance();
    }
    
    
	    public void setdata(Application a) 
		{
	    	
	    	
	    	StringBuilder stringBuilder = new StringBuilder();
	    	 stringBuilder.append(jp.getJobName(a.getJobID())+" ");
	    	
	    	this.ApplicationIDData.setText(stringBuilder.toString());
	    	
	    	//str = Integer.toString(a.getJobID());
	    	stringBuilder = new StringBuilder();
	    	stringBuilder.append(a.getJobID()+" ");
	    	
	    	this.JobIDData.setText(stringBuilder.toString());
	    	
	    	stringBuilder = new StringBuilder();
	    	stringBuilder.append(a.getDate()+" ");
	    	
	    	this.StatusData.setText(a.status());
	    	
	    	if(a.status().equals("Rejected"))
	    	{
	    	StatusData.setStyle("-fx-text-fill: red;");
	    	}
	    	else if(a.status().equals("Accepted"))
	    	{
	    		StatusData.setStyle("-fx-text-fill: green;");
	    	}
	    	
	    	this.AppliedDateData.setText(stringBuilder.toString());
			
			  
			 

		}
	    
}
