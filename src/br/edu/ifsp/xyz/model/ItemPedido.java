package br.edu.ifsp.xyz.model;

public class ItemPedido {
    private int idItem;
    private int idPedido;
    private Produto produto;
    private int qtVenda;

    public ItemPedido(int idItem, int idPedido, Produto produto, int qtVenda) {
        this.idItem = idItem;
        this.idPedido = idPedido;
        this.produto = produto;
        this.qtVenda = qtVenda;
    }

    public int getIdItem() { return idItem; }
    public int getIdPedido() { return idPedido; }
    public Produto getProduto() { return produto; }
    public int getQtVenda() { return qtVenda; }

    public double calcularValorVenda() {
        return produto.getPreco() * qtVenda;
    }

    @Override
    public String toString() {
        return "ItemPedido [idItem=" + idItem + ", idPedido=" + idPedido
                + ", produto=" + produto + ", qtVenda=" + qtVenda
                + ", valorVenda=" + String.format(java.util.Locale.US, "%.2f", calcularValorVenda()) + "]";
    }
}
