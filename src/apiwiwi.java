import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.drew.metadata.datareader.ReaderTitle;

public class apiwiwi {

    public static String Searchwiwi(String film, String data) throws IOException {
        //https://api.themoviedb.org/3/movie/550?api_key=f3d7678cf05f1b8ce1d237f960b98786
        //https://api.themoviedb.org/3/search/movie?api_key=f3d7678cf05f1b8ce1d237f960b98786?language=fr&query=the+avengers
        //https://www.themoviedb.org/search?language=fr&query=harry
        //1d66ea43
        //http://img.omdbapi.com/?apikey=1d66ea43
        //http://www.omdbapi.com/?apikey=1d66ea43&t=orphan+black&plot=full

        if(film.contains(" ")){
            film.replace(" ","+");
        }
        URL url = new URL("http://www.omdbapi.com/?apikey=1d66ea43&language=fr&plot=short&t="+film);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setDoOutput(true);
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");

        BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));

        String output;
        List<String> apifilm = new ArrayList<>();
        while ((output = br.readLine()) != null) {
            //System.out.println(output);
            int a = -1;
            String temp = "";
            //System.out.println(output);

            for(int i = 0 ; i < output.length();i++){

                if(output.charAt(i)=='"'){
                    a++;
                }
                if(a==4) {
                    //System.out.println();
                    apifilm.add(temp);
                    temp = "";
                    a=0;
                }
                if(output.charAt(i)=='['){
                    a=-5;
                }
                if(output.charAt(i)!=(',') & output.charAt(i)!='[' & output.charAt(i)!=']' & output.charAt(i)!='{' & output.charAt(i)!='}')
                    //System.out.print(output.charAt(i));
                    temp += output.charAt(i);
            }
            for (Object o : apifilm) {
                if(String.valueOf(o).contains(data)){
                    String []temp2 = String.valueOf(o).split(":");
                    return temp2[1].replaceAll("\"","");
                }
            }
            return "mal ecrit? nop pour l'isntant ca marche pas, parce que dans la video il n'y pas de titre OK! sinon NTRRRRR";
        }

        return "mal ecrit? nop pour l'isntant ca marche pas, parce que dans la video il n'y pas de titre OK!";
    }


    public static void main(String [] args) throws IOException, TikaException, SAXException {
        System.out.println(ReaderTitle("laponie.mp4"));
        System.out.println(Searchwiwi(ReaderTitle("laponie.mp4"),"Plot"));
    }
}