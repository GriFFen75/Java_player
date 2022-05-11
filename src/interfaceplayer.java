import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static com.drew.metadata.datareader.Readerwiwi;


//import com.tutorialspoint.media.demo;

public class interfaceplayer extends JFrame  {

    public String path = null;
    public String titreVideo;
    public  JLabel ZoneAffichageTitre = new JLabel();
    public JLabel ZoneAffichageAuteur = new JLabel();  // voir a zoneTitre Pourquoi elles sont ici
    public JLabel ZoneAffichageDateC = new JLabel();
    public JLabel ZoneAffichageDuree = new JLabel();
    public JLabel ZoneAffichageExtension = new JLabel();
    public static JCheckBoxMenuItem ext3;
    public static JCheckBoxMenuItem ext2;
    public static JCheckBoxMenuItem ext1;
    public  Image icon = Toolkit.getDefaultToolkit().getImage("image/BVW.png");
    public  JPanel panel1 = new JPanel(); //ma fenetre droite // défini ici car j'en est besoin plus haut
    public JPanel contentpane = (JPanel) getContentPane();
    public  JTextField ZoneDeTexte;
    public  JList<String> ZoneTitre;
    public  JPanel panelInfo = new JPanel(new GridLayout(9,1));
    public  String pathTitre;
    public  File PathDossier;


