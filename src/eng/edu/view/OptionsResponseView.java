/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eng.edu.view;

import eng.edu.ctrl.AssumptionsListener;
import static eng.edu.ctrl.QuestionController.toggleGroupList;
import eng.edu.model.AssumptionsDAO;
import eng.edu.model.AssumptionsModel;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author deeptichavan
 */
public class OptionsResponseView {

    public void displayAssumptions(ObservableList<AssumptionsDAO> assumptionsList, Scene scene) {

        highlightAllCorrectAssumptions(scene);
        highlightSelectedOptionsWithTick(scene);
        disableCheckBoxes(scene);

    }

    /*
    * this method will display the assumptions that the student has selected
    * with a tick mark against them
    * incorrectly answered assumptions will be in red
     */
    public void highlightSelectedOptionsWithTick(Scene scene) {

        AssumptionsModel adm = new AssumptionsModel();
        int i;
        for (i = 0; i < AssumptionsListener.response.size(); i++) {
            boolean isCorrect = adm.assumptionsList.get(AssumptionsListener.response.get(i)).getIsCorrect();
            String id = "#checkbox" + AssumptionsListener.response.get(i);
            CheckBox cb = (CheckBox) scene.lookup(id);
            cb.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
            cb.setSelected(true);
            if (!isCorrect) {
                cb.setStyle("-fx-text-fill: red;");
            }
        }

    }

    /*
    *this method will highlight all the correct assumptions in green
    *and will fade out the incorrect ones
     */
    public void highlightAllCorrectAssumptions(Scene scene) {

        AssumptionsModel adm = new AssumptionsModel();
        int i;
        for (i = 0; i < adm.assumptionsList.size(); i++) {

            boolean isCorrect = adm.assumptionsList.get(i).getIsCorrect();
            String id = "#checkbox" + i;
            CheckBox cb = (CheckBox) scene.lookup(id);
            if (isCorrect) {
                cb.setStyle("-fx-text-fill: green;");
            } else {
                cb.setStyle("-fx-text-fill: grey;");
            }

        }
    }

    /*
    * this method will highlight the selected reason as green or red 
    * depending on whether it's correct or incorrect
    */
    public static void highlightReasonResponse(ArrayList<String> correctReasonsList) {

        System.out.println("correctReasonsList :: " + correctReasonsList);
        for (int i = 0; i < toggleGroupList.size(); i++) {
            ToggleGroup group = toggleGroupList.get(i);
            if (group.getSelectedToggle() != null) {
                String selectedReason = group.getSelectedToggle().getUserData().toString();
                RadioButton rb = (RadioButton) group.getSelectedToggle();
                rb.setFont(Font.font("Tahoma", FontWeight.NORMAL, 13));

                if (selectedReason.equals(correctReasonsList.get(i))) {
                    rb.setStyle("-fx-text-fill: green;");
                } else {
                    rb.setStyle("-fx-text-fill: red;");
                }

            }
        }
    }

    /*
    * disable the options with fading out the incorrect ones
    */
    public static void disableRadioButtons(ArrayList<String> correctReasonsList) {

        for (int i = 0; i < toggleGroupList.size(); i++) {
            ToggleGroup group = toggleGroupList.get(i);
            if (group.getSelectedToggle() != null) {

                ObservableList<Toggle> toggle = group.getToggles();
                for (int j = 0; j < toggle.size(); j++) {

                    RadioButton rb = (RadioButton) toggle.get(j);
                    if (rb.getText().equals(correctReasonsList.get(i))) {
                        rb.setStyle("-fx-text-fill: green;");
                    } else {
                        if (!rb.isSelected()) {
                            rb.setStyle("-fx-text-fill: grey;");
                        }
                    }
                    rb.setDisable(true);
                }

            }
        }
    }

    public void disableCheckBoxes(Scene scene) {

        int i;
        for (i = 0; i < AssumptionsListener.aaumptionListSize; i++) {
            String id = "#checkbox" + i;
            CheckBox cb = (CheckBox) scene.lookup(id);
            cb.setDisable(true);
        }
    }

    public void displayScore(Scene scene, int score) {
        Label lb1 = (Label) scene.lookup("#score");
        lb1.setText("Score: " + score);
        System.out.println("updatedScore " + score);
    }

    //Pops up a dialogue box to indicate that user needs to select atleast one assumption
    public void showPopupForSelectingAtleastOneAssumption() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/eng/edu/view/dialogbox.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");

        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("Please select atleast one option!");
        alert.showAndWait();
    }

    //Pops up a dialogue box to indicate that user needs to give reasons for all incorrect assumptions seleted previously
    public void showPopupForSelectingAllReasons() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/eng/edu/view/dialogbox.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");

        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("Please select reasons for all incorrect assumptions!");
        alert.showAndWait();
    }
}
