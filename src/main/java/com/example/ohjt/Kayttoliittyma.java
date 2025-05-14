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
import java.util.Random;

public class Kayttoliittyma extends Application {

    //OlioLuokka olio = new OlioLuokka();
    Tiedosto tiedostoLuokka = new Tiedosto();

    private static int varausID = 1;
    private static int jarjestelmaID = 2;

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
    public ComboBox<String> cbMokkitaso = new ComboBox<>();
    public Button Haebutton = new Button("Hae");
    public TextField saatavuus = new TextField();
    public TextField hinta = new TextField();

    //PÄIVITYS NAPPI:
    private Button paivita = new Button("Tallenna varaus");

    public TextField laskuID = new TextField();
    public TextField laskuPva = new TextField();
    public TextField maksuntila = new TextField();

    public TableView taulukko = new TableView<>();
    public TableView taulukkoMaksut = new TableView<>();
    private ObservableList <OlioLuokka> asiakasTiedot = observableArrayList();
    public static DatePicker alkuDate = new DatePicker();
    public static DatePicker loppuDate = new DatePicker();

    //getterit
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
    public static int getVarausID() {
        return varausID;
    }
    public static int getJarjestelmaID() {
        return jarjestelmaID;
    }



    @Override
    public void start(Stage alkuikkuna) {

        tfhenkilokuntaID.setPromptText("syötä ID");
        /*tfhenkilokuntaID.textProperty().addListener((observable, oldValue, newValue) -> {
            button.setDisable(newValue.trim().isEmpty());
        });
        button.setDisable(tfhenkilokuntaID.getText().trim().isEmpty());*/

        Pane pohja = new Pane();
        Scene kehys = new Scene(pohja, 1000, 900);
        alkuikkuna.setTitle("Varausjärjestelmä");
        alkuikkuna.setScene(kehys);
        pohja.setStyle("-fx-background-color:#faf5f1;");
        button.setStyle("-fx-background-color:#ef6a9d;");

        alkuikkuna.show();

        GridPane pane = new GridPane();
        pane.setHgap(5);
        pane.setVgap(5);
        pane.setPadding(new javafx.geometry.Insets(20));
        //Vasen
        pane.add(new Label("Henkilökunta ID:"), 0, 0);
        pane.add(tfhenkilokuntaID, 1, 0);
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
        pane.add(paivita, 2, 12); //Paivita buttonnnn
        pane.add(new Label("Saatavuus:"), 0, 11);
        pane.add(saatavuus, 1, 11);
        pane.add(new Label("Generoitu asiakas ID:"), 0, 12);
        pane.add(tfAsiakasID, 1, 12);

        Haebutton.setPrefWidth(100);

        // PÄIVITÄ NAPIN TIEDOSTOON TALLENNUS
        paivita.setOnAction(actionEvent -> {
            tiedostoLuokka.tallennaTiedostoon(); //tallenna-nappia painaessa tallennaTiedostoon-metodia kutsutaan
            String nimi = tfAsiakkaanimi.getText();
            String sapo = tfAsiakasGmail.getText();
            String puh = tfAsiakasPuh.getText();
            String syntyma = tfAsiakasSynty.getValue().toString();
            String asiakasid = tfAsiakasID.getText();
            Tietokanta.lisaaVarausTietokantaan(nimi, sapo, puh, syntyma, asiakasid);
        });

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


        //Taulukko oikea
        TableColumn<OlioLuokka, Double> summaColumn = new TableColumn<>("Asiakas");
        summaColumn.setCellValueFactory(new PropertyValueFactory<>("summa" ));
        TableColumn<OlioLuokka, String> kategoriaColumn = new TableColumn<>("Kategoria");
        kategoriaColumn.setCellValueFactory(new PropertyValueFactory<>("kategoria"));
        TableColumn<OlioLuokka, String> paivaColumn = new TableColumn<>("Päivämäärä");
        paivaColumn.setCellValueFactory(new PropertyValueFactory<>("paiva"));
        taulukko.getColumns().addAll(summaColumn, kategoriaColumn, paivaColumn);
        pane.add(taulukko, 0,20,2,1);

        //Taulukko vasen
        TableColumn<OlioLuokka, String> laskuColumn= new TableColumn<>("Lasku");
        laskuColumn.setCellValueFactory(new PropertyValueFactory<>("Lasku "));
        TableColumn<OlioLuokka, String> erapaivaColumn = new TableColumn<>("Eräpäivä");
        erapaivaColumn.setCellValueFactory(new PropertyValueFactory<>("paiva"));
        taulukkoMaksut.getColumns().addAll(laskuColumn, erapaivaColumn);
        pane.add(taulukkoMaksut, 5,20,2,1);

        Haebutton.setOnAction(e-> {
            int satunnainenID = new Random().nextInt(30000)+1;
            tfAsiakasID.setText(String.valueOf(satunnainenID));
            tfAsiakasID.setEditable(false);

            int satunnainenLaskunID = new Random().nextInt(30000)+2000;
            laskuID.setText(String.valueOf(satunnainenLaskunID));
            laskuID.setEditable(false);
            laskuID.setText("L" + satunnainenLaskunID);

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

        // päivitetän taulukkoon tietoja ja värit
        taulukko.getColumns().clear();
        taulukko.getColumns().addAll(summaColumn, kategoriaColumn, paivaColumn);
        taulukko.setItems(asiakasTiedot);
        //taulukko.setItems(menoTiedot);
        taulukko.setPrefHeight(250);
        taulukko.setPrefWidth(250);
        taulukko.setStyle("-fx-background-color:#3a4a3d;");
        taulukko.setPlaceholder(new Label("Ei vielä tietoja"));
        taulukkoMaksut.setStyle("-fx-background-color:#3a4a3d;");
        taulukkoMaksut.setPlaceholder(new Label ("Ei vielä tietoja"));

        //toimiikoo nyt
        Image kuva1 = new Image(getClass().getResource("/cozy_spot_logo.png").toExternalForm());
        ImageView iv1 = new ImageView(kuva1);
        iv1.setFitHeight(140);
        iv1.setFitWidth(140);
        pane.add(iv1, 6,0,1,1);
    }

    //iii
    public static void main(String[] args) {
        launch(args);

    }
}

