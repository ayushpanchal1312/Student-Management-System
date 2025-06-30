package org.salonmaster.salonmaster.studnet_managemnt.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.salonmaster.salonmaster.studnet_managemnt.ConnectioProvider.ConnectionProvider;
import org.salonmaster.salonmaster.studnet_managemnt.Student_DATA.Student;

import java.io.IOException;
import java.sql.*;

/**
 * Controller class for the Admin Panel screen.
 * Displays student records in a TableView and provides search, navigation, and form opening functionality.
 */
public class AdminPanel {

    @FXML
    private Button addStudnet;

    @FXML
    private Button dashBoard;

    @FXML
    private TableColumn<Student, String> department;

    @FXML
    private TableColumn<Student, String> email;

    @FXML
    private TableColumn<Student, Integer> id;

    @FXML
    private TableColumn<Student, String> marks;

    @FXML
    private TableColumn<Student, String> name;

    @FXML
    private TableColumn<Student, String> phone;

    @FXML
    private TableColumn<Student, String> rollno;

    @FXML
    private TextField searchFeild;

    @FXML
    private VBox sideBar;

    @FXML
    private TableView<Student> studentTable;

    /**
     * ObservableList to store and manage student data for the TableView.
     */
    ObservableList<Student> list = FXCollections.observableArrayList();

    /**
     * Navigates to the student admission form when the "Add Student" button is clicked.
     *
     * @param event ActionEvent triggered by button click.
     * @throws IOException if FXML loading fails.
     */
    @FXML
    void admission(ActionEvent event) throws IOException {
        NavigationClass.navigateTo(event, "/org/salonmaster/salonmaster/studnet_managemnt/Student_From.fxml");
    }

    /**
     * Initializes the Admin Panel.
     * Sets up TableView columns, loads student data from the database,
     * adds double-click listener to open student forms,
     * and enables real-time search filtering.
     */
    @FXML
    public void initialize() {
        id.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        name.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getName()));
        rollno.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getRoll_no()));
        department.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getDepartment()));
        email.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getEmail()));
        phone.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getPhone()));
        marks.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(String.valueOf(cellData.getValue().getMarks())));

        loadStudentData();

        studentTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && studentTable.getSelectionModel().getSelectedItem() != null) {
                Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
                openApplicationForm(selectedStudent);
            }
        });

        searchFeild.textProperty().addListener((observable, oldValue, newValue) -> filterStudentList(newValue));
    }

    /**
     * Loads student records from the database and populates the TableView.
     */
    private void loadStudentData() {
        Connection con = ConnectionProvider.getConnection();
        try {
            String Students = "SELECT * FROM students";
            PreparedStatement statement = con.prepareStatement(Students);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String roll_no = rs.getString("roll_no");
                String department = rs.getString("department");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                double marks = rs.getDouble("marks");

                Student student = new Student(id, name, roll_no, department, email, phone, marks);
                list.add(student);
            }
            studentTable.setItems(list);

        } catch (SQLException e) {
            System.out.println("DATA NOT FOUND");
        }

        // TableView UI configuration
        id.setReorderable(false);
        name.setReorderable(false);
        rollno.setReorderable(false);
        department.setReorderable(false);
        email.setReorderable(false);
        phone.setReorderable(false);
        marks.setReorderable(false);
        studentTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    /**
     * Opens the student application form with the selected student's details pre-filled.
     *
     * @param student The student whose details will be passed to the form.
     */
    private void openApplicationForm(Student student) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/salonmaster/salonmaster/studnet_managemnt/Application_Form.fxml"));
            Parent root = loader.load();

            ApplicationForm controller = loader.getController();
            controller.setStudentData(student);

            Stage stage = (Stage) studentTable.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Filters the student list based on the provided keyword.
     * The filter applies to student names and email addresses.
     *
     * @param keyword The search keyword entered by the user.
     */
    private void filterStudentList(String keyword) {
        ObservableList<Student> filteredList = FXCollections.observableArrayList();

        if (keyword == null || keyword.isEmpty()) {
            studentTable.setItems(list);  // Show full list if search is empty
            return;
        }

        String lowerCaseKeyword = keyword.toLowerCase();

        for (Student student : list) {
            if (student.getName().toLowerCase().contains(lowerCaseKeyword) ||
                    student.getEmail().toLowerCase().contains(lowerCaseKeyword)) {
                filteredList.add(student);
            }
        }

        studentTable.setItems(filteredList);
    }
}
