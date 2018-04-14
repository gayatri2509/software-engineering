/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eng.edu.utilities;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author poojithadharmavaram
 */
public class UtilitiesTest {
   
    /**
     * Test of getNumberOfQuestions method, of class Utilities.
     */
    @Test
    public void testGetNumberOfQuestions() {
         System.out.println("getNumberOfQuestions");
        //The number of questions currently is 2.
        int expResult = 2;
        int result = Utilities.getNumberOfQuestions();
        assertEquals(expResult, result);

    }

    /**
     * Test of getRandomQuestion method, of class Utilities.
     */
    @Test
    public void testGetRandomQuestion() {
        System.out.println("getRandomQuestion");
        
        int n = 2; // n is the total number of questions.
        int result = Utilities.getRandomQuestion(n);
        
        boolean withinRange = false;
        
        //Checking if the random question number genereated is within the given range.
        
        if(result >= 1 && result <= n)
            withinRange = true;
        
        assertEquals(true,withinRange);
    }

    /**
     * Test of fileExists method, of class Utilities.
     */
    @Test
    public void testFileExists() {
        System.out.println("fileExists");
        
        String basePath = System.getProperty("user.home");
        String baseDirectory = "/questions/";
        String filename = basePath + baseDirectory + "q1/IdealizedModel1.png";
       
        Utilities instance = new Utilities();
        boolean expResult = true;
        boolean result = instance.fileExists(filename);
        
        assertEquals(expResult, result);
    }
    
}
