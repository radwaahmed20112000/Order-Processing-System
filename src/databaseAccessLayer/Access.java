package databaseAccessLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Access {

    public Connection getConnection() {

        //jdbc:mysql://localhost:3306/mydb
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?characterEncoding=latin1&useConfigs=maxPerformance",
                    "root", "123456");
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return null;
    }

}
