//package Default;
package Backend;

import java.util.ArrayList;
import java.util.List;

import DataBase.DB;

public class ProfileList 
{


    private List<Profile> profiles; 
    private int numOfProfiles;      

    // Constructor to initialize the ProfileList
    public ProfileList() 
    {
        this.profiles = new ArrayList<>();
        this.numOfProfiles = 0;
    }

    // Method to get information about all profiles
    public String getInfo() 
    {
        
        return "";
    }

   
    public Profile RetrieveProfile(String uname) 
    {
    	for(int i=0;i<this.profiles.size();i++)
        {
        	if(this.profiles.get(i).getuname().equals(uname))
        	{
        		//System.out.println("retrive-----");
        		return this.profiles.get(i);
        		
        		//break;
        	}
        }
    	
    	return null;
    }

   
    public void EditProfile(String Name,String Email,String Contact,String nUname,String npass,String uname,String pass) 
    {
        for(int i=0;i<this.profiles.size();i++)
        {
        	if(this.profiles.get(i).getuname().equals(uname)&&this.profiles.get(i).getPassword().equals(pass))
        	{
        		
        		this.profiles.get(i).UpdateProfile(Name, Email, Contact, uname, pass);
        		//System.out.println("updated profile-----"+Name);
        		
        		break;
        	}
        }
    }

    // Method to update the database (mock implementation)
    public void UpdateDB() 
    {
        System.out.println("Database updated with the latest profile list.");
    }

    // Method to add a profile to the list
    public void addProfile(Profile profile) 
    {
		this.numOfProfiles++;
		this.profiles.add(profile);
		
    }
    
    public boolean isProfileAvailable(String uname,String pass,String type) 
    {
		for(int i=0;i<profiles.size();i++)
		{
			if(profiles.get(i).getuname().equals(uname) && profiles.get(i).getPassword().equals(pass) && profiles.get(i).gettype().equals(type))
			{
				return true;
			}
		}
		return false;
    }
    
    public Profile getProfile(String uname,String pass,String type) 
    {
		for(int i=0;i<profiles.size();i++)
		{
			if(profiles.get(i).getuname().equals(uname) && profiles.get(i).getPassword().equals(pass)&& profiles.get(i).gettype().equals(type))
			{
				//return true;
				return profiles.get(i);
			}
		}
		return null;
    }
    
    //==================Load Job seekers from DB==============
    public void loaddatafromDB(DB db) 
    {
		db.loadJobSeeker(this);
		db.loadEmployers(this);
		//System.out.println("Total Profiles Loaded :"+this.numOfProfiles);
		
		/*for(int i=0;i<this.numOfProfiles;i++)
		{
			System.out.println("ids:"+this.profiles.get(i).getuid());
		}*/
    }
    
//===============================
	public List<Profile> getAllProfiles()
	{
		return profiles;
	}
    
}