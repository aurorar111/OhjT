package com.example.ohjt;

import java.io.*;
import java.util.Scanner;

public class Tiedosto extends Kayttoliittyma {

    public void tallennaTiedostoon (){
        try {
            File tiedosto = new File("tiedosto.txt");
            if (!tiedosto.exists()) {
                tiedosto.createNewFile();
            }
            FileWriter kTiedosto = new FileWriter("tiedosto.txt", true);
            kTiedosto.write(tfhenkilokuntaID.getText()
            kTiedosto.close();
            System.out.println("Tiedosto tallennettu");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void lueTiedosto (){
        try {
            File LTiedosto = new File("tiedosto.txt");
            Scanner lukija = new Scanner(LTiedosto);
            while (lukija.hasNextLine()) {
                String tiedot  = lukija.nextLine();
                lukija.close();
            }
        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}