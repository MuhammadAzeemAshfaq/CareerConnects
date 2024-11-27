//package Default;
package DataBase;

import java.sql.Connection;


import java.util.ArrayList;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.*;
import java.util.List;

import Backend.Application;
import Backend.ApplicationList;
import Backend.Employee;
import Backend.Job;
import Backend.JobList;
import Backend.ProfileList;
import Backend.JobSeeker;
import Backend.User;

//import Default.Employee;
public class DB 
{
	 Connection con;
	 PreparedStatement pstmt;
	 ProfileList pl;
	 
	public DB()
	{
		pstmt = null;
		 try{
		        
		       Class.forName("com.mysql.cj.jdbc.Driver");
		       con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CareerConnect","root","Mysql1122@");
		           
		        System.out.println("Connected to SQL Server!");

		       } catch (ClassNotFoundException e) 
		       {
		           System.out.println("MySQL JDBC Driver not found.");
		          // e.printStackTrace();
		       } catch (SQLException e) 
		       {
		           System.out.println("Connection failed!");
		           //e.printStackTrace();
		       }
		 
	}
		 
	 
	public void addEmployer(String Name,String Email,String Contact,String uname,String pass)
	{
		String sql=	"INSERT INTO Employer (Uname, password, Name, email, ContactInfo,Num_of_Jobs) VALUES (?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement pstmtInsert = con.prepareStatement(sql);
				pstmtInsert.setString(1, uname);
	        	pstmtInsert.setString(2, pass);
	        	pstmtInsert.setString(3, Name);
	            pstmtInsert.setString(4, Email);
	            pstmtInsert.setString(5, Contact);
	            pstmtInsert.setInt(6, 0);
	            pstmtInsert.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public void addJobSeeker(String Name,String Email,String Contact,String uname,String pass)
	{
		String sql=	"INSERT INTO JobSeeker (Uname, password, Name, Email, ContactInfo, Num_of_apps) VALUES (?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement pstmtInsert = con.prepareStatement(sql);
				pstmtInsert.setString(1, uname);
	        	pstmtInsert.setString(2, pass);
	        	pstmtInsert.setString(3, Name);
	            pstmtInsert.setString(4, Email);
	            pstmtInsert.setString(5, Contact);
	            pstmtInsert.setInt(6, 0);
	            pstmtInsert.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void addApplication(Application ap)
	{
		String sql=	"INSERT INTO Application (JobID, applicantId, employerId, dateApplied, coverLetter, resume, contact, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement pstmtInsert = con.prepareStatement(sql);
				pstmtInsert.setInt(1, ap.getJobID());
	        	pstmtInsert.setInt(2, ap.getJobSeekerID());
	        	pstmtInsert.setInt(3, ap.getEmpID());
	            pstmtInsert.setDate(4, java.sql.Date.valueOf(ap.getDate()));
	            pstmtInsert.setString(5, ap.getcoverletter());
	            pstmtInsert.setString(6, ap.getresume());
	            pstmtInsert.setString(7, ap.getcontact());
	            pstmtInsert.setString(8, ap.status());
	            pstmtInsert.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void addJob(Job job)
	{
		String sql=	"INSERT INTO Job (jobId,title, description, salary_min, salary_max, postedBy,postingDate,closingDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		String SKillSql = "INSERT INTO skillsRequired (JobId, skill) VALUES (?, ?)";
		
		
		try(PreparedStatement pstmtInsert = con.prepareStatement(sql);
				PreparedStatement stmt2 = con.prepareStatement(SKillSql)) {
			
				pstmtInsert.setInt(1, job.getDesc().getJobId());
				pstmtInsert.setString(2, job.getDesc().getTitle());
	        	pstmtInsert.setString(3, job.getDesc().getDescription());
	        	pstmtInsert.setDouble(4, job.getDesc().getMinSal());
	            pstmtInsert.setDouble(5, job.getDesc().getMaxSal());
	            pstmtInsert.setInt(6, job.getDesc().getEmployeeId());
	            pstmtInsert.setDate(7, java.sql.Date.valueOf(job.getDesc().getPostDate()));
	            pstmtInsert.setDate(8, java.sql.Date.valueOf(job.getDesc().getCloseDate()));
	            pstmtInsert.executeUpdate();
	            for(String skill: job.getDesc().getSkills())
	            {
	            	stmt2.setInt(1, job.getDesc().getJobId());
	            	stmt2.setString(2, skill);
	            	stmt2.executeUpdate();
	            }
	            
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getEmployerId(Employee emp)
	{
		String sql="Select EmployerId from Employer where uname = ? AND password = ?";
		ResultSet resultSet = null;
		try {
			PreparedStatement pstmtInsert = con.prepareStatement(sql);
			pstmtInsert.setString(1, emp.p.getuname());
			pstmtInsert.setString(2, emp.p.getPassword());
			//pstmtInsert.executeUpdate();
			
			ResultSet rs = pstmtInsert.executeQuery();
			
				if (rs.next())
				{
				int userId = rs.getInt("EmployerId");
				//System.out.println("id in db."+userId);
				emp.p.setuid(userId);
				return userId;
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public int getEmployerId(String username)
	{
		String sql="Select EmployerId from Employer where uname = ?";
		ResultSet resultSet = null;
		try(PreparedStatement pstmtInsert = con.prepareStatement(sql)) {
			
			pstmtInsert.setString(1, username);
			resultSet=pstmtInsert.executeQuery();
			if (resultSet.next())
			{	
				int userId = resultSet.getInt("EmployerId");
				return userId;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	
	public int getJobSeekerId(JobSeeker js)
	{
		String sql="Select JobSeekerId from JobSeeker where uname = ? AND password = ?";
		ResultSet resultSet = null;
		try {
			PreparedStatement pstmtInsert = con.prepareStatement(sql);
			pstmtInsert.setString(1, js.profile.getuname());
			pstmtInsert.setString(2, js.profile.getPassword());
			//pstmtInsert.executeUpdate();
			ResultSet rs = pstmtInsert.executeQuery();
			
			if (rs.next()) {
                int jobSeekerId = rs.getInt("JobSeekerId");
                //System.out.println("Login successful! JobSeekerId: " + jobSeekerId);
                return jobSeekerId;
            }
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public int getApplicationId(Application ap)
	{
		String sql="Select ApplicationId from Application where jobId = ? AND applicantId = ?";
		ResultSet resultSet = null;
		try {
			PreparedStatement pstmtInsert = con.prepareStatement(sql);
			pstmtInsert.setInt(1, ap.getJobID());
			pstmtInsert.setInt(2, ap.getJobSeekerID());
			//pstmtInsert.executeUpdate();
			ResultSet rs = pstmtInsert.executeQuery();
			
			if (rs.next()) {
                int ApplicationId = rs.getInt("ApplicationId");
                //System.out.println("ApplicationId: " + ApplicationId);
                return ApplicationId;
            }
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public void loadEmployers(ProfileList pl)
	{
		try
		{
			String sql = "SELECT EmployerId,uname, password, Name, Email, ContactInfo, Num_of_Jobs FROM Employer";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int id=rs.getInt("EmployerId");
				String uname = rs.getString("uname");
				String pass = rs.getString("password");
				String Name = rs.getString("Name");
				String Email = rs.getString("Email");
				String Contact = rs.getString("ContactInfo");
				int numOfJobs = rs.getInt("Num_of_Jobs");
				Employee emp=new Employee(Name, Email, Contact, uname, pass);
				emp.p.setuid(id);
				emp.p.set_num(numOfJobs);
				pl.addProfile(emp.p);
            }
	        rs.close();
	        stmt.close();
	       // con.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public void loadJobSeeker(ProfileList pl)
	{
		try
		{
			String sql = "SELECT JobSeekerId,uname, password, Name, email, ContactInfo, Num_of_apps FROM JobSeeker";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int uid = rs.getInt("JobSeekerId");
				String uname = rs.getString("uname");
				String pass = rs.getString("password");
				String Name = rs.getString("Name");
				String Email = rs.getString("email");
				String Contact = rs.getString("ContactInfo");
				int numOfJobs = rs.getInt("Num_of_apps");
				
				JobSeeker js=new JobSeeker(Name, Email, Contact, uname, pass);
				js.profile.set_num(numOfJobs);
				js.profile.setuid(uid);
				pl.addProfile(js.profile);
            }
	        rs.close();
	        stmt.close();
	       // con.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public void loadApplications(ApplicationList ap)
	{
		try
		{
			String sql = "SELECT ApplicationId,jobId, applicantId,employerId, dateApplied, coverLetter, resume,contact,status FROM Application";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int apid = rs.getInt("ApplicationId");
				int jid = rs.getInt("jobId");
				int jobskrid = rs.getInt("applicantId");
				int empid = rs.getInt("employerId");
				java.sql.Date applydate = rs.getDate("dateApplied");
				String coverletr = rs.getString("coverLetter");
				String resume = rs.getString("resume");
				String contact = rs.getString("contact");
				String status = rs.getString("status");
				
				
				Application apli=new Application(jobskrid,jid,empid,contact,resume,coverletr);
				
				LocalDate localDate = applydate.toLocalDate();
				
			apli.setApplicationID(apid);
			apli.setstatus(status);
			apli.setDate(localDate);
				
				ap.addApplication_toList(apli);
            }
	        rs.close();
	        stmt.close();
	       // con.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	

	public void loadJobs(JobList jL)
	{
		String sql = "SELECT jobId, title, description, salary_min, salary_max, postedBy,postingDate,closingDate FROM Job";
		String sql2 = "SELECT Skill FROM skillsRequired WHERE JobId = ?";
		
		try(Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				PreparedStatement stmt2 = con.prepareStatement(sql2))
		{
			
			
			List<String> skills = new ArrayList<>();
			while (rs.next()) {
				int jobId=rs.getInt("jobId");
				String title = rs.getString("title");
				String desc = rs.getString("description");
				double minSal = rs.getDouble("salary_min");
				double maxSal = rs.getDouble("salary_max");
				int empId=rs.getInt("postedBy");
				LocalDate postedDate = rs.getDate("postingDate").toLocalDate();
				LocalDate closingDate = rs.getDate("closingDate").toLocalDate();
				
				stmt2.setInt(1, jobId);
				ResultSet rs2 = stmt2.executeQuery();
				
				//System.out.println("Job id:"+jobId);
				
				while (rs2.next()) {
	                String skill = rs2.getString("Skill");
	                skills.add(skill);
	            }

				Job job=new Job(title,desc,empId,skills,minSal,maxSal,postedDate,closingDate);
				job.setJobId(jobId);
				jL.addJob(job.getDesc());;
            }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public void updateJobSeeker(String currentUname, String currentPass, String newUname, String newPass, String newName, String newEmail, String newContact) 
	{
	    String sql = "UPDATE JobSeeker SET Uname = ?, password = ?, Name = ?, Email = ?, ContactInfo = ? WHERE Uname = ? AND password = ?";
	    
	    try {
	        PreparedStatement pstmtUpdate = con.prepareStatement(sql);
	        // Setting new values
	        pstmtUpdate.setString(1, newUname);
	        pstmtUpdate.setString(2, newPass);
	        pstmtUpdate.setString(3, newName);
	        pstmtUpdate.setString(4, newEmail);
	        pstmtUpdate.setString(5, newContact);
	        // Matching current username and password
	        pstmtUpdate.setString(6, currentUname);
	        pstmtUpdate.setString(7, currentPass);
	        
	        int rowsAffected = pstmtUpdate.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("Job seeker data updated successfully.");
	        } else {
	            System.out.println("No record found with the provided username and password.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public void incrementNoOfApp(String uname, String pass) 
	{
	    String sql = "UPDATE JobSeeker SET Num_of_apps = Num_of_apps + 1 WHERE Uname = ? AND password = ?";
	    
	    try {
	        PreparedStatement pstmtUpdate = con.prepareStatement(sql);
	        pstmtUpdate.setString(1, uname);
	        pstmtUpdate.setString(2, pass);
	        
	        int rowsAffected = pstmtUpdate.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("Application count incremented successfully.");
	        } else {
	            System.out.println("No record found with the provided username and password.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public void updateJob(int jobId, String title, String description, List<String> skills, double minSal, double maxSal, LocalDate date) {
		// TODO Auto-generated method stub
		String sql = "Update Job set title=?, description=?, salary_min=?, salary_max=?, closingDate=? where JobId=?";
		String del="Delete from skillsRequired where jobId=?";
		String insert="Insert into skillsRequired (jobId,Skill) values (?, ?)";
		try( PreparedStatement stmt = con.prepareStatement(sql);
				PreparedStatement stmt2 = con.prepareStatement(del);
				PreparedStatement stmt3 = con.prepareStatement(insert))
		{
			stmt.setString(1, title);
			stmt.setString(2, description);
			stmt.setDouble(3, minSal);
			stmt.setDouble(4, maxSal);
			stmt.setDate(5, java.sql.Date.valueOf( date));
			stmt.setInt(6, jobId);
			stmt.executeUpdate();
			
			stmt2.setInt(1, jobId);
			stmt2.executeUpdate();
			
			for(String skill:skills)
			{
				stmt3.setInt(1, jobId);
				stmt3.setString(2, skill);
				stmt3.executeUpdate();
			}
		}catch (Exception e) {
            e.printStackTrace();
        }
	}
	public void updateProfile(int id,String Name,String Email,String Contact,String uname,String pass)
	{
		String sql = "Update Employer set Uname=?, password=?, Name=?, email=?, ContactInfo=? where EmployerId=?";
		try( PreparedStatement stmt = con.prepareStatement(sql))
		{
			stmt.setString(1, uname);
			stmt.setString(2, pass);
			stmt.setString(3, Name);
			stmt.setString(4, Email);
			stmt.setString(5, Contact);
			stmt.setInt(6, id);
			stmt.executeUpdate();
			
		}catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public void updateApplication(boolean check,int appId)
	{
		String sql="Update Application set status = ? where ApplicationId = ?";
		try(PreparedStatement stmt = con.prepareStatement(sql))
		{
			if(check)
			{
				stmt.setString(1, "Accepted");
			}
			else
			{
				stmt.setString(1, "Rejected");
			}
			stmt.setInt(2, appId);
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteJob(int jobID) {
		// TODO Auto-generated method stub
		String del1="Delete from Application where jobId=?";
		String del2="Delete from skillsRequired where jobId=?";
		String del3="Delete from job where jobId=?";
		try( PreparedStatement stmt = con.prepareStatement(del1);
				PreparedStatement stmt2 = con.prepareStatement(del2);
				PreparedStatement stmt3 = con.prepareStatement(del3))
		{
			stmt.setInt(1, jobID);
			stmt.executeUpdate();
			
			stmt2.setInt(1, jobID);
			stmt2.executeUpdate();
			
			stmt3.setInt(1, jobID);
			stmt3.executeUpdate();
			
		}catch (Exception e) {
            e.printStackTrace();
        }
	}

}