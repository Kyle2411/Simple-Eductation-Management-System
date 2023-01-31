
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ben_1
 */
public class FXMLSpecificDepartment extends FXMLMainMenu {

    
    //Table Columns Students
    @FXML
    private TableColumn<?, ?> idColStuSpecific;
    @FXML
    private TableColumn<?, ?> nameColStuSpecific;
    @FXML
    private TableColumn<?, ?> ageColStuSpecific;
    @FXML
    private TableColumn<?, ?> genderColStuSpecific;
    @FXML
    private TableColumn<?, ?> courseColStuSpecific;
    @FXML
    private TableColumn<?, ?> semesterColStuSpecific;
    @FXML
    private TableColumn<?, ?> departmentIDColStuSpecific;
    
    //Table Columns Teachers
    @FXML
    private TableColumn<?, ?> idColTeachSpecific;
    @FXML
    private TableColumn<?, ?> nameColTeachSpecific;
    @FXML
    private TableColumn<?, ?> ageColTeachSpecific;
    @FXML
    private TableColumn<?, ?> genderColTeachSpecific;
    @FXML
    private TableColumn<?, ?> specialtyColTeachSpecific;
    @FXML
    private TableColumn<?, ?> degreeColTeachSpecific;
    @FXML
    private TableColumn<?, ?> salaryColTeachSpecific;
    @FXML
    private TableColumn<?, ?> departmentIDColTeachSpecific;
    @FXML
    private TableColumn<?, ?> deanColTeachSpecific;
    
    //Table Columns Staff
    @FXML
    private TableColumn<?, ?> idColStaffSpecific;
    @FXML
    private TableColumn<?, ?> nameColStaffSpecific;
    @FXML
    private TableColumn<?, ?> ageColStaffSpecific;
    @FXML
    private TableColumn<?, ?> genderColStaffSpecific;
    @FXML
    private TableColumn<?, ?> dutyColStaffSpecific;
    @FXML
    private TableColumn<?, ?> workloadColStaffSpecific;
    @FXML
    private TableColumn<?, ?> salaryColStaffSpecific;
    @FXML
    private TableColumn<?, ?> departmentIDColStaffSpecific;
    
    //TableViews
    @FXML
    private TableView<Student> tvStuSpecific;
    @FXML
    private TableView<Teacher> tvTeachSpecific;
    @FXML
    private TableView<Staff> tvStaffSpecific;
    
    //Buttons
    @FXML
    private Button viewSpecificStuBtn;
    @FXML
    private Button viewSpecificTeachBtn;
    @FXML
    private Button viewSpecificStaffBtn;
    @FXML
    private Button backToViewDepartmentBtn;
    
    //Class Objects
    public static CreateStudent createStu = new CreateStudent();
    public static CreateTeacher createTeach = new CreateTeacher();
    public static CreateStaff createStaff = new CreateStaff();
    public static Department tempDep = new Department();

    @Override
    public void initialize(URL url, ResourceBundle rb) throws NullPointerException{
        //using .fire() methods to display table information when scene is opened
        viewSpecificStuBtn.fire();
        viewSpecificTeachBtn.fire();
        viewSpecificStaffBtn.fire();

    }

    //Method for when a button is pressed in the view Student, and Manage Student menus.
    @FXML
    private void mainSpecificDepMenuButton(ActionEvent event) throws IOException {

        //Checks if the refresh Student button is pressed
        if (event.getSource() == viewSpecificStuBtn) {
            displaySpecificStudent(CreateStudent.tempStudentList);
            tvStuSpecific.refresh();
        }
        //Checks if the refresh Teacher button is pressed
        if (event.getSource() == viewSpecificTeachBtn) {
            displaySpecificTeacher(CreateTeacher.tempTeacherList);
            tvTeachSpecific.refresh();
        }
        //Checks if the refresh Staff button is pressed
        if (event.getSource() == viewSpecificStaffBtn) {
            displaySpecificStaff(CreateStaff.tempStaffList);
            tvStaffSpecific.refresh();
        }

        //Checks if back button is pressed
        if (event.getSource() == backToViewDepartmentBtn) {
            try {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("viewDepartment.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                
            } catch (NullPointerException ex) {
                System.out.println("viewDepartment scene file not found");
                ex.printStackTrace();
            }
        }
    }

    //mehtod to display information of specific students based on their department's ID
    @FXML
    public void displaySpecificStudent(ObservableList<Student> stuArr) throws IOException {

        //calling readTempStu method that uses argument to specify the student's current department  
        //depCheck is defined in FXMLDepartment when user enters department ID before it switches scenes.
        createStu.readTempStu(FXMLDepartment.depCheck);
        
        idColStuSpecific.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColStuSpecific.setCellValueFactory(new PropertyValueFactory<>("Name"));
        ageColStuSpecific.setCellValueFactory(new PropertyValueFactory<>("Age"));
        genderColStuSpecific.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        courseColStuSpecific.setCellValueFactory(new PropertyValueFactory<>("Course"));
        semesterColStuSpecific.setCellValueFactory(new PropertyValueFactory<>("Semester"));
        departmentIDColStuSpecific.setCellValueFactory(new PropertyValueFactory<>("DeptChoice"));
        tvStuSpecific.setItems(stuArr);

    }
    
    //mehtod to display information of specific teachers based on their department's ID
    @FXML
    public void displaySpecificTeacher(ObservableList<Teacher> teachArr) throws IOException {

        //calling readTempTeach method that uses argument to specify the teacher's current department
        //depCheck is defined in FXMLDepartment when user enters department ID before it switches scenes.
        createTeach.readTempTeacher(FXMLDepartment.depCheck);
        
        idColTeachSpecific.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColTeachSpecific.setCellValueFactory(new PropertyValueFactory<>("Name"));
        ageColTeachSpecific.setCellValueFactory(new PropertyValueFactory<>("Age"));
        genderColTeachSpecific.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        specialtyColTeachSpecific.setCellValueFactory(new PropertyValueFactory<>("Specialty"));
        degreeColTeachSpecific.setCellValueFactory(new PropertyValueFactory<>("Degree"));
        salaryColTeachSpecific.setCellValueFactory(new PropertyValueFactory<>("Salary"));
        departmentIDColTeachSpecific.setCellValueFactory(new PropertyValueFactory<>("DeptChoice"));
        deanColTeachSpecific.setCellValueFactory(new PropertyValueFactory<>("Dean"));
        tvTeachSpecific.setItems(teachArr);

    }

    //mehtod to display information of specific staff based on their department's ID
    @FXML
    public void displaySpecificStaff(ObservableList<Staff> staffArr) throws IOException {

        //calling readTempStaff method that uses argument to specify the staff's current department 
        //depCheck is defined in FXMLDepartment when user enters department ID before it switches scenes.
        createStaff.readTempStaff(FXMLDepartment.depCheck);
        
        idColStaffSpecific.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColStaffSpecific.setCellValueFactory(new PropertyValueFactory<>("Name"));
        ageColStaffSpecific.setCellValueFactory(new PropertyValueFactory<>("Age"));
        genderColStaffSpecific.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        dutyColStaffSpecific.setCellValueFactory(new PropertyValueFactory<>("Duty"));
        workloadColStaffSpecific.setCellValueFactory(new PropertyValueFactory<>("Workload"));
        salaryColStaffSpecific.setCellValueFactory(new PropertyValueFactory<>("Salary"));
        departmentIDColStaffSpecific.setCellValueFactory(new PropertyValueFactory<>("DeptChoice"));
        tvStaffSpecific.setItems(staffArr);

    }

}
