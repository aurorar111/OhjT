package com.example.ohjt;

import java.io.*;
import java.util.Scanner;
import java.sql.Date;

public class Tiedosto {

    public OlioLuokka olioLuokka = new OlioLuokka();

    //Tallennetaan tiedostoon
    public void tallennaTiedostoon () {
        try {
            System.out.println("Nykyinen hakemisto: " + new File(".").getAbsolutePath());
            File tiedosto = new File("tiedosto.txt");
            if (!tiedosto.exists()) {
                tiedosto.createNewFile();
                System.out.println("tiedot tallenettu");
            }
            FileWriter kTiedosto = new FileWriter("tiedosto.txt", true);
            kTiedosto.write("\n\nUUSI ASIAKAS:\n");
            kTiedosto.write("hlökunta id: " + Kayttoliittyma.getHenkilokuntaID() + "\n");
            kTiedosto.write("hlökunta puh: " + olioLuokka.getHenkilokuntaPuh() + "\n");
            kTiedosto.write("varausID: " + Kayttoliittyma.getVarausID() + "\n");
            kTiedosto.write("alkupv: " + Kayttoliittyma.getVarauksenAlku().toString() + "\n");
            kTiedosto.write("loppu pv: " + Kayttoliittyma.getVarauksenLoppu().toString() + "\n");

            kTiedosto.write(Kayttoliittyma.getAsiakasNimi() + "\n");
            kTiedosto.write(Kayttoliittyma.getAsiakasGmail() + "\n");
            kTiedosto.write(Kayttoliittyma.getAsiakasPuhelinnumero() + "\n");
            kTiedosto.write(Kayttoliittyma.getAsiakasSynty().toString() + "\n");
            kTiedosto.write("asiakasID: " + Kayttoliittyma.getAsiakasID() + "\n");

            kTiedosto.write(Kayttoliittyma.getMokkiTaso() + "\n");
            kTiedosto.write(Kayttoliittyma.getVarauksenHinta() + "\n");
            kTiedosto.write("laskuID: " + Kayttoliittyma.getLaskuID() + "\n");

            kTiedosto.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Lue tiedostoo
    public String lueTiedosto (){
        StringBuilder tiedot= new StringBuilder();
        try {
            File LTiedosto = new File("tiedosto.txt");
            Scanner lukija = new Scanner(LTiedosto);
            while (lukija.hasNextLine()) {
                String rivi = lukija.nextLine();
                tiedot.append(rivi).append("\n");
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