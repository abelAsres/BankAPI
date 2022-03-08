package com.revature.ultility;

import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtility {

    public static Connection getConnection() throws SQLException {
        //create a connection string using enviornment variables url, username, password
        String url = System.getenv("postgres_url");
        String username = System.getenv("postgres_username");
        String password = System.getenv("postgresPS");

        //register the postgres driver from dependencies with DriverManager
        DriverManager.registerDriver(new Driver());

        //get connection obj from the DriverManager
        Connection connection = DriverManager.getConnection(url,username,password);

        return connection;
    }

}
