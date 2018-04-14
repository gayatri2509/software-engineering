/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eng.edu.view;

import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author poojithadharmavaram
 */
public class ReasonsDisplayViewTest {
    
    public static HashMap<String, ArrayList> incorrectAssumptionReasonsMap;
    
    public ReasonsDisplayViewTest(){
        
        incorrectAssumptionReasonsMap = new HashMap<>();
    }
   
    public HashMap getMap() {
        String assumption;
        ArrayList reasons = new ArrayList();
        
        reasons = new ArrayList();
        assumption = "Incorrect Assumption #1.1";
        reasons.add("Valid Reason #1.1.1");
        reasons.add("Invalid Reason #1.1.2");
        reasons.add("Invalid Reason #1.1.3");
        incorrectAssumptionReasonsMap.put(assumption, reasons);

        return incorrectAssumptionReasonsMap;
    }
    
    public ArrayList getList() {
        
        ArrayList reasons = new ArrayList();
        
        reasons = new ArrayList();
        reasons.add("Valid Reason #1.1.1");
        reasons.add("Invalid Reason #1.1.2");
        reasons.add("Invalid Reason #1.1.3");
        

        return reasons;
    }


    /**
     * Test of getReasonsForCurrentAssumption method, of class ReasonsDisplayView.
     */
    @Test
    public void testGetReasonsForCurrentAssumption() {
        System.out.println("getReasonsForCurrentAssumption");
        HashMap<String, ArrayList> incorrectAssumptionReasonsMap = getMap();
        ArrayList<String> incorrectlyAnsweredAssumptionsList = new ArrayList<>();
        incorrectlyAnsweredAssumptionsList.add("Incorrect Assumption #1.1");
        int count = 0;
        ArrayList<String> expResult = getList();
        ArrayList<String> result = ReasonsDisplayView.getReasonsForCurrentAssumption(incorrectAssumptionReasonsMap, incorrectlyAnsweredAssumptionsList, count);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    

    /**
     * Test of checkIfReasonsToBeDisplayedForCurrentAssumption method, of class ReasonsDisplayView.
     */
    @Test
    public void testCheckIfReasonsToBeDisplayedForCurrentAssumption() {
        System.out.println("checkIfReasonsToBeDisplayedForCurrentAssumption");
        HashMap<String, ArrayList> incorrectAssumptionReasonsMap = getMap();
        String currentAssumption = "Incorrect Assumption #1.1";
        boolean expResult = true;
        boolean result = ReasonsDisplayView.checkIfReasonsToBeDisplayedForCurrentAssumption(incorrectAssumptionReasonsMap, currentAssumption);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    
    @Test
    public void testCheckIfReasonsToBeDisplayedForCurrentAssumptionFalse() {
        System.out.println("checkIfReasonsToBeDisplayedForCurrentAssumption");
        HashMap<String, ArrayList> incorrectAssumptionReasonsMap = getMap();
        String currentAssumption = "Hip acts as a pivot point (no lifting off the bed)";
        boolean expResult = false;
        boolean result = ReasonsDisplayView.checkIfReasonsToBeDisplayedForCurrentAssumption(incorrectAssumptionReasonsMap, currentAssumption);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
