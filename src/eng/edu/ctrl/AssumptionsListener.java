/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eng.edu.ctrl;

import eng.edu.model.AssumptionsModel;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

/**
 *
 * @author Gayatri
 */
public class AssumptionsListener {

    public static ArrayList<Integer> response;
    public static int aaumptionListSize;
    //this will tell us whether any assumptions are selected or not
    public boolean checkIfAssumptionsMarked(ActionEvent event, Button submitId) {
        
        response = getStudentsResponse(submitId.getScene());
        int numberOfResponse = response.size();
        if (numberOfResponse == 0) {
            return false;
        } else {
            return true;
        }
    }
    
    //get the option numbers that the student selected
    public ArrayList<Integer> getStudentsResponse(Scene scene) {
        AssumptionsModel adm = new AssumptionsModel();
        ArrayList<Integer> response = new ArrayList<>();
        aaumptionListSize = adm.assumptionsList.size();

        int i;
        for (i = 0; i < aaumptionListSize; i++) {
            String id = "#checkbox" + i;
            CheckBox cb = (CheckBox) scene.lookup(id);
            if (cb.isSelected()) {
                response.add(i);
            }
        }
        return response;
    }
 
    //this will return all the incorrect assumptions marked by the student
    public static ArrayList<String> getIncorrectSelectedAssumption(Button submitId) {
        
        AssumptionsModel adm = new AssumptionsModel();
        //get the incorrect assumptions that the student selected
        ArrayList<String> incorrectSelectedResponse = new ArrayList<>();
        int i;
        for (i = 0; i < response.size(); i++) {

            String txt = adm.assumptionsList.get(response.get(i)).getAssumption();
            //verify against the correct result
            boolean res = adm.assumptionsList.get(response.get(i)).getIsCorrect();
            if (res == false) {
                incorrectSelectedResponse.add(txt);
            } 
        }
        return incorrectSelectedResponse;
    }
}
