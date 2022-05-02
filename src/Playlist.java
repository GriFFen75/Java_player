import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

public class Playlist {

    public static JList<String> ListePourAffichage;
    static LinkedList liste = new LinkedList();
    public static String titre;


    public static void ajout(String film) {
        liste.push(film);
    }

    public static void retireIndexElement(int index) {
        liste.remove(index);
    }

    public static void retireElementJoue() {
        liste.removeLast();
    }


    public static JList<String> affichage() {
        ListePourAffichage = new JList<>();
        ListePourAffichage.setBackground(Color.gray);
        String[] splitList = liste.toString().split(",");
        //System.out.println(splitList[1]);

        for (int i = 0; i < splitList.length; i++) {
            String[] splitListSplit = splitList[i].split("\\\\");
            //ListePourAffichage.setListData(recherche.barre_recherche(splitListSplit[splitListSplit.length-1],(ArrayList) recherche.fichiers));
            titre = splitListSplit[splitListSplit.length - 1];
            System.out.println("Playlist.affichage le titre : " + splitListSplit[splitListSplit.length - 1]);
            ListePourAffichage.setListData(recherche.barre_recherche(titre, (ArrayList) recherche.fichiers));
            System.out.println("Playlist.affichage : ListePourAffichage : " + ListePourAffichage);

        }
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