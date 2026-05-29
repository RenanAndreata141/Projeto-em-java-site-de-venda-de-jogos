package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexao {
    public static Connection getConexao() throws ClassNotFoundException, SQLException{
        Class.forName("org.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lojajogos", "localhost", "");
        return con;
    }
}
