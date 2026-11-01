package br.edu.ifsp.xyz.model;

import java.util.ArrayList;

import br.edu.ifsp.xyz.service.Leitor;

public class Vendedor extends Pessoa {
    private int idVendedor;
    private String cpf;

    /** Construtor que recebe os dados diretamente. */
    public Vendedor(String nome, String cpf) {
        super(nome);
        this.idVendedor = 0;
        this.cpf = cpf;
    }

    /** Construtor que procura um vendedor em arquivo. */
    public Vendedor(String caminho, int chave, String valorChave) throws Exception {
        super("Vendedor não identificado");
        ArrayList<String> vendedores = new Leitor(caminho, chave, valorChave).conteudo();
        if (vendedores.isEmpty()) {
            throw new Exception("Vendedor não encontrado para a chave: " + valorChave);
        }
        String[] campos = vendedores.get(0).split(";", -1);
        this.idVendedor = Integer.parseInt(campos[0]);
        this.cpf = campos[1];
        setNome(campos[2]);
    }

    public int getIdVendedor() { return idVendedor; }
    public String getCpf() { return cpf; }

    @Override
    public double calcularComissao(double valorVenda, double percentual) {
        return valorVenda * percentual / 100.0;
    }

    @Override
    public String toString() {
        return "Vendedor [idVendedor=" + idVendedor + ", cpf=" + cpf
                + ", nome=" + getNome() + "]";
    }
}
