package controler;

import static application.MainApp.getConnection;
import application.Constants;
import model.Atendente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class AtendenteController {

    public void criarAtendente(Atendente atendente) {
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO atendente (nome_atendente, telefone_atendente, email_atendente) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, atendente.getNomeAtendente());
                stmt.setString(2, atendente.getTelefoneAtendente());
                stmt.setString(3, atendente.getEmailAtendente());
                stmt.executeUpdate();
                System.out.println("Atendente criado com sucesso!");
                
            } catch (SQLException e) {
                System.out.println("Erro ao executar o SQL: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }        
    }

    public Atendente buscarAtendente(int idAtendente) {
        Atendente atendente = null;
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM atendente WHERE id_atendente = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, idAtendente);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        atendente = new Atendente(
                            rs.getInt("id_atendente"),
                            rs.getString("nome_atendente"),
                            rs.getString("telefone_atendente"),
                            rs.getString("email_atendente")
                        );
                    }
                }
                
            } catch (SQLException e) {
                System.out.println("Erro ao executar o SQL: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
        return atendente;
    }

    public List<Atendente> listarAtendentes() {
        List<Atendente> atendentes = new ArrayList<>();
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM atendente";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        Atendente atendente = new Atendente(
                            rs.getInt("id_atendente"),
                            rs.getString("nome_atendente"),
                            rs.getString("telefone_atendente"),
                            rs.getString("email_atendente")
                        );
                        atendentes.add(atendente);
                    }
                }
                
            } catch (SQLException e) {
                System.out.println("Erro ao executar o SQL: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
        return atendentes;
    }

    public void atualizarAtendente(Atendente atendente) {
        try (Connection conn = getConnection()) {
            String sql = "UPDATE atendente SET nome_atendente = ?, telefone_atendente = ?, email_atendente = ? WHERE id_atendente = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, atendente.getNomeAtendente());
                stmt.setString(2, atendente.getTelefoneAtendente());
                stmt.setString(3, atendente.getEmailAtendente());
                stmt.setInt(4, atendente.getIdAtendente());
                stmt.executeUpdate();
                System.out.println("Atendente atualizado com sucesso!");
                
            } catch (SQLException e) {
                System.out.println("Erro ao executar o SQL: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    public void deletarAtendente(int idAtendente) {
        try (Connection conn = getConnection()) {
            String sql = "DELETE FROM atendente WHERE id_atendente = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, idAtendente);
                stmt.executeUpdate();
                System.out.println("Atendente deletado com sucesso!");
            } catch (SQLException e) {
                System.out.println("Erro ao executar o SQL: " + e.getMessage());
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
    
}
