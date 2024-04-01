module com.mycompany.anxious {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.anxious to javafx.fxml;
    exports com.mycompany.anxious;
}
