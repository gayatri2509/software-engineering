/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eng.edu.ctrl;

import eng.edu.model.AssumptionsModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author chiragyeole
 */
public class WelcomePageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    @FXML
    private AnchorPane anchorWelcome;

    @FXML
    public void handleMainPage(ActionEvent event) {
        // load main form in to VBox (Root)
        AssumptionsModel adm;
        VBox mainPane = null;
        try {
            mainPane = (VBox) FXMLLoader.load(getClass().getResource("/eng/edu/view/main.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(WelcomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(mainPane);
        HBox hbox1 = (HBox) mainPane.getChildren().get(0);
        Label label = (Label) hbox1.getChildren().get(0);
        label.setText("Score: " + QuestionController.updatedScore);
        
        SplitPane split = (SplitPane) mainPane.getChildren().get(1);
        AnchorPane anchor = (AnchorPane) split.getItems().get(0);
        VBox vbox1 = (VBox) anchor.getChildren().get(2);
        ScrollPane scroll = (ScrollPane) vbox1.getChildren().get(2);

        //get all the checkboxes
        adm = new AssumptionsModel();
        adm.assignAssumptionsToCheckBoxes();
        adm.assignLablesToAssumptions();

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 10, 10, 20));
        vbox.setStyle("-fx-background-color: #C3C8CD;");

        //add these checkboxes to the vbox
        int i;
        for (i = 0; i < adm.checkBoxes.size(); i++) {
            System.out.println("Iteration" + i);
            final HBox hbox = new HBox();
            hbox.setStyle("-fx-background-color: #C3C8CD;");
            vbox.getChildren().add(hbox);
            hbox.getChildren().addAll(adm.labels.get(i), adm.checkBoxes.get(i));

            //hbox.getChildren().clear();
        }
        //add vbox to scroll pane
        scroll.setContent(vbox);
        scroll.setStyle("-fx-background: #C3C8CD; -fx-background-color: #C3C8CD;");
            
        //group the images and checkboxes together
        Group grp = new Group();
        grp.getChildren().addAll(mainPane, vbox);

        Stage stage = new Stage();
        stage.setTitle("Engineering Educators");
        stage.setScene(new Scene(grp));
        stage.setMaximized(false);
        stage.initStyle(StageStyle.UNDECORATED);
        //stage.setFullScreen(true); // make the main form fit to the screen
        stage.show();
        if (anchorWelcome != null) {
            anchorWelcome.getScene().getWindow().hide();
        }
    }
}
