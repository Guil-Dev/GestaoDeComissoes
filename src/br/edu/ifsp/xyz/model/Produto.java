package br.edu.ifsp.xyz.model;

import java.util.ArrayList;

import br.edu.ifsp.xyz.service.Leitor;

public class Produto {
    private int idProduto;
    private String codigo;
    private double preco;
    private Categoria categoria;

    /** Construtor que lê o produto e sua categoria dos arquivos. */
    public Produto(String caminho, int chave, String valorChave) throws Exception {
        ArrayList<String> produtos = new Leitor(caminho, chave, valorChave).conteudo();
        if (produtos.isEmpty()) {
            throw new Exception("Produto não encontrado para a chave: " + valorChave);
        }
        String[] campos = produtos.get(0).split(";", -1);
        this.idProduto = Integer.parseInt(campos[0]);
        this.codigo = campos[1];
        this.preco = Double.parseDouble(campos[2]);
        this.categoria = new Categoria("Categoria.txt", 0, campos[3]);
    }

    /** Construtor sobrecarregado que recebe os dados diretamente. */
    public Produto(int idProduto, String codigo, double preco, Categoria categoria) {
        this.idProduto = idProduto;
        this.codigo = codigo;
        this.preco = preco;
        this.categoria = categoria;
    }

    public int getIdProduto() { return idProduto; }
    public String getCodigo() { return codigo; }
    public double getPreco() { return preco; }
    public Categoria getCategoria() { return categoria; }

    @Override
    public String toString() {
        return "Produto [idProduto=" + idProduto + ", codigo=" + codigo
                + ", preco=" + String.format(java.util.Locale.US, "%.2f", preco)
                + ", categoria=" + categoria + "]";
    }
}
