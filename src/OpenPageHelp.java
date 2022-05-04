import java.awt.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class OpenPageHelp {
    public OpenPageHelp(){
        try
        {
            Path currentRelativePath = Paths.get("");
            String s = currentRelativePath.toAbsolutePath().toString();
            File file = new File(s+"/PageAide/page d'accueil.html");
            if(!Desktop.isDesktopSupported())
            {
                System.out.println("not supported");
                return;
            }
            Desktop desktop = Desktop.getDesktop();
            if(file.exists())
                desktop.open(file);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}