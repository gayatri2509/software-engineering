package eng.edu.ctrl;

import eng.edu.view.GifDecoder;
import java.awt.image.BufferedImage;
import java.net.URISyntaxException;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.net.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.image.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
//import javafx.scene.paint.Color;


/**
 * Requires GifDecoder from here: http://www.java2s.com/Code/Java/2D-Graphics-GUI/DecodesaGIFfileintooneormoreframes.htm
 */
public class EngEduMain extends Application {

    javafx.scene.paint.Color fxColor = javafx.scene.paint.Color.rgb(8, 1, 94, 1);
    private final Background focusBackground = new Background( new BackgroundFill( fxColor, CornerRadii.EMPTY, Insets.EMPTY ) );
    
    @Override
    public void start(Stage primaryStage) throws URISyntaxException, IOException {

        Parent root1 = FXMLLoader.load(getClass().getResource("/eng/edu/view/WelcomePage.fxml"));
//        VBox vbox1 = new VBox();
//        vbox1.getChildren().add(root1);
//        
        HBox root = new HBox();

        // TODO: provide gif file, ie exchange banana.gif with your file
//        Animation ani = new AnimatedGif(getClass().getResource("pop-quiz.gif").toExternalForm(), 1000);
        Animation ani = new AnimatedGif(getClass().getResource("pooh_firstpg.gif").toExternalForm(), 1000);
        
        ani.setCycleCount(10);
        ani.play();

        VBox vbox = new VBox(10);
        vbox.getChildren().add(ani.getView());
        vbox.setSpacing(2);
        vbox.setMargin(ani.getView(), new Insets(100, 0, 0, 260));
        vbox.setBackground( focusBackground );

        Image image = new Image("logo_trans.png");
        
        

        VBox vbox2 = new VBox();
        vbox2.getChildren().addAll( vbox, root1);
        
        root.getChildren().addAll(vbox2);

        Scene scene = new Scene(root, 900, 650);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Quiddz");
        primaryStage.getIcons().add(image);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

     class AnimatedGif extends Animation {

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

     class Animation extends Transition {

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
            if (index != lastIndex) {
                imageView.setImage(sequence[index]);
                lastIndex = index;
            }

        }

        public ImageView getView() {
            return imageView;
        }

    }

}












