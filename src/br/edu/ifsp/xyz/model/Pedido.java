package br.edu.ifsp.xyz.model;

import java.util.ArrayList;
import java.util.Arrays;
import br.edu.ifsp.xyz.service.Leitor;

public class Pedido {
    private int idPedido;
    private Vendedor vendedor;
    private double valor;
    private boolean desfeito;
    private ItemPedido[] itens;

    /** Construtor que carrega pedido, vendedor, itens e produtos dos arquivos. */
    public Pedido(String caminho, int chave, String valorChave) throws Exception {
        ArrayList<String> pedidos = new Leitor(caminho, chave, valorChave).conteudo();
        if (pedidos.isEmpty()) {
            throw new Exception("Pedido não encontrado para a chave: " + valorChave);
        }
        String[] campos = pedidos.get(0).split(";", -1);
        this.idPedido = Integer.parseInt(campos[0]);
        this.vendedor = new Vendedor("Vendedor.txt", 0, campos[1]);
        this.valor = Double.parseDouble(campos[2]);
        this.desfeito = Boolean.parseBoolean(campos[3]);

        ArrayList<String> itensLidos = new Leitor(
                "ItemPedido.txt", 1, String.valueOf(idPedido)).conteudo();
        this.itens = new ItemPedido[itensLidos.size()];
        for (int i = 0; i < itensLidos.size(); i++) {
            campos = itensLidos.get(i).split(";", -1);
            int idItem = Integer.parseInt(campos[0]);
            int idPedidoLido = Integer.parseInt(campos[1]);
            Produto produto = new Produto("Produto.txt", 0, campos[2]);
            int quantidade = Integer.parseInt(campos[3]);
            this.itens[i] = new ItemPedido(idItem, idPedidoLido, produto, quantidade);
        }
    }

    /** Construtor sobrecarregado para criar pedido diretamente. */
    public Pedido(int idPedido, Vendedor vendedor, double valor,
                  boolean desfeito, ItemPedido[] itens) {
        this.idPedido = idPedido;
        this.vendedor = vendedor;
        this.valor = valor;
        this.desfeito = desfeito;
        this.itens = itens;
    }

    public int getIdPedido() { return idPedido; }
    public Vendedor getVendedor() { return vendedor; }
    public double getValor() { return valor; }
    public boolean isDesfeito() { return desfeito; }
    public ItemPedido[] getItens() { return itens; }

    public double calcularValorItens() {
        double total = 0.0;
        for (ItemPedido item : itens) total += item.calcularValorVenda();
        return total;
    }

    @Override
    public String toString() {
        return "Pedido [idPedido=" + idPedido + ", vendedor=" + vendedor
                + ", valor=" + valor + ", desfeito=" + desfeito
                + ", itens=" + Arrays.toString(itens) + "]";
    }
}
