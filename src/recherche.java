
//import com.tutorialspoint.media.demo;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class recherche {

    public static File file;

    public static List<String> fichiers = new ArrayList<>();

    public static void liste_fichier() throws UnsupportedLookAndFeelException {
//        Path currentRelativePath = Paths.get("");
//        String s = currentRelativePath.toAbsolutePath().toString();
        // creates a file object
        //File file = new File(s+"/video");
        File file = new File(String.valueOf(SelectFiles.openfichier()));
        // returns an array of all files
        //String[] fileList = file.list(); //c'est quoi ??
        //System.out.println(str.replace(".mp4","").replace(".avi",""));
        //assert fileList != null;
        //fichiers.addAll(Arrays.asList(fileList)); // c'est quoi ???
        String[] ChaineS = file.toString().split("\\\\");
        //System.out.println(ChaineS[ChaineS.length-1]);
        if (!Objects.equals(ChaineS[ChaineS.length - 1], "null")){
            //new VideoReader(file.toString());
            demo.video(file.toString());
        }
    }
    public static void liste_dossier(){
        //Path currentRelativePath = Paths.get("");
        //String s = currentRelativePath.toAbsolutePath().toString();
        // creates a file object
        //File file = new File(s+"/video");
        file = new File(String.valueOf(SelectFiles.opendossier()));
        System.out.println("code recheche.liste_dossier"+file);
        if (!Objects.equals(file.toString(), "null")){ //si on ferme la plage sans rien selectionner
            //System.out.println(file);
            // returns an array of all files
            String[] fileList = file.list();
            //System.out.println(str.replace(".mp4","").replace(".avi",""));
            assert fileList != null;
            fichiers.clear();
            fichiers.addAll(Arrays.asList(fileList));
        }
    }
    public static File pathDossier(){
        System.out.println("file de recherche.pathDossier : "+file);
        return file;
    }

    public String Extension(String titre){
        String []titreChaine = titre.split("\\."); // \\pour dire qu'on split bien avec un . sinon  ca fonctionne pas
        return titreChaine[titreChaine.length-1];
    }
    // regarder ce que ca renvoie prÃ©cisement

    public static String [] barre_recherche(String video, ArrayList ext){
        int compteur = 0;
        int compteur_liste = 0;

        for (Object o : ext) {
            for (Object fichier : fichiers) {
                if (String.valueOf(fichier).contains(video) && String.valueOf(fichier).contains((CharSequence) o)) {
                    compteur++;
                }
            }
        }

        String [] temp = new String [compteur];

        for (Object o : ext) {
            for (Object fichier : fichiers) {
                if (String.valueOf(fichier).contains(video) && String.valueOf(fichier).contains((CharSequence) o)) {
                    temp[compteur_liste] = String.valueOf(fichier);
                    compteur_liste++;
                }
            }
        }
        return temp;
    }


    public String[] barreRechercheExtension(String video){
        int compteur = 0;
        int compteur_liste = 0;

        for (Object fichier : fichiers) {
            if (String.valueOf(fichier).contains(video)) {
                compteur++;
            }
        }


        String [] temp = new String [compteur];

        for (Object fichier : fichiers) {
            if (String.valueOf(fichier).contains(video)) {
                temp[compteur_liste] = String.valueOf(fichier);
                compteur_liste++;
            }
        }

        return temp;
    }

//    static void affichage(String[] liste){
//        for (String s : liste) {
//            System.out.println(s);
//        }
//        //System.out.println(liste.length);
//        //System.out.println("stop");
//    }
}
