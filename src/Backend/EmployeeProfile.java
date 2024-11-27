//package Default;
package Backend;

public class EmployeeProfile implements Profile 
{

    private String companyName;
    
	private int uid;
	private String Email;
	private String Contact;
	
	private String Username;
	private String Password;

	private int numJobs;  
	
	public String type;
	
    public EmployeeProfile(String Name,String Email,String Contact,String uname,String pass) 
    {
    	this.companyName=Name;
    	this.Contact=Contact;
        this.numJobs = 0;
        this.Email=Email;
        
        this.Username=uname;
        this.Password=pass;
        type="Employee";
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
    	return this.companyName;
    }
    
    public int get_num()
    {
    	return this.numJobs;
    }
    public void set_num(int aps)
    {
    	this.numJobs=aps;
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
        return "";/*"Company Name: " + companyName + "\n" +
               "Industry: " + industry + "\n" +
               "Role: " + role + "\n" +
               "Number of Applications: " + numApps;*/
    }

    // Method to update the profile with new data
    public void UpdateProfile(String newName, String newEmail, String Contact,String Username,String Password)
    {
    	
    	
    	this.companyName=newName;
    	this.Contact=Contact;
        this.Email=newEmail;
        
        
        this.Username=Username;
        this.Password=Password;
  
    }


}