package br.edu.ifsp.xyz.model;

/** Outro tipo de pessoa que recebe comissão. */
public class Representante extends Pessoa {
    public Representante(String nome) {
        super(nome);
    }

    @Override
    public double calcularComissao(double valorVenda, double percentual) {
        return valorVenda * percentual / 100.0;
    }

    @Override
    public String toString() {
        return "Representante [nome=" + getNome() + "]";
    }
}
