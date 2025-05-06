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

        GridPane pane = new GridPane();

        pane.setHgap(5);
        pane.setVgap(5);
        pane.setPadding(new javafx.geometry.Insets(20));
        pane.add(new Label("Henkilökunta ID:"), 0, 0);
        pane.add(tfhenkilokuntaID,1,0);
        pane.add(button,0,1);
        pane.add(new Label ("Varauksen alkupäivämäärä:"), 0, 2);
        pane.add(varauksenAlku,1,2);
        pane.add(varauksenLoppu,0,3);
        pane.add(tfAsiakkaanimi,0,4);
        pane.add(tfAsiakasGmail,0,5);
        pane.add(tfAsiakasPuh,0,6);
        pane.add(tfAsiakasSynty,0,7);
        pane.add(cbMokkitaso,0,8);
        pane.add(Haebutton,0,9);
        pane.add(saatavuus,0,10);
        pane.add(hinta,0,11);
        pane.add(laskuID,0,12);
        pane.add(lasku,0,13);
        pane.add(maksuntila,0,14);


        Scene kehys = new Scene(pane, 600, 500);
        alkuikkuna.setTitle("Varausjärjestelmä");
        alkuikkuna.setScene(kehys);
        alkuikkuna.show();

        HBox hb = new HBox(40);
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().addAll(pane);

        // ensimmäisen taulukon tekeminen jossa näkyy menotapahtumat yksittäisinä tapahtumina
        TableColumn<OlioLuokka, Double> vierasColumn = new TableColumn<>("Vieras");
        vierasColumn.setCellValueFactory(new PropertyValueFactory<>("summa" ));
        TableColumn<OlioLuokka, String> mokkiColumn = new TableColumn<>("Mökki");
        mokkiColumn.setCellValueFactory(new PropertyValueFactory<>("kategoria"));
        TableColumn<OlioLuokka, String> paivaColumn = new TableColumn<>("Päivämäärä");
        paivaColumn.setCellValueFactory(new PropertyValueFactory<>("paiva"));

        // päivitetän taulukkoon tietoja ja värit
        taulukko.getColumns().clear();
        taulukko.getColumns().addAll(vierasColumn, mokkiColumn, paivaColumn);
        //taulukko.setItems(menoTiedot);
        taulukko.setPrefHeight(250);
        taulukko.setPrefWidth(250);
        taulukko.setStyle("-fx-background-color:#D5E5D5;");
        taulukko.setPlaceholder(new Label("Ei vielä tietoja"));

        button.setOnAction(e -> kirjaudu());
        Haebutton.setOnAction(e-> haeTiedot());
    }



    public static void main(String[] args) {
        launch(args);
    }
}

