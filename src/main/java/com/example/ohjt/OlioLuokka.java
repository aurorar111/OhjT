package com.example.ohjt;

import java.sql.Date;
import java.time.LocalDate;

//Kayttoliittyma kayttoliittyma = new Kayttoliittyma();

public class OlioLuokka {

    public OlioLuokka(String asiakasNimi, String mokkiTaso, String varauksenAlkuPaiva) {
        this.asiakasNimi = asiakasNimi;
        this.mokkiTaso = mokkiTaso;
        this.varauksenAlkuPaiva = LocalDate.parse(varauksenAlkuPaiva);
    }

    public OlioLuokka (String asiakasNimi, int mokkiHinta, LocalDate maksunPaivamaara){
        this.asiakasNimi = asiakasNimi;
        this.mokkiHinta = mokkiHinta;
        this.maksunPaivamaara = maksunPaivamaara;
    }


    private int henkilokuntaID;
    private String henkilokuntaGmail;
    private int henkilokuntaPuh = 1234567890;
    /*private int varausID = 1;
    private int jarjestelmaID = 2;*/
    //private String asiakaTiedot;
    private LocalDate varauksenAlkuPaiva;
    private LocalDate varauksenLoppuPaiva;

    public String asiakasNimi;
    private String asiakasGmail;
    private int asiakasPuh;
    private int asiakasID;
    private int asiakasSyntymaAika;

    private double mokkiHinta;
    private boolean mokkiSaatavuus;
    private String mokkiTaso;
    private int mokkiID;
    private int laskuID;
    private boolean maksujenTila;
    private LocalDate maksunPaivamaara;

    public OlioLuokka() {

    }


    // Getterit ja setterit

    public int getHenkilokuntaID() {
        return henkilokuntaID;
    }

    public void setHenkilokuntaID(int henkilokuntaID) {
        this.henkilokuntaID = henkilokuntaID;
    }

    public String getHenkilokuntaGmail() {
        return henkilokuntaGmail;
    }

    public void setHenkilokuntaGmail(String henkilokuntaGmail) {
        this.henkilokuntaGmail = henkilokuntaGmail;
    }

    public int getHenkilokuntaPuh() {
        return henkilokuntaPuh;
    }

    public void setHenkilokuntaPuh(int henkilokuntaPuh) {
        this.henkilokuntaPuh = henkilokuntaPuh;
    }

    public LocalDate getVarauksenAlkuPaiva() {
        return varauksenAlkuPaiva;
    }

    public void setVarauksenAlkuPaiva(LocalDate varauksenAlkuPaiva) {
        this.varauksenAlkuPaiva = varauksenAlkuPaiva;
    }

    public LocalDate getVarauksenLoppuPaiva() {
        return varauksenLoppuPaiva;
    }

    public void setVarauksenLoppuPaiva(LocalDate varauksenLoppuPaiva) {
        this.varauksenLoppuPaiva = varauksenLoppuPaiva;
    }

    public String getAsiakasNimi() {
        return asiakasNimi;
    }

    public void setAsiakasNimi(String asiakasNimi) {
        this.asiakasNimi = asiakasNimi;
    }

    public String getAsiakasGmail() {
        return asiakasGmail;
    }

    public void setAsiakasGmail(String asiakasGmail) {
        this.asiakasGmail = asiakasGmail;
    }

    public int getAsiakasPuh() {
        return asiakasPuh;
    }

    public void setAsiakasPuh(int asiakasPuh) {
        this.asiakasPuh = asiakasPuh;
    }

    public int getAsiakasID() {
        return asiakasID;
    }

    public void setAsiakasID(int asiakasID) {
        this.asiakasID = asiakasID;
    }

    public int getAsiakasSyntymaAika() {
        return asiakasSyntymaAika;
    }

    public void setAsiakasSyntymaAika(int asiakasSyntymaAika) {
        this.asiakasSyntymaAika = asiakasSyntymaAika;
    }

    public double getMokkiHinta() {
        return mokkiHinta;
    }

    public void setMokkiHinta(double mokkiHinta) {
        this.mokkiHinta = mokkiHinta;
    }

    public boolean isMokkiSaatavuus() {
        return mokkiSaatavuus;
    }

    public void setMokkiSaatavuus(boolean mokkiSaatavuus) {
        this.mokkiSaatavuus = mokkiSaatavuus;
    }

    public String getMokkiTaso() {
        return mokkiTaso;
    }

    public void setMokkiTaso(String mokkiTaso) {
        this.mokkiTaso = mokkiTaso;
    }

    public int getMokkiID() {
        return mokkiID;
    }

    public void setMokkiID(int mokkiID) {
        this.mokkiID = mokkiID;
    }

    public int getLaskuID() {
        return laskuID;
    }

    public void setLaskuID(int laskuID) {
        this.laskuID = laskuID;
    }

    public boolean isMaksujenTila() {
        return maksujenTila;
    }

    public void setMaksujenTila(boolean maksujenTila) {
        this.maksujenTila = maksujenTila;
    }

    public LocalDate getMaksunPaivamaara() {
        return maksunPaivamaara;
    }

    public void setMaksunPaivamaara(LocalDate maksunPaivamaara) {
        this.maksunPaivamaara = maksunPaivamaara;
    }


}
