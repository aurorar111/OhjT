package com.example.ohjt;

import javafx.application.Application;
import javafx.stage.Stage;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import java.util.List;
import javafx.scene.control.TextArea;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.beans.property.SimpleStringProperty;
import static javafx.collections.FXCollections.*;



public class Kaytto extends Application {
    /** tekstikenttä menon syöttämiseen */
    private TextField tfhenkilokuntaID = new TextField();
    private Button button = new Button("Kirjaudu");

    private TextField tfAsiakkaanimi = new TextField();
    private TextField tfAsiakasGmail = new TextField();
    private TextField tfAsiakasPuh = new TextField();
    private TextField tfAsiakasSynty = new TextField();

    private TextField tfAlkupva = new TextField();
    private TextField tfLoppupva = new TextField();
    private ComboBox<String> cbMokkitaso = new ComboBox<>();
    private Button Haebutton = new Button("Hae");
    private TextField saatavuus = new TextField();
    private TextField hinta = new TextField();


    private TextField laskuID = new TextField();
    private TextField lasku = new TextField();
    private TextField maksuntila = new TextField();





    /** Valikko kategorian valitsemiseen*/
    private ComboBox<String> cbKategoria = new ComboBox<>();
    /** tekstikenttä päivämäärän syöttämiseen*/
    private TextField tfPaivamaara = new TextField();
    /** Nappi menon lisäämistä varten*/
    private Button btLisaa = new Button("Lisää meno");
    /** Nappi kaikkien tietojen tyhjentämiseen*/
    private Button btNollaa = new Button("Nollaa kaikki tiedot");
    /** Tekstikenttä yhteissumman näyttämiseen*/
    private TextField tfYhteensa = new TextField();
    /** Tekstialue jossa kulut kategorioittain*/
    private TextArea taMenotKategoriat = new TextArea();
    /** Lista kaikista menoista*/
    //private List<BudjetointiTyokalu> menot;
    /** Lista jossa menot*/
    private ObservableList menoTiedot = observableArrayList();
    /** Lista jossa kategoriat*/
    private ObservableList kategoriaLista = observableArrayList();
    /** Kokonaissumma */
    private double summa = 0;
    /** Taulukko meno tapahtumista*/
    private TableView taulukko = new TableView<>();
    /** Taulukko jossa kategorian menomäärä*/
    private TableView kategoriatTaulukko = new TableView<>();


