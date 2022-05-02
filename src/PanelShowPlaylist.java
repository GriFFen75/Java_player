import javax.swing.*;
import java.awt.*;

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
        JPanel panel = new JPanel();

        //System.out.println("PanelShowPlaylist.createPanel : Playlist affichage : " + Playlist.affichage());
        //panel.add(Playlist.liste)
        panel.add(Playlist.affichage());
        panel.setLayout(new BorderLayout());
        panel.add(new JLabel("coucou"));
        panel.setBackground(Color.black);

        frame.add(panel);
    }
    public PanelShowPlaylist(){
        CreateFrame();
    }
}
