import javax.swing.*;
import java.io.File;
import java.util.LinkedList;

public class Playlist {

    static LinkedList liste = new LinkedList();


    public static void ajout(String film){
        liste.push(film);
    }

    public static void retire(){
        liste.removeLast();
    }

    public static JList affichage(){
        JList ListePourAffichage = new JList();
        String[] splitList = liste.toString().split(",");
        for (int i = 0;i< splitList.length;i++){
            //ListePourAffichage.setListData(recherche.barre_recherche(splitList[i]));
        }
        return ListePourAffichage;
    }

//    public static void main(String [] args){
//        recherche.liste_fichier();
//
//        for(String a : recherche.fichiers){
//            //System.out.println(a);
//            ajout(a);
//        }
//
//        System.out.println(liste);
//        retire();
//        System.out.println(liste);
//    }
}
