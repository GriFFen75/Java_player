import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.filechooser.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SelectFiles
{
    public static String GetNom(){
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        String[] ChaineS = s.split("\\\\");
        if (ChaineS[0] != "C:"){
            return ChaineS[1];
        }
        return ChaineS[2];
    }
    public static File openfichier(){
        JFileChooser choose = new JFileChooser("C:/Users/"+ GetNom()); // on choisi le répertoire racine
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
        if(res == JFileChooser.APPROVE_OPTION)
        {
            if(choose.getSelectedFile().isFile())
            {
                // System.out.println("Vous avez selectionne le repertoire: "+ choose.getSelectedFile());
                return choose.getSelectedFile();

            }
        }
        return null;
    }
    public static File opendossier(){
        JFileChooser choose = new JFileChooser("C:/Users/"+ GetNom()); // on choisi le répertoire racine
        FileSystemView
                .getFileSystemView()
                .getHomeDirectory()
        ;

////////////////////////////extensions//////////////////////////////////////

        choose.setDialogTitle("BVW / ouverture dossier");
        //choose.setAcceptAllFileFilterUsed(false);
        //FileNameExtensionFilter filter = new FileNameExtensionFilter("mp4 ; mp3 ; avi", "mp4" , "mp3", "avi");
        //choose.addChoosableFileFilter(filter);

/////////////////////////////extensions/////////////////////////////////////

        choose.setDialogTitle("BVW / ouverture dossier");
        choose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // on choisi ce qu'on veut avoir (DIRECTORYONLY si on veut que les répertoires)
        int res = choose.showSaveDialog(null);
        if(res == JFileChooser.APPROVE_OPTION)
        {
            if(choose.getSelectedFile().isDirectory())
            {
                // System.out.println("Vous avez selectionne le repertoire: "+ choose.getSelectedFile());
                return choose.getSelectedFile();

            }
        }
        return null;
    }


    public static void main(String[] args)
    {
        //openfichier();
        //System.out.println(openfichier());
    }
} 