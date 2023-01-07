module com.example.dec3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.dec3 to javafx.fxml;
    exports com.example.dec3;
}