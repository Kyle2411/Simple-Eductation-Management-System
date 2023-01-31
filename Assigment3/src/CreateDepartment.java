
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ben_1
 */
public class CreateDepartment extends Department {

    //variables
    int departmentID;
    String departmentDescription;
    int deptNum = 0;

    //Counters
    int numOfDepartmentsCounter = 0;
    int numOfTeachersCounter = 0;
    int numOfStaffCounter = 0;
    int deptCounter = 0;

    
    //Method to read information from department file and store it to the arraylist using RandomAccessFile.
    public void readDepartment() throws IOException, FileNotFoundException {

        String filePath = "C:\\Users\\ben_1\\Desktop\\FinalProject\\Department.txt";

        RandomAccessFile file = new RandomAccessFile(filePath, "r");
        String str;

        while ((str = file.readLine()) != null) {

            String departmentLine[] = str.split(",");
            Department dep = new Department(Integer.parseInt(departmentLine[0]), departmentLine[1]);
            DepartmentList.add(dep);

        }

    }

    //method to append the information from the arrayList back to the department file using BufferedWriter.
    public void AppendToDepartment(String data, String fileName) throws IOException, FileNotFoundException {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.append("\n");
            writer.write(data);
        }
    }

    //method to delete departments from the department file.
    public void deleteDepartment(String departmentString) throws IOException, FileNotFoundException {

        File filePath = new File("C:\\Users\\ben_1\\Desktop\\FinalProject\\Department.txt");
        File tempFilePath = new File("C:\\Users\\ben_1\\Desktop\\FinalProject\\TempDepartment.txt");

        try (   //reads from original department file.
                BufferedReader reader = new BufferedReader(new FileReader(filePath));
                //writes to temporary department file.
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFilePath))) {
                String deleteDep;
            
            while ((deleteDep = reader.readLine()) != null) {
                String departmentLine = deleteDep.trim();
                
                /*reads through the original department file, and when the department that the user selected to be erased matches with the line
                in the department file, it skips that iteration(continue), so that line(department) is not rewritten.*/
                if (departmentLine.equals(departmentString)) {
                    continue;
                }
                //rewrites the original department file without the matching department line to a temporary department file.
                writer.write(deleteDep + System.getProperty("line.separator"));
                
            }
            
        }

        //Deletes the original department file
        filePath.delete();
        //renames the temporary department file to the original department file(Student.txt).
        boolean success = tempFilePath.renameTo(filePath);

    }

    //method to update departments from the department file.(uses same logic from delete department method).
    public void updateDepartment(String departmentString, String newLine) throws IOException, FileNotFoundException {

        File filePath = new File("C:\\Users\\ben_1\\Desktop\\FinalProject\\Department.txt");
        File tempFilePath = new File("C:\\Users\\ben_1\\Desktop\\FinalProject\\TempDepartment.txt");

        try (
                //reads from original department file.
                BufferedReader reader = new BufferedReader(new FileReader(filePath));
                //writes to temporary department file.
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFilePath))) {
                String updateDep;
            
            while ((updateDep = reader.readLine()) != null) {
                String departmentLine = updateDep.trim();
                
                /*reads through the original department file, and when the department that the user selected to be updated matches with the line
                in the department file, it skips that iteration(continue), and then replaces it with the updated department information(using the mehtod argument "newLine")*/
                if (departmentLine.equals(departmentString)) {
                    
                    writer.write(newLine + System.getProperty("line.separator"));
                    continue;
                }
                writer.write(updateDep + System.getProperty("line.separator"));
                
            }
            
        }

        //Deletes the original department file
        filePath.delete();
        //renames the temporary department file to the original department file(Student.txt).
        boolean finish = tempFilePath.renameTo(filePath);

    }
}
