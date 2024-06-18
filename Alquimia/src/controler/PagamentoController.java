package controler;

import static application.MainApp.getConnection;
import application.Constants;
import model.Pagamento;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PagamentoController {

    public void criarPagamento(Pagamento pagamento) {
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO pagamento (data_pagamento, valor_pagamento, tipo_pagamento) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setObject(1, pagamento.getDataPagamento());
                stmt.setBigDecimal(2, pagamento.getValorPagamento());
                stmt.setString(3, pagamento.getTipoPagamento());
                stmt.executeUpdate();
                System.out.println("Pagamento criado com sucesso!");
                
            } catch (SQLException e) {
                System.out.println("Erro ao executar o SQL: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    public Pagamento buscarPagamento(int idPagamento) {
        Pagamento pagamento = null;
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM pagamento WHERE id_pagamento = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, idPagamento);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        pagamento = new Pagamento(
                                rs.getInt("id_pagamento"),
                                rs.getTimestamp("data_pagamento").toLocalDateTime(),
                                rs.getBigDecimal("valor_pagamento"),
                                rs.getString("tipo_pagamento")
                        );
                    }
                }
                
            } catch (SQLException e) {
                System.out.println("Erro ao executar o SQL: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
        return pagamento;
    }

    public List<Pagamento> listarPagamentos() {
        List<Pagamento> pagamentos = new ArrayList<>();
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM pagamento";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        Pagamento pagamento = new Pagamento(
                                rs.getInt("id_pagamento"),
                                rs.getTimestamp("data_pagamento").toLocalDateTime(),
                                rs.getBigDecimal("valor_pagamento"),
                                rs.getString("tipo_pagamento")
                        );
                        pagamentos.add(pagamento);
                    }
                    
                }
            } catch (SQLException e) {
                System.out.println("Erro ao executar o SQL: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
        return pagamentos;
    }
}
