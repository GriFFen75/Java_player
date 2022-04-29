
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import static com.drew.metadata.datareader.Readerwiwi;

import com.example.demo.HelloApplication;

public class interfaceplayer extends JFrame  {

    public String path = null;
    public String titreVideo;
    public static JLabel ZoneAffichageTitre = new JLabel();
    public JLabel ZoneAffichageAuteur = new JLabel();  // voir a zoneTitre Pourquoi elles sont ici
    public JLabel ZoneAffichageDateC = new JLabel();
    public JLabel ZoneAffichageDuree = new JLabel();
    public JLabel ZoneAffichageExtension = new JLabel();
    public static JCheckBoxMenuItem ext3;
    public static JCheckBoxMenuItem ext2;
    public static JCheckBoxMenuItem ext1;

    public interfaceplayer(){

        JPanel panel1 = new JPanel(); //ma fenetre droite // défini ici car j'en est besoin plus haut //environ ligne 260 sinon

        setTitle("BVW"); //j'ai defini ce titre car on commence toujours avec le .mp4 en extension (le true dans la definition du bouton)
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // dispose_on_close permet de libérer les ressource de la frame et si c'est la dernière ca rend la main
        setSize(new Dimension(800,600)); // bien mettre ce avant la localisation sinon bug
        //setExtendedState(JFrame.MAXIMIZED_BOTH); // pour mettre la fenetre en pleine écran 
        setLocationRelativeTo(null);// on centre la fenetre 

        //initialiser mes variables pour pouvoir les utiliser plus facilement
        JTextField ZoneDeTexte = new JTextField(""); //l'entré du texte

        //set de l'icone de l'appli
        Image icon = Toolkit.getDefaultToolkit().getImage("image/BVW.png");
        setIconImage(icon);

        //ajoute le bar de menu
        JMenuBar mbr=new JMenuBar();

        //pour l'ouverture de la fenetre de recherche fichier
        JMenu mnuOpen = new JMenu("Ouvrir");
        mbr.add(mnuOpen);

        JMenuItem openFiles = new JMenuItem("Ouvrir un fichier");
        openFiles.setIcon(new ImageIcon("image/openFiles.png"));
        openFiles.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        openFiles.addActionListener(e -> {
            recherche.liste_fichier();
        });
        JMenuItem openFolder = new JMenuItem("Ouvrir un dossier");
        openFolder.setIcon(new ImageIcon("image/openFolder.png"));
        openFolder.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
        openFolder.addActionListener(e -> {
            recherche.liste_dossier();
        });

        mnuOpen.add(openFiles);
        mnuOpen.add(openFolder);

        JMenu mnuExtention=new JMenu("Extension");
        mbr.add(mnuExtention);
        ext3=new JCheckBoxMenuItem(".mp3");
        ext3.setToolTipText("Ajoute l'extension .mp3 à vos recherche");
        ext2=new JCheckBoxMenuItem(".avi");
        ext2.setToolTipText("Ajoute l'extension .avi à vos recherche");
        ext1=new JCheckBoxMenuItem(".mp4");
        ext1.setToolTipText("Ajoute l'extension .mp4 à vos recherche");

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
            JPanel PanelModifMeta = new JPanel(new GridLayout(4,2));
            PanelModifMeta.add(new JLabel(ZoneAffichageTitre.getText()));
            PanelModifMeta.add(new JTextField());
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


        //bouton d'aide
        JButton BoutonAide = new JButton();
        BoutonAide.setIcon(new ImageIcon("image/aide6.png"));
        BoutonAide.setToolTipText("Ouvre une page d'aide");
        BoutonAide.addActionListener(e -> OpenPageHelp.main(null));
        mbr.add(BoutonAide);


        setJMenuBar(mbr);

        //on set le panneau
        JPanel contentpane = (JPanel) getContentPane();
        contentpane.setLayout(new BorderLayout());

        //zone de texte multiligne  //plus utiliser
        JTextArea ZoneTexteArea = new JTextArea();
        ZoneTexteArea.setBackground(Color.black);
        ZoneTexteArea.setForeground(Color.white);
        //contentpane.add(ZoneTexteArea);

        //panel fichier // panel de droite
        panel1.setLayout(new BorderLayout());
        panel1.setBackground(Color.black);
        panel1.setBorder(BorderFactory.createLineBorder(Color.white, 1));

        //affichage de texte
        JList<String> ZoneTitre = new JList<>();

        //JScrollPane JSP = new JScrollPane(panel1);
        //panel1.setPreferredSize(new Dimension(200,0));
        ZoneTitre.setBackground(Color.lightGray);
        ZoneTitre.addListSelectionListener(e -> {
            titreVideo = ZoneTitre.getSelectedValue();
            path = titreVideo;
            ZoneAffichageExtension.setText(ExtensionGetTexte());//Readerwiwi("fan.mp4","Expected File Name Extension") // le mettre en premier sinon ca l'update pas pour les gif et les avi
            ZoneAffichageTitre.setText("Titre :   " + titreVideo); // il faut définir chacune des Zones avant la fonction car sinon on ne peut pas accéder a au Zones dans l'action
            //ZoneAffichageAuteur.setText(); //attend que l'api de william sois prête
            ZoneAffichageDateC.setText("Date de création :   "+ Readerwiwi(path,"Creation Time"));
            ZoneAffichageDuree.setText("Durée :     "+Readerwiwi(path,"Duration in Seconds"));

            HelloApplication.main(null,path); //on lance main de HelloApllication en recupérant le texte situer dans la Zone du Titre
            //System.out.println(path);
            HelloApplication.closeFrame(); // oblige de faire ca sinon il y a 2 fenêtres

        });

        panel1.add(ZoneTitre, BorderLayout.NORTH);
        contentpane.add(panel1);

        ZoneAffichageTitre.setForeground(Color.white);
        ZoneAffichageAuteur.setForeground(Color.white);
        ZoneAffichageDateC.setForeground(Color.white);
        ZoneAffichageDuree.setForeground(Color.white);
        ZoneAffichageExtension.setForeground(Color.white);
        JLabel ZoneAffichageTitre3 = new JLabel(ZoneDeTexte.getText());
        ZoneAffichageTitre3.setForeground(Color.white);
        ZoneAffichageAuteur.setMaximumSize(new Dimension(20,0)); // on peut définir une taille maximale

        //la scrollbar pour la fenetre de dossier
        JTree jtree = new JTree();
        JScrollPane JCB = new JScrollPane(jtree);
        JCB.setPreferredSize(new Dimension(150,0)); //pas besoin de mettre de height elle est definie automatiquement
        contentpane.add(JCB,BorderLayout.WEST);

        //fenetre info (en bas)
        JPanel panel2 = new JPanel(); // ma fenetre au sud pour les info
        panel2.setLayout(new GridLayout(4,1)); // ligne, colonne
        panel2.setBackground(Color.black);
        panel2.setForeground(Color.white);
        panel2.setBorder(BorderFactory.createLineBorder(Color.white, 1));
        contentpane.add(panel2,BorderLayout.SOUTH);
        panel2.add(ZoneAffichageTitre);
        panel2.add(ZoneAffichageAuteur);
        panel2.add(ZoneAffichageDateC);
        panel2.add(ZoneAffichageDuree);
        panel2.add(ZoneAffichageExtension);
        panel2.add(ZoneAffichageTitre3);
        panel2.setPreferredSize(new Dimension(0,50)); //pas besoin de mettre de valeur en x vue que elle est mise automatiquement 

        // ajout d'une frontière qui bouge entre le tree et la fenetre centrale
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT , JCB , panel1);
        contentpane.add(splitPane);

