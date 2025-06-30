package org.salonmaster.salonmaster.studnet_managemnt.main;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class StudentFrom extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/org/salonmaster/salonmaster/studnet_managemnt/Student_From.fxml"));

        Scene scene = new Scene(root);
        stage.setTitle("Dashboard");

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}



