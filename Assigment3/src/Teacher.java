/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ben_1
 */
public class Teacher extends Person implements Payroll {

    //Variables
    private String specialty;
    private String degree;
    private double salary;
    private int deptChoice;
    private String isDean;

    //Setters and Getters for Teacher attributes
    public String getSpecialty() {return specialty;}
    public void setSpecialty(String specialty) {this.specialty = specialty;}

    public double getSalary() {return salary;}

    public int getDeptChoice() {return deptChoice;}
    public void setDeptChoice(int deptChoice) {this.deptChoice = deptChoice;}

    public void setDegree(String degree) {this.degree = degree;}
    public String getDegree() {return this.degree;}

    //Constructors for Teacher class
    public Teacher() {
 
    }
    
    public Teacher(String degree1) {
        this.degree = degree1;
    }

    public Teacher(int id1, String name1, int age1, String gender1, String specialty1, String degree1, int deptChoice1) {

        this.id = id1;
        this.name = name1;
        this.age = age1;
        this.gender = gender1;
        this.specialty = specialty1;
        this.degree = degree1;
        this.deptChoice = deptChoice1;
    }
    
    public Teacher(int id1, String name1, int age1, String gender1, String specialty1, String degree1, double salary, int deptChoice1) {

        this.id = id1;
        this.name = name1;
        this.age = age1;
        this.gender = gender1;
        this.specialty = specialty1;
        this.degree = degree1;
        this.salary = salary;
        this.deptChoice = deptChoice1;
    }

    //Method to assign a dean to a department.
    public String isDean() {

        for (Department dep : Department.DepartmentList) {

            //Checks if the teacher's department ID matches a department ID in the arrayList.
            if (dep.getId() == getDeptChoice()) {

                 //Checks if that department has a dean yet.
                if (dep.getDean() != null) {

                    //Checks if the ID of the dean matches the teacher's ID.
                    if (dep.getDean().getId() == getId()) {

                        //Setting the isDean variable to "Yes".
                        this.isDean = "Yes";

                    }

                } else {
                    //Setting the isDean variable to "Yes".
                    this.isDean = "";
                }
            }
        }

        return this.isDean;
    }

    //Method to calculate the payRoll of the teacher depending on their degree.
    @Override
    public double computePayRoll() {

        //Checks if the degree of the teacher is "phd" and sets it to 112, then calculates the payRoll.
        if ("phd".equals(this.degree)) {
            this.salary = 112;
            return this.salary * 2 * 36 * 0.76;
        }

        //Checks if the degree of the teacher is "master" and sets it to 112, then calculates the payRoll.
        if ("master".equals(this.degree)) {
            this.salary = 82;
            return this.salary * 2 * 36 * 0.76;
        }

        //Checks if the degree of the teacher is "bachelor" and sets it to 112, then calculates the payRoll.
        if ("bachelor".equals(this.degree)) {
            this.salary = 42;
            return this.salary * 2 * 36 * 0.76;
        }

        return this.salary;

    }
    
    //method to override and display Teacher information
    @Override
    public String toString() {
        return (super.toString() + this.specialty + "," + this.degree + "," + deptChoice);

    }

}
