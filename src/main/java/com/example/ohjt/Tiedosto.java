package com.example.ohjt;

import java.io.*;
import java.util.Scanner;

public class Tiedosto extends Kayttoliittyma {

    public OlioLuokka olioLuokka = new OlioLuokka();

    public void tallennaTiedostoon (){
        try {
            File tiedosto = new File("tiedosto.txt");
            if (!tiedosto.exists()) {
                tiedosto.createNewFile();
                System.out.println("Ei tiedostoa)");
            }
            FileWriter kTiedosto = new FileWriter("tiedosto.txt", true);
            kTiedosto.write(olioLuokka.getHenkilokuntaID());
            kTiedosto.write("Moi");

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