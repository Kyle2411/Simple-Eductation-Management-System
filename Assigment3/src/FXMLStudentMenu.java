
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;

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
public class FXMLStudentMenu extends FXMLMainMenu {

    //Buttons
    @FXML
    private Button switchToManageStuBtn;
    @FXML
    private Button switchToViewStuBtn;
    
    //Variables
    @FXML
    public static boolean sceneCheck = false;
 
    
    //Method for when a button is pressed in the student menu.
    @FXML
    private void mainStuMenuButton(ActionEvent event) throws IOException {

        //Switches scenes when Manage Student button is pressed.
        if (event.getSource() == switchToManageStuBtn) {
            try {
                sceneCheck = true;
                root = FXMLLoader.load(getClass().getClassLoader().getResource("ManageStudent.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                
            } catch (NullPointerException ex) {
                System.out.println("ManageStudent scene file not found");
                ex.printStackTrace();
            }
        }
        //Switches scenes when View Student button is pressed.
        if (event.getSource() == switchToViewStuBtn) {
            try {
                sceneCheck = false;
                root = FXMLLoader.load(getClass().getClassLoader().getResource("ViewStudent.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                
            } catch (NullPointerException ex) {
                System.out.println("ViewStudent scene file not found");
                ex.printStackTrace();
            }
        }
    }
    
    /*Method to determine which scene the user is currently in, this is because different scenes have different
    objects that need to be deactivated when using the same method.*/
    public boolean getSceneCheck(){
        return sceneCheck;
        }

}
