package factory;

import java.sql.Connection;
import java.sql.SQLException;

public class TesteConexaoProduto {
    public static void main (String[]largs) throws SQLException{
        Connection connection = new ConnectionFactoryProduto().getConnetion();
        System.out.println("Conexao inicializada");
        
        connection.close();
    }
}
