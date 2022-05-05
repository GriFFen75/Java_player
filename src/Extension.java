import com.drew.metadata.datareader;
import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class Extension {

    public static String[] Exten(String ext) {
        //recherche.liste_fichier();
        //System.out.println(recherche.fichiers);
        String longueur = "";
        ArrayList<String> retourne = new ArrayList<String>();

        //for (String s : ext) {
            for (int i = 0; i < recherche.fichiers.size(); i++) {
                longueur += recherche.fichiers.get(i).charAt(recherche.fichiers.get(i).length() - 4);
                longueur += recherche.fichiers.get(i).charAt(recherche.fichiers.get(i).length() - 3);
                longueur += recherche.fichiers.get(i).charAt(recherche.fichiers.get(i).length() - 2);
                longueur += recherche.fichiers.get(i).charAt(recherche.fichiers.get(i).length() - 1);

                if (ext.equals(longueur)) {
                    retourne.add(recherche.fichiers.get(i));
                    //System.out.println(recherche.fichiers.get(i));
                }
                longueur = "";
            }
        //}
        //System.out.println(retourne);

        //return retourne;
        return new String[0];
    }

    public static void autre() throws IOException, TikaException, SAXException, UnsupportedLookAndFeelException {
        recherche.liste_fichier();
        System.out.println(recherche.fichiers);
        for(int i = 0; i < recherche.fichiers.size();i++){
            System.out.println(recherche.fichiers.get(i));
            System.out.println(apiwiwi.Searchwiwi(datareader.ReaderTitle(recherche.fichiers.get(i)),"Title"));
            //System.out.println(apiwiwi.Searchwiwi(datareader.Readerwiwi(recherche.fichiers.get(i),"Released")));

        }
    }

//    public static void main(String [] args) throws IOException, TikaException, SAXException {
//        ArrayList<String> ext = new ArrayList<String>();
//        ext.add(".mp3");
//        ext.add(".mp4");
//        //System.out.println(ext);
//        autre();
//    }
}
