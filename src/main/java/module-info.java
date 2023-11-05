module com.example.invetorysystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires java.sql;
    requires mysql.connector.j;

    opens com.example.invetorysystem to javafx.fxml;
    exports com.example.invetorysystem;
}