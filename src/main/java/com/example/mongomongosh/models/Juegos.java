package com.example.mongomongosh.models;
import com.example.mongomongosh.models.DataBase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.print.Doc;
import java.util.ArrayList;

public class Juegos {
    private String id;
    private String titulo;
    private String genero;
    private Double precio;
    private String fecha;

    public Juegos(String id, String titulo, String genero, double precio, String fecha){
        this.id = id;
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
                String id = doc.getObjectId("_id").toHexString();
                String titulo = doc.getString("titulo");
                String genero = doc.getString("genero");
                double precio = doc.getDouble("precio");
                String fecha = doc.getString("fecha_lanzamiento");

                Juegos juego = new Juegos(id, titulo, genero, precio, fecha);

                arrayJuegos.add(juego);

            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return arrayJuegos;
    }

    public void insertarJuego(String titulo, String genero, Double precio, String fecha){
        MongoDatabase database = DataBase.conexion();  // Suponiendo que tienes una conexión establecida
        MongoCollection<Document> collection = database.getCollection("juegos");

        Document nuevoJuego = new Document("titulo", titulo)
                .append("genero", genero)
                .append("precio", precio)
                .append("fecha_lanzamiento", fecha);


        collection.insertOne(nuevoJuego);
    }

    public void modificarJuego(String id, String titulo, String genero, Double precio, String fecha){
        MongoDatabase database = DataBase.conexion();  // Suponiendo que tienes una conexión establecida
        MongoCollection<Document> collection = database.getCollection("juegos");

        ObjectId objectId = new ObjectId(id);

        Document filtro = new Document("_id", objectId);

        Document updateJuego = new Document("titulo", titulo)
                .append("genero", genero)
                .append("precio", precio)
                .append("fecha_lanzamiento", fecha);


        Document actualizacion = new Document("$set", updateJuego);

        collection.updateOne(filtro, actualizacion);
    }

    public void eliminarJuego(String id){
        MongoDatabase database = DataBase.conexion();
        MongoCollection<Document> collection = database.getCollection("juegos");

        ObjectId objectId = new ObjectId(id);

        Document filtro = new Document("_id", objectId);

        collection.deleteOne(filtro);
    }

    public boolean comprobarJuegoExiste(String titulo, String genero, Double precio, String fecha){
        MongoDatabase database = DataBase.conexion();
        boolean existe = false;
        try{
            Document filtro = new Document("titulo", titulo)
                    .append("genero", genero)
                    .append("precio", precio)
                    .append("fecha_lanzamiento", fecha);

            MongoCollection<Document> collection = database.getCollection("juegos");
            MongoCursor<Document> cursor = collection.find(filtro).iterator();

            if(cursor.hasNext()){
                existe = true;
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return existe;
    }

    public ArrayList<String> retornarElecciones(){
        ArrayList<String> array = new ArrayList<>();
        MongoDatabase database = DataBase.conexion();

        try{
            MongoCollection<Document> collection = database.getCollection("juegos");
            MongoCursor<Document> cursor = collection.find().iterator();

            while(cursor.hasNext()){
                Document doc = cursor.next();
                String genero = doc.getString("genero");
                if(!array.contains(genero)){
                    array.add(genero);
                }
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return array;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Juegos{" +
                "id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", genero='" + genero + '\'' +
                ", precio=" + precio +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}
