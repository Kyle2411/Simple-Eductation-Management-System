/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ben_1
 */
public class Staff extends Person implements Payroll {

    //Variables
    private String duty;
    private int workload;
    private int deptChoice;
    private double salary;

    //Setters and Getters for Staff attributes
    public String getDuty() {return duty;}
    public void setDuty(String duty) {this.duty = duty;}

    public int getWorkload() {return workload;}
    public void setWorkload(int workload) {this.workload = workload;}
    
    public int getDeptChoice() {return deptChoice;}
    public void setDeptChoice(int departmentID) {this.deptChoice = departmentID;}
    
    public double getSalary() {return computePayRoll();}

    //Constructors for Staff class
    
    public Staff() {
    }
    public Staff(int workload1) {
        this.workload = workload1;

    }

    public Staff(int id1, String name1, int age1, String gender1, String duty1, int workload1, double salary1, int departmentID1) {
        this.id = id1;
        this.name = name1;
        this.age = age1;
        this.gender = gender1;
        this.duty = duty1;
        this.workload = workload1;
        this.salary = salary1;
        this.deptChoice = departmentID1;
    }
    
    public Staff(int id1, String name1, int age1, String gender1, String duty1, int workload1, int departmentID1) {
        this.id = id1;
        this.name = name1;
        this.age = age1;
        this.gender = gender1;
        this.duty = duty1;
        this.workload = workload1;
        this.deptChoice = departmentID1;
    }

   
    //method to override and display Staff information
    @Override
    public String toString(){
        return(super.toString() + this.duty + ","+ this.workload + "," + this.deptChoice);
        

    }

    //Method to calculate staff payRoll based on workload variable.
    @Override
    public double computePayRoll() {
        
        //checks if the workload is under or equal to 36 and calculates salary
        if (this.workload <= 36) {
            this.salary = this.workload * 32 * 2 * 0.85;
        }

        return this.salary;
        
    }


}
