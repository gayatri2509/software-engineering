/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eng.edu.ctrl;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author chiragyeole
 */
public class ScoreComputationTest {
    
    /**
     * Test of calculateScore method, of class ScoreComputation.
     */
    @Test
    public void testCalculateScoreForAssumptions() {
        System.out.println("calculateScore");
        int numberOfIncorrectResponses = 1;
        int numberOfResponses = 3;
        String scoreEvaluationType = "assumption";
        int expResult = 2;
        int result = ScoreComputation.calculateScore(numberOfIncorrectResponses, numberOfResponses, scoreEvaluationType, 1);
        assertEquals(expResult, result);
   
    }

    @Test
    public void testCalculateScoreForReasons() {
        System.out.println("calculateScore");
        int numberOfIncorrectResponses = 1;
        int numberOfResponses = 3;
        String scoreEvaluationType = "reason";
        int expResult = 1;
        int result = ScoreComputation.calculateScore(numberOfIncorrectResponses, numberOfResponses, scoreEvaluationType, 1);
        assertEquals(expResult, result);
   
    }
    
    @Test
    public void testReadScoreFile1(){
        
        ArrayList<Integer> expResult = new ArrayList<>();
        expResult.add(2);
        expResult.add(-2);
        expResult.add(1);
        expResult.add(-1);
        
        ArrayList<Integer> result = ScoreComputation.readScoreFile(1);
        
        assertEquals(expResult, result);
        
    }
    
    @Test
    public void testReadScoreFile2(){
        
        ArrayList<Integer> expResult = new ArrayList<>();
        expResult.add(2);
        expResult.add(-2);
        
        ArrayList<Integer> result = ScoreComputation.readScoreFile(2);
        
        assertEquals(expResult, result);
        
    }
}
