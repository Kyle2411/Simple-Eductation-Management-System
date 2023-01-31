/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ben_1
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ben_1
 */
public class CreateTeacher extends Department {

    //Variables
    int teacherID;
    String teacherName;
    int teacherAge;
    String teacherGender;
    String teacherSpecialty;
    String teacherDegree;

    //Counters
    int teachCount1;
    int teachCount2;
    double payRollCheck;

    //Temporary ObservableArrayList to store values of specific department.
    public static ObservableList<Teacher> tempTeacherList = FXCollections.observableArrayList();

    
    //Method to read information from teacher file and store it to the arraylist using RandomAccessFile.
    public void readTeacher() throws IOException, FileNotFoundException {

        String filePath = "C:\\Users\\ben_1\\Desktop\\FinalProject\\Teacher.txt";

        try (RandomAccessFile file = new RandomAccessFile(filePath, "r")) {
            String str;
            
            while ((str = file.readLine()) != null) {
                
                String teacherLine[] = str.split(",");
                Teacher tea = new Teacher(teacherLine[5]);
                Teacher tea1 = new Teacher(Integer.parseInt(teacherLine[0]), teacherLine[1],
                        Integer.parseInt(teacherLine[2]), teacherLine[3], teacherLine[4], teacherLine[5], tea.computePayRoll(), Integer.parseInt(teacherLine[6]));
                TeacherList.add(tea1);
                
            }
        }
    }
    
    //method to append the information from the arrayList back to the teacher file using BufferedWriter.
    public void AppendToTeacher(String data, String fileName) throws IOException, FileNotFoundException {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.append("\n");
            writer.write(data);
        }
    }

    /*Method to read information from teacher file and store it to the arrayList, depending on which department the user
    selected in the "view teacher based on department number" option.*/
    public void readTempTeacher(int specDepId) throws IOException, FileNotFoundException {

        tempTeacherList.clear();
        String filePath = "C:\\Users\\ben_1\\Desktop\\FinalProject\\Teacher.txt";

        try (RandomAccessFile file = new RandomAccessFile(filePath, "r")) {
            String str;
            
            while ((str = file.readLine()) != null) {
                
                String teacherLine[] = str.split(",");
                
                Teacher tea = new Teacher(teacherLine[5]);
                Teacher tea1 = new Teacher(Integer.parseInt(teacherLine[0]), teacherLine[1],
                        Integer.parseInt(teacherLine[2]), teacherLine[3], teacherLine[4], teacherLine[5], tea.computePayRoll(), Integer.parseInt(teacherLine[6]));
                
                
                for (Department dep : DepartmentList) {
                    
                    //checks if the given argument (user inputted department choice) mathces with a specific department ID in the arrayList.
                    if (specDepId == dep.getId()) {
                        
                        if (dep.getId() == Integer.parseInt(teacherLine[6])) {
                            
                            //creates arrayList with information of only that teacher's department.
                            tempTeacherList.add(tea1);
                            
                        }
                        
                    }

                }

            }
        }
    }

    //method to delete teachers from the teacher file.
    public void deleteTeacher(String teacherString) throws IOException, FileNotFoundException {

        File filePath = new File("C:\\Users\\ben_1\\Desktop\\FinalProject\\Teacher.txt");
        File tempFilePath = new File("C:\\Users\\ben_1\\Desktop\\FinalProject\\TempTeacher.txt");

       
        try (   //reads from original teacher file.
                BufferedReader reader = new BufferedReader(new FileReader(filePath));
                //writes to temporary teacher file.
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFilePath))) {
                String deleteTeach;
            
            while ((deleteTeach = reader.readLine()) != null) {
                String teachertLine = deleteTeach.trim();
                
                /*reads through the original teacher file, and when the teacher that the user selected to be erased matches with the line
                in the teacher file, it skips that iteration(continue), so that line(teacher) is not rewritten.*/
                if (teachertLine.equals(teacherString)) {
                    continue;
                }
                //rewrites the original teacher file without the matching teacher line to a temporary teacher file.
                writer.write(deleteTeach + System.getProperty("line.separator"));
                
            }
            
            
        }
        
        //Deletes the original teacher file
        filePath.delete();
        //renames the temporary teacher file to the original teacher file(Teacher.txt).
        boolean success = tempFilePath.renameTo(filePath);

    }

    //method to update teachers from the teacher file.(uses same logic from delete teacher method).
    public void updateTeacher(String teacherString, String newLine) throws IOException, FileNotFoundException {

        
        File filePath = new File("C:\\Users\\ben_1\\Desktop\\FinalProject\\Teacher.txt");
         
        File tempFilePath = new File("C:\\Users\\ben_1\\Desktop\\FinalProject\\TempTeacher.txt");

                //reads from original teacher file.
        try (   BufferedReader reader = new BufferedReader(new FileReader(filePath)); 
                //writes to temporary teacher file.
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFilePath))) {
                String updateTeach;
            
            while ((updateTeach = reader.readLine()) != null) {
                String teacherLine = updateTeach.trim();
                
                /*reads through the original teacher file, and when the teacher that the user selected to be updated matches with the line
                in the teacher file, it skips that iteration(continue), and then replaces it with the updated teacher information(using the mehtod argument "newLine")*/
                if (teacherLine.equals(teacherString)) {
                    
                    writer.write(newLine + System.getProperty("line.separator"));
                    
                    continue;
                }
                writer.write(updateTeach + System.getProperty("line.separator"));
                
            }
            
        }

        //Deletes the original teacher file
        filePath.delete();
        //renames the temporary teacher file to the original teacher file(Teacher.txt).
        boolean finish = tempFilePath.renameTo(filePath);

    }
}
