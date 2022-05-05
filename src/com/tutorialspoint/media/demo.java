package com.tutorialspoint.media;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serial;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import uk.co.caprica.vlcj.media.Media;
import uk.co.caprica.vlcj.media.MediaEventAdapter;
import uk.co.caprica.vlcj.media.MediaParsedStatus;
import uk.co.caprica.vlcj.media.TrackInfo;
import uk.co.caprica.vlcj.media.callback.CallbackMedia;
import uk.co.caprica.vlcj.player.base.ChapterDescription;
import uk.co.caprica.vlcj.player.base.Logo;
import uk.co.caprica.vlcj.player.base.LogoPosition;
import uk.co.caprica.vlcj.player.base.Marquee;
import uk.co.caprica.vlcj.player.base.MarqueePosition;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.base.TitleDescription;
import uk.co.caprica.vlcj.player.base.TrackDescription;
import uk.co.caprica.vlcj.player.component.AudioPlayerComponent;
import uk.co.caprica.vlcj.player.component.CallbackMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.fullscreen.adaptive.AdaptiveFullScreenStrategy;

public class demo extends JFrame {
    @Serial
    private static final long serialVersionUID = 1L;
    private static final String AUDIO_PATH = "file:///Users/wx/IdeaProjects/Java_player/video/akatsuki.mp3";
    private static final String LOGO_PATH = "file:///Users/wx/IdeaProjects/Java_player/image/play.png";
    private final CallbackMediaPlayerComponent mediaPlayerComponent;
    private final AudioPlayerComponent audioPlayerComponent;

