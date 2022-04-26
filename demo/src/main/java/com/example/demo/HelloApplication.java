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

import javax.swing.*;
import javax.swing.border.Border;

public class HelloApplication {


    public static String file ;
    public static JFrame frame = new JFrame("BVW / Lecteur vidéos");
    public static JFXPanel fxPanel;
    public static JPanel panelVideo;
    public static Image icon = Toolkit.getDefaultToolkit().getImage("image/BVW.png");

    private static void initAndShowGUI() {

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setSize(new Dimension(800, 600));
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        frame.setIconImage(icon);
        createPanel();
    }
    public static void createPanel(){
        //créer un panel pour réunir la video + la barre avec les boutons pour la modif de la vidéo
        panelVideo = new JPanel(new BorderLayout());
        //on met de border pour que ca sois plus joli
        panelVideo.setBorder(BorderFactory.createLineBorder(Color.white, 1));
        panelVideo.setBackground(Color.BLACK);

        fxPanel = new JFXPanel();
        fxPanel.setPreferredSize(new Dimension(800,600));
        panelVideo.add(fxPanel,BorderLayout.CENTER);

        //panel où il y aura tous les boutons
        JPanel panelOption = new JPanel();
        panelOption.setBackground(Color.BLACK);
        JButton boutonPlay = new JButton("Play");
        boutonPlay.setIcon(new ImageIcon("image/play.png"));
        boutonPlay.addActionListener(e -> actionPlay());
        JButton boutonPause = new JButton("Pause");
        boutonPause.setIcon(new ImageIcon("image/pause.png"));
        boutonPause.addActionListener(e -> actionPause());
        JButton boutonStop = new JButton("Stop");
        boutonStop.setIcon(new ImageIcon("image/stop.png"));
        boutonStop.addActionListener(e -> actionStop());
        JButton fullScreen = new JButton();
        fullScreen.setIcon(new ImageIcon("image/fullScreen.png"));
        fullScreen.addActionListener(e -> fenetreFullScreen());
        panelOption.add(boutonPlay);
        panelOption.add(boutonPause);
        panelOption.add(boutonStop);
        panelOption.add(fullScreen);
        panelVideo.add(panelOption, BorderLayout.SOUTH);

        frame.add(panelVideo,BorderLayout.CENTER);

        //panel pour mettre le synopsis
        JPanel panelSynopsis = new JPanel(new BorderLayout());
        panelSynopsis.setPreferredSize(new Dimension(0,100));
        panelSynopsis.setBackground(Color.BLACK);
        JLabel zoneSynopsis = new JLabel("Synopsis :  " );
        zoneSynopsis.setForeground(Color.WHITE);
        panelSynopsis.add(zoneSynopsis);
        frame.add(panelSynopsis,BorderLayout.SOUTH);

        //panel pour toutes les autres infos
        JPanel panelInfo = new JPanel(new BorderLayout());
        panelInfo.setPreferredSize(new Dimension(300,0));
        panelInfo.setBackground(Color.BLACK);
        JLabel zoneTitre = new JLabel("Titre :  " );
        zoneTitre.setForeground(Color.WHITE);
        panelInfo.add(zoneTitre);
        frame.add(panelInfo,BorderLayout.WEST);

        frame.setVisible(true);
        initFX();

    }
    JFrame frameVideo ;
    public static void fenetreFullScreen (){
        JFrame frameVideo = new JFrame("BVW / Lecture");
        frameVideo.setLayout(new BorderLayout());
        frameVideo.setIconImage(icon);
        frameVideo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameVideo.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frameVideo.setUndecorated(true); //pour enlever la barre windows en haut
        frameVideo.add(fxPanel);
        frameVideo.setVisible(true);

        //panel où il y aura tous les boutons
        JPanel panelOptionFullScreen = new JPanel();
        panelOptionFullScreen.setBackground(Color.BLACK);
        JButton boutonPlay = new JButton("Play");
        boutonPlay.setIcon(new ImageIcon("image/play.png"));
        boutonPlay.addActionListener(e -> actionPlay());
        JButton boutonPause = new JButton("Pause");
        boutonPause.setIcon(new ImageIcon("image/pause.png"));
        boutonPause.addActionListener(e -> actionPause());
        JButton boutonStop = new JButton("Stop");
        boutonStop.setIcon(new ImageIcon("image/stop.png"));
        boutonStop.addActionListener(e -> actionStop());
        JButton fullScreen = new JButton();
        fullScreen.setIcon(new ImageIcon("image/fullScreen.png"));
        fullScreen.addActionListener(e -> close(frameVideo));
        panelOptionFullScreen.add(boutonPlay);
        panelOptionFullScreen.add(boutonPause);
        panelOptionFullScreen.add(boutonStop);
        panelOptionFullScreen.add(fullScreen);
        panelVideo.add(panelOptionFullScreen, BorderLayout.SOUTH);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                initFX();
            }
        });
    }

    public static void closeFrame(){ // parce que j'ai 2 fenêtres qui s'ouvrent
        frame.dispose();
    }
    public static void close(JFrame frame){
        frame.dispose();
    }
    public static MediaPlayer mediaPlayer;
    private static void initFX() {

        Media media = new Media(new File(UpdatePath()).toURI().toString());
        System.out.println(media);
        mediaPlayer = new MediaPlayer(media);
        System.out.println(media);
        mediaPlayer.setAutoPlay(false);
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
    public static void actionPlay(){
        mediaPlayer.play();
    }
    public static void actionStop(){
        mediaPlayer.stop();
    }
    public static void actionPause(){
        mediaPlayer.pause();
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
