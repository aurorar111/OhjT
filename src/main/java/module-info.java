module com.example.ohjt {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.ohjt to javafx.fxml;
    exports com.example.ohjt;
}