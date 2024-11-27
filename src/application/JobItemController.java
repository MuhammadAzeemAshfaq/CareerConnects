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
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class JobItemController 
{

	private JobDescription j;
	private JobPortal jp;
	private Stage stage;
	 private Scene scene;
	 private Parent root;
	 
	public JobItemController() 
	{
		jp = JobPortal.getInstance(); // Initialize JobPortal (Example)
		//jp=new JobPortal();
    }
	
	@FXML
	private Label Description;

	@FXML
	private Label JobTitle;

	@FXML
	private Label Salary;

	@FXML
	private Label Skills;

	@FXML
    private Button applybtn;
	
	public void setdata(JobDescription jd) 
	{
		

		this.j=jd;
		
		  JobTitle.setText(jd.getTitle());
		  //Skills.setText("Noting");
		  
		  StringBuilder stringBuilder = new StringBuilder();
		  
		  for(int i=0;i<jd.getSkills().size();i++)
		  {
			 

			  stringBuilder.append(jd.getSkills().get(i));
			  stringBuilder.append(" , ");
			  
			  
		  }
		  Skills.setText(stringBuilder.toString());
		  Salary.setText("Min Sal:"+jd.getMinSal()+" || Max Sal:"+jd.getMaxSal());
		  Description.setText(jd.getDescription());
		 

	}
	
	public void apply(ActionEvent event)
	{
		//System.out.println("job appy clicked :"+this.j.getTitle());
		if(event.getSource()==applybtn)
		{
			System.out.println("job appy clicked :"+this.j.getTitle());
			if(this.jp!=null)
			{
				this.jp.cach1=this.j.getEmployeeId();
				this.jp.cach2=this.j.getJobId();
				
				try {
					
					root = FXMLLoader.load(getClass().getResource("AppyDataInput.fxml"));
					stage = (Stage)((Node)event.getSource()).getScene().getWindow();
					scene = new Scene(root);
					stage.setScene(scene);
					stage.show();
					 
		            
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//jp.SubmitApplication(1,1, "123456", "hy im azeem...", "my skills are...");
			}
		}
	}
	
	 
	

}
