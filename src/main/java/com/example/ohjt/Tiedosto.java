package com.example.ohjt;

import java.io.*;
import java.util.Scanner;
import java.sql.Date;

public class Tiedosto {

    public OlioLuokka olioLuokka = new OlioLuokka();

    public void tallennaTiedostoon (){
        try {
            System.out.println("Nykyinen hakemisto: " + new File(".").getAbsolutePath());
            File tiedosto = new File("tiedosto.txt");
            if (!tiedosto.exists()) {
                tiedosto.createNewFile();
                System.out.println("tiedot tallenettu");
            }
            FileWriter kTiedosto = new FileWriter("tiedosto.txt", true);
            kTiedosto.write("JUU TESTI XDXD");
            kTiedosto.write("MIKS ET TOIMI");
            /*kTiedosto.write(olioLuokka.getHenkilokuntaID());
            kTiedosto.write(olioLuokka.getHenkilokuntaPuh());
            kTiedosto.write(olioLuokka.getVarausID());*/
            //kTiedosto.write(olioLuokka.getVarauksenAlkuPaiva());

            /*kTiedosto.write(olioLuokka.getAsiakasNimi());
            kTiedosto.write(olioLuokka.getAsiakasGmail());
            kTiedosto.write(olioLuokka.getAsiakasPuh());
            kTiedosto.write(olioLuokka.getAsiakasSyntymaAika());
            kTiedosto.write(olioLuokka.getAsiakasID());*/

            kTiedosto.write("Moi");
            kTiedosto.write("NYT TESTI 123");

            kTiedosto.close();
        } catch (IOException e) {
            e.printStackTrace();
            
        }
    }


    public void lueTiedosto (){
        try {
            File LTiedosto = new File("tiedosto.txt");
            Scanner lukija = new Scanner(LTiedosto);
            while (lukija.hasNextLine()) {
                String tiedot  = lukija.nextLine();
            }lukija.close();
        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}