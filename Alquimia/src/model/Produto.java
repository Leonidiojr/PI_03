package model;


import java.math.BigDecimal;

public class Produto {
    private int idProduto;
    private String descricaoProduto;
    private BigDecimal valorUnitarioProduto;
    private String imagemProduto;

    // Construtor
    public Produto(int idProduto, String descricaoProduto, BigDecimal valorUnitarioProduto, String imagemProduto) {
        this.idProduto = idProduto;
        this.descricaoProduto = descricaoProduto;
        this.valorUnitarioProduto = valorUnitarioProduto;
        this.imagemProduto = imagemProduto;
    }

    // Métodos getters e setters
    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public BigDecimal getValorUnitarioProduto() {
        return valorUnitarioProduto;
    }

    public void setValorUnitarioProduto(BigDecimal valorUnitarioProduto) {
        this.valorUnitarioProduto = valorUnitarioProduto;
    }

    public String getImagemProduto() {
        return imagemProduto;
    }

    public void setImagemProduto(String imagemProduto) {
        this.imagemProduto = imagemProduto;
    }

    // Método para exibir informações do produto
    public void exibirInformacoes() {
        System.out.println("ID do Produto: " + idProduto);
        System.out.println("Descrição do Produto: " + descricaoProduto);
        System.out.println("Valor Unitário do Produto: " + valorUnitarioProduto);
        System.out.println("Imagem do Produto: " + imagemProduto);
    }
}
