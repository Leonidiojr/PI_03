package controler;

import static application.MainApp.getConnection;
import model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteController {

    public void criarCliente(Cliente cliente) {
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO cliente (nome_cliente, telefone_cliente, email_cliente) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, cliente.getNomeCliente());
                stmt.setString(2, cliente.getTelefoneCliente());
                stmt.setString(3, cliente.getEmailCliente());
                stmt.executeUpdate();
                System.out.println("Cliente criado com sucesso!");
                
            } catch (SQLException e) {
                System.out.println("Erro ao executar o SQL: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    public Cliente buscarCliente(int idCliente) {
        Cliente cliente = null;
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM cliente WHERE id_cliente = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, idCliente);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        cliente = new Cliente(
                            rs.getInt("id_cliente"),
                            rs.getString("nome_cliente"),
                            rs.getString("telefone_cliente"),
                            rs.getString("email_cliente")
                        );
                    }
                }
                
            } catch (SQLException e) {
                System.out.println("Erro ao executar o SQL: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
        return cliente;
    }

    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM cliente";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        Cliente cliente = new Cliente(
                            rs.getInt("id_cliente"),
                            rs.getString("nome_cliente"),
                            rs.getString("telefone_cliente"),
                            rs.getString("email_cliente")
                        );
                        clientes.add(cliente);
                    }
                }
                
            } catch (SQLException e) {
                System.out.println("Erro ao executar o SQL: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
        return clientes;
    }

    public void atualizarCliente(Cliente cliente) {
        try (Connection conn = getConnection()) {
            String sql = "UPDATE cliente SET nome_cliente = ?, telefone_cliente = ?, email_cliente = ? WHERE id_cliente = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, cliente.getNomeCliente());
                stmt.setString(2, cliente.getTelefoneCliente());
                stmt.setString(3, cliente.getEmailCliente());
                stmt.setInt(4, cliente.getIdCliente());
                stmt.executeUpdate();
                System.out.println("Cliente atualizado com sucesso!");
                
            } catch (SQLException e) {
                System.out.println("Erro ao executar o SQL: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    public void deletarCliente(int idCliente) {
        try (Connection conn = getConnection()) {
            String sql = "DELETE FROM cliente WHERE id_cliente = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, idCliente);
                stmt.executeUpdate();
                System.out.println("Cliente deletado com sucesso!");
            } catch (SQLException e) {
                System.out.println("Erro ao executar o SQL: " + e.getMessage());
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
    
}
