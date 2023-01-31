
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
public class CreateStaff extends Department {

    //Variables
    int staffID;
    String staffName;
    int staffAge;
    String staffGender;
    String staffDuty;
    int staffWorkload;

    //Counters
    int staffCount1;
    int staffCount2;

    //Temporary ObservableArrayList to store values of specific department.
    public static ObservableList<Staff> tempStaffList = FXCollections.observableArrayList();

    
    //Method to read information from staff file and store it to the arraylist using RandomAccessFile.
    public void readStaff() throws IOException, FileNotFoundException {

        String filePath = "C:\\Users\\ben_1\\Desktop\\FinalProject\\Staff.txt";

        RandomAccessFile file = new RandomAccessFile(filePath, "r");
        String str;

        while ((str = file.readLine()) != null) {

            String staffLine[] = str.split(",");
            Staff stf = new Staff(Integer.parseInt(staffLine[5]));
            Staff stf1 = new Staff(Integer.parseInt(staffLine[0]), staffLine[1],
                    Integer.parseInt(staffLine[2]), staffLine[3], staffLine[4], Integer.parseInt(staffLine[5]), stf.getSalary(), Integer.parseInt(staffLine[6]));
            StaffList.add(stf1);
            
        }

    }
    
    //method to append the information from the arrayList back to the staff file using BufferedWriter.
    public void AppendToStaff(String data, String fileName) throws IOException, FileNotFoundException {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.append("\n");
            writer.write(data);
        }
    }

    /*Method to read information from staff file and store it to the arrayList, depending on which department the user
    selected in the "view staff based on department number" option.*/
    public void readTempStaff(int specDepId) throws IOException, FileNotFoundException {

        tempStaffList.clear();
        String filePath = "C:\\Users\\ben_1\\Desktop\\FinalProject\\Staff.txt";

        RandomAccessFile file = new RandomAccessFile(filePath, "r");
        String str;

        while ((str = file.readLine()) != null) {

            String staffLine[] = str.split(",");
            Staff stf = new Staff(Integer.parseInt(staffLine[5]));
            Staff stf1 = new Staff(Integer.parseInt(staffLine[0]), staffLine[1],
                    Integer.parseInt(staffLine[2]), staffLine[3], staffLine[4], Integer.parseInt(staffLine[5]), stf.getSalary(), Integer.parseInt(staffLine[6]));
           

            for (Department dep : DepartmentList) {

                //checks if the given argument (user inputted department choice) mathces with a specific department ID in the arrayList.
                if (specDepId == dep.getId()) {

                    //checks if that department's Id matches with the staff's department choicewhen reading the file.
                    if (dep.getId() == Integer.parseInt(staffLine[6])) {

                        //creates arrayList with information of only that staff's department.
                        tempStaffList.add(stf1);

                    }

                }
            }

        }

    }

    //method to delete staffs from the staff file.
    public void deleteStaff(String staffString) throws IOException, FileNotFoundException {

        File filePath = new File("C:\\Users\\ben_1\\Desktop\\FinalProject\\Staff.txt");
        File tempFilePath = new File("C:\\Users\\ben_1\\Desktop\\FinalProject\\TempStafft.txt");

        try (   //reads from original staff file.
                BufferedReader reader = new BufferedReader(new FileReader(filePath));
                //writes to temporary staff file.
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFilePath))) {
                String deleteStaff;
            
            while ((deleteStaff = reader.readLine()) != null) {
                String staffLine = deleteStaff.trim();
                
                /*reads through the original staff file, and when the staff that the user selected to be erased matches with the line
                in the staff file, it skips that iteration(continue), so that line(staff) is not rewritten.*/
                if (staffLine.equals(staffString)) {
                    continue;
                }
                //rewrites the original staff file without the matching staff line to a temporary staff file.
                writer.write(deleteStaff + System.getProperty("line.separator"));
                
            }
            
        }

        //Deletes the original staff file
        filePath.delete();
        //renames the temporary staff file to the original staff file(Staff.txt).
        boolean success = tempFilePath.renameTo(filePath);

    }

    //method to update staffs from the staff file.(uses same logic from delete staff method).
    public void updateStaff(String staffString, String newLine) throws IOException, FileNotFoundException {

        File filePath = new File("C:\\Users\\ben_1\\Desktop\\FinalProject\\Staff.txt");
        File tempFilePath = new File("C:\\Users\\ben_1\\Desktop\\FinalProject\\TempStaff.txt");

                //reads from original staff file.
        try (   BufferedReader reader = new BufferedReader(new FileReader(filePath)); 
                //writes to temporary staff file.
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFilePath))) {
                String updateStaff;
            
            while ((updateStaff = reader.readLine()) != null) {
                String staffLine = updateStaff.trim();
                
                /*reads through the original staff file, and when the staff that the user selected to be updated matches with the line
                in the staff file, it skips that iteration(continue), and then replaces it with the updated staff information(using the mehtod argument "newLine")*/
                if (staffLine.equals(staffString)) {
                    
                    writer.write(newLine + System.getProperty("line.separator"));
                    continue;
                }
                writer.write(updateStaff + System.getProperty("line.separator"));
                
            }
           
        }

        //Deletes the original staff file
        filePath.delete();
        //renames the temporary staff file to the original staff file(Student.txt).
        boolean finish = tempFilePath.renameTo(filePath);

    }
}
