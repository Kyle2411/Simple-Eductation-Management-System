/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Department {

    //Variables
    private int id;
    private String description;
    private Teacher dean;
    
    //Creating ObservableArrayLists
    public static ObservableList<Teacher> TeacherList = FXCollections.observableArrayList();
    public static ObservableList<Student> StudentList = FXCollections.observableArrayList();
    public static ObservableList<Staff> StaffList = FXCollections.observableArrayList();
    public static ObservableList<Department> DepartmentList = FXCollections.observableArrayList();

    //Setters and Getters for Department attributes
    public int getId() {return this.id;}
    public void setId(int id) {this.id = id;}

    public String getDescription() {return this.description;}
    public void setDescription(String description) {this.description = description;}
    
    public void setDean(Teacher teach) {this.dean = teach;}
    public Teacher getDean() {return this.dean;}
    

    //Constructors for Department class
    public Department() {
    }

    public Department(int i, String d) {
        this.id = i;
        this.description = d;

    }

    //method to override and display Department information
    @Override
    public String toString() {
       return (this.id + "," + this.description);

    }

}
