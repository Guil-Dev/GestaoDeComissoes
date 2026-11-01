package br.edu.ifsp.xyz.teste;

import br.edu.ifsp.xyz.model.*;

public class PedidoTeste {
    public static void main(String[] args) throws Exception {
        Pedido pedido = new Pedido("dados/Pedido.txt", 0, "801");
        System.out.println("---> " + pedido);
    }
}