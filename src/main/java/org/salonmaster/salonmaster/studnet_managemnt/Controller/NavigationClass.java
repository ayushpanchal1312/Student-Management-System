package org.salonmaster.salonmaster.studnet_managemnt.Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Utility class for handling common navigation and alert functionalities within the application.
 */
public class NavigationClass {

    /**
     * Loads a new FXML scene and navigates to it based on the given event and FXML file path.
     *
     * @param event    The ActionEvent that triggered the navigation (typically a button click).
     * @param fxmlPath The relative path to the FXML file to load.
     * @throws IOException If the FXML file cannot be found or loaded.
     */
    static void navigateTo(ActionEvent event, String fxmlPath) throws IOException {
        Parent root = FXMLLoader.load(NavigationClass.class.getResource(fxmlPath));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Displays a pop-up alert with the given message and alert type.
     *
     * @param message The message to be displayed in the alert dialog.
     * @param type    The type of the alert (e.g., INFORMATION, WARNING, ERROR).
     */
    static void showAlert(String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle("Notification");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
