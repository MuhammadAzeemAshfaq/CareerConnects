//package Default;
package Backend;

public interface Profile
{

	/* String Name="";
	 String uid="";
	 String Email="";
     String Contact="";
	
	 String Username="";
     String Password="";*/
     
	public String getInfo();
	public void UpdateProfile(String newName, String newEmail, String Contact,String Username,String Password);
    public String getuname();
    public String getPassword();
    public int getUId();
    public String getContact();
    public String getEmail();
    public String getName();
	public void set_num(int i);
	public int get_num();
	public void setuid(int id);
	public int getuid();
	public String gettype();
	public void settype(String type);
}
