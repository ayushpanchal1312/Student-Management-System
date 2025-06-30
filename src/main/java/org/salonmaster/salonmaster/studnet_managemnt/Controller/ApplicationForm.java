package org.salonmaster.salonmaster.studnet_managemnt.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.salonmaster.salonmaster.studnet_managemnt.ConnectioProvider.ConnectionProvider;
import org.salonmaster.salonmaster.studnet_managemnt.Student_DATA.Student;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Controller class for the Application Form screen.
 * Displays details of a specific student and provides options to edit or delete their record.
 */
public class ApplicationForm {

    @FXML
    private Button Delete;

    @FXML
    private Text Dept;

    @FXML
    private Button Edit;

    @FXML
    private Text Email;

    @FXML
    private Text FirstName;

    @FXML
    private Text LastName;

    @FXML
    private Text Phone;

    @FXML
    private Text RollNo;

    @FXML
    private Button admission;

    @FXML
    private Button dashBoard;

    @FXML
    private VBox sideBar;

    /**
     * Stores the ID of the currently displayed student for deletion operations.
     */
    private int studentId;

    /**
     * Holds the currently displayed student object for use in edit operations.
     */
    private Student currentStudent;

    /**
     * Database connection object.
     */
    Connection con;

    /**
     * Populates the Application Form with the selected student's details.
     *
     * @param student The student whose details are to be displayed.
     */
    public void setStudentData(Student student) {
        FirstName.setText(student.getName());
        RollNo.setText(student.getRoll_no());
        Dept.setText(student.getDepartment());
        Email.setText(student.getEmail());
        Phone.setText(student.getPhone());
        studentId = student.getId();
        currentStudent = student;
    }

    /**
     * Navigates back to the Admin Panel when the "Dashboard" button is clicked.
     *
     * @param event ActionEvent triggered by button click.
     * @throws IOException if FXML loading fails.
     */
    @FXML
    void dashBoard(ActionEvent event) throws IOException {
        NavigationClass.navigateTo(event, "/org/salonmaster/salonmaster/studnet_managemnt/Admin_panel.fxml");
    }

    /**
     * Deletes the currently displayed student record from the database.
     * If successful, navigates back to the Admin Panel with a confirmation alert.
     * If deletion fails, displays an error alert.
     *
     * @param event ActionEvent triggered by button click.
     */
    @FXML
    void delete(ActionEvent event) {
        try {
            con = ConnectionProvider.getConnection();
            String deleteSql = "DELETE FROM students WHERE id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(deleteSql);
            preparedStatement.setInt(1, studentId);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                NavigationClass.navigateTo(event, "/org/salonmaster/salonmaster/studnet_managemnt/Admin_panel.fxml");
                NavigationClass.showAlert("Student Deleted Successfully", Alert.AlertType.INFORMATION);
            } else {
                NavigationClass.showAlert("Failed to delete student. Please try again.", Alert.AlertType.ERROR);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Opens the Update Form with the current student's details pre-filled for editing.
     *
     * @param event ActionEvent triggered by button click.
     * @throws IOException if FXML loading fails.
     */
    @FXML
    void edit(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/salonmaster/salonmaster/studnet_managemnt/UpdateForm.fxml"));
        Parent root = loader.load();

        UpdateForm updateFormController = loader.getController();
        updateFormController.setStudentData(currentStudent);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
