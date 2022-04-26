import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.*;

public class apiwiwi {
    public static void main(String [] args) throws IOException {
        //https://api.themoviedb.org/3/movie/550?api_key=f3d7678cf05f1b8ce1d237f960b98786
        //https://api.themoviedb.org/3/search/movie?api_key=f3d7678cf05f1b8ce1d237f960b98786?language=fr&query=the+avengers
        //https://www.themoviedb.org/search?language=fr&query=harry
        //1d66ea43
        //http://img.omdbapi.com/?apikey=1d66ea43
        //http://www.omdbapi.com/?apikey=1d66ea43&t=orphan+black&plot=full
        URL url = new URL("http://www.omdbapi.com/?t=orphan+black&apikey=1d66ea43&plot=short");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setDoOutput(true);
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");

        BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));

        String output;
        System.out.println("Output from Server .... \n");
        while ((output = br.readLine()) != null) {
            //System.out.println(output);

            for(int i = 0 ; i < output.length();i++){
                if(output.charAt(i)==','){
                    System.out.println();
                }
                else {
                    System.out.print(output.charAt(i));
                }
            }
        }
    }
}
