import javax.swing.*;
import java.awt.*;

public class PanelShowPlaylist extends JFrame {
    public void CreateFrame(){
        JFrame frame = new JFrame("BVW / Playlist");
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("image/BVW.png"));
        frame.setSize(new Dimension(800,600));
        frame.setLocationRelativeTo(null);

        CreatePanel();

        frame.setVisible(true);
    }
    public void CreatePanel(){
        JPanel panel = new JPanel();

        System.out.println(Playlist.affichage());
        //panel.add(Playlist.liste)

        this.add(panel);
    }
    public PanelShowPlaylist(){
        CreateFrame();
    }
}
