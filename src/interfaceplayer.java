import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
//import javax.swing.ImageIcon;
import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import static com.drew.metadata.datareader.Readerwiwi;

public class interfaceplayer extends JFrame {

    String cc = "salut"; //juste pour le teste

    public interfaceplayer(){
        String path = "1365070268951.mp4";
        JPanel panel1 = new JPanel(); //ma fenetre droite // défini ici car j'en est besoin plus haut //environ ligne 260 sinon

        setTitle("BVW .mp4 .mp3 .avi"); //j'ai defini ce titre car on commence toujours avec le .mp4 en extension (le true dans la definition du bouton)
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // dispose_on_close permet de libérer les ressource de la frame et si c'est la dernière ca rend la main 
        setSize(new Dimension(800,600)); // bien mettre ce avant la localisation sinon bug
        //setExtendedState(JFrame.MAXIMIZED_BOTH); // pour mettre la fenetre en pleine écran 
        setLocationRelativeTo(null);// on centre la fenetre 

        //initialiser mes variables pour pouvoir les utiliser plus facilement
        JTextField ZoneDeTexte = new JTextField("");

        //set de l'icone de l'appli
        Image icon = Toolkit.getDefaultToolkit().getImage("image/BVW.png");
        setIconImage(icon);

        //ajoute le bar de menu //helper menu peux etre ??
        //il faudra mettre la bar de menu apres la fenetre de fichier si on veut qu'elle intéragisse avec les autre élément 
        JMenuBar mbr=new JMenuBar();
        JMenu mnuExtention=new JMenu("Extension");
        mbr.add(mnuExtention);
        JCheckBoxMenuItem ext3=new JCheckBoxMenuItem(".mp3",true);
        JCheckBoxMenuItem ext2=new JCheckBoxMenuItem(".avi",true);
        JCheckBoxMenuItem ext1=new JCheckBoxMenuItem(".mp4",true);

        //extension 1 (mp4)
        ext1.setMnemonic(KeyEvent.VK_A);
        ext1.addItemListener(e -> {
            //mettre le code de ce qu'on veut faire si la case ext1 est coché
            //attention le code ne gère pas la décoche si on ne met pas de condition
            if(ext1.getState()){
                setTitle(getTitle()+" .mp4");
            }
            else{
                if (ext2.getState() && ext3.getState()){
                    setTitle("BVW"+" .avi"+" .mp3");
                }
                else if(ext2.getState()){
                    setTitle("BVW"+" .avi");
                }
                else if(ext3.getState()){
                    setTitle("BVW"+" .mp3");
                }
                else{
                    setTitle("BVW");
                }
            }
        });
        //extension 2 (avi)
        ext2.setMnemonic(KeyEvent.VK_Z);
        ext2.addItemListener(e -> {
            //mettre le code de ce qu'on veut faire si la case ext2 est coché
            if(ext2.getState()){
                setTitle(getTitle()+" .avi");
            }
            else{
                if (ext1.getState() && ext3.getState()){
                    setTitle("BVW"+" .mp4"+" .mp3");
                }
                else if(ext1.getState()){
                    setTitle("BVW"+" .mp4");
                }
                else if(ext3.getState()){
                    setTitle("BVW"+" .mp3");
                }
                else{
                    setTitle("BVW");
                }
            }
        });
        //extension 3 (mp3)
        ext3.setMnemonic(KeyEvent.VK_E);
        ext3.addItemListener(e -> {
            //mettre le code de ce qu'on veut faire si la case ext3 est coché
            if(ext3.getState()){
                setTitle(getTitle()+" .mp3");
            }
            else{
                if (ext1.getState() && ext2.getState()){
                    setTitle("BVW"+" .mp4"+" .avi");
                }
                else if(ext1.getState()){
                    setTitle("BVW"+" .mp4");
                }
                else if(ext2.getState()){
                    setTitle("BVW"+" .avi");
                }
                else{
                    setTitle("BVW");
                }
            }
        });
        mnuExtention.add(ext1);
        mnuExtention.add(ext2);
        mnuExtention.add(ext3);

        //on ajoute le trie dans la bar de menu 
        JMenu mnuTri=new JMenu("Trier par");
        mbr.add(mnuTri);

        JRadioButton tri1 = new JRadioButton("Realisateur");
        tri1.addActionListener(e -> panel1.setBackground(Color.red));
        JRadioButton tri2 = new JRadioButton("Annee");
        tri2.addActionListener(e -> panel1.setBackground(Color.lightGray));
        JRadioButton tri3 = new JRadioButton("Alphabetique");
        tri3.addActionListener(e -> panel1.setBackground(Color.orange));
        //pour le tri realisateur
        tri1.setMnemonic(KeyEvent.VK_R); // pour passer dans le menu avec les touches alt + R

        // pour le tri annee
        tri2.setMnemonic(KeyEvent.VK_A);

        // pour le tri alphabetique
        tri3.setMnemonic(KeyEvent.VK_B);

        //definir que les boutons de tri en temps que groupe avant de les ajouter 
        ButtonGroup groupB = new ButtonGroup();
        groupB.add(tri1);
        groupB.add(tri2);
        groupB.add(tri3);
        groupB.getSelection();

        mnuTri.add(tri1);
        mnuTri.add(tri2);
        mnuTri.add(tri3);

        //ajout du bouton de modification des metadata
        JButton BoutonModifData = new JButton("Modifier Metadata");
        BoutonModifData.setBackground(Color.BLACK);
        BoutonModifData.setForeground(Color.WHITE);
        BoutonModifData.addActionListener(e -> {
            JFrame FrameModifMetadata = new JFrame("BVW / Modification des metadata");
            FrameModifMetadata.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            FrameModifMetadata.setLayout(new BorderLayout());
            FrameModifMetadata.setSize(new Dimension(800, 600));
            FrameModifMetadata.setLocationRelativeTo(null);
            FrameModifMetadata.setIconImage(icon);


            //mise en place des zones de lecture et d'ecriture pour les metadata
            JPanel PanelModifMeta = new JPanel(new GridLayout(4,2));
            PanelModifMeta.add(new JLabel("Titres"));
            PanelModifMeta.add(new JTextField());
            PanelModifMeta.add(new JLabel("Réalisateur"));
            PanelModifMeta.add(new JTextField());
            PanelModifMeta.add(new JLabel("Date de création"));
            PanelModifMeta.add(new JTextField());
            PanelModifMeta.add(new JLabel("Durée"));
            PanelModifMeta.add(new JTextField());
            FrameModifMetadata.add(PanelModifMeta); //ajout dans la frame

            //bouton pour valider ou annuler nos choix
            JPanel PanelActionMeta = new JPanel();
            JButton appliquer = new JButton("Appliquer");
            appliquer.addActionListener(e1 -> {
                ////////////// mettre le code ici /////////////
                FrameModifMetadata.dispose(); //on ferme la frame de modification mais uniquement quand on aura save les modif
            });
            JButton annuler = new JButton("Annuler");
            annuler.addActionListener(e12 -> FrameModifMetadata.dispose());
            PanelActionMeta.add(appliquer);
            PanelActionMeta.add(annuler);
            FrameModifMetadata.add(PanelActionMeta,BorderLayout.SOUTH);

            FrameModifMetadata.setVisible(true);
        });

        mbr.add(BoutonModifData);


        setJMenuBar(mbr);

        //on set le panneau
        JPanel contentpane = (JPanel) getContentPane();
        contentpane.setLayout(new BorderLayout());

        //zone de texte multiligne  //plus utiliser
        JTextArea ZoneTexteArea = new JTextArea();
        ZoneTexteArea.setBackground(Color.black);
        ZoneTexteArea.setForeground(Color.white);
        //contentpane.add(ZoneTexteArea);

        //premier boutton
        JButton PushMe1 = new JButton("1er_bouton");
        // donne l'action au bouton
        PushMe1.addActionListener(evt -> { // quand l'action evt ce produit avec la barre espace
            cc = ZoneTexteArea.getText();
            ZoneTexteArea.setText("");
        });

        //affichage de texte
        JLabel ZoneAffichageAuteur = new JLabel("Auteur :   "+cc); // on peut afficher les truc recupere dans des variables (test)
        ZoneAffichageAuteur.setForeground(Color.white);
        JLabel ZoneAffichageTitre = new JLabel("Titre :     "+cc);
        ZoneAffichageTitre.setForeground(Color.white);
        JLabel ZoneAffichageDateC = new JLabel("Date de création :     "+ Readerwiwi(path,"Creation Time"));
        ZoneAffichageDateC.setForeground(Color.white);
        JLabel ZoneAffichageDuree = new JLabel("Durée :     "+Readerwiwi(path,"Duration in Seconds"));
        ZoneAffichageDuree.setForeground(Color.white);
        JLabel ZoneAffichageTitre3 = new JLabel(ZoneDeTexte.getText());
        ZoneAffichageTitre3.setForeground(Color.white);
        ZoneAffichageAuteur.setMaximumSize(new Dimension(20,0)); // on peut deffinir une taille maximal 

        //panel fichier // panel de droite
        //panel1.setLayout(new FlowLayout());
        panel1.setBackground(Color.black);
        panel1.setBorder(BorderFactory.createLineBorder(Color.white, 1));
        //panel1.setPreferredSize(new Dimension(200,0));
        JList ZoneTitre = new JList();
        ZoneTitre.setBackground(Color.lightGray);
        panel1.add(ZoneTitre);

        contentpane.add(panel1);

        //la scrollbar pour la fenetre de dossier 
        JScrollPane JCB = new JScrollPane(new JTree());
        JCB.getHorizontalScrollBar().setVisible(true); //je sais pas pourquoi ca marche pas
        JCB.setPreferredSize(new Dimension(200,0)); //pas besoin de mettre de height elle est definie automatiquement
        contentpane.add(JCB,BorderLayout.WEST);

        //fenetre info
        JPanel panel2 = new JPanel(); // ma fenetre au sud pour les info
        panel2.setLayout(new GridLayout(4,1)); // ligne, colonne
        panel2.setBackground(Color.black);
        panel2.setForeground(Color.white);
        panel2.setBorder(BorderFactory.createLineBorder(Color.white, 1));
        //JScrollPane JSP = new JScrollPane(panel2);
        contentpane.add(panel2,BorderLayout.SOUTH);
        panel2.add(ZoneAffichageTitre);
        panel2.add(ZoneAffichageAuteur);
        panel2.add(ZoneAffichageDateC);
        panel2.add(ZoneAffichageDuree);
        panel2.add(ZoneAffichageTitre3);
        panel2.setPreferredSize(new Dimension(0,50)); //pas besoin de mettre de valeur en x vue que elle est mise automatiquement 

        //zone de texte recherche //on recupère en temps réel le texte
        ZoneDeTexte.setBackground(Color.black);
        ZoneDeTexte.setForeground(Color.white);

        ZoneDeTexte.addKeyListener(new java.awt.event.KeyAdapter() {

            public void keyReleased(java.awt.event.KeyEvent e) {
                // jTextField.getText() contient le texte présent dans le JTextField après le relachement de la touche
                // e.getKeyChar() est le caractère correspondant à la touche relachée
                String texte = ZoneDeTexte.getText();
                //ZoneAffichageTitre3.setText(texte);

                 ZoneTitre.setListData(recherche.barre_recherche(texte));
            }
        });
        contentpane.add(ZoneDeTexte,BorderLayout.NORTH); //fenetre a ecrire
    }

    public static void main(String[] args) throws Exception {
        recherche.liste_fichier();

        //mettre un look 
        UIManager.setLookAndFeel(new NimbusLookAndFeel()); //look nimbus

        //créer la fenetre avec le constructeur interfaceplayer
        interfaceplayer fenetre = new interfaceplayer();
        fenetre.dispose();
        new interfaceplayer();
    }
}
