/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ben_1
 */
public class FXMLMainMenu implements Initializable {

    //Objects
    public static Stage stage;
    public static Scene scene;
    public static Parent root;
    public static FXMLStudent fxmlStu = new FXMLStudent();
    public static FXMLTeacher fxmlTeach = new FXMLTeacher();
    public static FXMLStaff fxmlStaff = new FXMLStaff();
    public static FXMLDepartment fxmlDepartment = new FXMLDepartment();
    
    //Buttons
    @FXML
    private Button switchToStuMenuBtn;
    @FXML
    private Button switchToTeachMenuBtn;
    @FXML
    private Button switchToStaffMenuBtn;
    @FXML
    private Button switchToDepMenuBtn;

 
    @Override
    public void initialize(URL url, ResourceBundle rb) {}

   
    //Method for when a button is pressed in the Main menu.
    @FXML
    private void mainMenuButton(ActionEvent event) throws IOException{

        //Switches scenes when Student Menu button is pressed.
        if (event.getSource() == switchToStuMenuBtn) {
            try {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("StudentMenu.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                
            } catch (NullPointerException ex) {
                System.out.println("StudentMenu scene file not found");
                ex.printStackTrace();
            } 
        }
        //Switches scenes when Teacher Menu button is pressed.
        if (event.getSource() == switchToTeachMenuBtn) {
            try {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("TeacherMenu.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                
            } catch (NullPointerException ex) {
                System.out.println("TeacherMenu cene file not found");
                ex.printStackTrace();
            } 
        }
        //Switches scenes when Staff Menu button is pressed.
        if (event.getSource() == switchToStaffMenuBtn) {
            try {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("StaffMenu.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                
            } catch (NullPointerException ex) {
                System.out.println("StaffMenu scene file not found");
                ex.printStackTrace();
            } 
        }
        //Switches scenes when Department Menu button is pressed.
        if (event.getSource() == switchToDepMenuBtn) {
            try {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("DepartmentMenu.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                
            } catch (NullPointerException ex) {
                System.out.println("DepartmentMenu scene file not found");
                ex.printStackTrace();
            } 
        }

    }

    //Method to switch scenes when back button is pressed.
    @FXML
    public void backToMainMenu(ActionEvent event) throws IOException{

        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("MainMenu.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
        } catch (NullPointerException ex) {
            System.out.println("MainMenu scene file not found");
            ex.printStackTrace();
        } 

    }

}
