//package Default;
package Backend;

import java.util.*;

import DataBase.DB;

import java.sql.Time;
import java.time.*;

//import Default.DB;

public class JobPortal
{

    
   // List<User> users;
   // List<Job> jobs;
    //List<Application> applications;
	private static JobPortal instance = null;
	JobList jl;
	ApplicationList appList;
	ProfileList proL;
	User e;
	DB db;
	public Profile p;
	public int cach1;
	public int cach2;
	public String cach3;
	public JobPortal()
	{
		db=new DB();
		jl=new JobList();
		appList=new ApplicationList();
		proL=new ProfileList();
		
		proL.loaddatafromDB(db);
		appList.RetrieveApplicationsfromDB(db);
		jl.loadDatafromDB(db);
	}
	
// Public method to provide access to the singleton instance
	  public static JobPortal getInstance() 
	  {
	      if (instance == null) {
	          instance = new JobPortal();
	      }
	      return instance;
	  }
	  
//============================
	  public void updateAllData()
	  {
		  appList=new ApplicationList();
			proL=new ProfileList();
			jl=new JobList();
			
		  proL.loaddatafromDB(db);
			appList.RetrieveApplicationsfromDB(db);
			jl.loadDatafromDB(db);
	  }
//=====Methods related to job browsing and application submission=====
	public void RegisterEmployer(String Name,String Email,String Contact,String uname,String pass )
	 {
		//if(!(proL.isProfileAvailable(uname,pass,"Employee")))
		//{
			Employee emp=new Employee(Name,Email,Contact,uname,pass);
			 db.addEmployer(Name,Email,Contact,uname,pass);
			 
			 int id=db.getEmployerId(emp);
			 emp.p.setuid(id);
			 proL.addProfile(emp.p);
		//}
		//else
		//{
			//System.out.println("Username already exists!");
		//}
	 }
	
	public void RegisterJobSeeker(String Name,String Email,String Contact,String uname,String pass )
	 {
		//if(!(proL.isProfileAvailable(uname,pass,"JobSeeker")))
		//{
			JobSeeker jk=new JobSeeker(Name,Email,Contact,uname,pass);
			db.addJobSeeker(Name,Email,Contact,uname,pass);
			
			int id=db.getJobSeekerId(jk);
			jk.profile.setuid(id);
			//System.out.println("user id:"+id);
			
			 proL.addProfile(jk.profile);
		//}
		//else
		//{
		//	System.out.println("Username already exists!");
		//}
	 }
    void OpenBrowseJob() 
    {
        // Logic to open the job browsing interface
    	System.out.println("Chose specific Filter");

    }

    void DisplaytSearchCriteria(String criteria)
    {
        // Logic to display search criteria
    }

    void DisplaytallJobs() 
    {
        // Logic to display all available jobs
    }

    void SelectJob(int JobID) 
    {
        
    	JobDescription j=jl.SearchJob(JobID);
    	if(j==null)
    	{
    		System.out.println("No specific job posted yet");
    	}
    	else
    	{
    	String details=j.toString();
    	}
    	
    	
    	
    }

   public void ClickApplyJob(int JobID) 
    {
    	
    	System.out.println("Enter Details(Cover letter, resume etc) :");
    }

   public void SubmitApplication(int JobID,int EmployeeID, String resume, String coverLetter) 
    {
	  
	  if(!this.appList.alreadyapplied(JobID, this.p.getuid()))
	  {
        Application application=new Application(this.p.getuid(),JobID,EmployeeID,this.p.getContact(),resume,coverLetter);
        appList.CreateApplication(application,this.db);
        db.incrementNoOfApp(this.p.getuname(), p.getPassword());
	  }
	  else
	  {
		  System.out.println("already applied");
	  }
        
    }

    boolean validateData() 
    {
        // Logic to validate the application data
        return true; // Return true if valid
    }

    void createApplication(String resume, String coverLetter) 
    {
        // Logic to create a new application
    }
    
   
//==========================================================================
    // Methods related to profile management
    public Profile selectManageProfile()
    {
    	//System.out.println("Profile details :");
    	//Profile p=this.proL.RetrieveProfile(Uname);
    	
    	if(p==null)
    	{
    		System.out.println("Profile not available :");
    		
    	}
    	else
    	{
    		// System.out.println(p.getName());
    		// System.out.println(p.getUId());
    		 //System.out.println(p.getEmail());
    		 //System.out.println(p.getContact());
    		 //System.out.println(p.get_num());
    	}
    	return p;
        
    }

    void editProfile() 
    {
        // Logic to edit user profile
    }

    public void submitProfileUpdate(String Name,String Email,String Contact,String uname,String pass)
    {
        // Logic to submit updated profile data
    	if(Name.length()==0)
    	{
    		
    		Name=this.p.getName();
    	}
    	if(Email.length()==0)
    	{
    		Email=this.p.getEmail();
    		
    	}
    	if(Contact.length()==0)
    	{
    		Contact=this.p.getContact();
    	}
    	if(uname.length()==0)
    	{
    		uname=this.p.getuname();
    	}
    	if(pass.length()==0)
    	{
    		pass=this.p.getPassword();
    	}
    	
    	
    	db.updateJobSeeker(this.p.getuname(),this.p.getPassword(), uname, pass, Name, Email, Contact);
    	
    	
    	this.proL.EditProfile(Name, Email, Contact, uname, pass,p.getuname(),p.getPassword());
    	
    	updateAllData();
    	
    	p=this.proL.getProfile(uname, pass,this.p.gettype());
    }
    
