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

public class ViewApplicationsSceneController {
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
	    private TableColumn<Application, Void> acceptColumn;
	    @FXML
	    private TableColumn<Application, Void> rejectColumn;
	    @FXML
		private Text NameField;
	    
	    public ViewApplicationsSceneController()
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
			    
			    addButtonToAcceptColumn();
		        addButtonToRejectColumn();
			 
		 }
	    
	    public void setJobDetails(JobDescription job) {
	        loadApplications(job);
	        currentJob=job;
	    }
	    
	    public void loadApplications(JobDescription job)
		 {
			 this.NameField.setText(jp.getLogedInEmployer().getName());
			 ObservableList<Application> appList = FXCollections.observableArrayList();
			 for(int i=0;i<jp.RequestApplicationLists().size();i++)
			 {
				 Application app=jp.RequestApplicationLists().get(i);
				 if(app.getEmpID()==jp.getLogedInEmployer().getUId() && app.getJobID()==job.getJobId() && app.status().equals("Pending"))
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
	    
	    
	    private void addButtonToAcceptColumn() {
	        acceptColumn.setCellFactory(column -> new TableCell<>() {
	            private final Button acceptButton = new Button("Accept");

	            {
	            	
	            	acceptButton.setOnAction(event -> {
	            		Application app = getTableView().getItems().get(getIndex());
	                    if (app != null) {
	                        handleAcceptApplication(app); // Pass the correct application
	                    }
	                });
	            }

	            @Override
	            protected void updateItem(Void item, boolean empty) {
	                super.updateItem(item, empty);
	                if (empty || getIndex() >= getTableView().getItems().size()) {
	                    setGraphic(null);
	                } else {
	                    setGraphic(acceptButton);
	                }
	            }
	        });
	    }

	    private void addButtonToRejectColumn() {
	        rejectColumn.setCellFactory(column -> new TableCell<>() {
	            private final Button rejectButton = new Button("Delete");

	            {
	            	rejectButton.setOnAction(event -> {
	            		Application app = getTableView().getItems().get(getIndex());
	                    if (app != null) {
	                        handleRejectApplication(app); // Pass the correct application
	                    }
	                });
	            }

	            @Override
	            protected void updateItem(Void item, boolean empty) {
	                super.updateItem(item, empty);
	                if (empty || getIndex() >= getTableView().getItems().size()) {
	                    setGraphic(null);
	                } else {
	                    setGraphic(rejectButton);
	                }
	            }
	        });
	    }
	    
	    public void refreshTable(JobDescription job) {
		    ObservableList<Application> appList = FXCollections.observableArrayList();
		    for (Application app : jp.RequestApplicationLists()) {
		        if (app.getEmpID() == jp.getLogedInEmployer().getUId() && app.getJobID()==job.getJobId() && app.status().equals("Pending")) {
		            appList.add(app);
		        }
		    }
		    applicationTable.setItems(appList);
		    applicationTable.refresh();
		}
	    
	    private void handleAcceptApplication(Application app) {
	        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
	        alert.setTitle("Accept Application");
	        alert.setHeaderText("Press ok to accept application");
	        alert.setContentText("Application: "+ app.getApplicationID());
	        
	        ButtonType OkBtn=new ButtonType("Ok");
	        ButtonType cancelBtn=new ButtonType("cancel");
	        alert.getButtonTypes().setAll(OkBtn,cancelBtn);
	        
	        alert.showAndWait().ifPresent(response->{
	        	if(response==OkBtn)
	        	{
	        		jp.ApproveApplication(app.getApplicationID());
	        		this.refreshTable(currentJob);
	        		showAlert(Alert.AlertType.INFORMATION,"Success","Application has been accepted");
	        		
	        		EmailService emailService = new EmailService();
	        		Profile p=jp.getUserprofile(app.getJobSeekerID());
	    			emailService.sendApplicationApprovalEmail(p.getEmail(), p.getName(), app.getJobID());
	        		
	        	}
	        	else
	        	{
	        		System.out.println("Canceled");
	        	}
	        });
	    }
	    
	    private void handleRejectApplication(Application app) {
	        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
	        alert.setTitle("Reject Application");
	        alert.setHeaderText("Press ok to reject application");
	        alert.setContentText("Application: "+ app.getApplicationID());
	        
	        ButtonType OkBtn=new ButtonType("Ok");
	        ButtonType cancelBtn=new ButtonType("cancel");
	        alert.getButtonTypes().setAll(OkBtn,cancelBtn);
	        
	        alert.showAndWait().ifPresent(response->{
	        	if(response==OkBtn)
	        	{
	        		jp.RejectAplication(app.getApplicationID());
	        		this.refreshTable(currentJob);
	        		showAlert(Alert.AlertType.INFORMATION,"Success","Application has been rejected");
	        		
	        	}
	        	else
	        	{
	        		System.out.println(" canceled");
	        	}
	        });
	    }

	    @FXML
	    void GoBack(ActionEvent event)
	    {
	    	try {
				root = FXMLLoader.load(getClass().getResource("Job.fxml"));
				stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    private void showAlert(Alert.AlertType alerttype,String title,String message)
	    {
	    	Alert alert=new Alert(alerttype);
	    	alert.setTitle(title);
	    	alert.setHeaderText(null);
	    	alert.setContentText(message);
	    	alert.showAndWait();
	    }
}
