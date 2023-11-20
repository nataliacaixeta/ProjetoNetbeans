package dao;

import model.Produto;
import java.sql.Connection;
import factory.ConnectionFactoryProduto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ProdutoDao {
    private Connection connection;
    private int idProduto;
    private String nome;
    private String descricao;
    private double valor;
    

    public ProdutoDao() {
        this.connection = new ConnectionFactoryProduto().getConnetion();
    }
    
    public void insert(Produto produto){
        String sql =  "INSERT INTO produto (nome,descricao,valor)VALUES(?,?,?)";
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setDouble(3, produto.getValor());
            
            stmt.execute();
            stmt.close();
        } catch (Exception e) {
        }
    }
    public List<Produto>leitura(){
        connection = new ConnectionFactoryProduto().getConnetion();
        PreparedStatement stml = null;
        ResultSet rs = null;
        
        List<Produto> produtos = new ArrayList<>();
        
    try {
    stml = connection.prepareStatement("SELECT * FROM produtos");
    rs = stml.executeQuery();
    
    while(rs.next()){
        Produto produto = new Produto();
        
        produto.setIdProduto(rs.getInt("IdProduto"));
        produto.setNome     (rs.getString("Nome"));
        produto.setDescricao(rs.getString("Descricao"));
        produto.setValor    (rs.getDouble("Valor"));
        produtos.add(produto);
    }
    
    } catch(Exception e){
    }
        return produtos;
    }
    
    public void delete(Produto produto){
        String sql = "DELETE FROM produto WHERE idProduto = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1,produto.getIdProduto());
            stmt.execute();
            JOptionPane.showMessageDialog(null,"Produto excluído com sucesso!");
            stmt.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Erro ao excluir Produto!");
        }
    }
    
    public void update(Produto produto){
        String sql = "UPDATE produto SET nome = ?,descricao = ?,valor = ? WHERE idProduto = ?";
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1,produto.getNome());
            stmt.setString(2,produto.getDescricao());
            stmt.setDouble(3,produto.getValor());
            
            stmt.execute();
            stmt.close();
            
        } catch (Exception e) {
            
        }
    }
}
