package org.salonmaster.salonmaster.studnet_managemnt.Controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.salonmaster.salonmaster.studnet_managemnt.ConnectioProvider.ConnectionProvider;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Controller class for the Student Registration Form.
 * Handles student data input, validation, and database storage.
 */
public class StudentFrom {

    @FXML
    private Button AddStudent;

    @FXML
    private Button admission;

    @FXML
    private Button dashBoard;

    @FXML
    private TextField dept;

    @FXML
    private TextField e_mail;

    @FXML
    private TextField fname;

    @FXML
    private ComboBox<String> gen;

    @FXML
    private TextField lname;

    @FXML
    private TextField mark;

    @FXML
    private TextField mobo;

    @FXML
    private TextField roll;

    @FXML
    private VBox sideBar;

    Connection con;

    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Navigates back to the Admin Panel screen.
     *
     * @param event The ActionEvent triggered by clicking the Dashboard button.
     * @throws IOException If the Admin Panel FXML file cannot be loaded.
     */
    @FXML
    public void dashBoard(ActionEvent event) throws IOException {
        NavigationClass.navigateTo(event, "/org/salonmaster/salonmaster/studnet_managemnt/Admin_panel.fxml");
    }

    /**
     * Handles the Add Student button click.
     * Validates input fields, checks for duplicate students, and inserts new student data into the database.
     *
     * @param event The ActionEvent triggered by clicking the Add Student button.
     */
    @FXML
    void addStudent(ActionEvent event) {
        String firstName = fname.getText();
        String lastName = lname.getText();
        String gender = gen.getValue();
        String roll_no = roll.getText();
        String department = dept.getText();
        String marksText = mark.getText();
        String email = e_mail.getText();
        String phone = mobo.getText();

        if (firstName.isEmpty() || lastName.isEmpty() || gender == null ||
                roll_no.isEmpty() || department.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All Fields are Required");
        } else if (!phone.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(null, "Invalid Phone Number");
        } else if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            JOptionPane.showMessageDialog(null, "Invalid Email");
        } else {
            try {
                double marks = Double.parseDouble(marksText);

                con = ConnectionProvider.getConnection();
                String checkEmail = "SELECT id FROM students WHERE email = ?";
                PreparedStatement checkStmt = con.prepareStatement(checkEmail);
                checkStmt.setString(1, email);
                ResultSet rs = checkStmt.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Student Already exists");
                } else {
                    String insertStudent = "INSERT INTO students(name, roll_no, department, email, phone, marks) VALUES (?, ?, ?, ?, ?, ?)";
                    PreparedStatement userStatement = con.prepareStatement(insertStudent);
                    userStatement.setString(1, firstName + lastName);
                    userStatement.setString(2, "CS" + roll_no);
                    userStatement.setString(3, department);
                    userStatement.setString(4, email);
                    userStatement.setString(5, phone);
                    userStatement.setDouble(6, marks);
                    userStatement.executeUpdate();

                    NavigationClass.navigateTo(event, "/org/salonmaster/salonmaster/studnet_managemnt/Admin_panel.fxml");
                    JOptionPane.showMessageDialog(null, "Student Registered");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Marks must be a valid number");
            } catch (SQLException | IOException e) {
                System.out.println(e);
            }
        }
    }

    /**
     * Initializes the controller after the FXML fields are loaded.
     * Populates the gender ComboBox with options.
     */
    @FXML
    public void initialize() {
        FillComboBox();
    }
    /**
     * Fills the gender ComboBox with predefined options.
     */
    private void FillComboBox() {
        gen.setItems(FXCollections.observableArrayList("MALE", "FEMALE"));
    }
}
