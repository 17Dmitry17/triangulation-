module com.example.task3kg {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.junit.jupiter.api;


    opens com.example.task3kg to javafx.fxml;
    exports com.example.task3kg;
}