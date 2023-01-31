/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author ben_1
 */
public class Main extends Application {

    //Class Objects
    public static CreateStudent createStudent = new CreateStudent();
    public static CreateTeacher createTeacher = new CreateTeacher();
    public static CreateStaff createStaff = new CreateStaff();
    public static CreateDepartment createDepartment = new CreateDepartment();

    //method to start program
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        //calling read methods to begin reading files
        createStudent.readStudent();
        createTeacher.readTeacher();
        createStaff.readStaff();
        createDepartment.readDepartment();

        //switching scenes to Main Menu
        try {
            Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (NullPointerException ex) {
            System.out.println("MainMenu scene file not found");
            ex.printStackTrace();
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
