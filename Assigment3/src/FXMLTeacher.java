
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
public class FXMLTeacher extends FXMLMainMenu {

    //Table Columns
    @FXML
    private TableColumn<?, ?> idColTeach;
    @FXML
    private TableColumn<?, ?> nameColTeach;
    @FXML
    private TableColumn<?, ?> ageColTeach;
    @FXML
    private TableColumn<?, ?> genderColTeach;
    @FXML
    private TableColumn<?, ?> specialtyColTeach;
    @FXML
    private TableColumn<?, ?> degreeColTeach;
    @FXML
    private TableColumn<?, ?> salaryColTeach;
    @FXML
    private TableColumn<?, ?> departmentIDColTeach;
    @FXML
    private TableColumn<?, ?> isDeanTeach;
    
    //TableViews
    @FXML
    private TableView<Teacher> tvTeach;
    @FXML
    private TextField tfDeanTeach;
    
    //Buttons
    @FXML
    private Button refreshTeachBtn;
    @FXML
    private Button btnAddTeach;
    @FXML
    private Button btnUpdateTeach;
    @FXML
    private Button btnDeleteTeach;
    @FXML
    private Button btnSearchTeach;
    @FXML
    private Button btnSearchViewTeach;
    @FXML
    private Button backToTeachMenuBtn;
    @FXML
    private Button btnAddDean;
    @FXML
    private Button btnDeleteDean;
 
    //TextFields
    @FXML
    private TextField tfIDTeacher;
    @FXML
    private TextField tfNameTeacher;
    @FXML
    private TextField tfAgeTeacher;
    @FXML
    private TextField tfGenderTeacher;
    @FXML
    private TextField tfSpecialtyTeacher;
    @FXML
    private TextField tfDegreeTeacher;
    @FXML
    private TextField tfDeptChoiceTeacher;
    @FXML
    private TextField tfIDSearchTeach;

    //Counters
    private boolean addTeachCheckCounter;
    private int teachdepChoiceCounter;
    private boolean deleteTeachCheckCounter;
    private boolean updateTeachCheckCounter;
    private int displayViewTeachCount;
    
    private int addDeanCheckCounter;
    private int addDeanCheckCounter2;
    private int deleteDeanCheckCounter;
    
    //Class Objects
    public static CreateTeacher createTeach = new CreateTeacher();
    public static FXMLTeacherMenu teachMenu = new FXMLTeacherMenu();

    //Temporary ObservableArrayList
    private ObservableList<Teacher> tempTeacherList = FXCollections.observableArrayList();

    
    @Override
    public void initialize(URL url, ResourceBundle rb) throws NullPointerException {
        //using .fire() method to display table information when scene is opened
        refreshTeachBtn.fire();
    }

