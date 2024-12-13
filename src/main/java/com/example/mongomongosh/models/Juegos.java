package com.example.mongomongosh.models;
import com.example.mongomongosh.models.DataBase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.print.Doc;
import java.util.ArrayList;

public class Juegos {
        private String nombre;
        private String genero;
        private double precio;
        private String fecha;

    public Juegos(String nombre, String genero, double precio, String fecha){
        this.nombre = nombre;
        this.genero = genero;
        this.precio = precio;
        this.fecha = fecha;
    }

    public Juegos(){

    }

    public ArrayList<Juegos> retornarJuegos(){
        ArrayList<Juegos> arrayJuegos = new ArrayList<>();
        MongoDatabase database = DataBase.conexion();
        try{
            MongoCollection<Document> coleccion = database.getCollection("restaurante");
            MongoCursor<Document> cursor = coleccion.find().iterator();

            while (cursor.hasNext()){
                System.out.println("hola");
            }





            System.out.println("hola");

        }
        catch (Exception e){
            System.out.println(e.getMessage() + "es este catch");
        }
        return arrayJuegos;
    }





    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }


}
