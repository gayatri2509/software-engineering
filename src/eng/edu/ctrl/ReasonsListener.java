/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eng.edu.ctrl;

import static eng.edu.ctrl.QuestionController.toggleGroupList;
import eng.edu.utilities.Utilities;
import eng.edu.view.ReasonsDisplayView;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;

/**
 *
 * @author Gayatri
 */
public class ReasonsListener {
    
    public static HashMap<String,String> correctReasons = new HashMap<String,String>();
    public static HashMap<Integer, String> selectedReasons = new HashMap<>();
    public String reasonsTxt;
    
    
    
    public static HashMap<String, ArrayList> getReasonsForIncorrectAssumptions(HashMap<String, ArrayList> assumptionReasonsMap, ArrayList<String> incorrectlyAnsweredAssumptionsList){
        HashMap<String, ArrayList> incorrectAssumptionReasonsMap = new HashMap<String, ArrayList>();
        for(int i=0; i<incorrectlyAnsweredAssumptionsList.size(); i++){
            if(assumptionReasonsMap.keySet().contains(incorrectlyAnsweredAssumptionsList.get(i))){
                incorrectAssumptionReasonsMap.put(incorrectlyAnsweredAssumptionsList.get(i), assumptionReasonsMap.get(incorrectlyAnsweredAssumptionsList.get(i)));
            }
        }  
        return incorrectAssumptionReasonsMap;
    } 
    
    
    public static void reasonsListener(ArrayList<String> incorrectlyAnsweredAssumptionsList, Scene scene){
        String fileName = "reasons";
        BufferedReader bufferedReader = Utilities.getFileReader(fileName, QuestionController.quesNo);
        HashMap<String, ArrayList> assumptionReasonsMap = readAllReasonsFromFile(bufferedReader);
                 
        HashMap<String, ArrayList> incorrectAssumptionReasonsMap = getReasonsForIncorrectAssumptions(assumptionReasonsMap, incorrectlyAnsweredAssumptionsList);      
        ScrollPane scrollPane = (ScrollPane)scene.lookup("#scrollPane");
        ReasonsDisplayView.displayReasons(incorrectAssumptionReasonsMap, incorrectlyAnsweredAssumptionsList, scrollPane);
    }
    
    
    /*
    *Reasons should be selected for all the incorrectly answered assumptions
    */
    public static boolean checkIfAllReasonsAreSelected(){
        int numberOfselectedReasons = 0;
        for (int i = 0; i < toggleGroupList.size(); i++) {
            ToggleGroup group = toggleGroupList.get(i);
            if(group.getSelectedToggle()!=null){
                numberOfselectedReasons++;
            }
        }           
        if(numberOfselectedReasons!=toggleGroupList.size()){
            return false;
        }
        else{
            return true;
        } 
    }
    
    public static ArrayList<String> getCorrectReasonsForIncorrectlySelectedReasons(ArrayList<String> incorrectlyAnsweredAssumptionsList, HashMap<String,String> correctReasons){
        //HashMap<String,String> correctReasons = ReasonsListener.correctReasons;
       
        ArrayList<String> correctReasonsList = new ArrayList<>();
        for(int i=0; i< incorrectlyAnsweredAssumptionsList.size(); i++){
            String currentAssumption = incorrectlyAnsweredAssumptionsList.get(i);
            String currentReason = correctReasons.get(currentAssumption);
            correctReasonsList.add(currentReason);
        }
        return correctReasonsList;
    }
    
    public static int getNumberOfIncorrectReasons(ArrayList<String> correctReasonsList){
        ArrayList<Integer> verificationResult = new ArrayList<>();
        int numberOfWrongReasonsSelected = 0;
        for (int i = 0; i < toggleGroupList.size(); i++) {
            ToggleGroup group = toggleGroupList.get(i);
            if(group.getSelectedToggle()!=null){
                String selectedReason = group.getSelectedToggle().getUserData().toString();
                if(selectedReason.equals(correctReasonsList.get(i))){
                    verificationResult.add(1);
                }
                else{
                    verificationResult.add(0);
                    numberOfWrongReasonsSelected++;
                }
                
            }
        }  
       System.out.println("verificationResult "+verificationResult);
       return numberOfWrongReasonsSelected;
    }
    
    public static HashMap readAllReasonsFromFile(BufferedReader bufferedReader) {
        HashMap<String, ArrayList> assumptionsReasonsMap = new HashMap<String, ArrayList>();
        try {
            String currentAssumption = new String();
            String CurrentLine;
            int count = 0;
            while ((CurrentLine = bufferedReader.readLine()) != null) {
                ArrayList<String> reasons = new ArrayList<>();
                if (count % 2 == 0) {
                    currentAssumption = CurrentLine;
                } else {
                    String[] temp = {CurrentLine};
                    if (CurrentLine.contains(";")) {
                        temp = CurrentLine.split(";");
                    }

                    for (int i = 0; i < temp.length; i++) {
                        if (temp[i].contains("|")) {
                            String lastReason = temp[i];
                            String temp1[] = lastReason.split("\\|");
                            correctReasons.put(currentAssumption,temp1[1]);
                            reasons.add(temp1[0]);
                        } else {
                            reasons.add(temp[i]);
                        }
                    }
                    assumptionsReasonsMap.put(currentAssumption, reasons);
                }
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return assumptionsReasonsMap;
    }
    
    
    
}
