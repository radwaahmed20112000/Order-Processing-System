package databaseAccessLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Access {

    public Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/appdb", "root", "2620"); ///
            return connection;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

}
