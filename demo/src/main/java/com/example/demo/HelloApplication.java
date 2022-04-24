package com.example.demo;

import java.io.File;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import javafx.embed.swing.JFXPanel;


public class HelloApplication extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        String path = "C:\\Users\\savad\\Documents\\cours\\1ere_cybersecurite\\Programmation_oriente_objet\\Projet\\WLC\\Java_player\\video\\fan.mp4";

        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        MediaView mediaView = new MediaView(mediaPlayer);

        Group root = new Group();
        root.getChildren().add(mediaView);
        Scene scene = new Scene(root,1000,800);
        stage.setScene(scene);
        stage.setTitle("Playing Video");
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
