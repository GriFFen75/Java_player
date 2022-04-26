package com.example.demo;

import java.awt.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import javafx.embed.swing.JFXPanel;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JPanel;

public class HelloApplication {


    public static String file ;
    public static JFrame frame = new JFrame("BVW / Lecteur vidéos");

    private static void initAndShowGUI() {
        // This method is invoked on Swing thread

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Image icon = Toolkit.getDefaultToolkit().getImage("image/BVW.png");
        frame.setIconImage(icon);

        JPanel panelOption = new JPanel();

        //frame.add(panelOption);

        JFXPanel fxPanel = new JFXPanel();
        frame.add(fxPanel);

        frame.setVisible(true);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                initFX(fxPanel);
            }
        });
    }

    public static void close(){ // parce que j'ai 2 fenêtres qui s'ouvrent
        frame.dispose();
    }

    private static void initFX(JFXPanel fxPanel) {
        // This method is invoked on JavaFX thread
        Media media = new Media(new File(UpdatePath()).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        MediaView mediaView = new MediaView(mediaPlayer);
        Group root = new Group();
        root.getChildren().add(mediaView);
        Scene scene = new Scene(root,1000,800);
        fxPanel.setScene(scene);
    }

    public static String UpdatePath(){
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        String path = new String(s+"/video/"+file);
        return path;
    }

    public static void main(String[] args, String lien) {//,String path

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                file = lien;
                initAndShowGUI();
            }
        });

    }
}
