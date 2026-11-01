package br.edu.ifsp.xyz.teste;

import br.edu.ifsp.xyz.model.*;

public class VendedorTeste {
    public static void main(String[] args) throws Exception {
        Vendedor vendedor = new Vendedor("dados/Vendedor.txt", 0, "101");
        System.out.println("---> " + vendedor);
    }
}