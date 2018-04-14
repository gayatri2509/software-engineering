/**
 * This class displays the real world and idealized model images
 */
package eng.edu.ctrl;

//import static eng.edu.ctrl.ReasonPageController.selectedReasons;
import static eng.edu.ctrl.AssumptionsListener.response;
import eng.edu.model.AssumptionsModel;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.stage.Window;

import eng.edu.utilities.Utilities;
import eng.edu.view.GifDecoder;
import eng.edu.view.OptionsResponseView;
import java.awt.image.BufferedImage;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class QuestionController {

    @FXML
    private VBox dataPane;

    @FXML
    private ImageView realWorldImage;

    @FXML
    private ImageView idealizedImage;

    @FXML
    private Button submitId;

    @FXML
    public Button nextId;

    @FXML
    public ScrollPane scrollPane;

    public static int updatedScore;

    public static ArrayList<String> incorrectlyAnsweredAssumptionsList;

    public static boolean isAssumptionListener = true;
    public static ArrayList<ToggleGroup> toggleGroupList = new ArrayList<>();

    public static int quesNo;
    
    javafx.scene.paint.Color fxColor = javafx.scene.paint.Color.rgb(8, 1, 94, 1);
    private final Background focusBackground = new Background( new BackgroundFill( fxColor, CornerRadii.EMPTY, Insets.EMPTY ) );
   

    public void initialize() throws MalformedURLException {

        isAssumptionListener = true;

        Utilities u = new Utilities();
        quesNo = u.number;

        //Real world image stored in users home location is displayed from here.
        String path = u.getPath("RealWorld", ".png");
        Image imageReal = new Image(path);
        realWorldImage.setImage(imageReal);

        //Idealized model image stored in users home location is displayed from here.
        path = u.getPath("IdealizedModel", ".png");
        Image imageIdeal = new Image(path);
        idealizedImage.setImage(imageIdeal);
    }

    public void setDataPane(Node node) {
        // update VBox with new form(FXML) depends on which button is clicked
        dataPane.getChildren().setAll(node);
    }

    public static void closeWindow(ActionEvent event) {
        Button button = (Button) (event.getSource());
        Window window = button.getScene().getWindow();
        Stage stage = (Stage) window;
        stage.hide();
    }

    //handles the steps after clicking the submit button
    @FXML
    public void handleSubmitButtonAction(ActionEvent event) throws IOException {

        AssumptionsModel assumptionsDisplayModel = new AssumptionsModel();
        OptionsResponseView optionsResponseView = new OptionsResponseView();

        //if submit is for assumptions or reasons
        if (isAssumptionListener) {
            AssumptionsListener assumptionsListener = new AssumptionsListener();
            boolean isAnswerSelected = assumptionsListener.checkIfAssumptionsMarked(event, submitId);
            //at least one assumption should be selected
            if (isAnswerSelected) {
                //so that next time the submit is for Reasons
                isAssumptionListener = false;
                incorrectlyAnsweredAssumptionsList = AssumptionsListener.getIncorrectSelectedAssumption(submitId);
                QuestionController.updatedScore += ScoreComputation.calculateScore(incorrectlyAnsweredAssumptionsList.size(), response.size(), "assumption", QuestionController.quesNo);
                optionsResponseView.displayScore(submitId.getScene(), QuestionController.updatedScore);

                //student didn't mark any incorrect assumptions
                if (incorrectlyAnsweredAssumptionsList.isEmpty()) {
                    optionsResponseView.displayAssumptions(assumptionsDisplayModel.assumptionsList, submitId.getScene());
                    submitId.setVisible(false);
                    nextId.setVisible(true);
                } else {
                    //give reasons for the incorrectly selected assumptions
                    ReasonsListener rl = new ReasonsListener();
                    rl.reasonsListener(incorrectlyAnsweredAssumptionsList, submitId.getScene());
                    optionsResponseView.displayAssumptions(assumptionsDisplayModel.assumptionsList, submitId.getScene());
                }
            } else {
                optionsResponseView.showPopupForSelectingAtleastOneAssumption();
            }
        } else {
            boolean result = ReasonsListener.checkIfAllReasonsAreSelected();
            if (result) {
                ArrayList<String> correctReasonsList = ReasonsListener.getCorrectReasonsForIncorrectlySelectedReasons(incorrectlyAnsweredAssumptionsList,  ReasonsListener.correctReasons);
                int numberOfWrongReasonsSelected = ReasonsListener.getNumberOfIncorrectReasons(correctReasonsList);
                optionsResponseView.disableRadioButtons(correctReasonsList);
                int score = ScoreComputation.calculateScore(numberOfWrongReasonsSelected, correctReasonsList.size(), "reasons", QuestionController.quesNo);
                updatedScore += score;
                optionsResponseView.displayScore(submitId.getScene(), updatedScore);
                submitId.setVisible(false);
                nextId.setVisible(true);
                
            } else {
                optionsResponseView.showPopupForSelectingAllReasons();
            }
        }
    }

    public void handleNextPage(ActionEvent event) throws IOException {
        int score = updatedScore;
        boolean quoteDisplayFlag = false;

        if (Utilities.questionAlreadyDone.size() == Utilities.max) {
            AnchorPane root1 = FXMLLoader.load(getClass().getResource("/eng/edu/view/EndPage.fxml"));
            
            
             HBox root = new HBox();

             
        // TODO: provide gif file, ie exchange banana.gif with your file
        Animation animation;
        if(score >= 4){
//            animation = new AnimatedGif(getClass().getResource("o5.gif").toExternalForm(), 800);
            animation = new AnimatedGif(getClass().getResource("pooh_lastpg.gif").toExternalForm(), 2500);
            quoteDisplayFlag=true;
        }
        else{
//            animation = new AnimatedGif(getClass().getResource("m.gif").toExternalForm(), 800);
            animation = new AnimatedGif(getClass().getResource("pooh_lowscore3.gif").toExternalForm(), 1800);
        }
        
        
        animation.setCycleCount(10);
        animation.play();

        VBox vbox = new VBox(10);
        vbox.getChildren().add(animation.getView());
        vbox.setSpacing(2);
        vbox.setMargin(animation.getView(), new Insets(80, 0, 0, 280));
        vbox.setBackground( focusBackground );

//        Label quoteLabel = new Label("Better luck next time");
    

        VBox vbox2 = new VBox();
        vbox2.getChildren().addAll( vbox, root1);
        
        root.getChildren().addAll(vbox2);

        Scene scene = new Scene(root, 900, 650);
            
            Label label1 = (Label) root1.getChildren().get(1);
            if(quoteDisplayFlag==true){
                label1.setText("Excellent Work! Keep it up!!");
            }
            else{
                label1.setText("     Nice try! Good luck!");
            }
            
            AnchorPane anchor = (AnchorPane) root1.getChildren().get(0);
            Label label = (Label) anchor.getChildren().get(1);
            label.setText("Your final score is " + score);
//            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UTILITY);
            stage.setScene(scene);
            stage.setTitle("Quiddz");
            Image image = new Image("logo_trans.png");
            stage.getIcons().add(image);
            stage.show();
        } else {
  
            WelcomePageController w = new WelcomePageController();
            w.handleMainPage(event);

        }
        closeWindow(event);
    }
    
    
    
    /*
    https://stackoverflow.com/questions/28183667/how-i-can-stop-an-animated-gif-in-javafx
    */
     public class AnimatedGif extends Animation {

        public AnimatedGif( String filename, double durationMs) {

            GifDecoder d = new GifDecoder();
            d.read( filename);

            Image[] sequence = new Image[ d.getFrameCount()];
            for( int i=0; i < d.getFrameCount(); i++) {

                WritableImage wimg = null;
                BufferedImage bimg = d.getFrame(i);
                sequence[i] = SwingFXUtils.toFXImage( bimg, wimg);

            }

            super.init( sequence, durationMs);
        }

    }

    public class Animation extends Transition {

        private ImageView imageView;
        private int count;

        private int lastIndex;

        private Image[] sequence;

        private Animation() {
        }

        public Animation( Image[] sequence, double durationMs) {
            init( sequence, durationMs);
        }

        private void init( Image[] sequence, double durationMs) {
            this.imageView = new ImageView(sequence[0]);
            this.sequence = sequence;
            this.count = sequence.length;

            setCycleCount(1);
            setCycleDuration(Duration.millis(durationMs));
            setInterpolator(Interpolator.LINEAR);

        }

        protected void interpolate(double k) {

            final int index = Math.min((int) Math.floor(k * count), count - 1);
            //if (index != lastIndex) {
                imageView.setImage(sequence[index]);
                lastIndex = index;
            //}

        }

        public ImageView getView() {
            return imageView;
        }

    }

}




 