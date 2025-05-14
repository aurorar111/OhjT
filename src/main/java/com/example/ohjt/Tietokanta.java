package com.example.ohjt;

import java.sql.*;

public class Tietokanta {

    //metodi yhteyden luontiin
    public static Connection connect() {
        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ot", // tietokannan nimi
                    "root",                           // käyttäjänimi
                    "salasana123"                     // salasana
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //metodi asiakkaan tietojen lisäykseen
    public static void lisaaVarausTietokantaan(String asiakasNimi, String asiakasGmail, String asiakasPuh, String asiakasSyntyma, String asiakasID) {
        String sql = "INSERT INTO asiakas(nimi, sähköposti, puhelinnumero, syntymäaika, asiakas_id) VALUES (?,?,?,?,?)";

        try (Connection conn = connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, asiakasNimi);
            statement.setString(2, asiakasGmail);
            statement.setString(3, asiakasPuh);
            statement.setString(4, asiakasSyntyma);
            statement.setString(5, asiakasID);
            statement.executeUpdate();
            System.out.println("Varaus tallennettu tietokantaan");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*try{
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/ot", // tietokannan nimi
                "root", // käyttäjänimi
                "salasana123" // salasana (lotan)
        );
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM henkilökunta");
        while(resultSet.next()){
            System.out.println("henkilökunta ID: " + resultSet.getString("henkilökunta_id"));
            System.out.println("hlökunta sähköposti: " + resultSet.getString("sähköposti"));
            System.out.println("hlökunta puhnro: " + resultSet.getString("puhelinnumero") + "\n");
        }
    }catch(SQLException e){
        e.printStackTrace();
    }*/

}
