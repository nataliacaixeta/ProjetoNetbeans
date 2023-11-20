package factory;

import java.sql.Connection;
import java.sql.SQLException;

public class TesteConexao {
    public static void main(String[]largs) throws SQLException{
        Connection connection = new ConnectionFactory().getConnetion();
        System.out.println("Conexao inicializada");
        
        connection.close();
    }
}
