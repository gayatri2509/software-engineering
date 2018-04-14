/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eng.edu.model;

import eng.edu.utilities.Utilities;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author deeptichavan
 *
 * This class reads the assumptions text file and stores them in a list, which
 * is then displayed from the AssumptionsTableView class
 */
public class AssumptionsModel {

    public String assumptionsTxt;
    public ObservableList<AssumptionsDAO> assumptionsList = FXCollections.observableArrayList();
    public ArrayList<CheckBox> checkBoxes;
    public ArrayList<Label> labels;

    File fileReal = new File(Utilities.basePath + "/questions/correct.png");

    public AssumptionsModel() {

        checkBoxes = new ArrayList<>();
        labels = new ArrayList<>();
        assumptionsTxt = Utilities.assumptionsTxt;
        String[] split = assumptionsTxt.split("file:");
        assumptionsTxt = split[1];

        assumptionsList = readAssumptionsFromFile(assumptionsTxt);

    }
    
    public ObservableList<AssumptionsDAO> readAssumptionsFromFile(String assumptionsTxt){
        
        BufferedReader br;

        try {

            String currentLine;
            br = new BufferedReader(new FileReader(assumptionsTxt));

            while ((currentLine = br.readLine()) != null) {
                //text and result is spearated by a | in the .txt file
                String[] temp = currentLine.split("\\|");
                //get the assumption text
                String aTxt = temp[0].trim();
                //whether the assumption is correct or no
                //true - correct and false - wrong
                boolean res;
                res = "true".equalsIgnoreCase(temp[1].trim());
                assumptionsList.add(new AssumptionsDAO(aTxt, res));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return assumptionsList;
    }
    
    public void assignAssumptionsToCheckBoxes() {

        int i;
        /*
        *assumptionsList :: generated from the assumptions text file
         */
        for (i = 0; i < assumptionsList.size(); i++) {
            String assumptionTxt = assumptionsList.get(i).getAssumption();
            CheckBox checkBox = new CheckBox(assumptionTxt);
            checkBox.setId("checkbox" + i);
            checkBox.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
            checkBoxes.add(checkBox);
        }

    }

    public void assignLablesToAssumptions() {

        int i;
        for (i = 0; i < assumptionsList.size(); i++) {
        Label label = new Label();
        ImageView correct = new ImageView(fileReal.toURI().toString());
        correct.setFitHeight(15);
        correct.setFitWidth(15);
        label.setGraphic(correct);
        label.setVisible(false);
        label.setId("label" + i);
        labels.add(label);
        }
    }
}
