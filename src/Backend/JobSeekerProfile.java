//package Default;
package Backend;

public class JobSeekerProfile implements Profile 
{

	private String Name;
	private int uid;
	private String Email;
	private String Contact;
	
	private String Username;
	private String Password;
    private int numApps;  
    //private String preferredRole; 
   
    public String type;

    // Constructor to initialize the profile with basic details
    public JobSeekerProfile(String Name,String Email,String Contact,String uname,String pass)
    {
    	this.Name=Name;
    	this.Contact=Contact;
        this.numApps = 0;
        this.Email=Email;
        
        this.Username=uname;
        this.Password=pass;
        
        type="JobSeeker";
       // this.preferredRole = preferredRole;
        //this.salaryRange = new int[]{minSalary, maxSalary};
    }

    public String getuname()
    {
    	return this.Username;
    }
    public String getPassword()
    {
    	return this.Password;
    }
    public int getUId()
    {
    	return this.uid;
    }
    public String getContact()
    {
    	return this.Contact;
    }
    public String getEmail()
    {
    	return this.Email;
    }
    public String getName()
    {
    	return this.Name;
    }
    public int get_num()
    {
    	return this.numApps;
    }
    public void set_num(int aps)
    {
    	this.numApps=aps;
    }
    
    public void setuid(int id)
    {
    	this.uid=id;
    }
	public int getuid()
	{
		return this.uid;
	}
	public String gettype()
	{
		return this.type;
	}
	public void settype(String type)
	{
		this.type=type;
	}
    // Method to retrieve profile information
    public String getInfo()
    {
        return " ";//"Preferred Role: " + preferredRole + "\n" +
               //"Number of Applications: " + numApps;
    }

    // Method to update the profile with new data
    public void UpdateProfile(String newName, String newEmail, String Contact,String Username,String Password)
    {
    	
    	
    	this.Name=newName;
    	this.Contact=Contact;
        this.Email=newEmail;
        
        
        this.Username=Username;
        this.Password=Password;
  
    }
}
