package dao;

import model.Cliente;
import java.sql.Connection;
import factory.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ClienteDao {
    private Connection connection;
    private int idCliente;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    
    public ClienteDao(){
        this.connection = new ConnectionFactory().getConnetion();
    }
    
    public void insert(Cliente cliente){
        String sql = "INSERT INTO cliente (nome,cpf,email,telefone)VALUES(?,?,?,?)";
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4 , cliente.getTelefone());
            
            stmt.execute();
            stmt.close();
            
        } catch (Exception e) {
        }
    }
    
    public List<Cliente>leitura(){
        connection = new ConnectionFactory().getConnetion();
        PreparedStatement stml = null;
        ResultSet rs = null;
        
        List<Cliente> clientes = new ArrayList<>();
        
    try {
    stml = connection.prepareStatement("SELECT * FROM cliente");
    rs = stml.executeQuery();
    
    while(rs.next()){
        Cliente cliente = new Cliente();
        
        cliente.setIdCliente(rs.getInt("idCliente"));
        cliente.setNome     (rs.getString("Nome"));
        cliente.setCpf      (rs.getString("Cpf"));
        cliente.setEmail    (rs.getString("Email"));
        cliente.setTelefone (rs.getString("Telefone"));
        clientes.add(cliente);
        
    }
    
    } catch(Exception e){
        JOptionPane.showMessageDialog(null,"View possui um erro!");
    }
        return clientes;
    }
    public void delete(Cliente cliente){
        String sql = "DELETE   FROM cliente WHERE idCliente = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1,cliente.getIdCliente());
            stmt.execute();
            JOptionPane.showMessageDialog(null,"Cliente excluído com sucesso!");
            stmt.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Erro ao excluir cliente!");
        }
    }
    public void update(Cliente cliente){
        String sql = "UPDATE cliente SET nome = ?,cpf = ?,email = ?,telefone = ? WHERE idCliente = ?";
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1,cliente.getNome());
            stmt.setString(2,cliente.getCpf());
            stmt.setString(3,cliente.getEmail());
            stmt.setString(4,cliente.getTelefone());
            stmt.setInt   (5,cliente.getIdCliente());
            
            stmt.execute();
            stmt.close();
            
        } catch (Exception e) {
            
        }
    }
    
}
