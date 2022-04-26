package com.example.demo;

import java.awt.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import javafx.embed.swing.JFXPanel;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class HelloApplication {


    public static String file ;
    public static JFrame frame = new JFrame("BVW / Lecteur vidéos");
    public static JFXPanel fxPanel;
    public static JPanel panelVideo;

    private static void initAndShowGUI() {
        // This method is invoked on Swing thread

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setSize(new Dimension(800,600));
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Image icon = Toolkit.getDefaultToolkit().getImage("image/BVW.png");
        frame.setIconImage(icon);

        //créer un panel pour réunir la video + la barre avec les boutons pour la modif de la vidéo
        panelVideo = new JPanel(new BorderLayout());

        fxPanel = new JFXPanel();
        fxPanel.setPreferredSize(new Dimension(800,600));
        panelVideo.add(fxPanel,BorderLayout.CENTER);

        JPanel panelOption = new JPanel();
        panelOption.setBackground(Color.red);
        panelOption.add(new Button("coucou"));
        panelVideo.add(panelOption, BorderLayout.SOUTH);


        frame.add(panelVideo,BorderLayout.CENTER);

        //panel pour mettre le synopsis
        JPanel panelSynopsis = new JPanel(new BorderLayout());
        panelSynopsis.setBackground(Color.BLACK);
        frame.add(panelSynopsis,BorderLayout.SOUTH);

        //panel pour toutes les autres infos
        JPanel panelInfo = new JPanel(new BorderLayout());
        panelInfo.setBackground(Color.gray);
        frame.add(panelInfo,BorderLayout.WEST);

        frame.setVisible(true);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                initFX();
            }
        });
    }

    public static void close(){ // parce que j'ai 2 fenêtres qui s'ouvrent
        frame.dispose();
    }

    private static void initFX() {
        // This method is invoked on JavaFX thread
        Media media = new Media(new File(UpdatePath()).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        MediaView mediaView = new MediaView(mediaPlayer);
        Group root = new Group();
        root.getChildren().add(mediaView);
        DoubleProperty mvw = mediaView.fitWidthProperty();
        DoubleProperty mvh = mediaView.fitHeightProperty();
        mvw.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
        mvh.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
        mediaView.setPreserveRatio(true);


        //Scene scene = new Scene(root,1000,800);
        fxPanel.setScene(new Scene(new Group(mediaView), panelVideo.getWidth(), panelVideo.getHeight()));
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
