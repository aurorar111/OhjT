module com.example.ohjt {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.ohjt to javafx.fxml;
    exports com.example.ohjt;
}