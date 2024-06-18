package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Pagamento {
    private int idPagamento;
    private LocalDateTime dataPagamento;
    private BigDecimal valorPagamento;
    private String tipoPagamento;

    // Construtor
    public Pagamento(int idPagamento, LocalDateTime dataPagamento, BigDecimal valorPagamento, String tipoPagamento) {
        this.idPagamento = idPagamento;
        this.dataPagamento = dataPagamento;
        this.valorPagamento = valorPagamento;
        this.tipoPagamento = tipoPagamento;
    }

    // Métodos getters e setters
    public int getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(int idPagamento) {
        this.idPagamento = idPagamento;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public BigDecimal getValorPagamento() {
        return valorPagamento;
    }

    public void setValorPagamento(BigDecimal valorPagamento) {
        this.valorPagamento = valorPagamento;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    // Método para exibir informações do pagamento
    public void exibirInformacoes() {
        System.out.println("ID do Pagamento: " + idPagamento);
        System.out.println("Data do Pagamento: " + dataPagamento);
        System.out.println("Valor do Pagamento: " + valorPagamento);
        System.out.println("Tipo de Pagamento: " + tipoPagamento);
    }
}
