package com.example.ohjt;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.collections.ObservableList;
import static javafx.collections.FXCollections.observableArrayList;

import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Kayttoliittyma extends Application {

    /*public Kayttoliittyma() {
        // tyhjä konstruktori FXML:ää varten
    }*/
    Tiedosto tiedostoLuokka = new Tiedosto();

//TextField, Combobox, DatePicker, Label, Button.....
    private static int varausID;
    private static int jarjestelmaID = 182;
    private static String mokkiID = "15c";

    public TextField henkilokuntaG = new TextField("");

    public static TextField tfhenkilokuntaID = new TextField();
    public Button button = new Button("Kirjaudu");
    public static TextField tfAsiakkaanimi = new TextField();
    public static TextField tfAsiakasGmail = new TextField();
    public static TextField tfAsiakasPuh = new TextField();
    public static DatePicker tfAsiakasSynty = new DatePicker();
    public static TextField tfAsiakasID = new TextField();
    public TextField varauksenAlku = new TextField();
    public TextField varauksenLoppu = new TextField();
    public static TextField laskuID = new TextField();
    public static TextField laskuPva = new TextField();
    public static TextField maksuntila = new TextField();
    public TextField saatavuus = new TextField();
    public static TextField hinta = new TextField();

    public static DatePicker alkuDate = new DatePicker();
    public static DatePicker loppuDate = new DatePicker();
    public static Label asiakasVaroitus = new Label();

    public static ComboBox<String> cbMokkitaso = new ComboBox<>();

    public Button Haebutton = new Button("Hae");
    //PÄIVITYS NAPPI:
    private Button paivita = new Button("Tallenna varaus");

    public TableView taulukko = new TableView<>();
    public TableView taulukkoMaksut = new TableView<>();
    private ObservableList <OlioLuokka> asiakasTiedot = observableArrayList();
    private ObservableList <OlioLuokka> maksutiedot = observableArrayList();

    // getterit
    public static int getHenkilokuntaID() {
        return Integer.parseInt(tfhenkilokuntaID.getText());
    }
    public static LocalDate getVarauksenAlku() {
        return alkuDate.getValue();
    }
    public static LocalDate getVarauksenLoppu() {
        return loppuDate.getValue();
    }
    public static String getAsiakasNimi() {
        return tfAsiakkaanimi.getText();
    }
    public static String getAsiakasGmail() {
        return tfAsiakasGmail.getText();
    }
    public static String getAsiakasPuhelinnumero() {
        return tfAsiakasPuh.getText();
    }
    public static LocalDate getAsiakasSynty() {
        return tfAsiakasSynty.getValue();
    }
    public static String getAsiakasID() {
        return tfAsiakasID.getText();
    }
    public static void setVarausID() {
        varausID = ThreadLocalRandom.current().nextInt(1, 100);
    }
    public static int getVarausID() {
        setVarausID();
        return varausID;
    }
    public static int getJarjestelmaID() {
        return jarjestelmaID;
    }
    public static String getVarauksenHinta() {
        return hinta.getText();
    }
    public static String getLaskuID() {
        return laskuID.getText();
    }
    public static String getLaskunErapaiva() {
        return laskuPva.getText();
    }
    public static String getMaksunTila() {
        return maksuntila.getText();
    }
    public static String getMokkiTaso() {
        return cbMokkitaso.getValue();
    }
    public static String getMokkiID() {
        return mokkiID;
    }


    @Override
    public void start(Stage alkuikkuna) {

        //Pohja
        Pane pohja = new Pane();
        Scene kehys = new Scene(pohja, 1000, 900);
        alkuikkuna.setTitle("The Cozy Spot – henkilökunnan varausjärjestelmä");
        alkuikkuna.setScene(kehys);
        pohja.setStyle("-fx-background-color:#faf5f1;");
        button.setStyle("-fx-background-color:#ef6a9d;");

        alkuikkuna.show();

        // pane johon lisätään ja asetetaan elementit oikeille paikoille
        GridPane pane = new GridPane();
        pane.setHgap(5);
        pane.setVgap(5);
        pane.setPadding(new javafx.geometry.Insets(20));

        //Vasen
        pane.add(new Label("Henkilökunta ID:"), 0, 0);
        pane.add(tfhenkilokuntaID, 1, 0);
        tfhenkilokuntaID.setPromptText("syötä ID");
        Label varoitus = new Label("Kirjoita 4 merkkinen ID");
        varoitus.setTextFill(Color.RED);
        pane.add(varoitus, 2, 0);
        pane.add(button, 0, 1);
        pane.add(new Label("Varauksen alkupäivämäärä:"), 0, 3);
        pane.add(alkuDate, 1, 3);
        pane.add(new Label("Varauksen loppupäivämäärä:"), 0, 4);
        pane.add(loppuDate, 1, 4);
        pane.add(new Label("Asiakkaan nimi:"), 0, 5);
        pane.add(tfAsiakkaanimi, 1, 5);
        pane.add(new Label("Asiakkaan sähköposti:"), 0, 6);
        pane.add(tfAsiakasGmail, 1, 6);
        pane.add(new Label("Asiakkaan puhelinnumero:"), 0, 7);
        pane.add(tfAsiakasPuh, 1, 7);
        pane.add(new Label("Asiakkaan syntymäpäivä:"), 0, 8);
        pane.add(tfAsiakasSynty, 1, 8);
        pane.add(cbMokkitaso, 0, 9);
        pane.add(Haebutton, 1, 9);
        Haebutton.setPrefWidth(100);
        pane.add(paivita, 2, 12); //Paivita buttonnnn
        pane.add(new Label("Saatavuus:"), 0, 11);
        pane.add(saatavuus, 1, 11);
        pane.add(new Label("Generoitu asiakas ID:"), 0, 12);
        pane.add(tfAsiakasID, 1, 12);
        pane.add(asiakasVaroitus,0,13);
        asiakasVaroitus.setText("");
        asiakasVaroitus.setTextFill(Color.RED);

        //Oikea
        pane.add(new Label("Hinta:"), 5, 3);
        pane.add(hinta, 6, 3);
        pane.add(new Label("Laskun ID:"), 5, 4);
        pane.add(laskuID, 6, 4);
        pane.add(new Label("Laskun eräpäivä:"), 5, 5);
        pane.add(laskuPva, 6, 5);
        pane.add(new Label("Maksun tila:"), 5, 6);
        pane.add(maksuntila, 6, 6);

        pohja.getChildren().add(pane);


        //SET ON ACTION BUTTONIT
        // PÄIVITÄ NAPIN TIEDOSTOON TALLENNUS
        paivita.setOnAction(actionEvent -> {
            String nimi2 = tfAsiakkaanimi.getText();
            int mokinHinta = 0;
            LocalDate alku = alkuDate.getValue();
            if (nimi2 != null && !nimi2.isEmpty() && alku != null) {
                OlioLuokka uusiRivi2 = new OlioLuokka();
                uusiRivi2.setAsiakasNimi(nimi2);
                uusiRivi2.setMokkiHinta(mokinHinta);
                uusiRivi2.setVarauksenAlkuPaiva(alku);
                asiakasTiedot.add(uusiRivi2);
            }

            if (tfAsiakkaanimi.getText().isEmpty() ||
                    tfAsiakasGmail.getText().isEmpty() ||
                    tfAsiakasPuh.getText().isEmpty() ||
                    tfAsiakasSynty.getValue() == null ||
                    tfAsiakasID.getText().isEmpty()) {

                asiakasVaroitus.setText("Täytä kaikki asiakastiedot ennen tallennusta!");
                return;
            }
            tiedostoLuokka.tallennaTiedostoon(); //tallenna-nappia painaessa tallennaTiedostoon-metodia kutsutaan
            String nimi = tfAsiakkaanimi.getText();
            String sapo = tfAsiakasGmail.getText();
            String puh = tfAsiakasPuh.getText();
            String syntyma = tfAsiakasSynty.getValue().toString();
            String asiakasid = tfAsiakasID.getText();


            Tietokanta.lisaaVarausTietokantaan(nimi, sapo, puh, syntyma, asiakasid);
        });
        Haebutton.setOnAction(e-> {
            int satunnainenID = new Random().nextInt(30000)+1;
            tfAsiakasID.setText(String.valueOf(satunnainenID));
            tfAsiakasID.setEditable(false);

            int satunnainenLaskunID = new Random().nextInt(30000)+2000;
            laskuID.setText(String.valueOf(satunnainenLaskunID));
            laskuID.setEditable(false);
            laskuID.setText("L" + satunnainenLaskunID);

            Map<String, Integer> mökinhinta = new HashMap<>();
            mökinhinta.put("Perusmökki",70);
            mökinhinta.put("Paremman puoleinen",100);
            mökinhinta.put("Melkein kartano",140);
            mökinhinta.put("TOP tier",200);

            LocalDate alku = alkuDate.getValue();
            LocalDate loppu = loppuDate.getValue();

            if (alku != null && loppu != null && !loppu.isBefore(alku)) {
                long varatutYot = ChronoUnit.DAYS.between(alku, loppu);
                if (varatutYot == 0) varatutYot = 1;


                String valittuMökki = cbMokkitaso.getValue();
                int hintaYolta = mökinhinta.get(valittuMökki);
                long varauksenHinta = varatutYot * hintaYolta;
                hinta.setText(varauksenHinta + "€");
            }

            String nimi = tfAsiakkaanimi.getText();
            String kategoria = cbMokkitaso.getValue();
            LocalDate paiva = alkuDate.getValue();

            if (nimi!= null && !nimi.isEmpty()&& kategoria != null && paiva !=null){
                OlioLuokka uusiRivi = new OlioLuokka();
                uusiRivi.setAsiakasNimi(nimi);
                uusiRivi.setMokkiTaso(kategoria);
                uusiRivi.setVarauksenAlkuPaiva(paiva);
                asiakasTiedot.add(uusiRivi);
            }
        });

        cbMokkitaso.getItems().addAll("Perusmökki", "Paremman puoleinen", "Melkein kartano", "TOP tier");
        cbMokkitaso.setValue("Valitse");

        // ei voi muokata tietoja ennen henkilökunta ID
        alkuDate.setDisable(true);
        loppuDate.setDisable(true);
        tfAsiakkaanimi.setDisable(true);
        tfAsiakasGmail.setDisable(true);
        tfAsiakasPuh.setDisable(true);
        tfAsiakasSynty.setDisable(true);
        saatavuus.setDisable(true);
        tfAsiakasID.setDisable(true);
        hinta.setDisable(true);
        laskuID.setDisable(true);
        laskuPva.setDisable(true);
        maksuntila.setDisable(true);
        cbMokkitaso.setDisable(true);
        varoitus.setVisible(false);

        //Ei toimii jos on empty tai jos ei ole ainakin 4 merkkiä
        button.setOnAction(e -> {
            if(!tfhenkilokuntaID.getText().isEmpty()&& tfhenkilokuntaID.getText().length()==4) {
                alkuDate.setDisable(false);
                loppuDate.setDisable(false);
                tfAsiakkaanimi.setDisable(false);
                tfAsiakasGmail.setDisable(false);
                tfAsiakasPuh.setDisable(false);
                tfAsiakasSynty.setDisable(false);
                saatavuus.setDisable(false);
                tfAsiakasID.setDisable(false);
                hinta.setDisable(false);
                laskuID.setDisable(false);
                laskuPva.setDisable(false);
                maksuntila.setDisable(false);
                cbMokkitaso.setDisable(false);
                varoitus.setVisible(false);
            }else {
                alkuDate.setDisable(true);
                loppuDate.setDisable(true);
                tfAsiakkaanimi.setDisable(true);
                tfAsiakasGmail.setDisable(true);
                tfAsiakasPuh.setDisable(true);
                tfAsiakasSynty.setDisable(true);
                saatavuus.setDisable(true);
                tfAsiakasID.setDisable(true);
                hinta.setDisable(true);
                laskuID.setDisable(true);
                laskuPva.setDisable(true);
                maksuntila.setDisable(true);
                cbMokkitaso.setDisable(true);
                varoitus.setVisible(true);
            }
        });

        //TAULUKKO
        //Taulukko oikea
        TableColumn<OlioLuokka, String> asiakasColumn = new TableColumn<>("Asiakas");
        asiakasColumn.setCellValueFactory(new PropertyValueFactory<>("asiakasNimi" ));
        asiakasColumn.setPrefWidth(150);
        TableColumn<OlioLuokka, String> kategoriaColumn = new TableColumn<>("Mökin taso");
        kategoriaColumn.setCellValueFactory(new PropertyValueFactory<>("mokkiTaso"));
        kategoriaColumn.setPrefWidth(125);
        TableColumn<OlioLuokka, String> paivaColumn = new TableColumn<>("Alkupäivä");
        paivaColumn.setCellValueFactory(new PropertyValueFactory<>("varauksenAlkuPaiva"));
        paivaColumn.setPrefWidth(100);
        taulukko.getColumns().addAll(asiakasColumn, kategoriaColumn, paivaColumn);
        taulukko.setItems(asiakasTiedot);
        pane.add(taulukko, 0,20,2,1);

        //Taulukko vasen
        TableColumn<OlioLuokka, String> nimiColumn= new TableColumn<>("Asiakas");
        nimiColumn.setCellValueFactory(new PropertyValueFactory<>("asiakasNimi "));
        TableColumn<OlioLuokka, String> laskuColumn= new TableColumn<>("Summa €");
        laskuColumn.setCellValueFactory(new PropertyValueFactory<>("mokinHinta "));
        TableColumn<OlioLuokka, String> erapaivaColumn = new TableColumn<>("Eräpäivä");
        erapaivaColumn.setCellValueFactory(new PropertyValueFactory<>("MaksunPaivamaara"));
        taulukkoMaksut.getColumns().addAll(nimiColumn,laskuColumn, erapaivaColumn);
        pane.add(taulukkoMaksut, 5,20,2,1);

        // päivitetän taulukkoon tietoja ja värit
        taulukko.getColumns().clear();
        taulukko.getColumns().addAll(asiakasColumn, kategoriaColumn, paivaColumn);
        taulukko.setItems(asiakasTiedot);
        taulukko.setPrefHeight(250);
        taulukko.setStyle("-fx-background-color:#3a4a3d;");
        taulukko.setPlaceholder(new Label("Ei vielä tietoja"));
        taulukkoMaksut.setStyle("-fx-background-color:#3a4a3d;");
        taulukkoMaksut.setPlaceholder(new Label ("Ei vielä tietoja"));
        taulukkoMaksut.setPrefWidth(250);
        taulukkoMaksut.getColumns().addAll(nimiColumn,laskuColumn, erapaivaColumn);
        taulukkoMaksut.setItems(maksutiedot);


        //TIETOKANTA
        //yhteys tietokantaan sekä haku tietokannasta SELECT komennolla
        try{
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ot", // tietokannan nimi
                    "root", // käyttäjänimi
                    "salasana123" // salasana (lotan)
            );
            Statement statement1 = conn.createStatement();
            String sql = "SELECT asiakas.nimi, mökit.mökkitaso, varaus.varaus_alku FROM asiakas JOIN varaus ON asiakas.asiakas_id = varaus.asiakas_id JOIN mökit ON varaus.mökki_id = mökit.mökki_id";

            ResultSet resultSet = statement1.executeQuery(sql);
            while(resultSet.next()){
                String nimi = resultSet.getString("nimi");
                String taso = resultSet.getString("mökkitaso");
                String paiva = resultSet.getString("varaus_alku");
                asiakasTiedot.add(new OlioLuokka(nimi, taso, paiva));
            }
            Statement statement2 = conn.createStatement();
            String sql2 = "SELECT asiaks.nimi, mökit.hinta, maksut.erapaiva FROM asiakas JOIN varaus ON asiakas.asiakas_id = varaus.asiakas_id JOIN mökit ON varaus.mökki_id = mökit.mökki_id JOIN maksut ON varaus.lasku_id = maksut.lasku_id";
            ResultSet resultSet2 = statement2.executeQuery(sql2);
            while(resultSet2.next()){
                String nimi = resultSet2.getString("nimi");
                String hinta = resultSet2.getString("hinta");
                String erapaiva = resultSet2.getString("erapaiva");
                maksutiedot.add(new OlioLuokka(nimi, hinta, erapaiva));
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        //KUVA
        Image kuva1 = new Image(getClass().getResource("/cozy_spot_logo.png").toExternalForm());
        ImageView iv1 = new ImageView(kuva1);
        iv1.setFitHeight(140);
        iv1.setFitWidth(140);
        pane.add(iv1, 6,0,1,1);
    }
    public static void main(String[] args) {
        launch(args);
    }
}

