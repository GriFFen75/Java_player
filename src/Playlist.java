import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Playlist {

    static LinkedList liste = new LinkedList();
    //public static String titre;


    public static void ajout(String film) {
        liste.push(film);
        System.out.println("La liste dans playlist.ajout "+liste);
    }

    public static void retireIndexElement(int index) {
        liste.remove(index);
        System.out.println("La liste dans playlist.retireIndexElement "+liste);
    }

    public static void retireElementJoue() {
        liste.removeLast();
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