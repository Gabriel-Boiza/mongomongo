module com.example.mongomongosh {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;
    requires org.mongodb.driver.core;
    requires java.desktop;

    opens com.example.mongomongosh to javafx.fxml;
    opens com.example.mongomongosh.models to java.base;
    opens com.example.mongomongosh.controllers to javafx.fxml;
    exports com.example.mongomongosh;
}