    //Method for when a button is pressed in the view Teacher, and Manage Teacher menus.
    @FXML
    private void mainTeachButton(ActionEvent event) throws IOException {

        //Checks if Add Teacher button is pressed
        if (event.getSource() == btnAddTeach) {
            addTeacher();
        }
        //Checks if Delete Teacher button is pressed
        if (event.getSource() == btnDeleteTeach) {
            deleteTeacher();
        }
        //Checks if Update Teacher button is pressed
        if (event.getSource() == btnUpdateTeach) {
            updateTeacher();
        }
        //Checks if Search Teacher button is pressed
        if (event.getSource() == btnSearchTeach) {
            searchTeacher();
        }
        //Checks if refresh button is pressed
        if (event.getSource() == refreshTeachBtn) {
            displayTeachers(Department.TeacherList);
            tvTeach.refresh();
            /*will disable buttons and clear table values when refresh button is pressed
            but only when in manage teacher scene, else user is in view teacher scene, and doesn't need these options*/
            if (teachMenu.getSceneCheck() == true) {
                tfIDTeacher.clear();
                tfNameTeacher.clear();
                tfAgeTeacher.clear();
                tfGenderTeacher.clear();
                tfSpecialtyTeacher.clear();
                tfDegreeTeacher.clear();
                tfDeptChoiceTeacher.clear();

                btnDeleteTeach.setDisable(true);
                btnUpdateTeach.setDisable(true);
            }
        }
        //Checks if search Teacher in view menu button is pressed
        if (event.getSource() == btnSearchViewTeach) {
            tempTeacherList.clear();
            displayViewTeacher(Department.TeacherList);
        }
        //Checks if back button is pressed
        if (event.getSource() == backToTeachMenuBtn) {
            try {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("TeacherMenu.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (NullPointerException ex) {
                System.out.println("TeacherMenu scene file not found!");
                ex.printStackTrace();
            }
        }
        //Checks if add Dean button is pressed
        if (event.getSource() == btnAddDean) {
            addDean();
        }
        //Checks if delete Dean button is pressed
        if (event.getSource() == btnDeleteDean) {
            deleteDean();
        }

    }

    //method to add teachers to the arrayList and the file.
    @FXML
    public void addTeacher() throws IOException, FileNotFoundException {

        //Counters
        addTeachCheckCounter = false;
        teachdepChoiceCounter = 0;

        try {
            for (Teacher teach : Department.TeacherList) {
                //checks if the Teacher Id entered by the user already exists
                if (Integer.parseInt(tfIDTeacher.getText()) == teach.getId()) {
                    System.out.println("------------------------------------");
                    System.out.println("This teacher ID already exists!");
                    addTeachCheckCounter = true;
                    break;

                }

            }

            for (Department dep : Department.DepartmentList) {
                //checks if the department choice enterd by the user is a department id that exists.
                if (Integer.parseInt(tfDeptChoiceTeacher.getText()) != dep.getId()) {

                    teachdepChoiceCounter++;

                }
            }
            
            //checks if the Teacher Id entered by the user already exists and if the department choice enterd by the user is a department id that exists.
            if (addTeachCheckCounter == false && teachdepChoiceCounter < Department.DepartmentList.size()) {
                //Checks if user entered teacher degree is equal to a valid degree.
                if ("phd".equals(tfDegreeTeacher.getText()) || "bachelor".equals(tfDegreeTeacher.getText()) || "master".equals(tfDegreeTeacher.getText())) {
                    
                    Teacher teach = (new Teacher(Integer.parseInt(tfIDTeacher.getText()), tfNameTeacher.getText(), Integer.parseInt(tfAgeTeacher.getText()), tfGenderTeacher.getText(), tfSpecialtyTeacher.getText(), tfDegreeTeacher.getText(), Integer.parseInt(tfDeptChoiceTeacher.getText())));
                    Department.TeacherList.add(teach);

                    String path = "C:\\Users\\ben_1\\Desktop\\FinalProject\\Teacher.txt";

                    //calling append to teacher method to write to teacher file.
                    createTeach.AppendToTeacher(teach.toString(), path);
                    System.out.println("------------------------------------");
                    System.out.println("Teacher Added!");
                    
                    //if user enters non valid tecaher degree.
                } else {
                    System.out.println("------------------------------------");
                    System.out.println("Must enter valid teacher degree");

                }

                //checks if the department choice enterd by the user is a department id that exists.
                if (teachdepChoiceCounter == Department.DepartmentList.size()) {
                    System.out.println("------------------------------------");
                    System.out.println("You must add teacher to exisitng Department!");
                }
            }

            //if user doesn't fill all textfields it throws NumberFormatException.
        } catch (NumberFormatException ex) {
            System.out.println("------------------------------------");
            System.out.println("Must enter all teacher values!");
        }

    }

    //method to delete teachers form the arrayList and the file
    @FXML
    public void deleteTeacher() throws IOException, FileNotFoundException {

        //Counters
        deleteTeachCheckCounter = false;

        try {
            for (int i = 0; i < Department.TeacherList.size(); i++) {
                //checks if the teacher id entered by the user matches with one in the arrayList.
                if (Department.TeacherList.get(i).getId() == Integer.parseInt(tfIDTeacher.getText())) {
                    //calls method deleteTeacher using the teacher that the user selected as argument.
                    createTeach.deleteTeacher(Department.TeacherList.get(i).toString());
                    Department.TeacherList.remove(i);
                    System.out.println("------------------------------------");
                    System.out.println("Teacher deleted!");
                    deleteTeachCheckCounter = true;

                }
            }

            //checks if the Teacher Id entered by the user was not found
            if (deleteTeachCheckCounter == false) {
                System.out.println("------------------------------------");
                System.out.println("No matching teacher ID found");
            }
            //if user doesn't entered a valid Teacher Id and it throws NumberFormatException.
        } catch (NumberFormatException ex) {
            System.out.println("------------------------------------");
            System.out.println("Must enter valid teacher ID!");
        }
    }

    //method to update teachers from the arrayList and the file.
    @FXML
    public void updateTeacher() throws IOException, FileNotFoundException {

        //Counters
        updateTeachCheckCounter = false;

        try {

            //Checks if user entered teacher degree is equal to a valid degree.
            if ("phd".equals(tfDegreeTeacher.getText()) || "bachelor".equals(tfDegreeTeacher.getText()) || "master".equals(tfDegreeTeacher.getText())) {
                
                Teacher teach = (new Teacher(Integer.parseInt(tfIDTeacher.getText()), tfNameTeacher.getText(), Integer.parseInt(tfAgeTeacher.getText()), tfGenderTeacher.getText(), tfSpecialtyTeacher.getText(), tfDegreeTeacher.getText(), Integer.parseInt(tfDeptChoiceTeacher.getText())));
                for (int i = 0; i < Department.TeacherList.size(); i++) {
                    //checks if the teacher id entered by the user matches with one in the arrayList.
                    if (Department.TeacherList.get(i).getId() == Integer.parseInt(tfIDTeacher.getText())) {
                        String newLine = Department.TeacherList.get(i).toString();
                        Department.TeacherList.set(i, teach);
                        /*calls method updateTeacher using the teacher that the new information of the update teacher, 
                        as well as the old information to locate the linein the file*/
                        createTeach.updateTeacher(newLine, teach.toString());
                        System.out.println("------------------------------------");
                        System.out.println("Teacher updated!");
                        updateTeachCheckCounter = true;
                    }
                }

                //checks if the Teacher Id entered by the user was not found
                if (updateTeachCheckCounter == false) {
                    System.out.println("------------------------------------");
                    System.out.println("No matching teacher ID found");
                }
                //if user enters non valid tecaher degree.
            } else {
                System.out.println("------------------------------------");
                System.out.println("Must enter valid teacher degree");

            }

            //if user doesn't entered a valid Teacher Id and it throws NumberFormatException.
        } catch (NumberFormatException ex) {
            System.out.println("------------------------------------");
            System.out.println("Must enter valid teacher ID!");

        }
    }

    //method to search through arrayList to find specific teacher.
    @FXML
    public void searchTeacher() {

        try {
            Teacher teach = new Teacher();
            for (int i = 0; i < Department.TeacherList.size(); i++) {
                
                //checks if the Teacher ID enterd by user matches an ID in the arrayList.
                if (Department.TeacherList.get(i).getId() == Integer.parseInt(tfIDTeacher.getText())) {
                    teach.setId(Department.TeacherList.get(i).getId());
                    teach.setName(Department.TeacherList.get(i).getName());
                    teach.setAge(Department.TeacherList.get(i).getAge());
                    teach.setGender(Department.TeacherList.get(i).getGender());
                    teach.setSpecialty(Department.TeacherList.get(i).getSpecialty());
                    teach.setDegree(Department.TeacherList.get(i).getDegree());
                    teach.setDeptChoice(Department.TeacherList.get(i).getDeptChoice());
                    System.out.println("------------------------------------");
                    System.out.println("Teacher found!");
                }

            }
            //checks if the Teacher ID is not 0, then fills text filed values of that teacher.
            if (teach.getId() != 0) {
                tfIDTeacher.setText(Integer.toString(teach.getId()));
                tfNameTeacher.setText((teach.getName()));
                tfAgeTeacher.setText(Integer.toString(teach.getAge()));
                tfGenderTeacher.setText((teach.getGender()));
                tfSpecialtyTeacher.setText(teach.getSpecialty());
                tfDegreeTeacher.setText(teach.getDegree());
                tfDeptChoiceTeacher.setText(Integer.toString(teach.getDeptChoice()));
                btnDeleteTeach.setDisable(false);
                btnUpdateTeach.setDisable(false);

                //if no matching Teacher ID was found
            } else {
                System.out.println("------------------------------------");
                System.out.println("no matching teacher ID found!");
                btnDeleteTeach.setDisable(true);
                btnUpdateTeach.setDisable(true);
            }

            //if user doesn't entered a valid Teacher Id and it throws NumberFormatException
        } catch (NumberFormatException ex) {
            System.out.println("------------------------------------");
            System.out.println("Must enter a valid teacher ID!");
            btnDeleteTeach.setDisable(true);
            btnUpdateTeach.setDisable(true);

        }
    }
    
    //method to display ALL teachers 
    public void displayTeachers(ObservableList<Teacher> teachArr) {

        idColTeach.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColTeach.setCellValueFactory(new PropertyValueFactory<>("Name"));
        ageColTeach.setCellValueFactory(new PropertyValueFactory<>("Age"));
        genderColTeach.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        specialtyColTeach.setCellValueFactory(new PropertyValueFactory<>("Specialty"));
        degreeColTeach.setCellValueFactory(new PropertyValueFactory<>("Degree"));
        salaryColTeach.setCellValueFactory(new PropertyValueFactory<>("Salary"));
        departmentIDColTeach.setCellValueFactory(new PropertyValueFactory<>("DeptChoice"));
        isDeanTeach.setCellValueFactory(new PropertyValueFactory<>("Dean"));
        tvTeach.setItems(teachArr);
    }

    //mehtod to display information of specific teachers using their teacher ID.
    public void displayViewTeacher(ObservableList<Teacher> teachArr) {

        //Counters
        displayViewTeachCount = 0;
            
        try {

            //adds to value of original arrayList to temporary arrayList.
            for (Teacher teach : teachArr) {
                tempTeacherList.add(teach);
                //checks if the Teacher ID entered matches with one in the arrayList.
                if (teach.getId() != Integer.parseInt(tfIDSearchTeach.getText())) {

                    displayViewTeachCount++;
                }
            }
            //checks if their were no matches in the arrayList.
            if (displayViewTeachCount == tempTeacherList.size()) {
                System.out.println("------------------------------------");
                System.out.println("No matching teacher ID Found");
            }

            idColTeach.setCellValueFactory(new PropertyValueFactory<>("id"));
            nameColTeach.setCellValueFactory(new PropertyValueFactory<>("Name"));
            ageColTeach.setCellValueFactory(new PropertyValueFactory<>("Age"));
            genderColTeach.setCellValueFactory(new PropertyValueFactory<>("Gender"));
            specialtyColTeach.setCellValueFactory(new PropertyValueFactory<>("Specialty"));
            degreeColTeach.setCellValueFactory(new PropertyValueFactory<>("Degree"));
            salaryColTeach.setCellValueFactory(new PropertyValueFactory<>("Salary"));
            departmentIDColTeach.setCellValueFactory(new PropertyValueFactory<>("DeptChoice"));
            isDeanTeach.setCellValueFactory(new PropertyValueFactory<>("Dean"));
            tvTeach.setItems(tempTeacherList);

            for (int i = tempTeacherList.size() - 1; i >= 0; i--) {

                //checks if the Teacher ID entered matches with one in the arrayList, if not, it removes that entry from the table.
                if (tempTeacherList.get(i).getId() == Integer.parseInt(tfIDSearchTeach.getText())) {

                } else {
                    tvTeach.getItems().remove(i);
                }

            }

            //if user doesn't entered a valid Teacher Id and it throws NumberFormatException
        } catch (NumberFormatException ex) {
            System.out.println("------------------------------------");
            System.out.println("Must enter a teacher ID!");

        }

    }

    
    //Method to add a dean to department
    @FXML
    public void addDean() {

        //Counters
        addDeanCheckCounter = 0;
        addDeanCheckCounter2 = 0;

        try {

            for (Teacher teach : Department.TeacherList) {
                addDeanCheckCounter++;
                //checks if Teacher Id entered by user matches with one in the arrayList.
                if (teach.getId() == Integer.parseInt(tfDeanTeach.getText())) {
                    addDeanCheckCounter = 0;
                    for (Department dep : Department.DepartmentList) {
                        addDeanCheckCounter2++;
                        //checks if the teacher's current department ID matches with a department ID in the department arrayList
                        if (dep.getId() == teach.getDeptChoice()) {
                            //checks if that department doesn't have a dean
                            if (dep.getDean() == null) {
                                
                                dep.setDean(teach);
                                tvTeach.refresh();
                                System.out.println("------------------------------------");
                                System.out.println("Dean has been set!");

                                break;
                                //if the department already has dean set.
                            } else {
                                System.out.println("------------------------------------");
                                System.out.println("There can only be one dean in this Department!");
                                break;
                            }
                        }
                    }

                }
            }

            //if the teacher ID entered by the user doesn't match with one in the arrayList
            if (addDeanCheckCounter == Department.TeacherList.size()) {
                System.out.println("------------------------------------");
                System.out.println("no matching teacher ID found!!");
            }
            //if the user attempts to add dean to department that does not exist.
            if (addDeanCheckCounter2 == Department.TeacherList.size()) {
                System.out.println("------------------------------------");
                System.out.println("teacher can only be dean of their current Department!");
            }

            //if user doesn't entered a valid Teacher Id and it throws NumberFormatException
        } catch (NumberFormatException ex) {
            System.out.println("------------------------------------");
            System.out.println("Must enter a teacher ID!");
        }
    }

    //Method to remove dean from department.
    @FXML
    public void deleteDean() {

        //Counters
        deleteDeanCheckCounter = 0;

        try {

            for (Teacher teach : Department.TeacherList) {
                deleteDeanCheckCounter++;
                //checks if Teacher Id entered by user matches with one in the arrayList.
                if (teach.getId() == Integer.parseInt(tfDeanTeach.getText())) {
                    deleteDeanCheckCounter = 0;
                    for (Department dep : Department.DepartmentList) {
                        //checks if the teacher's current department ID matches with a department ID in the department arrayList
                        if (dep.getId() == teach.getDeptChoice()) {
                            //checks if that department already has a dean
                            if (dep.getDean() != null) {
                                dep.setDean(null);
                                tvTeach.refresh();
                                System.out.println("------------------------------------");
                                System.out.println("Dean has been removed!");
                                break;
                                //if the department doesn't have a dean set.
                            } else {
                                System.out.println("------------------------------------");
                                System.out.println("this teacher is not dean!");
                                break;
                            }

                        }
                    }
                }
            }

            //if the teacher ID entered by the user doesn't match with one in the arrayList
            if (deleteDeanCheckCounter == Department.TeacherList.size()) {
                System.out.println("------------------------------------");
                System.out.println("no matching teacher ID found!!");
            }

            //if user doesn't entered a valid Teacher Id and it throws NumberFormatException
        } catch (NumberFormatException ex) {
            System.out.println("------------------------------------");
            System.out.println("Must enter a teacher ID!");
        }

    }
}
