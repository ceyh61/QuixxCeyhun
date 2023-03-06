package src.Quixx.Data;
import src.Quixx.Logik.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
public class Data_Spieler {
    private static final String pfad = "C:\\Users\\x61ce\\IdeaProjects\\QuixxCeyhun\\Data\\Spieler";
    public static void write(Spieler player)
    {
        String pfadName = pfad+"\\"+player.getName()+".txt";
        try {
            FileOutputStream f = new FileOutputStream(new File(pfadName));
            ObjectOutputStream o = new ObjectOutputStream(f);

            //Write objects to file
            o.writeObject(player);

            o.close();
            f.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        }
    }


    public static Spieler read(String spielername)
    {
        String pfadName = pfad+"\\"+spielername+".txt";
        try {
            System.out.println(pfadName);
            FileInputStream fi = new FileInputStream(new File(pfadName));
            ObjectInputStream oi = new ObjectInputStream(fi);

            // Read objects
            Spieler spieler = (Spieler) oi.readObject();
            oi.close();
            fi.close();

            return spieler;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Spieler> getSpieler()
    {
        ArrayList<Spieler> spielerlist = new ArrayList<Spieler>();
        try {
            File folder = new File(pfad);
            for (File file : folder.listFiles()) {
                FileInputStream fiSpie = new FileInputStream(new File(pfad + "\\" + file.getName()));
                ObjectInputStream oiSpie = new ObjectInputStream(fiSpie);

                //Search the User
                spielerlist.add((Spieler) oiSpie.readObject());
                oiSpie.close();
                fiSpie.close();


                System.out.println(spielerlist.get(spielerlist.size()-1).getName());


            }
            return spielerlist;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return spielerlist;
    }

    public static void deleteFile(String spielername)
    {
        String pfadName = pfad+"\\"+spielername+".txt";
        try {
            File myObj = new File(pfadName);
            myObj.delete();
        } catch (Exception e) {
            System.out.println("File not found");
        }
    }
}