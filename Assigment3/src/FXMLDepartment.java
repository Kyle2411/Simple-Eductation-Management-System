
import java.io.FileNotFoundException;
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
public class FXMLDepartment extends FXMLMainMenu {

    //Table Columns
    @FXML
    private TableColumn<?, ?> idColDepartment;
    @FXML
    private TableColumn<?, ?> descriptionColDepartment;

    //TableView
    @FXML
    private TableView<Department> tvDepartment;

    //Buttons
    @FXML
    private Button refreshDepBtn;
    @FXML
    private Button btnAddDep;
    @FXML
    private Button btnUpdateDep;
    @FXML
    private Button btnDeleteDep;
    @FXML
    private Button btnSearchDep;
    @FXML
    private Button backToDepMenuBtn;
    @FXML
    private Button searchSpecificDepBtn;

    //TextFields
    @FXML
    private TextField tfIDDepartment;
    @FXML
    private TextField tfDescriptionDep;

    //Counters
    private int displayViewDepCount;
    private boolean addDepCheckCounter;
    private boolean deleteDepCheckCounter;
    private boolean updateDepCheckCounter;
    public static int depCheck;

    //Class Objects
    public static CreateDepartment createDep = new CreateDepartment();
    public static FXMLDepartmentMenu depMenu = new FXMLDepartmentMenu();

    @Override
    public void initialize(URL url, ResourceBundle rb) throws NullPointerException {
        //using .fire() method to display table information when scene is opened
        refreshDepBtn.fire();

    }