    public interfaceplayer() {



        setTitle("BVW"); //j'ai defini ce titre car on commence toujours avec le .mp4 en extension (le true dans la definition du bouton)
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // dispose_on_close permet de libérer les ressource de la frame et si c'est la dernière ca rend la main
        setSize(new Dimension(800, 600)); // bien mettre ce avant la localisation sinon bug
        //setExtendedState(JFrame.MAXIMIZED_BOTH); // pour mettre la fenetre en pleine écran
        setLocationRelativeTo(null);// on centre la fenetre

        //initialiser mes variables pour pouvoir les utiliser plus facilement
        ZoneDeTexte = new JTextField(""); //l'entré du texte


        //set de l'icone de l'appli
        setIconImage(icon);

        //on set le MenuBar
        setJMenuBar(CreationMenuBar());

        //on set le panel
        contentpane.setLayout(new BorderLayout());

        //on set le panel1
        SetPanel1();

       //on set le panel2
       SetPanel2();

       //on ajoute le fonctionnement au tri par extension
       TriExtension();

       // on ajoute le panel des metadata
        //SetPanelInfo();
        contentpane.add(panelInfo,BorderLayout.WEST);

    }
    public JMenuBar CreationMenuBar() {

        //ajoute le bar de menu
        JMenuBar mbr = new JMenuBar();

        //pour l'ouverture de la fenetre de recherche fichier
        JMenu mnuOpen = new JMenu("Ouvrir");
        mbr.add(mnuOpen);

        JMenuItem openFiles = new JMenuItem("Ouvrir un fichier");
        openFiles.setIcon(new ImageIcon("image/openFiles.png"));
        openFiles.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        openFiles.addActionListener(e -> {

            try {
                recherche.liste_fichier();
            } catch (UnsupportedLookAndFeelException ex) {
                throw new RuntimeException(ex);
            }
        });
        JMenuItem openFolder = new JMenuItem("Ouvrir un dossier");
        openFolder.setIcon(new ImageIcon("image/openFolder.png"));
        openFolder.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
        openFolder.addActionListener(e -> {
            ZoneTitre.removeAll();
            recherche.liste_dossier();
            PathDossier = recherche.pathDossier();
            path = PathDossier.toString();
            ZoneDeTexte.setCaretPosition(0);
        });
        System.out.println(PathDossier);
        mnuOpen.add(openFiles);
        mnuOpen.add(openFolder);

        JMenu mnuExtention = new JMenu("Extension");
        mbr.add(mnuExtention);
        ext3 = new JCheckBoxMenuItem(".mp3");
        ext3.setToolTipText("Ajoute l'extension .mp3 à vos recherche");
        ext2 = new JCheckBoxMenuItem(".avi");
        ext2.setToolTipText("Ajoute l'extension .avi à vos recherche");
        ext1 = new JCheckBoxMenuItem(".mp4");
        ext1.setToolTipText("Ajoute l'extension .mp4 à vos recherche");

        //extension 1 (mp4)
        ext1.setMnemonic(KeyEvent.VK_A);
        ext1.addItemListener(e -> {
            //mettre le code de ce qu'on veut faire si la case ext1 est coché
            //attention le code ne gère pas la décoche si on ne met pas de condition
            if (ext1.getState()) {
                setTitle(getTitle() + " .mp4");
            } else {
                if (ext2.getState() && ext3.getState()) {
                    setTitle("BVW" + " .avi" + " .mp3");
                } else if (ext2.getState()) {
                    setTitle("BVW" + " .avi");
                } else if (ext3.getState()) {
                    setTitle("BVW" + " .mp3");
                } else {
                    setTitle("BVW");
                }
            }

        });
        //extension 2 (avi)
        ext2.setMnemonic(KeyEvent.VK_Z);
        ext2.addItemListener(e -> {
            //mettre le code de ce qu'on veut faire si la case ext2 est coché
            if (ext2.getState()) {
                setTitle(getTitle() + " .avi");
            } else {
                if (ext1.getState() && ext3.getState()) {
                    setTitle("BVW" + " .mp4" + " .mp3");
                } else if (ext1.getState()) {
                    setTitle("BVW" + " .mp4");
                } else if (ext3.getState()) {
                    setTitle("BVW" + " .mp3");
                } else {
                    setTitle("BVW");
                }
            }
        });
        //extension 3 (mp3)
        ext3.setMnemonic(KeyEvent.VK_E);
        ext3.addItemListener(e -> {
            //mettre le code de ce qu'on veut faire si la case ext3 est coché
            if (ext3.getState()) {
                setTitle(getTitle() + " .mp3");
            } else {
                if (ext1.getState() && ext2.getState()) {
                    setTitle("BVW" + " .mp4" + " .avi");
                } else if (ext1.getState()) {
                    setTitle("BVW" + " .mp4");
                } else if (ext2.getState()) {
                    setTitle("BVW" + " .avi");
                } else {
                    setTitle("BVW");
                }
            }
        });
        mnuExtention.add(ext1);
        mnuExtention.add(ext2);
        mnuExtention.add(ext3);

        //on ajoute le trie dans la bar de menu
        JMenu mnuTri = new JMenu("Trier par");
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
        JButton BoutonModifData = new JButton();
        BoutonModifData.setIcon(new ImageIcon("image/modifMetadata.png"));
        BoutonModifData.setToolTipText("Ouvre la fenêtre de modification des metadata");
        BoutonModifData.addActionListener(e -> {
            JFrame FrameModifMetadata = new JFrame("BVW / Modification des metadatas");
            FrameModifMetadata.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            FrameModifMetadata.setLayout(new BorderLayout());
            FrameModifMetadata.setSize(new Dimension(800, 600));
            FrameModifMetadata.setLocationRelativeTo(null);
            FrameModifMetadata.setIconImage(icon);


            //mise en place des zones de lecture et d'écriture pour les metadata
            JPanel PanelModifMeta = new JPanel(new GridLayout(4, 2));
            try {
                PanelModifMeta.add(new JLabel("Vrais Titre : "+com.drew.metadata.datareader.ReaderTitle(pathTitre)));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (TikaException ex) {
                throw new RuntimeException(ex);
            } catch (SAXException ex) {
                throw new RuntimeException(ex);
            }
            JTextField ZoneDeTexteModifMeta = new JTextField();
            PanelModifMeta.add(ZoneDeTexteModifMeta);
            PanelModifMeta.add(new JLabel(ZoneAffichageAuteur.getText()));
            PanelModifMeta.add(new JTextField());
            PanelModifMeta.add(new JLabel(ZoneAffichageDateC.getText()));//"Date de création"+ Readerwiwi(path,"Creation Time"))
            PanelModifMeta.add(new JTextField());
            PanelModifMeta.add(new JLabel(ZoneAffichageDuree.getText()));
            PanelModifMeta.add(new JTextField());
            FrameModifMetadata.add(PanelModifMeta); //ajout dans la frame

            //bouton pour valider ou annuler nos choix
            JPanel PanelActionMeta = new JPanel();
            JButton appliquer = new JButton("Appliquer");
            appliquer.addActionListener(e1 -> {
                ////////////// mettre le code ici /////////////
                try {
                    new ajoutdata(pathTitre,ZoneDeTexteModifMeta.getText());
                    SetPanelInfo();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                FrameModifMetadata.dispose(); //on ferme la frame de modification mais uniquement quand on aura save les modif
            });
            JButton annuler = new JButton("Annuler");
            annuler.addActionListener(e12 -> FrameModifMetadata.dispose());
            PanelActionMeta.add(appliquer);
            PanelActionMeta.add(annuler);
            FrameModifMetadata.add(PanelActionMeta, BorderLayout.SOUTH);

            FrameModifMetadata.setVisible(true);
        });
        mbr.add(BoutonModifData);

        //bouton panel affiche playlist
        JButton BoutonShowPlaylist = new JButton();
        BoutonShowPlaylist.setIcon(new ImageIcon("image/playlist.png"));
        BoutonShowPlaylist.setToolTipText("Ouvre une nouvelle fenêtre pour voir la playlist");
        BoutonShowPlaylist.addActionListener(e -> {
            new PanelShowPlaylist();
            //panel.add(Playlist.affichage());
        });


        mbr.add(BoutonShowPlaylist);

        //bouton d'aide
        JButton BoutonAide = new JButton();
        BoutonAide.setIcon(new ImageIcon("image/aide6.png"));
        BoutonAide.setToolTipText("Ouvre une page d'aide");
        BoutonAide.addActionListener(e -> new OpenPageHelp());
        mbr.add(BoutonAide);



        return mbr;
    }
    public void SetPanel1() {
        //panel fichier // panel de droite
        panel1.setLayout(new BorderLayout());
        panel1.setBackground(Color.black);
        panel1.setBorder(BorderFactory.createLineBorder(Color.white, 1));

        //affichage de texte
        ZoneTitre = new JList<>();

        new JScrollPane(panel1);
        //panel1.setPreferredSize(new Dimension(200,0));
        ZoneTitre.setBackground(Color.lightGray);
        System.out.println("BONJOUR");
            ZoneTitre.addListSelectionListener(et -> ZoneTitre.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.out.println("COUCOU");
                 if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 1){
                    try {
                        actionClicZoneTitre();
                    } catch (UnsupportedLookAndFeelException ex) {
                        throw new RuntimeException(ex);
                    }
                }
//                else if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 2) {
//                    try {
//                        System.out.println("le montant de clic dans setpanel1 : "+e.getClickCount());
//                        //actionClicZoneTitre ();
//                        demo.video(pathTitre);
//                    } catch (UnsupportedLookAndFeelException ex) {
//                        throw new RuntimeException(ex);
//                    }
//
//                }
                else{
                    System.out.println("NOP");

                }
                System.out.println("WTF");
            }
        }));

        panel1.add(ZoneTitre, BorderLayout.EAST);

        PopMenuClicDroit();

        contentpane.add(panel1);
    }
    public void actionClicZoneTitre () throws UnsupportedLookAndFeelException {


        titreVideo = ZoneTitre.getSelectedValue();
        pathTitre = path + "/" + ZoneTitre.getSelectedValue();
        System.out.println("normalement :   "+pathTitre);

//        System.out.println("le getSelectedValue interfaceplayer.setpanel1 : " + ZoneTitre.getSelectedValue());
//        System.out.println("le path du dossier interfaceplayer.setpanel1 : " + path);
        System.out.println("le path du fichier interfaceplayer.setpanel1 : " + pathTitre);
//        System.out.println("le titreVideo interfaceplayer.setpanel1 : " + titreVideo);
//        System.out.println("si le getSelectedValue et le titreVideo sont différent c'est qu'il y a une erreur");

        ZoneAffichageExtension.setText(ExtensionGetTexte());//Readerwiwi("fan.mp4","Expected File Name Extension") // le mettre en premier sinon ca l'update pas pour les gif et les avi
//        System.out.println("cheelou ici "+ExtensionGetTexte());
        ZoneAffichageTitre.setText("Titre :   " + titreVideo); // il faut définir chacune des Zones avant la fonction car sinon on ne peut pas accéder a au Zones dans l'action
        //ZoneAffichageAuteur.setText(); //attend que l'api de william sois prête
        ZoneAffichageDateC.setText("Date de création :   " + Readerwiwi(pathTitre, "Creation Time"));
        ZoneAffichageDuree.setText("Durée :     " + Readerwiwi(pathTitre, "Duration in Seconds"));
        if(pathTitre.contains("null")){
            System.out.println("\nLe path contient null\n");
            System.exit(0);
        }
        System.out.println("\n\n");
        //demo.video(pathTitre);

        //new VideoReader(pathTitre); //on lance main de HelloApllication en recupérant le texte situer dans la Zone du Titre
//                        VideoReader.close(); // oblige de faire ca sinon il y a 2 fenêtres
        //SetPanelInfo();// pour afficher le panel info apres avoir lancer le film
        //System.out.println("affichage de la ZoneTitre dans le main : "+ZoneTitre);
    }
    public void PopMenuClicDroit(){
        JPopupMenu ClicDroitMenu = new JPopupMenu("Menu");

        JMenuItem ouvrir = new JMenuItem("Ouvrir");
        ouvrir.addActionListener(e -> {
            //System.out.println("wjoaidhbjfnjaos "+titreVideo);
            try {
                actionClicZoneTitre();
                demo.video(pathTitre);
            } catch (UnsupportedLookAndFeelException ex) {
                throw new RuntimeException(ex);
            }
        });

        JMenuItem addPlaylist = new JMenuItem("Ajouter à la playlist");
        addPlaylist.addActionListener(e -> {
            pathTitre = path + "/"+ZoneTitre.getSelectedValue();
            //System.out.println("bizzare nbon /"+ZoneTitre.getSelectedValue());
            Playlist.ajout(pathTitre);
        });

        JMenuItem afficheMetadata = new JMenuItem("Afficher les metadata");
        afficheMetadata.addActionListener(e -> {
            SetPanelInfo();
        });

        ClicDroitMenu.add(ouvrir);
        ClicDroitMenu.add(addPlaylist);
        ClicDroitMenu.add(afficheMetadata);

            ZoneTitre.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (SwingUtilities.isRightMouseButton(e) && e.getClickCount() == 1) {
                        ClicDroitMenu.show(ZoneTitre, e.getX(), e.getY());

                    }
                }
            });

    }

    JLabel VraiTitre = new JLabel();
    JTextArea Synopsis = new JTextArea();
    JLabel Director = new JLabel();
    JLabel Auteur = new JLabel();
    JLabel Acteur = new JLabel();
    JLabel Pays = new JLabel();
    public void SetPanelInfo() {
        try{
            //code du panel pour les infos

            panelInfo.setBackground(Color.black);
            panelInfo.setForeground(Color.white);
            panelInfo.setPreferredSize(new Dimension(500,0));
            VraiTitre.setText("Titre : "+com.drew.metadata.datareader.ReaderTitle(pathTitre));//VraiTitre.setText("Vrai titre : "+apiwiwi.Searchwiwi(titreVideo,"Title"));
            VraiTitre.setForeground(Color.white);
            panelInfo.add(VraiTitre);

            Director.setText("Directeur : "+apiwiwi.Searchwiwi(com.drew.metadata.datareader.ReaderTitle(pathTitre),"Director"));
            Director.setForeground(Color.white);
            panelInfo.add(Director);

            Auteur.setText("Auteur : "+apiwiwi.Searchwiwi(com.drew.metadata.datareader.ReaderTitle(pathTitre),"Writer"));
            Auteur.setForeground(Color.white);
            panelInfo.add(Auteur);

            Acteur.setText("Acteurs : "+apiwiwi.Searchwiwi(com.drew.metadata.datareader.ReaderTitle(pathTitre),"Actors"));
            Acteur.setForeground(Color.white);
            panelInfo.add(Acteur);

            Pays.setText("Pays : "+apiwiwi.Searchwiwi(com.drew.metadata.datareader.ReaderTitle(pathTitre),"Country"));
            Pays.setForeground(Color.white);
            panelInfo.add(Pays);

            Synopsis.setText("Synopsis : "+apiwiwi.Searchwiwi(com.drew.metadata.datareader.ReaderTitle(pathTitre),"Plot"));
            Synopsis.setForeground(Color.white);
            Synopsis.setBackground(Color.black);
            Synopsis.setLineWrap(true);
            Synopsis.setWrapStyleWord(true);
            Synopsis.setEditable(false);
            //Synopsis.setSize(500,200);
            //Synopsis.setMaximumSize(new Dimension(300,200));
            panelInfo.add(Synopsis);


            // on ajoute ce qu'on veut ici
        }
        catch (IOException e){
            System.out.println("SetPanelInfo : "+e);
        } catch (TikaException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }

    }
    public void SetPanel2() {
        ZoneAffichageTitre.setForeground(Color.white);
        ZoneAffichageAuteur.setForeground(Color.white);
        ZoneAffichageDateC.setForeground(Color.white);
        ZoneAffichageDuree.setForeground(Color.white);
        ZoneAffichageExtension.setForeground(Color.white);
        ZoneAffichageAuteur.setMaximumSize(new Dimension(20, 0)); // on peut définir une taille maximale


        //fenetre info (en bas)
        JPanel panel2 = new JPanel(); // ma fenetre au sud pour les info
        panel2.setLayout(new GridLayout(4, 1)); // ligne, colonne
        panel2.setBackground(Color.black);
        panel2.setForeground(Color.white);
        panel2.setBorder(BorderFactory.createLineBorder(Color.white, 1));
        contentpane.add(panel2, BorderLayout.SOUTH);
        panel2.add(ZoneAffichageTitre);
        panel2.add(ZoneAffichageAuteur);
        panel2.add(ZoneAffichageDateC);
        panel2.add(ZoneAffichageDuree);
        panel2.add(ZoneAffichageExtension);
        panel2.setPreferredSize(new Dimension(0, 50)); //pas besoin de mettre de valeur en x vue que elle est mise automatiquement

        // ajout d'une frontière qui bouge entre le panelInfo et la fenetre centrale
//        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT , panelInfo , panel1); // on est remettra un quand la fenetre sera faite
//        contentpane.add(splitPane);

        //zone de texte recherche //on recupère en temps réel le texte
        ZoneDeTexte.setBackground(Color.black);
        ZoneDeTexte.setForeground(Color.white);

    }
    public void TriExtension(){
        ArrayList<String> extensionss = new ArrayList<>();
        final boolean[] vrai = {false};
        final boolean[] vrai2 = {false};
        final boolean[] vrai3 = {false};

        ZoneDeTexte.addKeyListener(new java.awt.event.KeyAdapter() {

            public void keyReleased(java.awt.event.KeyEvent e) {
                // jTextField.getText() contient le texte présent dans le JTextField après le relachement de la touche
                // e.getKeyChar() est le caractère correspondant à la touche relachée
                String texte = ZoneDeTexte.getText();
                //ZoneAffichageTitre3.setText(texte);
                //System.out.println(texte);

                // ici william, ou e parle tut seul
                //MaxBouton = ZoneTitre.getLastVisibleIndex(); // pour savoir le nombre max de titre qu'il y a affiché
                //System.out.println("le MaxBouton a mettre dans triExtension : "+MaxBouton);
                //SetpanelBouton();

                //System.out.println("??");
                // pour faire fonctionner les boutons d'extension
                if(ext3.isSelected()){
                    if(!extensionss.contains(".mp3")) {
                        extensionss.add(".mp3");
                        vrai[0] = false;
                        //System.out.println("mp3");
                    }
                }
                else{
                    vrai[0] = true;
                    extensionss.remove(".mp3");
                }
                if(ext2.isSelected()){
                    if(!extensionss.contains(".avi")) {
                        extensionss.add(".avi");
                        vrai2[0] = false;
                        //System.out.println("avi");
                    }
                }
                else{
                    extensionss.remove(".avi");
                    vrai2[0] = true;
                }

                if(ext1.isSelected()){
                    if(!extensionss.contains(".mp4")) {
                        extensionss.add(".mp4");
                        vrai3[0] = false;
                        //System.out.println("mp4");
                    }
                }
                else{
                    vrai3[0] = true;
                    extensionss.remove(".mp4");
                }
                if(!vrai[0] || !vrai2[0] ||! vrai3[0]) {
                    ZoneTitre.setListData(recherche.barre_recherche(texte, extensionss));
                }
                else{
                    ZoneTitre.setListData(recherche.barre_recherche(texte, (ArrayList) recherche.fichiers));
                }
            }
        });
        //JScrollPane JSCTitre = new JScrollPane(panel2);
        contentpane.add(ZoneDeTexte,BorderLayout.NORTH); //fenetre a ecrire
    }

    public String ExtensionGetTexte(){
        String titre = ZoneAffichageTitre.getText();
        String []titreChaine = titre.split("\\."); //pour dire qu'on split bien avec un . sinon  ca fonctionne pas
        return titreChaine[titreChaine.length-1];
    }

    public static void main(String[] args) throws Exception {
        //recherche.liste_fichier();

        //mettre un look
        UIManager.setLookAndFeel(new NimbusLookAndFeel()); //look nimbus

        //créer la fenetre avec le constructeur interfaceplayer
        interfaceplayer fenetre = new interfaceplayer();
        fenetre.dispose();
        new interfaceplayer();

        //VideoReader.main(null,null); //à l'ouverture de cette fenêtre on ferme la page de principal et quand on ferme cette page on r'ouvre la page principal
    }
}