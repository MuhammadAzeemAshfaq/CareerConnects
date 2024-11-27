//package Default;
package Backend;

import java.util.*;

public abstract class User
{

    //String name;
    //String ID;
    //String email;
   // String username;
   // String password;
	
	String UserName;
	String Password;
	
    void Register(String Name,String Email,String Contact,String uname,String pass) {};
    
    String getName()
    {
    	return null;
    }
    String getEmail()
    {
    	return null;
    }
    String getId()
    {
    	return null;
    }
    boolean login(String uname,String pass)
    {
    	return true;
    }
    
}