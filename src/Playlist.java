import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Playlist {

    public static JList<String> ListePourAffichage;
    static LinkedList liste = new LinkedList();
    public static String titre;


    public static void ajout(String film) {
        liste.push(film);
        System.out.println("wiwiwiw "+liste);
    }

    public static void retireIndexElement(int index) {
        liste.remove(index);
    }

    public static void retireElementJoue() {
        liste.removeLast();
    }


    public static JList<String> affichage() {

        //String[] splitList = liste.toString().split(",");
        //System.out.println(splitList[1]);

        for (int i = 0; i < liste.size(); i++) {
            ListePourAffichage.setListData(recherche.barre_recherche(titre, (ArrayList) recherche.fichiers));
            System.out.println("Playlist.affichage : ListePourAffichage : " + liste);
            //JLabel label = new JLabel(String.valueOf(liste), JLabel.CENTER);
            //frame.add(label);

        }
        System.out.println("laaaaa"+ListePourAffichage);
//        System.out.println("Playlist.affichage : ListePourAffichage à l'index 1 : "+ListePourAffichage);
//        System.out.println("Playlist.affichage : ListePourAffichage en dehors de la boucle for : "+ListePourAffichage);
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