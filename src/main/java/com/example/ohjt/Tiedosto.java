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
            kTiedosto.write("\n\nUUSI ASIAKAS:\n");
            kTiedosto.write(Kayttoliittyma.getHenkilokuntaID() + "\n");
            kTiedosto.write(olioLuokka.getHenkilokuntaPuh() + "\n");
            kTiedosto.write(Kayttoliittyma.getVarausID() + "\n");
            kTiedosto.write(Kayttoliittyma.getVarauksenAlku().toString() + "\n");
            kTiedosto.write(Kayttoliittyma.getVarauksenLoppu().toString() + "\n");

            kTiedosto.write(Kayttoliittyma.getAsiakasNimi() + "\n");
            kTiedosto.write(Kayttoliittyma.getAsiakasGmail() + "\n");
            kTiedosto.write(Kayttoliittyma.getAsiakasPuhelinnumero() + "\n");
            kTiedosto.write(Kayttoliittyma.getAsiakasSynty().toString() + "\n");
            kTiedosto.write(Kayttoliittyma.getAsiakasID() + "\n");

            kTiedosto.write(Kayttoliittyma.getVarauksenHinta() + "\n");
            kTiedosto.write(Kayttoliittyma.getLaskuID() + "\n");
            //kTiedosto.write(Kayttoliittyma.getLaskunErapaiva() + "\n");
            //kTiedosto.write(Kayttoliittyma.getMaksunTila() + "\n");

            kTiedosto.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String lueTiedosto (){
        StringBuilder tiedot= new StringBuilder();
        try {
            File LTiedosto = new File("tiedosto.txt");
            Scanner lukija = new Scanner(LTiedosto);
            while (lukija.hasNextLine()) {
                String rivi = lukija.nextLine();
                tiedot.append(rivi);
            }lukija.close();
        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }return tiedot.toString();
    }
    public static void main(String[] args) {
        Tiedosto tiedosto = new Tiedosto();
        tiedosto.tallennaTiedostoon();
        System.out.println(tiedosto.lueTiedosto());
    }
}