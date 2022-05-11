import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


public class PanelShowPlaylist extends JFrame {

    private JList<String> langages;
    public JList<String> ListePourAffichage = new JList<>();
    public JPanel panel;

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
//        JFrame frame = new JFrame();
//        frame.setSize(250, 250);
//        frame.setVisible(true);
        panel = new JPanel();


        System.out.println("PanelShowPlaylist.createPanel : Playlist affichage : " + affichage());
        //panel.add(Playlist.liste);
        //System.out.println("panelshowplayliste.createpanel playlist.affichage"+Playlist.affichage());
        panel.setLayout(new BorderLayout());
        panel.add(affichage(),BorderLayout.EAST);
        panel.add(new JLabel("test"), BorderLayout.NORTH);
        //ajoutJList();
        //panel.add(new JLabel("coucou"));

        JButton myButton = new JButton("My Button");
        panel.add(myButton);

        //créer la liste des langages

        System.out.println("get selected value dans paneshowplaylist.createpanel : "+ListePourAffichage.indexToLocation(1));


        frame.add(panel);
    }
    public JList<String> affichage() {

        String[] splitList = Playlist.liste.toString().split(",");
        //System.out.println(splitList[1]);



        for (int i = 0; i < splitList.length; i++) {
            System.out.println("les titre dans la playlist.affichage"+splitList[i]);
            ListePourAffichage.setListData(recherche.barre_recherche(splitList[i], (ArrayList) recherche.fichiers));
            //System.out.println("liste pour affichage dans playlist.affichage"+ListePourAffichage);
            //System.out.println("Playlist.affichage : ListePourAffichage dans la boucle for : " + ListePourAffichage);
            //JLabel label = new JLabel(String.valueOf(liste), JLabel.CENTER);
            //frame.add(label);

        }
        //System.out.println("playliste.affichage : listepouraffichage : "+ListePourAffichage);
//        System.out.println("Playlist.affichage : ListePourAffichage à l'index 1 : "+ListePourAffichage);
//        System.out.println("Playlist.affichage : ListePourAffichage en dehors de la boucle for : "+ListePourAffichage);
        return ListePourAffichage;
    }
    public void ajoutJList(){
        String[] splitList = Playlist.liste.toString().split(",");
        //System.out.println(splitList[1]);



        for (int i = 0; i < splitList.length; i++) {
            System.out.println("les titre dans la playlist.affichage" + splitList[i]);
            ListePourAffichage.setListData(recherche.barre_recherche(splitList[i], (ArrayList) recherche.fichiers));
        }
        panel.add(ListePourAffichage,BorderLayout.NORTH);
    }
    public PanelShowPlaylist(){
        CreateFrame();
    }
}