    public demo(String title) {
        super(title);
        mediaPlayerComponent = new CallbackMediaPlayerComponent() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Mouse Clicked. (" + e.getX() + "," + e.getY() + ")");
            }

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                super.mouseWheelMoved(e);
                System.out.println("Mouse wheel moved. " + e.getScrollAmount());
            }

            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                System.out.println("Key pressed. " + e.getKeyCode());
            }

            @Override
            public void playing(MediaPlayer mediaPlayer) {
                super.playing(mediaPlayer);
                System.out.println("Media Playback started.");
            }

            @Override
            public void finished(MediaPlayer mediaPlayer) {
                super.playing(mediaPlayer);
                System.out.println("Media Playback finished.");
            }

            @Override
            public void error(MediaPlayer mediaPlayer) {
                SwingUtilities.invokeLater(() -> System.out.println("Failed to load Media."));
            }
        };
        audioPlayerComponent = new AudioPlayerComponent();
        audioPlayerComponent.mediaPlayer().events().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
            @Override
            public void finished(MediaPlayer mediaPlayer) {
                System.out.println("Audio Playback Finished.");
            }
            @Override
            public void error(MediaPlayer mediaPlayer) {
                System.out.println("Failed to load Audio.");
            }
        });
        Marquee marquee = Marquee.marquee()
                .text("sous titre de william")
                .size(40)
                .colour(Color.WHITE)
                .position(MarqueePosition.BOTTOM_RIGHT)
                .opacity(0.5f)
                .enable();
        mediaPlayerComponent.mediaPlayer().marquee().set(marquee);
        Logo logo = Logo.logo()
                .file(LOGO_PATH)
                .position(LogoPosition.TOP_LEFT)
                .opacity(0.3f)
                .enable();
        mediaPlayerComponent.mediaPlayer().logo().set(logo);
        mediaPlayerComponent.mediaPlayer().events().addMediaEventListener(new MediaEventAdapter() {
            @Override
            public void mediaParsedChanged(Media media,
                                           MediaParsedStatus newStatus) {
                if(newStatus == MediaParsedStatus.DONE) {
                    MediaPlayer mediaPlayer = mediaPlayerComponent.mediaPlayer();
                    List<TrackDescription> videoTracks = mediaPlayer.video().trackDescriptions();
                    System.out.println(videoTracks.get(1));

                    List<TitleDescription> titles = mediaPlayer.titles().titleDescriptions();
                    if(!titles.isEmpty()) {
                        System.out.println(titles.get(0));
                    }
                    List<ChapterDescription> chapters = mediaPlayer.chapters().descriptions();
                    if(!chapters.isEmpty()) {
                        System.out.println(chapters.get(0));
                    }
                    List<? extends TrackInfo> trackInfo = mediaPlayer.media().info().tracks();
                    if(!trackInfo.isEmpty()) {
                        System.out.println(trackInfo.get(0));
                    }
                }
            }
        });
        //mediaPlayerComponent.mediaPlayer().fullScreen().strategy(new AdaptiveFullScreenStrategy(this));
        //mediaPlayerComponent.mediaPlayer().media().play((CallbackMedia) this);
        //mediaPlayerComponent.mediaPlayer().fullScreen().set(true);
        mediaPlayerComponent.mediaPlayer().fullScreen().toggle();
    }
    public void initialize() {

        setBounds(100, 100, 800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                mediaPlayerComponent.release();
                //System.exit(0);
            }
        });
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(mediaPlayerComponent, BorderLayout.CENTER);

        //Create a video border
        Border videoBorder = BorderFactory.createTitledBorder("Video Controls");

        //Create an audio border
        Border audioBorder = BorderFactory.createTitledBorder("Audio Controls");

        //Create others border
        Border othersBorder = BorderFactory.createTitledBorder("Others");

        JPanel videoControlsPane = new JPanel();
        videoControlsPane.setBorder(videoBorder);
        JButton playButton = new JButton("Play");
        videoControlsPane.add(playButton);
        JButton pauseButton = new JButton("Pause");
        videoControlsPane.add(pauseButton);
        JButton rewindButton = new JButton("Rewind");
        videoControlsPane.add(rewindButton);
        JButton skipButton = new JButton("Skip");
        videoControlsPane.add(skipButton);

        JPanel audioControlsPane = new JPanel();
        audioControlsPane.setBorder(audioBorder);
        JButton playAudioButton = new JButton("Play");
        audioControlsPane.add(playAudioButton);
        JButton pauseAudioButton = new JButton("Pause");
        audioControlsPane.add(pauseAudioButton);

        JPanel othersPane = new JPanel();
        othersPane.setBorder(othersBorder);
        JToggleButton toggleButton = new JToggleButton("Toggle Full Screen");
        othersPane.add(toggleButton);
        JPanel controlsPane = new JPanel();
        controlsPane.add(videoControlsPane);
        controlsPane.add(audioControlsPane);
        controlsPane.add(othersPane);
        contentPane.add(controlsPane, BorderLayout.SOUTH);

        playButton.addActionListener(e -> mediaPlayerComponent.mediaPlayer().controls().play());
        pauseButton.addActionListener(e -> mediaPlayerComponent.mediaPlayer().controls().pause());
        rewindButton.addActionListener(e -> mediaPlayerComponent.mediaPlayer().controls().skipTime(-14000)); //en microseconde
        skipButton.addActionListener(e -> mediaPlayerComponent.mediaPlayer().controls().skipTime(4000));
        playAudioButton.addActionListener(e -> audioPlayerComponent.mediaPlayer().controls().play());
        pauseAudioButton.addActionListener(e -> audioPlayerComponent.mediaPlayer().controls().pause());
        toggleButton.addActionListener(e -> mediaPlayerComponent.mediaPlayer().fullScreen().toggle());

        this.setContentPane(contentPane);
        this.setVisible(true);
    }
    public void loadVideo(String path) {
        mediaPlayerComponent.mediaPlayer().media().startPaused(path);
        mediaPlayerComponent.mediaPlayer().media().parsing().parse();
    }
    public void loadAudio(String path) {
        audioPlayerComponent.mediaPlayer().media().startPaused(path);
    }

    public static void video(String video){
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("le titreee chelou :"+video);
        demo demolication = new demo("La video");
        demolication.initialize();
        demolication.setVisible(true);
        demolication.loadVideo(video);
        demolication.loadAudio(AUDIO_PATH);
    }

}

