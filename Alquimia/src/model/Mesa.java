package model;

import controler.StatusMesa; // Importa o enum StatusMesa do pacote correto

public class Mesa {
    private int numeroMesa;
    private StatusMesa statusMesa; // Alterado para o tipo do enum StatusMesa
    private int mesaAtiva;

    // Construtor
    public Mesa(int numeroMesa, StatusMesa statusMesa) { // Ajustado para usar StatusMesa
        this.numeroMesa = numeroMesa;
        this.statusMesa = statusMesa;
    }

    // Métodos getters e setters
    public int getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public StatusMesa getStatusMesa() {
        return statusMesa;
    }

    public void setStatusMesa(StatusMesa statusMesa) {
        this.statusMesa = statusMesa;
    }

    // Método para exibir informações da mesa
    public void exibirInformacoes() {
        System.out.println("Número da Mesa: " + numeroMesa);
        System.out.println("Status da Mesa: " + statusMesa);
    }

    public int getMesaAtiva() {
        return mesaAtiva;
    }

    public void setMesaAtiva(int mesaAtiva) {
        this.mesaAtiva = mesaAtiva;
    }
}
