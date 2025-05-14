package com.example.ohjt;

import java.sql.Date;

//Kayttoliittyma kayttoliittyma = new Kayttoliittyma();

public class OlioLuokka {
    private int henkilokuntaID;
    private String henkilokuntaGmail;
    private int henkilokuntaPuh = 1234567890;
    private int varausID = 1;
    //private String asiakaTiedot;
    private Date varauksenAlkuPaiva;
    private Date varauksenLoppuPaiva;

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
    private int maksunPaivamaara;

    /*public int getHenkilokuntaID() {
        return henkilokuntaID;
    }*/
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
    public int getVarausID() {
        return varausID;
    }
    public void setVarausID(int varausID) {
        this.varausID = varausID;
    }
    /*public Date getVarauksenAlkuPaiva() {
        return varauksenAlkuPaiva;
    }*/
    public void setVarauksenAlkuPaiva(Date varauksenAlkuPaiva) {
        this.varauksenAlkuPaiva = varauksenAlkuPaiva;
    }
    public Date getVarauksenLoppuPaiva() {
        return varauksenLoppuPaiva;
    }
    public void setVarauksenLoppuPaiva(Date varauksenLoppuPaiva) {
        this.varauksenLoppuPaiva = varauksenLoppuPaiva;
    }

    public void setAsiakasNimi(String asiakasNimi) {
        this.asiakasNimi = asiakasNimi;
    }
    /*public String getAsiakasNimi() {
        return asiakasNimi;
    }*/
    public void setAsiakasGmail(String asiakasGmail) {
        this.asiakasGmail = asiakasGmail;
    }
    public String getAsiakasGmail() {
        return asiakasGmail;
    }
    public void setAsiakasPuh(int asiakasPuh) {
        this.asiakasPuh = asiakasPuh;
    }
    public int getAsiakasPuh() {
        return asiakasPuh;
    }
    public void setAsiakasID(int asiakasID) {
        this.asiakasID = asiakasID;
    }
    public int getAsiakasID() {
        return asiakasID;
    }
    public void setAsiakasSyntymaAika(int asiakasSyntymaAika) {
        this.asiakasSyntymaAika = asiakasSyntymaAika;
    }
    public int getAsiakasSyntymaAika() {
        return asiakasSyntymaAika;
    }
}