    //Method for when a button is pressed in the view department, and Manage department menus.
    @FXML
    private void mainDepButton(ActionEvent event) throws IOException {

        //Checks if Add department button is pressed
        if (event.getSource() == btnAddDep) {
            addDepartment();
        }
        //Checks if Delete department button is pressed
        if (event.getSource() == btnDeleteDep) {
            deleteDepartment();
        }
        //Checks if Update department button is pressed
        if (event.getSource() == btnUpdateDep) {
            updateDepartment();
        }
        //Checks if Search department button is pressed
        if (event.getSource() == btnSearchDep) {
            searchDepartment();
        }
        //Checks if refresh button is pressed
        if (event.getSource() == refreshDepBtn) {
            displayDepartments(Department.DepartmentList);
            tvDepartment.refresh();
            /*will disable buttons and clear table values when refresh button is pressed
            but only when in manage department scene, else user is in view department scene, and doesn't need these options*/
            if (depMenu.getSceneCheck() == true) {
                tfIDDepartment.clear();
                tfDescriptionDep.clear();

                btnDeleteDep.setDisable(true);
                btnUpdateDep.setDisable(true);
            }

        }
        //Checks if search specific department button is pressed
        if (event.getSource() == searchSpecificDepBtn) {

            try {

                //Counters
                displayViewDepCount = 0;

                for (Department dep : Department.DepartmentList) {

                    //checks if the Department ID entered matches with one in the arrayList.
                    if (dep.getId() != Integer.parseInt(tfIDDepartment.getText())) {

                        displayViewDepCount++;
                    }
                }
                //checks if their were no matches in the arrayList.
                if (displayViewDepCount == Department.DepartmentList.size()) {
                    System.out.println("------------------------------------");
                    System.out.println("No matching Department ID Found");
                }

                displayDepartments(Department.DepartmentList);

                for (Department dep : Department.DepartmentList) {

                    //checks if the department ID entered by the user matches with one in the arrayList
                    if (dep.getId() == Integer.parseInt(tfIDDepartment.getText())) {

                        try {
                            //switches to viewSpecificDepartment scene.
                            depCheck = dep.getId();//saves value of department ID chosen by user
                            root = FXMLLoader.load(getClass().getClassLoader().getResource("viewSpecificDepartment.fxml"));
                            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();
                            break;

                        } catch (NullPointerException ex) {
                            System.out.println("viewSpecificDepartment scene file not found!");
                            ex.printStackTrace();
                        }
                    }

                }
                //
            } catch (NumberFormatException ex) {
                System.out.println("------------------------------------");
                System.out.println("Must enter a department ID!");
            }
        }
        //Checks if back button is pressed
        if (event.getSource() == backToDepMenuBtn) {
            try {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("DepartmentMenu.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                
                //if user doesn't entered a valid Department Id and it throws NumberFormatException
            } catch (NullPointerException ex) {
                System.out.println("DepartmentMenu scene file not found!");
                ex.printStackTrace();
            }
        }

    }

    //method to add departments to the arrayList and the file.
    @FXML
    public void addDepartment() throws IOException, FileNotFoundException {

        //Counters
        addDepCheckCounter = false;

        try {
            for (Department dep : Department.DepartmentList) {
                //checks if the department Id entered by the user already exists
                if (Integer.parseInt(tfIDDepartment.getText()) == dep.getId()) {
                    System.out.println("------------------------------------");
                    System.out.println("This Department ID already exists!");
                    addDepCheckCounter = true;
                    break;

                }
            }

            //checks if the department Id entered by the user already exists
            if (addDepCheckCounter == false) {

                Department dep = (new Department(Integer.parseInt(tfIDDepartment.getText()), tfDescriptionDep.getText()));
                Department.DepartmentList.add(dep);

                String path = "C:\\Users\\ben_1\\Desktop\\FinalProject\\Department.txt";

                //calling append to department method to write to department file.
                createDep.AppendToDepartment(dep.toString(), path);
                System.out.println("------------------------------------");
                System.out.println("Department Added!");
            }

            //if user doesn't fill all textfields it throws NumberFormatException.
        } catch (NumberFormatException ex) {
            System.out.println("------------------------------------");
            System.out.println("Must enter all Department values!");
        }
    }

    //method to delete departments from the arrayList and the file
    @FXML
    public void deleteDepartment() throws IOException, FileNotFoundException {

        //Counters
        deleteDepCheckCounter = false;

        try {

            for (int i = 0; i < Department.DepartmentList.size(); i++) {
                //checks if the department id entered by the user matches with one in the arrayList.
                if (Department.DepartmentList.get(i).getId() == Integer.parseInt(tfIDDepartment.getText())) {
                    //calls method deleteDepartment using the department that the user selected as argument.
                    createDep.deleteDepartment(Department.DepartmentList.get(i).toString());
                    Department.DepartmentList.remove(i);
                    System.out.println("------------------------------------");
                    System.out.println("Department deleted!");
                    deleteDepCheckCounter = true;
                }
            }
            //checks if the Department Id entered by the user was not found
            if (deleteDepCheckCounter == false) {
                System.out.println("------------------------------------");
                System.out.println("No matching Department ID found");
            }
            //if user doesn't entered a valid Department Id and it throws NumberFormatException.
        } catch (NumberFormatException ex) {
            System.out.println("------------------------------------");
            System.out.println("Must enter valid Department ID!");
        }
    }

    //method to update departments from the arrayList and the file.
    @FXML
    public void updateDepartment() throws IOException, FileNotFoundException {

        //Counters
        updateDepCheckCounter = false;

        try {

            Department dep = (new Department(Integer.parseInt(tfIDDepartment.getText()), tfDescriptionDep.getText()));
            for (int i = 0; i < Department.DepartmentList.size(); i++) {
                //checks if the department id entered by the user matches with one in the arrayList.
                if (Department.DepartmentList.get(i).getId() == Integer.parseInt(tfIDDepartment.getText())) {

                    String newLine = Department.DepartmentList.get(i).toString();
                    Department.DepartmentList.set(i, dep);
                    /*calls method updateDepartment using the department that the new information of the update department, 
                    as well as the old information to locate the linein the file*/
                    createDep.updateDepartment(newLine, dep.toString());
                    System.out.println("------------------------------------");
                    System.out.println("Department updated!");
                    updateDepCheckCounter = true;
                }
            }
            //checks if the Department Id entered by the user was not found
            if (updateDepCheckCounter == false) {
                System.out.println("------------------------------------");
                System.out.println("No matching Department ID found");
            }

            //if user doesn't entered a valid Department Id and it throws NumberFormatException.
        } catch (NumberFormatException ex) {
            System.out.println("------------------------------------");
            System.out.println("Must enter valid Department ID!");

        }
    }

    //method to search through arrayList to find specific department.
    @FXML
    public void searchDepartment() {
        try {
            Department dep = new Department();
            for (int i = 0; i < Department.DepartmentList.size(); i++) {
                //checks if the Department ID enterd by user matches an ID in the arrayList.
                if (Department.DepartmentList.get(i).getId() == Integer.parseInt(tfIDDepartment.getText())) {
                    dep.setId(Department.DepartmentList.get(i).getId());
                    dep.setDescription(Department.DepartmentList.get(i).getDescription());
                    System.out.println("------------------------------------");
                    System.out.println("Department found!");

                }

            }
            //checks if the Department ID is not 0, then fills text filed values of that department.
            if (dep.getId() != 0) {
                tfIDDepartment.setText(Integer.toString(dep.getId()));
                tfDescriptionDep.setText((dep.getDescription()));
                btnDeleteDep.setDisable(false);
                btnUpdateDep.setDisable(false);

                //if no matching Department ID was found
            } else {
                System.out.println("------------------------------------");
                System.out.println("no matching Department ID found!");
                btnDeleteDep.setDisable(true);
                btnUpdateDep.setDisable(true);
            }

            //if user doesn't entered a valid Department Id and it throws NumberFormatException
        } catch (NumberFormatException ex) {
            System.out.println("------------------------------------");
            System.out.println("Must enter a valid Department ID!");
            btnDeleteDep.setDisable(false);
            btnUpdateDep.setDisable(false);

        }
    }

    //method to display ALL departments 
    public void displayDepartments(ObservableList<Department> depArr) throws IOException {

        idColDepartment.setCellValueFactory(new PropertyValueFactory<>("id"));
        descriptionColDepartment.setCellValueFactory(new PropertyValueFactory<>("Description"));
        tvDepartment.setItems(depArr);
    }

}
