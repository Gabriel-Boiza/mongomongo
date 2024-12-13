package com.example.mongomongosh.models;
import com.example.mongomongosh.models.DataBase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.print.Doc;
import java.util.ArrayList;

public class Juegos {
        private String titulo;
        private String genero;
        private Double precio;
        private String fecha;

    public Juegos(String titulo, String genero, double precio, String fecha){
        this.titulo = titulo;
        this.genero = genero;
        this.precio = precio;
        this.fecha = fecha;
    }

    public Juegos(){

    }

    public ArrayList<Juegos> retornarJuegos(){
        MongoDatabase database = DataBase.conexion();
        ArrayList<Juegos> arrayJuegos = new ArrayList<>();

        try{
            MongoCollection<Document> collection = database.getCollection("juegos");
            MongoCursor<Document> cursor = collection.find().iterator();

            while(cursor.hasNext()){
                Document doc = cursor.next();
                String titulo = doc.getString("titulo");
                String genero = doc.getString("genero");
                double precio = doc.getDouble("precio");
                String fecha = doc.getString("fecha_lanzamiento");

                Juegos juego = new Juegos(titulo, genero, precio, fecha);

                arrayJuegos.add(juego);

            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return arrayJuegos;
    }


    @Override
    public String toString() {
        return "Juegos{" +
                "titulo='" + titulo + '\'' +
                ", genero='" + genero + '\'' +
                ", precio=" + precio +
                ", fecha='" + fecha + '\'' +
                '}';
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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
