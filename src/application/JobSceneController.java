package application;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

import Backend.JobDescription;
import Backend.JobPortal;


public class JobSceneController {
	
	private JobPortal jp;
	private Stage stage;
	 private Scene scene;
	 private Parent root;
	 
	 @FXML
	    private TableView<JobDescription> jobTable;
	    @FXML
	    private TableColumn<JobDescription, String> jobTitleColumn;
	    @FXML
	    private TableColumn<JobDescription, String> jobDescColumn;
	    @FXML
	    private TableColumn<JobDescription, Double> minSalaryColumn;
	    @FXML
	    private TableColumn<JobDescription, Double> maxSalaryColumn;
	    @FXML
	    private TableColumn<JobDescription, String> datePostedColumn;
	    @FXML
	    private TableColumn<JobDescription, String> closingDateColumn;
	    @FXML
	    private TableColumn<JobDescription, Void> editColumn;
	    @FXML
	    private TableColumn<JobDescription, Void> deleteColumn;
	    @FXML
	    private TableColumn<JobDescription, Void> applicationsColumn;
	    @FXML
		private Text NameField;
	 
	 public JobSceneController()
	 {
		 jp = JobPortal.getInstance();
	 }
	 
	 @FXML
	 public void initialize()
	 {
		 jobTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
		    jobDescColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
		    minSalaryColumn.setCellValueFactory(new PropertyValueFactory<>("minSal"));
		    maxSalaryColumn.setCellValueFactory(new PropertyValueFactory<>("maxSal"));
		    datePostedColumn.setCellValueFactory(new PropertyValueFactory<>("postDate"));
		    closingDateColumn.setCellValueFactory(new PropertyValueFactory<>("closeDate"));
		    
		    addButtonToEditColumn();
	        addButtonToDeleteColumn();
	        addButtonToApplicationsColumn();
		 loadJobs();
	 }
	 public void loadJobs()
	 {
		 this.NameField.setText(jp.p.getName());
		 ObservableList<JobDescription> jobList = FXCollections.observableArrayList();
		 for(int i=0;i<jp.getJobs().size();i++)
		 {
			 JobDescription jD=jp.getJobs().get(i);
			 if(jD.getEmployeeId()==jp.p.getUId())
			 {
				 jobList.add(jD);
			 }
		 }
		 
		 jobTable.setItems(jobList);
	 }
	 public void refreshTable() 
	 {
		    ObservableList<JobDescription> jobList = FXCollections.observableArrayList();
		    for (JobDescription jD : jp.getAllJobs()) {
		        if (jD.getEmployeeId() == jp.getLogedInEmployer().getUId()) {
		            jobList.add(jD);
		        }
		    }
		    jobTable.setItems(jobList);
		    jobTable.refresh();
		}
	 public void PostJob(ActionEvent event)
	 {
		 try {
				root = FXMLLoader.load(getClass().getResource("JobPost.fxml"));
				stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }
	 
	 private void addButtonToEditColumn() {
	       editColumn.setCellFactory(column -> new TableCell<>() {
	           private final Button editButton = new Button("Edit");

	           {
	           	
	               editButton.setOnAction(event -> {
	               	if (getIndex() < getTableView().getItems().size())
	               	{
	               		JobDescription job = getTableView().getItems().get(getIndex());
	               		handleEditJob(event,job);
	               	}
	               });
	           }

	           @Override
	           protected void updateItem(Void item, boolean empty) {
	               super.updateItem(item, empty);
	               if (empty) {
	                   setGraphic(null);
	               } else {
	                   setGraphic(editButton);
	               }
	           }
	       });
	   }

	    private void addButtonToDeleteColumn() {
	        deleteColumn.setCellFactory(column -> new TableCell<>() {
	            private final Button deleteButton = new Button("Delete");

	            {
	                deleteButton.setOnAction(event -> {
	                	JobDescription job = getTableView().getItems().get(getIndex());
	                    handleDeleteJob(job);
	                });
	            }

	            @Override
	            protected void updateItem(Void item, boolean empty) {
	                super.updateItem(item, empty);
	                if (empty) {
	                    setGraphic(null);
	                } else {
	                    setGraphic(deleteButton);
	                }
	            }
	        });
	    }

	    private void addButtonToApplicationsColumn() {
	    	applicationsColumn.setCellFactory(column -> new TableCell<>() {
	    	    private final Button viewApplicationsButton = new Button("View Applications");

	    	    {
	    	        viewApplicationsButton.setOnAction(event -> {
	    	        	JobDescription job = getTableView().getItems().get(getIndex());
	    	            handleViewApplications(event,job);
	    	        });
	    	    }

	    	    @Override
	    	    protected void updateItem(Void item, boolean empty) {
	    	        super.updateItem(item, empty);
	    	        if (empty) {
	    	            setGraphic(null);
	    	        } else {
	    	            setGraphic(viewApplicationsButton);
	    	        }
	    	    }
	    	});
	    }

	    private void handleEditJob(ActionEvent event,JobDescription job) {
	        System.out.println("Editing job: " + job.getTitle());
	        // Open dialog for editing
	        try {
	        	FXMLLoader loader = new FXMLLoader(getClass().getResource("EditJob.fxml"));
	            Parent root = loader.load();
	            EditJobSceneController controller = loader.getController();
	            controller.setJobDetails(job);
	            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
	            scene = new Scene(root);
	            stage.setScene(scene);
	            stage.show();
	        } catch (IOException e) {
	        	// TODO Auto-generated catch block
	        	e.printStackTrace();
	        }
	    }

	    private void handleDeleteJob(JobDescription job)
	    {
	        System.out.println("Deleting job: " + job.getTitle());
	        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
	        alert.setTitle("Delete Confirmation");
	        alert.setHeaderText("Press ok to confirm delete");
	        alert.setContentText("Job Title: "+ job.getTitle());
	        
	        ButtonType OkBtn=new ButtonType("Ok");
	        ButtonType cancelBtn=new ButtonType("cancel");
	        alert.getButtonTypes().setAll(OkBtn,cancelBtn);
	        
	        alert.showAndWait().ifPresent(response->{
	        	if(response==OkBtn)
	        	{
	           		jp.DeleteJob(job.getJobId());
	           		this.refreshTable();
	           		showAlert(Alert.AlertType.INFORMATION,"Success","Job has been deleted");
	           		
	        	}
	        	else
	        	{
	           		System.out.println("Deletion canceled");
	        	}
	        });
	    }

	    private void handleViewApplications(ActionEvent event,JobDescription job) {
	        System.out.println("Viewing applications for job: " + job.getTitle());
	        try {
	        	FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewApplications.fxml"));
	            Parent root = loader.load();
	            ViewApplicationsSceneController controller = loader.getController();
	            controller.setJobDetails(job);
	            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
	            scene = new Scene(root);
	            stage.setScene(scene);
	            stage.show();
	        } catch (IOException e) {
	        	// TODO Auto-generated catch block
	        	e.printStackTrace();
	        }
	        
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
	    
	    private void showAlert(Alert.AlertType alerttype,String title,String message)
	    {
	    	Alert alert=new Alert(alerttype);
	    	alert.setTitle(title);
	    	alert.setHeaderText(null);
	    	alert.setContentText(message);
	    	alert.showAndWait();
	    }
}
