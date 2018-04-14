/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eng.edu.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author poojithadharmavaram
 */
public class AssumptionsDAOTest {
    
    public AssumptionsDAOTest() {
    }
    
    /**
     * Test of getAssumption method, of class AssumptionsDAO.
     */
    @Test
    public void testGetAssumption() {
        System.out.println("getAssumption");
        AssumptionsDAO instance = new AssumptionsDAO("This is a correct assumption",true);
        String expResult = "This is a correct assumption";
        String result = instance.getAssumption();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setAssumption method, of class AssumptionsDAO.
     */
    @Test
    public void testSetAssumption() {
        System.out.println("setAssumption");
        String assumption = "This is a correct assumption";
        AssumptionsDAO instance = new AssumptionsDAO("blah",true);
        instance.setAssumption(assumption);
        
        String expResult = "This is a correct assumption";
        String result = instance.getAssumption();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getIsCorrect method, of class AssumptionsDAO.
     */
    @Test
    public void testGetIsCorrect() {
        System.out.println("getIsCorrect");
        AssumptionsDAO instance = new AssumptionsDAO("This is a correct assumption",true);
        boolean expResult = true;
        boolean result = instance.getIsCorrect();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setIsCorrect method, of class AssumptionsDAO.
     */
    @Test
    public void testSetIsCorrect() {
        System.out.println("setIsCorrect");
        boolean isCorrect = true;
        AssumptionsDAO instance = new AssumptionsDAO("This is a correct assumption",true);
        instance.setIsCorrect(isCorrect);
        
        boolean expResult = true;
        boolean result = instance.getIsCorrect();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
