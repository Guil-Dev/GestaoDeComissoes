package br.edu.ifsp.xyz.teste;

import br.edu.ifsp.xyz.model.*;

public class CategoriaTeste {
    public static void main(String[] args) throws Exception {
        Categoria categoria = new Categoria("dados/Categoria.txt", 0, "201");
        System.out.println("---> " + categoria);
    }
}