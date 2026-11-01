package br.edu.ifsp.xyz.model;

import java.util.ArrayList;

import br.edu.ifsp.xyz.service.Leitor;

public class Categoria {
    private int idCategoria;
    private String nome;
    private double percentualVendedor;
    private double percentualRepresentante;

    /** Construtor que carrega uma categoria de um arquivo. */
    public Categoria(String caminho, int chave, String valorChave) throws Exception {
        Leitor leitor = new Leitor(caminho, chave, valorChave);
        ArrayList<String> categorias = leitor.conteudo();
        if (categorias.isEmpty()) {
            throw new Exception("Categoria não encontrada para a chave: " + valorChave);
        }
        String[] campos = categorias.get(0).split(";", -1);
        this.idCategoria = Integer.parseInt(campos[0]);
        this.nome = campos[1];
        this.percentualVendedor = Double.parseDouble(campos[2]);
        this.percentualRepresentante = Double.parseDouble(campos[3]);
    }

    /** Construtor sobrecarregado que recebe os dados diretamente. */
    public Categoria(int idCategoria, String nome, double percentualVendedor,
                     double percentualRepresentante) {
        this.idCategoria = idCategoria;
        this.nome = nome;
        this.percentualVendedor = percentualVendedor;
        this.percentualRepresentante = percentualRepresentante;
    }

    public int getIdCategoria() { return idCategoria; }
    public String getNome() { return nome; }
    public double getPercentualVendedor() { return percentualVendedor; }
    public double getPercentualRepresentante() { return percentualRepresentante; }

    @Override
    public String toString() {
        return "Categoria [idCategoria=" + idCategoria + ", nome=" + nome
                + ", percentualVendedor=" + percentualVendedor
                + ", percentualRepresentante=" + percentualRepresentante + "]";
    }
}
