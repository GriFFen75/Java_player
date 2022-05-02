import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
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
        System.out.println(splitList[1]);

        for (int i = 0;i< splitList.length;i++){
            String[] splitListSplit = splitList[i].split("\\\\");
            //ListePourAffichage.setListData(recherche.barre_recherche(splitListSplit[splitListSplit.length-1],(ArrayList) recherche.fichiers));
            String titre = splitListSplit[splitListSplit.length-1];
            //System.out.println("Playlist.affichage le titre : "+splitListSplit[splitListSplit.length-1]);
            ListePourAffichage.setListData(recherche.barre_recherche(titre, (ArrayList) recherche.fichiers));

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
