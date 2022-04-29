import java.io.File;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;


public class videoReader extends Application {

    //ne sert plus
    @Override
    public void start(Stage stage) {
        String path = "/Users/wx/IdeaProjects/Java_player/video/1365070268951.mp4";

        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        MediaView mediaView = new MediaView(mediaPlayer);

        Group root = new Group();
        root.getChildren().add(mediaView);
        Scene scene = new Scene(root,600,400);
        stage.setScene(scene);
        stage.setTitle("Playing Video");
        stage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
}
