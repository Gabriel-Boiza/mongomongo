package com.example.mongomongosh.controllers;

import com.example.mongomongosh.models.DataBase;
import com.example.mongomongosh.models.Juegos;
import com.mongodb.client.MongoDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class TablaJuegos {

    @FXML TableView<Juegos> tabla_juegos;
    @FXML TableColumn<Juegos, String> columna_titulo;
    @FXML TableColumn<Juegos, String> columna_genero;
    @FXML TableColumn<Juegos, String> columna_precio;
    @FXML TableColumn<Juegos, String> columna_fecha;

    @FXML TextField texto_titulo;
    @FXML TextField texto_genero;
    @FXML TextField texto_precio;
    @FXML DatePicker texto_fecha;

    @FXML Button btn_crear;
    @FXML Button btn_modificar;
    @FXML Button btn_limpiar;
    @FXML Button btn_eliminar;

    Juegos juegos = new Juegos();

    private ObservableList<Juegos> juegosObervable;
    private static Juegos juegoSeleccionado;


    @FXML
    public void initialize() {
        if (tabla_juegos != null) {
            mostrarJuegos();
            seleccionarJuego();
            accionesBtn();
        }
    }

    public void accionesBtn(){
        btn_crear.setOnAction(event ->{
            if (esDouble(texto_precio.getText()) && texto_fecha != null && !texto_precio.getText().isEmpty() && !texto_titulo.getText().isEmpty() && !texto_genero.getText().isEmpty()){

                LocalDate fechaSeleccionada = texto_fecha.getValue();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //Clase capaz de formatear un localdate
                String fecha = fechaSeleccionada.format(formatter);

                String titulo = texto_titulo.getText();
                String genero = texto_genero.getText();
                String precio = texto_precio.getText();
                Double valor_precio = Double.parseDouble(precio);

                if (!juegos.comprobarJuegoExiste(titulo, genero, valor_precio, fecha)){
                    juegos.insertarJuego(titulo, genero, valor_precio, fecha);
                    mostrarJuegos();
                }
            }
            else{
                Alert alertaValores = new Alert(Alert.AlertType.WARNING);
                alertaValores.setContentText("Los valores introducidos no son aptos");
                alertaValores.showAndWait();
            }
        });
        btn_modificar.setOnAction(actionEvent -> {
            if (esDouble(texto_precio.getText()) && texto_fecha != null && !texto_precio.getText().isEmpty() && !texto_titulo.getText().isEmpty() && !texto_genero.getText().isEmpty()){
                LocalDate fechaSeleccionada = texto_fecha.getValue();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //Clase capaz de formatear un localdate
                String fecha = fechaSeleccionada.format(formatter);

                String titulo = texto_titulo.getText();
                String genero = texto_genero.getText();
                String precio = texto_precio.getText();
                Double valor_precio = Double.parseDouble(precio);

                juegos.modificarJuego(juegoSeleccionado.getId(), titulo, genero, valor_precio, fecha);
                mostrarJuegos();
            }
            else{
                Alert alertaValores = new Alert(Alert.AlertType.WARNING);
                alertaValores.setContentText("Los valores introducidos no son aptos");
                alertaValores.showAndWait();
            }
        });
        btn_limpiar.setOnAction(actionEvent -> {
            limpiarInputs();
        });
        btn_eliminar.setOnAction(actionEvent -> {
            if(juegoSeleccionado != null){
                juegos.eliminarJuego(juegoSeleccionado.getId());
                mostrarJuegos();
            }
            else{
                Alert alerta2 = new Alert(Alert.AlertType.WARNING);
                alerta2.setContentText("Ningun juego seleccionado");
                alerta2.showAndWait();
            }
        });
    }



    public void mostrarJuegos(){

        ArrayList<Juegos> arrayJuegos = juegos.retornarJuegos();

        juegosObervable = FXCollections.observableArrayList(arrayJuegos);

        tabla_juegos.setItems(juegosObervable);
        columna_titulo.setCellValueFactory(new PropertyValueFactory<Juegos, String>("titulo"));
        columna_genero.setCellValueFactory(new PropertyValueFactory<Juegos, String>("genero"));
        columna_precio.setCellValueFactory(new PropertyValueFactory<Juegos, String>("precio"));
        columna_fecha.setCellValueFactory(new PropertyValueFactory<Juegos, String>("fecha"));

    }

    public void seleccionarJuego(){
        tabla_juegos.getSelectionModel().selectedItemProperty().addListener((observable, antiguo_valor, nuevo_valor) -> {
            if(nuevo_valor != null){
                juegoSeleccionado = nuevo_valor;
                rellenarInputs();
            }
        });
    }

    public void limpiarInputs(){
        texto_titulo.setText("");
        texto_genero.setText("");
        texto_precio.setText("");
        texto_fecha.setValue(null);

        juegoSeleccionado = null;
    }

    public void rellenarInputs() {
        String fechaString = juegoSeleccionado.getFecha();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fecha = LocalDate.parse(fechaString, formatter);

        texto_titulo.setText(juegoSeleccionado.getTitulo());
        texto_genero.setText(juegoSeleccionado.getGenero());
        texto_precio.setText(juegoSeleccionado.getPrecio().toString());
        texto_fecha.setValue(fecha);
    }

    public boolean esDouble(String texto) {
        boolean _double = true;
        try {
            Double.parseDouble(texto);
        } catch (NumberFormatException e) {
            _double = false;
        }
        return _double;
    }



}