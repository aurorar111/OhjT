package com.example.ohjt;

import java.sql.*;

public class Tietokanta {

    //metodi yhteyden luontiin
    public static Connection connect() {
        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ot",
                    "root",
                    "salasana123"
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //metodi asiakkaan tietojen lisäykseen
    public static void lisaaVarausTietokantaan(String asiakasNimi, String asiakasGmail, String asiakasPuh, String asiakasSyntyma, String asiakasID) {
        String sql = "INSERT INTO asiakas(nimi, sähköposti, puhelinnumero, syntymäaika, asiakas_id) VALUES (?,?,?,?,?)";
        String sql2 = "INSERT INTO varaus(asiakas_id, varaus_alku, varaus_loppu, varaus_id, järjestelmä_id, mökki_id, lasku_id) VALUES (?,?,?,?,?,?,?)";
        String sql3 = "INSERT INTO mökit (hinta, saatavuus, mökkitaso, mökki_id, järjestelmä_id) VALUES (?,?,?,?,?)";
        try (Connection conn = connect();
             PreparedStatement statement1 = conn.prepareStatement(sql)) {
            statement1.setString(1, asiakasNimi);
            statement1.setString(2, asiakasGmail);
            statement1.setString(3, asiakasPuh);
            statement1.setString(4, asiakasSyntyma);
            statement1.setString(5, asiakasID);

            PreparedStatement statement2 = conn.prepareStatement(sql2);
            statement2.setString(1, asiakasID);
            statement2.setString(2, Kayttoliittyma.getVarauksenAlku().toString());
            statement2.setString(3, Kayttoliittyma.getVarauksenLoppu().toString());
            statement2.setString(4, String.valueOf(Kayttoliittyma.getVarausID()));
            statement2.setString(5, String.valueOf(Kayttoliittyma.getJarjestelmaID()));
            statement2.setString(6, String.valueOf(Kayttoliittyma.getMokkiID()));
            statement2.setString(7, String.valueOf(Kayttoliittyma.getLaskuID()));

            PreparedStatement statement3 = conn.prepareStatement(sql3);
            statement3.setString(1, Kayttoliittyma.getVarauksenHinta());
            statement3.setString(2, String.valueOf(Kayttoliittyma.getSaatavuus()));
            statement3.setString(3, Kayttoliittyma.getMokkiTaso());
            statement3.setString(4, String.valueOf(Kayttoliittyma.getMokkiID()));
            statement3.setString(5, String.valueOf(Kayttoliittyma.getJarjestelmaID()));

            statement1.executeUpdate();
            statement3.executeUpdate();
            statement2.executeUpdate();
            System.out.println("Varaus tallennettu tietokantaan");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