        //zone de texte recherche //on recupère en temps réel le texte
        ZoneDeTexte.setBackground(Color.black);
        ZoneDeTexte.setForeground(Color.white);


        ArrayList<String> extensionss = new ArrayList<>();
        final boolean[] vrai = {false};

        ZoneDeTexte.addKeyListener(new java.awt.event.KeyAdapter() {

            public void keyReleased(java.awt.event.KeyEvent e) {
                // jTextField.getText() contient le texte présent dans le JTextField après le relachement de la touche
                // e.getKeyChar() est le caractère correspondant à la touche relachée
                String texte = ZoneDeTexte.getText();
                //ZoneAffichageTitre3.setText(texte);
                //System.out.println(texte);

                // ici william, ou e parle tut seul

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
                        vrai[0] = false;
                        //System.out.println("avi");
                    }
                }
                else{
                    extensionss.remove(".avi");
                    vrai[0] = true;
                }

                if(ext1.isSelected()){
                    if(!extensionss.contains(".mp4")) {
                        extensionss.add(".mp4");
                        vrai[0] = false;
                        //System.out.println("mp4");
                    }
                }
                else{
                    vrai[0] = true;
                    extensionss.remove(".mp4");
                }
                if(!vrai[0]) {
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

    public static String ExtensionGetTexte(){
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



        //HelloApplication.main(null,null); //à l'ouverture de cette fenêtre on ferme la page de principal et quand on ferme cette page on r'ouvre la page principal
    }
}