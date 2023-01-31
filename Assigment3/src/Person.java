/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author ben_1
 */
public abstract class Person {
    
     //Variables
    protected int id;
    protected String name;
    protected int age;
    protected String gender;
    
    
    //Setters and Getters for Person attributes.
    public int getId() {return this.id;}
    public void setId(int id) {this.id = id;}

    public String getName() {return this.name;}
    public void setName(String name) {this.name = name;}

    public int getAge() {return this.age;}
    public void setAge(int age) {this.age = age;}

    public String getGender() {return this.gender;}
    public void setGender(String gender) {this.gender = gender;}
    

    //Constructor for Person class.
    public Person() {
        this.name = null;
        this.id = 0;
        this.gender = null;
        this.age = 0;
    }

    //toString methtod to display student, teacher and staff information.
    @Override
     public String toString() {
        return(this.id + "," + this.name + "," + this.age + "," + this.gender + ",");
    
    }
}
    
