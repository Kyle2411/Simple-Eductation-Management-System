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

public class FXMLStaffMenu extends FXMLMainMenu {

    //Buttons
    @FXML
    private Button switchToManageStaffBtn;
    @FXML
    private Button switchToViewStaffBtn;
    
    //Variables
    @FXML
    public static boolean sceneCheck = false;

    
    //Method for when a button is pressed in the staff menu.
    @FXML
    private void mainStaffMenuButton(ActionEvent event) throws IOException {

        //Switches scenes when Manage Staff button is pressed.
        if (event.getSource() == switchToManageStaffBtn) {
            try {
                sceneCheck = true;
                root = FXMLLoader.load(getClass().getClassLoader().getResource("ManageStaff.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                
            } catch (NullPointerException ex) {
                System.out.println("ManageStaff scene file not found");
                ex.printStackTrace();
            }
         }
        //Switches scenes when View Staff button is pressed.
        if (event.getSource() == switchToViewStaffBtn) {
            try {
                sceneCheck = false;
                root = FXMLLoader.load(getClass().getClassLoader().getResource("ViewStaff.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                
            } catch (NullPointerException ex) {
                System.out.println("ViewStaff scene file not found");
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
