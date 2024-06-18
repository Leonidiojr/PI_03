package model;


public class Cliente {
    private int idCliente;
    private String nomeCliente;
    private String telefoneCliente;
    private String emailCliente;

    // Construtor
    public Cliente(int idCliente, String nomeCliente, String telefoneCliente, String emailCliente) {
        this.idCliente = idCliente;
        this.nomeCliente = nomeCliente;
        this.telefoneCliente = telefoneCliente;
        this.emailCliente = emailCliente;
    }

    // Métodos getters e setters
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getTelefoneCliente() {
        return telefoneCliente;
    }

    public void setTelefoneCliente(String telefoneCliente) {
        this.telefoneCliente = telefoneCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    // Método para exibir informações do cliente
    public void exibirInformacoes() {
        System.out.println("ID do Cliente: " + idCliente);
        System.out.println("Nome do Cliente: " + nomeCliente);
        System.out.println("Telefone do Cliente: " + telefoneCliente);
        System.out.println("Email do Cliente: " + emailCliente);
    }
}
