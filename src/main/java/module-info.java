module org.salonmaster.salonmaster.studnet_managemnt {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires postgresql;
    requires java.desktop;


    opens org.salonmaster.salonmaster.studnet_managemnt to javafx.fxml;
    opens org.salonmaster.salonmaster.studnet_managemnt.Controller to javafx.fxml;
    opens org.salonmaster.salonmaster.studnet_managemnt.Student_DATA to javafx.base;

    exports org.salonmaster.salonmaster.studnet_managemnt.main;
    exports org.salonmaster.salonmaster.studnet_managemnt.Controller;
}