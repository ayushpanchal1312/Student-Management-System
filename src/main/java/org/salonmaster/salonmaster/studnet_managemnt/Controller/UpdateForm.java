package org.salonmaster.salonmaster.studnet_managemnt.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.salonmaster.salonmaster.studnet_managemnt.ConnectioProvider.ConnectionProvider;
import org.salonmaster.salonmaster.studnet_managemnt.Student_DATA.Student;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Controller class for the Update Student Form.
 * Allows editing and updating of an existing student's information.
 */
public class UpdateForm {

    @FXML
    private Button UpdateStudent;

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
    private TextField mark;

    @FXML
    private TextField mobo;

    @FXML
    private TextField roll;

    @FXML
    private VBox sideBar;

    /** Holds the current student whose details are being updated. */
    private Student currentStudent;

    /**
     * Pre-fills the form fields with the data of the selected student.
     *
     * @param student The student whose details are to be updated.
     */
    public void setStudentData(Student student) {
        this.currentStudent = student;
        fname.setText(student.getName());
        roll.setText(student.getRoll_no());
        dept.setText(student.getDepartment());
        e_mail.setText(student.getEmail());
        mobo.setText(student.getPhone());
        mark.setText(String.valueOf(student.getMarks()));
    }

    /**
     * Handles the update action when the Update Student button is clicked.
     * Validates input fields and updates the student record in the database.
     *
     * @param event The ActionEvent triggered by the button click.
     */
    @FXML
    void updateStudent(ActionEvent event) {
        String name = fname.getText();
        String roll_no = roll.getText();
        String department = dept.getText();
        String email = e_mail.getText();
        String phone = mobo.getText();
        String marksText = mark.getText();

        // Validation
        if (name.isEmpty() || roll_no.isEmpty() || department.isEmpty() || email.isEmpty() || phone.isEmpty() || marksText.isEmpty()) {
            NavigationClass.showAlert("All fields are required.", Alert.AlertType.WARNING);
            return;
        }

        if (!phone.matches("\\d{10}")) {
            NavigationClass.showAlert("Invalid Phone Number", Alert.AlertType.ERROR);
            return;
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            NavigationClass.showAlert("Invalid Email", Alert.AlertType.ERROR);
            return;
        }

        try {
            double marks = Double.parseDouble(marksText);

            Connection con = ConnectionProvider.getConnection();
            String updateSql = "UPDATE students SET name = ?, roll_no = ?, department = ?, email = ?, phone = ?, marks = ? WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(updateSql);
            stmt.setString(1, name);
            stmt.setString(2, roll_no);
            stmt.setString(3, department);
            stmt.setString(4, email);
            stmt.setString(5, phone);
            stmt.setDouble(6, marks);
            stmt.setInt(7, currentStudent.getId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                NavigationClass.showAlert("Student details updated successfully!", Alert.AlertType.INFORMATION);
                NavigationClass.navigateTo(event, "/org/salonmaster/salonmaster/studnet_managemnt/Admin_panel.fxml");
            } else {
                NavigationClass.showAlert("Update failed. Please try again.", Alert.AlertType.ERROR);
            }

        } catch (NumberFormatException e) {
            NavigationClass.showAlert("Marks must be a valid number", Alert.AlertType.ERROR);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            NavigationClass.showAlert("An error occurred: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    /**
     * Navigates to the Admin Panel screen when the Dashboard button is clicked.
     *
     * @param event The ActionEvent triggered by the button click.
     * @throws IOException If the FXML file cannot be loaded.
     */
    @FXML
    void dashBoard(ActionEvent event) throws IOException {
        NavigationClass.navigateTo(event, "/org/salonmaster/salonmaster/studnet_managemnt/Admin_panel.fxml");
    }

    /**
     * Navigates to the Student Registration Form when the Admission button is clicked.
     *
     * @param event The ActionEvent triggered by the button click.
     * @throws IOException If the FXML file cannot be loaded.
     */
    @FXML
    void admission(ActionEvent event) throws IOException {
        NavigationClass.navigateTo(event, "/org/salonmaster/salonmaster/studnet_managemnt/Student_From.fxml");
    }
}
