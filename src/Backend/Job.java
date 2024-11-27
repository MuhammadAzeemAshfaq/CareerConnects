//package Default;
package Backend;

import java.time.*;
import java.util.Date;
import java.util.List;

public class Job 
{
    
    // Attributes
    private static int jobId;
    
    private JobDescription desc; // JobDescription is an instance of the JobDescription class
    
    // Constructor to initialize the job with a jobId and a JobDescription
    public Job(String title,String description,int id,List<String> skills,double minSal, double maxSal,LocalDate postDate,LocalDate closeDate)
    {
    	jobId++;
        desc=new JobDescription(title, description, id, skills, minSal, maxSal, postDate, closeDate);
        desc.setJobId(jobId);
    }

    // Getter for jobId
    public int getJobId() {
        return jobId;
    }

    // Setter for jobId
    public void setJobId(int jobId) 
    {
        this.jobId = jobId;
        this.desc.setJobId(jobId);
    }

    // Getter for desc (JobDescription)
    public JobDescription getDesc() {
        return desc;
    }

    // Setter for desc (JobDescription)
    public void setDesc(JobDescription desc) {
        this.desc = desc;
    }

    // Function to retrieve job details
    public void RetrieveJobDetails() {
        System.out.println("Job ID: " + jobId);
        System.out.println("Job Description: ");
        System.out.println(desc);  // Assuming JobDescription class has a toString() method
    }

    // Function to edit job details (e.g., update the JobDescription)
    public void EditJobDetails(JobDescription newDesc) {
        this.desc = newDesc;
        System.out.println("Job details updated.");
    }
}
