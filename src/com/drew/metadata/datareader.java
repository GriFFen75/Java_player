package com.drew.metadata;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class datareader {
    public static String Readerwiwi(String path,String data){ // detecte le reader de l'extension
        if (path == null){
            return path;
        }
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        File file = new File(s+"/video/"+path);
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(file);
            if(data.equals("Creation Time") || data.equals("Modification Time")) {
                String []temp = String.valueOf(print(metadata, "Using ImageMetadataReader", data)).split(" ");
                switch (temp[0]){
                    case "Mon":
                        temp[0] = "Lundi";
                        break;
                    case "Tue":
                        temp[0] = "Mardi";
                        break;
                    case "Wed":
                        temp[0] = "Mercredi";
                        break;
                    case "Thu":
                        temp[0] = "Jeudi";
                        break;
                    case "Fri":
                        temp[0] = "vendredi";
                        break;
                    case "Sat":
                        temp[0] = "Samedi";
                        break;
                    case "Sun":
                        temp[0] = "Dimanche";
                        break;
                }
                switch (temp[1]){
                    case "Jan":
                        temp[1] = "Janvier";
                        break;
                    case "Feb":
                        temp[1] = "Fevrier";
                        break;
                    case "Mar":
                        temp[1] = "Mars";
                        break;
                    case "Apr":
                        temp[1] = "Avril";
                        break;
                    case "May":
                        temp[1] = "Mai";
                        break;
                    case "Jun":
                        temp[1] = "Juin";
                        break;
                    case "Jul":
                        temp[1] = "Juillet";
                        break;
                    case "Aug":
                        temp[1] = "Aout";
                        break;
                    case "Sep":
                        temp[1] = "Septembre";
                        break;
                    case "Oct":
                        temp[1] = "Octobre";
                        break;
                    case "Nov":
                        temp[1] = "Novembre";
                        break;
                    case "Dec":
                        temp[1] = "Decembre";
                        break;
                }
                String end = temp[0]+" "+temp[2]+" "+temp[1]+" "+temp[5]+" à "+temp[3];
                return end;
            }
            if(data.equals("Duration in Seconds")){
                return print(metadata, "Using ImageMetadataReader", data)+" seconds";
            }
            return print(metadata, "Using ImageMetadataReader", data);
        } catch (ImageProcessingException e) {
            print(e);
        } catch (IOException e) {
            print(e);
        }
        return "pas de donnees";
    }
    /**
     * Write all extracted values to stdout.
     */
    private static String print(Metadata metadata, String method,String data){
        // A Metadata object contains multiple Directory objects
        for (Directory directory : metadata.getDirectories()) {

            //
            // Each Directory stores values in Tag objects
            //
            //System.out.println(directory);
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
            //System.out.println(directory.getTags());
            //System.out.println(directory.getTags().size());

            //
            // Each Directory may also contain error messages
            //
            /*
            for (String error : directory.getErrors()) {
                System.err.println("ERROR: " + error);
            }*/
        }
        return "pas de donnees";
    }
    private static void print(Exception exception) {
        System.err.println("EXCEPTION: " + exception);
    }

    public static void main(String[] args)  throws Exception{
        //System.out.println(Readerwiwi("fan.mp4","Creation Time"));
        //System.out.println(Readerwiwi("fan.mp4","Modification Time"));
        System.out.println(Readerwiwi("1365070268951.mp4","ALL"));
        //System.out.println(Readerwiwi("fan.mp4","File Name"));

    }
}