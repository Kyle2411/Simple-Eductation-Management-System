
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
public class FXMLStudent extends FXMLMainMenu {

    
    //Table Columns
    @FXML
    private TableColumn<?, ?> idColStu;
    @FXML
    private TableColumn<?, ?> nameColStu;
    @FXML
    private TableColumn<?, ?> ageColStu;
    @FXML
    private TableColumn<?, ?> genderColStu;
    @FXML
    private TableColumn<?, ?> courseColStu;
    @FXML
    private TableColumn<?, ?> semesterColStu;
    @FXML
    private TableColumn<?, ?> departmentIdColStu;

    //TableView
    @FXML
    private TableView<Student> tvStu;

    //Buttons
    @FXML
    private Button refreshStuBtn;
    @FXML
    public Button btnAddStu;
    @FXML
    public Button btnUpdateStu;
    @FXML
    public Button btnDeleteStu;
    @FXML
    private Button btnSearchViewStu;
    @FXML
    private Button btnSearchStu;
    @FXML
    private Button backToStuMenuBtn;

    //TextFields
    @FXML
    private TextField tfIDStu;
    @FXML
    private TextField tfNameStu;
    @FXML
    private TextField tfAgeStu;
    @FXML
    private TextField tfGenderStu;
    @FXML
    private TextField tfCourseStu;
    @FXML
    private TextField tfSemesterStu;
    @FXML
    private TextField tfDeptChoiceStu;
    @FXML
    private TextField tfIDSearchStu;

    //Counters
    private boolean addStuCheckCounter;
    private int studepChoiceCounter;
    private boolean deleteStuCheckCounter;
    private boolean updateStuCheckCounter;
    private int displayViewStuCount;
    
    //Class Objects
    public static CreateStudent createStu = new CreateStudent();
    public static FXMLStudentMenu stuMenu = new FXMLStudentMenu();
    
