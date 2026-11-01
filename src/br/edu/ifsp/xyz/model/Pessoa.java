package br.edu.ifsp.xyz.model;

/** Superclasse para pessoas que recebem comissão. */
public abstract class Pessoa {
    private String nome;

    public Pessoa(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    /** Permite que subclasses que carregam dados de arquivo atualizem o nome. */
    protected void setNome(String nome) {
        this.nome = nome;
    }

    public abstract double calcularComissao(double valorVenda, double percentual);
}
