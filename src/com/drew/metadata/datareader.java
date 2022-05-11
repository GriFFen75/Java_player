package com.drew.metadata;


import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import org.apache.tika.exception.TikaException;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class datareader {
    public static String Readerwiwi(String path,String data){ // detecte le reader de l'extension
//        Path currentRelativePath = Paths.get("");
//        String s = currentRelativePath.toAbsolutePath().toString();
        File file = new File(path);
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(file);
            if(data.equals("Creation Time") || data.equals("Modification Time")) {
                String []temp = print(metadata, data).split(" ");

                if(temp[0].equals("")){
                    return "Pas de date";
                }
                switch (temp[0]) {
                    case "Mon" -> temp[0] = "Lundi";
                    case "Tue" -> temp[0] = "Mardi";
                    case "Wed" -> temp[0] = "Mercredi";
                    case "Thu" -> temp[0] = "Jeudi";
                    case "Fri" -> temp[0] = "vendredi";
                    case "Sat" -> temp[0] = "Samedi";
                    case "Sun" -> temp[0] = "Dimanche";
                }
                switch (temp[1]) {
                    case "Jan" -> temp[1] = "Janvier";
                    case "Feb" -> temp[1] = "Fevrier";
                    case "Mar" -> temp[1] = "Mars";
                    case "Apr" -> temp[1] = "Avril";
                    case "May" -> temp[1] = "Mai";
                    case "Jun" -> temp[1] = "Juin";
                    case "Jul" -> temp[1] = "Juillet";
                    case "Aug" -> temp[1] = "Aout";
                    case "Sep" -> temp[1] = "Septembre";
                    case "Oct" -> temp[1] = "Octobre";
                    case "Nov" -> temp[1] = "Novembre";
                    case "Dec" -> temp[1] = "Decembre";
                }
                if(temp.length >3) {
                    String dateeee = temp[0] + " " + temp[2] + " " + temp[1] + " " + temp[5] + " à " + temp[3];
                    return dateeee;
                }
                return temp[0] + " " + temp[1] +" " +temp[2];
            }
            if(data.equals("Duration in Seconds")){
                return print(metadata, data)+" seconds";
            }
            return print(metadata, data);
        } catch (ImageProcessingException | IOException e) {
            print(e);
        }
        return "pas de donnees";
    }
    /**
     * Write all extracted values to stdout.
     */
    private static String print(Metadata metadata, String data){
        // A Metadata object contains multiple Directory objects
        for (Directory directory : metadata.getDirectories()) {
            if(data.equals("ALL")) {
                for (Tag tag : directory.getTags()) {
                    System.out.println(tag);
                }
            }
            else {
                for (Tag tag : directory.getTags()) {
                    String [] tempo = String.valueOf(tag).split("-");
                    if (tempo[0].contains(data)) {
                        boolean vrai = false;
                        StringBuilder temp = new StringBuilder();
                        for(int jj = 0; jj < String.valueOf(tag).length();jj++){
                            if(String.valueOf(tag).charAt(jj) == '-' && !vrai){
                                vrai = true;
                                jj+=2;
                            }
                            if(vrai){
                                temp.append(String.valueOf(tag).charAt(jj));
                            }
                        }
                        return temp.toString();
                    }
                }
            }
        }
        return "pas de donnees";
    }
    private static void print(Exception exception) {
        System.err.println("EXCEPTION: " + exception);
    }

    public static String ReaderTitle(String path) throws IOException, TikaException, SAXException {
        Path currentRelativePath = Paths.get("");
//        String s = currentRelativePath.toAbsolutePath().toString();
//        File file = new File(s+"/video/"+path);
        Parser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler();
        org.apache.tika.metadata.Metadata metadata = new org.apache.tika.metadata.Metadata();
        FileInputStream inputstream = new FileInputStream(path);
        ParseContext context = new ParseContext();
        parser.parse(inputstream, handler, metadata, context);
        String[] metadataNames = metadata.names();

        for (String name : metadataNames) {
            if (name.contains("dc:title")) {
                return metadata.get(name);
            }
        }
        return "il n'y a pas de titre";
    }

//    public static void main(String[] args) throws TikaException, IOException, SAXException {
//        System.out.println("titre:"+ReaderTitle("laponie.mp4"));
//        System.out.println(Readerwiwi("laponie.mp4","Creation Time"));
//        //System.out.println(Readerwiwi("fan.mp4","File Name"));
//    }
}