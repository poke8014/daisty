package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class LaunchScreenController {
    @FXML
    private Button getStarted;

    @FXML
    void onGetStarted(ActionEvent event) throws IOException {
        Parent choosingUserPage = FXMLLoader.load(getClass().getResource("SignInScreen.fxml"));
        Scene choosingUserScene = new Scene(choosingUserPage);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(choosingUserScene);
        appStage.show();
    }
}
