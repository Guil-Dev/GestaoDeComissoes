package br.edu.ifsp.xyz.teste;

import br.edu.ifsp.xyz.model.*;

public class ProdutoTeste {
    public static void main(String[] args) throws Exception {
        Produto produto = new Produto("dados/Produto.txt", 0, "301");
        System.out.println("---> " + produto);
    }
}