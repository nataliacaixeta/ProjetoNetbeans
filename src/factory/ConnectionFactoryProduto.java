package factory;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectionFactoryProduto {
    
   public Connection getConnetion(){
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost/cadastropessoas","root","");
        } catch (Exception excecao) {
            throw new RuntimeException(excecao);
        }
    }
}
