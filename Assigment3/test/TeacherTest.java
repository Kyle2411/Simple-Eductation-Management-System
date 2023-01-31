/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author ben_1
 */
public class TeacherTest {
    
    public TeacherTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of computePayRoll method, of class Teacher.
     */
    @Test
    public void testComputePayRoll() {
        System.out.println("computePayRoll");
       
 
        Teacher teach1 = new Teacher(1234,"Steve James",27,"male","computer vision","phd",1001);
        //Teacher teach2 = new Teacher(2345,"Patrick Dalton",30,"other","pre-history","bachelor",1003);
        //Teacher teach3 = new Teacher(5546,"Sana Malkesh",28,"female","topology","master",1001);
    
        
        double expResult = 6128.64;
        double result = teach1.computePayRoll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    @Test
    public void testComputePayRoll2() {
        System.out.println("computePayRoll");
       
 
        //Teacher teach1 = new Teacher(1234,"Steve James",27,"male","computer vision","phd",1001);
        Teacher teach2 = new Teacher(2345,"Patrick Dalton",30,"other","pre-history","bachelor",1003);
        //Teacher teach3 = new Teacher(5546,"Sana Malkesh",28,"female","topology","master",1001);
    
        
        double expResult = 4487.04;
        double result = teach2.computePayRoll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    @Test
    public void testComputePayRoll3() {
        System.out.println("computePayRoll");
       
 
        //Teacher teach1 = new Teacher(1234,"Steve James",27,"male","computer vision","phd",1001);
        //Teacher teach2 = new Teacher(2345,"Patrick Dalton",30,"other","pre-history","bachelor",1003);
        Teacher teach3 = new Teacher(5546,"Sana Malkesh",28,"female","topology","master",1001);
    
        
        double expResult = 2298.24;
        double result = teach3.computePayRoll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    
}
