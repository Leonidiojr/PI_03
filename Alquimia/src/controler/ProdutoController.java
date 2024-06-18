package controler;

import static application.MainApp.getConnection;
import application.Constants;
import model.Produto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoController {

    public void criarProduto(Produto produto) {
    try (Connection conn = getConnection()) {
            String sql = "INSERT INTO produto (descricao_produto, valor_unitario, imagem_produto) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, produto.getDescricaoProduto());
                stmt.setBigDecimal(2, produto.getValorUnitarioProduto());
                stmt.setString(3, produto.getImagemProduto());
                stmt.executeUpdate();
                System.out.println("Produto criado com sucesso!");
            } catch (SQLException e) {
                System.out.println("Erro ao executar o SQL: " + e.getMessage());
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    public Produto buscarProduto(int idProduto) {
        Produto produto = null;
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM produto WHERE id_produto = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, idProduto);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        produto = new Produto(
                            rs.getInt("id_produto"),
                            rs.getString("descricao_produto"),
                            rs.getBigDecimal("valor_unitario"),
                            rs.getString("imagem_produto")
                        );
                    }
                }
            } catch (SQLException e) {
                System.out.println("Erro ao executar o SQL: " + e.getMessage());
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
        return produto;
    }

    public List<Produto> listarProdutos() {
        
        List<Produto> produtos = new ArrayList<>();
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM produto";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        Produto produto = new Produto(
                            rs.getInt("id_produto"),
                            rs.getString("descricao_produto"),
                            rs.getBigDecimal("valor_unitario"),
                            rs.getString("imagem_produto")
                        );
                        produtos.add(produto);
                    }
                }
            } catch (SQLException e) {
                System.out.println("Erro ao executar o SQL: " + e.getMessage());
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
        return produtos;
    }

    public void atualizarProduto(Produto produto) {
        try (Connection conn = getConnection()) {
            String sql = "UPDATE produto SET descricao_produto = ?, valor_unitario = ?, imagem_produto = ? WHERE id_produto = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, produto.getDescricaoProduto());
                stmt.setBigDecimal(2, produto.getValorUnitarioProduto());
                stmt.setString(3, produto.getImagemProduto());
                stmt.setInt(4, produto.getIdProduto());
                stmt.executeUpdate();
                System.out.println("Produto atualizado com sucesso!");
                
            } catch (SQLException e) {
                System.out.println("Erro ao executar o SQL: " + e.getMessage());
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    public void deletarProduto(int idProduto) {
        try (Connection conn = getConnection()) {
            String sql = "DELETE FROM produto WHERE id_produto = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, idProduto);
                stmt.executeUpdate();
                System.out.println("Produto deletado com sucesso!");
            } catch (SQLException e) {
                System.out.println("Erro ao executar o SQL: " + e.getMessage());
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
    
}
