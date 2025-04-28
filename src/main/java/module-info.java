module com.example.ohjt {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ohjt to javafx.fxml;
    exports com.example.ohjt;
}