package org.salonmaster.salonmaster.studnet_managemnt.ConnectioProvider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Provides a method to establish and return a connection to the PostgreSQL database.
 * This class follows a simple singleton-like approach to maintain a shared connection instance.
 */
public class ConnectionProvider {

    /** Static connection instance reused across the application. */
    static Connection con;

    /**
     * Establishes and returns a connection to the PostgreSQL database.
     *
     * @return Connection object if successful, otherwise null.
     */
    public static Connection getConnection() {
        String url = "jdbc:postgresql://localhost:5432/Student_Management";
        String username = "postgres";
        String password = "admin123";

        try {
            Class.forName("org.postgresql.Driver");  // Load PostgreSQL driver
            con = DriverManager.getConnection(url, username, password);  // Establish connection
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }

        return con;
    }
}
