//package Default;
package Backend;

import java.util.List;
import java.util.Date;
import java.time.*;

public class JobDescription {
    
    // Attributes
	private int jobId;
	
    private String title;
    private int employeeId;
    private String Description;
    private List<String> skills;
    private double sal_min;
    private double sal_max;
    private LocalDate posting_date;
    private LocalDate closing_date;
    
    // Constructor to initialize the job description
    public JobDescription(String title, String desc,int employeeId, List<String> skills,double min,double max,LocalDate p,LocalDate closeDate) 
    {
        
    	this.title = title;
        this.Description = desc;
        this.skills = skills;
        this.employeeId=employeeId;
        this.sal_max=max;
        this.sal_min=min;
        this.closing_date=closeDate;
        this.posting_date=p;
    }
    
    // Getter and Setter for Title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setJobId(int jobId) 
    {
        this.jobId = jobId;
        
    }
    // Getter and Setter for JobId

    // Getter and Setter for empId
    public int getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(int eId) {
        this.employeeId = eId;
    }
    // Getter and Setter for JobName
   /* public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }*/
    // Method to edit the job description (e.g., updating title, job name, or skills)
    public void EditDescription(String newTitle, String newDesc, List<String> newSkills, double minSal, double maxSal, LocalDate date) 
    {
        this.title = newTitle;
        this.Description = newDesc;
        this.skills = newSkills;
        this.sal_min=minSal;
        this.sal_max=maxSal;
        this.closing_date=date;
        System.out.println("Job description updated.");
    }
    public String getDescription()
    {
    	return this.Description;
    }
    public double getMinSal()
    {
    	return this.sal_min;
    }
    public double getMaxSal()
    {
    	return this.sal_max;
    }
    public LocalDate getPostDate()
    {
    	return this.posting_date;
    }
    public LocalDate getCloseDate()
    {
    	return this.closing_date;
    }
    // Getter and Setter for Skills
    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
    
    // Method to edit the job description (e.g., updating title, job name, or skills)
    public void EditDescription(String newTitle, String newDesc, List<String> newSkills) 
    {
        this.title = newTitle;
        this.Description = newDesc;
        this.skills = newSkills;
        System.out.println("Job description updated.");
    }
    
    // Placeholder for operation2, which could represent an action or process
    public void operation2(String params) 
    {
        System.out.println("Operation2 executed with parameter: " + params);
    }

    // Placeholder for operation3, which could represent another action or process
    public void operation3() 
    {
        System.out.println("Operation3 executed.");
    }
    
    // ToString method to display job details
    @Override
    public String toString()
    {
        return  "\nTitle: " + title + "\nJob Desc: " + this.Description + "\nSkills: " + skills;
    }

	public int getJobId() {
		// TODO Auto-generated method stub
		return jobId;
	}
}
