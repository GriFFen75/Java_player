import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class recherche {

    public static List<String> fichiers = new ArrayList<>();

    static void liste_fichier(){
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        // creates a file object
        File file = new File(s+"/video");
        // returns an array of all files
        String[] fileList = file.list();
        //System.out.println(str.replace(".mp4","").replace(".avi",""));
        assert fileList != null;
        fichiers.addAll(Arrays.asList(fileList));
    }

    public static String [] barre_recherche(String video){
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

    static void affichage(String[] liste){
        for (String s : liste) {
            System.out.println(s);
        }
    }
}