    //Temporary ObservableArrayList
    private ObservableList<Student> tempStudentList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle rb) throws NullPointerException {
        //using .fire() method to display table information when scene is opened
        refreshStuBtn.fire();
    }

    //Method for when a button is pressed in the view Student, and Manage Student menus.
    @FXML
    private void mainStudentButton(ActionEvent event) throws IOException {

        //Checks if Add Student button is pressed
        if (event.getSource() == btnAddStu) {
            addStudent();
        }
        //Checks if Delete Student button is pressed
        if (event.getSource() == btnDeleteStu) {
            deleteStudent();
        }
        //Checks if Update Student button is pressed
        if (event.getSource() == btnUpdateStu) {
            updateStudent();
        }
        //Checks if Search Student button is pressed
        if (event.getSource() == btnSearchStu) {
            searchStudent();
        }
        //Checks if refresh button is pressed
        if (event.getSource() == refreshStuBtn) {
            displayStudents(Department.StudentList);
            tvStu.refresh();
            /*will disable buttons and clear table values when refresh button is pressed
            but only when in manage student scene, else user is in view student scene, and doesn't need these options*/
            if (stuMenu.getSceneCheck() == true) {
                tfIDStu.clear();
                tfNameStu.clear();
                tfAgeStu.clear();
                tfGenderStu.clear();
                tfCourseStu.clear();
                tfSemesterStu.clear();
                tfDeptChoiceStu.clear();

                btnDeleteStu.setDisable(true);
                btnUpdateStu.setDisable(true);
            }

        }
        //Checks if search Student in view menu button is pressed
        if (event.getSource() == btnSearchViewStu) {
            tempStudentList.clear();
            displayViewStudent(Department.StudentList);
        }
        //Checks if back button is pressed
        if (event.getSource() == backToStuMenuBtn) {
            try {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("StudentMenu.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                
            } catch (NullPointerException ex) {
                System.out.println("StudentMenu scene file not found!");
                ex.printStackTrace();
            }
        }

    }

    //method to add students to the arrayList and the file.
    @FXML
    public void addStudent() throws IOException, FileNotFoundException {

        //Counters
        addStuCheckCounter = false;
        studepChoiceCounter = 0;
        
        try {
            for (Student stu : Department.StudentList) {
                //checks if the Student Id entered by the user already exists
                if (Integer.parseInt(tfIDStu.getText()) == stu.getId()) {
                    System.out.println("------------------------------------");
                    System.out.println("This student ID already exists!");
                    addStuCheckCounter = true;
                    break;

                }

            }

            for (Department dep : Department.DepartmentList) {
                //checks if the department choice enterd by the user is a department id that exists.
                if (Integer.parseInt(tfDeptChoiceStu.getText()) != dep.getId()) {

                    studepChoiceCounter++;

                }
            }

            //checks if the Student Id entered by the user already exists and if the department choice enterd by the user is a department id that exists.
            if (addStuCheckCounter == false && studepChoiceCounter < Department.DepartmentList.size()) {
                
                Student Stu = (new Student(Integer.parseInt(tfIDStu.getText()), tfNameStu.getText(), Integer.parseInt(tfAgeStu.getText()), tfGenderStu.getText(), tfCourseStu.getText(), Integer.parseInt(tfSemesterStu.getText()), Integer.parseInt(tfDeptChoiceStu.getText())));
                Department.StudentList.add(Stu);

                String path = "C:\\Users\\ben_1\\Desktop\\FinalProject\\Student.txt";

                //calling append to student method to write to student file.
                createStu.AppendToStudent(Stu.toString(), path);
                System.out.println("------------------------------------");
                System.out.println("Student Added!");
            }
            //checks if the department choice enterd by the user is a department id that exists.
                if (studepChoiceCounter == Department.DepartmentList.size()) {
                    System.out.println("------------------------------------");
                    System.out.println("You must add student to exisitng Department!");
                }
            
         //if user doesn't fill all textfields it throws NumberFormatException.
        } catch (NumberFormatException ex) {
            System.out.println("------------------------------------");
            System.out.println("Must enter all student values!");
        }

    }

    //method to delete students from the arrayList and the file
    @FXML
    public void deleteStudent() throws IOException, FileNotFoundException {
        
        //Counters
        deleteStuCheckCounter = false;
        
        try {
            for (int i = 0; i < Department.StudentList.size(); i++) {
                //checks if the student id entered by the user matches with one in the arrayList.
                if (Department.StudentList.get(i).getId() == Integer.parseInt(tfIDStu.getText())) {
                    //calls method deleteStudent using the student that the user selected as argument.
                    createStu.deleteStudent(Department.StudentList.get(i).toString());
                    Department.StudentList.remove(i);
                    System.out.println("------------------------------------");
                    System.out.println("Student deleted!");
                    deleteStuCheckCounter = true;
                }

            }
            //checks if the Student Id entered by the user was not found
            if (deleteStuCheckCounter == false) {
                System.out.println("------------------------------------");
                System.out.println("No matching student ID found");
            }
           //if user doesn't entered a valid Student Id and it throws NumberFormatException.
        } catch (NumberFormatException ex) {
            System.out.println("------------------------------------");
            System.out.println("Must enter valid student ID!");
        }
    }

    //method to update students from the arrayList and the file.
    @FXML
    public void updateStudent() throws IOException, FileNotFoundException {
        
        //Counters
        updateStuCheckCounter = false;
        
        try {
            Student Stu = (new Student(Integer.parseInt(tfIDStu.getText()), tfNameStu.getText(), Integer.parseInt(tfAgeStu.getText()), tfGenderStu.getText(), tfCourseStu.getText(), Integer.parseInt(tfSemesterStu.getText()), Integer.parseInt(tfDeptChoiceStu.getText())));
            for (int i = 0; i < Department.StudentList.size(); i++) {
                //checks if the student id entered by the user matches with one in the arrayList.
                if (Department.StudentList.get(i).getId() == Integer.parseInt(tfIDStu.getText())) {
                    String newLine = Department.StudentList.get(i).toString();
                    Department.StudentList.set(i, Stu);
                    /*calls method updateStudent using the student that the new information of the update student, 
                    as well as the old information to locate the linein the file*/
                    createStu.updateStudent(newLine, Stu.toString());
                    System.out.println("------------------------------------");
                    System.out.println("Student updated!");
                    updateStuCheckCounter = true;
                }
            }
            //checks if the Student Id entered by the user was not found
            if (updateStuCheckCounter == false) {
                System.out.println("------------------------------------");
                System.out.println("No matching student ID found");
            }

            //if user doesn't entered a valid Student Id and it throws NumberFormatException.
        } catch (NumberFormatException ex) {
            System.out.println("------------------------------------");
            System.out.println("Must enter valid student ID!");

        }
    }

    //method to search through arrayList to find specific student.
    @FXML
    public void searchStudent() {

        try {
            Student stu = new Student();
            for (int i = 0; i < Department.StudentList.size(); i++) {

                //checks if the Student ID enterd by user matches an ID in the arrayList.
                if (Department.StudentList.get(i).getId() == Integer.parseInt(tfIDStu.getText())) {
                    stu.setId(Department.StudentList.get(i).getId());
                    stu.setName(Department.StudentList.get(i).getName());
                    stu.setAge(Department.StudentList.get(i).getAge());
                    stu.setGender(Department.StudentList.get(i).getGender());
                    stu.setCourse(Department.StudentList.get(i).getCourse());
                    stu.setSemester(Department.StudentList.get(i).getSemester());
                    stu.setDeptChoice(Department.StudentList.get(i).getDeptChoice());
                    System.out.println("------------------------------------");
                    System.out.println("Student found!");
                }

            }
            //checks if the Student ID is not 0, then fills text filed values of that student.
            if (stu.getId() != 0) {
                tfIDStu.setText(Integer.toString(stu.getId()));
                tfNameStu.setText((stu.getName()));
                tfAgeStu.setText(Integer.toString(stu.getAge()));
                tfGenderStu.setText((stu.getGender()));
                tfCourseStu.setText(stu.getCourse());
                tfSemesterStu.setText(Integer.toString(stu.getSemester()));
                tfDeptChoiceStu.setText(Integer.toString(stu.getDeptChoice()));
                btnDeleteStu.setDisable(false);
                btnUpdateStu.setDisable(false);

                //if no matching Student ID was found
            } else {
                System.out.println("------------------------------------");
                System.out.println("no matching student ID found!");
                btnDeleteStu.setDisable(true);
                btnUpdateStu.setDisable(true);

            }

            //if user doesn't entered a valid Student Id and it throws NumberFormatException
        } catch (NumberFormatException ex) {
            System.out.println("------------------------------------");
            System.out.println("Must enter a valid student ID!");
            btnDeleteStu.setDisable(true);
            btnUpdateStu.setDisable(true);

        }
    }
    
     //method to display ALL students 
    @FXML
    public void displayStudents(ObservableList<Student> stuArr) {

        idColStu.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColStu.setCellValueFactory(new PropertyValueFactory<>("Name"));
        ageColStu.setCellValueFactory(new PropertyValueFactory<>("Age"));
        genderColStu.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        courseColStu.setCellValueFactory(new PropertyValueFactory<>("Course"));
        semesterColStu.setCellValueFactory(new PropertyValueFactory<>("Semester"));
        departmentIdColStu.setCellValueFactory(new PropertyValueFactory<>("DeptChoice"));
        tvStu.setItems(stuArr);

    }
    
    //mehtod to display information of specific students using their student ID.
    public void displayViewStudent(ObservableList<Student> stuArr) {

        //Counters
        displayViewStuCount = 0;
        
        try {
            
            //adds to value of original arrayList to temporary arrayList.
            for (Student stu : stuArr) {
                tempStudentList.add(stu);
                //checks if the Student ID entered matches with one in the arrayList.
                if (stu.getId() != Integer.parseInt(tfIDSearchStu.getText())) {

                    displayViewStuCount++;
                }
            }
                //checks if their were no matches in the arrayList.
                if (displayViewStuCount == tempStudentList.size()) {
                System.out.println("------------------------------------");
                System.out.println("No matching student ID Found");
            }

                idColStu.setCellValueFactory(new PropertyValueFactory<>("id"));
                nameColStu.setCellValueFactory(new PropertyValueFactory<>("Name"));
                ageColStu.setCellValueFactory(new PropertyValueFactory<>("Age"));
                genderColStu.setCellValueFactory(new PropertyValueFactory<>("Gender"));
                courseColStu.setCellValueFactory(new PropertyValueFactory<>("Course"));
                semesterColStu.setCellValueFactory(new PropertyValueFactory<>("Semester"));
                departmentIdColStu.setCellValueFactory(new PropertyValueFactory<>("DeptChoice"));
                tvStu.setItems(tempStudentList);

                for (int i = tempStudentList.size() - 1; i >= 0; i--) {

                    //checks if the Student ID entered matches with one in the arrayList, if not, it removes that entry from the table.
                    if (tempStudentList.get(i).getId() == Integer.parseInt(tfIDSearchStu.getText())) {

                    } else {
                        tvStu.getItems().remove(i);
                    }

                }
            //if user doesn't entered a valid Student Id and it throws NumberFormatException
        } catch (NumberFormatException ex) {
            System.out.println("------------------------------------");
            System.out.println("Must enter a student ID!");

        }
    }
}
