package com.example.ohjt;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Kayttoliittyma extends Application {
    private TextField henkilokuntaG = new TextField("");
    private TextField varauksenAlku = new TextField("Alku");
    private TextField varauksenLoppu = new TextField("Loppu");

    @Override
    public void start(Stage alkuikkuna){

        GridPane pane = new GridPane();

        pane.setHgap(5);
        pane.setVgap(5);
        pane.add(henkilokuntaG,0,0);
        pane.add(varauksenAlku,0,1);
        pane.add(varauksenLoppu,0,3);

        Scene kehys = new Scene(pane, 600, 500);
        alkuikkuna.setTitle("Varausjärjestelmä");
        alkuikkuna.setScene(kehys);
        alkuikkuna.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
}

