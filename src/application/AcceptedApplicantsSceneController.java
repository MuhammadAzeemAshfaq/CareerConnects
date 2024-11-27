package application;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.scene.Node;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.beans.property.SimpleStringProperty;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import Backend.Application;
import Backend.JobDescription;
import Backend.JobPortal;
import Backend.Profile;

public class AcceptedApplicantsSceneController {
	private JobPortal jp;
	private JobDescription currentJob;
	private Stage stage;
	 private Scene scene;
	 private Parent root;
	 
	@FXML
    private TableView<Application> applicationTable;
    @FXML
    private TableColumn<Application, String> nameColumn;
    @FXML
    private TableColumn<Application, String> contactColumn;
    @FXML
    private TableColumn<Application, String> cvColumn;
    @FXML
    private TableColumn<Application, String> resumeColumn;
    @FXML
    private TableColumn<Application, LocalDate> dateColumn;
    @FXML
    private TableColumn<Application, String> jobColumn;
    @FXML
    private Button goBackBtn;
    @FXML
	private Text NameField;

	public AcceptedApplicantsSceneController()
	{
		jp=JobPortal.getInstance();
	}
	
	@FXML
    public void initialize()
	 {
    	
    	nameColumn.setCellValueFactory(cellData -> {
            Application app = cellData.getValue();
            String name = fetchApplicantName(app.getJobSeekerID());
            return new SimpleStringProperty(name);
        });

        jobColumn.setCellValueFactory(cellData -> {
            Application app = cellData.getValue();
            String title = fetchJobTitle(app.getJobID());
            return new SimpleStringProperty(title);
        });
    	
    	
		    contactColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
		    cvColumn.setCellValueFactory(new PropertyValueFactory<>("coverletter"));
		    resumeColumn.setCellValueFactory(new PropertyValueFactory<>("resume"));
		    dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
		 
	 }
    
    public void setJobDetails(List<JobDescription> jobs) {
        loadApplications(jobs);
    }
    
    public void loadApplications(List<JobDescription> jobs)
	 {
		 this.NameField.setText(jp.getLogedInEmployer().getName());
		 ObservableList<Application> appList = FXCollections.observableArrayList();
		 for(int i=0;i<jp.RequestApplicationLists().size();i++)
		 {
			 Application app=jp.RequestApplicationLists().get(i);
			 if(app.getEmpID()==jp.getLogedInEmployer().getUId() && app.status().equals("Accepted"))
			 {
				 appList.add(app);
			 }
		 }
		 
		 applicationTable.setItems(appList);
	 }
	
    private String fetchApplicantName(int applicantId) {
        // Fetch the JobSeekerProfile from JobPortal or database
        List<Profile> profiles = jp.getAllProfiles();
        for(int i=0;i<profiles.size();i++)
        {
        	Profile prof=profiles.get(i);
        	if(prof.getUId()==applicantId && prof.gettype().equals("JobSeeker"))
        	{
        		return prof.getName();
        	}
        }
        return "Unknown";
    }

    private String fetchJobTitle(int jobId) {
        // Fetch the JobDescription from JobPortal or database
        List<JobDescription> jobs = jp.getAllJobs();
        
        for(int i=0;i<jobs.size();i++)
        {
        	JobDescription job=jobs.get(i);
        	if(job.getJobId()==jobId)
        	{
        		return job.getTitle();
        	}
        }
        return "Unknown";
    }
    
    @FXML
    void GoBack(ActionEvent event)
    {
    	try {
			root = FXMLLoader.load(getClass().getResource("Employer.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
