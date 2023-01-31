
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
public class FXMLStaff extends FXMLMainMenu {

    //Table Columns
    @FXML
    private TableColumn<?, ?> idColStaff;
    @FXML
    private TableColumn<?, ?> nameColStaff;
    @FXML
    private TableColumn<?, ?> ageColStaff;
    @FXML
    private TableColumn<?, ?> genderColStaff;
    @FXML
    private TableColumn<?, ?> dutyColStaff;
    @FXML
    private TableColumn<?, ?> workloadColStaff;
    @FXML
    private TableColumn<?, ?> salaryColStaff;
    @FXML
    private TableColumn<?, ?> departmentIDColStaff;

    //TableView
    @FXML
    private TableView<Staff> tvStaff;

    //Buttons
    @FXML
    private Button refreshStaffBtn;
    @FXML
    private Button btnAddStaff;
    @FXML
    private Button btnUpdateStaff;
    @FXML
    private Button btnDeleteStaff;
    @FXML
    private Button btnSearchViewStaff;
    @FXML
    private Button btnSearchStaff;
    @FXML
    private Button backToStaffMenuBtn;

    //TextFields
    @FXML
    private TextField tfIDStaff;
    @FXML
    private TextField tfNameStaff;
    @FXML
    private TextField tfAgeStaff;
    @FXML
    private TextField tfGenderStaff;
    @FXML
    private TextField tfDutyStaff;
    @FXML
    private TextField tfWorkloadStaff;
    @FXML
    private TextField tfDeptChoiceStaff;
    @FXML
    private TextField tfIDSearchStaff;

    //Counters
    private boolean addStaffCheckCounter;
    private int staffdepChoiceCounter;
    private boolean deleteStaffCheckCounter;
    private boolean updateStaffCheckCounter;
    private int displayViewStaffCount;

    //Class Objects
    public static CreateStaff createStaff = new CreateStaff();
    public static FXMLStaffMenu staffMenu = new FXMLStaffMenu();

