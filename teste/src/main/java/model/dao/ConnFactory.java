package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnFactory {
    
    public static Connection getConnection() throws Exception {
        
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema", "root", "Sanches5210@");
        
        return conn;
    }
}
