/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author ben_1
 */
public class Student extends Person {

     //Variables
    private String course;
    private int semester;
    private int deptChoice;

    //Setters and Getters for Student attributes
    public int getDeptChoice() {return deptChoice;}
    public void setDeptChoice(int deptChoice) {this.deptChoice = deptChoice;}

    public int getSemester() {return this.semester;}
    public void setSemester(int semester) {this.semester = semester;}
    
    public String getCourse() {return this.course;}
    public void setCourse(String course) {this.course = course;}
    
    
    //Constructors for Student class
    public Student() {
    }

    public Student(int id1, String name1, int age1, String gender1, String course1, int semester1, int deptChoice1) {

        this.id = id1;
        this.name = name1;
        this.age = age1;
        this.gender = gender1;
        this.course = course1;
        this.semester = semester1;
        this.deptChoice = deptChoice1;
    }

    //method to override and display Teacher information
    @Override
     public String toString() {
       return (super.toString() + this.course + "," + this.semester + "," + this.deptChoice);

    }


}
