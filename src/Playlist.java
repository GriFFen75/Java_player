import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;


public class Playlist {

    public static LinkedList liste = new LinkedList();
    //public static String titre;


    public static void ajout(String film) {
        liste.push(film);
        System.out.println("La liste dans playlist.ajout "+liste);
        //retireElementJoue();
    }

    public static void retireIndexElement(int index) {
        liste.remove(index);
        System.out.println("La liste dans playlist.retireIndexElement "+liste);
    }

    public static void retireElementJoue() {
        //if (liste.size() > 10){
        if (liste.size() > 0) {
            System.out.println("un element en moins ");
            liste.removeFirst();
        }
        //}
    }
//    public void nextVideo() throws UnsupportedLookAndFeelException {
//        while (demo.statueBouton=="true"){
//            System.out.println("bouton pour next actionner dans Playlist.nextvideo : ");
//            //demo.dispose();
//            for (int i=0;i<=liste.size();i++) {
//                demo.video((String) liste.get(i));
//            }
//        }
//    }





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