    //Temporary ObservableArrayList
    private ObservableList<Staff> tempStaffList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) throws NullPointerException {
        //using .fire() method to display table information when scene is opened
        refreshStaffBtn.fire();
    }

    //Method for when a button is pressed in the view Staff, and Manage Staff menus.
    @FXML
    private void mainStaffButton(ActionEvent event) throws IOException {

        //Checks if Add Staff button is pressed
        if (event.getSource() == btnAddStaff) {
            addStaff();
        }
        //Checks if Delete Staff button is pressed
        if (event.getSource() == btnDeleteStaff) {
            deleteStaff();
        }
        //Checks if Update Staff button is pressed
        if (event.getSource() == btnUpdateStaff) {
            updateStaff();
        }
        //Checks if Search Staff button is pressed
        if (event.getSource() == btnSearchStaff) {
            searchStaff();
        }
        //Checks if refresh button is pressed
        if (event.getSource() == refreshStaffBtn) {
            displayStaff(Department.StaffList);
            tvStaff.refresh();
            /*will disable buttons and clear table values when refresh button is pressed
            but only when in manage staff scene, else user is in view staff scene, and doesn't need these options*/
            if (staffMenu.getSceneCheck() == true) {
                tfIDStaff.clear();
                tfNameStaff.clear();
                tfAgeStaff.clear();
                tfGenderStaff.clear();
                tfDutyStaff.clear();
                tfWorkloadStaff.clear();
                tfDeptChoiceStaff.clear();

                btnDeleteStaff.setDisable(true);
                btnUpdateStaff.setDisable(true);
            }
        }
        //Checks if search Staff in view menu button is pressed
        if (event.getSource() == btnSearchViewStaff) {
            tempStaffList.clear();
            displayViewStaff(Department.StaffList);
        }
        //Checks if back button is pressed
        if (event.getSource() == backToStaffMenuBtn) {
            try {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("StaffMenu.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (NullPointerException ex) {
                System.out.println("StaffMenu scene file not found!");
                ex.printStackTrace();
            }
        }
    }

    //method to add staffs to the arrayList and the file.
    @FXML
    public void addStaff() throws IOException, FileNotFoundException {

        //Counters
        addStaffCheckCounter = false;
        staffdepChoiceCounter = 0;

        try {
            for (Staff staff : Department.StaffList) {
                //checks if the Staff Id entered by the user already exists
                if (Integer.parseInt(tfIDStaff.getText()) == staff.getId()) {
                    System.out.println("------------------------------------");
                    System.out.println("This staff ID already exists!");
                    addStaffCheckCounter = true;
                    break;

                }

            }

            for (Department dep : Department.DepartmentList) {
                //checks if the department choice enterd by the user is a department id that exists.
                if (Integer.parseInt(tfDeptChoiceStaff.getText()) != dep.getId()) {

                    staffdepChoiceCounter++;

                }
            }

            //checks if the Staff Id entered by the user already exists and if the department choice enterd by the user is a department id that exists.
            if (addStaffCheckCounter == false && staffdepChoiceCounter < Department.DepartmentList.size()) {
                if (Integer.parseInt(tfWorkloadStaff.getText()) <= 36) {

                    Staff staff = (new Staff(Integer.parseInt(tfIDStaff.getText()), tfNameStaff.getText(), Integer.parseInt(tfAgeStaff.getText()), tfGenderStaff.getText(), tfDutyStaff.getText(), Integer.parseInt(tfWorkloadStaff.getText()), Integer.parseInt(tfDeptChoiceStaff.getText())));
                    Department.StaffList.add(staff);

                    String path = "C:\\Users\\ben_1\\Desktop\\FinalProject\\Staff.txt";

                    //calling append to staff method to write to staff file.
                    createStaff.AppendToStaff(staff.toString(), path);
                    System.out.println("------------------------------------");
                    System.out.println("Staff Added!");
                    
                    //if workload is greater than 36.
                } else {
                    System.out.println("------------------------------------");
                    System.out.println("workload must be 36 hours or under!");
                }

            }
            //checks if the department choice enterd by the user is a department id that exists.
            if (staffdepChoiceCounter == Department.DepartmentList.size()) {
                System.out.println("------------------------------------");
                System.out.println("You must add staff to exisitng Department!");
            }

            //if user doesn't fill all textfields it throws NumberFormatException.
        } catch (NumberFormatException ex) {
            System.out.println("------------------------------------");
            System.out.println("Must enter all staff values!");
        }
    }

    //method to delete staffs form the arrayList and the file
    @FXML
    public void deleteStaff() throws IOException, FileNotFoundException {

        //Counters
        deleteStaffCheckCounter = false;

        try {

            for (int i = 0; i < Department.StaffList.size(); i++) {
                //checks if the staff id entered by the user matches with one in the arrayList.
                if (Department.StaffList.get(i).getId() == Integer.parseInt(tfIDStaff.getText())) {
                    //calls method deletestaff using the staff that the user selected as argument.
                    createStaff.deleteStaff(Department.StaffList.get(i).toString());
                    Department.StaffList.remove(i);
                    System.out.println("------------------------------------");
                    System.out.println("Staff deleted!");
                    deleteStaffCheckCounter = true;

                }
            }

            //checks if the staff Id entered by the user was not found
            if (deleteStaffCheckCounter == false) {
                System.out.println("------------------------------------");
                System.out.println("No matching staff ID found");
            }
            //if user doesn't entered a valid staff Id and it throws NumberFormatException.
        } catch (NumberFormatException ex) {
            System.out.println("------------------------------------");
            System.out.println("Must enter valid staff ID!");
        }
    }

    //method to update staffs from the arrayList and the file.
    @FXML
    public void updateStaff() throws IOException, FileNotFoundException {

        //Counters
        updateStaffCheckCounter = false;

        try {
            //checks if staff workload is 36 hours or under.
            if (Integer.parseInt(tfWorkloadStaff.getText()) <= 36) {
                Staff staff = (new Staff(Integer.parseInt(tfIDStaff.getText()), tfNameStaff.getText(), Integer.parseInt(tfAgeStaff.getText()), tfGenderStaff.getText(), tfDutyStaff.getText(), Integer.parseInt(tfWorkloadStaff.getText()), Integer.parseInt(tfDeptChoiceStaff.getText())));
                for (int i = 0; i < Department.StaffList.size(); i++) {
                    //checks if the staff id entered by the user matches with one in the arrayList.
                    if (Department.StaffList.get(i).getId() == Integer.parseInt(tfIDStaff.getText())) {

                        String newLine = Department.StaffList.get(i).toString();
                        Department.StaffList.set(i, staff);
                        /*calls method updateStaff using the staff that the new information of the update staff, 
                    as well as the old information to locate the linein the file*/
                        createStaff.updateStaff(newLine, staff.toString());
                        System.out.println("------------------------------------");
                        System.out.println("Staff updated!");
                        updateStaffCheckCounter = true;
                    }
                }

                //checks if the Staff Id entered by the user was not found
                if (updateStaffCheckCounter == false) {
                    System.out.println("------------------------------------");
                    System.out.println("No matching staff ID found");
                }
                //if workload is greater than 36.
            } else {
                System.out.println("------------------------------------");
                System.out.println("workload must be 36 hours or under!");
            }

            //if user doesn't entered a valid Staff Id and it throws NumberFormatException.
        } catch (NumberFormatException ex) {
            System.out.println("------------------------------------");
            System.out.println("Must enter valid staff ID!");

        }
    }

    //method to search through arrayList to find specific staff.
    @FXML
    public void searchStaff() {
        try {
            Staff staff = new Staff();
            for (int i = 0; i < Department.StaffList.size(); i++) {
                //checks if the Staff ID enterd by user matches an ID in the arrayList.
                if (Department.StaffList.get(i).getId() == Integer.parseInt(tfIDStaff.getText())) {
                    staff.setId(Department.StaffList.get(i).getId());
                    staff.setName(Department.StaffList.get(i).getName());
                    staff.setAge(Department.StaffList.get(i).getAge());
                    staff.setGender(Department.StaffList.get(i).getGender());
                    staff.setDuty(Department.StaffList.get(i).getDuty());
                    staff.setWorkload(Department.StaffList.get(i).getWorkload());
                    staff.setDeptChoice((Department.StaffList.get(i).getDeptChoice()));
                    System.out.println("------------------------------------");
                    System.out.println("Staff found!");
                }

            }
            //checks if the Staff ID is not 0, then fills text filed values of that staff.
            if (staff.getId() != 0) {
                tfIDStaff.setText(Integer.toString(staff.getId()));
                tfNameStaff.setText((staff.getName()));
                tfAgeStaff.setText(Integer.toString(staff.getAge()));
                tfGenderStaff.setText((staff.getGender()));
                tfDutyStaff.setText(staff.getDuty());
                tfWorkloadStaff.setText(Integer.toString(staff.getWorkload()));
                tfDeptChoiceStaff.setText(Integer.toString(staff.getDeptChoice()));
                btnDeleteStaff.setDisable(false);
                btnUpdateStaff.setDisable(false);

            } else {
                //if no matching Staff ID was found
                System.out.println("------------------------------------");
                System.out.println("no matching staff ID found!");
                System.out.println("no Staff found!");
                btnDeleteStaff.setDisable(true);
                btnUpdateStaff.setDisable(true);

            }

            //if user doesn't entered a valid Staff Id and it throws NumberFormatException
        } catch (NumberFormatException ex) {
            System.out.println("------------------------------------");
            System.out.println("Must enter a valid staff ID!");
            btnDeleteStaff.setDisable(true);
            btnUpdateStaff.setDisable(true);

        }
    }

    //method to display ALL staffs 
    @FXML
    public void displayStaff(ObservableList<Staff> staffArr) throws IOException {

        idColStaff.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColStaff.setCellValueFactory(new PropertyValueFactory<>("Name"));
        ageColStaff.setCellValueFactory(new PropertyValueFactory<>("Age"));
        genderColStaff.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        dutyColStaff.setCellValueFactory(new PropertyValueFactory<>("duty"));
        workloadColStaff.setCellValueFactory(new PropertyValueFactory<>("workload"));
        salaryColStaff.setCellValueFactory(new PropertyValueFactory<>("Salary"));
        departmentIDColStaff.setCellValueFactory(new PropertyValueFactory<>("DeptChoice"));
        tvStaff.setItems(staffArr);
    }

    //mehtod to display information of specific staffs using their staff ID.
    public void displayViewStaff(ObservableList<Staff> staffArr) throws IOException {

        //Counters
        displayViewStaffCount = 0;

        try {

            //adds to value of original arrayList to temporary arrayList.
            for (Staff staff : staffArr) {
                tempStaffList.add(staff);
                //checks if the Staff ID entered matches with one in the arrayList.
                if (staff.getId() != Integer.parseInt(tfIDSearchStaff.getText())) {

                    displayViewStaffCount++;
                }
            }
            //checks if their were no matches in the arrayList.
            if (displayViewStaffCount == tempStaffList.size()) {
                System.out.println("------------------------------------");
                System.out.println("No matching staff ID Found");
            }

            idColStaff.setCellValueFactory(new PropertyValueFactory<>("id"));
            nameColStaff.setCellValueFactory(new PropertyValueFactory<>("Name"));
            ageColStaff.setCellValueFactory(new PropertyValueFactory<>("Age"));
            genderColStaff.setCellValueFactory(new PropertyValueFactory<>("Gender"));
            dutyColStaff.setCellValueFactory(new PropertyValueFactory<>("Duty"));
            workloadColStaff.setCellValueFactory(new PropertyValueFactory<>("Workload"));
            salaryColStaff.setCellValueFactory(new PropertyValueFactory<>("Salary"));
            departmentIDColStaff.setCellValueFactory(new PropertyValueFactory<>("DeptChoice"));
            tvStaff.setItems(tempStaffList);

            for (int i = tempStaffList.size() - 1; i >= 0; i--) {

                //checks if the Staff ID entered matches with one in the arrayList, if not, it removes that entry from the table.
                if (tempStaffList.get(i).getId() == Integer.parseInt(tfIDSearchStaff.getText())) {

                } else {
                    tvStaff.getItems().remove(i);
                }

            }
            //if user doesn't entered a valid Staff Id and it throws NumberFormatException
        } catch (NumberFormatException ex) {
            System.out.println("------------------------------------");
            System.out.println("Must enter a staff ID!");

        }

    }
}
