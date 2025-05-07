package com.example.ohjt;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.DatePicker;


public class Kayttoliittyma extends Application {
    private TextField henkilokuntaG = new TextField("");

    private TextField tfhenkilokuntaID = new TextField();
    private Button button = new Button("Kirjaudu");

    private TextField tfAsiakkaanimi = new TextField();
    private TextField tfAsiakasGmail = new TextField();
    private TextField tfAsiakasPuh = new TextField();
    private TextField tfAsiakasSynty = new TextField();
    private TextField tfAsiakasID = new TextField();

    private TextField varauksenAlku = new TextField();
    private TextField varauksenLoppu = new TextField();
    private ComboBox<String> cbMokkitaso = new ComboBox<>();
    private Button Haebutton = new Button("Hae");
    private TextField saatavuus = new TextField();
    private TextField hinta = new TextField();


    private TextField laskuID = new TextField();
    private TextField laskuPva = new TextField();
    private TextField maksuntila = new TextField();

    private TableView taulukko = new TableView<>();
    private TableView taulukkoMaksut = new TableView<>();
    private DatePicker alkuDate = new DatePicker();
    private DatePicker loppuDate = new DatePicker();

    @Override
    public void start(Stage alkuikkuna){
        Pane pohja = new Pane();
        Scene kehys = new Scene(pohja, 800, 800);
        alkuikkuna.setTitle("Varausjärjestelmä");
        alkuikkuna.setScene(kehys);
        pohja.setStyle("-fx-background-color:#e0f6e0;");
        alkuikkuna.show();

        GridPane pane = new GridPane();
        pane.setHgap(5);
        pane.setVgap(5);
        pane.setPadding(new javafx.geometry.Insets(20));
        //Vasen
        pane.add(new Label("Henkilökunta ID:"), 0, 0);
        pane.add(tfhenkilokuntaID,1,0);
        pane.add(button,0,1);
        pane.add(new Label ("Varauksen alkupäivämäärä:"), 0, 3);
        pane.add(alkuDate,1,3);
        pane.add(new Label ("Varauksen loppu päivämäärä:"), 0, 4);
        pane.add(loppuDate,1,4);
        pane.add(new Label ("Asiakkaan nimi:"), 0, 5);
        pane.add(tfAsiakkaanimi,1,5);
        pane.add(new Label ("Asiakkaan gmail:"), 0, 6);
        pane.add(tfAsiakasGmail,1,6);
        pane.add(new Label ("Asiakkaan Puhelinnumero:"), 0, 7);
        pane.add(tfAsiakasPuh,1,7);
        pane.add(new Label ("Asiakkaan Syntymäpäivä:"), 0, 8);
        pane.add(tfAsiakasSynty,1,8);
        pane.add(cbMokkitaso,0,9);
        pane.add(Haebutton,0,10);
        pane.add(new Label("Saatavuus:"),0,11);
        pane.add(saatavuus,1,11);
        pane.add(new Label ("Generoitu asiakas ID:"),0,12);
        pane.add(tfAsiakasID,1,12);

        //Oikea
        pane.add(new Label ("Hinta:"),5,3);
        pane.add(hinta,6,3);
        pane.add(new Label ("Laskun ID:"),5,4);
        pane.add(laskuID,6,4);
        pane.add(new Label ("Lasku eräpäivä:"),5,5);
        pane.add(laskuPva,6,5);
        pane.add(new Label ("Maksuntila:"),5,6);
        pane.add(maksuntila,6,6);
        pohja.getChildren().add(pane);

        cbMokkitaso.getItems().addAll("Perus", "Parempi", "Erinomainen", "TOP tier");
        cbMokkitaso.setValue("Valitse");


        TableColumn<OlioLuokka, Double> summaColumn = new TableColumn<>("Asiakas");
        summaColumn.setCellValueFactory(new PropertyValueFactory<>("summa" ));
        TableColumn<OlioLuokka, String> kategoriaColumn = new TableColumn<>("Kategoria");
        kategoriaColumn.setCellValueFactory(new PropertyValueFactory<>("kategoria"));
        TableColumn<OlioLuokka, String> paivaColumn = new TableColumn<>("Päivämäärä");
        paivaColumn.setCellValueFactory(new PropertyValueFactory<>("paiva"));
        taulukko.getColumns().addAll(summaColumn, kategoriaColumn, paivaColumn);
        pane.add(taulukko, 0,20,2,1);

        TableColumn<OlioLuokka, String> laskuColumn= new TableColumn<>("Lasku");
        laskuColumn.setCellValueFactory(new PropertyValueFactory<>("Lasku "));
        TableColumn<OlioLuokka, String> erapaivaColumn = new TableColumn<>("Eräpäivä");
        erapaivaColumn.setCellValueFactory(new PropertyValueFactory<>("paiva"));
        taulukkoMaksut.getColumns().addAll(laskuColumn, erapaivaColumn);
        pane.add(taulukkoMaksut, 10,20,2,1);



        // päivitetän taulukkoon tietoja ja värit
        taulukko.getColumns().clear();
        taulukko.getColumns().addAll(summaColumn, kategoriaColumn, paivaColumn);
        //taulukko.setItems(menoTiedot);
        taulukko.setPrefHeight(250);
        taulukko.setPrefWidth(250);
        taulukko.setStyle("-fx-background-color:#D5E5D5;");
        taulukko.setPlaceholder(new Label("Ei vielä tietoja"));
        taulukkoMaksut.setStyle("-fx-background-color:#D5E5D5;");
        taulukkoMaksut.setPlaceholder(new Label ("Ei vielä tietoja"));
        //toimiikoo

    }
    public static void main(String[] args) {
        launch(args);
    }
}

