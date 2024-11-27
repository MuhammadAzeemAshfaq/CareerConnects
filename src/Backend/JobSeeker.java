//package Default;
package Backend;

public class JobSeeker extends User 
{
 public Profile profile; 
JobPortal jPortal;

 public JobSeeker(String Name,String Email,String Contact,String uname,String pass)
 {
	 profile=new JobSeekerProfile(Name,Email,Contact,uname,pass);
 }
 
 void Register(String Name,String Email,String Contact,String uname,String pass) 
 {

 }

 // Method to browse available jobs
 void browseForJob()
 {
     this.jPortal.OpenBrowseJob();
     this.jPortal.DisplaytSearchCriteria("Software Engineer");
 }

 
 void applyForJob(Job job) 
 {
	 if(job!=null)
	 {
	    this.jPortal.SelectJob(job.getJobId());
		this.jPortal.ClickApplyJob(job.getJobId());
		String resume="hy im azeem...";
		String coverLetter="my skills are...";
		
		//this.jPortal.SubmitApplication(this.profile.getUId(),job.getJobId(),job.getDesc().getEmployeeId(),this.profile.getContact(),resume ,coverLetter );
	 }
 }

 // Method to manage the JobSeeker's profile


 // Method to display the JobSeeker's profile
 void displayProfile() 
 {
	// jPortal.selectManageProfile(UserName, Password);
	 
 }
 
 void updateProfile(String Name,String Email,String Contact,String uname,String pass) 
 {
	 this.jPortal.submitProfileUpdate(Name, Email, Contact, uname, pass);
	 //Name,Email,Contact,uname,pass
	 
 }

 // Method to log in the JobSeeker
 boolean login(String uname,String pass) 
 {
	 this.UserName=uname;
	 this.Password=pass;
    return this.jPortal.TryForLogin(uname,pass,"JobSeeker");
     
 }
}
