package application;

import static application.Utils.DialogBoxes.convertStringToDateTime;
import static application.Utils.DialogBoxes.getCurrentDateTime;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import view.Splash;


import controler.AtendenteController;
import controler.ClienteController;
import controler.ComandaController;
import controler.MesaController;
import controler.PagamentoController;
import controler.PedidoController;
import controler.ProdutoController;
import controler.StatusMesa;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Atendente;
import model.Cliente;
import model.Comanda;
import model.Mesa;
import model.Pagamento;
import model.Pedido;
import model.Produto;


public class MainApp extends JApplet {
    
    private static final int JFXPANEL_WIDTH_INT = 300;
    private static final int JFXPANEL_HEIGHT_INT = 250;
    private static JFXPanel fxContainer;
    
    public final int numeroMesa = 0;
    
    
    public static final AtendenteController atendenteController = new AtendenteController();
    public static final ClienteController clienteController = new ClienteController();
    public static final ComandaController comandaController = new ComandaController();
    public static final MesaController mesaController = new MesaController();
    public static final PagamentoController pagamentoController = new PagamentoController();
    public static final PedidoController pedidoController = new PedidoController();   
    public static final ProdutoController produtoController = new ProdutoController();   
    
    public static Mesa mesaAtiva = new Mesa(1,StatusMesa.OCUPADA);
    public static Atendente atendenteAtivo = new Atendente(0,"","","");
    public static Cliente clienteAtivo = new Cliente(0,"","","");
    public static Comanda comandaAtiva = new Comanda(0,convertStringToDateTime(getCurrentDateTime()), 1,clienteAtivo,"ABERTA");
    public static Pagamento pagamentoAtivo= new Pagamento(0,convertStringToDateTime(getCurrentDateTime()),new BigDecimal(0.0), "1");
    public static Pedido pedidoAtivo = new Pedido(0,0,0,new BigDecimal(0.0));
    public static Produto produtoAtivo = new Produto(0,"",new BigDecimal(0.0),"");
    
    public static List<Cliente> ListaClientes = null;
    public static List<Atendente> ListaAtendetes = null;
    public static List<Comanda> ListaComanda = null;
    public static List<Mesa> ListaMesa = null;
    public static List<Pagamento> ListaPagamento = null;
    public static List<Pedido> ListaPedido = null;
    public static List<Produto> ListaProduto = null;
        
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(Constants.FULL_DB_URL, Constants.USER, Constants.PASSWORD);
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                } catch (Exception e) {
                }                                                                                                                
                JFrame Splash = new Splash();                
                Splash.setVisible(true);                                                                                                      
            }
        });
    }
    
    @Override
    public void init() {
        fxContainer = new JFXPanel();
        fxContainer.setPreferredSize(new Dimension(JFXPANEL_WIDTH_INT, JFXPANEL_HEIGHT_INT));
        add(fxContainer, BorderLayout.CENTER);
        // create JavaFX scene
        Platform.runLater(new Runnable() {
            
            @Override
            public void run() {
                createScene();
            }
        });
    }
    
    private void createScene() {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        fxContainer.setScene(new Scene(root));
       
    }
    
    public static void CarregaListas(){
     
    ListaClientes = application.MainApp.clienteController.listarClientes();
    ListaAtendetes = application.MainApp.atendenteController.listarAtendentes();
    ListaComanda = application.MainApp.comandaController.listarComandas();
    ListaMesa = application.MainApp.mesaController.listarMesas();
    ListaPagamento = application.MainApp.pagamentoController.listarPagamentos();
    ListaPedido = application.MainApp.pedidoController.listarPedidos();
    ListaProduto = application.MainApp.produtoController.listarProdutos();
        
    }

            

    
}
