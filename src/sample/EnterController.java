package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class EnterController {

    @FXML
    private TextField name;

    @FXML
    private Button button;

    @FXML
    void ready(ActionEvent event) throws IOException {
        if (!name.getText().equals("")) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("test.fxml"));
            loader.setController(new TestController(name.getText()));
            loader.load();
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            button.getScene().getWindow().hide();
            stage.setTitle("Тестирование");
            stage.setScene(new Scene(root, 590, 540));
            stage.setResizable(false);
            stage.show();
        } else name.styleProperty().setValue("-fx-border-color: red");
    }

}
