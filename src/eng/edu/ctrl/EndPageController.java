/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eng.edu.ctrl;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author chiragyeole
 */
public class EndPageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    @FXML
    private AnchorPane anchorWelcome;

    @FXML
    public void handleEndPage(ActionEvent event) {
        // load main form in to VBox (Root)

        Button button = (Button) (event.getSource());
        Window window = button.getScene().getWindow();
        Stage stage = (Stage) window;
        stage.close();

    }
}
