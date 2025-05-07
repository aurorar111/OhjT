package com.example.ohjt;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
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

public class Kayttoliittyma extends Application {
    private TextField henkilokuntaG = new TextField("");

    private TextField tfhenkilokuntaID = new TextField();
    private Button button = new Button("Kirjaudu");

    private TextField tfAsiakkaanimi = new TextField();
    private TextField tfAsiakasGmail = new TextField();
    private TextField tfAsiakasPuh = new TextField();
    private TextField tfAsiakasSynty = new TextField();

    private TextField varauksenAlku = new TextField();
    private TextField varauksenLoppu = new TextField();
    private ComboBox<String> cbMokkitaso = new ComboBox<>();
    private Button Haebutton = new Button("Hae");
    private TextField saatavuus = new TextField();
    private TextField hinta = new TextField();


    private TextField laskuID = new TextField();
    private TextField lasku = new TextField();
    private TextField maksuntila = new TextField();

    private TableView taulukko = new TableView<>();

    @Override
    public void start(Stage alkuikkuna){
        Pane pohja = new Pane();
        Scene kehys = new Scene(pohja, 800, 800);
        alkuikkuna.setTitle("Varausjärjestelmä");
        alkuikkuna.setScene(kehys);
        alkuikkuna.show();

        GridPane pane = new GridPane();

        pane.setHgap(5);
        pane.setVgap(5);
        pane.setPadding(new javafx.geometry.Insets(20));
        pane.add(new Label("Henkilökunta ID:"), 0, 0);
        pane.add(tfhenkilokuntaID,1,0);
        pane.add(button,0,1);
        pane.add(new Label ("Varauksen alkupäivämäärä:"), 0, 2);
        pane.add(varauksenAlku,1,2);
        pane.add(new Label ("Varauksen loppu päivämäärä:"), 0, 3);
        pane.add(varauksenLoppu,1,3);
        pane.add(new Label ("Asiakkaan nimi:"), 0, 4);
        pane.add(tfAsiakkaanimi,1,4);
        pane.add(new Label ("Asiakkaan gmail:"), 0, 5);
        pane.add(tfAsiakasGmail,0,5);
        pane.add(new Label ("Asiakkaan Puhelinnumero:"), 0, 6);
        pane.add(tfAsiakasPuh,0,6);
        pane.add(new Label ("Asiakkaan Syntymäpäivä:"), 0, 7);
        pane.add(tfAsiakasSynty,0,7);
        pane.add(cbMokkitaso,0,8);
        pane.add(Haebutton,0,9);
        pane.add(saatavuus,0,10);
        pane.add(hinta,0,11);
        pane.add(laskuID,0,12);
        pane.add(lasku,0,13);
        pane.add(maksuntila,0,14);
        pane.add(new Label ("Varauksen loppu:"),0,16);
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

        // päivitetän taulukkoon tietoja ja värit
        taulukko.getColumns().clear();
        taulukko.getColumns().addAll(summaColumn, kategoriaColumn, paivaColumn);
        //taulukko.setItems(menoTiedot);
        taulukko.setPrefHeight(250);
        taulukko.setPrefWidth(250);
        taulukko.setStyle("-fx-background-color:#D5E5D5;");
        taulukko.setPlaceholder(new Label("Ei vielä tietoja"));

    }



    public static void main(String[] args) {
        launch(args);
    }
}

