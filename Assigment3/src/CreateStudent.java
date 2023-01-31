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
public class CreateStudent extends Department {

    //Variables
    int studentID;
    String studentName;
    int studentAge;
    String studentGender;
    String studentCourse;
    int studentSemester;

    //Counters
    int studentCount1;
    int studentCount2;

    //Temporary ObservableArrayList to store values of specific department.
    public static ObservableList<Student> tempStudentList = FXCollections.observableArrayList();

    
    //Method to read information from student file and store it to the arraylist using RandomAccessFile.
    public void readStudent() throws IOException, FileNotFoundException {

        String filePath = "C:\\Users\\ben_1\\Desktop\\FinalProject\\Student.txt";

        try (RandomAccessFile file = new RandomAccessFile(filePath, "r")) {
            String str;
            
            while ((str = file.readLine()) != null) {
                
                String studentLine[] = str.split(",");
                Student stu = new Student(Integer.parseInt(studentLine[0]), studentLine[1],
                        Integer.parseInt(studentLine[2]), studentLine[3], studentLine[4], Integer.parseInt(studentLine[5]), Integer.parseInt(studentLine[6]));
                StudentList.add(stu);
                
            }
        }

    }
    
    //method to append the information from the arrayList back to the student file using BufferedWriter.
    public void AppendToStudent(String data, String fileName) throws IOException, FileNotFoundException {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.append("\n");
            writer.write(data);
        }
    }

    /*Method to read information from student file and store it to the arrayList, depending on which department the user
    selected in the "view student based on department number" option.*/
    public void readTempStu(int specDepId) throws IOException, FileNotFoundException {

        tempStudentList.clear();
        String filePath = "C:\\Users\\ben_1\\Desktop\\FinalProject\\Student.txt";

        try (RandomAccessFile file = new RandomAccessFile(filePath, "r")) {
            String str;
            
            while ((str = file.readLine()) != null) {
                
                String studentLine[] = str.split(",");
                Student stu = new Student(Integer.parseInt(studentLine[0]), studentLine[1],
                        Integer.parseInt(studentLine[2]), studentLine[3], studentLine[4], Integer.parseInt(studentLine[5]), Integer.parseInt(studentLine[6]));
                
                for (Department dep : DepartmentList) {
                    
                    //checks if the given argument (user inputted department choice) mathces with a specific department ID in the arrayList.
                    if (specDepId == dep.getId()) {
                        
                        //checks if that department's Id matches with the student's department choicewhen reading the file.
                        if (dep.getId() == Integer.parseInt(studentLine[6])) {
                            
                            //creates arrayList with information of only that student's department.
                            tempStudentList.add(stu);
                            
                        }
                        
                    }
                }
                
            }
        }
        

    }

    //method to delete students from the student file.
    public void deleteStudent(String studentString) throws IOException, FileNotFoundException {

        File filePath = new File("C:\\Users\\ben_1\\Desktop\\FinalProject\\Student.txt");
        File tempFilePath = new File("C:\\Users\\ben_1\\Desktop\\FinalProject\\TempStudent.txt");

        
        try (   //reads from original student file.
                BufferedReader reader = new BufferedReader(new FileReader(filePath)); 
                //writes to temporary student file.
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFilePath))) {
                String deleteStu;
            
            while ((deleteStu = reader.readLine()) != null) {
                
                String studentLine = deleteStu.trim();
                
                /*reads through the original student file, and when the student that the user selected to be erased matches with the line
                in the student file, it skips that iteration(continue), so that line(student) is not rewritten.*/
                if (studentLine.equals(studentString)) {
                    continue;
                }
                //rewrites the original student file without the matching student line to a temporary student file.
                writer.write(deleteStu + System.getProperty("line.separator"));
                
            }
            
        }

        //Deletes the original student file
        filePath.delete();
        //renames the temporary student file to the original student file(Student.txt).
        boolean success = tempFilePath.renameTo(filePath);

    }

    //method to update students from the student file.(uses same logic from delete student method).
    public void updateStudent(String studentString, String newLine) throws IOException, FileNotFoundException {

        File filePath = new File("C:\\Users\\ben_1\\Desktop\\FinalProject\\Student.txt");
        File tempFilePath = new File("C:\\Users\\ben_1\\Desktop\\FinalProject\\TempStudent.txt");

       
        try (   //reads from original student file.
                BufferedReader reader = new BufferedReader(new FileReader(filePath));
                //writes to temporary student file.
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFilePath))) {
                String updateStu;
            
            while ((updateStu = reader.readLine()) != null) {
                String studentLine = updateStu.trim();
                
                /*reads through the original student file, and when the student that the user selected to be updated matches with the line
                in the student file, it skips that iteration(continue), and then replaces it with the updated student information(using the mehtod argument "newLine")*/
                if (studentLine.equals(studentString)) {
                    
                    writer.write(newLine + System.getProperty("line.separator"));
                    continue;
                }
                writer.write(updateStu + System.getProperty("line.separator"));
                
            }
            
        }

        //Deletes the original student file
        filePath.delete();
        //renames the temporary student file to the original student file(Student.txt).
        boolean finish = tempFilePath.renameTo(filePath);

    }
}
