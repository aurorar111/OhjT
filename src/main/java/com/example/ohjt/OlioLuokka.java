package com.example.ohjt;

import java.time.LocalDate;

public class OlioLuokka {

    //Alustaja
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
    private String mokkiID = "15c";
    private int laskuID;
    private boolean maksujenTila;
    private LocalDate maksunPaivamaara;

    public OlioLuokka() {

    }
    // Getterit ja setterit
    public int getHenkilokuntaPuh() {
        return henkilokuntaPuh;
    }
    public void setVarauksenAlkuPaiva(LocalDate varauksenAlkuPaiva) {
        this.varauksenAlkuPaiva = varauksenAlkuPaiva;
    }
    public void setAsiakasNimi(String asiakasNimi) {
        this.asiakasNimi = asiakasNimi;
    }
    public void setMokkiHinta(double mokkiHinta) {
        this.mokkiHinta = mokkiHinta;
    }
    public void setMokkiTaso(String mokkiTaso) {
        this.mokkiTaso = mokkiTaso;
    }
}
