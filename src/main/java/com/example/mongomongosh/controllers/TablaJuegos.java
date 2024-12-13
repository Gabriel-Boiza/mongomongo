package com.example.mongomongosh.controllers;

import com.example.mongomongosh.models.DataBase;
import com.example.mongomongosh.models.Juegos;
import com.mongodb.client.MongoDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;


public class TablaJuegos {

    @FXML TableView<Juegos> tabla_juegos;
    @FXML TableColumn<Juegos, String> columna_titulo;
    @FXML TableColumn<Juegos, String> columna_genero;
    @FXML TableColumn<Juegos, String> columna_precio;
    @FXML TableColumn<Juegos, String> columna_fecha;

    private ObservableList<Juegos> juegosObervable;


    @FXML
    public void initialize() {
        if (tabla_juegos != null) {
            Juegos juegos = new Juegos();
            ArrayList<Juegos> arrayJuegos = juegos.retornarJuegos();

            juegosObervable = FXCollections.observableArrayList(arrayJuegos);

            tabla_juegos.setItems(juegosObervable);
            columna_titulo.setCellValueFactory(new PropertyValueFactory<Juegos, String>("titulo"));
            columna_genero.setCellValueFactory(new PropertyValueFactory<Juegos, String>("genero"));
            columna_precio.setCellValueFactory(new PropertyValueFactory<Juegos, String>("precio"));
            columna_fecha.setCellValueFactory(new PropertyValueFactory<Juegos, String>("fecha"));
            System.out.println(arrayJuegos);
        }
    }



}