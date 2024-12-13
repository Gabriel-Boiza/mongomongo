package com.example.mongomongosh.models;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class DataBase {

    public static MongoDatabase conexion () {

        MongoDatabase mongo = null;
        try{
            MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
            mongo = mongoClient.getDatabase("gabriel");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return mongo;
    }
}
