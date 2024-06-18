package controler;

import static application.MainApp.getConnection;

import application.Constants;
import static application.MainApp.ListaComanda;
import static application.MainApp.clienteAtivo;
import static application.Utils.DialogBoxes.convertStringToDateTime;
import static application.Utils.DialogBoxes.getCurrentDateTime;
import model.Comanda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import model.Pedido;

public class ComandaController {

    // Exemplo de adaptação para incluir cliente na comanda
public void criarComanda(Comanda comanda) {
    String sql = "INSERT INTO comanda (data_comanda, numero_mesa, id_cliente, status_pagamento) VALUES (?, ?, ?, ?)";
    try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setObject(1, comanda.getDataComanda());
        stmt.setInt(2, comanda.getNumeroMesa());
        stmt.setInt(3, comanda.getCliente().getIdCliente());
        stmt.setString(4, comanda.getStatus_pagamento());
        stmt.executeUpdate();
        System.out.println("Comanda criada com sucesso!");        
        
    } catch (SQLException e) {
        System.out.println("Erro ao executar o SQL: " + e.getMessage());
    }
    }

    public Comanda buscarComanda(int idComanda) {
        Comanda comanda = null;
        String sql = "SELECT * FROM comanda WHERE id_comanda = ?";
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idComanda);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = application.MainApp.clienteController.buscarCliente(rs.getInt("id_cliente"));
                    comanda = new Comanda(
                            rs.getInt("id_comanda"),
                            rs.getObject("data_comanda", LocalDateTime.class),
                            rs.getInt("numero_mesa"), cliente, rs.getString("status_pagamento")
                            
                    );
                }
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar o SQL: " + e.getMessage());
        }
        return comanda;
    }

    public Comanda buscarComandaPorMesa(int numeroMesa) {
    Comanda comanda = null;
    String sql = "SELECT * FROM comanda WHERE numero_mesa = ?";
    try (Connection conn = getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, numeroMesa);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {                
                    Cliente cliente = application.MainApp.clienteController.buscarCliente(rs.getInt("id_cliente"));
                    comanda = new Comanda(
                            rs.getInt("id_comanda"),
                            rs.getObject("data_comanda", LocalDateTime.class),
                            rs.getInt("numero_mesa"), cliente, rs.getString("status_pagamento")
                );
            } else {
                // Se não encontrar uma comanda, cria uma nova comanda
                Cliente cliente = application.MainApp.clienteController.buscarCliente(rs.getInt("id_cliente"));
                if (cliente.getIdCliente() == 0) cliente.setIdCliente(1);
                comanda = new Comanda(proximaComanda(), convertStringToDateTime(getCurrentDateTime()), numeroMesa, cliente, "ABERTA");
            }
        }
        
    } catch (SQLException e) {
        System.out.println("Erro ao executar o SQL: " + e.getMessage());
    }
    return comanda;
    }
     
    
    
    public Comanda buscarComandaAbertaPorMesa(int numeroMesa) {
        Comanda comanda = null;
        String sql = "SELECT * FROM comanda WHERE numero_mesa = ? AND status_pagamento = 'ABERTA'";
        
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {            
            stmt.setInt(1, numeroMesa);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {                
                    Cliente cliente = application.MainApp.clienteController.buscarCliente(rs.getInt("id_cliente"));                                  
                    comanda = new Comanda(
                            rs.getInt("id_comanda"),
                            rs.getObject("data_comanda", LocalDateTime.class),
                            rs.getInt("numero_mesa"),
                            cliente, rs.getString("status_pagamento")
                    );
                } else {
                    // Se não encontrar uma comanda aberta, cria uma nova comanda
                    Cliente cliente = clienteAtivo;
                    comanda = new Comanda(
                            proximaComanda(),
                            convertStringToDateTime(getCurrentDateTime()),
                            numeroMesa,
                            cliente, "ABERTA"
                    );      
                    criarComanda(comanda);                    
                }
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar o SQL: " + e.getMessage());
        }
        
        return comanda;
    }
    

    public List<Comanda> listarComandas() {
        List<Comanda> comandas = new ArrayList<>();
        String sql = "SELECT * FROM comanda";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Cliente cliente = application.MainApp.clienteController.buscarCliente(rs.getInt("id_cliente"));
                Comanda comanda = new Comanda(
                        rs.getInt("id_comanda"),
                        rs.getObject("data_comanda", LocalDateTime.class),
                        rs.getInt("numero_mesa"), cliente, rs.getString("status_pagamento")
                );
                comandas.add(comanda);
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar o SQL: " + e.getMessage());
        }
        return comandas;
    }

    public List<Pedido> listarPedidosPorComandas(int id_Comanda) {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedidos where id_Comanda=" + id_Comanda + ";";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id_Comanda);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Pedido pedido = new Pedido(
                            rs.getInt("id_produto"),
                            rs.getInt("quantidade"),
                            rs.getInt("id_comanda"),
                            rs.getBigDecimal("valor_unitario")
                    );
                    pedidos.add(pedido);
                }
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao executar o SQL: " + e.getMessage());
        }

        return pedidos;
    }        
    
    public void atualizarComanda(Comanda comanda) {
        String sql = "UPDATE comanda SET data_comanda = ?, numero_mesa = ? WHERE id_comanda = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, comanda.getDataComanda());
            stmt.setInt(2, comanda.getNumeroMesa());
            stmt.setInt(3, comanda.getIdComanda());
            stmt.executeUpdate();
            
            System.out.println("Comanda atualizada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao executar o SQL: " + e.getMessage());
        }
    }

        public void FechaComanda(int id_Comanda) {
        String sql = "UPDATE comanda SET statusPagamento = ? WHERE id_comanda = ?";
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id_Comanda);
            stmt.executeUpdate();            
            System.out.println("Comanda fechada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao executar o SQL: " + e.getMessage());
        }
    }
        
    public void deletarComanda(int idComanda) {
        String sql = "DELETE FROM comanda WHERE id_comanda = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idComanda);
            stmt.executeUpdate();
            
            System.out.println("Comanda deletada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao executar o SQL: " + e.getMessage());
        }
    }
    
    
public int proximaComanda() {
    int quantidadeRegistros = 0;
    String sql = "SELECT COUNT(*) AS quantidade FROM comanda";
    try (Connection conn = getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                quantidadeRegistros = rs.getInt("quantidade");
            }
        }
        System.out.println("Consulta executada com sucesso! Quantidade de comandas: " + quantidadeRegistros);
        
    } catch (SQLException e) {
        System.out.println("Erro ao executar o SQL: " + e.getMessage());
    }
    return quantidadeRegistros;
}

}
