package application;

import static application.MainApp.getConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Atendente;
import model.Cliente;


public class Database_Alquimia {
                
    
      // Métodos para verificar conexão com o banco de dados
    public static boolean connectedDatabase() throws SQLException {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("Conectado ao banco de dados " + Constants.DB_NAME + ".");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }        return false;
    }

    public static boolean checkDatabase() {
        try (Connection conn = getConnection()) {
            String sql = "SELECT datname FROM pg_database WHERE datname = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, Constants.DB_NAME);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        System.out.println("O banco de dados existe: " + rs.getString("datname"));
                        return true;
                    } else {
                        System.out.println("O banco de dados não existe.");
                        return false;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao consultar o banco de dados: " + e.getMessage());
            return false;
        }
    }
    
    public static boolean prepareEnvironment() {
    try {
        if (!checkDatabase()) {
            if (createDatabase() && createTables()) {
                return true;
            } else {
                return false;
            }
        } else {
            return true; // Caso o banco de dados já exista, o ambiente está pronto
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}     
    
    public static void Prepared(){
        
        Atendente atendente = new Atendente(1,"Marina", "6298555-5997", "leonidiojr@gmail.com");            
        application.MainApp.atendenteController.criarAtendente(atendente);
        Cliente cliente = new Cliente(1,"Marina", "6298555-5997", "leonidiojr@gmail.com");            
        application.MainApp.clienteController.criarCliente(cliente);
        
    }

    private static boolean createDatabase() throws SQLException {
                    
        try (Connection conn = DriverManager.getConnection(Constants.URL, Constants.USER, Constants.PASSWORD);
             Statement stmt = conn.createStatement()) {

            String sql = "CREATE DATABASE " + Constants.DB_NAME;
            stmt.executeUpdate(sql);
            System.out.println("Banco de dados " + Constants.DB_NAME + " criado com sucesso.");
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao criar o banco de dados: " + e.getMessage());
            if (checkDatabase()) {
                return true;
            } else {
                return false;
            }
        }
    }

    private static boolean createTables() {
        try (Connection conn = DriverManager.getConnection(Constants.DB_URL, Constants.USER, Constants.PASSWORD);
             Statement stmt = conn.createStatement()) {

            // Criação das tabelas

             // Tabela cliente
            System.out.println("Iniciando criação da tabela de clientes...");
            String createClienteTable = "CREATE TABLE IF NOT EXISTS cliente (" +
                                        "id_cliente SERIAL PRIMARY KEY," +
                                        "nome_cliente VARCHAR(100)," +
                                        "telefone_cliente VARCHAR(20)," +
                                        "email_cliente VARCHAR(100)" +
                                        ")";
            stmt.executeUpdate(createClienteTable);

            // Inserção de cliente padrão
            System.out.println("Iniciando a inserção de clientes...");
            String insertClientTable = "INSERT INTO cliente (id_cliente, nome_cliente, telefone_cliente, email_cliente) " +
                                       "VALUES (1, 'Não identificado', 'LIVRE', 'LIVRE')";
            stmt.executeUpdate(insertClientTable);

            // Tabela atendente
            System.out.println("Iniciando a criação da tabela de atendentes...");
            String createAtendenteTable = "CREATE TABLE IF NOT EXISTS atendente (" +
                                          "id_atendente SERIAL PRIMARY KEY," +
                                          "nome_atendente VARCHAR(100)," +
                                          "telefone_atendente VARCHAR(20)," +
                                          "email_atendente VARCHAR(100)" +
                                          ")";
            stmt.executeUpdate(createAtendenteTable);

            // Inserção de atendente padrão
            System.out.println("Iniciando a inserção de atendentes...");
            String insertAtendTable = "INSERT INTO atendente (id_atendente, nome_atendente, telefone_atendente, email_atendente) " +
                                      "VALUES (1, 'Atendente 01', 'LIVRE', 'LIVRE')";
            stmt.executeUpdate(insertAtendTable);                    

             // Tabela comanda
            System.out.println("Iniciando a criação da tabela de comandas...");
            String createComandaTable = "CREATE TABLE IF NOT EXISTS comanda (" +
                    "id_comanda SERIAL PRIMARY KEY," +
                    "data_comanda TIMESTAMP," + // Alterado para TIMESTAMP para suportar LocalDateTime
                    "numero_mesa INT," +
                    "id_cliente INT," +
                    "status_pagamento VARCHAR(50)" +
                    ")";
            stmt.executeUpdate(createComandaTable);            

            // Tabela mesa
            System.out.println("Iniciando a criação da tabela de mesas...");
            String createMesaTable = "CREATE TABLE IF NOT EXISTS mesa (" +
                    "numero_mesa SERIAL PRIMARY KEY," +
                    "status_mesa VARCHAR(50)," +
                    "id_atendente INT," +
                    "FOREIGN KEY (id_atendente) REFERENCES atendente(id_atendente)," +
                    "id_cliente INT," +
                    "FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente)" +
                    ")";
            stmt.executeUpdate(createMesaTable);
            
            System.out.println("Iniciando a inserção de mesas...");
            String insertMesaTable =  "INSERT INTO mesa (numero_mesa, status_mesa, id_atendente, id_cliente) VALUES (1, 'LIVRE', NULL, NULL)," +
                    "(2, 'LIVRE', NULL, NULL)," +
                    "(3, 'LIVRE', NULL, NULL)," +
                    "(4, 'LIVRE', NULL, NULL)," +
                    "(5, 'LIVRE', NULL, NULL)," +
                    "(6, 'LIVRE', NULL, NULL)," +
                    "(7, 'LIVRE', NULL, NULL)," +
                    "(8, 'LIVRE', NULL, NULL)," +
                    "(9, 'LIVRE', NULL, NULL)," +
                    "(10, 'LIVRE', NULL, NULL)," +
                    "(11, 'LIVRE', NULL, NULL)," +
                    "(12, 'LIVRE', NULL, NULL)," +
                    "(13, 'LIVRE', NULL, NULL)," +
                    "(14, 'LIVRE', NULL, NULL)," +
                    "(15, 'LIVRE', NULL, NULL)," +
                    "(16, 'LIVRE', NULL, NULL)," +
                    "(17, 'LIVRE', NULL, NULL)," +
                    "(18, 'LIVRE', NULL, NULL)," +
                    "(19, 'LIVRE', NULL, NULL)," +
                    "(20, 'LIVRE', NULL, NULL)," +
                    "(21, 'LIVRE', NULL, NULL)," +
                    "(22, 'LIVRE', NULL, NULL)," +
                    "(23, 'LIVRE', NULL, NULL)," +
                    "(24, 'LIVRE', NULL, NULL)," +
                    "(25, 'LIVRE', NULL, NULL)," +
                    "(26, 'LIVRE', NULL, NULL)," +
                    "(27, 'LIVRE', NULL, NULL)," +
                    "(28, 'LIVRE', NULL, NULL)," +
                    "(29, 'LIVRE', NULL, NULL)," +
                    "(30, 'LIVRE', NULL, NULL)," +
                    "(31, 'LIVRE', NULL, NULL)," +
                    "(32, 'LIVRE', NULL, NULL)," +
                    "(33, 'LIVRE', NULL, NULL)," +
                    "(34, 'LIVRE', NULL, NULL)," +
                    "(35, 'LIVRE', NULL, NULL)," +
                    "(36, 'LIVRE', NULL, NULL)," +
                    "(37, 'LIVRE', NULL, NULL)," +
                    "(38, 'LIVRE', NULL, NULL)," +
                    "(39, 'LIVRE', NULL, NULL)," +
                    "(40, 'LIVRE', NULL, NULL)," +
                    "(41, 'LIVRE', NULL, NULL)," +
                    "(42, 'LIVRE', NULL, NULL)," +
                    "(43, 'LIVRE', NULL, NULL)," +
                    "(44, 'LIVRE', NULL, NULL)";
            stmt.executeUpdate(insertMesaTable);
            

            // Tabela pagamento
            System.out.println("Iniciando a criação da tabela de pagamentos...");
            String createPagamentoTable = "CREATE TABLE IF NOT EXISTS pagamento (" +
                    "id_pagamento SERIAL PRIMARY KEY," +
                    "data_pagamento DATE," +
                    "valor_pagamento DECIMAL," +
                    "tipo_pagamento VARCHAR(100)," +
                    "id_comanda INT," +
                    "FOREIGN KEY (id_comanda) REFERENCES comanda(id_comanda)" +
                    ")";
            stmt.executeUpdate(createPagamentoTable);

            // Tabela produto
            System.out.println("Iniciando a criação da tabela de produtos...");
            String createProdutoTable = "CREATE TABLE IF NOT EXISTS produto (" +
                    "id_produto SERIAL PRIMARY KEY," +
                    "descricao_produto TEXT," +
                    "valor_unitario DECIMAL," +
                    "imagem_produto BYTEA" +
                    ")";
            stmt.executeUpdate(createProdutoTable);        
            
            System.out.println("Inicando inserção de produtos...");            
            // Tabela pedidoSystem.out.println("Criando menu...");
            String sqlProdutosInsert = "INSERT INTO produto (id_produto,descricao_produto, valor_unitario, imagem_produto) VALUES " +
                "(1,'Hambúrguer Clássico', 15.50, 'hamburguer_classico.jpg')," +
                "(2,'Pizza Margherita', 25.00, 'pizza_margherita.jpg')," +
                "(3,'Salada Caesar', 18.75, 'salada_caesar.jpg')," +
                "(4,'Frango à Parmegiana', 28.00, 'frango_parmegiana.jpg')," +
                "(5,'Batata Frita', 10.50, 'batata_frita.jpg')," +
                "(6,'Cerveja Artesanal', 12.00, 'cerveja_artesanal.jpg')," +
                "(7,'Café Expresso', 8.75, 'cafe_expresso.jpg')," +
                "(8,'Torta de Maçã', 15.00, 'torta_maca.jpg')," +
                "(9,'Espaguete à Bolonhesa', 22.50, 'espaguete_bolonhesa.jpg')," +
                "(10,'Camarão Frito', 35.00, 'camarao_frito.jpg')," +
                "(11,'Suco de Laranja', 7.50, 'suco_laranja.jpg')," +
                "(12,'Chá Gelado', 6.00, 'cha_gelado.jpg')," +
                "(13,'Bife com Fritas', 30.00, 'bife_fritas.jpg')," +
                "(14,'Salmão Grelhado', 40.00, 'salmao_grelhado.jpg')," +
                "(15,'Risoto de Cogumelos', 25.00, 'risoto_cogumelos.jpg')," +
                "(16,'Panqueca de Carne', 20.00, 'panqueca_carne.jpg')," +
                "(17,'Quiche de Alho-poró', 18.00, 'quiche_alho_poro.jpg')," +
                "(18,'Brownie com Sorvete', 12.00, 'brownie_sorvete.jpg')," +
                "(19,'Sopa de Legumes', 15.00, 'sopa_legumes.jpg')," +
                "(20,'Taco de Carne', 12.50, 'taco_carne.jpg')," +
                "(21,'Nachos com Queijo', 18.50, 'nachos_queijo.jpg')," +
                "(22,'Sorvete de Chocolate', 10.00, 'sorvete_chocolate.jpg')," +
                "(23,'Coxinha de Frango', 6.00, 'coxinha_frango.jpg')," +
                "(24,'Empada de Palmito', 7.00, 'empada_palmito.jpg')," +
                "(25,'Pastel de Queijo', 5.00, 'pastel_queijo.jpg')," +
                "(26,'Lasanha à Bolonhesa', 27.50, 'lasanha_bolonhesa.jpg')," +
                "(27,'Filé Mignon', 50.00, 'file_mignon.jpg')," +
                "(28,'Brigadeiro', 3.00, 'brigadeiro.jpg')," +
                "(29,'Pudim de Leite', 8.00, 'pudim_leite.jpg')," +
                "(30,'Bolo de Cenoura', 10.00, 'bolo_cenoura.jpg')," +
                "(31,'Crepe de Nutella', 15.00, 'crepe_nutella.jpg')," +
                "(32,'Churrasco Misto', 45.00, 'churrasco_misto.jpg')," +
                "(33,'Feijoada', 35.00, 'feijoada.jpg')," +
                "(34,'Caldo Verde', 14.00, 'caldo_verde.jpg')," +
                "(35,'Cachorro-quente', 12.00, 'cachorro_quente.jpg')," +
                "(36,'Sushi Variado', 40.00, 'sushi_variado.jpg')," +
                "(37,'Ramen', 25.00, 'ramen.jpg')," +
                "(38,'Tempurá', 18.00, 'tempura.jpg')," +
                "(39,'Yakissoba', 20.00, 'yakissoba.jpg')," +
                "(40,'Ceviche', 30.00, 'ceviche.jpg')," +
                "(41,'Moqueca de Peixe', 38.00, 'moqueca_peixe.jpg')," +
                "(42,'Bobó de Camarão', 35.00, 'bobo_camarao.jpg')," +
                "(43,'Escondidinho de Carne', 22.00, 'escondidinho_carne.jpg')," +
                "(44,'Bolinho de Bacalhau', 15.00, 'bolinho_bacalhau.jpg')," +
                "(45,'Picanha na Brasa', 55.00, 'picanha_brasa.jpg')," +
                "(46,'Calzone', 28.00, 'calzone.jpg')," +
                "(47,'Pão de Alho', 8.00, 'pao_alho.jpg')," +
                "(48,'Bauru', 12.00, 'bauru.jpg')," +
                "(49,'Caponata', 18.00, 'caponata.jpg')," +
                "(50,'Carpaccio', 22.00, 'carpaccio.jpg')," +
                "(51,'Fondue de Queijo', 60.00, 'fondue_queijo.jpg')," +
                "(52,'Vinho Tinto', 45.00, 'vinho_tinto.jpg')," +
                "(53,'Espumante', 70.00, 'espumante.jpg')," +
                "(54,'Caipirinha', 15.00, 'caipirinha.jpg')," +
                "(55,'Margarita', 18.00, 'margarita.jpg')," +
                "(56,'Whisky', 50.00, 'whisky.jpg')," +
                "(57,'Gin Tônica', 20.00, 'gin_tonica.jpg')," +
                "(58,'Mojito', 16.00, 'mojito.jpg')," +
                "(59,'Piña Colada', 18.00, 'pina_colada.jpg')," +
                "(60,'Martini', 25.00, 'martini.jpg')," +
                "(61,'Tequila', 20.00, 'tequila.jpg')," +
                "(62,'Sangria', 30.00, 'sangria.jpg')," +
                "(63,'Bellini', 22.00, 'bellini.jpg')," +
                "(64,'Cosmopolitan', 18.00, 'cosmopolitan.jpg')," +
                "(65,'Long Island Iced Tea', 28.00, 'long_island_iced_tea.jpg')," +
                "(66,'Gin Fizz', 22.00, 'gin_fizz.jpg')," +
                "(67,'Caipiroska', 17.00, 'caipiroska.jpg')," +
                "(68,'Sex on the Beach', 20.00, 'sex_on_the_beach.jpg')," +
                "(69,'Negroni', 25.00, 'negroni.jpg')," +
                "(70,'Dry Martini', 22.00, 'dry_martini.jpg')," +
                "(71,'Manhattan', 26.00, 'manhattan.jpg')," +
                "(72,'Cuba Libre', 15.00, 'cuba_libre.jpg')," +
                "(73,'Aperol Spritz', 25.00, 'aperol_spritz.jpg')," +
                "(74,'Blue Lagoon', 20.00, 'blue_lagoon.jpg')," +
                "(75,'Mai Tai', 23.00, 'mai_tai.jpg')," +
                "(76,'Irish Coffee', 18.00, 'irish_coffee.jpg')," +
                "(77,'Brandy', 35.00, 'brandy.jpg')," +
                "(79,'Amaretto Sour', 22.00, 'amaretto_sour.jpg')," +
                "(80,'Bloody Mary', 25.00, 'bloody_mary.jpg')," +
                "(81,'White Russian', 20.00, 'white_russian.jpg')," +
                "(82,'French 75', 30.00, 'french_75.jpg')," +
                "(83,'Paloma', 18.00, 'paloma.jpg')," +
                "(84,'Singapore Sling', 28.00, 'singapore_sling.jpg')," +
                "(85,'Tom Collins', 20.00, 'tom_collins.jpg')," +
                "(86,'Vesper', 25.00, 'vesper.jpg')," +
                "(87,'Sazerac', 30.00, 'sazerac.jpg')," +
                "(88,'Boulevardier', 27.00, 'boulevardier.jpg')," +
                "(89,'Hurricane', 22.00, 'hurricane.jpg')," +
                "(90,'Mint Julep', 24.00, 'mint_julep.jpg')," +
                "(91,'Dark and Stormy', 20.00, 'dark_and_stormy.jpg')," +
                "(92,'Pisco Sour', 22.00, 'pisco_sour.jpg');";
            stmt.executeUpdate(sqlProdutosInsert);        
            
            System.out.println("Iniciando a criação da tabela de pedidos...");  
            String createPedidoTable = "CREATE TABLE IF NOT EXISTS pedido (" +
                    "id_pedido SERIAL PRIMARY KEY," +
                    "id_produto INT," +
                    "quantidade INT," +
                    "valor_unitario DECIMAL," +
                    "id_comanda INT," +
                    "numero_mesa INT," +
                    "FOREIGN KEY (id_produto) REFERENCES produto(id_produto)," +
                    "FOREIGN KEY (id_comanda) REFERENCES comanda(id_comanda)," +
                    "FOREIGN KEY (numero_mesa) REFERENCES mesa(numero_mesa)" +
                    ")";
            stmt.executeUpdate(createPedidoTable);

            // Adicionando extensão pgcrypto para UUID
            String createExtension = "CREATE EXTENSION IF NOT EXISTS \"pgcrypto\"";
            stmt.executeUpdate(createExtension);
            


            System.out.println("Tabelas criadas com sucesso.");
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabelas: " + e.getMessage());
            return false;
        }
    }
   

}
