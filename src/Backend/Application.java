//package Default;
package Backend;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

public class Application
{
    
    // Attributes
    private int applicationID;
    private int jobID;
    private int jobSeekerID;
    private int employeeID;
    private LocalDate date;
    private String resume;
    private String CoverLetter;
    private String contact;
   // private Time time;
    private String approved;

    // Constructor to initialize the application
    public Application(int seekerID,int JobID,int EmployeeID,String contact, String resume, String coverLetter)
    {
    	//this.applicationID=appID;
    	this.jobID=JobID;
    	this.employeeID=EmployeeID;
    	this.jobSeekerID=seekerID;
    	this.resume=resume;
    	this.CoverLetter=coverLetter;
    	this.contact=contact;
    	this.date=LocalDate.now();
       this.contact=contact;
       
        this.approved = "pending"; // Default to not approved
    }

    // Getter and Setter for applicationID
    public int getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(int applicationID) {
        this.applicationID = applicationID;
    }

    // Getter and Setter for jobSeekerID
    public int getJobSeekerID() {
        return jobSeekerID;
    }

    public void setJobSeekerID(int jobSeekerID) {
        this.jobSeekerID = jobSeekerID;
    }

    // Getter and Setter for date
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    // Getter and Setter for time
   /* public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }*/
    public int getJobID() {
        return this.jobID;
    }
    public int getEmpID() {
        return this.employeeID;
    }
    
    public String getcoverletter() {
        return this.CoverLetter;
    }
    public String getCoverletter() {
        return this.CoverLetter;
    }
    public String getresume() {
        return this.resume;
    }
    public String getResume() {
        return this.resume;
    }
    // Getter and Setter for approval status
    public String status() {
        return approved;
    }

    public void setstatus(String st) {
        this.approved = st;
    }
    
    public String getcontact()
    {
    	return this.contact;
    }
    public String getContact()
    {
    	return this.contact;
    }
    // Method to display the application details
    public void applicationDetails() 
    {
        System.out.println("Application ID: " + applicationID);
        System.out.println("Job Seeker ID: " + jobSeekerID);
        System.out.println("Application Date: " + date);
       // System.out.println("Application Time: " + time);
        //System.out.println("Application Approved: " + (approved ? "Yes" : "No"));
    }

    // Method to approve the application
    public void approveApplication() {
        this.approved = "approved";
        System.out.println("Application " + applicationID + " has been approved.");
    }
   
    
    public void rejectApplication()
    {
    	this.approved="Rejected";
    	System.out.println("Application " + applicationID + " has been Rejected.");
    }
}
