/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eng.edu.ctrl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author poojithadharmavaram
 */
public class ReasonsListenerTest {

    public static HashMap<String, ArrayList> assumptionReasonsMap;

    public ReasonsListenerTest() {

        assumptionReasonsMap = new HashMap<String, ArrayList>();
    }

    public HashMap getMap() {
        String assumption;
        ArrayList reasons = new ArrayList();
        assumption = "Incorrect Assumption that includes a lot of text to make certain you can handle it #1.3";
        reasons.add("Valid Reason #ExamplesAreHard");
        assumptionReasonsMap.put(assumption, reasons);

        reasons = new ArrayList();
        assumption = "Incorrect Assumption #1.1";
        reasons.add("Valid Reason #1.1.1");
        reasons.add("Invalid Reason #1.1.2");
        reasons.add("Invalid Reason #1.1.3");
        assumptionReasonsMap.put(assumption, reasons);

        assumption = "Complicating Assumption #Who Cares I Am Making All This Up?";
        reasons = new ArrayList();
        reasons.add("Invalid Reason #x.y.z");
        reasons.add("Valid");
        reasons.add("Invalid Reason #hashtag");
        reasons.add("Invalid Reason #HertzRules!");
        assumptionReasonsMap.put(assumption, reasons);

        assumption = "Incorrect Assumption #1.2";
        reasons = new ArrayList();
        reasons.add("Invalid Reason #1.2.1");
        reasons.add("Invalid Reason #1.2.2");
        reasons.add("Invalid Reason #1.2.3");
        reasons.add("Valid Reason #1.2.4");
        assumptionReasonsMap.put(assumption, reasons);

        return assumptionReasonsMap;
    }

    /**
     * Test of getReasonsForIncorrectAssumptions method, of class
     * ReasonsListener.
     */
    @Test
    public void testGetReasonsForIncorrectAssumptions() {
        System.out.println("getReasonsForIncorrectAssumptions");

        assumptionReasonsMap = getMap();
        ArrayList<String> incorrectlyAnsweredAssumptionsList = new ArrayList();
        incorrectlyAnsweredAssumptionsList.add("Incorrect Assumption #1.1");

        HashMap<String, ArrayList> expResult = new HashMap<String, ArrayList>();
        String assumption = "Incorrect Assumption #1.1";
        ArrayList reasons = new ArrayList();
        reasons.add("Valid Reason #1.1.1");
        reasons.add("Invalid Reason #1.1.2");
        reasons.add("Invalid Reason #1.1.3");
        expResult.put(assumption, reasons);

        HashMap<String, ArrayList> result = ReasonsListener.getReasonsForIncorrectAssumptions(assumptionReasonsMap, incorrectlyAnsweredAssumptionsList);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    @Test
    public void testGetCorrectReasonsForIncorrectlySelectedReasons(){
        
        System.out.println("getCorrectReasonsForIncorrectlySelectedReasons");
        
        ArrayList<String> incorrectlyAnsweredAssumptionsList = new ArrayList<>();
        incorrectlyAnsweredAssumptionsList.add("Incorrect Assumption #1.1");
        HashMap<String,String> correctReasons = new HashMap<>();
        correctReasons.put("Incorrect Assumption #1.1", "Valid Reason #1.1.1");
        correctReasons.put("Incorrect Assumption #1.2", "Valid Reason #1.2.4");
        correctReasons.put("Complicating Assumption #Who Cares I Am Making All This Up?", "Valid");
        correctReasons.put("Incorrect Assumption that includes a lot of text to make certain you can handle it #1.3", "Valid Reason #ExamplesAreHard");
        
        ArrayList<String> expResult = new ArrayList<>();
        expResult.add("Valid Reason #1.1.1");
        
        ArrayList<String> result = new ArrayList<>();
        result = ReasonsListener.getCorrectReasonsForIncorrectlySelectedReasons(incorrectlyAnsweredAssumptionsList, correctReasons);
        
        assertEquals(expResult, result);
    }
    
    /**
     * Test of readAllReasonsFromFile method, of class ReasonsListener.
     */
    @Test
    public void testReadAllReasonsFromFile() {
        System.out.println("readAllReasonsFromFile");

        String basePath = System.getProperty("user.home");
        String baseDirectory = "/questions/";
        String filename = basePath + baseDirectory + "q1/reasons1.txt";
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(filename));
        } catch (Exception e) {
            System.out.println(e);
        }
        ReasonsListener instance = new ReasonsListener();
        HashMap result = instance.readAllReasonsFromFile(bufferedReader);

        //
        HashMap<String, ArrayList> expResult = new HashMap<String, ArrayList>();
        String assumption;
        ArrayList reasons = new ArrayList();
        assumption = "Incorrect Assumption that includes a lot of text to make certain you can handle it #1.3";
        reasons.add("Valid Reason #ExamplesAreHard");
        expResult.put(assumption, reasons);

        reasons = new ArrayList();
        assumption = "Incorrect Assumption #1.1";
        reasons.add("Valid Reason #1.1.1");
        reasons.add("Invalid Reason #1.1.2");
        reasons.add("Invalid Reason #1.1.3");
        expResult.put(assumption, reasons);

        assumption = "Complicating Assumption #Who Cares I Am Making All This Up?";
        reasons = new ArrayList();
        reasons.add("Invalid Reason #x.y.z");
        reasons.add("Valid");
        reasons.add("Invalid Reason #hashtag");
        reasons.add("Invalid Reason #HertzRules!");
        expResult.put(assumption, reasons);

        assumption = "Incorrect Assumption #1.2";
        reasons = new ArrayList();
        reasons.add("Invalid Reason #1.2.1");
        reasons.add("Invalid Reason #1.2.2");
        reasons.add("Invalid Reason #1.2.3");
        reasons.add("Valid Reason #1.2.4");
        expResult.put(assumption, reasons);

        assertEquals(expResult, result);
    }

}
