/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ben_1
 */
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class FXMLTeacherMenu extends FXMLMainMenu {

    //Buttons
    @FXML
    private Button switchToManageDeanBtn;
    @FXML
    private Button switchToManageTeachBtn;
    @FXML
    private Button switchToViewTeachBtn;
    
    //Variables
    @FXML
    public static boolean sceneCheck = false;

    
    //Method for when a button is pressed in the teacher menu.
    @FXML
    private void mainTeachMenuButton(ActionEvent event) throws IOException {

        //Switches scenes when Manage Teacher button is pressed.
        if (event.getSource() == switchToManageTeachBtn) {
            try {
                sceneCheck = true;
                root = FXMLLoader.load(getClass().getClassLoader().getResource("ManageTeacher.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                
            } catch (NullPointerException ex) {
                System.out.println("ManageTeacher scene file not found");
                ex.printStackTrace();
            }
        }
        //Switches scenes when View Teacher button is pressed.
        if (event.getSource() == switchToViewTeachBtn) {

            try {
                sceneCheck = false;
                root = FXMLLoader.load(getClass().getClassLoader().getResource("ViewTeacher.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                
            } catch (NullPointerException ex) {
                System.out.println("ViewTeacher scene file not found");
                ex.printStackTrace();
            }
        }
        //Switches scenes when Manage Dean button is pressed.
        if (event.getSource() == switchToManageDeanBtn) {
            try {
                sceneCheck = false;
                root = FXMLLoader.load(getClass().getClassLoader().getResource("ManageDean.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                
            } catch (NullPointerException ex) {
                System.out.println("ManageDean scene file not found");
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
