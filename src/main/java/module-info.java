module com.example.databasejavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.databasejavafx to javafx.fxml;
    exports com.example.databasejavafx;
}