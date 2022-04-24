package com.example.demo;

import java.awt.*;
import java.io.File;

public class OpenPageHelp {
    public static void main(String args[]){
        try
        {
            File file = new File("C:\\Users\\savad\\Documents\\cours\\1ere_cybersecurite\\Programmation_oriente_objet\\Projet\\WLC\\Java_player\\PageAide\\page d'accueil.html");
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