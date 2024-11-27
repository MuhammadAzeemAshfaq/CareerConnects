//package Default;
package Backend;

import java.util.*;

import DataBase.DB;

//import Default.DB;
public class Employee extends User
{
 public Profile p; 
 JobPortal j;
 DB db;
 public Employee(String Name,String Email,String Contact,String uname,String pass)
 {
	 p=new EmployeeProfile(Name,Email,Contact,uname,pass);
 }
 
 
 // Method to register the employee
 public void Register(String Name,String Email,String Contact,String uname,String pass )
 {

 }

 // Method to post a job
 public void postJob() 
 {
     
 }

 // Method to manage existing job posts
 public void managePosts() 
 {
    
 }

 // Method to manage applications for the employee's job posts
 public void manageApplications() 
 {
     
 }

 // Method to display the employee's profile
 public void displayProfile() 
 {
     
 }

 // Method to log in the employee
 public boolean login(String uname,String pass) {
    
     return true; 
 }
}
