/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
/**
 *
 * @author felipe
 */
public class ConnectionDB {
    
    private static Connection conn;
    public Connection getMysqlConnection() throws Exception
    {
        String stringDeConexao = "jdbc:mysql://localhost/TESTE";
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(stringDeConexao,"root","root");
        return conn;
    }
    
    
}
