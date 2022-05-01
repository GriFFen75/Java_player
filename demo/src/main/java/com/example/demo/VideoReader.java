package com.example.demo;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import javax.swing.*;
import java.awt.*;
import java.io.File;


public class VideoReader {

    public  String file ;
    public static JFrame frame = new JFrame("BVW / Lecteur vidéos");
    public  JPanel panelVideo;
    public  JPanel panelFx;
    //private static MediaPlayer mediaPlayer;
    public  Image icon = Toolkit.getDefaultToolkit().getImage("image/BVW.png");

    private void initAndShowGUI() {

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setSize(new Dimension(800, 600));
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        frame.setIconImage(icon);
        createPanel();
    }
    private void createPanel(){
        //créer un panel pour réunir la video + la barre avec les boutons pour la modif de la vidéo
        panelVideo = new JPanel(new BorderLayout());
        //on met de border pour que ça sois plus joli
        panelVideo.setBorder(BorderFactory.createLineBorder(Color.white, 1));
        //panelVideo.setBackground(Color.BLACK);

        JFXPanel fxPanel = new JFXPanel();
        //fxPanel.setPreferredSize(new Dimension(800,600));

        panelFx = new JPanel();
        panelFx.add(fxPanel);

        panelVideo.add(panelFx,BorderLayout.CENTER);

        //panel où il y aura tous les boutons
        JPanel panelOption = new JPanel();
        //panelOption.setBackground(Color.BLACK);
        panelOption.setOpaque(false);
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
        fullScreen.addActionListener(e -> {
            fenetreFullScreen();
            close(frame);
        });
        panelOption.add(boutonPlay);
        panelOption.add(boutonPause);
        panelOption.add(boutonStop);
        panelOption.add(fullScreen);
        panelVideo.add(panelOption, BorderLayout.SOUTH);

        frame.add(panelVideo,BorderLayout.CENTER);


        frame.setVisible(true);
        System.out.println("HelloApllication.createpanel : "+file);
        Platform.runLater(() -> initFX(fxPanel));

    }
    private void fenetreFullScreen (){
        JFrame frameVideo = new JFrame("BVW / Lecture");
        frameVideo.setLayout(new BorderLayout());
        frameVideo.setIconImage(icon);
        frameVideo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameVideo.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frameVideo.setUndecorated(true); //pour enlever la barre windows en haut

        JFXPanel fxPanel = new JFXPanel();
        frameVideo.add(fxPanel,BorderLayout.CENTER);
        frameVideo.setVisible(true);

        //panel où il y aura tous les boutons
        JPanel panelOptionFullScreen = new JPanel();
        //panelOptionFullScreen.setBackground(Color.BLACK);
        panelOptionFullScreen.setOpaque(false);
        //panelOptionFullScreen.setBackground(Color.BITMASK);
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
        fullScreen.addActionListener(e -> {
            //exitMP();
            close(frameVideo);
            //System.out.println(file);
            new VideoReader(file);
        });

        panelOptionFullScreen.add(boutonPlay);
        panelOptionFullScreen.add(boutonPause);
        panelOptionFullScreen.add(boutonStop);
        panelOptionFullScreen.add(fullScreen);
        frameVideo.add(panelOptionFullScreen, BorderLayout.SOUTH);
        Platform.runLater(() -> initFX(fxPanel));
    }
    private static MediaPlayer mediaPlayer;
    private void actionPlay(){
        mediaPlayer.play();
    }


    private static void close (Frame frame){
        frame.dispose();
    }
    public static void close(){ //pour éviter la surcharge
        close(frame);
    }
    private void exitMP(){
        mediaPlayer.stop();
        mediaPlayer.dispose();
        mediaPlayer = null;
    }

    private void initFX(JFXPanel fxPanel) { // beug sur cette partie je pense (media , mediaplayer , mediaview??)

        Media media = new Media(new File(file).toURI().toString()); // il faudrait faire un code de verification si le media existe
        mediaPlayer = new MediaPlayer(media);
        //mediaPlayer.setAutoPlay(false);
        MediaView mediaView = new MediaView(mediaPlayer);

        Group root = new Group();
        root.getChildren().add(mediaView);
        DoubleProperty mvw = mediaView.fitWidthProperty(); //code pour redimentionner la jfxframe a la bonne taille
        DoubleProperty mvh = mediaView.fitHeightProperty();
        mvw.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
        mvh.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
        mediaView.setPreserveRatio(true);
        //System.out.println(media);



        //Scene scene = new Scene(root,1000,800);
        fxPanel.setScene(new Scene(new Group(mediaView), panelVideo.getWidth(), panelVideo.getHeight()));
    }

    private void actionStop(){
        mediaPlayer.stop();
    }
    private void actionPause(){
        mediaPlayer.pause();
    }


    /*public static String UpdatePath(){
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        String path;
        path = (s+"/video/"+file);
        return path;
    }*/
    public VideoReader(String lien) {//,String path
        //SwingUtilities.invokeLater(() -> {
        file = lien;
        initAndShowGUI();
        //});
    }
}