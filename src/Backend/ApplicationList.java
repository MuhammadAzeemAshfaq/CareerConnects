//spackage Default;
package Backend;

import java.util.ArrayList;
import java.util.List;

import DataBase.DB;

public class ApplicationList 
{
    
    private int numApps; 
   private List<Application> appList; 

    
    public ApplicationList() 
    {
        this.numApps = 0;  
       this.appList = new ArrayList<>();
    }

   
    public int getNumApps() 
    {
        return numApps;
    }

    // Getter for apps (list of applications)
    public List<Application> getApps() {
        return appList;
    }

    // Function to retrieve all applications
    public void RetrieveApplicationsfromDB(DB db) 
    {
       db.loadApplications(this);
       //System.out.println("Total Applications Loaded :"+this.numApps);
       
      /* for(int i=0;i<this.numApps;i++)
		{
			System.out.println("ids:appli:"+this.appList.get(i).getApplicationID());
		}*/
       
    }

    // Function to retrieve a specific application by its application ID
    public List<Application> RetrieveApplications(Profile pro) 
    {
    	List<Application> ap= new ArrayList<>();; 
    	//System.out.println("User applications:");
        for(int i=0;i<this.numApps;i++)
        {
     	   if(this.appList.get(i).getJobSeekerID()==pro.getUId()) 
     	   {
     		 // System.out.println("application id:"+appList.get(i).getApplicationID());
     		   ap.add(appList.get(i));
     	   }
        }
        
        return ap;
    }
    
    public void addApplication_toList(Application ap) 
    {
        this.appList.add(ap);
        this.numApps++;
    }

    // Function to create and add a new application to the list
    public void CreateApplication(Application application,DB db) 
    {
       this.numApps++;
       
       //------assign recent id--
       //application.setApplicationID("123");
       
       db.addApplication(application);
       int id=db.getApplicationId(application);
       application.setApplicationID(id);
       //------------------------
       this.appList.add(application);
       
       System.out.println("Application added to db/list successfully");
    }

    public boolean alreadyapplied(int jobid,int uid)
    {
    	for(int i=0;i<this.appList.size();i++)
    	{
    		if(appList.get(i).getJobID()==jobid && appList.get(i).getJobSeekerID()==uid)
    		{
    			return true;
    		}
    	}
    	return false;
    	
    }
    // Function to update the list (can be used for any additional updates to applications in the list)
    public void UpdateList() 
    {
        
    }

    // Function to simulate updating the applications in a database
    public void UpdateDatabase()
    {
       
    }

    // Function to delete an application from the database
    public void DeleteFromDatabase(Application application) 
    {
       
    }
    
    public void ApproveApplication(DB db,int appId)
    {
    	for(int i=0;i<appList.size();i++)
    	{
    		if(appList.get(i).getApplicationID()==appId)
			{
				appList.get(i).approveApplication();
				break;
			}
    	}
    	db.updateApplication(true, appId);
    	
    }
    
    public void RejectApplication(DB db,int appId)
    {
    	for(int i=0;i<appList.size();i++)
    	{
    		if(appList.get(i).getApplicationID()==appId)
			{
				appList.get(i).rejectApplication();
				break;
			}
    	}
    	db.updateApplication(false, appId);
    }
}
