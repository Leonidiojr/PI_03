package model;

import java.time.LocalDateTime;

public class Comanda {
    private int idComanda;
    private LocalDateTime dataComanda;
    private int numeroMesa;
    private Cliente cliente; // Novo campo para o cliente associado à comanda
    private String status_pagamento;

    // Construtor
    public Comanda(int idComanda, LocalDateTime dataComanda, int numeroMesa, Cliente cliente, String status_pagamento) {
        this.idComanda = idComanda;
        this.dataComanda = dataComanda;
        this.numeroMesa = numeroMesa;
        this.cliente = cliente;
        this.status_pagamento = status_pagamento;
    }

    // Métodos getters e setters
    public int getIdComanda() {
        return idComanda;
    }

    public void setIdComanda(int idComanda) {
        this.idComanda = idComanda;
    }

    public String getStatus_pagamento() {
        return status_pagamento;
    }

    public void setStatus_pagamento(String status_pagamento) {
        this.status_pagamento = status_pagamento;
    }
    
    public LocalDateTime getDataComanda() {
        return dataComanda;
    }

    public void setDataComanda(LocalDateTime dataComanda) {
        this.dataComanda = dataComanda;
    }

    public int getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    // Método para exibir informações da comanda
    public void exibirInformacoes() {
        System.out.println("ID da Comanda: " + idComanda);
        System.out.println("Data da Comanda: " + dataComanda);
        System.out.println("Número da Mesa: " + numeroMesa);
        if (cliente != null) {
            System.out.println("Cliente: " + cliente.getNomeCliente()); // Exemplo: obtendo o nome do cliente
        } else {
            System.out.println("Cliente: Nenhum cliente associado.");
        }
        System.out.println("Status do pagamento: " + status_pagamento);
    }
}
