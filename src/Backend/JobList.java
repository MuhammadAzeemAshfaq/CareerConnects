//package Default;
package Backend;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

import DataBase.DB;


public class JobList 
{
    
    
    private List<JobDescription> jobsd;
    private int numOfJobs;
    

    public JobList()
    {
        jobsd = new ArrayList<>();
        numOfJobs = 0;
    }
    
 
    
    public void addJob(JobDescription jD)
    {
    	this.numOfJobs++;
    	this.jobsd.add(jD);
    }
    //====================================
    public void loadDatafromDB(DB db)
    {
    	db.loadJobs(this);
    	//System.out.println("Total Jobs Loaded :"+this.numOfJobs);
    	
    	/*for(int i=0;i<this.numOfJobs;i++)
		{
			System.out.println("ids:jobbb ;"+this.jobsd.get(i).getJobId());
		}*/
    	
    }
    
    //===================================
    // Method to search for a job by JobID
    public JobDescription SearchJob(int JobID)
    {
    	if(this.numOfJobs==0)
    	{
    		return null;
    	}
    	
    	JobDescription j=null;
    	for(int i=0;i<jobsd.size();i++)
		{
    		if(jobsd.get(i).getJobId()==JobID)
			{
    			//String details=jobs.get(i).toString();
    			//j=new JobDescription(jobsd.get(i).getTitle(),jobsd.get(i).getJobId(),jobsd.get(i).getJobName(),jobsd.get(i).getSkills());
    			j=jobsd.get(i);
    			break;
			}
		}
    	
    	return j;
    }
    
    // Method to search for jobs based on some criteria (e.g., title)
    public void SearchJobs(String criteria) 
    {
        
    }
    
 // Method to edit a job in the database (for persistence)
    public void EditInDatabase(int jobId,String title,String description,List<String> skills,double minSal, double maxSal,LocalDate date,DB db) 
    {
        db.updateJob(jobId,title, description, skills,minSal,maxSal,date);
        int i=0;
        for (JobDescription job : jobsd) {
           	
            if (job.getJobId() == jobId) {
               	jobsd.get(i).EditDescription(title, description, skills,minSal,maxSal,date);
               	break;
            }
            i++;
       }
    }
    
    // Method to edit a job by its ID
    public void EditJob(int JobID)
    {
       
    }
    
    // Method to edit a job in the database (for persistence)
    public void EditInDatabase(int JobID) 
    {
        
    }
    
    // Method to delete a job by its ID from the list
    public void DeleteJob(int JobID)
    {
        
    }
    
    // Method to delete a job from the database (for persistence)
    public void DeleteFromDatabase(int JobID) 
    {
        
    }
    
    // Method to create a new job by JobID
    public void createJob(int JobID) 
    {
        
    }
    
    // Method to add a job to the database
    public void AddsToDatabase(Job job) 
    {
        
    }
    
    // Getter for numOfJobs
    public int getNumOfJobs() 
    {
        return numOfJobs;
    }
    
    // Setter for numOfJobs (if necessary)
    public void setNumOfJobs(int numOfJobs) 
    {
       
    }
    
    public List<JobDescription> getJobList()
    {
    	List<JobDescription> joblist=new ArrayList<>(this.jobsd);
    	
    	return joblist;
    }
    // Method to delete a job from the database (for persistence)
    public void DeleteFromDatabase(int JobID, DB db) 
    {
    	db.deleteJob(JobID);
    	jobsd.removeIf(jD -> jD.getJobId() == JobID);
    }
}
