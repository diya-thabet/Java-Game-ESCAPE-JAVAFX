module EscapeGame {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.base;
    requires java.desktop;

    opens application to javafx.graphics, javafx.fxml;
    //exports world1;
    opens world1 to javafx.graphics, javafx.fxml; 
}
