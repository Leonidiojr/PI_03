package model;

import controler.AtendenteController;
public class Atendente {
    private int idAtendente;
    private String nomeAtendente;
    private String telefoneAtendente;
    private String emailAtendente;

    // Construtor
    public Atendente(int idAtendente, String nomeAtendente, String telefoneAtendente, String emailAtendente) {
        this.idAtendente = idAtendente;
        this.nomeAtendente = nomeAtendente;
        this.telefoneAtendente = telefoneAtendente;
        this.emailAtendente = emailAtendente;                 
    }

    // Métodos getters e setters
    public int getIdAtendente() {
        return idAtendente;
    }

    public void setIdAtendente(int idAtendente) {
        this.idAtendente = idAtendente;
    }

    public String getNomeAtendente() {
        return nomeAtendente;
    }

    public void setNomeAtendente(String nomeAtendente) {
        this.nomeAtendente = nomeAtendente;
    }

    public String getTelefoneAtendente() {
        return telefoneAtendente;
    }

    public void setTelefoneAtendente(String telefoneAtendente) {
        this.telefoneAtendente = telefoneAtendente;
    }

    public String getEmailAtendente() {
        return emailAtendente;
    }

    public void setEmailAtendente(String emailAtendente) {
        this.emailAtendente = emailAtendente;
    }

    // Método para exibir informações do cliente
    public void exibirInformacoes() {
        System.out.println("ID do Atendente: " + idAtendente);
        System.out.println("Nome do Atendente: " + nomeAtendente);
        System.out.println("Telefone do Atendente: " + telefoneAtendente);
        System.out.println("Email do Atendente: " + emailAtendente);
    }
}
