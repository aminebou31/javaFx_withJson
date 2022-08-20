module com.example.w22comp1011gctest2student {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.w22comp1011gctest2student to javafx.fxml;
    exports com.example.w22comp1011gctest2student;
}