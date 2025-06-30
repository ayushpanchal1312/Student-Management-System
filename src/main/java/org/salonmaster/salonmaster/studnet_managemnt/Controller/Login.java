package org.salonmaster.salonmaster.studnet_managemnt.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import org.salonmaster.salonmaster.studnet_managemnt.ConnectioProvider.ConnectionProvider;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Controller class for the Login screen.
 * Handles user authentication by verifying credentials against the database.
 */
public class Login {

    @FXML
    private Button Login;

    @FXML
    private TextField password;

    @FXML
    private TextField uname;

    /**
     * Handles the login button click event.
     * Authenticates the user by checking the provided username and password
     * against the records in the "admin" table of the database.
     * If successful, navigates to the Admin Panel. Otherwise, shows an error alert.
     *
     * @param event ActionEvent triggered by the login button click.
     */
    @FXML
    void login(ActionEvent event) {
        String username = uname.getText();
        String pass = password.getText();

        Connection con = ConnectionProvider.getConnection();
        String query = "SELECT * FROM admin WHERE username = ? AND password = ?";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                NavigationClass.navigateTo(event, "/org/salonmaster/salonmaster/studnet_managemnt/Admin_panel.fxml");
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setHeaderText("Login Failed");
                alert.setContentText("Invalid Username or Password!");
                alert.show();
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
