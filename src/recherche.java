import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class recherche {

    public static List fichiers = new ArrayList();

    private static void liste_fichier(){
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        // creates a file object
        File file = new File(s+"/video");
        // returns an array of all files
        String[] fileList = file.list();
        for(String str : fileList) {
            //System.out.println(str.replace(".mp4","").replace(".avi",""));
            fichiers.add(str);
        }
    }

    public static String [] barre_recherche(String video){
        int compteur = 0;
        int compteur_liste = 0;

        for(int i = 0; i < fichiers.size(); i ++){
            if(String.valueOf(fichiers.get(i)).contains(video)){
                compteur ++;
            }
        }

        String [] temp = new String [compteur];

        for(int i = 0; i < fichiers.size(); i ++){
            if(String.valueOf(fichiers.get(i)).contains(video)){
                temp[compteur_liste] = String.valueOf(fichiers.get(i));
                compteur_liste ++;
            }
        }
        return temp;
    }

    private static void affichage(String [] liste){
        for(int i = 0 ; i < liste.length;i++){
            System.out.println(liste[i]);
        }
    }

    public static void main(String [] args){
        liste_fichier();
        System.out.println(fichiers);
        affichage(barre_recherche("1"));

    }

}
