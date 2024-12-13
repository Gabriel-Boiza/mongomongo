package com.example.mongomongosh.controllers;

import com.example.mongomongosh.models.DataBase;
import com.example.mongomongosh.models.Juegos;
import com.mongodb.client.MongoDatabase;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class TablaJuegos {

    @FXML TableView<Juegos> tabla_juegos;
    @FXML TableColumn<Juegos, String> columna_nombre;
    @FXML TableColumn<Juegos, String> columna_genero;
    @FXML TableColumn<Juegos, String> columna_precio;
    @FXML TableColumn<Juegos, String> columna_fecha;


    @FXML
    public void initialize() {
        if(tabla_juegos != null){

            Juegos juegos = new Juegos();
            juegos.retornarJuegos();
        }
    }


}