    @Override
    public void start(Stage stage) {
        menot = TiedostonKasittely.lataaMenot();
        menoTiedot.addAll(menot);

        /** Paneeli johon asetellaan menon syöttämiseen tarvittavat kentät vasempaan yläkulmaan */
        GridPane paneeli = new GridPane();
        paneeli.setPadding(new javafx.geometry.Insets(20)); // irrottaa reunasta
        paneeli.setAlignment(Pos.TOP_LEFT);
        paneeli.setHgap(10); // kenttien väliin tilaa
        paneeli.setVgap(10);

        // asetetaan kenttien leveyksiä jotta ne asettuu nätisti
        tfMenoMaara.setMaxWidth(150);
        cbKategoria.setMaxWidth(150);
        tfPaivamaara.setMaxWidth(150);
        tfYhteensa.setMaxWidth(700);
        btLisaa.setPrefWidth(200);
        btNollaa.setPrefWidth(120);
        taMenotKategoriat.setPrefWidth(150);
        tfPaivamaara.setPromptText("pv.kk.vvvv"); // kenttään ehdotus teksti josta tulee ilmi haluttu muoto syötteelle

        // asetellaan paneeliin ja lisätään label
        paneeli.add(new Label("Meno (0.0 €) "), 0, 0);
        paneeli.add(tfMenoMaara, 1, 0);
        paneeli.add(new Label("Kategoria: "), 0, 1);
        paneeli.add(cbKategoria, 1, 1);
        paneeli.add(new Label("Päivämäärä: "), 0, 2);
        paneeli.add(tfPaivamaara, 1, 2);
        paneeli.add(btLisaa, 1, 3);
        paneeli.add(btNollaa, 7, 0);

        // muutetaan värejä, käytän # muotoa koska löysin hyvän colourpaleten canvasta
        tfMenoMaara.setStyle("-fx-background-color:#D5E5D5;-fx-text-fill: black");
        cbKategoria.setStyle("-fx-background-color:#D5E5D5;-fx-text-fill: black");
        tfPaivamaara.setStyle("-fx-background-color:#D5E5D5;-fx-text-fill: black");
        btLisaa.setStyle("-fx-background-color:#6A9C89; -fx-text-fill: black; -fx-font-weight: bold;");
        btNollaa.setStyle("-fx-background-color:#D98324;-fx-text-fill: grey; ");
        tfYhteensa.setStyle("-fx-background-color:#6A9C89;-fx-text-fill: black");
        taMenotKategoriat.setStyle("-fx-background-color:#D5E5D5; -fx-text-fill: black;");
        btNollaa.setStyle("-fx-background-color:#D5E5D5;-fx-text-fill: grey; ");

        cbKategoria.getItems().addAll("Ruoka", "Asuminen", "Viihde", "Muut");
        cbKategoria.setValue("Valitse");

        tfYhteensa.setEditable(false); // käyttäjä ei voi muuttaa tietoja
        taMenotKategoriat.setEditable(false);

        // nappien toiminnan lisääminen
        btLisaa.setOnAction(e -> lisaaMeno());
        btNollaa.setOnAction(e-> nollaaTiedot());

        // ensimmäisen taulukon tekeminen jossa näkyy menotapahtumat yksittäisinä tapahtumina
        TableColumn<OlioLuokka, Double> summaColumn = new TableColumn<>("Summa €");
        summaColumn.setCellValueFactory(new PropertyValueFactory<>("summa" ));
        TableColumn<OlioLuokka, String> kategoriaColumn = new TableColumn<>("Kategoria");
        kategoriaColumn.setCellValueFactory(new PropertyValueFactory<>("kategoria"));
        TableColumn<OlioLuokka, String> paivaColumn = new TableColumn<>("Päivämäärä");
        paivaColumn.setCellValueFactory(new PropertyValueFactory<>("paiva"));

        // päivitetän taulukkoon tietoja ja värit
        taulukko.getColumns().clear();
        taulukko.getColumns().addAll(summaColumn, kategoriaColumn, paivaColumn);
        taulukko.setItems(menoTiedot);
        taulukko.setPrefHeight(250);
        taulukko.setPrefWidth(250);
        taulukko.setStyle("-fx-background-color:#D5E5D5;");
        taulukko.setPlaceholder(new Label("Ei vielä tietoja"));

        //hbox jotta saan taulukot vieretysten
        HBox hb = new HBox(40);
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().addAll(taulukko, kategoriatTaulukko);

        // hbox ja paneeli vboxiin päällekkäin
        VBox vbox = new VBox(10,  paneeli, hb);
        vbox.setStyle("-fx-background-color:#A7C0BE;");

        Label yhteensaLabel = new Label("Kaikki yhteensä: ");
        yhteensaLabel.setStyle("-fx-background-color:#6A9C89; -fx-text-fill: black; -fx-padding: 5px;");

        // tän ehkä ois voinu laittaa vaan vboxiin mutta säädin asetteluiden kanssa, kun halusin pitää ikkunan suht pienenä
        HBox hb2 = new HBox(0);
        hb2.setAlignment(Pos.CENTER_LEFT);
        hb2.getChildren().addAll(yhteensaLabel, tfYhteensa);
        hb2.setPadding(new javafx.geometry.Insets( 0,0 , 10, 100));
        vbox.getChildren().add(hb2);

        // ikkunan koko ja otsikko
        Scene scene = new Scene(vbox, 600, 500);
        stage.setTitle("Kulujen seurantasovellus");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Lisää menon syötettävien tietojen pohjalta
     * Tarkistaa että kaikki kentät täytetty oikein ja lisää menon listaan
     * Taulukoiden näkymät päivittyy ja tiedot tallentuu
     */
    private void lisaaMeno() {
        try {
            double maara = Double.parseDouble(tfMenoMaara.getText());
            String kategoria = cbKategoria.getValue();
            String paivamaara = tfPaivamaara.getText();

            //tarkistelaan onko syöte ok
            if (kategoria == null || paivamaara.isEmpty() || maara <= 0) {
                tfYhteensa.setText("Täytä kaikki kentät!");
                return;
            }
            if (!OlioLuokka.PaivaValidator.isValidDate(paivamaara)) {
                tfYhteensa.setText("Virheellinen päivämäärä!");
                return;
            }
            OlioLuokka uusiMeno = new OlioLuokka(maara, kategoria, paivamaara);
            menot.add(uusiMeno);
            menoTiedot.add(uusiMeno);
            summa += maara;

            TiedostonKasittely.tallennaMenot(menot); // tallentaa tiedostoon

            paivitaMenotKategoriat();

            tfYhteensa.setText(String.format("%.2f €", summa));
            tfMenoMaara.clear();
            tfPaivamaara.clear();

        } catch (NumberFormatException e) {
            tfYhteensa.setText("Virheellinen meno!");
        }
    }

    /**
     * Päivittää menojen kategoriat ja niiden summat
     * Laskee yhteen kategorioiden kokonaismenot ja näyttää toisessa taulukossa
     */
    private void paivitaMenotKategoriat() {

        double ruokaSumma = 0, asuminenSumma = 0, viihdeSumma = 0, muutSumma = 0;
        for (OlioLuokka menot : menot) {
            if ("Ruoka".equals(menot.getKategoria())) {
                ruokaSumma += menot.getSumma();
            } else if ("Asuminen".equals(menot.getKategoria())) {
                asuminenSumma += menot.getSumma();
            }else if ("Viihde".equals(menot.getKategoria())) {
                viihdeSumma += menot.getSumma();
            } else if ("Muut".equals(menot.getKategoria())) {
                muutSumma += menot.getSumma();
            }
        }
        kategoriaLista.clear();
        kategoriaLista.add(new String[] {"Ruoka", String.format("%.2f €", ruokaSumma)});
        kategoriaLista.add(new String[] {"Asuminen", String.format("%.2f €", asuminenSumma)});
        kategoriaLista.add(new String[] {"Viihde", String.format("%.2f €", viihdeSumma)});
        kategoriaLista.add(new String[] {"Muut", String.format("%.2f €", muutSumma)});

        // toinen taulukko johon kategorioittain kokonaissummat
        TableColumn<String[], String> kategoriaNimiColumn = new TableColumn<>("Kategoria");
        kategoriaNimiColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[0]));

