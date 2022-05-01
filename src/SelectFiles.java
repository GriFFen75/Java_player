import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.filechooser.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;


public class SelectFiles
{

    public static String getpath(){
        Path currentRelativePath = Paths.get("");
        return currentRelativePath.toAbsolutePath().toString();
    }
    public static String GetNom(){
        String s = getpath();
        String[] ChaineS = s.split("/");
        System.out.println(ChaineS[2]);
        return ChaineS[2];
    }
    public static File openfichier(){
        System.out.println(getpath()+"/video");
        JFileChooser choose = new JFileChooser(getpath()+"/video"); // on choisi le répertoire racine
        FileSystemView
                .getFileSystemView()
                .getHomeDirectory()
        ;

////////////////////////////extensions//////////////////////////////////////

        choose.setDialogTitle("BVW / ouverture fichier");
        choose.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("mp4 ; mp3 ; avi", "mp4" , "mp3", "avi");
        choose.addChoosableFileFilter(filter);

/////////////////////////////extensions/////////////////////////////////////

        choose.setDialogTitle("BVW / ouverture fichier");
        choose.setFileSelectionMode(JFileChooser.FILES_ONLY); // on choisi ce qu'on veut avoir (DIRECTORYONLY si on veut que les répertoires)
        int res = choose.showSaveDialog(null);
        if(res == JFileChooser.APPROVE_OPTION) {
            if(choose.getSelectedFile().isFile()) {
                //System.out.println("Vous avez selectionne le repertoire: "+ choose.getSelectedFile());
                return choose.getSelectedFile();

            }
        }
        return null;
    }
    public static File opendossier(){
        System.out.println(getpath()+"/video");
        JFileChooser choose = new JFileChooser(getpath()+"/video"); // on choisi le répertoire racine
        FileSystemView
                .getFileSystemView()
                .getHomeDirectory();

////////////////////////////extensions//////////////////////////////////////

        choose.setDialogTitle("BVW / ouverture dossier");
        //choose.setAcceptAllFileFilterUsed(false);
        //FileNameExtensionFilter filter = new FileNameExtensionFilter("mp4 ; mp3 ; avi", "mp4" , "mp3", "avi");
        //choose.addChoosableFileFilter(filter);

/////////////////////////////extensions/////////////////////////////////////

        choose.setDialogTitle("BVW / ouverture dossier");
        choose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // on choisi ce qu'on veut avoir (DIRECTORYONLY si on veut que les répertoires)
        int res = choose.showSaveDialog(null);
        if(res == JFileChooser.APPROVE_OPTION) {
            if(choose.getSelectedFile().isDirectory()) {
                // System.out.println("Vous avez selectionne le repertoire: "+ choose.getSelectedFile());
                return choose.getSelectedFile();
            }
        }
        return null;
    }

} 