    public void submitProfileUpdate2(String Name,String Email,String Contact,String uname,String pass)
    {
        // Logic to submit updated profile data
       	int id=db.getEmployerId(this.getLogedInEmployer().getuname());
       	this.proL.EditProfile(Name, Email, Contact, uname, pass,this.getLogedInEmployer().getuname(),this.getLogedInEmployer().getPassword());
       	db.updateProfile(id,Name, Email, Contact, uname, pass);
    }
    
    void addProfile(Profile prf)
    {
    	this.proL.addProfile(prf);
        
    }
    // Methods related to application management
    public List<Application> RequestApplicationLists() 
    {
    	return appList.getApps();
    }
  //====================================================== 
    // Methods related to application status
   public List<Application> NavigateApplicationStatus() 
    {
    	return this.appList.RetrieveApplications(p);
    }

    void SelectApplicationForStatus(int ApplicationID) 
    {
        // Logic to select an application and view its status
    }
//====================================
    // Methods related to login
    public boolean  TryForLogin(String uname,String pass,String type) 
    {
    	 if(this.proL.isProfileAvailable(uname, pass,type))
    	 {
    		 p=this.proL.getProfile(uname, pass,type);
    		 return true;
    	 }
    	 else
    	 {
    		 return false;
    	 }
    	 
    }

    void provideDetails(String userType, String email, String password) 
    {
        // Logic to provide login details for validation
    }

    // Methods related to job posting
   /* void CreateJobPost(Employee e,String title,String description,List<String> skills,double minSal, double maxSal,LocalDate closeDate)
    {
        // Logic to create a new job post
    	int id=db.getEmployerId(e);
    	Job job=new Job(title, description, id, skills, minSal, maxSal, LocalDate.now(), closeDate);
    	e.p.getuname();
    }*/
    
    public void CreateJobPost(int empId,String title,String description,List<String> skills,double minSal, double maxSal,LocalDate date)
    {
        // Logic to create a new job post
    	//int id=db.getEmployerId(e);
    	Job job=new Job(title,description, empId, skills, minSal, maxSal, LocalDate.now(), date);
    	db.addJob(job);
    	//e.p.getuname();
    	
    }

    void provideDetails(String title, String description) 
    {
        // Logic to input job details such as title and description
    }

    void SelectType() 
    {
        // Logic to select the type of job (e.g., full-time, part-time)
    }

    void AddSalary()
    {
        // Logic to input salary details
    }

    boolean ValidateData() 
    {
        // Logic to validate job data
        return true; // Return true if valid
    }

    void CreateJob(String title, String description, String type, String salary) 
    {
        // Logic to create a job with the provided details
    }

    // Methods related to job management
    void RequestJobLists()
    {
        // Logic to fetch and display the list of jobs
    }
    public void EditJobDetails(int jobId,String title,String description,List<String> skills,double minSal, double maxSal,LocalDate date) 
    {
        jl.EditInDatabase(jobId, title, description, skills, minSal, maxSal, date,db);
    }
    void EditJobDetails(String desc) 
    {
        // Logic to edit job details
    }

    void SubmitEditedJob(String job) 
    {
        // Logic to submit updated job details
    }

    void DeleteJob(String job) 
    {
        // Logic to delete a specific job
    }



    void SelectApplication() 
    {
        // Logic to select a specific application for review
    }

    void ApproveApplication() 
    {
        // Logic to approve an application
    }

    void RejectAplication() 
    {
        // Logic to reject an application
    }

    // Methods related to messaging and applicant management
    void getApplicantList() 
    {
        // Logic to retrieve the list of applicants
    }

    void SelectApplicant(int id) 
    {
        // Logic to select an applicant by their ID
    }

    void SendMessage(String text)
    {
        // Logic to send a message to an applicant
    }
    
    public String getJobName(int jid)
    {
    	for(int i=0;i<this.jl.getJobList().size();i++)
    	{
    		if(this.jl.getJobList().get(i).getJobId()==jid)
    		{
    			return this.jl.getJobList().get(i).getTitle();
    		}
    	}
    	
    	return "uknown";
    }
    
    public Profile getUserprofile(int uid)
    {
    	for(int i=0;i<this.proL.getAllProfiles().size();i++)
    	{
    		if(this.proL.getAllProfiles().get(i).getuid()==uid)
    		{
    			return this.proL.getAllProfiles().get(i);
    		}
    	}
    	
    	return null;
    }
    
    public List<JobDescription> getJobs()
    {
    	
    	
    	return this.jl.getJobList();
    }
    public List<JobDescription> getAllJobs()
    {
    	return jl.getJobList();
    }
    public Profile getLogedInEmployer()
    {
    	return this.p;
    }
    public List<Profile> getAllProfiles()
    {
    	return this.proL.getAllProfiles();
    }
    public ProfileList getProfileList()
    {
    	return this.proL;
    }

    public void ApproveApplication(int appId) 
    {
        appList.ApproveApplication(db,appId);
    }

    public void RejectAplication(int appId) 
    {
        appList.RejectApplication(db,appId);
    }
    
    public void DeleteJob(int jobId) 
    {
        jl.DeleteFromDatabase(jobId,db);
    }
    
}