        TableColumn<String[], String> kategoriaSummaColumn = new TableColumn<>("Yhteensä €");
        kategoriaSummaColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[1]));

        kategoriatTaulukko.getColumns().clear();
        kategoriatTaulukko.getColumns().add(kategoriaNimiColumn);
        kategoriatTaulukko.getColumns().add(kategoriaSummaColumn);

        kategoriatTaulukko.setItems(kategoriaLista);
        kategoriatTaulukko.setPrefHeight(150);
        kategoriatTaulukko.setPrefWidth(160);
        kategoriatTaulukko.setStyle("-fx-background-color:#D5E5D5;");
        kategoriatTaulukko.setPlaceholder(new Label("Ei vielä tietoja"));

    }

    /**
     * Nollaa napin toiminnallisuus eli nollataan tiedot ja tyhjentää tallennetut menot
     * Poistaa tiedot muistista ja tyhjentää näkymän
     */
    private void nollaaTiedot() {
        menot.clear();
        menoTiedot.clear();
        summa = 0;
        tfMenoMaara.clear();
        tfPaivamaara.clear();
        taMenotKategoriat.clear();
        kategoriaLista.clear();
        TiedostonKasittely.tyhjennaTiedosto(); //tiedoston tyhjentäminen
    }

    /**
     * Pääohjelman käynnistys
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}


