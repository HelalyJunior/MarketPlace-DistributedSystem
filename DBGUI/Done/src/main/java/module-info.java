module com.example.done{
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires javafx.graphics;
    requires java.desktop;
    requires java.sql;

    exports com.example.done;
    opens com.example.done to javafx.fxml, javafx.graphics;
}
