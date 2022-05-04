import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PanelShowPlaylist extends JFrame {

    public JFrame frame;
    public void CreateFrame(){
        frame = new JFrame("BVW / Playlist");
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("image/BVW.png"));
        frame.setSize(new Dimension(800,600));
        frame.setLocationRelativeTo(null);

        CreatePanel();

        frame.setVisible(true);
    }
    public void CreatePanel(){
        JFrame frame = new JFrame("Hello World");
        frame.setSize(250, 250);
        frame.setVisible(true);

        //System.out.println("PanelShowPlaylist.createPanel : Playlist affichage : " + Playlist.affichage());
        //panel.add(Playlist.liste)
        System.out.println("test de caca"+Playlist.affichage());
        frame.add(Playlist.affichage());
        frame.setLayout(new BorderLayout());
        frame.add(new JLabel("coucou"));
        frame.setBackground(Color.black);

        frame.add(frame);
    }
    public PanelShowPlaylist(){
        CreateFrame();
    }
}
