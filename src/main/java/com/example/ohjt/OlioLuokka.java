package com.example.ohjt;

import java.sql.Date;

public class OlioLuokka {
    private int henkilokuntaID;
    private String henkilokuntaGmail;
    private int henkilokuntaPuh;
    private int varausID;
    //private String asiakaTiedot;
    private Date varauksenAlkuPaiva;
    private Date varauksenLoppuPaiva;
    private String asiakasNimi;
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
    public int getVarausID() {
        return varausID;
    }
    public void setVarausID(int varausID) {
        this.varausID = varausID;
    }
    public Date getVarauksenAlkuPaiva() {
        return varauksenAlkuPaiva;
    }
    public void setVarauksenAlkuPaiva(Date varauksenAlkuPaiva) {
        this.varauksenAlkuPaiva = varauksenAlkuPaiva;
    }
    public Date getVarauksenLoppuPaiva() {
        return varauksenLoppuPaiva;
    }
    public void setVarauksenLoppuPaiva(Date varauksenLoppuPaiva) {
        this.varauksenLoppuPaiva = varauksenLoppuPaiva;
    }
}
