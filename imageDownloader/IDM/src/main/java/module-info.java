module com.example.idm {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.idm to javafx.fxml;
    exports com.example.idm;
}