package com.drew.metadata;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class datareader {

    public static String Readerwiwi(String path,String data){ // detecte le reader de l'extension
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        File file = new File(s+"/video/"+path);
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(file);
            return String.valueOf(print(metadata, "Using ImageMetadataReader",data));
        } catch (ImageProcessingException e) {
            print(e);
        } catch (IOException e) {
            print(e);
        }
        return "pas de donnees";
    }

    public static void main(String[] args) {

        System.out.println(Readerwiwi("/1365070268951.mp4","Creation Time"));

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

            for (Tag tag : directory.getTags()) {
                if(String.valueOf(tag).contains(data)) {
                    return String.valueOf(tag);
                }

            }
            System.out.println(directory.getTags());
            //System.out.println(directory.getTags().size());

            //
            // Each Directory may also contain error messages
            //
            for (String error : directory.getErrors()) {
                System.err.println("ERROR: " + error);
            }
        }
        return "pas de donnees";
    }

    private static void print(Exception exception) {
        System.err.println("EXCEPTION: " + exception);
    }
}